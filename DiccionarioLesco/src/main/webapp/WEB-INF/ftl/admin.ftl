<#import "common.ftl" as c/>
<@c.page css="resources/css/admin.css" js="resources/js/admin.js">
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

    
</@c.page>