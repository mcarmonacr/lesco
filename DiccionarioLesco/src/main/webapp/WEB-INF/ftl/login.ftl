<#import "common.ftl" as c />
<@c.page css="resources/css/home.css" js="resources/js/home.js">
<!-- custom page content -->

<!-- Whole content row -->
<div class="row" id="main-content">
	<div class="jumbotron">
		<div class="text-center">
			<i class="fa fa-5x fa-exclamation-triangle" style="color: #d9534f;"></i>
		</div>
		<h1 class="text-center">
			Contenido Restringido
			<p></p>
			<!--         	<p><small class="text-center"> Oh noes everything broke</small></p> -->
		</h1>
		<p class="text-center">Debe estar autenticado para poder accesar
			el contenido solicitado.</p>
		<p class="text-center">
			<a class="btn btn-primary" href="/lesco/"><i
				class="fa fa-home"></i> Diccionario Lesco</a>
		</p>
	</div>
</div>

</@c.page>
