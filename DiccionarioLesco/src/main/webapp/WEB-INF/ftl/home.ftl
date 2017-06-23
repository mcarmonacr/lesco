<#import "common.ftl" as c/>
<@c.page css="resources/css/home.css" js="resources/js/home.js">
    <!-- custom page content -->
    
    <!-- Whole content row -->
    <div class="row" id="main-content">
    
    		 <!-- Video detail column -->
    <div class="col-md-9">
	<div>


	<!-- Video Details Section -->
		<div class="panel panel-default">
		  <div class="panel-body">
		  
		  
		  <#if randomWord.wordName??>
		 	 	<div class="row">
			    	<h1><span class="label label-primary terms-header" id="wordName"> ${randomWord.wordName}</span></h1>
			    </div>
		  <#else>
		  	<div class="row">
			    	<h1><span class="label label-primary terms-header" id="wordName"> No Hay Términos</span></h1>
			    </div>
		  </#if>

			
		  <!-- Nav tabs -->
		  <!-- Usar .col-md-offset-* para correr una columna *  a la derecha. Este es, creo: .col-md-4 .col-md-offset-4 -->
		  <div class="row">
			  <div class="col-md-8 col-md-offset-4">
				  <ul class="nav nav-tabs" role="tablist">
				    <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">Término</a></li>
				    <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">Definición</a></li>
				    <li role="presentation"><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab">Explicación</a></li>
				    <li role="presentation"><a href="#settings" aria-controls="settings" role="tab" data-toggle="tab">Ejemplo(s)</a></li>
				  </ul>
			  </div>
		  </div>
		
		  <!-- Tab panes -->	  
		  <div class="tab-content">
		    <div role="tabpanel" class="tab-pane active" id="home">
		    	<div class="row embed-responsive embed-responsive-16by9">
		    		<#assign termSourceURL = "https://www.youtube.com/embed/${randomWord.video.termYoutubeVideoID}?controls=1">
					 <iframe class="embed-responsive-item" id="videoIframe"
					src=${termSourceURL}>
					</iframe> 
				</div>
				<div class="row">
					<div class="col-md-6">
						<h2>
						<!-- 	    Checks if the user is logged in the site -->
					    <#if ((isSessionValid??) && (isSessionValid == "true"))>
							  <!-- 						Icons taken from: https://bootsnipp.com/iconsearch/ -->
									 <#if ((videosMetadata.termVideoRating??) && (videosMetadata.termVideoRating == "like"))> 								 
										 <a onclick="rateVideo('${randomWord.video.termYoutubeVideoID}', 'like')" class="btn-lg btn-primary">
									      	<span id="spanLike-${randomWord.video.termYoutubeVideoID}" class="fa fa-thumbs-up"> ${videosMetadata.termVideoLikes}</span> 
									      </a>
									 <#else>
										  <a onclick="rateVideo('${randomWord.video.termYoutubeVideoID}', 'like')" class="btn-lg btn-primary">
									      	<span id="spanLike-${randomWord.video.termYoutubeVideoID}" class="fa fa-thumbs-o-up"> ${videosMetadata.termVideoLikes}</span> 
									      </a>
									 </#if>
									 
									 <a>|</a>
									 
									 <#if ((videosMetadata.termVideoRating??) && (videosMetadata.termVideoRating == "dislike"))> 
										 <a onclick="rateVideo('${randomWord.video.termYoutubeVideoID}', 'dislike')" class="btn-lg btn-primary">
									      	<span id="spanDislike-${randomWord.video.termYoutubeVideoID}" class="fa fa-thumbs-down"> ${videosMetadata.termVideoDislikes}</span> 
									      </a>
									 <#else>
										 <a onclick="rateVideo('${randomWord.video.termYoutubeVideoID}', 'dislike')" class="btn-lg btn-primary">
									      	<span id="spanDislike-${randomWord.video.termYoutubeVideoID}" class="fa fa-thumbs-o-down"> ${videosMetadata.termVideoDislikes}</span> 
									      </a>
									 </#if>
									 
									 <a>|</a>
						  <#else>
							  <a class="btn-lg btn-primary">
							      	<span id="spanLike-${randomWord.video.termYoutubeVideoID}" class="fa fa-thumbs-up"> ${videosMetadata.termVideoLikes}</span> 
						      </a>
						      
						      <a>|</a>
						      
						      <a class="btn-lg btn-primary">
						      	<span id="spanDislike-${randomWord.video.termYoutubeVideoID}" class="fa fa-thumbs-down"> ${videosMetadata.termVideoDislikes}</span> 
						      </a>
						  
						  	  <a>|</a>
						  </#if>
						</h2>
					</div>
					<div class="col-md-6">
						<h2>
							<#-- <#assign visits = "Visitas: ${randomWord.numberOfVisits}"> -->
							<a class="btn-lg btn-primary pull-right">
					      		<span id="numberOfTermVideoVisits" class="fa fa-eye"> ${videosMetadata.termVideo}</span>
					      	</a>
							
						</h2>
					</div>
				</div>
		    </div>
		    <div role="tabpanel" class="tab-pane" id="profile">
		    	<div class="row embed-responsive embed-responsive-16by9">
		    	<#assign definitionSourceURL = "https://www.youtube.com/embed/${randomWord.video.definitionYoutubeVideoID}?controls=1">
					 <iframe class="embed-responsive-item" id="videoIframe"
					src=${definitionSourceURL}>
					</iframe> 
				</div>
			    <div class="row">
			    
			    <div class="panel panel-primary">
					<div class="panel-heading">
					  <h3 class="panel-title">Definición</h3>
					  </div>
					  <div class="panel-body">
					    ${randomWord.definition}
					  </div>
					</div>
