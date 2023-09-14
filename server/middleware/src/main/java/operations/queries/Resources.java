package operations.queries;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateRequest;
import org.eclipse.rdf4j.sparqlbuilder.constraint.Expressions;
import org.eclipse.rdf4j.sparqlbuilder.core.SparqlBuilder;
import org.eclipse.rdf4j.sparqlbuilder.core.Variable;
import org.eclipse.rdf4j.sparqlbuilder.core.query.InsertDataQuery;
import org.eclipse.rdf4j.sparqlbuilder.core.query.ModifyQuery;
import org.eclipse.rdf4j.sparqlbuilder.core.query.Queries;
import org.eclipse.rdf4j.sparqlbuilder.core.query.SelectQuery;
import org.eclipse.rdf4j.sparqlbuilder.rdf.Iri;
import utils.Constants;

import java.util.ArrayList;

import static org.eclipse.rdf4j.sparqlbuilder.rdf.Rdf.iri;

public class Resources {

    public Resources() {}

    public Query getURI(String uuid) {
        Variable resource = SparqlBuilder.var("resource");

        SelectQuery selectQuery = Queries.SELECT().distinct().prefix(Constants.rdf).
                select(resource).
                where(
                        resource.has(Constants.rdf.iri("id"), uuid)
                );

        return QueryFactory.create(selectQuery.getQueryString());
    }

    public Query getOutgoingRelations(String uuid) {
        Variable resource = SparqlBuilder.var("resource");
        Variable predicate = SparqlBuilder.var("predicate");
        Variable object = SparqlBuilder.var("object");
        Variable objectUuid = SparqlBuilder.var("objectUuid");

        SelectQuery selectQuery = Queries.SELECT().distinct().prefix(Constants.rdf).prefix(Constants.type).prefix(Constants.ns2).
                select(predicate, object, objectUuid).
                where(
                        resource.has(predicate, object).
                            andHas(Constants.rdf.iri("id"), uuid).
                            and(object.has(Constants.rdf.iri("id"), objectUuid).optional())
                );

        return QueryFactory.create(selectQuery.getQueryString());
    }

    public Query getIncomingRelations(String uuid) {
        Variable resource = SparqlBuilder.var("resource");
        Variable predicate = SparqlBuilder.var("predicate");
        Variable subject = SparqlBuilder.var("subject");
        Variable subjectUuid = SparqlBuilder.var("subjectUuid");

        SelectQuery selectQuery = Queries.SELECT().distinct().prefix(Constants.rdf).prefix(Constants.type).prefix(Constants.ns2).
                select(subject, subjectUuid, predicate).
                where(
                        subject.has(predicate, resource).
                            and(resource.has(Constants.rdf.iri("id"), uuid)).
                            and(subject.has(Constants.rdf.iri("id"), subjectUuid).optional())
                );

        return QueryFactory.create(selectQuery.getQueryString());
    }

    public Query getResourceLabel(String uuid) {
        Variable resource = SparqlBuilder.var("resource");
        Variable label = SparqlBuilder.var("label");

        SelectQuery selectQuery = Queries.SELECT().distinct().prefix(Constants.rdf).prefix(Constants.rdfs).
                select(label).
                where(
                        resource.has(Constants.rdf.iri("id"), uuid).
                                andHas(Constants.rdfs.iri("label"), label)
                );

        return QueryFactory.create(selectQuery.getQueryString());
    }

    public Query getType(String uuid) {
        Variable resource = SparqlBuilder.var("resource");
        Variable type = SparqlBuilder.var("type");

        SelectQuery selectQuery = Queries.SELECT().distinct().prefix(Constants.rdf).
                select(type).
                where(
                        resource.has(Constants.rdf.iri("id"), uuid).
                                andHas(Constants.rdf.iri("type"), type)
                );

        return QueryFactory.create(selectQuery.getQueryString());
    }

