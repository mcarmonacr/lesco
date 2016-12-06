jQuery(document).ready(function($) {

	$("#addTermForm").submit(function(event) {
		submitform();

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


function submitform() {

	  var userName=document.getElementById("wordName");
	  var emailAddress=document.getElementById("categoryName");
	  var password=document.getElementById("definition");
	  var passwordConfirmation=document.getElementById("explanation");
	  var birthDate=document.getElementById("example");
	  var termsAndConditions=document.getElementById("youtubeType");
	  var termsAndConditions=document.getElementById("fileType");
	  var termsAndConditions=document.getElementById("videoURL");
	  var termsAndConditions=document.getElementById("filePath");
	  
	  var formData = {
	            "wordName":wordName.value,
	            "categoryName":categoryName.value,
	            "definition":definition.value,
	            "explanation":explanation.value,
	            "example":example.value,
	            "youtubeType":youtubeType.checked,
	            "fileType":fileType.checked,
	            "videoURL":videoURL.checked,
	            "filePath":filePath.checked
	    }

	  $.ajax({
	  	headers: { 
	        'Accept': 'application/json',
	        'Content-Type': 'application/json' 
	    },
		type: 'post',
	    contentType : "application/json",
	    //url: "http://localhost:8080/DiccionarioLesco/registro/verificarUsuario",
	    url: "/DiccionarioLesco/registro/agregarUsuario",
	    data : JSON.stringify(formData),
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