<#import "common.ftl" as c/>
<@c.page css="resources/css/contact.css" js="resources/js/contact.js">
    <!-- custom page content -->
        
   <div class="jumbotron jumbotron-sm">
    <div class="container">
        <div class="row">
            <div class="col-sm-12 col-lg-12">
                <h1 class="h1">
                    Contáctenos <small>Haganos saber su valiosa opinión</small></h1>
            </div>
        </div>
    </div>
</div>
<div class="container">
    <div class="row">
        <div class="col-md-8">
            <div class="well well-sm">
                <form id="contactForm" name="contactForm">
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="contactName">
                                Name</label>
                            <input type="text" class="form-control" id="contactName" name="contactName" placeholder="Enter name" required="required" />
                        </div>
                        <div class="form-group">
                            <label for="contactEmail">
                                Email Address</label>
                            <div class="input-group">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-envelope"></span>
                                </span>
                                <input type="email" class="form-control" id="contactEmail" name="contactEmail" placeholder="Enter email" required="required" /></div>
                        </div>
                        <div class="form-group">
                            <label for="contactSubject">
                                Subject</label>
                            <select id="contactSubject" name="contactSubject" class="form-control" required="required">
                                <option value="na" selected="">Choose One:</option>
                                <option value="service">General Customer Service</option>
                                <option value="suggestions">Suggestions</option>
                                <option value="product">Product Support</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="contactMessage">
                                Mensaje</label>
                            <textarea name="contactMessage" id="contactMessage" class="form-control" rows="9" cols="25" required="required"
                                placeholder="Mensaje"></textarea>
                        </div>
                    </div>
                    <div class="col-md-12">
                        <button type="submit" class="btn btn-primary pull-right" id="btnContactUs">
                            Enviar</button>
                    </div>
                </div>
                </form>
            </div>
        </div>
        <div class="col-md-4">
            <form>
            <legend><span class="glyphicon glyphicon-globe"></span> Nuestra Ubicación</legend>
            <address>
                <strong>Diccionario Lesco</strong><br>
                4 Reinas<br>
                Tibás, San José 11305<br>
                <abbr title="Phone">
                    P:</abbr>
                (123) 456-7890
            </address>
            <address>
                <strong>Dirección Electrónica</strong><br>
                <a href="mailto:#">diccionariolesco@gmail.com</a>
            </address>
            </form>
        </div>
    </div>
    <div class="row">
    	<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
		  <div class="modal-dialog modal-sm" role="document">
		    <div class="modal-content">
		      ...
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		        <button type="button" class="btn btn-primary">Save changes</button>
		      </div>
		    </div>
		  </div>
		</div>
    </div>
</div>
   
    
</@c.page>