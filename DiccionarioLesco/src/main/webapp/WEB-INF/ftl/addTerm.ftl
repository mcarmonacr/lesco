<#import "common.ftl" as c />
<@c.page css="resources/css/home.css" js="resources/js/addTerm.js">

<!-- custom page content --> <!-- Whole content row -->
<div class="row" id="main-content">

	<!-- Terms column -->
	<div class="col-md-3">

		<div class="panel panel-default">
			<div class="panel-body">

				<!-- Terms Section -->
				<div class="list-group">

					<div class="row">
						<h1>
							<span class="label label-primary terms-header">Solicitudes</span>
						</h1>
					</div>

					<div class="row input-group search-text-box">
						<span
							class="glyphicon glyphicon-search input-group-addon home-search-glyphicon"></span>
						<!-- 		   <span class="input-group-addon" id="basic-addon1">@</span> -->
						<input id="requestInput" name="requestInput" type="text"
							class="form-control" placeholder="Buscar"
							aria-describedby="sizing-addon2">
					</div>

					<div id="requestListDiv" class="row terms-list">
						<!-- 		  <a href="#" class="list-group-item active"> -->
						<!-- 		    Cras justo odio -->
						<!-- 		  </a> -->

						<#list requestList as request> <a
							onclick="loadRequestDetail(${request.requestId})" href="#"
							class="list-group-item">${request.wordName}</a> </#list>
					</div>

					<div class="row">
						<h1>
							<span id="totalRequestsCounter"
								class="label label-primary terms-header">Total:
								${requestList?size}</span>
						</h1>
					</div>

				</div>

			</div>
		</div>
	</div>

	<div class="col-md-9">

		<!--Como usar Tabs: http://getbootstrap.com/javascript/#tabs -->

		<!-- Nav tabs -->
		<ul class="nav nav-tabs" role="tablist">
			<li role="presentation" class="active"><a href="#addTerm"
				aria-controls="addTerm" role="tab" data-toggle="tab">Agregar
					Término</a></li>
			<li role="presentation"><a href="#requestTerm"
				aria-controls="requestTerm" role="tab" data-toggle="tab">Solicitar
					Término</a></li>
		</ul>

		<!-- Tab panes -->
		<div class="tab-content">
			<div role="tabpanel" class="tab-pane active" id="addTerm">

				<!-- Video detail column -->
				<!-- 					    	<div class="col-md-9"> -->
				<!-- Video Details Section -->
				<div class="panel panel-default">
					<div class="panel-body">

						<form class="form-horizontal" name="addTermForm" id="addTermForm"
							enctype="multipart/form-data">
							<fieldset>

								<!-- Form Name -->
								<legend>Agregar Término</legend>

								<!-- Text input-->
								<div class="form-group">
									<label class="col-md-4 control-label" for="textinput">Término</label>
									<div class="col-md-4">
										<input id="wordName" name="wordName" placeholder="Término"
											class="form-control input-md" type="text" required="required">
										<input id="filePath" name="filePath" class="input-file"
											type="file" accept="video/*" required="required">
										<!-- 					  <span class="help-block">help</span>   -->
									</div>
								</div>

								<!-- Select Basic -->
								<div class="form-group">
									<label class="col-md-4 control-label" for="categoryName">Categoría</label>
									<div class="col-md-4">
										<select id="categoryName" name="categoryName"
											class="form-control">
											<option value=""></option>
											<#list listCategories as category>
											<option value="${category.categoryName}">${category.categoryName}</option>
											</#list>
											<!-- 					       <option value="1">Option one</option> -->
											<!-- 					      <option value="2">Option two</option> -->
										</select>
									</div>
								</div>

								<!-- Textarea -->
								<div class="form-group">
									<label class="col-md-4 control-label" for="definition">Definición</label>
									<div class="col-md-6">
										<textarea class="form-control" placeholder="Definición"
											id="definition" name="definition" required="required"></textarea>
										<input id="definitionFilePath" name="definitionFilePath"
											class="input-file" type="file" accept="video/*">
									</div>
								</div>

								<!-- Textarea -->
								<div class="form-group">
									<label class="col-md-4 control-label" for="explanation">Explicación</label>
									<div class="col-md-6">
										<textarea class="form-control" id="explanation"
											placeholder="Explicación" name="explanation"></textarea>
										<input id="explanationFilePath" name="explanationFilePath"
											class="input-file" type="file" accept="video/*">
									</div>
								</div>

								<!-- Textarea -->
								<div class="form-group">
									<label class="col-md-4 control-label" for="example">Ejemplos</label>
									<div class="col-md-6">
										<textarea class="form-control" id="example"
											placeholder="Ejemplos" name="example"></textarea>
										<input id="examplesFilePath" name="examplesFilePath"
											class="input-file" type="file" accept="video/*">
									</div>
								</div>

								<!-- 					Multiple Radios -->
								<!-- 					<div class="form-group"> -->
								<!-- 					  <label class="col-md-4 control-label" for="radios">Tipo de Archivo</label> -->
								<!-- 					  <div class="col-md-4"> -->
								<!-- 					  <div class="radio"> -->
								<!-- 					    <label for="youtubeType"> -->
								<!-- 					      <input name="youtubeType" id="youtubeType" value="1" checked="checked" type="radio"> -->
								<!-- 					      Video de YouTube -->
								<!-- 					    </label> -->
								<!-- 						</div> -->
								<!-- 					  <div class="radio"> -->
								<!-- 					    <label for="fileType"> -->
								<!-- 					      <input name="fileType" id="fileType" value="2" type="radio" disabled="disabled"> -->
								<!-- 					      Archivo Del Dispositivo -->
								<!-- 					    </label> -->
								<!-- 						</div> -->
								<!-- 					  </div> -->
								<!-- 					</div> -->

								<!-- Text input-->
								<!-- 					<div class="form-group"> -->
								<!-- 					  <label class="col-md-4 control-label" for="videoURL">URL</label>   -->
								<!-- 					  <div class="col-md-5"> -->
								<!-- 					  <input id="videoURL" name="videoURL" placeholder="URL de YouTube" class="form-control input-md" type="text"> -->
								<!-- 					  <span class="help-block">help</span>   -->
								<!-- 					  </div> -->
								<!-- 					</div> -->

								<!-- Button (Double) -->
								<div class="form-group">
									<!-- 										  <label class="col-md-4 control-label" for="button1id">Acción</label> -->
									<div class="col-md-6 col-md-offset-6">
										<button id="addTermSubmitForm" name="addTermSubmitForm"
											class="btn btn-success">Enviar</button>
										<!-- 										    <button id="button2id" name="button2id" class="btn btn-danger">Descartar</button> -->
									</div>
								</div>
							</fieldset>
						</form>
					</div>
				</div>
				<!-- 					  </div> -->
			</div>
			<div role="tabpanel" class="tab-pane" id="requestTerm">
				<div class="panel panel-default">
					<div class="panel-body">

						<form class="form-horizontal" name="requestTermForm"
							id="requestTermForm" enctype="multipart/form-data">
							<fieldset>

								<!-- Form Name -->
								<legend>Solicitar Término</legend>

								<!-- Text input-->
								<div class="form-group">
									<label class="col-md-4 control-label" for="requestedWordName">Término</label>
									<div class="col-md-4">
										<input id="requestedWordName" name="requestedWordName"
											placeholder="Término" class="form-control input-md"
											type="text" required="required">
									</div>
								</div>

								<!-- Textarea -->
								<div class="form-group">
									<label class="col-md-4 control-label" for="requestedDescription">Descripción</label>
									<div class="col-md-6">
										<textarea class="form-control"
											placeholder="requestedDescription" id="requestedDescription"
											name="requestedDescription" required="required"></textarea>
									</div>
								</div>

								<!-- Button (Double) -->
								<div class="form-group">
									<!-- 										  <label class="col-md-4 control-label" for="button1id">Acción</label> -->
									<div class="col-md-6 col-md-offset-6">
										<button id="requestTermSubmitForm" name="requestTermSubmitForm"
											class="btn btn-success">Enviar</button>
										<!-- 										    <button id="button2id" name="button2id" class="btn btn-danger">Descartar</button> -->
									</div>
								</div>
							</fieldset>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Modals inclusion -->
	<#include "/modal/loadingModal.ftl"> <#include"/modal/addTermModal.ftl">
	<#include "/modal/requestTermModal.ftl">
	
</div>

</@c.page>