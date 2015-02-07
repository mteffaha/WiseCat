package org.polytech.unice.websem.wisecat.ontology;

import com.hp.hpl.jena.rdf.model.*;
import org.polytech.unice.websem.wisecat.model.Movie;

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


    private void addStatement( String leftResource,String relation, String rightResource  ){
        Statement newMovie = ResourceFactory.createStatement(ResourceFactory.createResource(NAMESPACE+leftResource)
                ,ResourceFactory.createProperty(NAMESPACE,relation)
                ,ResourceFactory.createResource(NAMESPACE+rightResource));

        // Adding the statement
        logger.info("Added Statement : "+newMovie.toString());
        model.add(newMovie);
    }



    public void likeMovie(String userID,String movieID){
        addStatement("User/"+userID,"Rm2","Movie/"+movieID);
    }


    public void followUser(String followerID,String followedID){
        addStatement("User/"+followerID,"follow","User/"+followedID);
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
