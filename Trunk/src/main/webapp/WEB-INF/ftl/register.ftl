<#import "common.ftl" as c/>
<@c.page css="resources/css/register.css">
    <!-- custom page content -->
        
    <h3>Creación de Usuario <span class="label label-default">Nuevo</span></h3>
    
	<form class="form-horizontal" name="registerForm" id="registerForm">
		<fieldset>
		
			<!-- Form Name -->
			<legend>Formulario de Registro</legend>
			
			<!-- Text input-->
			<div class="form-group">
			  <label class="col-md-4 control-label" for="textinput">Nombre de Usuario</label>  
			  <div class="col-md-4">
			  <input id="userName" name="userName" placeholder="Nombre de Usuario" class="form-control input-md" required="" type="text">
<!-- 			  <span class="help-block">Nombre de Usuario</span>   -->
			  </div>
			  <div class="col-md-4 bs-example">
			    <div class="alert alert-info fade in">
			        <a href="#" class="close" data-dismiss="alert">&times;</a>
			        <strong>Note!</strong> Please read the comments carefully.
			    </div>
			  </div>
			</div>
			
			<!-- Text input-->
			<div class="form-group">
			  <label class="col-md-4 control-label" for="textinput">Correo Electrónico</label>  
			  <div class="col-md-4">
			  <input id="emailAddress" name="emailAddress" placeholder="Correo Electrónico" class="form-control input-md" required="" type="email">
<!-- 			  <span class="help-block">Correo Electrónico</span>   -->
			  </div>
			</div>
			
			<!-- Password input-->
			<div class="form-group">
			  <label class="col-md-4 control-label" for="passwordinput">Contraseña</label>
			  <div class="col-md-4">
			    <input id="password" name="password" placeholder="Contraseña" class="form-control input-md" required="" type="password">
<!-- 			    <span class="help-block">Contraseña</span> -->
			  </div>
			</div>
			
			<!-- Password input-->
			<div class="form-group">
			  <label class="col-md-4 control-label" for="passwordinput">Confirmación Contraseña</label>
			  <div class="col-md-4">
			    <input id="passwordConfirmation" name="passwordConfirmation" placeholder="Confirmación Contraseña" class="form-control input-md" required="" type="password">
<!-- 			    <span class="help-block">Confirmación Contraseña</span> -->
			  </div>
			</div>
			
			<!-- Password input-->
			<div class="form-group">
			  <label class="col-md-4 control-label" for="passwordinput">Fecha de Nacimiento</label>
			  <div class="col-md-4">
			  <div class="input-group input-append date" id="datePicker">
			    <input type="text" class="form-control" id="birthdate" name="birthdate" value="10/24/2000" />
			    	<span class="input-group-addon">
			            <span class="glyphicon glyphicon-calendar"></span>
			        </span>
			        </div>
			  </div>
			</div>
			
			 
			<script type="text/javascript">
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

// 				$("#registerForm").submit(function(event) {

// 					// Disble the search button
// 					//enableSearchButton(false);

// 					// Prevent the form from submitting via the browser.
// 					//event.preventDefault();

// 					submitdata();

// 				});

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
			
			</script>
			
			
			<!-- Multiple Checkboxes -->
			<div class="form-group">
			  <label class="col-md-4 control-label" for="checkboxes">Aceptación Términos y Condiciones</label>
			  <div class="col-md-4">
			  <div class="checkbox">
			    <label for="checkboxes-0">
			      <input name="checkboxes" id="checkboxes-0" value="1" type="checkbox">
			      He leído y acepto los <a href="#">Términos Y Condiciones</a>
			    </label>
				</div>
			  </div>
			</div>
			
			<!-- Button (Double) -->
			<div class="form-group">
			  <label class="col-md-4 control-label" for="button1id"></label>
			  <div class="col-md-8">
			    <button id="button1id" name="button1id" class="btn btn-primary">Registrar</button>
			    <button id="button2id" name="button2id" class="btn btn-danger">Descartar</button>
			  </div>
			</div>
		
		</fieldset>
	</form>

    
</@c.page>