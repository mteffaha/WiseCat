			var urls = {
				base : "",
				getRecommandation:this.base+"/recommendation"
			}
			
			var yRotation = 10; // the multiplier for the rotation effect (each element will be rotated yRotation*index on the y axis)
			var animationDuration = 1.4;
			var leftTranslate = 100;
			var topTranslate = 10;
			var maxRotation  = 0; 
			
			
			var movieCount = 5;
			
			var positions=[];
			
			
			var loadPages = {
				recommendation:function(){
				
					// Setting the structure of the page
					$("main").html("<div>" +
									+"<div id=\"recommandationList\">"
									
										+"<div id=\"wrapperMoviesList\">"
											
										+"</div>"
									+"</div>"
									+"<div id=\"currentMovieDetail\">"
									
									+"</div>"
									+"</div>");
				
					// Loading Content
					
					movie = {
						poster:"http://www.impawards.com/2008/posters/dark_knight_ver12_xlg.jpg",
						title:"The Dark night",
						duration:"90",
						genre:"Thriller, Action, Psychological",
						directors:["Steven Spielberg"],
						releaseYear:"2013",
						plot:"Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?",
						trailer:"http://www.imdb.com/video/imdb/vi3843072281/imdb/single?vPage=1",
						cast:["Susan Strasberg","Susan Strasberg","Susan Strasberg","Susan Strasberg"]
					};
					
					loadMovieDetails(movie);
					
					// Setting handlers
					// Setting Handlers
				TweenMax.set($('#wrapperMoviesList'),{perspective:1200}); // Initialising the 3d scene
				
				
				maxRotation = 0;
				for(var i=0;i<movieCount;i++){
					maxRotation-=5;
					
					elementScale = i/movieCount;
					elementLeft = i * (leftTranslate * (i/movieCount));
					elementTop = i * topTranslate;
					elementRotationY = maxRotation;
					
					if(i != (movieCount -1)){
						positions.push({css: { left: elementLeft, top: elementTop, scale: elementScale ,rotationY:elementRotationY},ease:Elastic.easeOut});
					}else{ // last element
						elementRotationY = 0;
						positions.push({css: { left: elementLeft, top: elementTop, scale: elementScale ,rotationY:elementRotationY},ease:Elastic.easeOut});
					}
				}
				
			
				$.ajax({
				  url: urls.recommandation,
				  beforeSend: function( xhr ) {
					xhr.overrideMimeType( "text/plain; charset=x-user-defined" );
				  }
				})
				  .done(function( movies ) {
					//Si erreur de type de donnée , il faut parser en JSON
					movies = $.parseJSON(movies);
					console.log(movies);
					$.each(movies,function(index,movie){
						addMovie(movie);
					});
					animateCarrousel();
				  });
				
				
				
				
				$('#floatingMenu').click(function(){
					removeCurrentMovie();
					addMovie({poster:'http://gdj.gdj.netdna-cdn.com/wp-content/uploads/2011/12/grey-movie-poster.jpg',title:'The Grey'});
					
					
				});
				}
				
		
			
			}
		
			
			
			
			
			$(document).ready(function(){
				loadPages.recommendation();
				
			});
			
			
			function addMovie(movie){
					$('#wrapperMoviesList').prepend('<div class="movieElement" style="background-image:url('+movie.poster+');"><h1>'+movie.title+'</h1>'+
					'<div class="buttonContainer"><a class="warch">Watch</a><a class="watchlater">Watch Later</a><a class="ignore">Ignore</a></div>'+
					'</div>');
					animateCarrousel();
					$("#recommandationList").css("background-image",$('#wrapperMoviesList .movieElement').last().css('background-image'));
			}
			
			function removeCurrentMovie(){
				$('#wrapperMoviesList .movieElement').last().slideDown().remove();
			}
			
			function animateCarrousel(){
				$.each($('.movieElement'),function(index,element){
					TweenMax.to(element, animationDuration, positions[index]);
					
				});
			}
			
			function loadMovieDetails(movie){
			
				var cast = "";
				$.each(movie.cast,function(index,element){
					cast+="<span>"+element+"</span>";	
				});
				index = movie.trailer.indexOf("imdb");
				console.log(movie.trailer.substring(0,index));
				console.log(movie.trailer.substring(0,movie.trailer.lastIndexOf("imdb")+4));
				$("#currentMovieDetail").html("");
				$("#currentMovieDetail").html("<!-- A Panel displaying the current movie details , will be nice to show it with a parallax effect -->\n" +
						"<div class=\"moviePoster\">\n" +
						"<img src=\""+movie.poster+"\" alt=\""+movie.title+"\" />\n" +
						"</div><!-- The movie poster, we could grab it from imdb  -->\n" +
						"<div class=\"movieInformation\"><!-- the movies details -->\n" +
						"<h1>"+movie.title+"</h1> <!-- the movies title -->\n" +
						"<div> <!-- all the uncatagorised informations like , genre, duration, release date , scores on imdb/rottentomates -->\n" +
						"<span>\n" +
						"<p>Duration</p> <!-- the information title -->\n" +
						"<span>"+movie.duration+"</span><!-- the information value -->\n" +
						"</span>\n" +
						"<span>\n" +
						"<p>Genre</p> <!-- the information title -->\n" +
						"<span>"+movie.genre+"</span><!-- the information value -->\n" +
						"</span>\n" +
						"<span>\n" +
						"<p>Director</p> <!-- the information title -->\n" +
						"<span>"+movie.directors.join()+"</span><!-- the information value -->\n" +
						"</span>\n" +
						"<span>\n" +
						"<p>Release Year</p> <!-- the information title -->\n" +
						"<span>"+movie.releaseYear+"</span><!-- the information value -->\n" +
						"</span>\n" +
						"\n" +
						"</div>\n" +
						"<span>Plot :</span>\n" +
						"<div class=\"moviePlot\">\n" +
						"\n" +
						"<p>"+movie.plot+"</p>\n" +
						"</div> <!-- the movie plot -->\n" +
						"<div class=\"movieTrailer\">\n" +
						"<h2>Trailer</h2>\n" +
						"<iframe src=\""+movie.trailer.substring(0,movie.trailer.lastIndexOf("imdb")+4)+"/embed?autoplay=false&width=480\" width=\"480\" height=\"270\" allowfullscreen=\"true\" mozallowfullscreen=\"true\" webkitallowfullscreen=\"true\" frameborder=\"no\" scrolling=\"no\"></iframe>\n" +
						"<div class=\"movieCast\"> <!-- the movie cast -->\n" +
						"<h2>Cast</h2> <!-- the actor name -->\n" +cast+
						"</div>\n" +
						"\n" +
						"<div><!-- container for information about the network of user, which friends like/dislike this movies actor or any rankable attribute-->\n" +
						"</div>\n" +
						"</div>\n" +
						"</div>\n" +
						"");
				
				
				
			}
		
			
			