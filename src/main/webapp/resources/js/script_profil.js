$(document).ready(function(){
    buildWindow();
    token = readCookie("token");
    var resData = "token="+token;

    var name = $("#name");
    identifiant = $("#identifiant");
    date = $("#date");
    email = $("#email");
    phone = $("#phone");
    categ = $("#category");

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/user/data",
        data:resData,
        beforeSend: function (xhr) {
            if (xhr && xhr.overrideMimeType) {
                xhr.overrideMimeType('application/json;charset=utf-8');
            }
        },
        datatype: "jsonp",

        success:function(res){
            var i = 0;
            var birthday;
            $.each(res, function(index, user) {
                console.log(index);
               switch(index){
                case "name" :
                    nom = user;
                    break;
                case "firstname" :
                    prenom = user;
                    break;
                case "login":
                    identifiant.text(user);
                    break;

                case "birthday" :
                    user = new Date(user);
                    birthday = user.toString();
                    birthday = birthday.substring(0,16);
                    date.text(birthday);
                    break;

               case "phone" :
                    phone.text(user);
                    break;

               case "email" :
                   email.text(user);
                   break;

               case "category" :
                    setCategory(user.nameCategory);
                    categ.text(getCategory());
                   break;
               }
            });

            name.text(nom + " " + prenom);

            contentUpdate = '<form id="update_form" method="POST" action="http://localhost:8080/user/update"><div class="div-update"><div class="row"><div class="col-md-offset-2 col-md-8"><h1> Modification</h1></div></div><div class="row"><div class="col-md-offset-2 col-md-3"><div class="form-group"><label for="Nom">Nom</label>'
                                    +'<input type="text" class="form-control" id="nom" value="'+nom+'" name="name"></div></div><div class="col-md-offset-1 col-md-3"><div class="form-group"><label for="Prenom">Pr&eacute;nom</label><input type="text" class="form-control" id="prenom" value="'+prenom+'" name="firstname"></div></div></div><div class="row"><div class="col-md-offset-2 col-md-3">'
                                    +'<div class="form-group"><label for="Email">Email</label><input type="text" class="form-control" id="email" value="'+email.text()+'" name="email"></div></div><div class="col-md-offset-1 col-md-3"><label for="category">Cat&eacute;gorie</label><input type="text" class="form-control" id="categorye" value="'+categ.text()+'" name="category"><div class="form-group">'
                                    +'</div></div></div><div class="row"><div class="col-md-offset-2 col-md-3"><div class="form-group"><label for="Password">Mot de passe</label><input type="password" class="form-control" id="password" placeholder="Mot de passe" name="password"></div></div><div class="col-md-offset-1 col-md-3"><div class="form-group"><label for="Vpassword">V&eacute;rification mot de passe</label>'
                                    +'<input type="password" class="form-control" id="vpassword" placeholder="V&eacute;rification mot de passe"></div></div></div><div class="row"><div class="col-md-offset-2 col-md-3"><div class="input-group"><span class="input-group-addon glyphicon glyphicon-earphone"></span><input type="text" class="form-control" value="'+phone.text()+'" aria-describedby="basic-addon1" name="phone">'
                                    +'</div><div class="input-group"><span class="input-group-addon glyphicon glyphicon-calendar"></span><input type="date" class="form-control" aria-describedby="basic-addon1" name="date"></div></div></div><br/><div class="row"><div class="col-md-offset-2 col-md-1"><button type="submit" class="btn  btn-primary " id="btnid">Envoyer mes informations</button>'
                                    +'</div></div></div></form>';
        },

        error: function(){
            console.log("Erreur : token is missing");
        }

    });
});

var token
var update = false;
var identifiant, date, email, phone, nom, prenom, category;

function removeUser(){
    var r = confirm("Voulez vous supprimer votre compte ?");
    if (r == true) {
         $.ajax({
            type: "POST",
            url: "http://localhost:8080/user/delete",
            data:"token="+token,

            success:function(){
                logoutSession();
                eraseCookie();
                window.location.href = "http://localhost:8080/accueil";
            }
        });
    }
}

function updateUser(){

    var r = confirm("Voulez vous modifier votre compte ?");
    if (r == true) {
        update = true;
        buildWindow();
        /* $.ajax({
            type: "POST",
            url: "http://localhost:8080/user/delete",
            data:"token="+token,

            success:function(){
                logoutSession();
                eraseCookie();
                window.location.href = "http://localhost:8080/accueil";
            }
        });*/
    }
}

function buildWindow(){
    var child = document.getElementById("middle").firstChild;
    $("#middle").empty();

    if(update === false){
        $("#middle").append(content);
    } else {
        $("#middle").append(contentUpdate);
    }
}


var content = ' <div class="row"><div class="panel"><div class="panel-heading"><h3> <div id="name"/> </h3></div><div class="panel-body">'
                 +'<div class=" col-md-6 col-lg-6 "><table class="table table-user-information"><tbody><tr><td>Identifiant</td><td><span id="identifiant" /></td></tr>'
                 +'<tr><td>Date de naissance</td><td><span id="date" /></td></tr><tr><td>Email</td><td><span id="email" /></td></tr>'
                 +'<tr><td>Catégorie</td><td><span id="category" /></td></tr>'
                 +'<tr><td>T&eacute;l&eacute;phone</td><td><span id="phone" /></td></tr></tbody></table>'
                 +'<a data-original-title="Edit this user" data-toggle="tooltip" type="button" class="btn btn-sm btn-warning btn" id="edit" onClick="updateUser();"><i class="glyphicon glyphicon-edit"></i></a>&nbsp;&nbsp;<a data-original-title="Remove this user" data-toggle="tooltip" type="button" class="btn btn-sm btn-danger btn" id="remove" onClick="removeUser();"><i class="glyphicon glyphicon-remove"></i></a>'
                 +'</div></div></div></div></div></div>';

var contentUpdate;