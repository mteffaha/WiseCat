window.fbAsyncInit = ->
	FB.init
		appId      : '769251583167247'
		cookie     : true  		# enable cookies to allow the server to access 
		                		# the session
		xfbml      : true  		# parse social plugins on this page
		version    : 'v2.1' 	# use version 2.1

# https://developers.facebook.com/docs/facebook-login/login-flow-for-web/v2.2?locale=fr_FR
# https://developers.facebook.com/apps/769251583167247/dashboard/