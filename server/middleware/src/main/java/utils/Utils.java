package utils;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryFactory;
import org.eclipse.rdf4j.sparqlbuilder.constraint.Expressions;
import org.eclipse.rdf4j.sparqlbuilder.core.SparqlBuilder;
import org.eclipse.rdf4j.sparqlbuilder.core.Variable;
import org.eclipse.rdf4j.sparqlbuilder.core.query.Queries;
import org.eclipse.rdf4j.sparqlbuilder.core.query.SelectQuery;

public class Utils {
    public static Query getAllResources() {
        Variable subject = SparqlBuilder.var("subject");
        Variable object = SparqlBuilder.var("object");

        SelectQuery selectQuery = Queries.SELECT().prefix(Constants.rdf).distinct().
                select(subject).
                where(subject.has(Constants.rdf.iri("type"), object).
                    filter(
                        Expressions.and(
                            Expressions.not(Expressions.isBlank(subject)),
                            Expressions.regex(Expressions.str(subject), "^http://dbtune.org/classical/resource"),
                            Expressions.not(Expressions.regex(Expressions.str(subject), "^http://dbtune.org/classical/resource/type/")),
                            Expressions.not(Expressions.regex(Expressions.str(subject), "^http://dbtune.org/classical/resource/vocab/"))
                        )
                    )
                );

        return QueryFactory.create(selectQuery.getQueryString());
    }
}
