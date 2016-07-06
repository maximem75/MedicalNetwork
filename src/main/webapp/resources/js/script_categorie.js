$(document).ready(function(){
    displayListCateg();
});

function displayListCateg(){
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
    var table = $(".div-table");

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
    var name, firstname, iduser;
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


           $.each(result.responseJSON, function(index, value){
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
                $("#user_li").append(displayUserList(name + " " + firstname,iduser));
     
            });
        },
        error:function(){
            console.log("error");
        }
    });
}

function displayUserList(full_name, id){
    var list_dom =''+
        '<li class="list-group-item">' +
            '<div class="userName">' +
                '<label for="userName">'+full_name+'</label>' +
            '</div>' +
            '<div class="action-buttons button_user">' +
                '<a onclick="sendRequestContact('+id+')">' +
                    '<span class="glyphicon glyphicon-user"></span>' +
                '</a>' +
                '<a onclick="contactUser('+id+')">' +
                    '<span class="glyphicon glyphicon-envelope"></span>' +
                '</a>' +
            '</div>' +
        '</li>';


    return list_dom;
}

function sendRequestContact(id){
   // var myJSON = '{ "accepted" : "false", "message" : "demande de contact", "user" : [{"iduser" : "1"}, {"idcontact" : "15"}]}';
    var myJSON = '{"idcontact" : '+id+', "message" : "demande de contact" }';
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/contact/add?token='+readCookie("token"),
        data: myJSON,
        contentType: "application/json; charset=utf-8",

        complete:function(result){
           
        },
        error:function(){
            console.log("error");
        }
    });
}


