
	<footer id="siteFooter" class="navbar navbar-default navbar-fixed-bottom">
		<div class="container-fluid">
			<div class="row footer-content">
				<!-- 			  	<hr> -->
				<div class="col-lg-12">
					<div class="col-md-8 col-sm-12">
						<a data-toggle="modal" data-target="#termsAndConditionsModal">T�rminos Y
							Condiciones</a> | <a data-toggle="modal" data-target="#privacyModal">Privacidad</a>
						<!-- 			        <a href="/lesco/legal/terminos-de-servicio">T�rminos Y Condiciones</a> | <a href="/lesco/legal/privacidad">Privacidad</a> -->
					</div>
					<div class="col-md-4 col-sm-12">
						<p class="muted">� ${year} Diccionario Lesco. Todos
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


<!-- Common loading modal -->
<#include "/legal/privacyModal.ftl">
<#include "/legal/termsAndConditionsModal.ftl">