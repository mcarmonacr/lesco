<#import "common.ftl" as c/>
<@c.page css="resources/css/admin.css">
    <!-- custom page content -->
        
    <h2><span class="label label-default">Administración</span></h2>
    
    <h3><span class="label label-primary">Categorías</span></h3>
    
    
    <#-- <a href="#" class="list-group-item">${categoryName}</a> -->
    	
    <ul class="list-group">	
    <#list listCategories as category>
    	<li class="list-group-item list-group-item-info">${category.categoryName}</li>
  	</#list>
     </ul>
    
	<form name="categoryForm" id="categoryForm">
	  <div class="form-group">
	    <label for="category">Category:</label>
	    <input type="text" class="form-control" id="categoryName" name="categoryName">
	  </div>
	  <button type="submit" class="btn btn-default">Guardar</button>
	</form>
	
	<script type="text/javascript">
	
	jQuery(document).ready(function($) {

		$("#categoryForm").submit(function(event) {

			// Disble the search button
			//enableSearchButton(false);

			// Prevent the form from submitting via the browser.
			//event.preventDefault();

			submitdata();

		});

	});
	
	
	function submitdata() {

		  var categoryName=document.getElementById( "categoryName" );
		  
		  var search = {
		            "categoryName":categoryName.value
		    }

		  $.ajax({
		  	headers: { 
		        'Accept': 'application/json',
		        'Content-Type': 'application/json' 
		    },
			type: 'post',
		    contentType : "application/json",
		    url: "http://localhost:8080/DiccionarioLesco/admin/agregarCategoria",
		    data : JSON.stringify(search),
		    dataType : 'json',
		    success : function(data) {
				console.log("SUCCESS: ", data);
				display(data);
			},
			error : function(e) {
				console.log("ERROR: ", e);
				display(e);
			},
			done : function(e) {
				console.log("DONE");
				enableSearchButton(true);
			}
		  });

		  //return false;

		}
</script>
    
</@c.page>