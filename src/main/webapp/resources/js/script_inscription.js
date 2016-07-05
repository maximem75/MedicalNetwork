$(document).ready(function(){
    displayCategs("idcategory");
    $("#inscription_form").on("submit", function(e){
        e.preventDefault();
        var $this = $(this);
        if(checkValues()==true) {
           var myJson = '{'+
               '"login": "'+$("#login").val()+'",'+
               '"password": "'+$("#password").val()+'",'+
               '"name": "'+$("#name").val()+'",'+
               '"firstname": "'+$("#firstname").val()+'",'+
               '"birthday": "'+$("#birthday").val()+'",'+
               '"phone": "'+$("#phone").val()+'",'+
               '"email": "'+$("#email").val()+'",'+
               '"category":{"idcategory": "'+$("#idcategory").find(":selected").val()+'"}'+
               '}';
               console.log(myJson);
           $.ajax({
               type: $this.attr("method"),
               url: $this.attr("action"),
               data:myJson,
               contentType: "application/json; charset=utf-8",

               success:function(res){
                    createCookie("token", token, 1);
                   window.location.href = "http://localhost:8080/accueil";
               },

               error: function(){

               }
           });
        }

    });

});


function checkValues(){
    var error = false;
    var login = $("#login").val();
    var password = $("#password").val();
    var password2 = $("#vpassword").val();
    var name = $("#name").val();
    var firstname = $("#firstname").val();
    var date = $("#birthday").val();
    var phone = $("#phone").val();
    var mail = $("#email").val();

    var message = "";

    if(login.length < 5){
        console.log(login + " / " + login.length)
        error = true;
        $("#login").addClass("error-inscription");
        $("#login").removeClass("good-inscription");
    } else {
        $("#login").removeClass("error-inscription");
        $("#login").addClass("good-inscription");
    }

    if(password.length < 6){
        error = true;
        $("#password").addClass("error-inscription");
        $("#password").removeClass("good-inscription");
    } else {
        $("#password").removeClass("error-inscription");
        $("#password").addClass("good-inscription");
    }

    if(password != password2){
        error = true;
        $("#vpassword").addClass("error-inscription");
        $("#vpassword").removeClass("good-inscription");
    } else {
        $("#vpassword").removeClass("error-inscription");
        $("#vpassword").addClass("good-inscription");
    }

    if(name === "" ){
        error = true;
        $("#name").addClass("error-inscription");
        $("#name").removeClass("good-inscription");
    } else {
        $("#name").removeClass("error-inscription");
        $("#name").addClass("good-inscription");
    }

    if(firstname === ""){
        error = true;
        $("#firstname").addClass("error-inscription");
        $("#firstname").removeClass("good-inscription");
    } else {
        $("#firstname").removeClass("error-inscription");
        $("#firstname").addClass("good-inscription");
    }


    var filter_1 = /^(0)[1,2,3,4,5,6,7,9]{1}[0-9]{8}$/;
    var filter_2 = /^(\+33)[1,2,3,4,5,6,7,9]{1}[0-9]{8}$/;

    if (filter_1.test(phone)==false && filter_2.test(phone)==false) {
        error = true;
        $("#phone").addClass("error-inscription");
        $("#phone").removeClass("good-inscription");
    } else {
        $("#phone").removeClass("error-inscription");
        $("#phone").addClass("good-inscription");
    }

    if(mail.length < 1){
        error = true;
        $("#email").addClass("error-inscription");
        $("#email").removeClass("good-inscription");
    } else {
        $("#email").addClass("good-inscription");
        $("#email").removeClass("error-inscription");
    }

    if(error === false){
        return true;
    } else {
        return false;
    }

}