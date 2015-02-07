http 	= require 'http'
bl 		= require 'bl'

#############################################################################
# 								EXPORTS										#
#############################################################################

module.exports = 
	recommend : (cb) -> _query '/recommendation', cb
	lmdb : (cb) -> _query '/lmdb', cb


#############################################################################
# 								REST API CALLS								#
#############################################################################

_query = (path, cb) ->
	options = 
		hostname	: 'localhost'
		port 		: 8080
		path		: '/WiseCat/rest/v1/private' + path
		headers		: 
			# 'Content-Type'	: 'application/json'
			'Content-Type'	: 'application/x-www-form-urlencoded'

	req = http.get options, (res) ->
		res.pipe bl cb