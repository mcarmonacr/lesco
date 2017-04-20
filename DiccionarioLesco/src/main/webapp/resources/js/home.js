jQuery(document).ready(function($) {
	
	$("#termsInput").keyup(function(){
		checkTerm();
	});

});

function assignCategory(category, categoryId)
{
    $("#dropdownMenu1").html('<span class="glyphicon glyphicon-tasks"></span> '+ category +' <span class="caret"></span>' + '<div id="categoryIdDiv" hidden>' + categoryId+ '</div>');
    //$("#categoryIdDiv").text(categoryId);
    
    //Check the term lists
    checkTerm();
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

	  var termsInput= document.getElementById("termsInput");
	  var categoryIdDiv= document.getElementById("categoryIdDiv");
	  
	  var search= {
	            "termsInput":termsInput.value,
	            "categoryIdDiv":categoryIdDiv.textContent
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
	    		//$('#divUserName').removeClass('has-error').addClass('has-success');
				//$('#divUserName .glyphicon').removeClass('glyphicon-remove').addClass('glyphicon-ok');
	    		updateTermsList(data.content.wordsList);
	    	}else if(data != null && data.code == "001"){
	    		//$('#divUserName').removeClass('has-success').addClass('has-error');
				//$('#divUserName .glyphicon').removeClass('glyphicon-ok').addClass('glyphicon-remove');
	    		var wordListDiv= $("#wordListDiv");
	    		wordListDiv.children().remove();
	    		
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

function updateTermsList(wordList){
	
	//Get the ID wordListDiv
	var wordListDiv= $("#wordListDiv");
	
	//Remove all anchors form the wordListDiv
	wordListDiv.children().remove();
	
	//Insert the new set of word from the query
	for (index = 0; index < wordList.length; index++) {
		var anchor= $("<a>");
		anchor.attr("href", "#");
		anchor.attr("onclick", "loadDetail("+ wordList[index].wordId +")");
		anchor.addClass("list-group-item");
		anchor.text(wordList[index].wordName);
		
		wordListDiv.append(anchor);
	}
	
	//Update the total terms counter
	var totalTermsCounter= $("#totalTermsCounter");
	
	totalTermsCounter.text("Total: " + wordList.length);
	
	
	//totalTermsCounter
		
	
	//wordListDiv
	
	//<a onclick="loadDetail(${word.wordId})" href="#" class="list-group-item">${word.wordName}</a>
	
}
