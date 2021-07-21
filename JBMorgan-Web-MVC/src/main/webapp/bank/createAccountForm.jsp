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
      
      
      <script>
      
      		if('${ AccountMsg }')
      			alert('${ AccountMsg }')
      		
      		function createAccountChk(){
      		
      			 let c = document.createAccountForm
      			
      			if(c.password.value == ''){
      				
      				alert('비밀번호를 입력하세요.')
      				c.password.focus()
      				return false
      			}
      			
				if(c.phone.value =='' ){
      				
      				alert('전화번호를 입력하세요.')
      				c.phone.focus()
      				return false
      			}
				
				
				if(c.ssnf.value =='' ){
      				
					alert('주민번호 앞자리를 입력하세요.')
      				c.ssnf.focus()
      				return false
      			}
				
				if(c.ssnb.value =='' ){
      				
      				alert('주민번호 뒷자리를 입력하세요.')
      				c.ssnb.focus()
      				return false
      			}
				
				if(c.accountName.value =='' ){
					
      				alert('계좌명을 입력하세요.')
      				c.accountName.focus()
      				return false
      			}
				
				if(c.accountPassword.value =='' ){
					
      				alert('계좌의 비밀번호를 입력하세요.')
      				c.accountPassword.focus()
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
                     <h2>계좌개설</h2>
                  </div>
                  <form action="<%= request.getContextPath()%>/bank/createAccountProcess.jb" id="request" class="main_form" method="post" onsubmit="return createAccountChk()" name="createAccountForm">
                  	<input type="hidden" name=id value='${userVO.id}'>
                  	<input type="hidden" name=name value='${userVO.name}'>
                     <div class="row">
                        <div class="col-md-12">
                           <input class="contactus" placeholder="password" type="password" name="password">                          
                        </div>
                        <div class="col-md-12">
                           <input class="contactus" placeholder="phone" type="text" name="phone">                          
                        </div>
                        <span class="col-md-12">
                           <input class="contactus" style="width: 50%; float: left" placeholder="SSN-Front" type="text" name="ssnf">
                           <input class="contactus" style="width: 50%; float: left" placeholder="SSN-Back" type="password" name="ssnb">                
                        </span>
                        <div class="col-md-12">
                           <input class="contactus" placeholder="Account_Name" type="text" name="accountName">                          
                        </div>
                        <div class="col-md-12">
                           <input class="contactus" placeholder="Account_Password" type="password" name="accountPassword">                          
                        </div>
                        <div class="col-md-12">
                           <button class="send_btn">계좌개설</button>  
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