var http = require('http');
var dl = require('delivery');
var fs = require('fs-path');
var rest = require('restler');

httpServer = http.createServer(function(req, res){
	console.log("Un utilisateur s'est connecté");
});

httpServer.listen(1337);
var io = require('socket.io').listen(httpServer);
var users = {}
var messages = []
var history = 2; // nombre de message historique à charger

//*****
//-------------- REST CALL ------------------
//*****
rest.get('http://demo3723079.mockable.io/medicalNetworkAddMessage').on('complete', function(result) {
  if (result instanceof Error) {
    console.log('Error:', result.message);
    this.retry(5000); // try again after 5 sec 
  } else {
    console.log(result);
  }
});

rest.post(' http://demo3723079.mockable.io/MedicalNetworkPost', {
  data: { nom: "nom test" },
}).on('complete', function(data, response) {
    console.log("Post succed ? "+response.raw);
  
}).on('fail',function(data,response){
	console.log("faiiiiiiiiil");
});
//------------------------------------------

io.sockets.on('connection', function(socket){
	
	var me=false;

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
		messages.push(message); // A faire fonctionner avec bdd
		if(messages.length > history){
			messages.shift() // supprime l'entrée la plus veille
		}
		console.log(message.room);
		io.to(message.room).emit('newmsg',message);
	});


	// ****
	//  User se connecte
	// ****
	socket.on('login', function(user){
		me = user;
		me.id = user.mail.replace('@','-').replace('.','_');
		me.avatar = 'http://forum.canardpc.com/customavatars/thumbs/avatar16737_1.gif';
		me.room = user.room

		for(var k in users){
			if(users[k].room == me.room)
				socket.emit('newusr',users[k]);
		}
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
        console.log("File saved."+"transferts/"+file.name);
      };
    });
  });

});