jQuery(document).ready(function($) {
	
	//Contact form submission function
	$("#contactForm").submit(function(event) {
		// Disble the search button
		//enableSearchButton(false);

		// Prevent the form from submitting via the browser.
		event.preventDefault();
		submitdata();
	});
});

/**
 * Send contact message function
 * 
 * @returns nothing
 */
function submitdata() {

		//Get the data inputs
	  	var contactName=document.getElementById( "contactName" );
	  	var contactEmail=document.getElementById( "contactEmail" );
	  	var contactSubject=document.getElementById( "contactSubject" );
	  	var contactMessage=document.getElementById( "contactMessage" );
	  
	  	//Create the JSON string
	  	var search = {
	            "contactName":contactName.value,
	            "contactEmail":contactEmail.value,
	            "contactSubject":contactSubject.value,
	            "contactMessage":contactMessage.value
	    }

	  	$.ajax({
			  	headers: { 
			        'Accept': 'application/json',
			    'Content-Type': 'application/json' 
			},
			type: 'post',
			contentType : "application/json",
			url: "/lesco/contacto/enviarFormularioContacto",
			data : JSON.stringify(search),
			dataType : 'json',
			success : function(data) {
				console.log("SUCCESS: ", data);
				//display(data);
							
				//Sets the contact modal as static, so it does not vanishes if click outside
				$('#contactModal').modal({
					backdrop: 'static'
				});
				
				//Shows the contact modal
				$('#contactModal').modal('show');
			
				//location.reload();
			},
			error : function(e) {
				console.log("ERROR: ", e);
				//display(e);
				location.reload();
			},
			done : function(e) {
				console.log("DONE");
				//enableSearchButton(true);
				//location.reload();
			}
		});
	  //return false;
	}