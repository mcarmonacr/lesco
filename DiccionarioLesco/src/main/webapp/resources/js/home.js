jQuery(document).ready(function($) {

	//Checks the main input of words and updates the page accordingly
	$("#termsInput").keyup(function(){
		checkTerm();
	});
	
	//Checks preferred my terms input of words and updates the section accordingly
	$("#myPreferredTermsInput").keyup(function(){
		checkMyPreferredTerm();
	});
	
	//Checks my terms input of words and updates the section accordingly
	$("#myTermsInput").keyup(function(){
		checkMyTerm();
	});
	
	//Confirmation dialog that pops when the user attempts to delete a category	
	$(".deleteWordConfirm").click(function(e) {
	    e.preventDefault();
	    var spanId = $(this).attr("id");
	    
	    var splitResult= spanId.split("-");
	    
	    var wordId= splitResult[1];

	    $("#deleteWordDialog").dialog({
	      buttons : {
	        "Confirm" : function() {
	        	//If the user confirms, then the category should be deleted
	        	deleteWord(wordId);
	        	$(this).dialog("close");
	        },
	        "Cancel" : function() {
	          $(this).dialog("close");
	        }
	      }
	    });

	    $("#deleteWordDialog").dialog("open");
	  });

});

/**
 * Assign a category to the drop-down menu when an option is picked
 * Assign the name and also sets the categoryId to the hidden field
 * 
 * @param category
 * @param categoryId
 * @returns nothing
 */
function assignCategory(category, categoryId) {
	$("#categoriesDropdownMenu").html('<span class="glyphicon glyphicon-tasks"></span> '+ category +' <span class="caret"></span>' + '<div id="categoryIdDiv" hidden>' + categoryId+ '</div>');
	
	//Check the term lists
	checkTerm();
}

/**
 * Assign a category to the drop-down menu when an option is picked
 * Assign the name and also sets the categoryId to the hidden field
 * 
 * @param category
 * @param categoryId
 * @returns nothing
 */
function assignMyPreferredCategory(category, categoryId) {
	$("#myPreferredCategoryDropdownMenu").html('<span class="glyphicon glyphicon-tasks"></span> '+ category +' <span class="caret"></span>' + '<div id="myPreferredCategoryIdDiv" hidden>' + categoryId+ '</div>');
	
	//Check the term lists
	checkMyPreferredTerm();
}

/**
 * Assign a category to the drop-down menu when an option is picked
 * Assign the name and also sets the categoryId to the hidden field
 * 
 * @param category
 * @param categoryId
 * @returns nothing
 */
function assignMyCategory(category, categoryId) {
	$("#myCategoryDropdownMenu").html('<span class="glyphicon glyphicon-tasks"></span> '+ category +' <span class="caret"></span>' + '<div id="myCategoryIdDiv" hidden>' + categoryId+ '</div>');
	
	//Check the term lists
	checkMyTerm();
}

/**
 * Loads all the data of a term in its proper fields
 * 
 * @param wordId
 * @returns nothing
 */
function loadDetail(wordId) {
	
	//Create the JSON search string  
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
				loadTermDetails(data.content);
			}else {
				console.log("Data: " + data.content.word);
			}
		},
		error : function(e) {
			console.log("ERROR: ", e);
		},
		done : function(e) {
			console.log("DONE: ", e);
		}
	});
}

/**
 * Loads into the DOM all the term details
 * 
 * @param content
 * @returns nothing
 */
