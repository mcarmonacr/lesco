<#macro page parameters...>
	<!DOCTYPE html>
	<html lang="en">

<#-- <#assign css = css> -->

	


	<!-- Bootstrap configuration section -->
	<#include "/bootstrap_declaration.ftl">
	
	    <body>

	    
		    <div class="container-fluid">
			    <!-- header section -->
			    <#include "/header.ftl">			    
			    
			    <!-- Page content -->
			    <#nested/>
			    
			    <!-- footer section -->
			    <#include "/footer.ftl">
		    </div>
	    </body>
	    
	</html>
</#macro>