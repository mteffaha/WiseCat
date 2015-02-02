express 			= require 'express'
WC 					= express()
bodyParser 			= require 'body-parser'
async 				= require 'async'
lmdb	 			= require './queries'
myapi 	 			= require './myapifilms'

###
# Server config
###
server = WC.listen 3000, ->
	host = server.address().address
	port = server.address().port
	console.log 'Server listening at : http://%s:%s', host, port

###
# Static content
###
WC.use express.static __dirname
WC.use bodyParser.urlencoded(
	extended: yes
	)

###
# Routing
###

# Search
WC.route '/autocomplete'
	.post (req, res) ->
		text = req.body.val
		myapi.autocomplete text, (err, data) ->
			data = JSON.parse data
			res.json (d.title for d in data)
		# async.parallel
		# 	autocomplete: (cb)	-> myapi.autocomplete 	text, cb
		# 	data		: (cb)	-> myapi.data 			text, cb,
		# 	(err, results)		-> console.log results


WC.route '/search'
	.post (req, res) ->
		text = req.body.val
		lmdb.search title: text, (err, contents) -> res.json contents.toString()

		# async.waterfall [
		# 	(cb)			 -> lmdb.search text,
		# 	(err, results)	 -> console.log results
		# ]