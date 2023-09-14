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

import static org.eclipse.rdf4j.sparqlbuilder.rdf.Rdf.iri;

public class Composers {
    public Composers() {

    }

    /*
    # Equivalent query in SPARQL

    PREFIX type: <http://dbtune.org/classical/resource/type/>

    SELECT ?composer ?predicate ?object
    WHERE {
        ?composer a type:Composer .
        ?composer ?predicate ?object .

        filter (regex(str(?composer), "^http://dbtune.org/classical/resource/composer/uspensky_vladimir$")) .
    }
    */

    public Query getComposer(String id) {
        Variable composer = SparqlBuilder.var("composer");
        Variable predicate = SparqlBuilder.var("predicate");
        Variable object = SparqlBuilder.var("object");

        SelectQuery selectQuery = Queries.SELECT().distinct().prefix(Constants.type).prefix(Constants.rdf).
                select(composer, predicate, object).
                where(
                    composer.has(Constants.rdf.iri("type"), Constants.type.iri("Composer")).
                    andHas(predicate, object).
                    filter(
                        Expressions.regex(
                            Expressions.str(composer),
                            String.format("^http://dbtune.org/classical/resource/composer/%s$", id)
                        )
                    )
                );

        return QueryFactory.create(selectQuery.getQueryString());
    }

    public Query getIri(String id) {
        Variable composerIri = SparqlBuilder.var("composerIri");

        SelectQuery selectQuery = Queries.SELECT().distinct().prefix(Constants.rdf).
                select(composerIri).
                where(
                        composerIri.has(Constants.rdf.iri("id"), id)
                );

        return QueryFactory.create(selectQuery.getQueryString());
    }

    public Query getLabel(String id) {
        Variable composer = SparqlBuilder.var("composer");
        Variable label = SparqlBuilder.var("label");

        SelectQuery selectQuery = Queries.SELECT().distinct().prefix(Constants.rdf).prefix(Constants.rdfs).
                select(label).
                where(
                        composer.has(Constants.rdf.iri("id"), id).
                                andHas(Constants.rdfs.iri("label"), label)
                );

        return QueryFactory.create(selectQuery.getQueryString());
    }

    public Query getName(String id) {
        Variable composer = SparqlBuilder.var("composer");
        Variable name = SparqlBuilder.var("name");

        SelectQuery selectQuery = Queries.SELECT().distinct().prefix(Constants.rdf).prefix(Constants.foaf).
                select(name).
                where(
                        composer.has(Constants.rdf.iri("id"), id).
                                andHas(Constants.foaf.iri("name"), name)
                );

        return QueryFactory.create(selectQuery.getQueryString());
    }

    public Query getNationalities(String id) {
        Variable composer = SparqlBuilder.var("composer");
        Variable nationality = SparqlBuilder.var("nationality");

        SelectQuery selectQuery = Queries.SELECT().distinct().prefix(Constants.rdf).prefix(Constants.ns3).
                select(nationality).
                where(
                        composer.has(Constants.rdf.iri("id"), id).
                                andHas(Constants.ns3.iri("nationality"), nationality).optional()
                );

        return QueryFactory.create(selectQuery.getQueryString());
    }

    public Query getAlias(String id) {
        Variable composer = SparqlBuilder.var("composer");
        Variable alias = SparqlBuilder.var("alias");

        SelectQuery selectQuery = Queries.SELECT().distinct().prefix(Constants.rdf).prefix(Constants.ns1).
                select(alias).
                where(
                        composer.has(Constants.rdf.iri("id"), id).
                                andHas(Constants.ns1.iri("alias"), alias).optional()
                );

        return QueryFactory.create(selectQuery.getQueryString());
    }

    public Query getPeriod(String id) {
        Variable composer = SparqlBuilder.var("composer");
        Variable period = SparqlBuilder.var("period");

        SelectQuery selectQuery = Queries.SELECT().distinct().prefix(Constants.rdf).prefix(Constants.ns3).
                select(period).
                where(
                        composer.has(Constants.rdf.iri("id"), id).
                                andHas(Constants.ns3.iri("period"), period).optional()
                );

        return QueryFactory.create(selectQuery.getQueryString());
    }

