jQuery(document).ready(function($) {

	//Login form event binding
	$("#loginForm").submit(function(event) {
		event.preventDefault();
		submitGlobalLoginForm();
	});
	
	//Password recovery form event binding
	$("#passwordRecoveryForm").submit(function(event) {
		event.preventDefault();
		submitPasswordRecoveryForm();
	});
	
	$( "#loginEmailAddress" ).focus(function() {
		  hideFooter();
	});
	
	$( "#loginPassword" ).focus(function() {
		  hideFooter();
	});

});

function hideFooter(){
	
	var width = screen.width;
	var height = screen.height;
	
	//If the screen is less than 500, then hide the footer
	if(width < 500 || height < 500){
		$("#siteFooter" ).css( "display", "none" );
	}
	
}

/**
 * Submit the global login form
 * 
 * @returns nothing
 */
function submitGlobalLoginForm() {

	//Get the user name and password
	var loginEmailAddress=document.getElementById("loginEmailAddress");
	var loginPassword=document.getElementById("loginPassword");

	//Create the JSON seach string
	var search = {
			"emailAddress":loginEmailAddress.value,
	        "password":loginPassword.value
	}

	$.ajax({
		  	headers: { 
		        'Accept': 'application/json',
		    'Content-Type': 'application/json' 
		},
		type: 'post',
		contentType : "application/json",
		//url: "http://localhost:8080/DiccionarioLesco/registro/verificarUsuario",
		url: "/DiccionarioLesco/ingreso/iniciarSesion",
		data : JSON.stringify(search),
		dataType : 'json',
		success : function(data) {
			console.log("SUCCESS: ", data);
			if(data != null  && data.code == "000"){
				//Get the current location of the user
				var pathname = window.location.pathname; // Returns path only
				var ingresar = pathname.includes("/ingresar");
				
				//Means that the user comes from an attempt to access a restricted web page and should be redirected to the home page after login
				//"/ingresar" is where the interceptor redirects the not logged users
				if(ingresar){
					$(window).attr('location','/DiccionarioLesco/')
				} else{
					//Reload the current location
					window.location.reload(true);
				}
			} else {
				$("#passwordRecoveryDiv").css("display", "inline");
			}
		},
		error : function(e) {
			console.log("ERROR: ", e);
		},
		done : function(e) {
			console.log("DONE");
		}
	});
}

/**
 * Password recovery form submit
 * 
 * @returns nothing
 */
function submitPasswordRecoveryForm() {

	//Get the recovery password modal
	var loginEmailAddressModal= document.getElementById("loginEmailAddressModal");

	//Create the JSON search string
	var search = {
			"emailAddress":loginEmailAddressModal.value
	}

	$.ajax({
		  	headers: { 
		        'Accept': 'application/json',
		    'Content-Type': 'application/json' 
		},
		type: 'post',
		contentType : "application/json",
		url: "/DiccionarioLesco/registro/recuperarPassword",
		data : JSON.stringify(search),
		dataType : 'json',
		success : function(data) {
			console.log("SUCCESS: ", data);
			
			//The true parameter forces the page to release it's cache.
			window.location.reload(true);
			
			if(data != null  && data.code == "000"){
			} else {
				//alert("Wrong password");
			}
		},
		error : function(e) {
			console.log("ERROR: ", e);
			//display(e);
		},
		done : function(e) {
			console.log("DONE");
		}
	});
}

/**
 * Ends the user session
 *  
 * @returns nothing
 */
function endUserSession() {

	$.ajax({
		  	headers: { 
		        'Accept': 'application/json',
		    'Content-Type': 'application/json' 
		},
		type: 'post',
		contentType : "application/json",
		url: "/DiccionarioLesco/ingreso/finalizarSesion",
		//data : JSON.stringify(search),
		dataType : 'json',
		success : function(data) {
			console.log("SUCCESS: ", data);
			
			//The true parameter forces the page to release it's cache.
			window.location.reload(true);
		},
		error : function(e) {
			console.log("ERROR: ", e);
			//display(e);
		},
		done : function(e) {
			console.log("DONE");
			}
	});
}