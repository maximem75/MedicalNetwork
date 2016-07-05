$(document).ready(function(){
    $("#login-form").on("submit", function(e){
        e.preventDefault();

        var $this = $(this);

        var login = $("#login_id").val();
        var password = $("#password_id").val();

        var resData = "login="+login+"&password="+password;

        $.ajax({
            type: $this.attr("method"),
            url: $this.attr("action"),
            data:resData,
            beforeSend: function (xhr) {
                if (xhr && xhr.overrideMimeType) {
                    xhr.overrideMimeType('application/json;charset=utf-8');
                }
            },
           datatype: "json",

            error: function(){
        
            },

            complete:function(res){
                console.log(res.responseText.length);
                if(res.responseText.length > 0){
                    createCookie("token", res.responseText, 1);
                    window.location.href = "http://localhost:8080/accueil";
                } else {
                    $("#span_error").remove();
                    $("#login-form").append("<span id='span_error'>Mauvais Identifiants</span>");
                    $("#span_error").css({
                    "color" : "red"
                    });
                }
                    
            
            }

        });
    });
});

