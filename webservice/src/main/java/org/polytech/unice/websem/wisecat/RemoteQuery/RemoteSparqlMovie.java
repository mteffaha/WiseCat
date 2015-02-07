package org.polytech.unice.websem.wisecat.RemoteQuery;

import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;

/**
 * Created by mtoffaha on 03/02/2015.
 */
public class RemoteSparqlMovie {
    private static final String LINKEDMDB_SERVICE = "http://data.linkedmdb.org/sparql";
    private static final String PREFIXES =
        "  PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
        "\tPREFIX dc:   <http://purl.org/dc/terms/>\n" +
        "\tPREFIX owl:  <http://www.w3.org/2002/07/owl#>\n" +
        "\tPREFIX lmdb: <http://data.linkedmdb.org/resource/movie/>\n" +
        "\tPREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" +
        "\tPREFIX skos: <http://www.w3.org/2004/02/skos/core#>\n";

    public static void importMovie(String movieId){
        ResultSet results = RemoteSparql.importActors(movieId);
        ResultSetFormatter.out(System.out, results);

        results = RemoteSparql.importDirector(movieId);
        ResultSetFormatter.out(System.out, results);

        results = RemoteSparql.importEditor(movieId);
        ResultSetFormatter.out(System.out, results);

        results = RemoteSparql.importWriter(movieId);
        ResultSetFormatter.out(System.out, results);

        results = RemoteSparql.importGenre(movieId);
        ResultSetFormatter.out(System.out, results);

        results = RemoteSparql.importSong(movieId);
        ResultSetFormatter.out(System.out, results);

        results = RemoteSparql.importComposer(movieId);
        ResultSetFormatter.out(System.out, results);

        results = RemoteSparql.importSubject(movieId);
        ResultSetFormatter.out(System.out, results);

        results = RemoteSparql.importRuntime(movieId);
        ResultSetFormatter.out(System.out, results);

        results = RemoteSparql.importDate(movieId);
        ResultSetFormatter.out(System.out, results);

        results = RemoteSparql.importLanguage(movieId);
        ResultSetFormatter.out(System.out, results);
    }

    private static ResultSet importActors(String movieId){
        String query = PREFIXES +
"\t     SELECT *                                                               \n" +
"\t     WHERE  {                                                                    \n" +
"\t        <" + movieId + "> lmdb:actor ?actor.                               \n" +
"\t        ?actor lmdb:actor_name ?name.                                     \n" +
"\t     }                                                                           \n";

        return QueryExecutionFactory.sparqlService(LINKEDMDB_SERVICE, query).execSelect();
    }

    private static ResultSet importDirector(String movieId){
        String query = PREFIXES +
"\t     SELECT *                                                               \n" +
"\t     WHERE  {                                                                    \n" +
"\t        <" + movieId + "> lmdb:director ?director.                               \n" +
"\t        ?director lmdb:director_name ?name.                                     \n" +
"\t     }                                                                           \n";

        return QueryExecutionFactory.sparqlService(LINKEDMDB_SERVICE, query).execSelect();
    }

    private static ResultSet importEditor(String movieId){
        String query = PREFIXES +
"\t     SELECT *                                                               \n" +
"\t     WHERE  {                                                                    \n" +
"\t        <" + movieId + "> lmdb:editor ?editor.                               \n" +
"\t        ?editor lmdb:editor_name ?name.                                     \n" +
"\t     }                                                                           \n";

        return QueryExecutionFactory.sparqlService(LINKEDMDB_SERVICE, query).execSelect();
    }

    private static ResultSet importWriter(String movieId){
        String query = PREFIXES +
"\t     SELECT *                                                               \n" +
"\t     WHERE  {                                                                    \n" +
"\t        <" + movieId + "> lmdb:writer ?writer.                               \n" +
"\t        ?writer lmdb:writer_name ?name.                                     \n" +
"\t     }                                                                           \n";

        return QueryExecutionFactory.sparqlService(LINKEDMDB_SERVICE, query).execSelect();
    }

    private static ResultSet importGenre(String movieId){
        String query = PREFIXES +
"\t     SELECT *                                                               \n" +
"\t     WHERE  {                                                                    \n" +
"\t        <" + movieId + "> lmdb:genre ?genre.                               \n" +
"\t        ?genre lmdb:film_genre_name ?name.                                     \n" +
"\t     }                                                                           \n";

        return QueryExecutionFactory.sparqlService(LINKEDMDB_SERVICE, query).execSelect();
    }

    private static ResultSet importSong(String movieId){
        String query = PREFIXES +
"\t     SELECT *                                                               \n" +
"\t     WHERE  {                                                                    \n" +
"\t        <" + movieId + "> lmdb:film_featured_song ?song.                               \n" +
"\t        ?song lmdb:film_featured_song_name ?name.                                     \n" +
"\t     }                                                                           \n";

        return QueryExecutionFactory.sparqlService(LINKEDMDB_SERVICE, query).execSelect();
    }

    private static ResultSet importComposer(String movieId){
        String query = PREFIXES +
"\t     SELECT *                                                               \n" +
"\t     WHERE  {                                                                    \n" +
"\t        <" + movieId + "> lmdb:music_contributor ?composer.                              \n" +
"\t        ?composer lmdb:music_contributor_name ?name.                                     \n" +
"\t     }                                                                           \n";

        return QueryExecutionFactory.sparqlService(LINKEDMDB_SERVICE, query).execSelect();
    }

    private static ResultSet importSubject(String movieId){
        String query = PREFIXES +
"\t     SELECT *                                                               \n" +
"\t     WHERE  {                                                                    \n" +
"\t        <" + movieId + "> skos:subject ?subject.                               \n" +
"\t        ?subject lmdb:film_subject_name ?name.                                     \n" +
"\t     }                                                                           \n";

        return QueryExecutionFactory.sparqlService(LINKEDMDB_SERVICE, query).execSelect();
    }

    private static ResultSet importRuntime(String movieId){
        String query = PREFIXES +
"\t     SELECT *                                                               \n" +
"\t     WHERE  {                                                                    \n" +
"\t        <" + movieId + "> lmdb:runtime ?runtime.                                     \n" +
"\t     }                                                                           \n";

        return QueryExecutionFactory.sparqlService(LINKEDMDB_SERVICE, query).execSelect();
    }

    private static ResultSet importDate(String movieId){
        String query = PREFIXES +
"\t     SELECT *                                                               \n" +
"\t     WHERE  {                                                                    \n" +
"\t        <" + movieId + "> dc:date ?date.                                     \n" +
"\t     }                                                                           \n";

        return QueryExecutionFactory.sparqlService(LINKEDMDB_SERVICE, query).execSelect();
    }

    private static ResultSet importLanguage(String movieId){
        String query = PREFIXES +
"\t     SELECT *                                                               \n" +
"\t     WHERE  {                                                                    \n" +
"\t        <" + movieId + "> lmdb:language ?language.                               \n" +
"\t     }                                                                           \n";

        return QueryExecutionFactory.sparqlService(LINKEDMDB_SERVICE, query).execSelect();
    }

    private static ResultSet importPage(String movieId){
        String query = PREFIXES +
"\t     SELECT *                                                               \n" +
"\t     WHERE  {                                                                    \n" +
"\t        <" + movieId + "> foaf:page ?page.                                 \n" +
"\t     }                                                                           \n";

        return QueryExecutionFactory.sparqlService(LINKEDMDB_SERVICE, query).execSelect();
    }
}
