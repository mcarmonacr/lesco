<#import "common.ftl" as c/>
<@c.page css="resources/css/register.css" js="resources/js/register.js">
    <!-- custom page content -->
        
    <h3>Creación de Usuario <span class="label label-default">Nuevo</span></h3>
    
	<form class="form-horizontal" name="registerForm" id="registerForm">
		<fieldset>
		
			<!-- Form Name -->
			<legend>Formulario de Registro</legend>
			
			<!-- Text input-->
			<div id="divUserName" class="form-group has-feedback">
			  <label class="col-md-4 control-label" for="textinput">Nombre de Usuario</label>  
			  <div class="col-md-4">
				  <input id="userName" name="userName" placeholder="Nombre de Usuario" class="form-control input-md" required="" type="text">
				  <span class="glyphicon form-control-feedback"></span>
			  </div>
			  
			  
			</div>
			
			<!-- Text input-->
			<div id="divEmailAddress" class="form-group has-feedback">
			  <label class="col-md-4 control-label" for="textinput">Correo Electrónico</label>  
			  <div class="col-md-4">
			  <input id="emailAddress" name="emailAddress" placeholder="Correo Electrónico" class="form-control input-md" required="" type="email">
				<!-- 			  <span class="help-block">Correo Electrónico</span>   -->
				<span class="glyphicon form-control-feedback"></span>
			  </div>
			</div>
			
<!-- 			<div class="form-group has-error has-feedback"> -->
<!-- 		      <label class="col-sm-2 control-label" for="inputSuccess">Input with success and glyphicon</label> -->
<!-- 		      <div class="col-sm-10"> -->
<!-- 		        <input type="text" class="form-control" id="inputSuccess"> -->
<!-- 		        <span class="glyphicon glyphicon-remove form-control-feedback"></span> -->
<!-- 		      </div> -->
<!-- 		    </div> -->
			
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
			  <label class="col-md-4 control-label" for="birthDate">Fecha de Nacimiento</label>
			  <div class="col-md-4">
			  <div class="input-group input-append date" id="datePicker">
			    <input type="text" class="form-control" id="birthDate" name="birthDate" value="10/24/2000" />
			    <!-- 			http://www.daterangepicker.com/ -->
			    	<span class="input-group-addon">
			            <span class="glyphicon glyphicon-calendar"></span>
			        </span>
			        </div>
			  </div>
			</div>
			
			
			<!-- Multiple Checkboxes -->
			<div class="form-group">
			  <label class="col-md-4 control-label" for="checkboxes">Aceptación Términos y Condiciones</label>
			  <div class="col-md-4">
			  <div class="checkbox">
			    <label for="checkboxes-0">
			      <input name="termsAndConditions" id="termsAndConditions" type="checkbox" required="">
			      He leído y acepto los <a data-toggle="modal" data-target="#myModal">Términos Y Condiciones</a>
			    </label>
				</div>
			  </div>
			</div>
			
			<!-- Button (Double) -->
			<div class="form-group">
			  <label class="col-md-4 control-label" for="button1id"></label>
			  <div class="col-md-8">
			    <button type="submit" id="button1id" name="button1id" class="btn btn-primary">Registrar</button>
			    <button id="button2id" name="button2id" class="btn btn-danger">Descartar</button>
			  </div>
			</div>
		
		</fieldset>
	</form>
	
	<!-- Button trigger modal -->
<!-- <button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal"> -->
<!--   Launch demo modal -->
<!-- </button> -->

<!-- Modal -->
<div class="modal fade bs-example-modal-lg" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Términos Y Condiciones</h4>
      </div>
      <div class="modal-body">
       
<div class="container">

      <div class="row starter-template">
      
      <div class="row">
        	<div class="col-md-9">
      			<img src="resources/images/terminos_y_condiciones2.png" alt="..." class="img-rounded img-responsive center-block">
       			<h1>Términos De Servicio</h1>
        	</div>
        </div>
        
        <br>
        
        <div class="row">
        	<div class="col-md-9">
        		<h2>Historia</h2>
        		<p class="lead">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu.</p>
        	</div>
        </div>
        
        <div class="row">
        	<div class="col-md-9">
        		<h2>Misión</h2>
        		<p class="lead">In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus.</p>
        </div>
        </div>
        
        <div class="row">
        	<div class="col-md-9">
        		<h2>Visión</h2>
        		<p class="lead">Phasellus viverra nulla ut metus varius laoreet. Quisque rutrum. Aenean imperdiet. Etiam ultricies nisi vel augue. Curabitur ullamcorper ultricies nisi. Nam eget dui. Etiam rhoncus. Maecenas tempus, tellus eget condimentum rhoncus, sem quam semper libero, sit amet adipiscing sem neque sed ipsum. Nam quam nunc, blandit vel, luctus pulvinar, hendrerit id, lorem. Maecenas nec odio et ante tincidunt tempus. Donec vitae sapien ut libero venenatis faucibus. Nullam quis ante. Etiam sit amet orci eget eros faucibus tincidunt. Duis leo. Sed fringilla mauris sit amet nibh. Donec sodales sagittis magna.</p>
        </div>
        </div>
        
      </div>

    </div><!-- /.container -->


      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
<!--         <button type="button" class="btn btn-primary">Save changes</button> -->
      </div>
    </div>
  </div>
</div>


    
</@c.page>