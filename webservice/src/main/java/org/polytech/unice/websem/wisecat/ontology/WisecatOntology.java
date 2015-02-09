package org.polytech.unice.websem.wisecat.ontology;

import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.*;
import org.polytech.unice.websem.wisecat.RemoteQuery.RemoteSparqlMovie;
import org.polytech.unice.websem.wisecat.model.Message;
import org.polytech.unice.websem.wisecat.model.Movie;
import org.polytech.unice.websem.wisecat.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by mtoffaha on 07/02/2015.
 */
public class WisecatOntology {

    private static final String NAMESPACE = "http://www.wisecat.com";
    private static final String LINKEDMDB_SERVICE = "http://data.linkedmdb.org/sparql";
    private static final String LINKEDMDB_PREFIXES =
            "  PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                    "\tPREFIX dc:   <http://purl.org/dc/terms/>\n" +
                    "\tPREFIX owl:  <http://www.w3.org/2002/07/owl#>\n" +
                    "\tPREFIX lmdb: <http://data.linkedmdb.org/resource/movie/>\n" +
                    "\tPREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" +
                    "\tPREFIX skos: <http://www.w3.org/2004/02/skos/core#>\n";
    private Logger logger = Logger.getLogger(WisecatOntology.class.getName());



    private Model model;
    private Resource resource;

    private static WisecatOntology instance;


    public static WisecatOntology getInstance(String modelURL){
        if(instance == null){
            instance = new WisecatOntology(modelURL);
        }
        return instance;
    }


    private WisecatOntology(String modelURL){
        model = ModelFactory.createDefaultModel();
        // Reading the model
        model.read(modelURL);
        logger.info("## Ontology Loaded");
    }


    private void addStatementBetweenResources(String leftResource, String relation, String rightResource){
        Statement newMovie = ResourceFactory.createStatement(ResourceFactory.createResource(NAMESPACE+"/"+leftResource)
                ,ResourceFactory.createProperty(NAMESPACE,relation)
                ,ResourceFactory.createResource(NAMESPACE+"/"+rightResource));

        // Adding the statement
        logger.info("Added Statement : "+newMovie.toString());
        model.add(newMovie);
    }

    private void addStatementBetweenResourceAndProperty(String leftResource,String relation,Object property){
        Statement newMovie = ResourceFactory.createStatement(ResourceFactory.createResource(NAMESPACE+"/"+leftResource)
                ,ResourceFactory.createProperty(NAMESPACE,relation)
                ,ResourceFactory.createTypedLiteral(property));

        // Adding the statement
        logger.info("Added Statement : "+newMovie.toString());
        model.add(newMovie);
    }

    private ResultSet executeQuery(String query){

        String strQuery = "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" +
                "PREFIX fn: <http://www.w3.org/2005/xpath-functions#>\n" +
                "PREFIX skos: <http://www.w3.org/2004/02/skos/core#>\n" +
                "PREFIX wc: <"+NAMESPACE+">\n" +
                "PREFIX user: <"+NAMESPACE+"/User>\n"
                + query;


        Query queryObject = QueryFactory.create(strQuery);

        QueryExecution qExe = QueryExecutionFactory.create(queryObject, model);
        return qExe.execSelect();
    }

    public void likeMovie(String userID,String movieID){ // TODO Set Like Level


        addStatementBetweenResources("User/" + userID, "/Rm2", "Movie/" + movieID); // TODO Check that this is compatible with linkedMDB (URI Wise)

    }

    public List<Movie> getRecommandation(String userID){

        List<Movie> recommandation = new ArrayList<Movie>();

        // Get Recommandation

        ResultSet resultSet = executeQuery("SELECT ?movie WHERE{ " +
                "<http://www.wisecat.com/User/"+userID+"> <http://www.wisecat.com/Rm2> ?rankable ." +
                "?rankable <http://www.wisecat.com/with> ?movie}");

        int grabbed = 0;
        while(resultSet.hasNext() && grabbed<5){
            // Fetch ID
            QuerySolution solution = resultSet.next();

            if(solution.contains("movie")){
                String movieID =solution.get("movie").asResource().getURI().substring(solution.get("movie").asResource().getURI().lastIndexOf('/') + 1);
                recommandation.add(RemoteSparqlMovie.importMovie(movieID));
            }
            // Fetch Movie

            grabbed++;
        }
        // Add as recommanded

        return recommandation;
    }

    public void likeActor(String userID,String actorID){
        // Check if we fetched Related Movie
        List<Message> mesages = new ArrayList<Message>();
        ResultSet resultsRes = executeQuery("SELECT ?movie WHERE{ ?movie <http://www.wisecat.com/with> <http://www.wisecat.com/Rankable/"+actorID+"> }");

        if(resultsRes.hasNext()){ // Rankable already liked
            logger.info("Actor Already Liked");
            return;
        }

        // Like Rankable
        addStatementBetweenResources("User/" + userID, "/Rm2", "Rankable/" + actorID);


        // Add Related Movie
        ResultSet results = QueryExecutionFactory.sparqlService(LINKEDMDB_SERVICE, "SELECT ?movie WHERE { ?movie <http://data.linkedmdb.org/resource/movie/actor> <http://data.linkedmdb.org/resource/actor/29704> }").execSelect();

        while(results.hasNext()){
            QuerySolution solution = results.next();

            String mov = solution.get("movie").asResource().getURI().substring(solution.get("movie").asResource().getURI().lastIndexOf('/')+1);

            addStatementBetweenResources("Rankable/" + actorID, "/with", "Movie/" + mov);
        }

    }

    public void followUser(String followerID,String followedID){
        addStatementBetweenResources( "User/"+followerID, "/follow",  "User/"+followedID);
    }

    public void addUser(String userId,String userName){
        addStatementBetweenResourceAndProperty( "User/"+userId, "/name", userName);
    }

    public List<User> searchUser(final String searchPhrase){
        List<User> matches = new ArrayList<User>();
        ResultSet resultsRes = executeQuery("select ?name ?user where {\n" +
               "?user <http://www.wisecat.com/name> ?name .\n" +
                "\tFILTER( fn:contains( ?name , '"+searchPhrase+"'))\n}");

        try {
            System.out.println("Checking Found Users");
            while (resultsRes.hasNext()) {
                QuerySolution solution = resultsRes.nextSolution();
                System.out.println(solution.toString());
                String userID = "";
                String userName = "";


                if(solution.contains("name")){
                    userName = solution.get("name").asLiteral().getString();
                }

                if(solution.contains("user")){
                    userID = solution.get("user").asResource().getLocalName();
                }
                matches.add(new User(userID,userName));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return matches;
    }

    public void postMessage(String userId,String message){
        addStatementBetweenResourceAndProperty("User/"+userId,"/post",message);
    }

    public List<Message> getWall(String userID){
        List<Message> mesages = new ArrayList<Message>();
        ResultSet resultsRes = executeQuery("SELECT ?message WHERE{ <http://www.wisecat.com/User/"+userID+"> <http://www.wisecat.com/post> ?message }");

        try {
            while (resultsRes.hasNext()) {
                QuerySolution soln = resultsRes.nextSolution();
                if(soln.contains("message")) {
                    mesages.add(new Message(soln.get("message").asLiteral().getString(), new Date()));
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return mesages;
    }

    public void printOntology(){
        System.out.println("#########################################################################################");
        //model.write(System.out);
        StmtIterator iter = model.listStatements();
        while(iter.hasNext()){
            Statement st = iter.next();
            System.out.println(st);

        }
        System.out.println("#########################################################################################");
    }


}
