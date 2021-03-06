function getUrlVars()
{
    var vars = [], hash;
    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
    for(var i = 0; i < hashes.length; i++)
    {
        hash = hashes[i].split('=');
        vars.push(hash[0]);
        vars[hash[0]] = hash[1];
    }
    return vars;
}

(function($){

	var socket = io.connect('http://localhost:1337');
	// On sauvegarde le bloc HTML 'msgtpl' et on supprime le spécimen qu'on réinjectera avec des valeurs dans l'event 'newusr'
	var msgtpl = $('#msgtpl').html();
	var lastmsg = false;
	var delivery;
	var key = " Passphrase";
	var me;
	var userName ="";
	var localRoom;
	var getParams = getUrlVars();
	socket.emit('getRoom',{token : getParams["token"], recev:getParams["recev"] });

	$('#msgtpl').remove();

	$('#history').submit(function(event){
		event.preventDefault();
		socket.emit('history');
		$('.message').remove();
	});

	$('#loginform').submit(function(event){
		
		event.preventDefault();
		socket.emit('login',{
			username: userName,
			mail	: $('#mail').val(),
			room	: localRoom,
			token	: getParams["token"],
			recev: getParams["recev"]
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

	socket.on('resultRoom',function(room){
		console.info("Room: "+room);
		localRoom = room;
	})
	socket.on('getUserName',function(username){
		console.info("Name: "+username);
		userName = username;
		
	})
	socket.on('debug',function(debug){
		console.info("Got room: ");
	})

	$('#form').submit(function(event){
		event.preventDefault();
				if( $('#message').val()==""){
			return;
		}
		var file;
		var jsonData =  { 
			"headers": { 'Access-Control-Allow-Origin': '*' },
			"date" : "", 
			"content" :  CryptoJS.AES.encrypt($('#message').val(), localRoom).toString(), 
			"sender" : {"iduser" : "3" }, 
			"receiver" :{"iduser" : getParams["recev"]}  
		};		
		
	     	// $("#upload").click(function(evt){ CryptoJS.AES.encrypt($('#message').val(), localRoom).toString()
	      		file = $("#upload")[0].files[0];
	      		if(typeof file === 'undefined'){
	     
	      			socket.emit('newmsg', {message:CryptoJS.AES.encrypt($('#message').val(), localRoom).toString(),room : localRoom, data : jsonData, sender: userName});
					$('#message').val('');
	      		}
	      		else{
				//evt.preventDefault;
				
					socket.emit('newmsg', {message: CryptoJS.AES.encrypt($('#message').val(), localRoom).toString(), upload:'<a href="http://localhost/medicalnetwork/src/main/resources/transferts/'+file.name+'">'+file.name+'</a>',separateur: '-- Piece Jointe --',room : localRoom, data : jsonData, sender: userName});
	      		}
			
		$('#form').wrap('<form>').closest('form').get(0).reset();
		$('#message').focus();
		});



// Upload sur le bouton type=file
	$("input[type=file]").on('change',function(){
	    	delivery.on('delivery.connect',function(delivery){
			console.info("Delivery client connect");
	     	// $("#upload").click(function(evt){
	      		file = $("#upload")[0].files[0];

					var extraParams = {foo: 'bar'}
					delivery.send(file,extraParams);
					console.log("Delivery send done");
					//evt.preventDefault;
	      		})
			//});
			delivery.on('send.success',function(fileUID){
			console.log("Le fichier à bien été envoyé au serveur");
			});
		});
	

	// ****
	//  Reception message message
	// ****
	socket.on('newmsg', function(message){
		//console.info("Message received from room : " + message.room);
		// if permettant d'intercaler un séparateurs entre les blocks de messages n'appartenants pas au même user : gros gain de lisibilité
		if(lastmsg != message.user.username){
			$('#messages').append('<div class="sep"></div>');
			lastmsg = message.user.username;
		}
		// Injection d'un message
		//var  tmp = CryptoJS.AES.encrypt(, "Secret Passphrase").toString();
		//console.log("Encry: "+tmp.toString());





		
		message.message = CryptoJS.AES.decrypt(message.message, localRoom).toString(CryptoJS.enc.Utf8);//;
		//message.message = message.message, localRoom;//;
		// outputs hello world
		//console.log(decrypt(hw).toString('utf8')); 

		$('#messages').append('<div class="message">' + Mustache.render(msgtpl, message) + '</div>');
		$('#messages').animate({scrollTop : $('#messages').prop('scrollHeight') }, 10); //Permet d'auto scroll a la reception d'un message, BEAUCOUP plus agréable
	});

	// ****
	//Gestion des connctés
	// ****
	socket.on('newusr',function(user){
		var userTmp = userName;
		if(user.room == localRoom){
			$('#users').append('<img src="' + user.avatar + '" id="' + user.id + '">');
			if( userTmp == user.username)
				me = user;
		}
	});

	socket.on('disusr',function(user){
		$('#'+user.id).remove();
	});


})(jQuery);