<!-- 					<div class="panel-group"> -->
<!-- 					  <div class="panel panel-default"> -->
<!-- 					    <div class="panel-heading"> -->
<!-- 					      <h4 class="panel-title"> -->
<!-- 					        <a data-toggle="collapse" href="#collapse2">Definición</a> -->
<!-- 					      </h4> -->
<!-- 					    </div> -->
<!-- 					    <div id="collapse2" class="panel-collapse collapse"> -->
<!-- 					      <div id="definitionDiv" class="panel-body">Es un tipo de saludo.</div> -->
<!-- 					      <div class="panel-footer">Panel Footer</div> -->
<!-- 					    </div> -->
<!-- 					  </div> -->
<!-- 					</div> -->
				</div>
				<div class="row">
					<div class="col-md-6">
						<h2>
						 <!-- 	    Checks if the user is logged in the site -->
					    <#if ((isSessionValid??) && (isSessionValid == "true"))>
							  <!-- 						Icons taken from: https://bootsnipp.com/iconsearch/ -->
									 <#if ((videosMetadata.definitionVideoRating??) && (videosMetadata.definitionVideoRating == "like"))> 								 
										 <a onclick="rateVideo('${randomWord.video.definitionYoutubeVideoID}', 'like')" class="btn-lg btn-primary">
									      	<span id="spanLike-${randomWord.video.definitionYoutubeVideoID}" class="fa fa-thumbs-up"> ${videosMetadata.definitionVideoLikes}</span> 
									      </a>
									 <#else>
										  <a onclick="rateVideo('${randomWord.video.definitionYoutubeVideoID}', 'like')" class="btn-lg btn-primary">
									      	<span id="spanLike-${randomWord.video.definitionYoutubeVideoID}" class="fa fa-thumbs-o-up"> ${videosMetadata.definitionVideoLikes}</span> 
									      </a>
									 </#if>
									 
									 <a>|</a>
									 
									 <#if ((videosMetadata.definitionVideoRating??) && (videosMetadata.definitionVideoRating == "dislike"))> 
										 <a onclick="rateVideo('${randomWord.video.definitionYoutubeVideoID}', 'dislike')" class="btn-lg btn-primary">
									      	<span id="spanDislike-${randomWord.video.definitionYoutubeVideoID}" class="fa fa-thumbs-down"> ${videosMetadata.definitionVideoDislikes}</span> 
									      </a>
									 <#else>
										 <a onclick="rateVideo('${randomWord.video.definitionYoutubeVideoID}', 'dislike')" class="btn-lg btn-primary">
									      	<span id="spanDislike-${randomWord.video.definitionYoutubeVideoID}" class="fa fa-thumbs-o-down"> ${videosMetadata.definitionVideoDislikes}</span> 
									      </a>
									 </#if>
									 
									 <a>|</a>
						  <#else>
							  <a class="btn-lg btn-primary">
							      	<span id="spanLike-${randomWord.video.definitionYoutubeVideoID}" class="fa fa-thumbs-up"> ${videosMetadata.definitionVideoLikes}</span> 
						      </a>
						      
						      <a>|</a>
						      
						      <a class="btn-lg btn-primary">
						      	<span id="spanDislike-${randomWord.video.definitionYoutubeVideoID}" class="fa fa-thumbs-down"> ${videosMetadata.definitionVideoDislikes}</span> 
						      </a>
						  
						  	  <a>|</a>
						  </#if>
						</h2>
					</div>
					<div class="col-md-6">
						<h2>
							<#-- <#assign visits = "Visitas: ${randomWord.numberOfVisits}"> -->
							<a class="btn-lg btn-primary pull-right">
					      		<span id="numberOfDefinitionVideoVisits" class="fa fa-eye"> ${videosMetadata.definitionVideo}</span>
					      	</a>
						</h2>
					</div>
				</div>
		    </div>
		    <div role="tabpanel" class="tab-pane" id="messages">
			    <div class="row">
			    <#assign explanationSourceURL = "https://www.youtube.com/embed/${randomWord.video.explanationYoutubeVideoID}?controls=1">
					 <iframe class="video-container" id="videoIframe"
					src=${explanationSourceURL}>
					</iframe> 
				</div>
			    <div class="row">
					<div class="panel-group">
					  <div class="panel panel-primary">
					    <div class="panel-heading">
					      <h4 class="panel-title">
					        <a data-toggle="collapse" href="#collapse1"><span class="glyphicon glyphicon-expand"></span> Explicación</a>
					      </h4>
					    </div>
					    <div id="collapse1" class="panel-collapse collapse">
					      <div id="explanationDiv" class="panel-body">${randomWord.explanation}</div>
		<!-- 			      <div class="panel-footer">Panel Footer</div> -->
					    </div>
					  </div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<h2>
						 <!-- 	    Checks if the user is logged in the site -->
					    <#if ((isSessionValid??) && (isSessionValid == "true"))>
							  <!-- 						Icons taken from: https://bootsnipp.com/iconsearch/ -->
									 <#if ((videosMetadata.explanationVideoRating??) && (videosMetadata.explanationVideoRating == "like"))> 								 
										 <a onclick="rateVideo('${randomWord.video.explanationYoutubeVideoID}', 'like')" class="btn-lg btn-primary">
									      	<span id="spanLike-${randomWord.video.explanationYoutubeVideoID}" class="fa fa-thumbs-up"> ${videosMetadata.exampleVideoLikes}</span> 
									      </a>
									 <#else>
										  <a onclick="rateVideo('${randomWord.video.explanationYoutubeVideoID}', 'like')" class="btn-lg btn-primary">
									      	<span id="spanLike-${randomWord.video.explanationYoutubeVideoID}" class="fa fa-thumbs-o-up"> ${videosMetadata.exampleVideoLikes}</span> 
									      </a>
									 </#if>
									 
									 <a>|</a>
									 
									 <#if ((videosMetadata.explanationVideoRating??) && (videosMetadata.explanationVideoRating == "dislike"))> 
										 <a onclick="rateVideo('${randomWord.video.explanationYoutubeVideoID}', 'dislike')" class="btn-lg btn-primary">
									      	<span id="spanDislike-${randomWord.video.explanationYoutubeVideoID}" class="fa fa-thumbs-down"> ${videosMetadata.explanationVideoDislikes}</span> 
									      </a>
									 <#else>
										 <a onclick="rateVideo('${randomWord.video.explanationYoutubeVideoID}', 'dislike')" class="btn-lg btn-primary">
									      	<span id="spanDislike-${randomWord.video.explanationYoutubeVideoID}" class="fa fa-thumbs-o-down"> ${videosMetadata.explanationVideoDislikes}</span> 
									      </a>
									 </#if>
									 
									 <a>|</a>
						  <#else>
							  <a class="btn-lg btn-primary">
							      	<span id="spanLike-${randomWord.video.explanationYoutubeVideoID}" class="fa fa-thumbs-up"> ${videosMetadata.exampleVideoLikes}</span> 
						      </a>
						      
						      <a>|</a>
						      
						      <a class="btn-lg btn-primary">
						      	<span id="spanDislike-${randomWord.video.explanationYoutubeVideoID}" class="fa fa-thumbs-down"> ${videosMetadata.explanationVideoDislikes}</span> 
						      </a>
						  
						  	  <a>|</a>
						  </#if>
						</h2>
					</div>
					<div class="col-md-6">
						<h2>
							<#-- <#assign visits = "Visitas: ${randomWord.numberOfVisits}"> -->
							<a class="btn-lg btn-primary pull-right">
					      		<span id="numberOfExplanationVideoVisits" class="fa fa-eye"> ${videosMetadata.explanationVideo}</span>
					      	</a>
						</h2>
					</div>
				</div>
		    </div>
		    <div role="tabpanel" class="tab-pane" id="settings">
		    	<div class="row">
		    	<#assign exampleSourceURL = "https://www.youtube.com/embed/${randomWord.video.exampleYoutubeVideoID}?controls=1">
					 <iframe class="video-container" id="videoIframe"
					src=${exampleSourceURL}>
					</iframe> 
				</div>
				<div class="row">
					<div class="panel-group">
					  <div class="panel panel-primary">
						<div class="panel-heading">
						  <h4 class="panel-title">
							<a data-toggle="collapse" href="#collapse3"> <span class="glyphicon glyphicon-expand"></span> Ejemplos</a>
						  </h4>
						</div>
						<div id="collapse3" class="panel-collapse collapse">
						  <div id="exampleDiv" class="panel-body">${randomWord.example}</div>
					<!-- 			      <div class="panel-footer">Panel Footer</div> -->
						</div>
					  </div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<h2>
						 <!-- 	    Checks if the user is logged in the site -->
					    <#if ((isSessionValid??) && (isSessionValid == "true"))>
							  <!-- 						Icons taken from: https://bootsnipp.com/iconsearch/ -->
									 <#if ((videosMetadata.exampleVideoRating??) && (videosMetadata.exampleVideoRating == "like"))> 								 
										 <a onclick="rateVideo('${randomWord.video.exampleYoutubeVideoID}', 'like')" class="btn-lg btn-primary">
									      	<span id="spanLike-${randomWord.video.exampleYoutubeVideoID}" class="fa fa-thumbs-up"> ${videosMetadata.exampleVideoLikes}</span> 
									      </a>
									 <#else>
										  <a onclick="rateVideo('${randomWord.video.exampleYoutubeVideoID}', 'like')" class="btn-lg btn-primary">
									      	<span id="spanLike-${randomWord.video.exampleYoutubeVideoID}" class="fa fa-thumbs-o-up"> ${videosMetadata.exampleVideoLikes}</span> 
									      </a>
									 </#if>
									 
									 <a>|</a>
									 
									 <#if ((videosMetadata.exampleVideoRating??) && (videosMetadata.exampleVideoRating == "dislike"))> 
										 <a onclick="rateVideo('${randomWord.video.exampleYoutubeVideoID}', 'dislike')" class="btn-lg btn-primary">
									      	<span id="spanDislike-${randomWord.video.exampleYoutubeVideoID}" class="fa fa-thumbs-down"> ${videosMetadata.exampleVideoDislikes}</span> 
									      </a>
									 <#else>
										 <a onclick="rateVideo('${randomWord.video.exampleYoutubeVideoID}', 'dislike')" class="btn-lg btn-primary">
									      	<span id="spanDislike-${randomWord.video.exampleYoutubeVideoID}" class="fa fa-thumbs-o-down"> ${videosMetadata.exampleVideoDislikes}</span> 
									      </a>
									 </#if>
									 
									 <a>|</a>
						  <#else>
							  <a class="btn-lg btn-primary">
							      	<span id="spanLike-${randomWord.video.exampleYoutubeVideoID}" class="fa fa-thumbs-up"> ${videosMetadata.exampleVideoLikes}</span> 
						      </a>
						      
						      <a>|</a>
						      
						      <a class="btn-lg btn-primary">
						      	<span id="spanDislike-${randomWord.video.exampleYoutubeVideoID}" class="fa fa-thumbs-down"> ${videosMetadata.exampleVideoDislikes}</span> 
						      </a>
						  
						  	  <a>|</a>
						  </#if>
						</h2>
					</div>
					<div class="col-md-6">
						<h2>
							<#-- <#assign visits = "Visitas: ${randomWord.numberOfVisits}"> -->
							<a class="btn-lg btn-primary pull-right">
					      		<span id="numberOfExampleVideoVisits" class="fa fa-eye"> ${videosMetadata.exampleVideo}</span>
					      	</a>
						</h2>
					</div>
				</div>			
		    </div>
		  </div>

		    </div> 
		</div> <!-- Closes Video Details Section -->

