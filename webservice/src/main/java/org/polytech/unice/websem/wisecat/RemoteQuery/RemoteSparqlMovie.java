package org.polytech.unice.websem.wisecat.RemoteQuery;

import org.polytech.unice.websem.wisecat.model.Movie;
import org.polytech.unice.websem.wisecat.model.RankableString;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSetFormatter;
import java.util.ArrayList;
import java.util.List;

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

    public static String search(String title){
        String query = PREFIXES +
                "\t     SELECT *                                                               \n" +
                "\t     WHERE  {                                                                    \n" +
                "\t        ?movieId dc:title \"" + title + "\".                               \n" +
                "\t     }                                                                           \n";

        ResultSet results = QueryExecutionFactory.sparqlService(LINKEDMDB_SERVICE, query).execSelect();
        // TODO next() == NULL
        return results.next().get("movieId").toString();
    }

    public static Movie importMovie(String movieId){
        Movie movie = new Movie();
        movie.setMovieID(movieId);

        ResultSet results = RemoteSparqlMovie.importActors(movieId);

        List<RankableString> list = new ArrayList<RankableString>();
        RankableString person = new RankableString("","");
        String name;
        String id;
        while(results.hasNext()){
            id = results.next().get("actor").toString();
            name = results.next().get("name").toString();
            person.setRankableID(id);
            person.setName(name);
            list.add(person);
        }
        movie.setActors(list);
        // ResultSetFormatter.out(System.out, results);

        results = RemoteSparqlMovie.importDirector(movieId);

        list = new ArrayList<RankableString>();
        person = new RankableString("","");

        while(results.hasNext()){
            id = results.next().get("director").toString();
            name = results.next().get("name").toString();
            person.setRankableID(id);
            person.setName(name);
            list.add(person);
        }
        movie.setDirectors(list);
        // ResultSetFormatter.out(System.out, results);

        results = RemoteSparqlMovie.importEditor(movieId);

        list = new ArrayList<RankableString>();
        person = new RankableString("","");

        while(results.hasNext()){
            id = results.next().get("editor").toString();
            name = results.next().get("name").toString();
            person.setRankableID(id);
            person.setName(name);
            list.add(person);
        }
        movie.setEditors(list);
        // ResultSetFormatter.out(System.out, results);

        results = RemoteSparqlMovie.importWriter(movieId);

        list = new ArrayList<RankableString>();
        person = new RankableString("","");
        while(results.hasNext()){
            id = results.next().get("writer").toString();
            name = results.next().get("name").toString();
            person.setRankableID(id);
            person.setName(name);
            list.add(person);
        }
        movie.setWriters(list);
        // ResultSetFormatter.out(System.out, results);

        results = RemoteSparqlMovie.importGenre(movieId);

        list = new ArrayList<RankableString>();
        while(results.hasNext()){
            id = results.next().get("genre").toString();
            name = results.next().get("name").toString();
            person.setRankableID(id);
            person.setName(name);
            list.add(person);
        }
        movie.setGenres(list);
        // ResultSetFormatter.out(System.out, results);

        results = RemoteSparqlMovie.importSong(movieId);

        List<String> listStr = new ArrayList<String>();
        while(results.hasNext()){
            name = results.next().get("name").toString();
            listStr.add(name);
        }
        movie.setSongs(listStr);
        // ResultSetFormatter.out(System.out, results);

        results = RemoteSparqlMovie.importComposer(movieId);

        list = new ArrayList<RankableString>();
        person = new RankableString("","");
        while(results.hasNext()){
            id = results.next().get("composer").toString();
            name = results.next().get("name").toString();
            person.setRankableID(id);
            person.setName(name);
            list.add(person);
        }
        movie.setComposers(list);
        // ResultSetFormatter.out(System.out, results);

        results = RemoteSparqlMovie.importSubject(movieId);

        listStr = new ArrayList<String>();
        person = new RankableString("","");
        while(results.hasNext()){
            id = results.next().getLiteral("subject").getString();
            name = results.next().getLiteral("name").getString();
            person.setRankableID(id);
            person.setName(name);
            listStr.add(id);
        }
        movie.setSubject(listStr);
        // ResultSetFormatter.out(System.out, results);

        results = RemoteSparqlMovie.importRuntime(movieId);

        // TODO == NULL
        movie.setDuration(Integer.parseInt(results.next().getLiteral("duration").getString()));
        // ResultSetFormatter.out(System.out, results);

        results = RemoteSparqlMovie.importDate(movieId);

        // TODO == NULL
        // TODO Transform to Date
        //movie.setReleaseDate(results.next().getLiteral("date").getString());
        // ResultSetFormatter.out(System.out, results);

        results = RemoteSparqlMovie.importLanguage(movieId);

        // TODO == NULL
        movie.setLanguage(results.next().getLiteral("language").getString());
        // ResultSetFormatter.out(System.out, results);

        results = RemoteSparqlMovie.importPage(movieId);

        // TODO == NULL
        // TODO Find the correct id
        //movie.setImdbID(results.next().getLiteral("imdb"));
        // ResultSetFormatter.out(System.out, results);
        return movie;
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
