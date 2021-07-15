<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   
   <head>
      <!-- basic -->
      <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <!-- mobile metas -->
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <meta name="viewport" content="initial-scale=1, maximum-scale=1">
      <!-- site metas -->
      <title>JBMorgans</title>
      <meta name="keywords" content="">
      <meta name="description" content="">
      <meta name="author" content="">
      <!-- bootstrap css -->
      <link rel="stylesheet" href="/JBMorgan-Web-MVC/resources/css/bootstrap.min.css">
      <!-- style css -->
      <link rel="stylesheet" href="/JBMorgan-Web-MVC/resources/css/style.css">
      <!-- responsive-->
      <link rel="stylesheet" href="/JBMorgan-Web-MVC/resources/css/responsive.css">
      <!-- awesome fontfamily -->
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
   	
   	  <!-- Javascript files-->
      <script src="/JBMorgan-Web-MVC/resources/js/jquery.min.js"></script>
      <script src="/JBMorgan-Web-MVC/resources/js/bootstrap.bundle.min.js"></script>
      <script src="/JBMorgan-Web-MVC/resources/js/jquery-3.0.0.min.js"></script>
      <script src="/JBMorgan-Web-MVC/resources/js/custom.js"></script>

   </head>
   
   <!-- body -->
   <body class="main-layout">
      <!-- loader  -->
      <div class="loader_bg">
         <div class="loader"><img src="resources/images/loading.gif" alt="" /></div>
      </div>
      <!-- end loader -->
      
      <!-- header -->
      <header>
			<jsp:include page="/include/topMenu.jsp" />
      </header>
      <!-- end header -->
	   
      <!-- start slider section -->
      <div id="top_section" class=" banner_main">
         <div class="container">
            <div class="row">
               <div class="col-md-12">
                  <div id="myCarousel" class="carousel slide" data-ride="carousel">
                     <div class="carousel-inner">
                        <div class="carousel-item active">
                           <div class="container-fluid">
                              <div class="carousel-caption relative">
                                 <div class="bluid">
                                    <h1>생겨요, 좋은 일  <br>J.B.Morgan Banking</h1>
                                    <p>J.B.Morgan Banking은 편리하고 간편한 서비스를 통해<br>당신에게 더 나은 하루를 선물합니다.
                                    </p>
                                    <a class="read_more" href="<%= request.getContextPath()%>/login/loginForm.jb">로그인</a>
                                    <a class="read_more" href="<%= request.getContextPath()%>/login/registerForm.jb">회원가입</a>
                                 </div>
                              </div>
                           </div>
                        </div>
                     </div>
                  </div>
               </div>
            </div>
         </div>
      </div>
      <!-- end slider section -->
      
      <!-- footer -->
      <footer>
         <jsp:include page="/include/bottom.jsp" />
      </footer>
      <!-- end footer -->
      
      
   </body>
</html>