</div>
  </div>
    
    
    <!-- Terms column -->
    <div class="col-md-3">
    
    
     <!--Como usar Tabs: http://getbootstrap.com/javascript/#tabs -->

 <!-- 	    Checks if the user is logged in the site -->
		    <#if ((isSessionValid??) && (isSessionValid == "true"))>
				  <!-- Nav tabs -->
				  <ul class="nav nav-tabs" role="tablist">
				    <li role="presentation" class="active"><a href="#siteTerms" aria-controls="siteTerms" role="tab" data-toggle="tab">Términos</a></li>
				    <li role="presentation"><a href="#myFavoriteTerms" aria-controls="myFavoriteTerms" role="tab" data-toggle="tab">Favoritos</a></li>
				  </ul>
			  </#if>
			  
			  
			  <!-- Tab panes -->
			  <div class="tab-content">
			    <div role="tabpanel" class="tab-pane active" id="siteTerms">
			    	<div class="panel panel-default">
		  <div class="panel-body">

    	<!-- Terms Section -->
	    <div class="list-group">
	    
	    <div class="row">
	    	<h1><span class="label label-primary terms-header glyphicon glyphicon-th-list"> Términos</span></h1>
	    </div>
	    
	    <div class="row input-group search-text-box">
		  <span class="glyphicon glyphicon-search input-group-addon home-search-glyphicon"></span>
