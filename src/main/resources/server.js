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
//var token = "7e2fa51e-5f09-4249-86a0-951787b36371";
var receiver = "";

//*****
//-------------- REST CALL ------------------
//*****
/*rest.get('http://localhost:8080/message/getConversation?token='+token+'&idcontact=2').on('complete', function(result) {
  if (result instanceof Error) {
    console.log('Error:', result.message);
    this.retry(5000); // try again after 5 sec 
  } else {
    console.log(result);
  }
});*/


/*var jsonData =  { 
	  	date : "2012-03-02", 
	  	content : "blabla", 
	  	sender : {"iduser" : "1"}, 
	  	receiver :{"iduser" : "2"}  
};
rest.postJson('http://localhost:8080/message/addMessage?token=6f163583-b904-4d20-943c-ed24c2872e00', jsonData).on('complete', function(data, response) {
	    console.log("Post succed ? "+response.raw);
	}).on('fail',function(data,response){
		console.log("faiiiiiiiiil");
	});*/

	/*rest.post('http://localhost:8080/message/addMessage?token=6f163583-b904-4d20-943c-ed24c2872e00', {
	  headers: { 
	        'Accept': 'application/json',
	        'Content-Type': 'application/json' 
	  },
	  data: { 
	  	date : "12/03/2012", 
	  	content : "blabla", 
	  	sender : {"iduser" : "1"}, 
	  	receiver :{"iduser" : "2"}  

	  },
	}).on('complete', function(data, response) {
	    console.log("Post succed ? "+response.raw);
	}).on('fail',function(data,response){
		console.log("faiiiiiiiiil");
	});*/
//------------------------------------------

io.sockets.on('connection', function(socket){
	
	var me=false;


	/*for(var k in messages){
		socket.emit('newmsg',messages[k]);
	}*/
	socket.on('getRoom',function(ids){
		
		console.log('http://localhost:8080/message/getEncryptionKey?token='+ids.token+'&idcontact='+ids.recev+'');
		rest.get('http://localhost:8080/message/getEncryptionKey?token='+ids.token+'&idcontact='+ids.recev+'').on('complete', function(result) {
		  if (result instanceof Error) {
		    console.log('Error:', result.message);
		    this.retry(5000); // try again after 5 sec 
		 	} 
		  else {
		    console.log("result room:" +result);
			socket.emit('resultRoom',result);
			}
		 });
	});

	// ****
	//  Reception  message
	// ****
	socket.on('newmsg',function(message){
		message.user = me;
		date = new Date();
		message.A = date.getFullYear();
		message.M = date.getMonth()+1;
		message.j = date.getDate();
		message.h = date.getHours();
		message.m = date.getMinutes();
		message.data.date = date;
		message.data.receiver = parseInt(me.recev);

		messages.push(message); // A faire fonctionner avec bdd
		if(messages.length > history){
			messages.shift() // supprime l'entrée la plus veille
		}
		console.log("user msg data: "+'http://localhost:8080/message/addMessage?token='+me.token+'');
		rest.postJson('http://localhost:8080/message/addMessage?token='+me.token+'',message.data).on('complete', function(data, response) {
			    console.log("Message POST succed ! : "+message.data.date);
		}).on('fail',function(data,response){
				console.log("Message POST failed !");
		});

		console.log("MESSAGE FOR ROOM : "+message.room);
		io.to(message.room).emit('newmsg',message);
	});


	// ****
	//  User se connecte
	// ****
	socket.on('login', function(user){
		me = user;
		me.id = user.mail.replace('@','-').replace('.','_');
		me.avatar = 'https://s32.postimg.org/a097o0fzp/image.jpg';
		me.room = user.room;
		me.token = user.token;
		me.recev = user.recev;
console.log('http://localhost:8080/message/lastMessages?token='+me.token+'&idcontact='+me.recev+'');
		rest.get('http://localhost:8080/message/lastMessages?token='+me.token+'&idcontact='+me.recev+'').on('complete', function(result) {
		  if (result instanceof Error) {
		    console.log('Error:', result.message);
		    this.retry(5000); // try again after 5 sec 
		  } else {
		    var messageBack ={message:""};
		    for(var bddMessage in result){
		    	messageBack.user = me;
			    date = new Date(result[bddMessage][0]);
				messageBack.A = date.getFullYear();
				messageBack.M = date.getMonth()+1;
				messageBack.j = date.getDate();
				messageBack.h = date.getHours();
				messageBack.m = date.getMinutes();
			    messageBack.message = result[bddMessage][1];
			    console.log(bddMessage);
				socket.emit('newmsg',messageBack);
			}
		  }
		});


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