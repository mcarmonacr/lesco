<#import "common.ftl" as c/>
<@c.page css="resources/css/register.css">
    <!-- custom page content -->
        
    <h3>Creación de Usuario <span class="label label-default">Nuevo</span></h3>
    
	<form>
	  <div class="form-group">
	    <label for="email">Email address:</label>
	    <input type="email" class="form-control" id="email">
	  </div>
	  <div class="form-group">
	    <label for="pwd">Password:</label>
	    <input type="password" class="form-control" id="pwd">
	  </div>
	  <div class="checkbox">
	    <label><input type="checkbox"> Remember me</label>
	  </div>
	  <button type="submit" class="btn btn-default">Submit</button>
	</form>
    
</@c.page>