express 			= require 'express'
WC 					= express()
bodyParser 			= require 'body-parser'
async 				= require 'async'
lmdb	 			= require './modules/queries'
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
		text = req.body.val
		myapi.autocomplete text, (err, data) ->
			data = JSON.parse data
			res.json (d.title for d in data)
		# async.parallel
		# 	autocomplete: (cb)	-> myapi.autocomplete 	text, cb
		# 	data		: (cb)	-> myapi.data 			text, cb,
		# 	(err, results)		-> console.log results


# Search
WC.route '/search'
	.post (req, res) ->
		text = req.body.val
		lmdb.search title: text, (err, contents) ->
			if err
				console.log err
			res.json contents.toString()

		# async.waterfall [
		# 	(cb)			 -> lmdb.search text,
		# 	(err, results)	 -> console.log results
		# ]

# Recommendation
WC.route '/recommend'
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
