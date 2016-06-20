$(document).ready(function(){
    manageSession();
});

var userToken = "null";
var tokenName = "token";
var category = "null";

function manageSession(){

    if(readCookie("token") != null){
        userToken = readCookie("token");
        userConnected(userToken);
    } else {
        userDisconnected();
    }
}

function readCookie(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for(var i=0;i < ca.length;i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1,c.length);
        if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
    }
    return null;
}

function userConnected(){
    $('#menu_main').append('<li><a href="http://localhost:8080/profil" data-title="Profil">Profil</a></li>');
    $('#menu_main').append('<li><a href="http://localhost:8080/categorie" data-title="Catégories">Catégories</a></li>');
    $('#menu_main').append('<li><a href="http://localhost:8080/contact" data-title="Contact">Contact</a></li>');
    $('#menu_main').append('<li><a onclick="logoutSession();" data-title="Déconnexion">Déconnexion</a></li>');
}

function userDisconnected(){
    $('#menu_main').append('<li><a href="http://localhost:8080/connexion" data-title="Connexion">Connexion</a></li>');
    $('#menu_main').append('<li><a href="http://localhost:8080/inscription" data-title="Inscription">Inscription</a></li>');
}

function logoutSession(){
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/user/logout?token='+ userToken,

        success:function(){
            eraseCookie();
            window.location.href = "http://localhost:8080/accueil";
        }
    });
}

function eraseCookie() {
    createCookie(tokenName,"",-1);
    userToken = "null";
}

function createCookie(name,value,days) {
    if (days) {
        var date = new Date();
        date.setTime(date.getTime()+(days*24*60*60*1000));
        var expires = "; expires="+date.toGMTString();
    }
    else var expires = "";
    document.cookie = name+"="+value+expires+"; path=/";
}

function getCategoryById(id){
    console.log(userToken);
    var resData = "token="+userToken+"&idcategory="+id;
    $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/category',
            data: resData,
            beforeSend: function (xhr) {
                if (xhr && xhr.overrideMimeType) {
                    xhr.overrideMimeType('application/json;charset=utf-8');
                }
            },
            datatype: "jsonp",

            success:function(res){
                var i = 0;
                $.each(res, function(index, category) {
                   if(index == "nameCategory"){
                    console.log(index);
                    setCategory(category);
                   }
                   i++;
                });
            },

            error:function(){
                console.log("error");
            }

        });
}

function getCategory(){
    return category;
}

function setCategory(val){
    category = val;
}