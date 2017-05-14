 <!-- Not needed button -->
<!-- 	  <button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#processing-modal"> -->
<!--             <i class="glyphicon glyphicon-play"></i> Start Processing -->
<!--       </button>   -->
  
  <!-- Loading indicator modal. Taken from: http://bootsnipp.com/snippets/d3mxM-->
  <!-- Static Modal -->
<!-- Modal -->
<div class="modal fade" id="myPasswordModal" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Recuperar Contraseña</h4>
      </div>
      <div class="modal-body">
        <p>Por favor ingrese la misma dirección de correo registrada en el sistema, ahí recibirá un correo con su nueva contraseña</p>
      </div>
      <form id="passwordRecoveryForm" name="passwordRecoveryForm">
      <div class="form-group">
      	<div class="col-md-12">
      	
        	<input type="text" placeholder="Email" class="form-control" id="loginEmailAddressModal" name="loginEmailAddressModal">
        </div>
      </div>
      <br>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
        <button type="submit" class="btn btn-primary">Enviar</button>
      </div>
      </form>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->