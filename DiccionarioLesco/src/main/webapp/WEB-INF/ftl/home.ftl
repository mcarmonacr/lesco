<#import "common.ftl" as c/>
<@c.page css="resources/css/home.css" js="resources/js/home.js">
    <!-- custom page content -->
    
    <div id= "main-content">
    
    <div class="panel panel-default terms">
		  <div class="panel-body">

    	<!-- Terms Section -->
	    <div class="list-group">
	    
	    <h1><span class="label label-primary terms-header">Términos</span></h1>
	    
	    <div class="input-group search-text-box">
		  <span class="glyphicon glyphicon-search input-group-addon home-search-glyphicon"></span>
<!-- 		   <span class="input-group-addon" id="basic-addon1">@</span> -->
		  <input type="text" class="form-control" placeholder="Buscar" aria-describedby="sizing-addon2">
		</div>
	    
	    <div class="dropdown dropdown-container-home">
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

	    <div class="terms-list">
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
		  
		  <h1><span class="label label-primary terms-header">Total: 250</span></h1>
		  
		  
		</div>
		
		  </div>
		</div>
		
		<!-- Video Details Section -->
		<div class="panel panel-default video-details">
		  <div class="panel-body">
		    <h1><span class="label label-primary terms-header">Nombre del video</span></h1>
		    
		    <video class="video-container" controls>
			  <source src="resources/videos/Video.mp4" type="video/mp4">
<!-- 			  <source src="mov_bbb.ogg" type="video/ogg"> -->
<!-- 			  Your browser does not support HTML5 video. -->
			</video>
			
			<div class="panel-group">
			  <div class="panel panel-default">
			    <div class="panel-heading">
			      <h4 class="panel-title">
			        <a data-toggle="collapse" href="#collapse2">Definición</a>
			      </h4>
			    </div>
			    <div id="collapse2" class="panel-collapse collapse">
			      <div class="panel-body">Panel content Panel content Panel content Panel content Panel content Panel content</div>
<!-- 			      <div class="panel-footer">Panel Footer</div> -->
			    </div>
			  </div>
			</div>
			
			<div class="panel-group">
			  <div class="panel panel-default">
			    <div class="panel-heading">
			      <h4 class="panel-title">
			        <a data-toggle="collapse" href="#collapse1">Explicación | Ejemplos</a>
			      </h4>
			    </div>
			    <div id="collapse1" class="panel-collapse collapse">
			      <div class="panel-body">Panel content Panel content Panel content Panel content Panel content Panel content</div>
<!-- 			      <div class="panel-footer">Panel Footer</div> -->
			    </div>
			  </div>
			</div>
			
<!-- 			<div id="collapse2" class="panel-collapse collapse"> -->
<!-- 			  <div class="panel-heading"> -->
<!-- 			    <h3 class="panel-title">Definición</h3> -->
<!-- 			  </div> -->
<!-- 			  <div class="panel-body"> -->
<!-- 			    Panel content Panel content Panel content Panel content Panel content Panel content -->
<!-- 			  </div> -->
<!-- 			</div> -->
			
<!-- 			<div class="panel panel-primary" id="collapse3" class="panel-collapse collapse"> -->
<!-- 			  <div class="panel-heading"> -->
<!-- 			    <h3 class="panel-title">Explicación | Ejemplos</h3> -->
<!-- 			  </div> -->
<!-- 			  <div class="panel-body"> -->
<!-- 			    Panel content Panel content Panel content Panel content Panel content Panel content -->
<!-- 			  </div> -->
<!-- 			</div> -->

			<h2>
				<span class="label label-primary punctuation glyphicon glyphicon-star"> Puntuación: 4.5</span>
				<span class="label label-primary visits glyphicon glyphicon-eye-open"> Visitas: 35</span>
			</h2>
			
			
		    
		  </div>
		</div>

</div>
    
</@c.page>