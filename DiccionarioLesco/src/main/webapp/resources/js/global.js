jQuery(document).ready(function($) {

		$("#loginForm").submit(function(event) {
			event.preventDefault();
			submitGlobalLoginForm();

		});
		
		$("#passwordRecoveryForm").submit(function(event) {
			event.preventDefault();
			submitPasswordRecoveryForm();

		});

});

function submitGlobalLoginForm() {

	  var loginEmailAddress=document.getElementById("loginEmailAddress");
	  var loginPassword=document.getElementById("loginPassword");

	  
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
				//The true parameter forces the page to release it's cache.
				window.location.reload(true);
			} else {
				//alert("Wrong password");
				//document.getElementById("passwordRecoveryDiv").css("visibility", "visible");
				$("#passwordRecoveryDiv").css("display", "inline");
			}
		},
		error : function(e) {
			console.log("ERROR: ", e);
			//display(e);
		},
		done : function(e) {
			console.log("DONE");
			//enableSearchButton(true);
		}
	  });
	  //return false;
}

function submitPasswordRecoveryForm() {

	  var loginEmailAddressModal= document.getElementById("loginEmailAddressModal");

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
			
			//display(data);
			
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
			//enableSearchButton(true);
		}
	  });
	  //return false;
}

function endUserSession() {


	  $.ajax({
	  	headers: { 
	        'Accept': 'application/json',
	        'Content-Type': 'application/json' 
	    },
		type: 'post',
	    contentType : "application/json",
	    //url: "http://localhost:8080/DiccionarioLesco/registro/verificarUsuario",
	    url: "/DiccionarioLesco/ingreso/finalizarSesion",
	    //data : JSON.stringify(search),
	    dataType : 'json',
	    success : function(data) {
			console.log("SUCCESS: ", data);
			
			//The true parameter forces the page to release it's cache.
			window.location.reload(true);
			
			//display(data);
		},
		error : function(e) {
			console.log("ERROR: ", e);
			//display(e);
		},
		done : function(e) {
			console.log("DONE");
			//enableSearchButton(true);
		}
	  });
	  //return false;
}