package org.polytech.unice.websem.wisecat.RemoteQuery;

import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;

/**
 * Created by mtoffaha on 03/02/2015.
 */
public class RemoteSparql {
   private static final String LINKEDMDB_SERVICE = "http://data.linkedmdb.org/sparql";
    static void callLinkedMDB(String query){

        String endpoint = "otee:Endpoints";
        /*String endpointsSparql =
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                        "\tPREFIX dc: <http://purl.org/dc/terms/>\n" +
                        "\tPREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                        "\tPREFIX lmdb: <http://data.linkedmdb.org/resource/movie/>\n" +
                        "\tPREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" +
                        "\tPREFIX skos: <http://www.w3.org/2004/02/skos/core#>\n"+
                        "		select ?filme\n"+
                        "		where {\n"+
                        "		?filme dc:title 'Saw'\n"+
                        "		}\n";
                        */

        QueryExecution x = QueryExecutionFactory.sparqlService(LINKEDMDB_SERVICE,query);
        ResultSet results = x.execSelect();
        ResultSetFormatter.out(System.out, results);

    }
}