    public Query getExternalDatasets() {
        Variable dataset = SparqlBuilder.var("dataset");
        Variable title = SparqlBuilder.var("title");
        Variable sparqlEndpoint = SparqlBuilder.var("sparqlEndpoint");

        SelectQuery selectQuery = Queries.SELECT().prefix(Constants.rdf).prefix(Constants.voidPrefix).prefix(Constants.dcterms).
                select(dataset, title, sparqlEndpoint).distinct().
                where(
                        dataset.has(Constants.rdf.iri("type"), Constants.voidPrefix.iri("Dataset")).
                                andHas(Constants.dcterms.iri("title"), title).
                                andHas(Constants.voidPrefix.iri("sparqlEndpoint"), sparqlEndpoint).
                                filter(Expressions.not(Expressions.regex(title, "classical", "i")))
                );

        return QueryFactory.create(selectQuery.getQueryString());
    }

    public ArrayList<UpdateRequest> linkExternalEntity(String linkedClassicalEntityURIString, String externalEntityURIString, String externalDatasetURIString){
        Iri externalEntityURI = iri(externalEntityURIString);
        Iri linkedClassicalEntityURI = iri(linkedClassicalEntityURIString);
        Iri externalDatasetURI = iri(externalDatasetURIString);

        InsertDataQuery sameAsInsertQuery = Queries.INSERT_DATA().prefix(Constants.owl).
                insertData(
                        linkedClassicalEntityURI.has(Constants.owl.iri("sameAs"), externalEntityURI)
                );

        InsertDataQuery inDatasetInsertQuery = Queries.INSERT_DATA().prefix(Constants.voidPrefix).
                insertData(
                        externalEntityURI.has(Constants.voidPrefix.iri("inDataset"), externalDatasetURI)
                );

        ArrayList<UpdateRequest> res = new ArrayList<>();
        res.add(UpdateFactory.create(sameAsInsertQuery.getQueryString()));
        res.add(UpdateFactory.create(inDatasetInsertQuery.getQueryString()));

        return res;
    }

    public ArrayList<UpdateRequest> deleteExternalEntityLink(String linkedClassicalEntityURIString,
                                                             String externalEntityURIString) {
        Iri linkedClassicalEntityURI = iri(linkedClassicalEntityURIString);
        Iri externalEntityURI = iri(externalEntityURIString);
        Variable anything = SparqlBuilder.var("anything");

        ModifyQuery deleteSameAsQuery = Queries.DELETE().prefix(Constants.owl).where(
                        linkedClassicalEntityURI.has(Constants.owl.iri("sameAs"), externalEntityURI));

        ModifyQuery deleteInDatasetQuery = Queries.DELETE().prefix(Constants.voidPrefix).where(
                        externalEntityURI.has(Constants.voidPrefix.iri("inDataset"), anything));

        ArrayList<UpdateRequest> res = new ArrayList<>();
        res.add(UpdateFactory.create(deleteSameAsQuery.getQueryString()));
        res.add(UpdateFactory.create(deleteInDatasetQuery.getQueryString()));

        return res;
    }

    public Query getAvailableExternalDatasets(String uuid) {
        String uri = Constants.linkedClassicalString + uuid;

        Iri entity = iri(uri);
        Variable externalEntity = SparqlBuilder.var("externalEntity");
        Variable externalDataset = SparqlBuilder.var("externalDataset");

        SelectQuery selectQuery = Queries.SELECT().prefix(Constants.owl)
                .prefix(Constants.voidPrefix).select(externalDataset).distinct().where(
                        entity.has(Constants.owl.iri("sameAs"), externalEntity),
                        externalEntity.has(Constants.voidPrefix.iri("inDataset"),
                                externalDataset));

        return QueryFactory.create(selectQuery.getQueryString());
    }

