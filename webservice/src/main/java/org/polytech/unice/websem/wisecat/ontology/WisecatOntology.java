package org.polytech.unice.websem.wisecat.ontology;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import org.polytech.unice.websem.wisecat.model.Movie;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by mtoffaha on 07/02/2015.
 */
public class WisecatOntology {


    private Logger logger = Logger.getLogger(WisecatOntology.class.getName());

    private static WisecatOntology instance;


    public static WisecatOntology getInstance(String modelURL){
        if(instance == null){
            instance = new WisecatOntology(modelURL);
        }

        return instance;
    }


    private WisecatOntology(String modelURL){
        Model m2 = ModelFactory.createDefaultModel();
        m2.read(modelURL);
        logger.info("Ontology Loaded");
    }



    public List<Movie> executeMovieQuery(String query){

    }

}