    public Query getBirth(String id) {
        Variable composer = SparqlBuilder.var("composer");
        Variable birth = SparqlBuilder.var("birth");
        Variable label = SparqlBuilder.var("label");
        Variable approximate = SparqlBuilder.var("approximate");
        Variable date = SparqlBuilder.var("date");
        Variable birthUuid = SparqlBuilder.var("birthUuid");

        SelectQuery selectQuery = Queries.SELECT().distinct().prefix(Constants.rdf).prefix(Constants.ns3).prefix(Constants.ns5).prefix(Constants.rdfs).
                select(birth, label, approximate, date, birthUuid).
                where(
                        composer.has(Constants.rdf.iri("id"), id).
                                and(
                                        composer.has(Constants.ns3.iri("birth"), birth).
                                                and(
                                                        birth.has(Constants.rdfs.iri("label"), label)
                                                ).
                                                and(
                                                        birth.has(Constants.ns3.iri("approximate"), approximate).optional()
                                                ).
                                                and(
                                                        birth.has(Constants.rdf.iri("id"), birthUuid)
                                                ).
                                                and(
                                                        birth.has(Constants.ns5.iri("date"), date)
                                                ).optional()
                                )
                );

        return QueryFactory.create(selectQuery.getQueryString());
    }

    public Query getDeath(String id) {
        Variable composer = SparqlBuilder.var("composer");
        Variable death = SparqlBuilder.var("death");
        Variable label = SparqlBuilder.var("label");
        Variable approximate = SparqlBuilder.var("approximate");
        Variable date = SparqlBuilder.var("date");
        Variable deathUuid = SparqlBuilder.var("deathUuid");

        SelectQuery selectQuery = Queries.SELECT().distinct().prefix(Constants.rdf).prefix(Constants.ns3).prefix(Constants.ns5).prefix(Constants.rdfs).
                select(death, label, approximate, date, deathUuid).
                where(
                        composer.has(Constants.rdf.iri("id"), id).
                                and(
                                        composer.has(Constants.ns3.iri("birth"), death).
                                                and(
                                                        death.has(Constants.rdfs.iri("label"), label)
                                                ).
                                                and(
                                                        death.has(Constants.ns3.iri("approximate"), approximate).optional()
                                                ).
                                                and(
                                                        death.has(Constants.rdf.iri("id"), deathUuid)
                                                ).
                                                and(
                                                        death.has(Constants.ns5.iri("date"), date)
                                                ).optional()
                                )
                );

        return QueryFactory.create(selectQuery.getQueryString());
    }

    public Query getLocations(String id) {

        String selectQuery = String.format("PREFIX gn: <http://www.geonames.org/ontology#>\n" +
                "PREFIX wgs84_pos: <http://www.w3.org/2003/01/geo/wgs84_pos#>\n" +
                "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "SELECT ?location ?latitude ?longitude ?locationName ?locationPopulation\n" +
                "WHERE {\n" +
                "  ?composer rdf:id \"%s\" .\n" +
                "  ?composer foaf:based_near ?location .\n" +
                "  BIND(URI(REPLACE(str(?location), \"(^http://sws\\\\.geonames\\\\.org/\\\\d+)$\", \"$1/\")) AS ?geoNamesURI) .\n" +
                "  SERVICE <http://linkedgeodata.org/sparql> {\n" +
                "    ?geoNamesURI wgs84_pos:lat ?latitude ;\n" +
                "    wgs84_pos:long ?longitude ;\n" +
                "    gn:name ?locationName .\n" +
                "    OPTIONAL { ?geoNamesURI gn:population ?locationPopulation . }\n" +
                "  }\n" +
                "}", id);

        return QueryFactory.create(selectQuery);
    }

