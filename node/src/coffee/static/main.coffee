$ ->

	# Search KEYUP handler
	# Send AJAX POST request to /search to server
	$('#search input').on 'keyup', (event) ->
		text = $(this).val()
		# Search
		if event.keyCode is 13
			$.ajax 	
				url: '/search'
				type: 'POST'
				data: 
					val: text
				success: (data) ->
					$('#title').val data
				dataType: 'json'
		# Autocomplete
		else if event.key.length is 1
			$.ajax 	
				url: '/autocomplete'
				type: 'POST'
				data: 
					val: text
				success: (data) ->
					$('#autocomplete').val data
				dataType: 'json'
			




# TODO
# API Node pour client
# API node pour JENA
# Connection FB G+
# REST IMDB affiche bande annonce
# Autocompletion