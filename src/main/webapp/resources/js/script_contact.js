$(document).ready(function() {
	getMyPendings();
	getMyContacts();
	init();	
});

var pending, id_contact, name_contact, firstname_contact;
var arrayPending = [];
var arrayContact = [];
var arrayContactAccepted = [];

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
		getMyContacts();
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
		getMyPendings();
	});
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
    var id_user, name , firstname, completeName, id_contact, message;

     $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/contact/pending',
        data: "token="+readCookie("token"),
        contentType: "application/json; charset=utf-8",

        complete:function(result){
           pending = result.responseJSON;
           var json ="";
           var i = 0;
           arrayContact = new Array();
           $.each(pending, function(index, value){           		
           		$.each(value, function(id, val){
           			switch(id){
           				case 0:           				
           					json ='{"idrelation" : "'+val+'",';
           				break;
           				case 1:
           					json+='"accepted":"false",';
           				break;

           				case 2: 
           					json +='"message" : "'+val+'",';
           					message = val
           				break;

           				case 3:           					
           					json += '"idcontact" : {"iduser" : "'+val+'"},';              				        					
           					id_user = val;
           				break;
           				case 4:
           					name = val;
           				break;
           				case 5:
           					firstname = val;
           				break;
           				case 6:
           					json += '"iduser" : {"iduser" : "'+val+'"}}';           					
           					id_contact = val;
           				break;
           			}
           		});           		
           		arrayContact.push(json);
           		completeName = name + " " + firstname;   
         			
   				displayPendings(id_user, i,completeName, message);
   				i++;
			          		
           });
           
        },
        error:function(){
            console.log("error");
        }
    });
}

function displayPendings(id, id_array, name, message){		
	var check = checkDisplayedPendings(id);
	
	if(check == true){
		$("#pending_content").find(".div_table").append("<div class='div_row' id='pending_"+ id +"'>"+
			"<div class='div_cell' ><label class='table_component'>"+ name +"</label></div>"+
			"<div class='div_cell'><div class='message_content'>"+message+"</div></div>"+
			"<div class='div_cell second_cell'>"+
			"<button type='button' class='btn btn-default marg_t' onclick='acceptPending("+id_array+",pending_"+ id +")'><span class='glyphicon glyphicon-ok' aria-hidden='true'></span></button><button type='button' class='btn btn-default marg_t' onclick='removePending(pending_"+ id +","+ id +")'><span class='glyphicon glyphicon-trash' aria-hidden='true'></span></button></div></div>");
	}		
}

function checkDisplayedPendings(id){
	var res = true;
	if(arrayPending == ""){
		arrayPending.push(id);		
	} else {
		$.each(arrayPending, function(ind, val){
			if(val == id){			
				res = false;		
			}
		});
		if (res == true){
			arrayPending.push(id);
		}
	}	
	return res;
}

function acceptPending(id, id_div){		
		var contact = arrayContact[id];
	    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/contact/accept?token='+readCookie("token"),
        contentType: "application/json; charset=utf-8",
        data: contact,
       
       success:function(result){
        id_div.remove();
        verifyPendings();
        },
        error:function(){
            console.log("error");
        }
    });
}

function removePending(id_div ,id_contact){

     $.ajax({
        type: "POST",
        url: "http://localhost:8080/contact/remove",
      	data:"token="+readCookie("token")+"&idcontact="+id_contact,
       
       success:function(result){
        	id_div.remove();
        	verifyPendings();
        },
        error:function(){
            console.log("error");
        }
    });
}

function getMyContacts(){
	$("#contact_content").find(".div_table").empty();
	var json;
	$.ajax({
        type: 'GET',
        url: 'http://localhost:8080/user/contact',
        data: "token="+readCookie("token"),

        complete:function(result){           
           $.each(result.responseJSON, function(index, value){
           		$.each(value, function(id, val){
           			switch(id){
           				case "iduser":           				
           					json ='{"iduser" : "'+val+'",';
           					id_contact = val;
           				break;
           				case "name":
           					json += '"name" : "'+val+'",';
           					name_contact = val;
           				break;

           				case "firstname": 
           					json += '"firstname" : "'+val+'"}';
           					firstname_contact = val;
           				break;

           			}
           		});             		
           		arrayContactAccepted.push(json);
           		displayContactsAccepted(id_contact, name_contact, firstname_contact);
           	});     
        },
        error:function(){
            console.log("error");
        }
    });
}

function displayContactsAccepted(id, name, firstname){
	$("#contact_content").find(".div_table").append("<div class='div_row' id='contact_"+ id +"'>"+
			"<div class='div_cell div_cell_text' ><label class='table_component'>"+ name +" " +firstname +"</label></div>"+
			"<div class='div_cell second_cell'>"+
			"<button type='button' class='btn btn-default' onclick='sendMessage("+id+");'><span class='glyphicon glyphicon-envelope' aria-hidden='true'></span></button><button type='button' class='btn btn-default' onclick='removePending(contact_"+ id +","+ id +")'><span class='glyphicon glyphicon-trash'  aria-hidden='true'></span></button></div></div>");


}

function sendMessage(id){
	window.location.href = "http://localhost/MedicalNetwork/chat/templates/chat.html?token="+readCookie("token")+"&recev="+id;
}


function manageInputSearchContact(){
    $("#inpt_search").on("keyup",function(){
        researchContacts($("#inpt_search").val());
    });
    
}

function researchContacts(val){
    var name, firstname, id;
    var array_cell = $("#contact_content").find(".div_cell_text");
    array_name = new Array();

    $.each(array_cell, function(id, val){
    	array_name.push(val);   
    });

    $.each(array_name, function(id, value){    	
    	console.log(id);
    	if($(value).parents(".div_row").text().toLowerCase().indexOf(val.toLowerCase()) == -1 && $(value).parents(".div_row").text().length > 0){
    		console.log($(array_cell[id]).parents(".div_row"));
    		console.log("false");
    		$(array_cell[id]).parents(".div_row").css({"display" : "none"});
    	} else {
    		console.log($(array_cell[id]));
    		console.log("true");
    		$(array_cell[id]).parents(".div_row").css({"display" : "table-row"});
    	}
    });
    
}