    public Query getSameAs(String id) {
        Variable composer = SparqlBuilder.var("composer");
        Variable sameAs = SparqlBuilder.var("sameAs");

        SelectQuery selectQuery = Queries.SELECT().distinct().prefix(Constants.rdf).prefix(Constants.owl).
                select(sameAs).
                where(
                        composer.has(Constants.rdf.iri("id"), id).
                                andHas(Constants.owl.iri("sameAs"), sameAs).optional()
                );

        return QueryFactory.create(selectQuery.getQueryString());
    }

    public Query getPages(String id) {
        Variable composer = SparqlBuilder.var("composer");
        Variable page = SparqlBuilder.var("page");

        SelectQuery selectQuery = Queries.SELECT().distinct().prefix(Constants.rdf).prefix(Constants.foaf).
                select(page).
                where(
                        composer.has(Constants.rdf.iri("id"), id).
                                andHas(Constants.foaf.iri("page"), page).optional()
                );

        return QueryFactory.create(selectQuery.getQueryString());
    }

    public Query getWikipediaPage(String id) {
        Variable composer = SparqlBuilder.var("composer");
        Variable wikipediaPage = SparqlBuilder.var("wikipediaPage");

        SelectQuery selectQuery = Queries.SELECT().distinct().prefix(Constants.rdf).prefix(Constants.ns2).
                select(wikipediaPage).
                where(
                        composer.has(Constants.rdf.iri("id"), id).
                                andHas(Constants.ns2.iri("wikipedia"), wikipediaPage).optional()
                );

        return QueryFactory.create(selectQuery.getQueryString());
    }

    public Query getHasInfluenced(String id) {
        Variable composer = SparqlBuilder.var("composer");
        Variable influencedComposer = SparqlBuilder.var("influencedComposer");
        Variable influencedComposerUuid = SparqlBuilder.var("influencedComposerUuid");

        SelectQuery selectQuery = Queries.SELECT().distinct().prefix(Constants.rdf).prefix(Constants.ns4).
                select(influencedComposer, influencedComposerUuid).
                where(
                        composer.has(Constants.rdf.iri("id"), id).
                                andHas(Constants.ns4.iri("hasInfluenced"), influencedComposer).
                                and(influencedComposer.has(Constants.rdf.iri("id"), influencedComposerUuid)).
                                optional()
                );

        return QueryFactory.create(selectQuery.getQueryString());
    }

    public Query getInfluencedBy(String id) {
        Variable composer = SparqlBuilder.var("composer");
        Variable composerWhoInfluenced = SparqlBuilder.var("composerWhoInfluenced");
        Variable composerWhoInfluencedUuid = SparqlBuilder.var("composerWhoInfluencedUuid");

        SelectQuery selectQuery = Queries.SELECT().distinct().prefix(Constants.rdf).prefix(Constants.ns4).
                select(composerWhoInfluenced, composerWhoInfluencedUuid).
                where(
                        composer.has(Constants.rdf.iri("id"), id).
                                andHas(Constants.ns4.iri("influencedBy"), composerWhoInfluenced).
                                and(composerWhoInfluenced.has(Constants.rdf.iri("id"), composerWhoInfluencedUuid)).
                                optional()
                );

        return QueryFactory.create(selectQuery.getQueryString());
    }

    public Query getStylePeriods(String id) {
        Variable composer = SparqlBuilder.var("composer");
        Variable stylePeriod = SparqlBuilder.var("stylePeriod");
        Variable stylePeriodTitle = SparqlBuilder.var("stylePeriodTitle");
        Variable stylePeriodUuid = SparqlBuilder.var("stylePeriodUuid");

        SelectQuery selectQuery = Queries.SELECT().distinct().prefix(Constants.rdf).prefix(Constants.ns4).prefix(Constants.dc).
                select(stylePeriod, stylePeriodTitle, stylePeriodUuid).
                where(
                        composer.has(Constants.rdf.iri("id"), id).
                                and(
                                        composer.has(Constants.ns4.iri("from_style_period"), stylePeriod).
                                                and(
                                                        stylePeriod.has(Constants.dc.iri("title"), stylePeriodTitle).
                                                                andHas(Constants.rdf.iri("id"), stylePeriodUuid)
                                                )
                                ).optional()
                );

        return QueryFactory.create(selectQuery.getQueryString());
    }

