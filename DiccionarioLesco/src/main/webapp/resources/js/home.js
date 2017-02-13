jQuery(document).ready(function($) {
	
	$("#termsInput").keyup(function(){
		checkTerm();
	});

});

function assignCategory(category)
{
    $("#dropdownMenu1").html(category + '  <span class="caret"></span>');
}

function loadDetail(wordId)
{
    //$("#dropdownMenu1").html(category + '  <span class="caret"></span>');
	
	  var userName=document.getElementById("userName");
	  
	  var search = {
	            "wordId":wordId
	    }

	  $.ajax({
	  	headers: { 
	        'Accept': 'application/json',
	        'Content-Type': 'application/json' 
	    },
		type: 'post',
	    contentType : "application/json",
	    url: "/DiccionarioLesco/termino/obtenerTermino",
	    data : JSON.stringify(search),
	    dataType : 'json',
	    success : function(data) {
	    	console.log("SUCCESS: ", data);
	    	if(data != null && data.code == "000"){
	    		
	    		//console.log("Data: " + data.content.word);
	    		
	    		 $("#wordName").text(data.content.wordName);
	    		 $('#videoIframe').attr('src', "https://www.youtube.com/embed/" + data.content.youtubeVideoID + "?controls=1");
	    		 $("#definitionDiv").text(data.content.definition);
	    		 $("#explanationDiv").text(data.content.explanation);
	    		 $("#exampleDiv").text(data.content.example);
	    		 $("#numberOfVisitsSpan").text(data.content.numberOfVisits);
	    		 
	    		 
	    		
	    		
	    		//$('#divUserName').removeClass('has-error').addClass('has-success');
				//$('#divUserName .glyphicon').removeClass('glyphicon-remove').addClass('glyphicon-ok');
	    	}else {
	    		//$('#divUserName').removeClass('has-success').addClass('has-error');
				//$('#divUserName .glyphicon').removeClass('glyphicon-ok').addClass('glyphicon-remove');
	    		
	    		console.log("Data: " + data.content.word);
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

function checkTerm() {

	  var termsInput=document.getElementById("termsInput");
	  
	  var search = {
	            "termsInput":termsInput.value
	    }

	  $.ajax({
	  	headers: { 
	        'Accept': 'application/json',
	        'Content-Type': 'application/json' 
	    },
		type: 'post',
	    contentType : "application/json",
	    url: "/DiccionarioLesco/termino/obtenerListaTerminos",
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
