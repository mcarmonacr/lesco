<#import "common.ftl" as c/>
<@c.page css="resources/css/register.css">
    <!-- custom page content -->
        
    <h3>Creación de Usuario <span class="label label-default">Nuevo</span></h3>
    
	<form class="form-horizontal">
<fieldset>

<!-- Form Name -->
<legend>Formulario de Registro</legend>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="textinput">Nombre de Usuario</label>  
  <div class="col-md-4">
  <input id="textinput" name="textinput" placeholder="placeholder" class="form-control input-md" required="" type="text">
  <span class="help-block">help</span>  
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="textinput">Correo Electrónico</label>  
  <div class="col-md-4">
  <input id="textinput" name="textinput" placeholder="placeholder" class="form-control input-md" required="" type="text">
  <span class="help-block">help</span>  
  </div>
</div>

<!-- Password input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="passwordinput">Contraseña</label>
  <div class="col-md-4">
    <input id="passwordinput" name="passwordinput" placeholder="placeholder" class="form-control input-md" required="" type="password">
    <span class="help-block">help</span>
  </div>
</div>

<!-- Password input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="passwordinput">Confirmación Contraseña</label>
  <div class="col-md-4">
    <input id="passwordinput" name="passwordinput" placeholder="placeholder" class="form-control input-md" required="" type="password">
    <span class="help-block">help</span>
  </div>
</div>



<!-- Multiple Checkboxes -->
<div class="form-group">
  <label class="col-md-4 control-label" for="checkboxes">Aceptación Términos y Condiciones</label>
  <div class="col-md-4">
  <div class="checkbox">
    <label for="checkboxes-0">
      <input name="checkboxes" id="checkboxes-0" value="1" type="checkbox">
      Acepto lo siguiente: <a href="#">Términos Y Condiciones</a>
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