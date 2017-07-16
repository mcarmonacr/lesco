<#import "common.ftl" as c />
<@c.page css="resources/css/admin.css" css2="resources/css/home.css"
js="resources/js/admin.js">

<!-- custom page content --> <!-- Whole content row -->
<div class="row" id="main-content">

	<!-- Terms column -->
	<div class="col-md-3">

		<div class="panel panel-default">
			<div class="panel-body">

				<!-- Terms Section -->
				<div class="list-group">

					<div class="row">
						<h2>
							<span class="header-black">Categorías</span>
						</h2>
					</div>

					<!-- 				<div class="row input-group search-text-box"> -->
					<!-- 					<span class="glyphicon glyphicon-search input-group-addon home-search-glyphicon"></span> -->
					<!-- 					<input id="requestInput" name="requestInput" type="text" class="form-control" placeholder="Buscar" aria-describedby="sizing-addon2"> -->
					<!-- 				</div> -->

					<div id="categoryListDiv" class="row terms-list">
						<!-- 		  <a href="#" class="list-group-item active"> -->
						<!-- 		    Cras justo odio -->
						<!-- 		  </a> -->

						<#--
						<#list requestList as request> <a
							onclick="loadRequestDetail(${request.requestId})" href="#"
							class="list-group-item">${request.wordName}</a> 
						</#list>
						<ul class="list-group">
							<#list listCategories as category>
							<li class="list-group-item list-group-item-info">${category.categoryName}</li>
							</#list>
						</ul>
						-->
						<#list listCategories as category> <a
							class="list-group-item"> <span id="${category.categoryId}"
							title="Eliminar" class="btn btn-sm btn-danger glyphicon glyphicon-trash confirmLink"></span>
							<span> ${category.categoryName}</span>
						</a> </#list>

					</div>
					<div class="row">
						<#--
						<h1>
							<span id="totalRequestsCounter"
								class="label label-primary terms-header">Total:
								${requestList?size}</span>
						</h1>
						-->
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="col-md-9">

		<div class="panel panel-default">
			<div class="panel-body">

				<form class="form-horizontal" name="categoryForm" id="categoryForm"
					enctype="multipart/form-data">
					<fieldset>

						<!-- Form Name -->
						<legend>Agregar Categoría</legend>

						<div class="form-group">
							<label for="category">Category:</label> <input type="text"
								class="form-control" id="categoryName" name="categoryName">
						</div>

						<!-- Button (Double) -->
						<div class="form-group">
							<!-- 										  <label class="col-md-4 control-label" for="button1id">Acción</label> -->
							<div class="col-md-6 col-md-offset-6">
								<button type="submit" class="btn btn-default">Guardar</button>
							</div>
						</div>
						
					</fieldset>
				</form>
			</div>
		</div>
	</div>

	<!-- This DIV gets automatically hidden by JQueryUI -->
	<div id="dialog" title="Confirmación Requerida">¿Desea eliminar
		esta categoría?</div>

</div>

</@c.page>