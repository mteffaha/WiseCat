package org.polytech.unice.websem.wisecat.controller;

import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import org.polytech.unice.websem.wisecat.model.Movie;
import org.polytech.unice.websem.wisecat.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/v1/private/")
public class RecommandationController {

	String[] movieTitles = {"the Grey","Saw","The Dark night","the shawshank redemption","Into the wild","The expendables"};
	String[] posters = {"the Grey",
			"http://bcgavel.com/wp-content/uploads/2013/02/saw-poster.jpg",
			"http://www.impawards.com/2008/posters/dark_knight_ver3.jpg",
			"http://www.amovieaweek.com/images/shawshankposter.jpg",
			"http://www.travaasa.com/holidayproblems/wp-content/uploads/2012/11/into-the-wild-movie-poster-1020406877.jpg",
			"http://cdn.collider.com/wp-content/uploads/the-expendables-2-poster2.jpg"};

	@RequestMapping(value = "recommandation", method = RequestMethod.GET)
	public @ResponseBody
	List<Movie> getShopInJSON() {
		List<Movie> recommandations = new ArrayList<Movie>();
		Random random = new Random();
		for(int i=0;i<movieTitles.length;i++){
			Movie mv = new Movie(movieTitles[i],Math.abs((random.nextInt()+90) % 200),"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",posters[i]);
			mv.setGenres(getRandomGenre());
			recommandations.add(mv);
			mv.setPoster(posters[i]);
			mv.setActors(getRandomCast());
		}



		return recommandations;

	}


	String[] genresList = {"Action","Adventure","Comedy","Crime","Fiction","Fantasy","Historical","Horror","Mystery","Paranoid","Philosophical","Political","Romance","Saga","Satire","Science fiction","Slice of Life","Speculative","Thriller","Urban"};
	List<String> getRandomGenre(){
		List<String> genres = new ArrayList<String>();
		int max = new Random().nextInt(5)+1;
		for(int i=0;i<max;i++){
			genres.add(genresList[new Random().nextInt(genresList.length)]);
		}
		return genres;
	}

	String[] actorsNames = {"Marlon Brando","Jack Nicholson","James Stewart","Humphrey Bogart","Spencer Tracy","Henry Fonda","Robert De Niro","Gary Cooper","Charles Chaplin","Anthony Hopkins","John Wayne","Fred Astaire","Laurence Olivier","Cary Grant","Clark Gable","Dustin Hoffman","Paul Newman","Gregory Peck","Al Pacino","William Holden","Buster Keaton","James Dean","James Cagney","Gene Kelly","Robert Mitchum","Burt Lancaster","Sidney Poitier","Kirk Douglas","Michael Caine","Peter Sellers","Gene Hackman","Jack Lemmon","Ralph Fiennes","Johnny Depp","Anthony Quinn","Richard Burton","Robert Duvall","Sean Penn","John Hurt","Orson Welles","William Hurt","Kenneth Branagh","John Gielgud","Marcello Mastroianni","Edward G. Robinson","Clint Eastwood","Charlton Heston","Yves Montand","Liam Neeson","Denzel Washington","Kevin Spacey","Robert Redford","Steve McQueen","Alec Guinness","Richard Harris","Peter O'Toole","Harrison Ford","Gary Oldman","Tom Hanks","Morgan Freeman","Leonardo DiCaprio","Jon Voight","Alan Rickman","Christopher Walken","Mel Brooks","Colin Firth","Daniel Day-Lewis","Heath Ledger","Christopher Plummer","Andy Garcia","Javier Bardem","Robert Downey Jr.","Jim Carrey","Tommy Lee Jones","Sean Connery","Bill Murray","Alan Arkin","Max von Sydow","Jeff Bridges","Ben Kingsley","Steve Buscemi","Ian McKellen","Richard Dreyfuss","Jeremy Irons","John Malkovich","Samuel L. Jackson","Russell Crowe","Philip Seymour Hoffman","Matt Damon","Christian Bale","Brad Pitt","Tim Robbins","Chris Cooper","Kevin Costner","Benicio Del Toro","Edward Norton","Vincent Cassel","Viggo Mortensen","Tom Cruise","Christopher Lee"
	};

	List<Person> getRandomCast(){
		List<Person> cast = new ArrayList<Person>();
		int max = (new Random().nextInt(20)+5);
		for(int i=0;i<max;i++){
			cast.add(new Person(actorsNames[new Random().nextInt(actorsNames.length)]));
		}
		return cast;
	}

}