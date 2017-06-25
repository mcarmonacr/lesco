jQuery(document).ready(function($) {
	
	$("#termsInput").keyup(function(){
		checkTerm();
	});
	
	$("#myTermsInput").keyup(function(){
		checkMyTerm();
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
	    		
	    		loadTermDetails(data.content);

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


function loadTermDetails(content){ 
	
	 $("#wordName").text(content.wordName);
	 $('#termSourceURLIframe').attr('src', "https://www.youtube.com/embed/" + content.termYoutubeVideoID + "?controls=1");
	 updateVideoRatingMetadata(content.termYoutubeVideoID, content.termVideoRating, content.termVideoMetadata);
	 
	 $("#definitionDiv").text(data.content.definition);
	 $('#definitionSourceURLIframe').attr('src', "https://www.youtube.com/embed/" + content.definitionYoutubeVideoID + "?controls=1");
	 updateVideoRatingMetadata(content.definitionYoutubeVideoID, content.definitionVideoRating, content.definitionVideoMetadata);
	 
	 $("#explanationDiv").text(data.content.explanation);
	 $('#explanationSourceURLIframe').attr('src', "https://www.youtube.com/embed/" + content.explanationYoutubeVideoID + "?controls=1");
	 updateVideoRatingMetadata(content.explanationYoutubeVideoID, content.explanationVideoRating, content.explanationVideoMetadata);
	 
	 $("#exampleDiv").text(data.content.example);
	 $('#exampleSourceURLIframe').attr('src', "https://www.youtube.com/embed/" + content.exampleYoutubeVideoID + "?controls=1");
	 updateVideoRatingMetadata(content.exampleYoutubeVideoID, content.exampleVideoRating, content.exampleVideoMetadata);
	 
	 //$("#numberOfVisitsSpan").text(data.content.numberOfVisits);	
}

function togglePreferred(wordId) {
    //$("#dropdownMenu1").html(category + '  <span class="caret"></span>');
	
	  var spanElement=$("#span-"+wordId);
	  
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
	    url: "/DiccionarioLesco/termino/agregarPreferido",
	    data : JSON.stringify(search),
	    dataType : 'json',
	    success : function(data) {
	    	console.log("SUCCESS: ", data);
	    	if(data != null && data.code == "000"){
	    		
	    		//If is true, then it should be now a favorite term
	    		if(data.content.isOneOfMyFavoriteTerms == true){
	    			$("#span-"+wordId).removeClass('fa-star-o').addClass('fa-star');
	    		} 
	    		if(data.content.isOneOfMyFavoriteTerms == false) {
	    			$("#span-"+wordId).removeClass('fa-star').addClass('fa-star-o');
	    		}
	    		
	    		//Update my Terms section
	    		updateMyTermsList(data.content.listMyWords);
	    		
	    	}else {
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
	    		updateTermsList(data.content.wordsList, data.content.myWordsList, data.content.isSessionValid);
	    	}else if(data != null && data.code == "001"){
	    		//$('#divUserName').removeClass('has-success').addClass('has-error');
				//$('#divUserName .glyphicon').removeClass('glyphicon-ok').addClass('glyphicon-remove');
	    		var wordListDiv= $("#wordListDiv");
	    		wordListDiv.children().remove();
	    		
	    		//Update the total terms counter
	    		var totalTermsCounter= $("#totalTermsCounter");
	    		
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

function checkMyTerm() {

	  var termsInput= document.getElementById("myTermsInput");
	  var categoryIdDiv= document.getElementById("myCategoryIdDiv");
	  
	  var search= {
	            "myTermsInput":myTermsInput.value,
	            "myCategoryIdDiv":myCategoryIdDiv.textContent
	    }

	  $.ajax({
	  	headers: { 
	        'Accept': 'application/json',
	        'Content-Type': 'application/json' 
	    },
		type: 'post',
	    contentType : "application/json",
	    url: "/DiccionarioLesco/termino/obtenerListaMisTerminos",
	    data : JSON.stringify(search),
	    dataType : 'json',
	    success : function(data) {
	    	console.log("SUCCESS: ", data);
	    	if(data != null && data.code == "000"){
	    		//$('#divUserName').removeClass('has-error').addClass('has-success');
				//$('#divUserName .glyphicon').removeClass('glyphicon-remove').addClass('glyphicon-ok');
	    		updateMyTermsList(data.content.myWordsList);
	    	}else if(data != null && data.code == "001"){
	    		//$('#divUserName').removeClass('has-success').addClass('has-error');
				//$('#divUserName .glyphicon').removeClass('glyphicon-ok').addClass('glyphicon-remove');
	    		var myWordListDiv= $("#myWordListDiv");
	    		myWordListDiv.children().remove();
	    		
	    		//Update the total terms counter
	    		var myTotalTermsCounter= $("#myTotalTermsCounter");
	    		
	    		myTotalTermsCounter.text("Total: 0");
	    		
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

function updateTermsList(wordList, myWordList, isSessionValid){
	
	//Get the ID wordListDiv
	var wordListDiv= $("#wordListDiv");
	
	//Remove all anchors form the wordListDiv
	wordListDiv.children().remove();
	
	//Insert the new set of word from the query
	for (index = 0; index < wordList.length; index++) {
		var anchor= $("<a>");
		anchor.addClass("list-group-item");
		
		if(isSessionValid){
			
			if(myWordList.length > 0) {
				for (myIndex = 0; myIndex < myWordList.length; myIndex++) {
					
					if(wordList[index].wordName == myWordList[myIndex].wordName){
						var spanFavorite= $("<span>");
						spanFavorite.attr("href", "#");
						spanFavorite.attr("onclick", "togglePreferred("+ wordList[index].wordId +")");
						spanFavorite.attr("title", "Deshacer Favorito");
						spanFavorite.attr("id", "span-"+wordList[index].wordId);
						spanFavorite.attr("class", "fa fa-lg fa-star pull-left");
						
						var spanDetail= $("<span>");
						spanDetail.attr("href", "#");
						spanDetail.attr("onclick", "loadDetail("+ wordList[index].wordId +")");
						spanDetail.attr("title", "Cargar Detalle");
						spanDetail.text(wordList[index].wordName);
						
						anchor.append(spanFavorite);
						anchor.append(spanDetail);
					} else {
						var spanFavorite= $("<span>");
						spanFavorite.attr("href", "#");
						spanFavorite.attr("onclick", "togglePreferred("+ wordList[index].wordId +")");
						spanFavorite.attr("title", "Hacer Favorito");
						spanFavorite.attr("id", "span-"+wordList[index].wordId);
						spanFavorite.attr("class", "fa fa-lg fa-star-o pull-left");
						
						var spanDetail= $("<span>");
						spanDetail.attr("href", "#");
						spanDetail.attr("onclick", "loadDetail("+ wordList[index].wordId +")");
						spanDetail.attr("title", "Cargar Detalle");
						spanDetail.text(wordList[index].wordName);
						
						anchor.append(spanFavorite);
						anchor.append(spanDetail);
					}
				}
			} else { //Case where the user is logged in, but does not have favorites
				var spanFavorite= $("<span>");
				spanFavorite.attr("href", "#");
				spanFavorite.attr("onclick", "togglePreferred("+ wordList[index].wordId +")");
				spanFavorite.attr("title", "Hacer Favorito");
				spanFavorite.attr("id", "span-"+wordList[index].wordId);
				spanFavorite.attr("class", "fa fa-lg fa-star-o pull-left");
				
				var spanDetail= $("<span>");
				spanDetail.attr("href", "#");
				spanDetail.attr("onclick", "loadDetail("+ wordList[index].wordId +")");
				spanDetail.attr("title", "Cargar Detalle");
				spanDetail.text(wordList[index].wordName);
				
				anchor.append(spanFavorite);
				anchor.append(spanDetail);
			}

		}else{
			var spanDetail= $("<span>");
			spanDetail.attr("href", "#");
			spanDetail.attr("onclick", "loadDetail("+ wordList[index].wordId +")");
			spanDetail.attr("title", "Cargar Detalle");
			spanDetail.text(wordList[index].wordName);
			
			anchor.append(spanDetail);
		}

		wordListDiv.append(anchor);
	}
	
	//Update the total terms counter
	var totalTermsCounter= $("#totalTermsCounter");
	
	totalTermsCounter.text("Total: " + wordList.length);
	
	
	//totalTermsCounter
		
	
	//wordListDiv
	
	//<a onclick="loadDetail(${word.wordId})" href="#" class="list-group-item">${word.wordName}</a>
	
}

function updateMyTermsList(myWordList){
	
	//Get the ID wordListDiv
	var myWordListDiv= $("#myWordListDiv");
	
	//Remove all anchors form the wordListDiv
	myWordListDiv.children().remove();
	
	//Insert the new set of word from the query
	for (index = 0; index < myWordList.length; index++) {
		var anchor= $("<a>");
		anchor.attr("href", "#");
		anchor.attr("onclick", "loadDetail("+ myWordList[index].wordId +")");
		anchor.addClass("list-group-item");
		anchor.text(myWordList[index].wordName);
		
		myWordListDiv.append(anchor);
	}
	
	//Update the total terms counter
	var myTotalTermsCounter= $("#myTotalTermsCounter");
	
	myTotalTermsCounter.text("Total: " + myWordList.length);
	
	
	//totalTermsCounter
		
	
	//wordListDiv
	
	//<a onclick="loadDetail(${word.wordId})" href="#" class="list-group-item">${word.wordName}</a>
	
}

function rateVideo(videoId, action){
	var termsInput= document.getElementById("myTermsInput");
	var categoryIdDiv= document.getElementById("myCategoryIdDiv");
	
	var hasLike= $('#spanLike-'+videoId).hasClass("fa-thumbs-up");
	var hasDislike= $('#spanDislike-'+videoId).hasClass("fa-thumbs-down");
	  
	var search= {
	            "videoId":videoId,
	            "action":action
	    }

	  $.ajax({
	  	headers: { 
	        'Accept': 'application/json',
	        'Content-Type': 'application/json' 
	    },
		type: 'post',
	    contentType : "application/json",
	    url: "/DiccionarioLesco/termino/evaluarVideo",
	    data : JSON.stringify(search),
	    dataType : 'json',
	    success : function(data) {
	    	console.log("SUCCESS: ", data);
	    	if(data != null && data.code == "000"){
	    		//$('#divUserName').removeClass('has-error').addClass('has-success');
				//$('#divUserName .glyphicon').removeClass('glyphicon-remove').addClass('glyphicon-ok');
	    		
	    		//updateMyTermsList(data.content.myWordsList);
	    		
	    		updateVideoRatingMetadata(videoId, data.content.videoRating.rating, data.content.videoMetadata);
	    		    		    		
	    	}else if(data != null && data.code == "001"){
	    		//$('#divUserName').removeClass('has-success').addClass('has-error');
				//$('#divUserName .glyphicon').removeClass('glyphicon-ok').addClass('glyphicon-remove');
	    		
	    		//var myWordListDiv= $("#myWordListDiv");
	    		//myWordListDiv.children().remove();
	    		
	    		//Update the total terms counter
	    		//var myTotalTermsCounter= $("#myTotalTermsCounter");
	    		
	    		//myTotalTermsCounter.text("Total: 0");
	    		
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

//Updates the likes, the icons related and the number of view of a video
function updateVideoRatingMetadata(videoId, videoRating, videoMetadata){
	if(videoRating == "like"){
		$('#spanLike-'+videoId).removeClass('fa-thumbs-o-up').addClass('fa-thumbs-up');
		$('#spanDislike-'+videoId).removeClass('fa-thumbs-down').addClass('fa-thumbs-o-down');
	} else if (videoRating == "dislike"){
		$('#spanLike-'+videoId).removeClass('fa-thumbs-up').addClass('fa-thumbs-o-up');
		$('#spanDislike-'+videoId).removeClass('fa-thumbs-o-down').addClass('fa-thumbs-down');
	} else { //Default case where the rating is none
		$('#spanLike-'+videoId).removeClass('fa-thumbs-up').addClass('fa-thumbs-o-up');
		$('#spanDislike-'+videoId).removeClass('fa-thumbs-down').addClass('fa-thumbs-o-down');
	}
	
	if(videoMetadata.statistics.likeCount != null && videoMetadata.statistics.dislikeCount != null){
		$('#spanLike-'+videoId).text(" " + videoMetadata.statistics.likeCount);
		$('#spanDislike-'+videoId).text(" " + videoMetadata.statistics.dislikeCount);
	}
	
	//TO-DO
	//Updates the view count
	$('#numberOfVisits-'+videoId).text(" " + videoMetadata.statistics.viewCount);
}