    public Query getExternalInfoboxesWikidata(String uuid) {
        String entity = Constants.linkedClassicalString + uuid;

        String query = String.format("PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                "PREFIX void: <http://rdfs.org/ns/void#>\n" +
                "PREFIX wdt: <http://www.wikidata.org/prop/direct/>\n" +
                "PREFIX wd: <http://www.wikidata.org/entity/>\n" +
                "PREFIX cidoc_crm: <http://erlangen-crm.org/200717/>\n" +
                "PREFIX wikibase: <http://wikiba.se/ontology#>\n" +
                "\n" +
                "SELECT DISTINCT ?property ?propertyLabel ?value ?valueLabel \n" +
                "WHERE {\n" +
                "  <%s> owl:sameAs ?externalEntity .\n" +
                "  ?entity owl:sameAs ?externalEntity .\n" +
                "  ?externalEntity void:inDataset ?dataset .\n" +
                "  ?dataset void:sparqlEndpoint ?sparqlEndpoint .\n" +
                "  \n" +
                "  SERVICE ?sparqlEndpoint { \n" +
                "    ?externalEntity ?property ?value .\n" +
                "    ?propertyEntity wikibase:directClaim ?property .\n" +
                "    \n" +
                "    OPTIONAL {\n" +
                "      ?propertyEntity rdfs:label ?propertyLabel .\n" +
                "      FILTER (lang(?propertyLabel) = \"en\") .\n" +
                "    }\n" +
                "    \n" +
                "    OPTIONAL { \n" +
                "      ?value rdfs:label ?valueLabel .\n" +
                "      FILTER (lang(?valueLabel) = \"en\") .\n" +
                "    }\n" +
                "    \n" +
                "    FILTER ( IF (isLiteral(?value), lang(?value) = \"en\" || lang(?value) = \"\", TRUE) ) .\n" +
                "  }\n" +
                "}\n", entity);

        return QueryFactory.create(query);
    }

    public Query getExternalEntityWikidataSummary(String uuid) {
        String entity = Constants.linkedClassicalString + uuid;

        String query = String.format("PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                "PREFIX void: <http://rdfs.org/ns/void#>\n" +
                "PREFIX wdt: <http://www.wikidata.org/prop/direct/>\n" +
                "PREFIX cidoc_crm: <http://erlangen-crm.org/200717/>\n" +
                "PREFIX schema: <http://schema.org/>\n" +
                "\n" +
                "SELECT DISTINCT ?labelEn ?instanceOf ?instanceOfLabel ?description\n" +
                "WHERE {\n" +
                "  <%s> owl:sameAs ?externalEntity .\n" +
                "  ?externalEntity void:inDataset ?dataset .\n" +
                "  ?dataset void:sparqlEndpoint ?sparqlEndpoint .\n" +
                "  \n" +
                "  SERVICE ?sparqlEndpoint { \n" +
                "    ?externalEntity rdfs:label ?labelEn .\n" +
                "    filter (lang(?labelEn) = \"en\") .\n" +
                "    \n" +
                "    ?externalEntity wdt:P31 ?instanceOf .\n" +
                "    ?instanceOf rdfs:label ?instanceOfLabel .\n" +
                "    filter (lang(?instanceOfLabel) = \"en\") .\n" +
                "    \n" +
                "    ?externalEntity schema:description ?description .\n" +
                "    filter (lang(?description) = \"en\") .\n" +
                "  }\n" +
                "}", entity);

        return QueryFactory.create(query);
    }

    public Query searchComposer(String searchText) {
        Variable resource = SparqlBuilder.var("resource");
        Variable id = SparqlBuilder.var("id");
        Variable label = SparqlBuilder.var("label");

        SelectQuery selectQuery = Queries.SELECT().distinct().prefix(Constants.rdf).prefix(Constants.rdfs).
                select(resource, id, label).
                where(
                        resource.has(Constants.rdf.iri("id"), id).
                                andHas(Constants.rdfs.iri("label"), label).
                                filter(
                                        Expressions.regex(
                                                Expressions.str(label),
                                                searchText,
                                                "i"
                                        )
                                )
                ).limit(100);

        return QueryFactory.create(selectQuery.getQueryString());
    }

    public Query searchMusicalWork(String searchText) {
        Variable resource = SparqlBuilder.var("resource");
        Variable id = SparqlBuilder.var("id");
        Variable label = SparqlBuilder.var("label");

        SelectQuery selectQuery = Queries.SELECT().distinct().prefix(Constants.rdf).prefix(Constants.rdfs).
                select(resource, id, label).
                where(
                        resource.has(Constants.rdf.iri("id"), id).
                                andHas(Constants.rdfs.iri("label"), label).
                                filter(
                                        Expressions.regex(
                                                Expressions.str(label),
                                                searchText,
                                                "i"
                                        )
                                )
                ).limit(100);

        return QueryFactory.create(selectQuery.getQueryString());
    }
}
