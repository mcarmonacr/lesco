jQuery(document).ready(function($) {

	//Add user form event
	$("#addTermForm").submit(function(event) {
		event.preventDefault();
		submitForm();
	});
	
	//Add a new request form event
	$("#requestTermForm").submit(function(event) {
		event.preventDefault();
		submitRequestForm();
	});

	//User name input check of existence
	$("#userName").keyup(function(){
		checkUserName();
	});
	
	//Email address input check of existence
	$("#emailAddress").keyup(function(){
		checkEmailAddress();
	});
	
	//Request input check of existence
	$("#requestInput").keyup(function(){
		checkTerm();
	});
	
});

/**
 * Check the existence of the value in the input field userName
 * 
 * @returns nothing
 */
function checkUserName() {

	//Get the element from the DOM
	var userName=document.getElementById("userName");
	
	//Create the search JSON
	var search = {
            "userName":userName.value
    }

	$.ajax({
		headers: { 
	        'Accept': 'application/json',
		'Content-Type': 'application/json' 
		},
		type: 'post',
		contentType : "application/json",
		url: "/lesco/registro/verificarUsuario",
		data : JSON.stringify(search),
		dataType : 'json',
		success : function(data) {
			console.log("SUCCESS: ", data);
			
			//Check the data response code
			if(data != null && data.code == "000") {
			$('#divUserName').removeClass('has-error').addClass('has-success');
			$('#divUserName .glyphicon').removeClass('glyphicon-remove').addClass('glyphicon-ok');
			}else {
				$('#divUserName').removeClass('has-success').addClass('has-error');
			$('#divUserName .glyphicon').removeClass('glyphicon-ok').addClass('glyphicon-remove');
			}
		},
		error : function(e) {
			console.log("ERROR: ", e);
			//display(e);
		},
		done : function(e) {
			//console.log("DONE",e);
			//enableSearchButton(true);
		}
	});
	//return false;
}

/**
 * Add term form function
 * 
 * @returns nothing
 */
function submitForm() {

	//Sets the processing modal as static, so it does not vanishes if click outside
	$('#processing-modal').modal({
		backdrop: 'static'
	});
	
	//Shows the modal
	$('#processing-modal').modal('show');
	
	//Form data, which includes both, the videos and the text inputs
	var formData= new FormData();
	
	//Text inputs
	var wordName=document.getElementById("wordName");
	var categoryName=document.getElementById("categoryName");
	var definition=document.getElementById("definition");
	var explanation=document.getElementById("explanation");
	var example=document.getElementById("example");
	
	//Video inputs
	var filePath=document.getElementById("filePath");
	var definitionFilePath=document.getElementById("definitionFilePath");
	var explanationFilePath=document.getElementById("explanationFilePath");
	var examplesFilePath=document.getElementById("examplesFilePath");
	 
	//Forms the JSON of the text parameters
	var formParameters = {
		"wordName":wordName.value,
		"categoryName":categoryName.value,
		"definition":definition.value,
		"explanation":explanation.value,
		"example":example.value
	}

	//Appends the video and text data into the form data variable
	formData.append("data", new Blob([JSON.stringify(formParameters)], { type: "application/json"})); 
	formData.append("video", filePath.files[0]);
	formData.append("definitionVideo", definitionFilePath.files[0]);
	formData.append("explanationVideo", explanationFilePath.files[0]);
	formData.append("exampleVideo", examplesFilePath.files[0]);
	
	//formData.append("data", JSON.stringify(formParameters)); 	  
	//formData.serialize();

	$.ajax({
		//headers: { 
		//'Accept': 'application/json',
		//'Content-Type': 'application/json' 
		//},
		type: 'post',
		//contentType : "application/json",
		//url: "http://localhost:8080/lesco/registro/verificarUsuario",
		url: "/lesco/termino/agregarTermino",
		processData: false,
		contentType: false,
		//contentType: 'multipart/form-data;boundary=----WebKitFormBoundary0XBBar2mAFEE8zbv',
		//headers: {'Content-Type': undefined},
		data : formData,
		//dataType : 'json',
		success : function(data) {
			console.log("SUCCESS: ", data);
			//display(data);
			
			//Hides the processing modal
			$('#processing-modal').modal('hide');
			
			//Sets the add term modal as static, so it does not vanishes if click outside
			$('#addTermModal').modal({
				backdrop: 'static'
			});
			//Shows the add term modal
			$('#addTermModal').modal('show');
		},
		error : function(e) {
			console.log("ERROR: ", e);
			//display(e);
		},
		done : function(e) {
			console.log("DONE");
			//enableSearchButton(true);
		}
	});
	//return false;
}

/**
 * Adds a new request to the system
 * 
 * @returns
 */
