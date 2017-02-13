<!--   <div class ="footer"> -->
	 
	 <div  class="row" id="footer">
		<footer class="navbar navbar-default navbar-fixed-bottom">
			<div class="container-fluid">
			  <div class="row">
<!-- 			  	<hr> -->
			    <div class="col-lg-12">
			      <div class="col-md-8">
			        <a href="/DiccionarioLesco/legal/terminos-de-servicio">Términos Y Condiciones</a> | <a href="/DiccionarioLesco/legal/privacidad">Privacidad</a>    
			      </div>
			      <div class="col-md-4">
			        <p class="muted pull-right">© ${year} Diccionario Lesco. Todos los derechos reservados</p>
			      </div>
			    </div>
			  </div>
			 </div>
		</footer>  
	
	<!-- For performance it is a good practice to place the scripts at the end of the page -->
	
	<#if (parameters??)>
		<#if (parameters?size > 0)>
			<#if parameters.js??>
		 	 	 <script type="text/javascript" src=${parameters.js}></script>
			</#if>
		</#if>
	</#if>

	
</div>