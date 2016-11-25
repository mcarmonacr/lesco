<#import "common.ftl" as c/>
<@c.page css="resources/css/carousel.css">
    <!-- custom page content -->


    <!-- Carousel
    ================================================== -->
    <div id="myCarousel" class="carousel slide" data-ride="carousel">
      <!-- Indicators -->
      <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
      </ol>
      <div class="carousel-inner" role="listbox">
        <div class="item active">
<!--           <img class="first-slide" src="resources/images/logo-ucr2.png" alt="First slide"> -->
          <img class="first-slide" src="resources/images/free-background-wallpaper.jpg" alt="First slide">
          <div class="container">
            <div class="carousel-caption">
              <h1>Universidad de Costa Rica</h1>
              <p>La Universidad de Costa Rica (también llamada por sus siglas, UCR) es una de las cinco universidades públicas de la República de Costa Rica y una de las más prestigiosas y reconocidas de América Latina.</p>
              <p><a class="btn btn-lg btn-primary" href="/DiccionarioLesco/acerca/ucr" role="button">Conocer Más</a></p>
            </div>
          </div>
        </div>
        <div class="item">
          <img class="second-slide" src="resources/images/free-background-wallpaper.jpg" alt="Second slide">
          <div class="container">
            <div class="carousel-caption">
              <h1>LESCO</h1>
              <p>El LESCO es el  Lenguaje de Señas Costarricense. También es conocido como el Lenguaje de Signos Costarricense que es utilizado para comunicarse con personas sordas.</p>
              <p><a class="btn btn-lg btn-primary" href="#" role="button">Conocer Más</a></p>
            </div>
          </div>
        </div>
        <div class="item">
          <img class="third-slide" src="resources/images/free-background-wallpaper.jpg" alt="Third slide">
          <div class="container">
            <div class="carousel-caption">
              <h1>ECCI</h1>
              <p>La Escuela de Ciencias de la Computación e Infomática de la Universidad de Costa Rica se fundó en 1981, como resultado de la fusión de dos programas distintos pero relacionados, el de Bachillerato en Informática y el de Bachillerato y Licenciatura en Computación</p>
              <p><a class="btn btn-lg btn-primary" href="#" role="button">Conocer Más</a></p>
            </div>
          </div>
        </div>
      </div>
      <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
      </a>
      <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
      </a>
    </div><!-- /.carousel -->


    <!-- Marketing messaging and featurettes
    ================================================== -->
    <!-- Wrap the rest of the page in another container to center all the content. -->

    <div class="container marketing">

      <!-- Three columns of text below the carousel -->
      <div class="row">
      
      <h1 class="autores">Autores</h1>
      
      <br>
      
        <div class="col-lg-4">
          <img class="img-circle" src="resources/images/marioCarmona2.PNG" alt="Mario Alonso Carmona Dinarte" width="140" height="140">
          <h2>Mario Alonso Carmona Dinarte</h2>
          <p>Bachiller y estudiante del grado de licenciatura de la Escuela de Ciencias de la Computación e Informática (ECCI) de la Universidad de Costa Rica.</p>
<!--           <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p> -->
        </div><!-- /.col-lg-4 -->
        <div class="col-lg-4">
          <img class="img-circle" src="resources/images/luisGuerrero.png" alt="Luis Alberto Guerrero Blanco" width="140" height="140">
          <h2>Luis Alberto Guerrero Blanco</h2>
          <p>Profesor y actualmente coordinador Programa de Doctorado de la Escuela de Ciencias de la Computación e Informática (ECCI) de la Universidad de Costa Rica.</p>
<!--           <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p> -->
        </div><!-- /.col-lg-4 -->
        <div class="col-lg-4">
          <img class="img-circle" src="resources/images/luisQuesada2.jpg" alt="Luis Quesada Quirós" width="140" height="140">
          <h2>Luis Quesada Quirós</h2>
          <p>Profesor e Investigador de la Escuela de Ciencias de la Computación e Informática (ECCI) de la Universidad de Costa Rica.</p>
<!--           <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p> -->
        </div><!-- /.col-lg-4 -->
      </div><!-- /.row -->


      <!-- START THE FEATURETTES -->

      <hr class="featurette-divider">

      <div class="row featurette">
        <div class="col-md-7">
          <h2 class="featurette-heading">First featurette heading. <span class="text-muted">It'll blow your mind.</span></h2>
          <p class="lead">Donec ullamcorper nulla non metus auctor fringilla. Vestibulum id ligula porta felis euismod semper. Praesent commodo cursus magna, vel scelerisque nisl consectetur. Fusce dapibus, tellus ac cursus commodo.</p>
        </div>
        <div class="col-md-5">
          <img class="featurette-image img-responsive center-block" data-src="holder.js/500x500/auto" src="resources/images/Sign_language_families.png" alt="Generic placeholder image">
<!--           https://upload.wikimedia.org/wikipedia/en/2/2f/Sign_language_families.png -->
        </div>
      </div>

      <hr class="featurette-divider">

      <div class="row featurette">
        <div class="col-md-7 col-md-push-5">
          <h2 class="featurette-heading">Oh yeah, it's that good. <span class="text-muted">See for yourself.</span></h2>
          <p class="lead">Donec ullamcorper nulla non metus auctor fringilla. Vestibulum id ligula porta felis euismod semper. Praesent commodo cursus magna, vel scelerisque nisl consectetur. Fusce dapibus, tellus ac cursus commodo.</p>
        </div>
        <div class="col-md-5 col-md-pull-7">
          <img class="featurette-image img-responsive center-block" data-src="holder.js/500x500/auto" src="resources/images/sign-language.jpg" alt="Generic placeholder image">
<!--           https://s-media-cache-ak0.pinimg.com/736x/09/53/50/095350844e504af390b80bd7a30e7142.jpg -->
        </div>
      </div>

      <hr class="featurette-divider">

      <div class="row featurette">
        <div class="col-md-7">
          <h2 class="featurette-heading">And lastly, this one. <span class="text-muted">Checkmate.</span></h2>
          <p class="lead">Donec ullamcorper nulla non metus auctor fringilla. Vestibulum id ligula porta felis euismod semper. Praesent commodo cursus magna, vel scelerisque nisl consectetur. Fusce dapibus, tellus ac cursus commodo.</p>
        </div>
        <div class="col-md-5">
          <img class="featurette-image img-responsive center-block" data-src="holder.js/500x500/auto" src="resources/images/dictionary.jpg" alt="Generic placeholder image">
<!--           http://websearch.com/cms/imgfolder/dictionary2-flash.jpg -->
        </div>
      </div>

      <hr class="featurette-divider">

      <!-- /END THE FEATURETTES -->
   
    
</@c.page>