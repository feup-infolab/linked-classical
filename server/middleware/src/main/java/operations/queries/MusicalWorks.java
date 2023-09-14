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

public class MusicalWorks {

    public MusicalWorks() {

    }

    public Query getWork(String composerId, String workId) {
        Variable work = SparqlBuilder.var("work");
        Variable predicate = SparqlBuilder.var("predicate");
        Variable object = SparqlBuilder.var("object");

        SelectQuery selectQuery = Queries.SELECT().distinct().prefix(Constants.rdf).prefix(Constants.type).prefix(Constants.ns2).
                select(work, predicate, object).
                where(
                        work.has(Constants.rdf.iri("type"), Constants.ns2.iri("MusicalWork")).
                                andHas(predicate, object).
                                filter(
                                        Expressions.regex(
                                                Expressions.str(work),
                                                String.format("^http://dbtune.org/classical/resource/work/%s/%s$", composerId, workId)
                                        )
                                )
                );

        return QueryFactory.create(selectQuery.getQueryString());
    }

    public Query getIri(String id) {
        Variable musicalWork = SparqlBuilder.var("musicalWork");

        SelectQuery selectQuery = Queries.SELECT().distinct().prefix(Constants.rdf).
                select(musicalWork).
                where(
                        musicalWork.has(Constants.rdf.iri("id"), id)
                );

        return QueryFactory.create(selectQuery.getQueryString());
    }

    public Query getLabel(String id) {
        Variable musicalWork = SparqlBuilder.var("musicalWork");
        Variable label = SparqlBuilder.var("label");

        SelectQuery selectQuery = Queries.SELECT().distinct().prefix(Constants.rdf).prefix(Constants.rdfs).
                select(label).
                where(
                        musicalWork.has(Constants.rdf.iri("id"), id).
                                andHas(Constants.rdfs.iri("label"), label).optional()
                );

        return QueryFactory.create(selectQuery.getQueryString());
    }

    public Query getAlias(String id) {
        Variable musicalWork = SparqlBuilder.var("musicalWork");
        Variable alias = SparqlBuilder.var("alias");

        SelectQuery selectQuery = Queries.SELECT().distinct().prefix(Constants.rdf).prefix(Constants.ns1).
                select(alias).
                where(
                        musicalWork.has(Constants.rdf.iri("id"), id).
                                andHas(Constants.ns1.iri("alias"), alias).optional()
                );

        return QueryFactory.create(selectQuery.getQueryString());
    }

    public Query getTitle(String id) {
        Variable musicalWork = SparqlBuilder.var("musicalWork");
        Variable title = SparqlBuilder.var("title");

        SelectQuery selectQuery = Queries.SELECT().distinct().prefix(Constants.rdf).prefix(Constants.dc).
                select(title).
                where(
                        musicalWork.has(Constants.rdf.iri("id"), id).
                                andHas(Constants.dc.iri("title"), title).optional()
                );

        return QueryFactory.create(selectQuery.getQueryString());
    }

    public Query getRemarks(String id) {
        Variable musicalWork = SparqlBuilder.var("musicalWork");
        Variable remarks = SparqlBuilder.var("remarks");

        SelectQuery selectQuery = Queries.SELECT().distinct().prefix(Constants.rdf).prefix(Constants.ns3).
                select(remarks).
                where(
                        musicalWork.has(Constants.rdf.iri("id"), id).
                                andHas(Constants.ns3.iri("remarks"), remarks).optional()
                );

        return QueryFactory.create(selectQuery.getQueryString());
    }

    public Query getKey(String id) {
        Variable musicalWork = SparqlBuilder.var("musicalWork");
        Variable key = SparqlBuilder.var("key");

        SelectQuery selectQuery = Queries.SELECT().distinct().prefix(Constants.rdf).prefix(Constants.ns2).
                select(key).
                where(
                        musicalWork.has(Constants.rdf.iri("id"), id).
                                andHas(Constants.ns2.iri("key"), key).optional()
                );

        return QueryFactory.create(selectQuery.getQueryString());
    }

    public Query getNumber(String id) {
        Variable musicalWork = SparqlBuilder.var("musicalWork");
        Variable number = SparqlBuilder.var("number");

        SelectQuery selectQuery = Queries.SELECT().distinct().prefix(Constants.rdf).prefix(Constants.ns2).
                select(number).
                where(
                        musicalWork.has(Constants.rdf.iri("id"), id).
                                andHas(Constants.ns2.iri("number"), number).optional()
                );

        return QueryFactory.create(selectQuery.getQueryString());
    }

    public Query getOpus(String id) {
        Variable musicalWork = SparqlBuilder.var("musicalWork");
        Variable opus = SparqlBuilder.var("opus");

        SelectQuery selectQuery = Queries.SELECT().distinct().prefix(Constants.rdf).prefix(Constants.ns2).
                select(opus).
                where(
                        musicalWork.has(Constants.rdf.iri("id"), id).
                                andHas(Constants.ns2.iri("opus"), opus).optional()
                );

        return QueryFactory.create(selectQuery.getQueryString());
    }

    public Query getCatalogue(String id) {
        Variable musicalWork = SparqlBuilder.var("musicalWork");
        Variable catalogue = SparqlBuilder.var("catalogue");

        SelectQuery selectQuery = Queries.SELECT().distinct().prefix(Constants.rdf).prefix(Constants.ns2).
                select(catalogue).
                where(
                        musicalWork.has(Constants.rdf.iri("id"), id).
                                andHas(Constants.ns2.iri("catalogue"), catalogue).optional()
                );

        return QueryFactory.create(selectQuery.getQueryString());
    }

