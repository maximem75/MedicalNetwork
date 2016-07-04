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
            console.log(res);
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
                $("#user_li").append(displayUserList(value.name + " " + value.firstname, value.iduser));
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
                '<a onclick="addContact("'+id+'")">' +
                    '<span class="glyphicon glyphicon-user"></span>' +
                '</a>' +
                '<a onclick="conactUser("'+id+'")">' +
                    '<span class="glyphicon glyphicon-envelope"></span>' +
                '</a>' +
            '</div>' +
        '</li>';


    return list_dom;
}

function addContact(id){
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/category/add',
        data: 'token='+readCookie("token")+'&iduser',
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
                $("#user_li").append(displayUserList(value.name + " " + value.firstname, value.iduser));
            });
        },
        error:function(){
            console.log("error");
        }
    });

}