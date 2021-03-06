package org.polytech.unice.websem.wisecat.controller;

import org.apache.jena.atlas.lib.RandomLib;
import org.polytech.unice.websem.wisecat.RemoteQuery.MyMovieAPIWrapper;
import org.polytech.unice.websem.wisecat.model.Message;
import org.polytech.unice.websem.wisecat.model.Movie;
import org.polytech.unice.websem.wisecat.model.RankableString;
import org.polytech.unice.websem.wisecat.RemoteQuery.RemoteSparqlMovie;
import org.polytech.unice.websem.wisecat.ontology.WisecatOntology;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/v1/private/")
public class RecommandationController {


	@Autowired
	private ApplicationContext applicationContext;



	@RequestMapping(value = "lmdb/{title}", method = RequestMethod.GET)
	public Movie lmdb(@PathVariable("title") String title) {
		String movieId = RemoteSparqlMovie.search(title).toString();
		return RemoteSparqlMovie.importMovie(movieId);

		// System.out.println("http://data.linkedmdb.org/resource/film/1771");
		// RemoteSparqlMovie.importMovie("http://data.linkedmdb.org/resource/film/1771");
		// System.out.println("http://data.linkedmdb.org/resource/actor/29411");
		// RemoteSparqlActor.importActor("http://data.linkedmdb.org/resource/actor/29411");
		// System.out.println("http://data.linkedmdb.org/resource/film_featured_song/67");
		// RemoteSparqlActor.importSong("http://data.linkedmdb.org/resource/film_featured_song/67");
		// System.out.println("http://data.linkedmdb.org/resource/film_genre/12");
		// RemoteSparqlActor.importGenre("http://data.linkedmdb.org/resource/film_genre/12");
	}

	String[] movieTitles = {"the Grey","Saw","The Dark night","the shawshank redemption","Into the wild","The expendables"};
	String[] posters = {"the Grey",
			"http://bcgavel.com/wp-content/uploads/2013/02/saw-poster.jpg",
			"http://www.impawards.com/2008/posters/dark_knight_ver3.jpg",
			"http://www.amovieaweek.com/images/shawshankposter.jpg",
			"http://www.travaasa.com/holidayproblems/wp-content/uploads/2012/11/into-the-wild-movie-poster-1020406877.jpg",
			"http://cdn.collider.com/wp-content/uploads/the-expendables-2-poster2.jpg"};

	@RequestMapping(value = "recommendation", method = RequestMethod.GET)
	public @ResponseBody
	List<Movie> getShopInJSON() {


		Resource resource = applicationContext.getResource("wisecat.ttl");
		WisecatOntology ontology = null;


		try {
			ontology = WisecatOntology.getInstance(resource.getFile().getPath());
		} catch (IOException e) {
			e.printStackTrace();
		}

		ontology.likeMovie("jj12312312","imdb213213");

		ontology.followUser("jj12312312", "imdb213213");
		ontology.addUser("user123", "Mortadha Teffaha");
		ontology.addUser("user001", "Truchi Romain");

		ontology.likeActor("user123","29704");

		ontology.postMessage("user123", "bla bla bla bla bla");
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ontology.searchUser("Mortadha Teffaha");
		List<Message> messages = ontology.getWall("user123");
		System.out.println("###########    Messages Fetched "+messages.size());
		for(Message msg : messages){
			System.out.println("# Mesage : "+msg.getMessage());
		}

		//ontology.printOntology();
		List<Movie> movies = ontology.getRecommandation("user123");

		for(int i=0;i<movies.size();i++){
			movies.set(i, MyMovieAPIWrapper.populateMovie(movies.get(i)));
		}
		return movies;



	}


	String[] genresList = {"Action","Adventure","Comedy","Crime","Fiction","Fantasy","Historical","Horror","Mystery","Paranoid","Philosophical","Political","Romance","Saga","Satire","Science fiction","Slice of Life","Speculative","Thriller","Urban"};
	List<RankableString> getRandomGenre(){
		List<RankableString> genres = new ArrayList<RankableString>();
		int max = new Random().nextInt(5)+1;
		for(int i=0;i<max;i++){
			genres.add(new RankableString("genre"+i,genresList[new Random().nextInt(genresList.length)]));
		}
		return genres;
	}

	String[] actorsNames = {"Marlon Brando","Jack Nicholson","James Stewart","Humphrey Bogart","Spencer Tracy","Henry Fonda","Robert De Niro","Gary Cooper","Charles Chaplin","Anthony Hopkins","John Wayne","Fred Astaire","Laurence Olivier","Cary Grant","Clark Gable","Dustin Hoffman","Paul Newman","Gregory Peck","Al Pacino","William Holden","Buster Keaton","James Dean","James Cagney","Gene Kelly","Robert Mitchum","Burt Lancaster","Sidney Poitier","Kirk Douglas","Michael Caine","Peter Sellers","Gene Hackman","Jack Lemmon","Ralph Fiennes","Johnny Depp","Anthony Quinn","Richard Burton","Robert Duvall","Sean Penn","John Hurt","Orson Welles","William Hurt","Kenneth Branagh","John Gielgud","Marcello Mastroianni","Edward G. Robinson","Clint Eastwood","Charlton Heston","Yves Montand","Liam Neeson","Denzel Washington","Kevin Spacey","Robert Redford","Steve McQueen","Alec Guinness","Richard Harris","Peter O'Toole","Harrison Ford","Gary Oldman","Tom Hanks","Morgan Freeman","Leonardo DiCaprio","Jon Voight","Alan Rickman","Christopher Walken","Mel Brooks","Colin Firth","Daniel Day-Lewis","Heath Ledger","Christopher Plummer","Andy Garcia","Javier Bardem","Robert Downey Jr.","Jim Carrey","Tommy Lee Jones","Sean Connery","Bill Murray","Alan Arkin","Max von Sydow","Jeff Bridges","Ben Kingsley","Steve Buscemi","Ian McKellen","Richard Dreyfuss","Jeremy Irons","John Malkovich","Samuel L. Jackson","Russell Crowe","Philip Seymour Hoffman","Matt Damon","Christian Bale","Brad Pitt","Tim Robbins","Chris Cooper","Kevin Costner","Benicio Del Toro","Edward Norton","Vincent Cassel","Viggo Mortensen","Tom Cruise","Christopher Lee"
	};

	List<RankableString> getRandomCast(){
		List<RankableString> cast = new ArrayList<RankableString>();
		int max = (new Random().nextInt(20)+5);
		for(int i=0;i<max;i++){
			cast.add(new RankableString("user"+i,actorsNames[new Random().nextInt(actorsNames.length)]));
		}
		return cast;
	}

}