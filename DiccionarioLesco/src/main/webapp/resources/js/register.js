//Date picker initializer
$(function() {
    $('input[name="birthDate"]').daterangepicker({
        singleDatePicker: true,
        showDropdowns: true
//			        locale: {
//			            format: 'YYYY-MM-DD'
//			          },
    }, 
    function(start, end, label) {
        //var years = moment().diff(start, 'years');
        //alert("You are " + years + " years old.");
    });
});

jQuery(document).ready(function($) {

	//Register user form event binding 
	$("#registerForm").submit(function(event) {
		event.preventDefault();
		submitform();
	});

	//User name existence check  event
	$("#userName").keyup(function(){
		checkUserName();
	});
	
	//Email address existence check event
	$("#emailAddress").keyup(function(){
		checkEmailAddress();
	});
	
	//Password equality check event
	$("#password").keyup(function(){
		checkPassword();
	});
	
	//Password equality check event
	$("#passwordConfirmation").keyup(function(){
		checkPassword();
	});

});

/**
 * User name existence check
 * 
 * @returns nothing
 */
function checkUserName() {

	//Get the user name element
	var userName=document.getElementById("userName");
	  
	//Create the JSON search string
	var search = {
			"userName":userName.value
	}

	$.ajax({
		headers: { 
			'Accept': 'application/json',
			'Content-Type': 'application/json' 
		},
		type: 'post',
		contentType : "application/json",
		url: "/lesco/registro/verificarUsuario",
		data : JSON.stringify(search),
		dataType : 'json',
		success : function(data) {
			console.log("SUCCESS: ", data);
			
			//Set the proper classes to the proper elements
			if(data != null && data.code == "000"){
				$('#divUserName').removeClass('has-error').addClass('has-success');
				$('#divUserName .glyphicon').removeClass('glyphicon-remove').addClass('glyphicon-ok');
			}else {
				$('#divUserName').removeClass('has-success').addClass('has-error');
				$('#divUserName .glyphicon').removeClass('glyphicon-ok').addClass('glyphicon-remove');
			}
			
		},
		error : function(e) {
			console.log("ERROR: ", e);
		},
		done : function(e) {
			console.log("DONE: ", e);
			}
	});
}

/**
 * Checks the email address existence
 * 
 * @returns nothing
 */
function checkEmailAddress() {

	//Get the email address element
	var emailAddress=document.getElementById("emailAddress");
	  
	//Create the JSON search string
	var search = {
			"emailAddress":emailAddress.value
	}

	$.ajax({
		  	headers: { 
		        'Accept': 'application/json',
		'Content-Type': 'application/json' 
		},
		type: 'post',
		contentType : "application/json",
		url: "/lesco/registro/verificarCorreo",
		data : JSON.stringify(search),
		dataType : 'json',
		success : function(data) {
			console.log("SUCCESS: ", data);
			
			//Set the proper classes to the proper elements
			if(data != null && data.code == "000"){
				$('#divEmailAddress').removeClass('has-error').addClass('has-success');
				$('#divEmailAddress .glyphicon').removeClass('glyphicon-remove').addClass('glyphicon-ok');
			}else {
				$('#divEmailAddress').removeClass('has-success').addClass('has-error');
				$('#divEmailAddress .glyphicon').removeClass('glyphicon-ok').addClass('glyphicon-remove');
			}
		},
		error : function(e) {
			console.log("ERROR: ", e);
		},
		done : function(e) {
			console.log("DONE: ", e);
			}
	});
}

/**
 * Checks that the password and the password confirmation are equal
 * 
 * @returns
 */
function checkPassword() {

	//Get the elements
	var password=$('#password');
	var passwordConfirmation=$('#passwordConfirmation');
	  
	//If both are equal
    if(password.value != null && password.value.lenght != 0){
    	if(passwordConfirmation.value != null && passwordConfirmation.value.lenght != 0){
		  
    	}else {
		  
    	}
    }else {
	  //$('#divEmailAddress').removeClass('has-error').addClass('has-success');
	  //$('#divEmailAddress .glyphicon').removeClass('glyphicon-remove').addClass('glyphicon-ok');
    }
}

/**
 * Create new user form event
 * 
 * @returns nothing
 */
function submitform() {

	//Get the elements to be entered
	var userName=document.getElementById("userName");
	var emailAddress=document.getElementById("emailAddress");
	var password=document.getElementById("password");
	var passwordConfirmation=document.getElementById("passwordConfirmation");
	var birthDate=document.getElementById("birthDate");
	var termsAndConditions=document.getElementById("termsAndConditions");
	var administrator=document.getElementById("administrator");
	  
    var search = {
		"userName":userName.value,
		"emailAddress":emailAddress.value,
		"password":password.value,
		"passwordConfirmation":passwordConfirmation.value,
		"birthDate":birthDate.value,
		"termsAndConditions":termsAndConditions.checked
        //"administrator":administrator.checked
    }

	$.ajax({
		headers: { 
			'Accept': 'application/json',
			'Content-Type': 'application/json' 
		},
		type: 'post',
		contentType : "application/json",
		url: "/lesco/registro/nuevoUsuario",
		data : JSON.stringify(search),
		dataType : 'json',
		success : function(data) {
			console.log("SUCCESS: ", data);
		
			//Set the register user modal as static so the user can get rid of it by clicking outside of it
			$('#registerUserModal').modal({
				backdrop: 'static'
			});
			
			//Show the register user modal
			$('#registerUserModal').modal('show');
		
		},
		error : function(e) {
			console.log("ERROR: ", e);
		},
		done : function(e) {
			console.log("DONE");
			}
	});
	}