<!-- 		   <span class="input-group-addon" id="basic-addon1">@</span> -->
		  <input id="termsInput" name="termsInput" type="text" class="form-control" placeholder="Buscar" aria-describedby="sizing-addon2">
		</div>
	    
	    <div class="row dropdown dropdown-container-home">
		  <button class="btn btn-default dropdown-toggle dropdown-button-home" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
		    <span class="glyphicon glyphicon-tasks"></span> Categoría
		    <span class="caret"></span>
		    <div id="categoryIdDiv" hidden></div>
		  </button>
		  <ul class="dropdown-menu dropdown-ul-home" aria-labelledby="dropdownMenu1">
		  		<li onclick="assignCategory($(this).find('a').text(), $(this).find('div').text())">
		  			<a href="#">Cualquiera</a>
		  			<div hidden></div>
		  		</li>
		  		<li role="separator" class="divider"></li>
		  <#list listCategories as category>
		  		
<!-- 	    	<li class="list-group-item list-group-item-info"><a href="#">${category.categoryName}</a></li> -->
				<li onclick="assignCategory($(this).find('a').text(), $(this).find('div').text())">
					<a href="#">${category.categoryName}</a>
					<div hidden>${category.categoryId}</div>
				</li>
				
<!-- 				<li onclick="assignCategory(this.value)" id="1">1</li> -->
	  	</#list>
