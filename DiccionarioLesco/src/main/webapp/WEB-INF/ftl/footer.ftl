<div class="row" id="footer">
	<footer class="navbar navbar-default navbar-fixed-bottom">
		<div class="container-fluid">
			<div class="row">
				<!-- 			  	<hr> -->
				<div class="col-lg-12">
					<div class="col-md-8">
						<a data-toggle="modal" data-target="#myFooterModal">Términos Y
							Condiciones</a> | <a data-toggle="modal" data-target="#myFooterModal">Privacidad</a>
						<!-- 			        <a href="/DiccionarioLesco/legal/terminos-de-servicio">Términos Y Condiciones</a> | <a href="/DiccionarioLesco/legal/privacidad">Privacidad</a> -->
					</div>
					<div class="col-md-4">
						<p class="muted pull-right">© ${year} Diccionario Lesco. Todos
							los derechos reservados</p>
					</div>
				</div>
			</div>
		</div>
	</footer>

	<!-- For performance it is a good practice to place the scripts at the end of the page -->

	<#if (parameters??)> 
		<#if (parameters?size> 0)> <!-- 			Gets all the parameters that contain the css keyword in their string -->
			<#list parameters?values as v> 
				<#if v?contains("js")> 
					<script	type="text/javascript" src=${v}></script> 
				</#if> 
			</#list> 
		</#if> 
	</#if>
</div>

<!-- Common loading modal -->
<#include "/modal/termsAndConditionsModal.ftl">