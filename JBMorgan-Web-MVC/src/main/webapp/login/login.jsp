<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

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
   <body class="main-layout inner_page">
      <!-- loader  -->
      <div class="loader_bg">
         <div class="loader"><img src="images/loading.gif" alt="" /></div>
      </div>
      <!-- end loader -->
      
      <!-- header -->
      <header> 
      		<jsp:include page="/include/topMenu.jsp" />
	  </header>
      <!-- end header -->
      
      <!-- contact -->
      <div class="contact">
         <div class="container">
            <div class="row ">
               <div class="col-md-8 offset-md-2">
                  <div class="titlepage text_align_left">
                     <h2>로그인</h2>
                  </div>
                  <form id="request" class="main_form">
                     <div class="row">
                        <div class="col-md-12">
                           <input class="contactus" placeholder="id" type="text" name="id"> 
                        </div>
                        <div class="col-md-12">
                           <input class="contactus" placeholder="password" type="password" name="password">                          
                        </div>
                        <div class="col-md-12">
                           <button class="send_btn">로그인</button>
                        </div>
                     </div>
                  </form>
               </div>
            </div>
         </div>
      </div>
      <!-- contact -->
      
      <!-- footer -->
      <footer>
          <jsp:include page="/include/bottom.jsp" />
      </footer>
      <!-- end footer -->
      
   </body>
</html>