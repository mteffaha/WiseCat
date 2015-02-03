# https://developers.facebook.com/docs/facebook-login/login-flow-for-web/v2.2?locale=fr_FR
# https://developers.facebook.com/apps/769251583167247/dashboard/

FB.connect = ->
	FB.getLoginStatus FB.statusChangeCallback

FB.statusChangeCallback = (response) ->
	if response.status is 'connected'
		FB.user.id 		= response.authResponse.userID
		FB.user.token 	= response.authResponse.accessToken
		FB.api '/me', (response) -> FB.user.name = response.first_name # first_name, last_name, name
	else
		FB.login 	FB.statusChangeCallback,
					# scope: 'public_profile,email,user_friends' # useful ?

FB.user = 
	id 		: ''
	name 	: ''
	token 	: ''

$ ->
	FB.connect()
