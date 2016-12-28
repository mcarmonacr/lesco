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

 				$("#registerForm").submit(function(event) {
 					submitform();

 				});

				$("#userName").keyup(function(){
					checkUserName();
				});
				
				$("#emailAddress").keyup(function(){
					checkEmailAddress();
				});
				
				$("#password").keyup(function(){
					checkPassword();
				});
				
				$("#passwordConfirmation").keyup(function(){
					checkPassword();
				});

			});
			
			
			function checkUserName() {

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
				    url: "/DiccionarioLesco/registro/verificarUsuario",
				    data : JSON.stringify(search),
				    dataType : 'json',
				    success : function(data) {
				    	console.log("SUCCESS: ", data);
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
						//display(e);
					},
					done : function(e) {
						//console.log("DONE");
						//enableSearchButton(true);
					}
				  });
				  //return false;
			}
			
			function checkEmailAddress() {

				  var emailAddress=document.getElementById("emailAddress");
				  
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
				    url: "/DiccionarioLesco/registro/verificarCorreo",
				    data : JSON.stringify(search),
				    dataType : 'json',
				    success : function(data) {
				    	console.log("SUCCESS: ", data);
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
						//display(e);
					},
					done : function(e) {
						//console.log("DONE");
						//enableSearchButton(true);
					}
				  });
				  //return false;
			}
			
			function checkPassword() {

				  var password=$('#password');
				  var passwordConfirmation=$('#passwordConfirmation');
				  
				  if(password.value != null && password.value.lenght != 0){
					  if(passwordConfirmation.value != null && passwordConfirmation.value.lenght != 0){
						  
					  }else {
						  
					  }
				  }else {
					//$('#divEmailAddress').removeClass('has-error').addClass('has-success');
					//$('#divEmailAddress .glyphicon').removeClass('glyphicon-remove').addClass('glyphicon-ok');
				  }
				  
			}
			
			function submitform() {

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
				            "termsAndConditions":termsAndConditions.checked,
				            "administrator":administrator.checked
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
						console.log("DONE");
						//enableSearchButton(true);
					}
				  });
				  //return false;
				}