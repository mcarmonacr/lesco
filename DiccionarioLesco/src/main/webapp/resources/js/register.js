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
				  var userName=document.getElementById("emailAddress");
				  var userName=document.getElementById("password");
				  var userName=document.getElementById("passwordConfirmation");
				  var userName=document.getElementById("birthdate");
				  var userName=document.getElementById("termsAndConditions");
				  
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