    public Query getDBpediaData(String id) {
        String selectQuery = String.format("PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                "PREFIX void: <http://rdfs.org/ns/void#>\n" +
                "PREFIX DBpedia: <https://www.dbpedia.org/>\n" +
                "\n" +
                "SELECT DISTINCT ?predicate ?predicateLabel ?value ?valueLabel\n" +
                "WHERE {\n" +
                "  DBpedia: void:sparqlEndpoint ?sparqlEndpoint .\n" +
                "  <http://dbtune.org/classical/resource/composer/%s> owl:sameAs ?externalResource .\n" +
                "  filter ( regex(str(?externalResource), \"dbpedia\")) .\n" +
                "  \n" +
                "  SERVICE ?sparqlEndpoint {\n" +
                "    ?externalResource ?predicate ?value .\n" +
                "    \n" +
                "    OPTIONAL { \n" +
                "   \t  ?predicate rdfs:label ?predicateLabel .\n" +
                "      FILTER (lang(?predicateLabel) = \"en\") .\n" +
                "    }\n" +
                "    \n" +
                "    OPTIONAL { \n" +
                "   \t  ?value rdfs:label ?valueLabel .\n" +
                "      FILTER (lang(?valueLabel) = \"en\") .\n" +
                "    }\n" +
                "    \n" +
                "    FILTER ( IF (isLiteral(?value), lang(?value) = \"en\", TRUE) ) .\n" +
                "  }\n" +
                "}", id);

        return QueryFactory.create(selectQuery);
    }

    public UpdateRequest deleteComposer(String id) {
        Variable composer = SparqlBuilder.var("composer");
        Variable predicate = SparqlBuilder.var("predicate");
        Variable object = SparqlBuilder.var("object");

        ModifyQuery deleteQuery = Queries.DELETE(composer.has(predicate, object)).
                where(
                        composer.has(predicate, object).
                                    filter(Expressions.or(
                                        Expressions.regex(
                                                Expressions.str(composer),
                                                String.format("^http://dbtune.org/classical/resource/composer/%s$", id)
                                        ),
                                        Expressions.regex(
                                                Expressions.str(object),
                                                String.format("^http://dbtune.org/classical/resource/composer/%s$", id)
                                        )
                                ))
                );

        return UpdateFactory.create(deleteQuery.getQueryString());
    }

    public UpdateRequest insertComposer(String composerUuid, String composerURIString, String name) {
        Iri composerURI = iri(composerURIString);

        InsertDataQuery insertDataQuery = Queries.INSERT_DATA().prefix(Constants.rdf).prefix(Constants.foaf)
                .insertData(
                        composerURI.has(Constants.rdf.iri("id"), composerUuid).
                                andHas(Constants.foaf.iri("name"), name)
                );

        return UpdateFactory.create(insertDataQuery.getQueryString());
    }

    public Query searchComposer(String searchString) {
        Variable resource = SparqlBuilder.var("resource");
        Variable type = SparqlBuilder.var("type");
        Variable id = SparqlBuilder.var("id");
        Variable label = SparqlBuilder.var("label");

        SelectQuery selectQuery = Queries.SELECT().distinct().prefix(Constants.rdf).prefix(Constants.rdfs).prefix(Constants.type).prefix(Constants.owl).
                select(resource, id, label).
                where(
                        resource.has(Constants.rdf.iri("type"), Constants.type.iri("Composer"))
                            .and(
                                    resource.has(Constants.rdf.iri("id"), id).
                                    andHas(Constants.rdfs.iri("label"), label).
                                    filter(
                                            Expressions.regex(
                                                    Expressions.str(label),
                                                    searchString,
                                                    "i"
                                            )
                                    )
                            )

                ).limit(100);

        return QueryFactory.create(selectQuery.getQueryString());
    }
}
