$(document).ready(function(){
    checkMyContacts();
    getCurrentId();
    manageSession();
    manageFooter();
    eventFooter();
    displayListCateg();
});

var userToken = "null";
var tokenName = "token";
var arrayContact = [];
var current_ID;
var userList;
verifyPendings();


function manageSession(){

    if(readCookie("token") != null){
        userToken = readCookie("token");
        userConnected(userToken);
        displayCategs();
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
    $('#menu_main').append('<li><a href="http://localhost:8080/contact" id="li_contact" data-title="Contact">Contact</a></li>');
    $('#menu_main').append('<li><a onclick="logoutSession();" data-title="Déconnexion">Déconnexion</a></li>');
}

function userDisconnected(){
    if(window.location.href != "http://localhost:8080/connexion" && window.location.href != "http://localhost:8080/inscription"){
        window.location.href = "http://localhost:8080/connexion";
    }
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


function displayCategs(id){
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/category/all",
        beforeSend: function (xhr) {
            if (xhr && xhr.overrideMimeType) {
                xhr.overrideMimeType('application/json;charset=utf-8');
            }
        },
        datatype: "jsonp",

        success:function(res){
            $.each(res, function(index, category) {
                $("#"+id).append("<option class='option-category' value='"+category.idcategory+"'>"+category.nameCategory+"</option>");
            });
        },
        error: function(){
            alert("error");
        },
    });
}

function getUserList(){
    return userList;
}

function setUserList(val){
    userList = val;
}


function verifyPendings(){
    var pendings;
    if(readCookie("token") != undefined){
        $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/contact/pending',
        data: "token="+readCookie("token"),
        contentType: "application/json; charset=utf-8",

        complete:function(result){
           if(result.responseJSON.length > 0){     
                $("#new_contact").remove();
                $("#li_contact").append("<span id='new_contact' class='glyphicon glyphicon-bell' aria-hidden='true'></span>");
            } else {
                $("#new_contact").remove();
            }   
        },
        error:function(){
            console.log("error");
        }
     });
    }
}

function manageFooter(){
   var height = $(window).height();
   var res = height - $("#menu_main").height();
   $(".footer").css({
    "top" : res +"px"
   });
   $("#middle").css({
    "height" : res - $(".footer").height() +"px"
   });
   console.log("resize");
}

function eventFooter(){
    $(window).on('resize',manageFooter);
}



var pendings;

function displayListCateg(){
    console.log("display categs");
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/category/all",
        beforeSend: function (xhr) {
            if (xhr && xhr.overrideMimeType) {
                xhr.overrideMimeType('application/json;charset=utf-8');
            }
        },
        datatype: "jsonp",

        success:function(res){
            var i = 0;
            $.each(res, function(index, category) {
                if(i === 6){
                    addBox(category.nameCategory,true);
                } else {
                    addBox(category.nameCategory,false);
                }
                i++;
            });
        },
        error: function(){
            alert("error");
        }
    });
}

function searchCateg(elem){
    var id = $(elem).attr("id");
    var resData = "token="+readCookie("token")+"&research="+id;

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/category/research',
        data: resData,
        beforeSend: function (xhr) {
            if (xhr && xhr.overrideMimeType) {
                xhr.overrideMimeType('application/json;charset=utf-8');
            }
        },
        datatype: "jsonp",

        error:function(){
            console.log("error");
        },

        complete:function(res){
            var datas = "token="+readCookie("token")+"&idcategory="+res.responseJSON[0].idcategory;
            getUserListByCateg(datas);
        }
    });
}

function addBox(name, newLine){
    var table = $("#middle").find(".div-table");

    var box = "<div class='div-cell'><div class='div-box' id='"+name+"' onClick='searchCateg(this);'><span class='name-categ'>"+name+"</span></div></div>";
    var row = "<div class='div-row'></div>";

    if(newLine === true){
        $(table).append(row);
        var elem = document.getElementById(name);
        $(".div-row").last().append(box);

    } else {
        $(".div-row").last().append(box);
    }
}


