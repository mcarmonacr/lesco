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
	    	<h1><span class="label label-primary terms-header">Términos</span></h1>
	    </div>
	    
	    <div class="row input-group search-text-box">
		  <span class="glyphicon glyphicon-search input-group-addon home-search-glyphicon"></span>
<!-- 		   <span class="input-group-addon" id="basic-addon1">@</span> -->
		  <input type="text" class="form-control" placeholder="Buscar" aria-describedby="sizing-addon2">
		</div>
	    
	    <div class="row dropdown dropdown-container-home">
		  <button class="btn btn-default dropdown-toggle dropdown-button-home" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
		    Categoría
		    <span class="caret"></span>
		  </button>
		  <ul class="dropdown-menu dropdown-ul-home" aria-labelledby="dropdownMenu1">
		  <#list listCategories as category>
<!-- 	    	<li class="list-group-item list-group-item-info"><a href="#">${category.categoryName}</a></li> -->
				<li onclick="assignCategory($(this).text())"><a href="#">${category.categoryName}</a></li>
				
<!-- 				<li onclick="assignCategory(this.value)" id="1">1</li> -->
	  	</#list>
<!-- 		    <li><a href="#">Action</a></li> -->
<!-- 		    <li><a href="#">Another action</a></li> -->
<!-- 		    <li><a href="#">Something else here</a></li> -->
<!-- 		    <li role="separator" class="divider"></li> -->
<!-- 		    <li><a href="#">Separated link</a></li> -->
		  </ul>
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
				
				</fieldset>
			</form>
		  	
			
		    
		  </div>
		</div>
  </div>
</div>
    
</@c.page>