function loadTermDetails(content){ 
	
	 $("#wordName").text(decodeURIComponent(escape(content.wordName)));
	 $('#termSourceURLIframe').attr('src', "https://www.youtube.com/embed/" + content.termYoutubeVideoID + "?controls=1");
	 updateVideoRatingMetadata(content.termYoutubeVideoID, content.termVideoRating, content.termVideoMetadata);
	 
	 $("#definitionDiv").text(decodeURIComponent(escape(data.content.definition)));
	 $('#definitionSourceURLIframe').attr('src', "https://www.youtube.com/embed/" + content.definitionYoutubeVideoID + "?controls=1");
	 updateVideoRatingMetadata(content.definitionYoutubeVideoID, content.definitionVideoRating, content.definitionVideoMetadata);
	 
	 $("#explanationDiv").text(decodeURIComponent(escape(data.content.explanation)));
	 $('#explanationSourceURLIframe').attr('src', "https://www.youtube.com/embed/" + content.explanationYoutubeVideoID + "?controls=1");
	 updateVideoRatingMetadata(content.explanationYoutubeVideoID, content.explanationVideoRating, content.explanationVideoMetadata);
	 
	 $("#exampleDiv").text(decodeURIComponent(escape(data.content.example)));
	 $('#exampleSourceURLIframe').attr('src', "https://www.youtube.com/embed/" + content.exampleYoutubeVideoID + "?controls=1");
	 updateVideoRatingMetadata(content.exampleYoutubeVideoID, content.exampleVideoRating, content.exampleVideoMetadata);
}

/**
 * Toggles the word into a preferred and non-preferred state
 * 
 * @param wordId
 * @returns nothing
 */
function togglePreferred(wordId) {
	
	//Get the proper element based on the given ID
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
		},
		done : function(e) {
			console.log("DONE: ", e);
			}
	});
}

/**
 * Checks the matching terms from the input term field and loads them in the list
 * 
 * @returns nothing
 */
function checkTerm() {

	//Get the search elements
	var termsInput= document.getElementById("termsInput");
	var categoryIdDiv= document.getElementById("categoryIdDiv");
	  
	//Creates the JSON search string
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
				//Updates the terms give the data from the query
				updateTermsList(data.content.wordsList, data.content.myWordsList, data.content.isSessionValid);
			}else if(data != null && data.code == "001"){
				//Empty the current list
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
		},
		done : function(e) {
			console.log("DONE: ", e);
			}
	});
}

/**
 * Checks the matching terms from the my input term field and loads them in the list
 * 
 * @returns nothing
 */
function checkMyPreferredTerm() {

	//Get the search elements
	var myPreferredTermsInput= document.getElementById("myPreferredTermsInput");
	var myPreferredCategoryIdDiv= document.getElementById("myPreferredCategoryIdDiv");
	  
	//Creates the JSON search string
	var search= {
		"myPreferredTermsInput":myPreferredTermsInput.value,
		"myPreferredCategoryIdDiv":myPreferredCategoryIdDiv.textContent
	}

	$.ajax({
	  	headers: { 
	        'Accept': 'application/json',
	        'Content-Type': 'application/json' 
		},
		type: 'post',
		contentType : "application/json",
		url: "/DiccionarioLesco/termino/obtenerListaMisTerminosPreferidos",
		data : JSON.stringify(search),
		dataType : 'json',
		success : function(data) {
			console.log("SUCCESS: ", data);
			if(data != null && data.code == "000"){
				//Updates the list based on the results from the query
				updateMyPreferredTermsList(data.content.myPreferredWordsList);
			}else if(data != null && data.code == "001"){
				//Empties the current list
				var myPreferredWordListDiv= $("#myPreferredWordListDiv");
				myPreferredWordListDiv.children().remove();
				
				//Update the total terms counter
				var myTotalPreferredTermsCounter= $("#myTotalPreferredTermsCounter");
				
				myTotalPreferredTermsCounter.text("Total: 0");
				
				//TO DO Update counter when there is an empty list
				
			} else {
				
			}
		},
		error : function(e) {
			console.log("ERROR: ", e);
		},
		done : function(e) {
			console.log("DONE: ", e);
			}
		  });
}

/**
 * Checks the matching terms from the my input term field and loads them in the list
 * 
 * @returns nothing
 */
function checkMyTerm() {

	//Get the search elements
	var myTermsInput= document.getElementById("myTermsInput");
	var myCategoryIdDiv= document.getElementById("myCategoryIdDiv");
	  
	//Creates the JSON search string
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
				//Updates the list based on the results from the query
				updateMyTermsList(data.content.myWordsList);
			}else if(data != null && data.code == "001"){
				//Empties the current list
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
		},
		done : function(e) {
			console.log("DONE: ", e);
			}
		  });
}

