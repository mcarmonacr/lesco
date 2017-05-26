jQuery(document).ready(function($) {

	$("#addTermForm").submit(function(event) {
		event.preventDefault();
		submitForm();

	});
	
	$("#requestTermForm").submit(function(event) {
		event.preventDefault();
		submitRequestForm();

	});

	$("#userName").keyup(function(){
		checkUserName();
	});
	
	$("#emailAddress").keyup(function(){
		checkEmailAddress();
	});
	
});


function checkUserName() {

	  var userName=document.getElementById("userName");
	  
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
	    url: "/DiccionarioLesco/registro/verificarUsuario",
	    data : JSON.stringify(search),
	    dataType : 'json',
	    success : function(data) {
	    	console.log("SUCCESS: ", data);
	    		    	
	    	if(data != null && data.code == "000"){
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
			//console.log("DONE");
			//enableSearchButton(true);
		}
	  });
	  //return false;
}


function submitForm() {

	$('#processing-modal').modal({
		backdrop: 'static'
	});
	
	$('#processing-modal').modal('show');
	
	  var formData= new FormData();
	
	  var wordName=document.getElementById("wordName");
	  var categoryName=document.getElementById("categoryName");
	  var definition=document.getElementById("definition");
	  var explanation=document.getElementById("explanation");
	  var example=document.getElementById("example");
	  //var termsAndConditions=document.getElementById("youtubeType");
	  //var termsAndConditions=document.getElementById("fileType");
	  //var termsAndConditions=document.getElementById("videoURL");
	  var filePath=document.getElementById("filePath");
	  var definitionFilePath=document.getElementById("definitionFilePath");
	  var explanationFilePath=document.getElementById("explanationFilePath");
	  var examplesFilePath=document.getElementById("examplesFilePath");
	  
	  var formParameters = {
	            "wordName":wordName.value,
	            "categoryName":categoryName.value,
	            "definition":definition.value,
	            "explanation":explanation.value,
	            "example":example.value
//	            "youtubeType":youtubeType.checked,
//	            "fileType":fileType.checked,
//	            "videoURL":videoURL.checked,
//	            "filePath":filePath
	    }

	  formData.append("data", new Blob([JSON.stringify(formParameters)], { type: "application/json"})); 
	  formData.append("video", filePath.files[0]);
	  formData.append("definitionVideo", definitionFilePath.files[0]);
	  formData.append("explanationVideo", explanationFilePath.files[0]);
	  formData.append("exampleVideo", examplesFilePath.files[0]);
	  //formData.append("data", JSON.stringify(formParameters)); 
	  
	  //formData.serialize();

	  $.ajax({
//	  	headers: { 
//	        'Accept': 'application/json',
//	        'Content-Type': 'application/json' 
//	    },
		type: 'post',
	    //contentType : "application/json",
	    //url: "http://localhost:8080/DiccionarioLesco/registro/verificarUsuario",
	    url: "/DiccionarioLesco/termino/agregarTermino",
	    processData: false,
        contentType: false,
	    //contentType: 'multipart/form-data;boundary=----WebKitFormBoundary0XBBar2mAFEE8zbv',
        //headers: {'Content-Type': undefined},
	    data : formData,
	    //dataType : 'json',
	    success : function(data) {
			console.log("SUCCESS: ", data);
			//display(data);
			
			$('#processing-modal').modal('hide');
			
			$('#addTermModal').modal({
				backdrop: 'static'
			});
			
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

function submitRequestForm() {

	  var requestedWordName= document.getElementById("requestedWordName");
	  var requestedDescription= document.getElementById("requestedDescription");
	  
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
	    url: "/DiccionarioLesco/solicitud/agregarSolicitud",
	    data : JSON.stringify(search),
	    dataType : 'json',
	    success : function(data) {
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