<!-- 		    <li><a href="#">Action</a></li> -->
<!-- 		    <li><a href="#">Another action</a></li> -->
<!-- 		    <li><a href="#">Something else here</a></li> -->
<!-- 		    <li role="separator" class="divider"></li> -->
<!-- 		    <li><a href="#">Separated link</a></li> -->
		  </ul>
		</div>

	    <div id="wordListDiv" class="row terms-list text-center">
	    
<!-- 	    <a href="#" class="list-group-item active"> -->
<!-- 		    Hola -->
<!-- 		  </a> -->

<!-- 		  <a href="#" class="list-group-item">Importante</a> -->
<!-- 		  <a href="#" class="list-group-item">Letra</a> -->
<!-- 		  <a href="#" class="list-group-item">Nunca</a> -->
	    
	    
			<!-- 	    Checks if the user is logged in the site -->
		    <#if ((isSessionValid??) && (isSessionValid == "true"))>
				<!-- Work to do here: Add the proper classes and onClick actions -->
			    <#list listWords as word>
				     <#if ((listMyWords??) && (listMyWords?size > 0))>
					    <#list listMyWords as myWord>
					    <!-- This means that the word is a preferred word by the user -->
					    	<#if (word.wordName == myWord.wordName) >
								<a class="list-group-item">
									<span id="span-${word.wordId}" title="Deshacer Favorito" onclick="togglePreferred(${word.wordId})" class="fa fa-lg fa-star pull-left"></span> 
									<span onclick="loadDetail(${word.wordId})" title="Cargar detalle"> ${word.wordName}</span>
								</a>
								
					    	</#if>
					    </#list>
					<#else>								
						<a class="list-group-item">
							<span id="span-${word.wordId}" title="Hacer Favorito" onclick="togglePreferred(${word.wordId})" class="fa fa-lg fa-star-o pull-left"></span> 
							<span onclick="loadDetail(${word.wordId})" title="Cargar detalle"> ${word.wordName}</span>
						</a>
					</#if>
			    </#list>
			<#else>
				<#list listWords as word>							
					<a class="list-group-item"> 
						<span onclick="loadDetail(${word.wordId})" title="Cargar detalle"> ${word.wordName}</span>
					</a>
			    </#list>		
			</#if>

		 </div>
		    
		  <#if randomWord.wordName??>
		  	<div class="row">
		  		<h1><span id="totalTermsCounter" class="label label-primary terms-header">Total: ${listWords?size} </span></h1>
		  	</div>
		  <#else>
		  	<div class="row">
		  		<h1><span id="totalTermsCounter" class="label label-primary terms-header">Total: 0 </span></h1>
		  	</div>
		  </#if>
		  
		</div>
		
		  </div>
		</div>
			    </div>
			    
			    <div role="tabpanel" class="tab-pane" id="myFavoriteTerms">
			    
			    
			    <div class="panel panel-default">
		  <div class="panel-body">

    	<!-- Terms Section -->
	    <div class="list-group">
	    
	    <div class="row">
	    	<h1><span class="label label-primary terms-header glyphicon glyphicon-th-list"> Favoritos</span></h1>
	    </div>
	    
	    <div class="row input-group search-text-box">
		  <span class="glyphicon glyphicon-search input-group-addon home-search-glyphicon"></span>
