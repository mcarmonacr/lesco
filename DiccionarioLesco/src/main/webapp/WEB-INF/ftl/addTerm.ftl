<#import "common.ftl" as c/>
<@c.page css="resources/css/home.css" js="resources/js/addTerm.js">
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
	    	<h1><span class="label label-primary terms-header">Solicitudes</span></h1>
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
		  <a href="#" class="list-group-item">Agua</a>
		  <a href="#" class="list-group-item">Alajuela</a>
		  <a href="#" class="list-group-item">Amigo</a>
		  <a href="#" class="list-group-item">Boda</a>
		  <a href="#" class="list-group-item">Bosque</a>
		  <a href="#" class="list-group-item">Camisa</a>
		  <a href="#" class="list-group-item">Casa</a>
		  <a href="#" class="list-group-item">Despacio</a>
		  <a href="#" class="list-group-item">Enero</a>
		  <a href="#" class="list-group-item">Entender</a>
		  <a href="#" class="list-group-item">Equipo</a>
		  <a href="#" class="list-group-item">Gripe</a>
		  <a href="#" class="list-group-item">Hotel</a>
		  <a href="#" class="list-group-item">Importante</a>
		  <a href="#" class="list-group-item">Letra</a>
		  <a href="#" class="list-group-item">Nunca</a>
		  
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
		  
		  	<form class="form-horizontal" name="addTermForm" id="addTermForm" enctype="multipart/form-data">
				<fieldset>
				
					<!-- Form Name -->
					<legend>Agregar Término</legend>
					
					<!-- Text input-->
					<div class="form-group">
					  <label class="col-md-4 control-label" for="textinput">Término</label>  
					  <div class="col-md-4">
					  <input id="wordName" name="wordName" placeholder="Término" class="form-control input-md" type="text" required="required">
					  <input id="filePath" name="filePath" class="input-file" type="file" accept="video/*" required="required">
<!-- 					  <span class="help-block">help</span>   -->
					  </div>
					</div>
					
					
					<!-- Select Basic -->
					<div class="form-group">
					  <label class="col-md-4 control-label" for="categoryName">Categoría</label>
					  <div class="col-md-4">
					    <select id="categoryName" name="categoryName" class="form-control">
					    	<option value=""></option>
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
					  <div class="col-md-6">                     
					    <textarea class="form-control" placeholder="Definición" id="definition" name="definition" required="required"></textarea>
					    <input id="definitionFilePath" name="definitionFilePath" class="input-file" type="file" accept="video/*">
					  </div>
					</div>
										
					<!-- Textarea -->
					<div class="form-group">
					  <label class="col-md-4 control-label" for="textarea">Explicación</label>
					  <div class="col-md-6">                     
					    <textarea class="form-control" id="explanation" placeholder="Explicación" name="explanation"></textarea>
					    <input id="explanationFilePath" name="explanationFilePath" class="input-file" type="file" accept="video/*">
					  </div>
					</div>
					
					<!-- Textarea -->
					<div class="form-group">
					  <label class="col-md-4 control-label" for="textarea">Ejemplos</label>
					  <div class="col-md-6">                     
					    <textarea class="form-control" id="example" placeholder="Ejemplos" name="example"></textarea>
					    <input id="examplesFilePath" name="examplesFilePath" class="input-file" type="file" accept="video/*">
					  </div>
					</div>
										
<!-- 					Multiple Radios -->
<!-- 					<div class="form-group"> -->
<!-- 					  <label class="col-md-4 control-label" for="radios">Tipo de Archivo</label> -->
<!-- 					  <div class="col-md-4"> -->
<!-- 					  <div class="radio"> -->
<!-- 					    <label for="youtubeType"> -->
<!-- 					      <input name="youtubeType" id="youtubeType" value="1" checked="checked" type="radio"> -->
<!-- 					      Video de YouTube -->
<!-- 					    </label> -->
<!-- 						</div> -->
<!-- 					  <div class="radio"> -->
<!-- 					    <label for="fileType"> -->
<!-- 					      <input name="fileType" id="fileType" value="2" type="radio" disabled="disabled"> -->
<!-- 					      Archivo Del Dispositivo -->
<!-- 					    </label> -->
<!-- 						</div> -->
<!-- 					  </div> -->
<!-- 					</div> -->
					
					<!-- Text input-->
<!-- 					<div class="form-group"> -->
<!-- 					  <label class="col-md-4 control-label" for="videoURL">URL</label>   -->
<!-- 					  <div class="col-md-5"> -->
<!-- 					  <input id="videoURL" name="videoURL" placeholder="URL de YouTube" class="form-control input-md" type="text"> -->
<!-- 					  <span class="help-block">help</span>   -->
<!-- 					  </div> -->
<!-- 					</div> -->
					
					
					
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
		  	
		  	<div class="row">
		    	<div id="addTermModal" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
				  <div class="modal-dialog modal-md" role="document">
				    <div class="modal-content text-center">
				      ¡Término Agregado Exitosamente!
				      <div class="modal-footer">
				        <div class="row">
				        	<div class="col-md-5 text-center">
				        		<a href="/DiccionarioLesco/agregar" class="btn btn-info" role="button">Agregar Nuevo Término</a>
				        	</div>
				        	<div class="col-md-2"></div>
					        <div class="col-md-5 text-center">
					        	<a href="/DiccionarioLesco/" class="btn btn-info" role="button">Página Principal</a>
					        </div>
				        </div>
				      </div>
				    </div>
				  </div>
				</div>
    		</div> <!-- row div -->

		  </div>
		</div>
  </div>
  
	<!-- Common loading modal -->
	<#include "/loadingModal.ftl">
  
</div>
    
</@c.page>