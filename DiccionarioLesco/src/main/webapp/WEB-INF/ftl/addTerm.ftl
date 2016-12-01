<#import "common.ftl" as c/>
<@c.page css="resources/css/home.css" js="resources/js/home.js">
    <!-- custom page content -->
    
    <!-- Whole content row -->
    <div class="row" id="main-content">
    
    <!-- Terms column -->
    <div class="col-md-3">
    
    <div class="panel panel-default">
		  <div class="panel-body">

    	<!-- Terms Section -->
	    <div class="list-group">
	    
	    <div class="row">
	    	<h1><span class="label label-primary terms-header">Términos Solicitados</span></h1>
	    </div>
	    
	    <div class="row input-group search-text-box">
		  <span class="glyphicon glyphicon-search input-group-addon home-search-glyphicon"></span>
<!-- 		   <span class="input-group-addon" id="basic-addon1">@</span> -->
		  <input type="text" class="form-control" placeholder="Buscar" aria-describedby="sizing-addon2">
		</div>
	    
	    <div class="row terms-list">
		  <a href="#" class="list-group-item active">
		    Cras justo odio
		  </a>
		  <a href="#" class="list-group-item">Dapibus ac facilisis in</a>
		  <a href="#" class="list-group-item">Morbi leo risus</a>
		  <a href="#" class="list-group-item">Porta ac consectetur ac</a>
		  <a href="#" class="list-group-item">Vestibulum at eros</a>
		  <a href="#" class="list-group-item">Dapibus ac facilisis in</a>
		  <a href="#" class="list-group-item">Morbi leo risus</a>
		  <a href="#" class="list-group-item">Porta ac consectetur ac</a>
		  <a href="#" class="list-group-item">Vestibulum at eros</a>
		  <a href="#" class="list-group-item">Dapibus ac facilisis in</a>
		  <a href="#" class="list-group-item">Morbi leo risus</a>
		  <a href="#" class="list-group-item">Porta ac consectetur ac</a>
		  <a href="#" class="list-group-item">Vestibulum at eros</a>
		  
		  </div>
		  
		  <div class="row">
		  	<h1><span class="label label-primary terms-header">Total: 250</span></h1>
		  </div>
		  
		</div>
		
		  </div>
		</div>
		    </div>

		 <!-- Video detail column -->
    	<div class="col-md-9">
		<!-- Video Details Section -->
		<div class="panel panel-default">
		  <div class="panel-body">
		  
		  	<form class="form-horizontal">
				<fieldset>
				
					<!-- Form Name -->
					<legend>Agregar Término</legend>
					
					<!-- Text input-->
					<div class="form-group">
					  <label class="col-md-4 control-label" for="textinput">Término</label>  
					  <div class="col-md-4">
					  <input id="textinput" name="textinput" placeholder="placeholder" class="form-control input-md" type="text">
					  <span class="help-block">help</span>  
					  </div>
					</div>
					
					
					<!-- Select Basic -->
					<div class="form-group">
					  <label class="col-md-4 control-label" for="selectbasic">Categoría</label>
					  <div class="col-md-4">
					    <select id="selectbasic" name="selectbasic" class="form-control">
					    	<#list listCategories as category>
					     		<option value="${category.categoryName}">${category.categoryName}</option>
					     	 </#list>
					      
<!-- 					       <option value="1">Option one</option> -->
<!-- 					      <option value="2">Option two</option> -->
					      
					    </select>
					  </div>
					</div>
					
					<!-- Textarea -->
					<div class="form-group">
					  <label class="col-md-4 control-label" for="textarea">Definición</label>
					  <div class="col-md-4">                     
					    <textarea class="form-control" id="textarea" name="textarea">default text</textarea>
					  </div>
					</div>
					
					<!-- Textarea -->
					<div class="form-group">
					  <label class="col-md-4 control-label" for="textarea">Explicación | Ejemplos</label>
					  <div class="col-md-4">                     
					    <textarea class="form-control" id="textarea" name="textarea">default text</textarea>
					  </div>
					</div>
					
					<!-- Multiple Radios -->
					<div class="form-group">
					  <label class="col-md-4 control-label" for="radios">Tipo de Archivo</label>
					  <div class="col-md-4">
					  <div class="radio">
					    <label for="radios-0">
					      <input name="radios" id="radios-0" value="1" checked="checked" type="radio">
					      Video de YouTube
					    </label>
						</div>
					  <div class="radio">
					    <label for="radios-1">
					      <input name="radios" id="radios-1" value="2" type="radio">
					      Archivo Del Dispositivo
					    </label>
						</div>
					  </div>
					</div>
					
					<!-- Text input-->
					<div class="form-group">
					  <label class="col-md-4 control-label" for="textinput">URL</label>  
					  <div class="col-md-4">
					  <input id="textinput" name="textinput" placeholder="URL de YouTube" class="form-control input-md" type="text">
<!-- 					  <span class="help-block">help</span>   -->
					  </div>
					</div>
					
					<!-- File Button --> 
					<div class="form-group">
					  <label class="col-md-4 control-label" for="filebutton">Archivo</label>
					  <div class="col-md-4">
					    <input id="filebutton" name="filebutton" class="input-file" type="file">
					  </div>
					</div>
					
					<!-- Button (Double) -->
					<div class="form-group">
					  <label class="col-md-4 control-label" for="button1id">Acción</label>
					  <div class="col-md-8">
					    <button id="button1id" name="button1id" class="btn btn-success">Enviar</button>
					    <button id="button2id" name="button2id" class="btn btn-danger">Descartar</button>
					  </div>
					</div>
				
				</fieldset>
			</form>
		  	
			
		    
		  </div>
		</div>
  </div>
</div>
    
</@c.page>