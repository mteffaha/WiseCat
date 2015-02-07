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

    // Page (commmon)
    private static ResultSet importPage(String id){
        String query = PREFIXES +
"\t     SELECT *                                                               \n" +
"\t     WHERE  {                                                                    \n" +
"\t        <" + id + "> foaf:page ?page.                               \n" +
"\t     }                                                                           \n";

        return QueryExecutionFactory.sparqlService(LINKEDMDB_SERVICE, query).execSelect();
    }

    /**********************************************************************************************/
    /**********************************ACTOR*******************************************************/
    /**********************************************************************************************/

    public static void importActor(String id){
        ResultSet results = RemoteSparqlActor.importActorName(id);
        ResultSetFormatter.out(System.out, results);

        results = RemoteSparqlActor.importActorMovies(id);
        ResultSetFormatter.out(System.out, results);

        results = RemoteSparqlActor.importPage(id);
        ResultSetFormatter.out(System.out, results);
    }

    private static ResultSet importActorName(String id){
        String query = PREFIXES +
"\t     SELECT *                                                               \n" +
"\t     WHERE  {                                                                    \n" +
"\t        <" + id + "> lmdb:actor_name ?name.                               \n" +
"\t     }                                                                           \n";

        return QueryExecutionFactory.sparqlService(LINKEDMDB_SERVICE, query).execSelect();
    }

    private static ResultSet importActorMovies(String id){
        String query = PREFIXES +
"\t     SELECT *                                                               \n" +
"\t     WHERE  {                                                                    \n" +
"\t        ?movie lmdb:actor <" + id + ">.                               \n" +
"\t     }                                                                           \n";

        return QueryExecutionFactory.sparqlService(LINKEDMDB_SERVICE, query).execSelect();
    }

    /**********************************************************************************************/
    /**********************************DIRECTOR****************************************************/
    /**********************************************************************************************/

    public static void importDirector(String id){
        ResultSet results = RemoteSparqlActor.importDirectorName(id);
        ResultSetFormatter.out(System.out, results);

        results = RemoteSparqlActor.importDirectorMovies(id);
        ResultSetFormatter.out(System.out, results);

        results = RemoteSparqlActor.importPage(id);
        ResultSetFormatter.out(System.out, results);
    }

    private static ResultSet importDirectorName(String id){
        String query = PREFIXES +
"\t     SELECT *                                                               \n" +
"\t     WHERE  {                                                                    \n" +
"\t        <" + id + "> lmdb:director_name ?name.                               \n" +
"\t     }                                                                           \n";

        return QueryExecutionFactory.sparqlService(LINKEDMDB_SERVICE, query).execSelect();
    }

    private static ResultSet importDirectorMovies(String id){
        String query = PREFIXES +
"\t     SELECT *                                                               \n" +
"\t     WHERE  {                                                                    \n" +
"\t        ?movie lmdb:director <" + id + ">.                               \n" +
"\t     }                                                                           \n";

        return QueryExecutionFactory.sparqlService(LINKEDMDB_SERVICE, query).execSelect();
    }

    /**********************************************************************************************/
    /**********************************EDITOR******************************************************/
    /**********************************************************************************************/

    public static void importEditor(String id){
        ResultSet results = RemoteSparqlActor.importEditorName(id);
        ResultSetFormatter.out(System.out, results);

        results = RemoteSparqlActor.importEditorMovies(id);
        ResultSetFormatter.out(System.out, results);
    }

    private static ResultSet importEditorName(String id){
        String query = PREFIXES +
"\t     SELECT *                                                               \n" +
"\t     WHERE  {                                                                    \n" +
"\t        <" + id + "> lmdb:editor_name ?name.                               \n" +
"\t     }                                                                           \n";

        return QueryExecutionFactory.sparqlService(LINKEDMDB_SERVICE, query).execSelect();
    }

    private static ResultSet importEditorMovies(String id){
        String query = PREFIXES +
"\t     SELECT *                                                               \n" +
"\t     WHERE  {                                                                    \n" +
"\t        ?movie lmdb:editor <" + id + ">.                               \n" +
"\t     }                                                                           \n";

        return QueryExecutionFactory.sparqlService(LINKEDMDB_SERVICE, query).execSelect();
    }

    /**********************************************************************************************/
    /**********************************WRITER******************************************************/
    /**********************************************************************************************/

    public static void importWriter(String id){
        ResultSet results = RemoteSparqlActor.importWriterName(id);
        ResultSetFormatter.out(System.out, results);

        results = RemoteSparqlActor.importWriterMovies(id);
        ResultSetFormatter.out(System.out, results);
    }

    private static ResultSet importWriterName(String id){
        String query = PREFIXES +
"\t     SELECT *                                                               \n" +
"\t     WHERE  {                                                                    \n" +
"\t        <" + id + "> lmdb:writer_name ?name.                               \n" +
"\t     }                                                                           \n";

        return QueryExecutionFactory.sparqlService(LINKEDMDB_SERVICE, query).execSelect();
    }

    private static ResultSet importWriterMovies(String id){
        String query = PREFIXES +
"\t     SELECT *                                                               \n" +
"\t     WHERE  {                                                                    \n" +
"\t        ?movie lmdb:writer <" + id + ">.                               \n" +
"\t     }                                                                           \n";

        return QueryExecutionFactory.sparqlService(LINKEDMDB_SERVICE, query).execSelect();
    }

    /**********************************************************************************************/
    /**********************************COMPOSER****************************************************/
    /**********************************************************************************************/

    public static void importComposer(String id){
        ResultSet results = RemoteSparqlActor.importComposerName(id);
        ResultSetFormatter.out(System.out, results);

        results = RemoteSparqlActor.importComposerMovies(id);
        ResultSetFormatter.out(System.out, results);
    }

    private static ResultSet importComposerName(String id){
        String query = PREFIXES +
"\t     SELECT *                                                               \n" +
"\t     WHERE  {                                                                    \n" +
"\t        <" + id + "> lmdb:music_contributor_name ?name.                               \n" +
"\t     }                                                                           \n";

        return QueryExecutionFactory.sparqlService(LINKEDMDB_SERVICE, query).execSelect();
    }

    private static ResultSet importComposerMovies(String id){
        String query = PREFIXES +
"\t     SELECT *                                                               \n" +
"\t     WHERE  {                                                                    \n" +
"\t        ?movie lmdb:music_contributor <" + id + ">.                               \n" +
"\t     }                                                                           \n";

        return QueryExecutionFactory.sparqlService(LINKEDMDB_SERVICE, query).execSelect();
    }

    /**********************************************************************************************/
    /**********************************GENRE*******************************************************/
    /**********************************************************************************************/

    public static void importGenre(String id){
        ResultSet results = RemoteSparqlActor.importGenreName(id);
        ResultSetFormatter.out(System.out, results);

        results = RemoteSparqlActor.importGenreMovies(id);
        ResultSetFormatter.out(System.out, results);
    }

    private static ResultSet importGenreName(String id){
        String query = PREFIXES +
"\t     SELECT *                                                               \n" +
"\t     WHERE  {                                                                    \n" +
"\t        <" + id + "> lmdb:film_genre_name ?name.                               \n" +
"\t     }                                                                           \n";

        return QueryExecutionFactory.sparqlService(LINKEDMDB_SERVICE, query).execSelect();
    }

    private static ResultSet importGenreMovies(String id){
        String query = PREFIXES +
"\t     SELECT *                                                               \n" +
"\t     WHERE  {                                                                    \n" +
"\t        ?movie lmdb:genre <" + id + ">.                               \n" +
"\t     }                                                                           \n";

        return QueryExecutionFactory.sparqlService(LINKEDMDB_SERVICE, query).execSelect();
    }
}
