package org.polytech.unice.websem.wisecat.RemoteQuery;

import com.hp.hpl.jena.query.*;
import org.polytech.unice.websem.wisecat.model.Movie;

import javax.xml.transform.SourceLocator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by mtoffaha on 03/02/2015.
 */
public class RemoteSparql {
    private static final String LINKEDMDB_SERVICE = "http://data.linkedmdb.org/sparql";
    public static List<Movie> getMoviesFromLinkedMDB(String query){
        List<Movie> movies = new ArrayList<Movie>();
        String endpoint = "otee:Endpoints";
        String queryPrefix =
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                        "\tPREFIX dc: <http://purl.org/dc/terms/>\n" +
                        "\tPREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                        "\tPREFIX lmdb: <http://data.linkedmdb.org/resource/movie/>\n" +
                        "\tPREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" +
                        "\tPREFIX skos: <http://www.w3.org/2004/02/skos/core#>\n";
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

        System.out.println("|-------------------------------------------------------------------|");
        System.out.println(queryPrefix+query);
        System.out.println("|-------------------------------------------------------------------|");



        QueryExecution x = QueryExecutionFactory.sparqlService(LINKEDMDB_SERVICE,queryPrefix+query);
        ResultSet results = x.execSelect();
        ResultSetFormatter.out(System.out, results);
        while(results.hasNext()){
            QuerySolution solution = results.next();
            Movie newMovie = new Movie();

            Iterator<String> names = solution.varNames();

            while(names.hasNext()){
                System.out.println("###   "+names.next());
            }
            if(solution.contains("labe")){
                //newMovie.setTitle(solution.get("filme").);
            }
        }
        return movies;
    }
}
