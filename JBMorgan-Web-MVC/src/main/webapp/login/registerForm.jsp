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
       	function doRegister(){
       		
       		let r = document.registerForm
       		
       		if(r.id.value == '' ){
       			
       			alert('아이디를 입력하세요.')
    			r.id.focus()
    			return false
       		}
       		
       		if(r.password.value ==''){
       			
       			alert('패스워드를 입력하세요.')
       			r.password.focus()
       			return false      				
       			
       		}
       		
			if(r.name.value ==''){
       			
       			alert('이름을 입력하세요.')
       			r.name.focus()
       			return false
       		}
			
			if(r.phone.value ==''){
       			
       			alert('전화번호를 입력하세요.')
       			r.phone.focus()
       			return false
       			
       		}
       		
			if(r.email.value ==''){
		
				alert('이메일을 입력하세요.')
				r.email.focus()
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
                     <h2>회원가입</h2>
                  </div>
                  <form action="<%=request.getContextPath()%>/login/register.jb" id="request" class="main_form" 
                  name="registerForm" onsubmit="return doRegister()">
                     <div class="row">
                        <div class="col-md-12">
                           <input class="contactus" placeholder="id" type="text" name="id">
                        </div>
                        <div class="col-md-12">
                           <input class="contactus" placeholder="password" type="password" name="password">                        
                        </div>
                        <span class="col-md-12">
                           <input class="contactus" style="width: 50%; float: left" placeholder="SSN-Front" type="text" name="ssnf">
                           <input class="contactus" style="width: 50%; float: left" placeholder="SSN-Back" type="password" name="ssnb">                
                        </span>
                        <div class="col-md-12">
                           <input class="contactus" placeholder="name" type="text" name="name">                          
                        </div>
                        <div class="col-md-12">
                           <input class="contactus" placeholder="phone" type="text" name="phone">                          
                        </div>
                        <div class="col-md-12">
                           <input class="contactus" placeholder="email" type="text" name="email">                          
                        </div>
                        <div class="col-md-12">
                           <button class="send_btn">회원가입</button>
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