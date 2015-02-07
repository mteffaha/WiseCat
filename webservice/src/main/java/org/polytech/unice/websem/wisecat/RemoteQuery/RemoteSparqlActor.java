package org.polytech.unice.websem.wisecat.RemoteQuery;

import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;

/**
 * Created by mtoffaha on 03/02/2015.
 */
public class RemoteSparqlActor {
    private static final String LINKEDMDB_SERVICE = "http://data.linkedmdb.org/sparql";
    private static final String PREFIXES =
        "  PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
        "\tPREFIX dc:   <http://purl.org/dc/terms/>\n" +
        "\tPREFIX owl:  <http://www.w3.org/2002/07/owl#>\n" +
        "\tPREFIX lmdb: <http://data.linkedmdb.org/resource/movie/>\n" +
        "\tPREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" +
        "\tPREFIX skos: <http://www.w3.org/2004/02/skos/core#>\n";

    public static void importActor(String id){
        ResultSet results = RemoteSparqlActor.importName(id);
        ResultSetFormatter.out(System.out, results);

        results = RemoteSparqlActor.importMovies(id);
        ResultSetFormatter.out(System.out, results);
    }

    private static ResultSet importName(String id){
        String query = PREFIXES +
"\t     SELECT *                                                               \n" +
"\t     WHERE  {                                                                    \n" +
"\t        <" + id + "> lmdb:actor_name ?name.                               \n" +
"\t     }                                                                           \n";

        return QueryExecutionFactory.sparqlService(LINKEDMDB_SERVICE, query).execSelect();
    }

    private static ResultSet importMovies(String id){
        String query = PREFIXES +
"\t     SELECT *                                                               \n" +
"\t     WHERE  {                                                                    \n" +
"\t        ?movie lmdb:actor <" + id + ">.                               \n" +
"\t     }                                                                           \n";

        return QueryExecutionFactory.sparqlService(LINKEDMDB_SERVICE, query).execSelect();
    }
}
