jQuery(document).ready(function($) {

		$("#loginForm").submit(function(event) {
			submitform();

		});

});

function submitform() {

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