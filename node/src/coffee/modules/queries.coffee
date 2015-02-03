http 		= require 'http'
bl 			= require 'bl'


#############################################################################
# 								EXPORTS										#
#############################################################################

module.exports = 
	search 		: (vals, cb) -> _query _search(vals),
										cb
	# description : (film, cb)  -> _query _description film, k for k, v of _keys
	# 									cb


#############################################################################
# 								STUFF										#
#############################################################################

###
# Keys
# 
# Cette object stocke le(s) prédicat(s) des concepts que nous manipulons
# Utile à la génération de requêtes
###
_keys = 
	title			: 'dc:title'
	# Ici par exemple, il y a 2 prédicats pour obtenir le nom d'un acteur à partir d'un film :
	# ?film lmdb:actor [ lmdb:actor_name ?name ]
	actor 			: [
						'lmdb:actor'
						'lmdb:actor_name'
					]
	director 		: [
						'lmdb:director'
						'lmdb:director_name'
					]
	editor			: [
						'lmdb:editor'
						'lmdb:editor_name'
					]
	writer			: [
						'lmdb:writer'
						'lmdb:writer_name'
					]
	composer 		: [
						'lmdb:music_contributor'
						'lmdb:music_contributor_name'
					]
	song 			: [
						'lmdb:film_featured_song'
						'lmdb:film_featured_song_name'
					]
	subject 		: [
						'skos:subject'
						'lmdb:film_subject_name'
					]
	genre			: [
						'lmdb:genre'
						'lmdb:film_genre_name'
					]
	runtime 		: 'lmdb:runtime'
	date 			: 'dc:date'

###
# Prefixes
# 
# Un ensemble de préfixes utiles
###
_prefixes = """
	PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
	PREFIX dc: <http://purl.org/dc/terms/>
	PREFIX owl: <http://www.w3.org/2002/07/owl#>
	PREFIX lmdb: <http://data.linkedmdb.org/resource/movie/>
	PREFIX foaf: <http://xmlns.com/foaf/0.1/>
	PREFIX skos: <http://www.w3.org/2004/02/skos/core#>
"""

###
# Query
# 
# La fonction permettant d'executer une requête (HTML POST) SPARQL sur le endpoint de LinkedMDB
###
_query = (query, cb) ->
	# La donnée à envoyer
	data = 'query=' + _prefixes + ' ' + query

	# Les options de requêtes
	options = 
		hostname	: 'data.linkedmdb.org'
		path		: '/sparql'
		# Les requêtes générées peuvent être très longues !
		method		: 'POST'
		headers		: 
			'Content-Type'		: 'application/x-www-form-urlencoded'
			'Content-Length'	: data.length

	# L'objet request et le callback de la réponse
	req = http.request options, (res) ->
		res.pipe bl cb

	req.on 'error', (e) ->
		console.log e.message

	# Ecrire la requête et l'envoyer !
	req.write data
	req.end()

#############################################################################
# 								QUERIES										#
#############################################################################

###
# Recherche d'un film
#
# Un utilisateur peut rechercher un film en renseignant nos différents critères (acteurs, réalilsateur, ...).
# Ces informations sont centralisées dans l'objet vals
#
# Un exemple ici avec 2 acteurs et un réalisateur (pour le film : Saw)
###
# _vals = 
	# title			: 'Saw'
	# actor 			: [
	# 					'Dina Meyer'
	# 					'Cary Elwes'
	# 				]
	# director 		: [
	# 					'James Wan'
	# 				]
	# editor			: [
	# 					'e1'
	# 					'e2'
	# 				]
	# writer			: [
	# 					'w1'
	# 					'w2'
	# 				]
	# composer 		: [
	# 					'c1'
	# 					'c2'
	# 				]
	# song 			: [
	# 					's1'
	# 					's2'
	# 				]
	# subject 		: [
	# 					'sub1'
	# 					'sub2'
	# 				]
	# genre			: [
	# 					'g1'
	# 					'g2'
	# 				]
	# runtime 		: [
	# 					'<=' # Opérateur de comparaison
	# 					'90'
	# 				]
	# date 			: [
	# 					'=' # Opérateur de comparaison
	# 					'2004-10-29'
	# 				]

# Cette fonction génère la requête de recherche de film
_search = (vals) ->
	opt = ''
	for key, arr of vals
		if key is 'title'
			# Pour le titre, nous insérons dans la requête :
			# ?film lmdb:title "Saw" .
			opt += """
				?film #{_keys[key]} "#{arr}" .
			"""
		else if key in ['runtime', 'date']
			# Durée et date de sortie sont aussi des critères discriminants :
			# ?film lmdb:runtime ?runtime .
			# FILTER ( ?runtime <= 90) .
			opt += """
				?film #{_keys[key]} ?#{key} .
				FILTER(?#{key} #{arr[0]} "#{arr[1]}") .
			"""
		else
			i = 0
			for val in arr
				# Tout les autres critères sont optionnels :
				# OPTIONAL {
				# 	?film lmdb:actor ?actor0 .
				# 	?actor0 lmdb:actor_name "Dina Meyer" .
				# } .
				opt +="""
					OPTIONAL {
						?film #{_keys[key][0]} ?#{key}#{i} .
						?#{key}#{i} #{_keys[key][1]} "#{val}" .
					}.
				"""
				i += 1
	# Requête de base, dans laquelle sont ajoutés les chaînes précédentes
	# (Valeur de retour de la fonction)
	"""
		SELECT *
		WHERE {
			?film a lmdb:film .
			#{opt}
		}
	"""

# Lance la requête de recherche avec le jeu de données vals
# Retourne un ensemble d'URI de film avec éventuellement d'autres URIs bindées
# Plus un résultat a de variables bindées, plus il est pertinent

# console.log _search _vals
# query _search _vals

###
# Description d'un film
#
# Connaissant l'URI d'un film, nous pouvons lancer des requêtes nous permettant de le décrire (acteurs, réalisateur, ...) à nos utilisateurs
###

# URI du film trouvé précédemment
_film = '<http://data.linkedmdb.org/resource/film/1771>' # Saw toujours :]

# Fonction générant une requête décrivant un film par un critère (acteurs, date, ...)
_description = (film, key) ->
	if key in ['title', 'runtime', 'date']
		# Il n'y a ici qu'un prédicat entre le film et l'objet de notre recherche
		p = keys[key]
		"""
			SELECT *
			WHERE {
				#{film} #{p} ?#{key} .
			}
		"""
	else
		# Ici, il y a une 'indirection' pour retrouver les noms sous forme de chaîne de caractères
		p0 = keys[key][0]
		p1 = keys[key][1]
		"""
			SELECT *
			WHERE {
				#{film} #{p0} [ #{p1} ?#{key} ] .
			}
		"""

# Lance autant de requêtes que de critères de description

# console.log _description _film, k for k, v of _keys
# query _description _film, k for k, v of _keys