    public Query getIsPartOf(String id) {
        Variable musicalWork = SparqlBuilder.var("musicalWork");
        Variable partOf = SparqlBuilder.var("partOf");
        Variable partOfUuid = SparqlBuilder.var("partOfUuid");

        SelectQuery selectQuery = Queries.SELECT().distinct().prefix(Constants.rdf).prefix(Constants.dc).
                select(partOf, partOfUuid).
                where(
                        musicalWork.has(Constants.rdf.iri("id"), id).
                                and(
                                        musicalWork.has(Constants.dc.iri("isPartOf"), partOf).
                                                and(partOf.has(Constants.rdf.iri("id"), partOfUuid))
                                                .optional()
                                )
                );

        return QueryFactory.create(selectQuery.getQueryString());
    }

    public Query getComposition(String id) {
        Variable musicalWork = SparqlBuilder.var("musicalWork");
        Variable composition = SparqlBuilder.var("composition");
        Variable date = SparqlBuilder.var("date");
        Variable composer = SparqlBuilder.var("composer");
        Variable composerId = SparqlBuilder.var("composerId");

        SelectQuery selectQuery = Queries.SELECT().distinct().prefix(Constants.rdf).prefix(Constants.ns2).prefix(Constants.ns5).
                select(composition, date, composer, composerId).
                where(
                        musicalWork.has(Constants.rdf.iri("id"), id).
                                andHas(Constants.ns2.iri("composed_in"), composition).
                                and(composition.has(Constants.ns5.iri("date"), date)).
                                and(composition.has(Constants.ns2.iri("composer"), composer)).
                                and(composer.has(Constants.rdf.iri("id"), composerId))
                );

        return QueryFactory.create(selectQuery.getQueryString());
    }

    public Query getDocument(String id) {
        Variable musicalWork = SparqlBuilder.var("musicalWork");
        Variable document = SparqlBuilder.var("document");
        Variable siteName = SparqlBuilder.var("siteName");
        Variable documentUuid = SparqlBuilder.var("documentUuid");

        SelectQuery selectQuery = Queries.SELECT().distinct().prefix(Constants.rdf).prefix(Constants.foaf).prefix(Constants.ns3).
                select(document, siteName, documentUuid).
                where(
                        musicalWork.has(Constants.rdf.iri("id"), id).
                                and(
                                        musicalWork.has(Constants.foaf.iri("page"), document).
                                                and(
                                                        document.has(Constants.ns3.iri("siteName"), siteName)
                                                                .andHas(Constants.rdf.iri("id"), documentUuid)
                                                )
                                ).optional()
                );

        return QueryFactory.create(selectQuery.getQueryString());
    }

    public UpdateRequest deleteWork(String composerId, String workId) {
        Variable work = SparqlBuilder.var("work");
        Variable predicate = SparqlBuilder.var("predicate");
        Variable object = SparqlBuilder.var("object");


        ModifyQuery deleteQuery = Queries.DELETE(work.has(predicate, object))
                .where(
                        work.has(predicate, object).
                                filter(Expressions.or(
                                        Expressions.regex(
                                                Expressions.str(work),
                                                String.format("^http://dbtune.org/classical/resource/work/%s/%s$", composerId, workId)
                                        ),
                                        Expressions.regex(
                                                Expressions.str(object),
                                                String.format("^http://dbtune.org/classical/resource/work/%s/%s$", composerId, workId)
                                        )
                                        )
                                )
                );
        return UpdateFactory.create(deleteQuery.getQueryString());
    }

    public UpdateRequest createWork(String workUuid, String workURIString, String title) {
        Iri workURI = iri(workURIString);

        InsertDataQuery insertDataQuery = Queries.INSERT_DATA().prefix(Constants.rdf).prefix(Constants.dc)
                .insertData(
                        workURI.has(Constants.rdf.iri("id"), workUuid).
                                andHas(Constants.dc.iri("title"), title)
                );

        return UpdateFactory.create(insertDataQuery.getQueryString());
    }

    public Query searchWork(String searchString) {
        Variable dbtuneResource = SparqlBuilder.var("dbtuneResource");
        Variable resource = SparqlBuilder.var("resource");
        Variable id = SparqlBuilder.var("id");
        Variable label = SparqlBuilder.var("label");
        Variable composer = SparqlBuilder.var("composer");
        Variable composerId = SparqlBuilder.var("composerId");
        Variable composition = SparqlBuilder.var("composition");
        Variable composerLabel = SparqlBuilder.var("composerLabel");

        SelectQuery selectQuery = Queries.SELECT().distinct().prefix(Constants.rdf).prefix(Constants.rdfs).prefix(Constants.ns2).prefix(Constants.owl).
                select(resource, id, label, composerId, composerLabel).
                where(
                        resource.has(Constants.rdf.iri("type"), Constants.ns2.iri("MusicalWork")).
                            andHas(Constants.rdf.iri("id"), id).
                            andHas(Constants.rdfs.iri("label"), label).
                            andHas(Constants.ns2.iri("composed_in"), composition).
                            and(composition.has(Constants.ns2.iri("composer"), composer)).
                            and(composer.has(Constants.rdf.iri("id"), composerId)).
                            and(composer.has(Constants.rdfs.iri("label"), composerLabel)).
                            filter(
                                    Expressions.or(
                                            Expressions.regex(
                                                    Expressions.str(label),
                                                    searchString,
                                                    "i"
                                            ),
                                            Expressions.regex(
                                                    Expressions.str(composerLabel),
                                                    searchString,
                                                    "i"
                                            )
                                    )

                            )


                ).limit(100);

        return QueryFactory.create(selectQuery.getQueryString());
    }
}
