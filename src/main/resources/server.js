var http = require('http');
var md5 = require('MD5');
var dl = require('delivery');
var fs = require('fs-path');


httpServer = http.createServer(function(req, res){
	console.log("Un utilisateur s'est connecté");
});

httpServer.listen(1337);
var io = require('socket.io').listen(httpServer);
var users = {}
var messages = []
var history = 2; // nombre de message historique à charger

io.sockets.on('connection', function(socket){
	
	var me=false;

	for(var k in users){
		socket.emit('newusr',users[k]);
	}

	for(var k in messages){
		socket.emit('newmsg',messages[k]);
	}

	// ****
	//  Reception  message
	// ****
	socket.on('newmsg',function(message){
		message.user = me;
		date = new Date();
		message.h = date.getHours();
		message.m = date.getMinutes();
		//zmessage.upload = "test";
		messages.push(message); // A faire fonctionner avec bdd
		if(messages.length > history){
			messages.shift() // supprime l'entrée la plus veille
		}
		io.sockets.emit('newmsg',message);
	});


	// ****
	//  User se connecte
	// ****
	socket.on('login', function(user){
		me = user;
		me.id = user.mail.replace('@','-').replace('.','_');
		//me.avatar = 'http//gravatar.com/avatar/'+md5(user.mail)+'?s=50';
		me.avatar = 'http://forum.canardpc.com/customavatars/thumbs/avatar16737_1.gif';
		me.room = user.username

		// création de room, ici pour 2 utilisateurs : il va falloir trouver comment créer le chan.
		socket.join(me.room);
		console.log('room '+me.room );

		socket.emit('logged');
		users[me.id] = me;
		io.sockets.emit('newusr',me);
	});

	// ****
	//  User se deconnecte
	// ****
	socket.on('disconnect', function(){
		if(!me){
			return false;
		}
		delete users[me.id];
		io.sockets.emit('disusr',me);
	});


	// ****
	//  Transfert de fichiers
	// ****

  delivery = dl.listen(socket);
  delivery.on('receive.success',function(file){

    fs.writeFile("transferts/"+file.name,file.buffer, function(err){
      if(err){
        console.log('File could not be saved.');
      }else{
        console.log('File saved.');

      };
    });
  });

});