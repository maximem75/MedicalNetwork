var http = require('http');
var md5 = require('MD5');

httpServer = http.createServer(function(req, res){
	console.log("Un utilisateur s'est connecté");
});

httpServer.listen(1337);
var io = require('socket.io').listen(httpServer);
var users = {}
io.sockets.on('connection', function(socket){
	
	var me=false;
	console.log('Nouvel utilisateur');

	for(var k in users){
		socket.emit('newusr',users[k]);
	}



	// User se connecte
	socket.on('login', function(user){
		me = user;
		me.id = user.mail.replace('@','-').replace('.','_');
		//me.avatar = 'http//gravatar.com/avatar/'+md5(user.mail)+'?s=50';
		me.avatar = 'http://forum.canardpc.com/customavatars/thumbs/avatar16737_1.gif';
		socket.emit('logged');
		users[me.id] = me;
		io.sockets.emit('newusr',me);
	})

	//User se deconnecte
	socket.on('disconnect', function(){
		if(!me){
			return false;
		}
		delete users[me.id];
		io.sockets.emit('disusr',me);
	})

});