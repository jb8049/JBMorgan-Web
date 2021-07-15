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
      <link rel="stylesheet" href="css/bootstrap.min.css">
      <!-- style css -->
      <link rel="stylesheet" href="css/style.css">
      <!-- responsive-->
      <link rel="stylesheet" href="css/responsive.css">
      <!-- awesome fontfamily -->
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
      <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script><![endif]-->
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
         <div class="header">
            <div class="container-fluid">
               <div class="row d_flex">
                  <div class=" col-md-2 col-sm-3 col logo_section">
                     <div class="full">
                        <div class="center-desk">
                           <div class="logo">
                              <a href="index.html"><img src="images/logo.png" alt="#" /></a>
                           </div>
                        </div>
                     </div>
                  </div>
                  <div class="col-md-8 col-sm-9">
                     <nav class="navigation navbar navbar-expand-md navbar-dark ">
                        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample04" aria-controls="navbarsExample04" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                        </button>
                        <div class="collapse navbar-collapse" id="navbarsExample04">
                           <ul class="navbar-nav mr-auto">
                              <li class="nav-item">
                                 <a class="nav-link" href="index.html">Home</a>
                              </li>
                              <li class="nav-item">
                                 <a class="nav-link" href="about.html">About</a>
                              </li>
                              <li class="nav-item">
                                 <a class="nav-link" href="we_do.html">What we do</a>
                              </li>
                              <li class="nav-item">
                                 <a class="nav-link" href="portfolio.html">Portfolio </a>
                              </li>
                              <li class="nav-item active">
                                 <a class="nav-link" href="contact.html">Contact Us</a>
                              </li>
                           </ul>
                        </div>
                     </nav>
                  </div>
                  <div class="col-md-2 d_none">
                     <ul class="email text_align_right">
                        <li> <a href="Javascript:void(0)"> Login </a></li>
                        <li> <a href="Javascript:void(0)"> <i class="fa fa-search" style="cursor: pointer;" aria-hidden="true"> </i></a> </li>
                     </ul>
                  </div>
               </div>
            </div>
         </div>
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
         <div class="footer">
            <div class="container">
               <div class="row">
               </div>
            </div>
            <div class="copyright text_align_left">
               <div class="container">
                  <div class="row d_flex">
                     <div class="col-md-6">
                        <p>© 2021 All Rights Reserved.  <a href="#">J.B.Morgan Banking</a></p>
                     </div>
                     <div class="col-md-6">
                        <ul class="social_icon text_align_center">
                           <li> <a href="Javascript:void(0)"><i class="fa fa-facebook-f"></i></a></li>
                           <li> <a href="Javascript:void(0)"><i class="fa fa-twitter"></i></a></li>
                           <li> <a href="Javascript:void(0)"><i class="fa fa-linkedin-square" aria-hidden="true"></i></a></li>
                           <li> <a href="Javascript:void(0)"><i class="fa fa-instagram" aria-hidden="true"></i></a></li>
                           <li> <a href="Javascript:void(0)"><i class="fa fa-youtube-play" aria-hidden="true"></i></a></li>
                        </ul>
                     </div>
                  </div>
               </div>
            </div>
         </div>
      </footer>
      <!-- end footer -->
      <!-- Javascript files-->
      <script src="js/jquery.min.js"></script>
      <script src="js/bootstrap.bundle.min.js"></script>
      <script src="js/jquery-3.0.0.min.js"></script>
      <script src="js/custom.js"></script>
   </body>
</html>