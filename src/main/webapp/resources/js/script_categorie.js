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

            var categoryArray = [];
            var i = 0;
            $.each(res, function(index, category) {
                categoryArray[i] = category;
                if(i === 6){
                    addBox(category.nameCategory,true);
                } else {
                    addBox(category.nameCategory,false);
                }

                i++;

                console.log(category.nameCategory);
            });
        },

        error: function(){
            alert("error");
        }
    });
});

function searchCateg(elem){
    var id = $(elem).attr("id");
    document.location.href = 'file:///C:/Users/molla/Desktop/Projet%20Annuel%20(medicalnetwork)/Front/listMedecin.html?search='+id;
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