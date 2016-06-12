(function($){

	var socket = io.connect('http://localhost:1337');
	// On sauvegarde le bloc HTML 'msgtpl' et on supprime le spécimen qu'on réinjectera avec des valeurs dans l'event 'newusr'
	var msgtpl = $('#msgtpl').html();
	var lastmsg = false;
	var delivery;
	$('#msgtpl').remove();
	$('#loginform').submit(function(event){
		event.preventDefault();
		socket.emit('login',{
			username: $('#username').val(),
			mail	: $('#mail').val()
		})
	});

	socket.on('logged', function(){
		$('#login').fadeOut();
		$('#message').focus();
	});

	// ****
	//  Envoi message
	// ****
socket.on('connect', function(){
 delivery = new Delivery(socket);
});
	$('#form').submit(function(event){
		event.preventDefault();
		var file;
		console.info("CLick submit");

		
		delivery.on('delivery.connect',function(delivery){
			console.info("Delivery client connect");
	     	// $("#upload").click(function(evt){
	      		file = $("#upload")[0].files[0];
	      		if(typeof file === 'undefined'){
	      			//socket.emit('newmsg', {message: $('#message').val()});
	      			socket.emit('newmsg', {message: $('#message').val()});
					$('#message').val('');
					$('#message').focus();
	      		}
	      		else{

					var extraParams = {foo: 'bar'}
					delivery.send(file,extraParams);
					console.log("Delivery send done");
					//evt.preventDefault;
					socket.emit('newmsg', {message: $('#message').val(), upload:'<a href="http://localhost/medicalnetwork/src/main/resources/transferts/'+file.name+'">'+file.name+'</a>'});

	      		}
			//});
		});

		delivery.on('send.success',function(fileUID){
			console.log("Le fichier à bien été envoyé au serveur");
		})


		


	});

	// ****
	//  Reception message message
	// ****
	socket.on('newmsg', function(message){
		// if permettant d'intercaler un séparateurs entre les blocks de messages n'appartenants pas au même user : gros gain de lisibilité
		if(lastmsg != message.user.id){
			$('#messages').append('<div class="sep"></div>');
			lastmsg = message.user.id;
		}
		// Injection d'un message
		$('#messages').append('<div class="message">' + Mustache.render(msgtpl, message) + '</div>');
		$('#messages').animate({scrollTop : $('#messages').prop('scrollHeight') }, 500); //Permet d'auto scroll a la reception d'un message, BEAUCOUP plus agréable
	});

	// ****
	//Gestion des connctés
	// ****

	socket.on('newusr',function(user){
		$('#users').append('<img src="' + user.avatar + '" id="' + user.id + '">');
	});

	socket.on('disusr',function(user){
		$('#'+user.id).remove();
	});

	// ****
	//  Transfert de fichiers
	// ****



})(jQuery);


