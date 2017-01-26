jQuery(document).ready(function($) {
	
	
	
	$("#contactForm").submit(function(event) {
		// Disble the search button
		//enableSearchButton(false);

		// Prevent the form from submitting via the browser.
		event.preventDefault();
		submitdata();
	});
});


function submitdata() {

	  var contactName=document.getElementById( "contactName" );
	  var contactEmail=document.getElementById( "contactEmail" );
	  var contactSubject=document.getElementById( "contactSubject" );
	  var contactMessage=document.getElementById( "contactMessage" );
	  
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
	    url: "/DiccionarioLesco/contacto/enviarFormularioContacto",
	    data : JSON.stringify(search),
	    dataType : 'json',
	    success : function(data) {
			console.log("SUCCESS: ", data);
			//display(data);
			
			
			$('#contactModal').modal({
				backdrop: 'static'
			});
			
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
			enableSearchButton(true);
			
			//location.reload();
		}
	  });

	  //return false;

	}