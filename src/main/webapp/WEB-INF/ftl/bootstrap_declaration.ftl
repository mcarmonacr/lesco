<head>
	<title>Diccionario Lesco</title>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	
	<!--   Full JQuery UI is required to use the dialog function (In the admin section) -->
	<!--   <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script> -->
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	  
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	
	<script type="text/javascript" src="//cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
	  
	<!-- Global site js -->
	<script type="text/javascript" src="/lesco/resources/js/global.js"></script>
	
	<!-- Include Date Range Picker -->
	<script type="text/javascript" src="//cdn.jsdelivr.net/bootstrap.daterangepicker/2/daterangepicker.js"></script>
	<link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/bootstrap.daterangepicker/2/daterangepicker.css" />
	  
	  <link rel="stylesheet" type="text/css" href="/lesco/resources/css/common.css">
	
	<link rel="stylesheet" type="text/css" href="/lesco/resources/css/jumbotron.css">
	
	<!-- Font Awesome Icons -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

	<#if (parameters??)>
		<#if (parameters?size > 0)>
	<!-- 			Gets all the parameters that contain the css keyword in their string -->
			<#list parameters?values as v>
				<#if v?contains("css")>
					<link rel="stylesheet" type="text/css" href=${v}>
				</#if>
		    </#list>
		</#if>
	</#if>
</head>