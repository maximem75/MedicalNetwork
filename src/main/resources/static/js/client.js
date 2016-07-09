(function($){

	console.info( "bdeac".split('').sort().join(''))
	var socket = io.connect('http://localhost:1337');
	// On sauvegarde le bloc HTML 'msgtpl' et on supprime le spécimen qu'on réinjectera avec des valeurs dans l'event 'newusr'
	var msgtpl = $('#msgtpl').html();
	var lastmsg = false;
	var delivery;
	var key = " Passphrase";
	var me;
	var localRoom;

	$('#msgtpl').remove();
	$('#loginform').submit(function(event){
		event.preventDefault();
		socket.emit('login',{
			username: $('#username').val(),
			mail	: $('#mail').val(),
			room	: $('#room').val()
		})

		localRoom = $('#room').val();
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

	     	// $("#upload").click(function(evt){
	      		file = $("#upload")[0].files[0];
	      		if(typeof file === 'undefined'){
	      			//socket.emit('newmsg', {message: $('#message').val()});
	      			//file = $("#upload")[0].files[0];
	      			console.info("sending 1: "+CryptoJS.AES.encrypt($('#message').val(), key).toString());

	      		/*	var jsonData =  { 
					  	date : "2012-03-02", 
					  	content : "blabla", 
					  	sender : {"iduser" : "1"}, 
					  	receiver :{"iduser" : "2"}  
				};*/
				/*rest.postJson('http://localhost:8080/message/addMessage?token=6f163583-b904-4d20-943c-ed24c2872e00', { 
					  	date : new date(), 
					  	content : CryptoJS.AES.encrypt($('#message').val(), key).toString(), 
					  	sender : {"iduser" : "1"}, 
					  	receiver :{"iduser" : "2"}  
				}).on('complete', function(data, response) {
					    console.log("Post succed ? "+response.raw);
					}).on('fail',function(data,response){
						console.log("faiiiiiiiiil");
					});*/
					/*var jsonData=  { 
					  	date : Date(), 
					  	content : CryptoJS.AES.encrypt($('#message').val(), key).toString(), 
					  	sender : {"iduser" : "1"}, 
					  	receiver :{"iduser" : "2"}  
					};*/

					//var jsonData = JSON.parse( JSONObject );    
					var jsonData =  { 
						"headers": { 'Access-Control-Allow-Origin': '*' },
					  	"date" : "", 
					  	"content" : CryptoJS.AES.encrypt($('#message').val(), key).toString(), 
					  	"sender" : {"iduser" : "1"}, 
					  	"receiver" :{"iduser" : "2"}  
				};
					/*var request = $.ajax({
					  url: "http://localhost:8080/message/addMessage?token=6f163583-b904-4d20-943c-ed24c2872e00",
					  type: "POST",
					  data: jsonData,
					  dataType: "jsonp",
					  contentType: "application/json"
					}); */   

	      			socket.emit('newmsg', {message: CryptoJS.AES.encrypt($('#message').val(), key).toString(),room : localRoom, data : jsonData});
					$('#message').val('');
	      		}
	      		else{
				//evt.preventDefault;
					console.log("sending 2: "+CryptoJS.AES.encrypt($('#message').val(), key).toString());
					socket.emit('newmsg', {message: CryptoJS.AES.encrypt($('#message').val(), key).toString(), upload:'<a href="http://localhost/medicalnetwork/src/main/resources/transferts/'+file.name+'">'+file.name+'</a>',separateur: '-- Piece Jointe --',room : localRoom});
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
		console.info("Message received from room : " + message.room);
		// if permettant d'intercaler un séparateurs entre les blocks de messages n'appartenants pas au même user : gros gain de lisibilité
		if(lastmsg != message.user.id){
			$('#messages').append('<div class="sep"></div>');
			lastmsg = message.user.id;
		}
		// Injection d'un message
		//var  tmp = CryptoJS.AES.encrypt(, "Secret Passphrase").toString();
		//console.log("Encry: "+tmp.toString());
		console.log(message.message);
		message.message = CryptoJS.AES.encrypt(message.message, key).toString()
		console.log("Decry  "+message.message+"   :     "+ CryptoJS.AES.decrypt(message.message, key).toString(CryptoJS.enc.Utf8));
		message.message = CryptoJS.AES.decrypt(message.message, key).toString(CryptoJS.enc.Utf8);
		// outputs hello world
		//console.log(decrypt(hw).toString('utf8'));

		$('#messages').append('<div class="message">' + Mustache.render(msgtpl, message) + '</div>');
		$('#messages').animate({scrollTop : $('#messages').prop('scrollHeight') }, 500); //Permet d'auto scroll a la reception d'un message, BEAUCOUP plus agréable
	});

	// ****
	//Gestion des connctés
	// ****
	socket.on('newusr',function(user){
		if(user.room == localRoom){
			$('#users').append('<img src="' + user.avatar + '" id="' + user.id + '">');
			me = user;
		}
	});

	socket.on('disusr',function(user){
		$('#'+user.id).remove();
	});


})(jQuery);


