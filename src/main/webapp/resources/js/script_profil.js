$(document).ready(function(){
    buildWindow();
    token = readCookie("token");
    var resData = "token="+token;
    var success = false;
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
               switch(index){
                   case "iduser":
                       user_id = user;
                       break;
                case "name" :
                    nom = user;
                    user_name = user;
                    break;
                case "firstname" :
                    prenom = user;
                    user_firstname = user;
                    break;
                case "login":
                    identifiant.text(user);
                    user_login = user;
                    break;

                case "birthday" :
                    user = new Date(user);
                    user_date = user;
                    birthday = user.toString();
                    birthday = birthday.substring(0,16);
                    date.text(birthday);
                    break;

               case "phone" :
                   phone.text(user);
                   user_phone = user;
                   break;

               case "email" :
                   email.text(user);
                   user_email = user;
                   break;

                   case "category" :
                    categ.text(user.nameCategory);
                       user_category = user.nameCategory;
                       category_id = user.idcategory;
                   break;
               }
            });

            name.text(nom + " " + prenom);
            success = true;

        },

        complete: function(){
            contentUpdate = '<form id="update_form" method="POST" action="http://localhost:8080/user/update"><div class="div-update"><div class="row"><div class="col-md-offset-2 col-md-8"><h1> Modification</h1></div></div><div class="row"><div class="col-md-offset-2 col-md-3"><div class="form-group"><label for="Nom">Nom</label>'
                +'<input type="text" class="form-control" id="nom" value="'+nom+'" name="name" readonly></div></div><div class="col-md-offset-1 col-md-3"><div class="form-group"><label for="Prenom">Pr&eacute;nom</label><input readonly type="text" class="form-control" id="prenom" value="'+prenom+'" name="firstname"></div></div></div><div class="row"><div class="col-md-offset-2 col-md-3">'
                +'<div class="form-group"><label for="Email">Email</label><input type="text" class="form-control" id="email" value="'+email.text()+'" name="email"></div></div><div class="col-md-offset-1 col-md-3"><label for="category">Cat&eacute;gorie</label><select class="form-control" id="categorieID"></select><div class="form-group">'
                +'</div></div></div><div class="row"><div class="col-md-offset-2 col-md-3"><div class="form-group"><label for="Password">Mot de passe</label><input type="password" class="form-control" id="password" placeholder="Mot de passe" name="password"></div></div><div class="col-md-offset-1 col-md-3"><div class="form-group"><label for="Vpassword">V&eacute;rification mot de passe</label>'
                +'<input type="password" class="form-control" id="vpassword" placeholder="V&eacute;rification mot de passe"></div></div></div><div class="row"><div class="col-md-offset-2 col-md-3"><div class="input-group"><span class="input-group-addon glyphicon glyphicon-earphone"></span><input type="text" class="form-control" value="'+phone.text()+'" aria-describedby="basic-addon1" name="phone">'
                +'</div></div></div></br><div class="row"><div class="col-md-offset-2 col-md-1"><button type="submit" class="btn  btn-primary " id="btnid">Envoyer mes informations</button></div><div class="col-md-offset-1 col-md-1"><button type="button" class="btn  btn-primary " onclick="updateFailed();" id="btn_annul">Annuler</button></div>'
                +'</div></div></div></form>';

        },
        //<form id="update_form" method="POST" action="http://localhost:8080/user/update">
        error: function(){
            console.log("Erreur : token is missing");
        }

    });
    
    $("#update_form").on("submit", function(){
        console.log($("#categorieID").find(":selected").val());
        var myJson = '{'+
            '"iduser": "'+user_id+'",'+
            '"password": "'+$("#password").val()+'",'+
            '"name": "'+user_name+'",'+
            '"firstname": "'+user_firstname+'",'+
            '"birthday": "",'+
            '"phone": "'+$("#phone").val()+'",'+
            '"email": "'+$("#email").val()+'",'+
            '"category":{"idcategory": "'+$("#categorieID").find(":selected").val()+'"}'+
            '}';

        $.ajax({
            type: "POST",
            url: "http://localhost:8080/user/update",
            data:myJson,
            contentType: "application/json; charset=utf-8",

            success:function(res){
                window.location.href = "http://localhost:8080/profil";
            },

            error: function(){

            }
        });
    });
});

var token;
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
    }
}

function buildWindow(){
    var child = document.getElementById("middle").firstChild;
    $("#middle").empty();

    if(update === false){
        $("#middle").append(content);
    } else {
        $("#middle").append(contentUpdate);
        displayCategs("categorieID");
        //console.log($("#categorieID").find(".option-category").toArray());
       // setSelectedIndex($("#categorieID"), user_category);

    }
}

function updateFailed(){
    window.location.href = "http://localhost:8080/profil";
}

function setSelectedIndex(s, v) {
    for (var i = 0; i < s.options.length; i++) {
        console.log(s.options[i].text + " / " + v);
        if (s.options[i].text == v) {
            s.options[i].selected = true;
            return;
        }
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

var user_id, user_name, user_firstname, user_login, user_password, user_phone, user_date, user_email, user_category, category_id;