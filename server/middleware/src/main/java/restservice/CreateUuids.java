package restservice;

import operations.SPARQLOperations;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.sparql.modify.request.UpdateData;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateRequest;
import org.eclipse.rdf4j.sparqlbuilder.core.SparqlBuilder;
import org.eclipse.rdf4j.sparqlbuilder.core.Variable;
import org.eclipse.rdf4j.sparqlbuilder.core.query.DeleteDataQuery;
import org.eclipse.rdf4j.sparqlbuilder.core.query.InsertDataQuery;
import org.eclipse.rdf4j.sparqlbuilder.core.query.Queries;
import org.eclipse.rdf4j.sparqlbuilder.core.query.SelectQuery;
import utils.Constants;
import utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import static org.eclipse.rdf4j.sparqlbuilder.rdf.Rdf.iri;

public class CreateUuids {
    public String host;
    SPARQLOperations conn;

    public CreateUuids(String host) {
        this.host = host;
        this.conn = new SPARQLOperations(this.host);
    }

    public void createUuids() throws Exception {
        if (hasCreatedUuids()) return;

        Query getAllResourcesQuery = Utils.getAllResources();
        ArrayList<HashMap<String, String>> resources = conn.executeQuery(getAllResourcesQuery);

        ArrayList<UpdateRequest> insertQueries = new ArrayList<>();

        for (HashMap<String, String> resource: resources) {
            String dbtuneResourceIriString = resource.get("subject");
            String uuid = UUID.randomUUID().toString();
            String newURI = String.format("http://example.org/linkedclassical/resource/%s", uuid);

            String updateURIsQuery = String.format("DELETE {\n" +
                    "  ?subject ?predicate <%s> .\n" +
                    "}\n" +
                    "INSERT {\n" +
                    "\t?subject ?predicate <%s>\n" +
                    "}\n" +
                    "WHERE {\n" +
                    "  ?subject ?predicate <%s> .\n" +
                    "};\n" +
                    "\n" +
                    "DELETE {\n" +
                    "  <%s> ?predicate ?object .\n" +
                    "}\n" +
                    "INSERT {\n" +
                    "\t<%s> ?predicate ?object\n" +
                    "}\n" +
                    "WHERE {\n" +
                    "  <%s> ?predicate ?object .\n" +
                    "}", dbtuneResourceIriString, newURI, dbtuneResourceIriString, dbtuneResourceIriString, newURI, dbtuneResourceIriString);


            InsertDataQuery insertDataQuery = Queries.INSERT_DATA().prefix(Constants.rdf).prefix(Constants.owl).insertData(
                iri(newURI).has(Constants.rdf.iri("id"), uuid).
                        andHas(Constants.owl.iri("sameAs"), iri(dbtuneResourceIriString))
            );

            insertQueries.add(UpdateFactory.create(updateURIsQuery));
            insertQueries.add(UpdateFactory.create(insertDataQuery.getQueryString()));
        }

        conn.executeUpdateList(insertQueries);
    }

    public boolean hasCreatedUuids() {
        Variable subject = SparqlBuilder.var("subject");
        Variable object = SparqlBuilder.var("object");
        Variable uuid = SparqlBuilder.var("uuid");

        SelectQuery selectQuery = Queries.SELECT().prefix(Constants.rdf).
                select(subject).
                where(
                        subject.has(Constants.rdf.iri("type"), object).
                        andHas(Constants.rdf.iri("id"), uuid)
                ).limit(1);

        Query query = QueryFactory.create(selectQuery.getQueryString());
        ArrayList<HashMap<String, String>> resourceWithUuid = conn.executeQuery(query);

        return resourceWithUuid.size() != 0;
    }
}