function submitRequestForm() {

	//Get the word name and description elements
	var requestedWordName= document.getElementById("requestedWordName");
	var requestedDescription= document.getElementById("requestedDescription");
	  
	//Create the search cirteria JSON
	var search = {
			"wordName":requestedWordName.value,
			"description":requestedDescription.value
	}

	$.ajax({
	  	headers: { 
	        'Accept': 'application/json',
		'Content-Type': 'application/json' 
		},
		type: 'post',
		contentType : "application/json",
		url: "/lesco/solicitud/agregarSolicitud",
		data : JSON.stringify(search),
		dataType : 'json',
		success : function(data) {
			
			//Sets the request term modal as static, so it does not vanishes if click outside
			$('#requestTermModal').modal({
				backdrop: 'static'
			});
			
			//Shows the request term modal
			$('#requestTermModal').modal('show');
			
			console.log("SUCCESS: ", data);
			//display(data);
		},
		error : function(e) {
			console.log("ERROR: ", e);
		//display(e);
		},
		done : function(e) {
			console.log("DONE");
		//enableSearchButton(true);
			}
	});
	//return false;
}

/**
 * Load the request detail into the proper DOM elements
 * 
 * @param requestId
 * @returns nothing
 */
function loadRequestDetail(requestId) {
	var search = {
			"requestId":requestId
	}

	  $.ajax({
	  	headers: { 
	        'Accept': 'application/json',
	        'Content-Type': 'application/json' 
	    },
		type: 'post',
	    contentType : "application/json; charset:ISO-8859-1",
	    // This is the important part
	    beforeSend: function(jqXHR) {
	        jqXHR.overrideMimeType('text/html;charset=iso-8859-1');
	    },
	    responseType:"application/json;charset:ISO-8859-1",
	    //	    mimeType:"text/plain; charset=ISO-8859-1",
	    url: "/lesco/solicitud/obtenerSolicitud",
	    data : JSON.stringify(search),
	    dataType : 'json',
	    success : function(data) {
	    	console.log("SUCCESS: ", data);
	    	if(data != null && data.code == "000"){	    		
	    		console.log("Data: " + data);
	    		
	    		//This line: fixedstring=decodeURIComponent(escape(badstring)); converts a bad encoded string into the correct one
	    		$("#wordName").val(decodeURIComponent(escape(data.content.wordName)));
	    		//$('#videoIframe').attr('src', "https://www.youtube.com/embed/" + data.content.youtubeVideoID + "?controls=1");
	    		$("#definition").val(decodeURIComponent(escape(data.content.description)));
	    	}else {
	    		console.log("Data: " + data.content.word);
	    	}
		},
		error : function(e) {
			console.log("ERROR: ", e);
			//display(e);
		},
		done : function(e) {
			console.log("DONE", e);
			//enableSearchButton(true);
		}
	  });
	  //return false;
}

/**
 * Check if a term already exists
 * 
 * @returns nothing
 */
function checkTerm() {

	  var requestInput= document.getElementById("requestInput");
	  
	  var search= {
			  "requestInput":requestInput.value
	  }

	  $.ajax({
			headers: { 
			    'Accept': 'application/json',
			    'Content-Type': 'application/json' 
			},
			type: 'post',
			contentType : "application/json",
			url: "/lesco/solicitud/obtenerListaSolicitudes",
			data : JSON.stringify(search),
			dataType : 'json',
			success : function(data) {
				console.log("SUCCESS: ", data);
				if(data != null && data.code == "000"){
					updateRequestsList(data.content.requestsList);
				}else if(data != null && data.code == "001"){
					var requestListDiv= $("#requestListDiv");
					requestListDiv.children().remove();
					//Update the total terms counter
					var totalTermsCounter= $("#totalRequestsCounter");
					
					totalTermsCounter.text("Total: 0");
					
					//TO DO Update counter when there is an empty list
				} else {
					
				}
			},
			error : function(e) {
				console.log("ERROR: ", e);
				//display(e);
			},
			done : function(e) {
				//console.log("DONE");
				//enableSearchButton(true);
			}
	  });
	  //return false;
}

/**
 * Updates the list of request
 * 
 * @param requestList
 * @returns nothing
 */
function updateRequestsList(requestList){
	
	//Get the ID wordListDiv
	var requestListDiv= $("#requestListDiv");
	
	//Remove all anchors form the wordListDiv
	requestListDiv.children().remove();
	
	//Insert the new set of word from the query
	for (index = 0; index < requestList.length; index++) {
		var anchor= $("<a>");
		anchor.attr("href", "#");
		anchor.attr("onclick", "loadRequestDetail("+ requestList[index].requestId +")");
		anchor.addClass("list-group-item");
		anchor.text(decodeURIComponent(escape(requestList[index].wordName)));
		
		requestListDiv.append(anchor);
	}
	
	//Update the total terms counter
	var totalTermsCounter= $("#totalRequestsCounter");
	
	totalTermsCounter.text("Total: " + requestList.length);
}