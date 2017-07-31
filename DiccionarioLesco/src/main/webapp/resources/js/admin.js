jQuery(document).ready(function($) {
	
	$("#categoryForm").submit(function(event) {
		// Disable the search button
		//enableSearchButton(false);

		// Prevent the form from submitting via the browser.
		event.preventDefault();
		submitdata();
	});
	
	//This dialog is automatically hidden by JQuery
	$("#dialog").dialog({
	      autoOpen: false,
	      modal: true
	    });
	
	//Confirmation dialog that pops when the user attempts to delete a category	
	$(".confirmLink").click(function(e) {
	    e.preventDefault();
	    var categoryId = $(this).attr("id");

	    $("#dialog").dialog({
	      buttons : {
	        "Confirm" : function() {
	        	//If the user confirms, then the category should be deleted
	        	deleteCategory(categoryId);
	        },
	        "Cancel" : function() {
	          $(this).dialog("close");
	        }
	      }
	    });

	    $("#dialog").dialog("open");
	  });
	
});

function deleteCategory(categoryId){

	//Search JSON string
	var search = {
            "categoryId":categoryId
    }

	  $.ajax({
	  	headers: { 
	        'Accept': 'application/json',
			'Content-Type': 'application/json' 
			},
			type: 'post',
			contentType : "application/json",
			url: "/DiccionarioLesco/admin/eliminarCategoria",
			data : JSON.stringify(search),
			dataType : 'json',
			success : function(data) {
				console.log("SUCCESS: ", data);
				//display(data);
				//Updas the page so the change is reflected in the list of categories
				location.reload();
			},
			error : function(e) {
				console.log("ERROR: ", e);
				//display(e);
				location.reload();
			},
			done : function(e) {
				console.log("DONE");
				//enableSearchButton(true);
				location.reload();
			}
	  });
	
}

/**
 * Add category form event
 * 
 * @returns nothing
 */
function submitdata() {

	//Get the category element
	var categoryName=document.getElementById( "categoryName" );
	  
	//Form the search JSON string
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
			//enableSearchButton(true);
			location.reload();
		}
	});
	  //return false;
}