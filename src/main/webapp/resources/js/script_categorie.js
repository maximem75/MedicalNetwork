$(document).ready(function(){
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/category/all?token="+readCookie("token"),
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
});

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
        var elem = document.getElementById(name)
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
        },
        error:function(){
            console.log("error");
        }
    });
}

var full_name = "null";

var list_dom = '<tr>'
    +'<td><span class="label label-default">'+full_name+'</span></td>'
    +'<td style="width: 20%;"><a href="#" class="table-link"><span class="fa-stack"><i class="fa fa-square fa-stack-2x"></i><i class="fa fa-search-plus fa-stack-1x fa-inverse"></i></span>'
    +'</a><a href="#" class="table-link"><span class="fa-stack"><i class="fa fa-square fa-stack-2x"></i><i class="fa fa-pencil fa-stack-1x fa-inverse"></i></span></a>'
    +'<a href="#" class="table-link danger"><span class="fa-stack"><i class="fa fa-square fa-stack-2x"></i><i class="fa fa-trash-o fa-stack-1x fa-inverse"></i></span>'
    +'</a></td></tr>';
