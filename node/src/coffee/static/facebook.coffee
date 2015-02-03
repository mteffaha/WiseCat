window.fbAsyncInit = ->
	FB.init
		appId      : '{your-app-id}'
		cookie     : true  		# enable cookies to allow the server to access 
		                	# the session
		xfbml      : true  		# parse social plugins on this page
		version    : 'v2.1' 	# use version 2.1
