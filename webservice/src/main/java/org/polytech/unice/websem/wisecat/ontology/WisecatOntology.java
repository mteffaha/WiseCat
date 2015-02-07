package org.polytech.unice.websem.wisecat.ontology;

import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.*;
import org.polytech.unice.websem.wisecat.model.Movie;
import org.polytech.unice.websem.wisecat.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by mtoffaha on 07/02/2015.
 */
public class WisecatOntology {

    private static final String NAMESPACE = "http://www.wisecat.com/#";


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
        Statement newMovie = ResourceFactory.createStatement(ResourceFactory.createResource(NAMESPACE+leftResource)
                ,ResourceFactory.createProperty(NAMESPACE,relation)
                ,ResourceFactory.createResource(NAMESPACE+rightResource));

        // Adding the statement
        logger.info("Added Statement : "+newMovie.toString());
        model.add(newMovie);
    }

    private void addStatementBetweenResourceAndProperty(String leftResource,String relation,String property){
        Statement newMovie = ResourceFactory.createStatement(ResourceFactory.createResource(NAMESPACE+leftResource)
                ,ResourceFactory.createProperty(NAMESPACE,relation)
                ,ResourceFactory.createTypedLiteral(property));

        // Adding the statement
        logger.info("Added Statement : "+newMovie.toString());
        model.add(newMovie);
    }



    public void likeMovie(String userID,String movieID){
        addStatementBetweenResources("User/" + userID, "Rm2", "Movie/" + movieID);
    }


    public void followUser(String followerID,String followedID){
        addStatementBetweenResources("User/" + followerID, "follow", "User/" + followedID);
    }


    public void addUser(String userId,String userName){
        addStatementBetweenResourceAndProperty("User/" + userId, "name", userName);
    }


    public List<User> searchUser(String searchPhrase){
        List<User> matches = new ArrayList<User>();
        Query query = QueryFactory.create("PREFIX wc:  <http://www.wisecat.com/#>" +
                "select ?name ?user where {" +
                "?user wc:name ?name." +
                "FILTER(contains(?name,\""+searchPhrase+"\"))}"); //s2 = the query above
        QueryExecution qExe = QueryExecutionFactory.create(query, model);
        ResultSet resultsRes = qExe.execSelect();

        try {
            while (resultsRes.hasNext()) {
                QuerySolution solution = resultsRes.nextSolution();
                System.out.println("Result Found : ");
                String userID = "";
                String userName = "";


                if(solution.contains("name")){
                    userName = solution.get("name").asLiteral().getString();
                }

                if(solution.contains("user")){
                    userID = solution.get("user").asLiteral().getString();
                }
                matches.add(new User(userID,userName));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return matches;
    }


    public void postMessage(String userId,String message){
        addStatementBetweenResourceAndProperty(userId,"post",message);
    }

    public void printOntology(){
        System.out.println("#########################################################################################");
        model.write(System.out);
        System.out.println("#########################################################################################");
    }



    public List<Movie> executeMovieQuery(String query){
        return null;
    }

}
