$(document).ready(function(){
    $("#inscription_form").on("submit", function(e){
        e.preventDefault();
        console.log("inscription");

        var $this = $(this);

        var login = $("#login").val();
        var password = $("#password").val();
        var name = $("#name").val();
        var firstname = $("#firstname").val();
        var date = $("#date").val();
        var phone = $("#phone").val();
        var mail = $("#mail").val();


        var resData = "login="+login+"&password="+password+"&name="+name+"&firstname="+firstname+"&birthday="+date+"&phone="+phone+"&email="+mail;

        //checkValues();

         $.ajax({
         type: $this.attr("method"),
         url: $this.attr("action"),
         data:resData,
         contentType: "application/json; charset=utf-8",
         datatype: "jsonp",

         success:function(res){
             window.location.href = "http://localhost:8080/accueil";
         },

         error: function(){
         alert("Identifiants incorrects");
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
