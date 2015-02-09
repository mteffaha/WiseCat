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
			$(document).ready(function(){
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