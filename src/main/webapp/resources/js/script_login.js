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
            datatype: "jsonp",

            success:function(res){
                var i = 0;
                $.each(res, function(index, user) {
                    if(index === "token"){
                        createCookie("token", user, 1);
                    }
                    i++;
                    console.log("User : " + user + " / id : " + index + " / i : " + i);
                });

                window.location.href = "http://localhost:8080/accueil";
            },

            error: function(){
                alert("Identifiants incorrects");
            }

        });
    });
});

function createCookie(name,value,days) {
    if (days) {
        var date = new Date();
        date.setTime(date.getTime()+(days*24*60*60*1000));
        var expires = "; expires="+date.toGMTString();
    }
    else var expires = "";
    document.cookie = name+"="+value+expires+"; path=/";
}