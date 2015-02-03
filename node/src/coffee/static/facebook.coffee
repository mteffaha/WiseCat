# https://developers.facebook.com/docs/facebook-login/login-flow-for-web/v2.2?locale=fr_FR
# https://developers.facebook.com/apps/769251583167247/dashboard/

FB.statusChangeCallback = (response) ->
	console.log response
	if response.status is 'connected'
		console.log response.authResponse.userID
		console.log response.authResponse.accessToken
	else
		FB.login 	FB.statusChangeCallback,
					scope: 'public_profile,email,user_friends' # useful ?
$ ->
	FB.getLoginStatus FB.statusChangeCallback