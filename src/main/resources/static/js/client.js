(function($){

	var socket = io.connect('http://localhost:1337');
	// On sauvegarde le bloc HTML 'msgtpl' et on supprime le spécimen qu'on réinjectera avec des valeurs dans l'event 'newusr'
	var msgtpl = $('#msgtpl').html();
	var lastmsg = false;

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

	$('#form').submit(function(event){
		event.preventDefault();
		socket.emit('newmsg', {message: $('#message').val()});
		$('#message').val('');
		$('#message').focus();
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

})(jQuery);
