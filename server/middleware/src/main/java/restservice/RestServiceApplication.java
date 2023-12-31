package restservice;

import operations.SPARQLOperations;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import utils.Datasets;

@SpringBootApplication(scanBasePackages = {"controllers"})
public class RestServiceApplication {

    public static void main(String[] args) {

        ApplicationContext applicationContext = SpringApplication.run(RestServiceApplication.class, args);

        Datasets.setHost(applicationContext.getEnvironment().getProperty("config.fusekiHost"));
        Datasets datasets = new Datasets();

        try {
            // DEFAULT HOST
            SPARQLOperations cn = new SPARQLOperations(datasets.getDatasetHostname("default"));

            cn.importOWL(datasets.getDatasetFiles("default"));

            System.out.println("===========================Creating UUIDS==========================================");

            new CreateUuids(datasets.getDatasetHostname("default")).createUuids();

            System.out.println("===========================Creating Labels==========================================");

            new CreateLabels(datasets.getDatasetHostname("default")).createLabels();

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("===========================REST API Ready==========================================");
    }

}