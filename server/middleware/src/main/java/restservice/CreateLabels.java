package restservice;

import operations.SPARQLOperations;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateRequest;
import org.eclipse.rdf4j.sparqlbuilder.core.SparqlBuilder;
import org.eclipse.rdf4j.sparqlbuilder.core.Variable;
import org.eclipse.rdf4j.sparqlbuilder.core.query.InsertDataQuery;
import org.eclipse.rdf4j.sparqlbuilder.core.query.Queries;
import org.eclipse.rdf4j.sparqlbuilder.core.query.SelectQuery;
import utils.Constants;

import java.util.ArrayList;
import java.util.HashMap;

import static org.eclipse.rdf4j.sparqlbuilder.rdf.Rdf.iri;


public class CreateLabels {
    public String host;
    SPARQLOperations conn;

    public CreateLabels(String host) {
        this.host = host;
        this.conn = new SPARQLOperations(this.host);
    }

    public void createLabels() throws Exception {
        if (hasCreatedLabels()) return;

        createComposerLabels();
        createMusicalWorkLabels();
        createBirthLabels();
        createDeathLabels();
    }

    public void createComposerLabels() throws Exception {
        ArrayList<HashMap<String, String>> composers = conn.executeQuery(getAllComposers());

        ArrayList<UpdateRequest> insertQueries = new ArrayList<>();

        for (HashMap<String, String> composer: composers) {
            String composerURIString = composer.get("composer");
            String composerName = composer.get("name");

            InsertDataQuery insertDataQuery = Queries.INSERT_DATA().prefix(Constants.rdfs).insertData(
                    iri(composerURIString).has(Constants.rdfs.iri("label"), composerName)
            );

            insertQueries.add(UpdateFactory.create(insertDataQuery.getQueryString()));
        }

        conn.executeUpdateList(insertQueries);
    }

    public Query getAllComposers() {
        Variable composer = SparqlBuilder.var("composer");
        Variable name = SparqlBuilder.var("name");

        SelectQuery selectQuery = Queries.SELECT().prefix(Constants.rdf).prefix(Constants.foaf).prefix(Constants.type).prefix(Constants.owl).distinct().
                select(composer, name).
                where(
                        composer.has(Constants.rdf.iri("type"),  Constants.type.iri("Composer")).
                                andHas(Constants.foaf.iri("name"), name)
                );

        return QueryFactory.create(selectQuery.getQueryString());
    }

    public void createMusicalWorkLabels() throws Exception {
        ArrayList<HashMap<String, String>> musicalWorks = conn.executeQuery(getAllMusicalWorks());

        ArrayList<UpdateRequest> insertQueries = new ArrayList<>();

        for (HashMap<String, String> musicalWork: musicalWorks) {
            String musicalWorkURIString = musicalWork.get("musicalWork");
            String musicalWorkTitle = musicalWork.get("title");

            InsertDataQuery insertDataQuery = Queries.INSERT_DATA().prefix(Constants.rdfs).insertData(
                    iri(musicalWorkURIString).has(Constants.rdfs.iri("label"), musicalWorkTitle)
            );

            insertQueries.add(UpdateFactory.create(insertDataQuery.getQueryString()));
        }

        conn.executeUpdateList(insertQueries);
    }

    public Query getAllMusicalWorks() {
        Variable musicalWork = SparqlBuilder.var("musicalWork");
        Variable title = SparqlBuilder.var("title");

        SelectQuery selectQuery = Queries.SELECT().prefix(Constants.rdf).prefix(Constants.ns2).prefix(Constants.dc).distinct().
                select(musicalWork, title).
                where(
                        musicalWork.has(Constants.rdf.iri("type"), Constants.ns2.iri("MusicalWork")).
                                andHas(Constants.dc.iri("title"), title)
                );

        return QueryFactory.create(selectQuery.getQueryString());
    }

    public void createBirthLabels() throws Exception {
        ArrayList<HashMap<String, String>> births = conn.executeQuery(getAllBirths());

        ArrayList<UpdateRequest> insertQueries = new ArrayList<>();

        for (HashMap<String, String> birth: births) {
            String birthURIString = birth.get("birth");
            String birthDate = birth.get("date");

            InsertDataQuery insertDataQuery = Queries.INSERT_DATA().prefix(Constants.rdfs).insertData(
                    iri(birthURIString).has(Constants.rdfs.iri("label"), birthDate)
            );

            insertQueries.add(UpdateFactory.create(insertDataQuery.getQueryString()));
        }

        conn.executeUpdateList(insertQueries);
    }

    public Query getAllBirths() {
        Variable birth = SparqlBuilder.var("birth");
        Variable date = SparqlBuilder.var("date");

        SelectQuery selectQuery = Queries.SELECT().prefix(Constants.rdf).prefix(Constants.ns5).distinct().
                select(birth, date).
                where(
                        birth.has(Constants.rdf.iri("type"), Constants.ns5.iri("Birth")).
                                andHas(Constants.ns5.iri("date"), date)
                );

        return QueryFactory.create(selectQuery.getQueryString());
    }

    public void createDeathLabels() throws Exception {
        ArrayList<HashMap<String, String>> deaths = conn.executeQuery(getAllDeaths());

        ArrayList<UpdateRequest> insertQueries = new ArrayList<>();

        for (HashMap<String, String> death: deaths) {
            String deathURIString = death.get("death");
            String deathDate = death.get("date");

            InsertDataQuery insertDataQuery = Queries.INSERT_DATA().prefix(Constants.rdfs).insertData(
                    iri(deathURIString).has(Constants.rdfs.iri("label"), deathDate)
            );

            insertQueries.add(UpdateFactory.create(insertDataQuery.getQueryString()));
        }

        conn.executeUpdateList(insertQueries);
    }

    public Query getAllDeaths() {
        Variable death = SparqlBuilder.var("death");
        Variable date = SparqlBuilder.var("date");

        SelectQuery selectQuery = Queries.SELECT().prefix(Constants.rdf).prefix(Constants.ns5).distinct().
                select(death, date).
                where(
                        death.has(Constants.rdf.iri("type"), Constants.ns5.iri("Death")).
                                andHas(Constants.ns5.iri("date"), date)
                );

        return QueryFactory.create(selectQuery.getQueryString());
    }

    public boolean hasCreatedLabels() {
        Variable subject = SparqlBuilder.var("subject");
        Variable label = SparqlBuilder.var("label");

        SelectQuery selectQuery = Queries.SELECT().prefix(Constants.rdfs).
                select(subject).
                where(
                        subject.has(Constants.rdfs.iri("label"), label)
                ).limit(1);

        Query query = QueryFactory.create(selectQuery.getQueryString());
        ArrayList<HashMap<String, String>> resourceWithLabel = conn.executeQuery(query);

        return resourceWithLabel.size() != 0;
    }
}