<!-- 		   <span class="input-group-addon" id="basic-addon1">@</span> -->
		  <input id="myTermsInput" name="myTermsInput" type="text" class="form-control" placeholder="Buscar" aria-describedby="sizing-addon2">
		</div>
	    
	    <div class="row dropdown dropdown-container-home">
		  <button class="btn btn-default dropdown-toggle dropdown-button-home" type="button" id="myCategoryDropdownMenu" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
		    <span class="glyphicon glyphicon-tasks"></span> Categoría
		    <span class="caret"></span>
		    <div id="myCategoryIdDiv" hidden></div>
		  </button>
		  <ul class="dropdown-menu dropdown-ul-home" aria-labelledby="myCategoryDropdownMenu">
		  		<li onclick="assignMyCategory($(this).find('a').text(), $(this).find('div').text())">
		  			<a href="#">Cualquiera</a>
		  			<div hidden></div>
		  		</li>
		  		<li role="separator" class="divider"></li>
		  <#list listCategories as category>
		  		
<!-- 	    	<li class="list-group-item list-group-item-info"><a href="#">${category.categoryName}</a></li> -->
				<li onclick="assignMyCategory($(this).find('a').text(), $(this).find('div').text())">
					<a href="#">${category.categoryName}</a>
					<div hidden>${category.categoryId}</div>
				</li>
				
<!-- 				<li onclick="assignCategory(this.value)" id="1">1</li> -->
	  	</#list>
<!-- 		    <li><a href="#">Action</a></li> -->
<!-- 		    <li><a href="#">Another action</a></li> -->
<!-- 		    <li><a href="#">Something else here</a></li> -->
<!-- 		    <li role="separator" class="divider"></li> -->
<!-- 		    <li><a href="#">Separated link</a></li> -->
		  </ul>
		</div>

	    <div id="myWordListDiv" class="row terms-list text-center">
	    
<!-- 	    <a href="#" class="list-group-item active"> -->
<!-- 		    Hola -->
<!-- 		  </a> -->
		  
<!-- 		  <a href="#" class="list-group-item">Hotel</a> -->
<!-- 		  <a href="#" class="list-group-item">Importante</a> -->
<!-- 		  <a href="#" class="list-group-item">Letra</a> -->
<!-- 		  <a href="#" class="list-group-item">Nunca</a> -->
	    
	    <#if listMyWords??>
		    <#list listMyWords as myWord>
			  <a onclick="loadMyDetail(${myWord.wordId})" href="#" class="list-group-item">${myWord.wordName}</a>
		    </#list>
		</#if>
		
		 </div>
		    
		  <#if listMyWords??>
		  	<div class="row">
		  		<h1><span id="myTotalTermsCounter" class="label label-primary terms-header">Total: ${listMyWords?size} </span></h1>
		  	</div>
		  <#else>
		  	<div class="row">
		  		<h1><span id="myTotalTermsCounter" class="label label-primary terms-header">Total: 0 </span></h1>
		  	</div>
		  </#if>
		  
		</div>
		
		  </div>
		</div>
			    
			    </div>
		    </div>

    </div>

</div>
    
</@c.page>