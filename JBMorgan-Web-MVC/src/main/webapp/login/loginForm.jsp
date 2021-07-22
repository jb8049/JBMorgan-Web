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
	  			
	  			scope : 'account_email',
	  			
	  			//authObj 받아온 오브젝트 데이터, access 토큰 값 들어있음
	  			success: function(authObj){
	  				//console.log(authObj)
	  				
	  				//로그인된 상태에서 유저정보들 받아오기
	  				window.Kakao.API.request({
	  					//로그인한 사용자의 정보를 받아옴
	  					url:'/v2/user/me',
	  					success : res => {
	  						
	  						const kakao_account = res.kakao_account
	  						console.log(kakao_account); // 변수에 해당하는 값 출력
	  						//console.log(res.id)
	  						location.href ="<%=request.getContextPath()%>/login/kakaoLoginProcess.jb?kakao_id=" + res.id ;
	  					}
	  					
	  				});
	  				
	  				
	  			}
	  		});
	  		
	  	}
	  </script>
      
      
	
      <script>
      
      		if('${ msg }')
      			alert('${ msg }')
      		
      		
      		function loginChk(){
      			
      			let l = document.loginForm
      			
      			if(l.id.value == ''){
      				
      				alert('아이디를 입력하세요.')
      				l.id.focus()
      				return false
      			}
      			
      			if(l.password.value =='' ){
      				
      				alert('비밀번호를 입력하세요.')
      				l.password.focus()
      				return false
      			}
      			
      			return true
      		}
      			
      			
      </script>

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
                  <form action="<%= request.getContextPath()%>/login/loginProcess.jb" id="request" class="main_form" method="post" onsubmit="return loginChk()" name="loginForm">
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
                  <br>
                  <div class="col-md-12" style="padding-left: 0px;">
                  	<a class="send_btn" href="javascript:kakaoLogin();"><img src="https://www.gb.go.kr/Main/Images/ko/member/certi_kakao_login.png" style="max-width: 252px; height: 70px"></a>
               	  </div>
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