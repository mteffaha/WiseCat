express 			= require 'express'
WC 					= express()
bodyParser 			= require 'body-parser'
async 				= require 'async'
lmdb	 			= require './modules/queries'
myapi 	 			= require './modules/myapifilms'

###
# Server config
###
server = WC.listen 3000, ->
	host = server.address().address
	port = server.address().port

###
# Static content
###
WC.use express.static __dirname + '/static'
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
		console.log req.body.user
		myapi.autocomplete text, (err, data) ->
			data = JSON.parse data
			res.json (d.title for d in data)
		# async.parallel
		# 	autocomplete: (cb)	-> myapi.autocomplete 	text, cb
		# 	data		: (cb)	-> myapi.data 			text, cb,
		# 	(err, results)		-> console.log results


WC.route '/search'
	.post (req, res) ->
		console.log req.body.user
		text = req.body.val
		lmdb.search title: text, (err, contents) ->
			if err
				console.log err
			res.json contents.toString()

		# async.waterfall [
		# 	(cb)			 -> lmdb.search text,
		# 	(err, results)	 -> console.log results
		# ]