/**
 * Updates the list of term with the given data
 * 
 * @param wordList
 * @param myWordList
 * @param isSessionValid
 * @returns nothing
 */
function updateTermsList(wordList, myWordList, isSessionValid){
	
	//Get the ID wordListDiv
	var wordListDiv= $("#wordListDiv");
	
	//Remove all anchors form the wordListDiv
	wordListDiv.children().remove();
	
	//Insert the new set of word from the query
	for (index = 0; index < wordList.length; index++) {
		var anchor= $("<a>");
		anchor.addClass("list-group-item");
		
		//Checks if the session is a valid one
		if(isSessionValid){
			
			//If it isn't an empty list
			if(myWordList.length > 0) {
				for (myIndex = 0; myIndex < myWordList.length; myIndex++) {
					
					//Checks if the word is a preferred one
					if(wordList[index].wordName == myWordList[myIndex].wordName){
						var spanFavorite= $("<span>");
						spanFavorite.attr("href", "#");
						spanFavorite.attr("onclick", "togglePreferred("+ wordList[index].wordId +")");
						spanFavorite.attr("title", "Deshacer Favorito");
						spanFavorite.attr("id", "span-"+wordList[index].wordId);
						spanFavorite.attr("class", "preferred-button btn btn-sm btn-warning fa fa-lg fa-star pull-left");
						
						var spanDetail= $("<span>");
						spanDetail.attr("href", "#");
						spanDetail.attr("onclick", "loadDetail("+ wordList[index].wordId +")");
						spanDetail.attr("title", "Cargar Detalle");
						spanDetail.text(decodeURIComponent(escape(wordList[index].wordName)));
						
						anchor.append(spanFavorite);
						anchor.append(spanDetail);
					} else {
						var spanFavorite= $("<span>");
						spanFavorite.attr("href", "#");
						spanFavorite.attr("onclick", "togglePreferred("+ wordList[index].wordId +")");
						spanFavorite.attr("title", "Hacer Favorito");
						spanFavorite.attr("id", "span-"+wordList[index].wordId);
						spanFavorite.attr("class", "preferred-button btn btn-sm btn-warning fa fa-lg fa-star-o pull-left");
						
						var spanDetail= $("<span>");
						spanDetail.attr("href", "#");
						spanDetail.attr("onclick", "loadDetail("+ wordList[index].wordId +")");
						spanDetail.attr("title", "Cargar Detalle");
						spanDetail.text(decodeURIComponent(escape(wordList[index].wordName)));
						
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
				spanFavorite.attr("class", "preferred-button btn btn-sm btn-warning fa fa-lg fa-star-o pull-left");
				
				var spanDetail= $("<span>");
				spanDetail.attr("href", "#");
				spanDetail.attr("onclick", "loadDetail("+ wordList[index].wordId +")");
				spanDetail.attr("title", "Cargar Detalle");
				spanDetail.text(decodeURIComponent(escape(wordList[index].wordName)));
				
				anchor.append(spanFavorite);
				anchor.append(spanDetail);
			}

		}else{
			var spanDetail= $("<span>");
			spanDetail.attr("href", "#");
			spanDetail.attr("onclick", "loadDetail("+ wordList[index].wordId +")");
			spanDetail.attr("title", "Cargar Detalle");
			spanDetail.text(decodeURIComponent(escape(wordList[index].wordName)));
			
			anchor.append(spanDetail);
		}
		//Appends the new row to the list
		wordListDiv.append(anchor);
	}
	//Update the total terms counter
	var totalTermsCounter= $("#totalTermsCounter");
	
	//Set the counter number
	totalTermsCounter.text("Total: " + wordList.length);
}

/**
 * Updates my term list based on the given data
 * 
 * @param myWordList
 * @returns nothing 
 */
function updateMyTermsList(myWordsList){
	
	//Get the ID wordListDiv
	var myWordListDiv= $("#myWordListDiv");
	
	//Remove all anchors form the wordListDiv
	myWordListDiv.children().remove();
	
	//Insert the new set of word from the query
	for (index = 0; index < myWordsList.length; index++) {
		var anchor= $("<a>");
		anchor.addClass("list-group-item");

		var spanDelete= $("<span>");
		spanDelete.attr("title", "Eliminar");
		spanDelete.attr("id", "word-"+myWordsList[index].wordId);
		spanDelete.attr("class", "btn btn-sm btn-danger glyphicon glyphicon-trash pull-left deleteWordConfirm");
		
		var spanDetail= $("<span>");
		spanDetail.attr("onclick", "loadDetail("+ myWordsList[index].wordId +")");
		spanDetail.attr("title", "Cargar Detalle");
		spanDetail.text(decodeURIComponent(escape(myWordsList[index].wordName)));
		
		//Appends the two spans to the new anchor 
		anchor.append(spanDelete);
		anchor.append(spanDetail);
		
		//Appends the new row to the list
		myWordListDiv.append(anchor);
	}
	//Update the total terms counter
	var myTotalTermsCounter= $("#myTotalTermsCounter");
	
	//Set the counter number
	myTotalTermsCounter.text("Total: " + myWordsList.length);	
}

/**
 * Updates my term list based on the given data
 * 
 * @param myWordList
 * @returns nothing 
 */
function updateMyPreferredTermsList(myPreferredWordsList){
	
	//Get the ID wordListDiv
	var myPreferredWordListDiv= $("#myPreferredWordListDiv");
	
	//Remove all anchors form the wordListDiv
	myPreferredWordListDiv.children().remove();
	
	//Insert the new set of word from the query
	for (index = 0; index < myPreferredWordsList.length; index++) {
		var anchor= $("<a>");
		anchor.attr("href", "#");
		anchor.attr("onclick", "loadDetail("+ myPreferredWordsList[index].wordId +")");
		anchor.addClass("list-group-item");
		anchor.text(decodeURIComponent(escape(myPreferredWordsList[index].wordName)));
		
		//Appends the new row to the list
		myPreferredWordListDiv.append(anchor);
	}
	//Update the total terms counter
	var myTotalPreferredTermsCounter= $("#myTotalPreferredTermsCounter");
	
	//Set the counter number
	myTotalPreferredTermsCounter.text("Total: " + myPreferredWordsList.length);	
}

/**
 * Rate video function
 * 
 * @param videoId
 * @param action
 * @returns nothing
 */
function rateVideo(videoId, action){	
	//Create the JSON search string
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
	    		
	    		//Update the rating of the specific video
	    		updateVideoRatingMetadata(videoId, data.content.videoRating.rating, data.content.videoMetadata);
	    		
	    	}else {
	    		
	    	}
		},
		error : function(e) {
			console.log("ERROR: ", e);
		},
		done : function(e) {
			console.log("DONE: ", e);
		}
	  });
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
	
	//Update the likes and dislikes count
	if(videoMetadata.statistics.likeCount != null && videoMetadata.statistics.dislikeCount != null){
		$('#spanLike-'+videoId).text(" " + videoMetadata.statistics.likeCount);
		$('#spanDislike-'+videoId).text(" " + videoMetadata.statistics.dislikeCount);
	}
	
	//Updates the view count
	$('#numberOfVisits-'+videoId).text(" " + videoMetadata.statistics.viewCount);
}

/**
 * Elimina un término específico
 * 
 * @param wordId
 * @returns nothing
 */
function deleteWord(wordId){

	//Search JSON string
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
			url: "/DiccionarioLesco/termino/eliminarTermino",
			data : JSON.stringify(search),
			dataType : 'json',
			success : function(data) {
				console.log("SUCCESS: ", data);
				//display(data);
				//Updas the page so the change is reflected in the list of categories
				//location.reload();
				
				//All the lists have to be reviewed
				checkTerm();
				
				checkMyPreferredTerm();
				
				checkMyTerm();
			},
			error : function(e) {
				console.log("ERROR: ", e);
				//display(e);
				//location.reload();
			},
			done : function(e) {
				console.log("DONE");
				//enableSearchButton(true);
				//location.reload();
			}
	  });
	
}
