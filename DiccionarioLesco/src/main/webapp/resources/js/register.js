$(function() {
			    $('input[name="birthdate"]').daterangepicker({
			        singleDatePicker: true,
			        showDropdowns: true
			    }, 
			    function(start, end, label) {
			        var years = moment().diff(start, 'years');
			        //alert("You are " + years + " years old.");
			    });
			});
			
			
			jQuery(document).ready(function($) {

 				$("#registerForm").submit(function(event) {
 					submitform();

 				});

				$("#userName").keyup(function(){
					submitdata();
				});

			});
			
			
			function submitdata() {

				  var userName=document.getElementById("userName");
				  
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
				    //url: "http://localhost:8080/DiccionarioLesco/registro/verificarUsuario",
				    url: "/DiccionarioLesco/registro/verificarUsuario",
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
						//console.log("DONE");
						//enableSearchButton(true);
					}
				  });
				  //return false;
				}
			
			function submitform() {

				  var userName=document.getElementById("userName");
				  var emailAddress=document.getElementById("emailAddress");
				  var password=document.getElementById("password");
				  var passwordConfirmation=document.getElementById("passwordConfirmation");
				  var birthDate=document.getElementById("birthDate");
				  var termsAndConditions=document.getElementById("termsAndConditions");
				  
				  var search = {
				            "userName":userName.value,
				            "emailAddress":emailAddress.value,
				            "password":password.value,
				            "passwordConfirmation":passwordConfirmation.value,
				            "birthDate":birthDate.value,
				            "termsAndConditions":termsAndConditions.checked
				    }

				  $.ajax({
				  	headers: { 
				        'Accept': 'application/json',
				        'Content-Type': 'application/json' 
				    },
					type: 'post',
				    contentType : "application/json",
				    //url: "http://localhost:8080/DiccionarioLesco/registro/verificarUsuario",
				    url: "/DiccionarioLesco/registro/agregarUsuario",
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
						//console.log("DONE");
						//enableSearchButton(true);
					}
				  });
				  //return false;
				}