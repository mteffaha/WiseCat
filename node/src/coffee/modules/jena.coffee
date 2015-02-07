http 	= require 'http'
bl 		= require 'bl'

#############################################################################
# 								EXPORTS										#
#############################################################################

module.exports = 
	recommend 	: (cb) -> 			_query '/recommendation', cb
	lmdb 		: (cb) -> 			_query '/lmdb', cb
	search 		: (title, cb) -> 	_query '/lmdb/' + encodeURI(title), cb


#############################################################################
# 								REST API CALLS								#
#############################################################################

_query = (path, cb) ->
	console.log 'omihdf'
	options = 
		hostname	: 'localhost'
		port 		: 8080
		path		: '/WiseCat/rest/v1/private' + path
		headers		: 
			# 'Content-Type'	: 'application/json'
			'Content-Type'	: 'application/x-www-form-urlencoded'

	req = http.get options, (res) ->
		res.pipe bl cb