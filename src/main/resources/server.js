var http = require('http');

httpServer = http.createServer(function(req, res){
	console.log("Un utilisateur s'est connecté");
});

httpServer.listen(1337);
var io = require('socket.io').listen(httpServer);

io.sockets.on('connection', function(socket){
	console.log('Nouvel utilisateur');

	socket.on('login', function(user){
		console.log(user);
	})

});