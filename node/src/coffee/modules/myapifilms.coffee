http 	= require 'http'
bl 		= require 'bl'

#####################
# 		EXPORTS 	#
#####################

module.exports =
	autocomplete 	: (text, cb) -> _query 
										title: text
										limit: 3,
										cb
	data 			: (text, cb) -> _query
										title: text,
										cb

###
# Query
# 
# La fonction permettant d'executer une requête (HTML GET) sur le service de OMDb
###
_query = (params, cb) ->
	getParams = ''
	getParams += key + '=' + encodeURI(val) + '&' for key, val of params

	options = 
		hostname	: 'www.myapifilms.com'
		path		: '/imdb?' + getParams
		headers		: 
			'Content-Type'	: 'application/x-www-form-urlencoded'

	req = http.get options, (res) ->
		res.pipe bl cb

# _autocomplete = (text, cb) ->
	# onData = (err, data, cb1) =>
	# 	data = JSON.parse data
	# 	cb1 err, (d.title for d in data)

	# _query
	# 	title: text
	# 	limit: 3,
	# 	cb

# _data = (text, cb) ->
	# onData = (err, data, cb2) ->
	# 	data = JSON.parse data
	# 	data = 
	# 		poster 	: data.urlPoster
	# 		plot 	: data.plot
	# 	cb2 err, data

	# _query
	# 	title: text,
	# 	cb