<!-- <div class="page-header"> -->
<div class="row" id="header"> 
   <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container-fluid">
      
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="/DiccionarioLesco/">Diccionario Lesco</a>
        </div>
        
        <div id="navbar" class="navbar-collapse collapse">
	        <ul class="nav navbar-nav">
<!-- 	            <li class="active"><a href="#">Diccionario</a></li> -->
<!-- 	             <li><a href="#">Diccionario</a></li> -->
	            <li><a href="/DiccionarioLesco/acerca">Acerca Del Proyecto</a></li>
	            
	             <#if (userRole??)>
					<#if (isSessionValid == "true")>
						<li><a href="/DiccionarioLesco/agregar">Agregar Término</a></li>
					</#if>
				</#if>
	            
	            <li><a href="/DiccionarioLesco/contacto">Contacto</a></li>
	            
	            <#if (isSessionValid??)>
					<#if (userRole == "administrator")>
						<li><a href="/DiccionarioLesco/admin">Admin</a></li>
					</#if>
				</#if>
	            
	            
<!-- 	            <li class="dropdown"> -->
<!-- 	              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a> -->
<!-- 	              <ul class="dropdown-menu"> -->
<!-- 	                <li><a href="#">Action</a></li> -->
<!-- 	                <li><a href="#">Another action</a></li> -->
<!-- 	                <li><a href="#">Something else here</a></li> -->
<!-- 	                <li role="separator" class="divider"></li> -->
<!-- 	                <li class="dropdown-header">Nav header</li> -->
<!-- 	                <li><a href="#">Separated link</a></li> -->
<!-- 	                <li><a href="#">One more separated link</a></li> -->
<!-- 	              </ul> -->
<!-- 	            </li> -->
	          </ul>
	        
	       
	        <#if (isSessionValid??)>
				<#if (isSessionValid == "true")>
					<#if (userName??)>
					<div class="navbar-form navbar-right">
						<strong class="text-primary">Bienvenido, ${userName}</strong>
<!-- 						<img class="img-circle" src="resources/images/marioCarmona2.PNG" alt="Mario Alonso Carmona Dinarte" width="60" height="60"> -->
							<p class="text-primary">Salir</p>
					</div>
						
					</#if>
				</#if>
			<#else>
				<form class="navbar-form navbar-right" id="loginForm" name="loginForm">
		            <div class="form-group">
		              <input type="text" placeholder="Email" class="form-control" id="loginEmailAddress" name="loginEmailAddress">
		            </div>
		            <div class="form-group">
		              <input type="password" placeholder="Password" class="form-control" id="loginPassword" name="loginPassword">
		            </div>
		            <button type="submit" class="btn btn-primary">Ingresar</button>
	<!-- 	            <button type="submit" class="btn btn-success" href="/registrarse/">Registrarse</button> -->
		            <a href="/DiccionarioLesco/registrarse" class="btn btn-primary">
				      <span class="glyphicon glyphicon-user"></span> Registrarse 
				    </a>
	          </form>
			</#if>
	        
	          
	          
        </div><!--/.navbar-collapse -->
      </div>
    </nav>
    <!-- Space between the header and the content -->
    <br>
</div>