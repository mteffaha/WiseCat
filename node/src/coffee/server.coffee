express 			= require 'express'
WC 					= express()
bodyParser 			= require 'body-parser'
async 				= require 'async'
myapi 	 			= require './modules/myapifilms'
jena 	 			= require './modules/jena'

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

# Autocompletion
WC.route '/autocomplete'
	.post (req, res) ->
		console.log 'autocomplete'
		text = req.body.text
		myapi.autocomplete text, (err, data) ->
			data = JSON.parse data
			res.json (d.title for d in data)

# Search
WC.route '/search'
	.post (req, res) ->
		console.log 'search'
		title = req.body.title
		# lmdb.search title: text, (err, contents) ->
		jena.search title, (err, contents) ->
			if err
				console.log err
			res.json contents.toString()

# Recommendation
WC.route '/recommendation'
	.post (req, res) ->
		jena.recommend (err, contents) ->
			if err
				console.log err
			res.json contents.toString()

# Recommendation
WC.route '/lmdb'
	.post (req, res) ->
		jena.lmdb (err, contents) ->
			if err
				console.log err



		# async.parallel
		# 	autocomplete: (cb)	-> myapi.autocomplete 	text, cb
		# 	data		: (cb)	-> myapi.data 			text, cb,
		# 	(err, results)		-> console.log results

		# async.waterfall [
		# 	(cb)			 -> lmdb.search text,
		# 	(err, results)	 -> console.log results
		# ]
