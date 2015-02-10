var yRotation = 10; // the multiplier for the rotation effect (each element will be rotated yRotation*index on the y axis)
var animationDuration = 1.4;
var leftTranslate = 100;
var topTranslate = 10;
var maxRotation  = 0; 

var movieCount = 5;

var positions=[];


$(document).ready(function(){
	// Facebook connection !
	FB.connect();
	console.log('qsfdqsdfqsdfs');

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

	$('#floatingMenu').click(function(){
		removeCurrentMovie();
		addMovie({poster:'http://gdj.gdj.netdna-cdn.com/wp-content/uploads/2011/12/grey-movie-poster.jpg',title:'The Grey'});
		
		
	});

	/*************************************
	 * 		EVENT HANDLERS TO JAVA API
	 *************************************/

	// Recommendation
	$('#recommendation').on('click', function(event){
		userURI 	= FB.user.id;
		
		AJAX.ajax('/recommendation', {
			userURI		: userURI
		}, AJAX.CB.recommendation);
	});
	// Fire recommendation on load !
	$('#recommendation').trigger('click');

	// Autocomplete
	$('#searchButton input').on('keyup', function(event){
		if(event.key.length == 1){
			text 		= $(this).val();
			
			AJAX.ajax('/autocomplete', {
				text		: text
			}, AJAX.CB.autocomplete);
		}
	});

	// Search movie
	$('#searchButton input').on('keyup', function(event){
		if(event.keyCode == 13){
			title 		= $(this).val();
			
			AJAX.ajax('/search', {
				title		: title
			}, AJAX.CB.search);
		}
	});

	// Like a rankable
	$('DOM').on('EVENT', function(event){
		userURI 	= FB.user.id;
		rankableURI = '';

		AJAX.ajax('/like', {
			userURI: userURI,
			rankableURI	: rankableURI
		}, AJAX.CB.like);
	});

	// Recommend to user
	// $('DOM').on('EVENT', function(event){
	// 	user1URI 	= FB.user.id;
	// 	rankableURI = '';
	// 	user2URI 	= '';
		
	// 	AJAX.ajax('/recommend', {
	// 		user1URI	: user1URI,
	// 		rankableURI	: rankableURI,
	// 		user2URI	: user2URI
	// 	}, AJAX.CB.recommend);
	// });

	// Follow user
	// $('DOM').on('EVENT', function(event){
	// 	user1URI 	= FB.user.id;
	// 	user2URI 	= '';
		
	// 	AJAX.ajax('/follow', {
	// 		user1URI	: user1URI,
	// 		user2URI	: user2URI
	// 	}, AJAX.CB.follow);
	// });

	// User matches
	// $('DOM').on('EVENT', function(event){
	// 	userURI 	= FB.user.id;
		
	// 	AJAX.ajax('/match', {
	// 		userURI		: userURI
	// 	}, AJAX.CB.match);
	// });

	// Post message
	// $('DOM').on('EVENT', function(event){
	// 	userURI 	= FB.user.id;
	// 	message 	= '';
		
	// 	AJAX.ajax('/post', {
	// 		userURI		: userURI,
	// 		message		: message
	// 	}, AJAX.CB.post);
	// });

	// Get wall
	// $('DOM').on('EVENT', function(event){
	// 	userURI 	= FB.user.id;
		
	// 	AJAX.ajax('/wall', {
	// 		userURI		: userURI
	// 	}, AJAX.CB.wall);
	// });

	// Watch later
	// $('DOM').on('EVENT', function(event){
	// 	userURI 	= FB.user.id;
	// 	movieURI	= '';
		
	// 	AJAX.ajax('/later', {
	// 		userURI		: userURI,
	// 		movieURI	: movieURI
	// 	}, AJAX.CB.later);
	// });

	// Watch list
	// $('DOM').on('EVENT', function(event){
	// 	userURI 	= FB.user.id;
		
	// 	AJAX.ajax('/list', {
	// 		userURI		: userURI
	// 	}, AJAX.CB.list);
	// });

	// Search user
	// $('DOM').on('EVENT', function(event){
	// 	userName 	= '';
		
	// 	AJAX.ajax('/user', {
	// 		userURI		: userURI
	// 	}, AJAX.CB.user);
	// });
});

//
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

/*************************************
 * 			JAVA API CALLBACKS
 *************************************/

AJAX = {
	CB: {
		// Like a rankable
		like: function(data){
		},

		// Recommendation
		recommendation: function(data){
			//Si erreur de type de donnée , il faut parser en JSON
			movies = $.parseJSON(movies);
			console.log(movies);
			$.each(movies,function(index,movie){
				addMovie(movie);
			});
			animateCarrousel();
		},

		// Autocomplete
		autocomplete: function(data){
			console.log(data);
		},

		// Search movie
		search: function(data){
			console.log(data);
		},

		// Recommend to user
		recommend: function(data){

		},

		// Follow user
		follow: function(data){

		},

		// User matches
		match: function(data){

		},

		// Post message
		post: function(data){

		},

		// Get wall
		wall: function(data){

		},

		// Watch later
		later: function(data){

		},

		// Watch list
		list: function(data){

		},

		// Search user
		user: function(data){

		}
	},

	// Ajax helper
	ajax: function(url, data, callback){
		$.ajax({
			url: url,
			type: 'POST',
			data: data,
			dataType: 'json',
			success: callback
		});
	}
};

/*************************************
 * 		FACEBOOK AUTHENTICATION
 *************************************/

FB = {
	user: {
		id: '',
		name: '',
		token: ''
	},

	connect: function(){
		FB.getLoginStatus(FB.statusChangeCallback);
	},

	statusChangeCallback: function(response){
		if(response.status 	== 'connected'){
			FB.user.id 		= response.authResponse.userID;
			FB.user.token 	= response.authResponse.accessToken;
			FB.api('/me', function(response){
				FB.user.name = response.name;// first_name, last_name, name
				AJAX.ajax('/addUser', FB.user, $.noop);
			});
		}else{
			FB.login(FB.statusChangeCallback);
		}
	}
};