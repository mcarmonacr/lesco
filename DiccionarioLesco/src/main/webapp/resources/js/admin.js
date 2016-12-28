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
	    url: "/DiccionarioLesco/admin/agregarCategoria",
	    data : JSON.stringify(search),
	    dataType : 'json',
	    success : function(data) {
			console.log("SUCCESS: ", data);
			//display(data);
			
			location.reload();
		},
		error : function(e) {
			console.log("ERROR: ", e);
			//display(e);
			
			location.reload();
		},
		done : function(e) {
			console.log("DONE");
			enableSearchButton(true);
			
			location.reload();
		}
	  });

	  //return false;

	}