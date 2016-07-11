$(document).ready(function() {
	pending = getMyPendings();
	init();	
});

var pending;
var arrayPending = [];

function init(){
	$("#tab_contact_id").css({
			"background-color" : "white"
	});	

	$("#tab_contact_id").find(".tab_text").css({
		"color" : "#337ab7"
	});	

	$("#tab_pending_id").css({
		"background-color" : "#337ab7"
	});

	$("#tab_pending_id").find(".tab_text").css({
		"color" : "white"
	});

	$("#contact_content").css({
		"display" : "block"
	});

	$("#pending_content").css({
		"display" : "none"
	});

	$("#tab_contact_id").on("click",function(){
		$("#tab_contact_id").addClass("active");
		$("#tab_pending_id").removeClass("active");		

		$("#tab_contact_id").css({
			"background-color" : "white"
		});	

		$("#tab_contact_id").find(".tab_text").css({
			"color" : "#337ab7"
		});	

		$("#tab_pending_id").css({
			"background-color" : "#337ab7"
		});

		$("#tab_pending_id").find(".tab_text").css({
			"color" : "white"
		});	

		manageTab("tab_contact_id");
	});

	$("#tab_pending_id").on("click",function(){
		$("#tab_contact_id").removeClass("active");
		$("#tab_pending_id").addClass("active");	

		$("#tab_pending_id").css({
			"background-color" : "white"
		});	

		$("#tab_pending_id").find(".tab_text").css({
			"color" : "#337ab7"
		});	

		$("#tab_contact_id").css({
			"background-color" : "#337ab7"
		});

		$("#tab_contact_id").find(".tab_text").css({
			"color" : "white"
		});	

		manageTab("tab_pending_id");
	});
	acceptPending();
}

function manageTab(id){
	switch(id){
		case "tab_contact_id":
			$("#contact_content").css({
				"display" : "block"
			});

			$("#pending_content").css({
				"display" : "none"
			});
		break;

		case "tab_pending_id":
			$("#pending_content").css({
				"display" : "block"
			});

			$("#contact_content").css({
				"display" : "none"
			});			
		break;
	}
}

function getMyPendings(){
    var pendings;
    var id_user, name , firstname, completeName;
     $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/user/pending',
        data: "token="+readCookie("token"),
        contentType: "application/json; charset=utf-8",

        complete:function(result){
           pending = result.responseJSON;
           $.each(pending, function(index, value){
           		$.each(value, function(id, val){
           			switch(id){
           				case 0:
           					id_user = val;
           				break;
           				case 1:
           					name = val;
           				break;
           				case 2:
           					firstname = val;
           				break;
           			}
           		});
           		completeName = name + " " + firstname;      			
   				displayPendings(id_user, completeName);
			          		
           });
           
        },
        error:function(){
            console.log("error");
        }
    });
}

function displayPendings(id, name){		
	var check = checkDisplayedPendings(id);
	if(check == true){
		$(".div_table").append("<div class='div_row'>"+
			"<div class='div_cell' id='pending_"+ id +"'><label class='table_component'>"+ name +"</label></div>"+
			"<div class='div_cell' class='group_btn_pending'></div>"+
			"<button type='button' class='btn btn-small' onclick='acceptPending()'><span class='glyphicon glyphicon-ok' aria-hidden='true'></span></button>"+
			"<button type='button' class='btn btn-default'><span class='glyphicon glyphicon-remove' aria-hidden='true'></span></button></div>");
	}		
}

function checkDisplayedPendings(id){
	if(arrayPending == ""){
		arrayPending[0] = id;		
	} else {
		var res = false;
		$.each(arrayPending, function(ind, val){
			if(val == id){
				return false;
			}
		});
		if (res == false){
			arrayPending.push(id);
			return true;
		}
	}	
}

function acceptPending(){
	     $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/contact/accept',
        data: "iduser=4&idcontact=1",
       
       success:function(result){
        	console.log("success accept pending");
        },
        error:function(){
            console.log("error");
        }
    });
}