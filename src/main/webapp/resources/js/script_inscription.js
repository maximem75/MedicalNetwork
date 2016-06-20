(function ($) {
    $.fn.serializeFormJSON = function () {

        var o = {};
        var a = this.serializeArray();
        $.each(a, function () {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };
})(jQuery);

$(document).ready(function(){

   $("#inscription_form").on("submit", function(e){

        var form = $(this);
        var data = $(this).serializeFormJSON();
        $.postJSON = function(url, data, callback) {
        console.log(data);
            return jQuery.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'type': 'POST',
            'url': url,
            'data': JSON.stringify(data),
            'dataType': 'json',
            'success': callback
            });
        };
        $.postJSON(form.attr("action"), data, (data) => {
            alert("ok" + " " + data);
        });
        e.preventDefault();
    });
});

/*
    $("#inscription_form").on("submit", function(e){
        e.preventDefault();

        var $this = $(this);

       /* var login = $("#login").val();
        var password = $("#password").val();
        var name = $("#name").val();
        var firstname = $("#firstname").val();
        //var date = $("#date").val();
        var date = new Date();
        var phone = $("#phone").val();
        var mail = $("#mail").val();


        var login = "true";
        var password = "ta";
        var name = "mere";
        var firstname = "gros";
        var date = new Date();
        var phone = "FILS";
        var mail = "DEPUTE";

        var resData = "login="+login+"&password="+password+"&name="+name+"&firstname="+firstname+"&birthday="+date+"&phone="+phone+"&email="+mail+"&idcategory=1";

        //checkValues();

         $.ajax({
         type: $this.attr("method"),
         url: $this.attr("action"),
         data:{
            "login":login,
            "password":password,
            "name":name,
            "firstname":firstname,
            "birthday":date,
            "phone":phone,
            "email":mail,
            "idcategory":"1"

         },
         contentType: "application/json; charset=utf-8",

         success:function(res){
             window.location.href = "http://localhost:8080/accueil";
         },

         error: function(){

         }

         });
    });
});

function checkValues(){
    var error = false;
    var login = $("#login").val();
    var password = $("#password").val();
    var password2 = $("#password2").val();
    var name = $("#name").val();
    var firstname = $("#firstname").val();
    var date = $("#date").val();
    var phone = $("#phone").val();
    var mail = $("#mail").val();

    var phoneReg = /^\+33[0-9]{9}$/;
    var phoneReg = /^(06)[0-9]{9}$/;

    var message = "";

    if($(login).length < 4){
        error = true;
    }

    if(password != password2){
        error = true;
    }

    if($(password).length < 6){
        error = true;
    }

    if(name === "" || firstname === "" || date === ""){
        error = true;
    }

    var resPhone1 = phone.match(phoneReg);

    if(resPhone != undefined){
        console.log(resPhone);
    }

    if(error != "true"){
        return true;
    } else {
        return false;
    }

}
*/