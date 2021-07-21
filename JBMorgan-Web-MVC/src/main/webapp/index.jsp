<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	
	<!--카카오톡 로그인 -->
	<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
	
	<script>
	  	// bbe9ec6b57b56d8ef60c464ef2214a90 // 자바스크립트 카카오 key값
	  	// 화면이 열리자마자~ 어떤 웹 어플리케이션이 연동될지 검증됨
	  	window.Kakao.init("bbe9ec6b57b56d8ef60c464ef2214a90");
	  	
	  	function kakaoLogin() {
	  		//문서에 보면 다나와있음
	  		//로그인 버튼 누르면 실행됨
	  		window.Kakao.Auth.login({
	  			
	  			scope : 'account_email, gender',
	  			
	  			//authObj 받아온 오브젝트 데이터
	  			success: function(authObj){
	  				console.log(authObj)
	  				//로그인된 상태에서 유저정보들 받아오기
	  				window.Kakao.API.request({
	  				//로그인한 사용자의 정보를 받아옴
	  					url:'/v2/user/me',
	  					success : res => {
	  						
	  						const kakao_account = res.kakao_account
	  						console.log(kakao_account);
	  					}
	  					
	  				});
	  				
	  				
	  			}
	  		});
	  		
	  	}
	  </script>
		
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
                                    <c:if test="${empty userVO}">
	                                    <a class="read_more" href="<%= request.getContextPath()%>/login/loginForm.jb">로그인</a>
	                                    <a class="read_more" href="javascript:kakaoLogin();"><img src="https://www.gb.go.kr/Main/Images/ko/member/certi_kakao_login.png" style="height: 100%"></a>
	                                    <a class="read_more" href="<%= request.getContextPath()%>/login/registerForm.jb">회원가입</a>
                                    </c:if>
                                    <c:if test="${not empty userVO}">
                                    	<a class="read_more" href="<%= request.getContextPath()%>/bank/createAccountForm.jb">계좌개설</a>
	                                    <a class="read_more" href="<%= request.getContextPath()%>/bank/searchAccount.jb">계좌조회</a>
                                    </c:if>
                                    
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