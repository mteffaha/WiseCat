package org.polytech.unice.websem.wisecat.RemoteQuery;

import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;

/**
 * Created by mtoffaha on 03/02/2015.
 */
public class ImportLMDB {
   /* private static final String LINKEDMDB_SERVICE = "http://data.linkedmdb.org/sparql";
    private static final String PREFIXES =
        "  PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
        "\tPREFIX dc:   <http://purl.org/dc/terms/>\n" +
        "\tPREFIX owl:  <http://www.w3.org/2002/07/owl#>\n" +
        "\tPREFIX lmdb: <http://data.linkedmdb.org/resource/movie/>\n" +
        "\tPREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" +
        "\tPREFIX skos: <http://www.w3.org/2004/02/skos/core#>\n";

    private static ImportMovie movie;
    private static ImportActor actor;
    private static ImportDirector director;
    private static ImportEditor editor;
    private static ImportWriter writer;
    private static ImportGenre genre;
    private static ImportSong song;
    private static ImportComposer composer;

    private ImportLMDB(){
    }

    public static ImportMovie importMovie(){
        if(movie == null){
            movie = new ImportMovie();
        }
        return movie;
    }

    public static ImportActor importActor(){
        if(actor == null){
            actor = new ImportActor();
        }
        return actor;
    }

    public static ImportDirector importDirector(){
        if(director == null){
            director = new ImportDirector();
        }
        return director;
    }

    public static ImportEditor importEditor(){
        if(editor == null){
            editor = new ImportEditor();
        }
        return editor;
    }

    public static ImportWriter importWriter(){
        if(writer == null){
            writer = new ImportWriter();
        }
        return writer;
    }

    public static ImportGenre importGenre(){
        if(genre == null){
            genre = new ImportGenre();
        }
        return genre;
    }

    public static ImportSong importSong(){
        if(song == null){
            song = new ImportSong();
        }
        return song;
    }

    public static ImportComposer importComposer(){
        if(composer == null){
            composer = new ImportComposer();
        }
        return composer;
    }

    public static void import(String id){
        ResultSet results = RemoteSparql.importActors(id);
        ResultSetFormatter.out(System.out, results);

        results = RemoteSparql.importDirector(id);
        ResultSetFormatter.out(System.out, results);

        results = RemoteSparql.importEditor(id);
        ResultSetFormatter.out(System.out, results);

        results = RemoteSparql.importWriter(id);
        ResultSetFormatter.out(System.out, results);

        results = RemoteSparql.importGenre(id);
        ResultSetFormatter.out(System.out, results);

        results = RemoteSparql.importSong(id);
        ResultSetFormatter.out(System.out, results);

        results = RemoteSparql.importComposer(id);
        ResultSetFormatter.out(System.out, results);

        results = RemoteSparql.importSubject(id);
        ResultSetFormatter.out(System.out, results);

        results = RemoteSparql.importRuntime(id);
        ResultSetFormatter.out(System.out, results);

        results = RemoteSparql.importDate(id);
        ResultSetFormatter.out(System.out, results);

        results = RemoteSparql.importLanguage(id);
        ResultSetFormatter.out(System.out, results);
    }

    private static ResultSet importActors(String id){
        String query = PREFIXES +
"\t     SELECT *                                                               \n" +
"\t     WHERE  {                                                                    \n" +
"\t        <" + id + "> lmdb:actor ?actor.                               \n" +
"\t        ?actor lmdb:actor_name ?name.                                     \n" +
"\t     }                                                                           \n";

        return QueryExecutionFactory.sparqlService(LINKEDMDB_SERVICE, query).execSelect();
    }

    private static ResultSet importDirector(String id){
        String query = PREFIXES +
"\t     SELECT *                                                               \n" +
"\t     WHERE  {                                                                    \n" +
"\t        <" + id + "> lmdb:director ?director.                               \n" +
"\t        ?director lmdb:director_name ?name.                                     \n" +
"\t     }                                                                           \n";

        return QueryExecutionFactory.sparqlService(LINKEDMDB_SERVICE, query).execSelect();
    }

    private static ResultSet importEditor(String id){
        String query = PREFIXES +
"\t     SELECT *                                                               \n" +
"\t     WHERE  {                                                                    \n" +
"\t        <" + id + "> lmdb:editor ?editor.                               \n" +
"\t        ?editor lmdb:editor_name ?name.                                     \n" +
"\t     }                                                                           \n";

        return QueryExecutionFactory.sparqlService(LINKEDMDB_SERVICE, query).execSelect();
    }

    private static ResultSet importWriter(String id){
        String query = PREFIXES +
"\t     SELECT *                                                               \n" +
"\t     WHERE  {                                                                    \n" +
"\t        <" + id + "> lmdb:writer ?writer.                               \n" +
"\t        ?writer lmdb:writer_name ?name.                                     \n" +
"\t     }                                                                           \n";

        return QueryExecutionFactory.sparqlService(LINKEDMDB_SERVICE, query).execSelect();
    }

    private static ResultSet importGenre(String id){
        String query = PREFIXES +
"\t     SELECT *                                                               \n" +
"\t     WHERE  {                                                                    \n" +
"\t        <" + id + "> lmdb:genre ?genre.                               \n" +
"\t        ?genre lmdb:film_genre_name ?name.                                     \n" +
"\t     }                                                                           \n";

        return QueryExecutionFactory.sparqlService(LINKEDMDB_SERVICE, query).execSelect();
    }

    private static ResultSet importSong(String id){
        String query = PREFIXES +
"\t     SELECT *                                                               \n" +
"\t     WHERE  {                                                                    \n" +
"\t        <" + id + "> lmdb:film_featured_song ?song.                               \n" +
"\t        ?song lmdb:film_featured_song_name ?name.                                     \n" +
"\t     }                                                                           \n";

        return QueryExecutionFactory.sparqlService(LINKEDMDB_SERVICE, query).execSelect();
    }

    private static ResultSet importComposer(String id){
        String query = PREFIXES +
"\t     SELECT *                                                               \n" +
"\t     WHERE  {                                                                    \n" +
"\t        <" + id + "> lmdb:music_contributor ?composer.                              \n" +
"\t        ?composer lmdb:music_contributor_name ?name.                                     \n" +
"\t     }                                                                           \n";

        return QueryExecutionFactory.sparqlService(LINKEDMDB_SERVICE, query).execSelect();
    }

    private static ResultSet importSubject(String id){
        String query = PREFIXES +
"\t     SELECT *                                                               \n" +
"\t     WHERE  {                                                                    \n" +
"\t        <" + id + "> skos:subject ?subject.                               \n" +
"\t        ?subject lmdb:film_subject_name ?name.                                     \n" +
"\t     }                                                                           \n";

        return QueryExecutionFactory.sparqlService(LINKEDMDB_SERVICE, query).execSelect();
    }

    private static ResultSet importRuntime(String id){
        String query = PREFIXES +
"\t     SELECT *                                                               \n" +
"\t     WHERE  {                                                                    \n" +
"\t        <" + id + "> lmdb:runtime ?runtime.                                     \n" +
"\t     }                                                                           \n";

        return QueryExecutionFactory.sparqlService(LINKEDMDB_SERVICE, query).execSelect();
    }

    private static ResultSet importDate(String id){
        String query = PREFIXES +
"\t     SELECT *                                                               \n" +
"\t     WHERE  {                                                                    \n" +
"\t        <" + id + "> dc:date ?date.                                     \n" +
"\t     }                                                                           \n";

        return QueryExecutionFactory.sparqlService(LINKEDMDB_SERVICE, query).execSelect();
    }

    private static ResultSet importLanguage(String id){
        String query = PREFIXES +
"\t     SELECT *                                                               \n" +
"\t     WHERE  {                                                                    \n" +
"\t        <" + id + "> lmdb:language ?language.                               \n" +
"\t     }                                                                           \n";

        return QueryExecutionFactory.sparqlService(LINKEDMDB_SERVICE, query).execSelect();
    }

    private static ResultSet importPage(String id){
        String query = PREFIXES +
"\t     SELECT *                                                               \n" +
"\t     WHERE  {                                                                    \n" +
"\t        <" + id + "> foaf:page ?page.                                 \n" +
"\t     }                                                                           \n";

        return QueryExecutionFactory.sparqlService(LINKEDMDB_SERVICE, query).execSelect();
    }


    */
}