function getUserListByCateg(datas){
    var name, firstname, iduser, resultat;
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/user/list',
        data: datas,
        beforeSend: function (xhr) {
            if (xhr && xhr.overrideMimeType) {
                xhr.overrideMimeType('application/json;charset=utf-8');
            }
        },
        datatype: "jsonp",

        complete:function(result){
            setUserList(result);
            $("#middle").empty();
            $("#middle").append('<div class="panel-body" id="panelB"><ul class="list-group" id="user_li"></ul></div>');

           checkMyContacts();
           $.each(result.responseJSON, function(index, value){
            console.log(value);
                $.each(value, function(id, val){
                    switch(id){
                        case 1:
                        name = val;
                        break;
                        case 2:
                        firstname = val;
                        break;

                        case 0:
                        iduser = val;
                        break;
                    }
                });
                resultat = true;
                $.each(arrayContact, function(index, value){
      
                    if(iduser == value){
                        resultat = false;                        
                    } else if(iduser == current_ID){
                        resultat = "same";
                    }
                    
                });

                $("#user_li").append(displayUserList(name + " " + firstname, iduser, resultat));
     
            });
        },
        error:function(){
            console.log("error");
        }
    });
}

function displayUserList(full_name, id, res){
    if(res == true){
        var list_dom =''+
        '<li class="list-group-item">' +
            '<div class="userName">' +
                '<label for="userName">'+full_name+'</label>' +
            '</div>' +
            '<div class="action-buttons button_user">' +
                '<a id="btnSend_'+id+'" onclick="sendRequestContact('+id+', this, btnSend_'+id+')">' +
                    '<span class="glyphicon glyphicon-user"></span>' +
                '</a>' +
                '<a onclick="contactUser('+id+')">' +
                    '<span class="glyphicon glyphicon-envelope"></span>' +
                '</a>' +
            '</div>' +
        '</li>';
    } else if(res == false){
        var list_dom =''+
        '<li class="list-group-item">' +
            '<div class="userName">' +
                '<label for="userName">'+full_name+'</label>' +
            '</div>' +
            '<div class="action-buttons button_user">' +
                '<a onclick="contactUser('+id+')">' +
                    '<span class="glyphicon glyphicon-envelope"></span>' +
                '</a>' +
            '</div>' +
        '</li>';
    } else if(res == "same"){
        var list_dom =''+
        '<li class="list-group-item">' +
            '<div class="userName">' +
                '<label for="userName">'+full_name+'</label>' +
            '</div>' +
        '</li>';
    }
    


    return list_dom;
}

function sendRequestContact(id, elem, idBtn){
    var $this = $(elem);
    var u_id = id;
    $("#id_wizard_message").remove();
    $this.parents('li').append("<div id='id_wizard_message' class='wizard_message'><textarea id='id_text_message' placeholder='Veuillez saisir un message.'></textarea><button id='btn_id_request' class='btn btn-sm btn-primary btn-block'>Envoyer la demande</button><button id='btn_id_cancel' class='btn btn-sm btn-primary btn-block'>Annuler</button></div>");
    var message = "";
    $("#btn_id_request").css({
        "margin-left" : "4%",
        "width" : "40%",
        "display" : "inline-block"
    });
    $("#btn_id_cancel").css({
        "margin-left" : "5%",
        "margin-top" : "0px",

        "width" : "40%",
        "display" : "inline-block"
    });
    $("#btn_id_request").on('click',function(){
        var myJSON = '{"idcontact" : '+u_id+', "message" : "'+$("#id_text_message").val()+'" }';
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/contact/add?token='+readCookie("token"),
            data: myJSON,
            contentType: "application/json; charset=utf-8",

            success:function(result){
               console.log("success");
               $('#id_wizard_message').remove();        
               idBtn.remove();
            },
            error:function(){
                console.log("error");
            }
        });
    });

    $("#btn_id_cancel").on('click', function(){
        $("#id_wizard_message").remove();
    });
    
}

function checkMyContacts(){
    if(readCookie("token") != undefined){
        $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/user/contact',
        data: "token="+readCookie("token"),
        contentType: "application/json; charset=utf-8",

        complete:function(result){
            arrayContact= [];
            $.each(result.responseJSON, function(id, val){
                $.each(val, function(index, value){
                    if(index == 0){
                        arrayContact.push(value);
                    }
                });
            });
        },
        error:function(){
            console.log("error");
        }
     });
    }
}

function getCurrentId(){
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/user/data",
        data:"token="+readCookie("token"),

        success:function(res){
            
            $.each(res, function(index, user) {
               
               if(index == 9){
                current_ID = user;
               }
            });
        },

        error: function(){
            console.log("Erreur");
        }

    }); 
}