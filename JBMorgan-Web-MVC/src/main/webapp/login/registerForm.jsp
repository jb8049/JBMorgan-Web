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
<link rel="stylesheet"
	href="/JBMorgan-Web-MVC/resources/css/bootstrap.min.css">
<!-- style css -->
<link rel="stylesheet" href="/JBMorgan-Web-MVC/resources/css/style.css?after">
<!-- responsive-->
<link rel="stylesheet"
	href="/JBMorgan-Web-MVC/resources/css/responsive.css">
<!-- awesome fontfamily -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<!-- Javascript files-->
<script src="/JBMorgan-Web-MVC/resources/js/jquery.min.js"></script>
<script src="/JBMorgan-Web-MVC/resources/js/bootstrap.bundle.min.js"></script>
<script src="/JBMorgan-Web-MVC/resources/js/jquery-3.0.0.min.js"></script>
<script src="/JBMorgan-Web-MVC/resources/js/custom.js"></script>


<script>

let checkId = false; // 중복확인 성공하면 true로 변경
let checkSms = false;
let checkEmail = false;




       	function doRegister(){
       		
       		let r = document.registerForm
       		
       		 if(r.id.value == '' ){
       			
       			$('#TestModal').modal('show');
				$('#modal-body').html('아이디를 입력하세요.')
				
    			return false
       		}
       		
       		
       		
       		if(r.password.value ==''){
       			
       			$('#TestModal').modal('show');
				$('#modal-body').html('패스워드를 입력하세요.')
       			return false      				
       			
       		}
       		
       		
			if(r.ssnf.value =='') {
				
				$('#TestModal').modal('show');
				// modal-body의 <p>태그에 원하는 메세지 삽입
				$('#modal-body').html('주민번호 앞자리를 입력하세요.')
				
				return false
			}
			
			if(r.ssnb.value =='') {
				
				$('#TestModal').modal('show');
				$('#modal-body').html('주민번호 뒷자리를 입력하세요.')
				
				return false
			}
       		
			if(r.name.value ==''){
       			
				$('#TestModal').modal('show');
				$('#modal-body').html('이름을 입력하세요.')
       			return false
       		}
			
			if(r.phone.value ==''){
       			
				$('#TestModal').modal('show');
				$('#modal-body').html('전화번호를 입력하세요.')
       			return false
       			
       		}
			
		
			/* if($('#authCheck').val() == '인증필요'){
				
				$('#TestModal').modal('show')
				$('#modal-body').html('문자 인증을 진행해주세요.')
				return false
				
			}
			
			if($('#authCheck').val() == '인증실패'){
				
				$('#TestModal').modal('show')
				$('#modal-body').html('문자 인증번호를 잘못 입력하셨습니다.')
				return false
				
			} */
			
			if(r.email.value ==''){
		
				$('#TestModal').modal('show');
				$('#modal-body').html('이메일 주소를 입력하세요.')
				return false
			}
			
			/* if($('#emailCheck').val() == '인증필요'){
				
				$('#TestModal').modal('show')
				$('#modal-body').html('이메일 인증을 진행해주세요.')
				return false
				
			}
			
			if($('#emailCheck').val() == '인증실패'){
				
				$('#TestModal').modal('show')
				$('#modal-body').html('이메일 인증번호를 잘못 입력하셨습니다.')
				return false
				
			} */
			
			
			// 문자열 비교x, 체크 하는 부분을 true, false로
			
			if(!checkId){
       			
       			$('#TestModal').modal('show');
       			$('#modal-body').html('아이디 중복체크를 해주세요.')
       			
       			return false
       			
       		}
			
			if(!checkSms){ // 전화번호 인증을 완료하면(성공하면) checkSms가 true가 됨
				$('#TestModal').modal('show')
				$('#modal-body').html('전화번호를 인증하세요.')
				return false
			}
			
			if(!checkEmail){
				
				$('#TestModal').modal('show')
				$('#modal-body').html('이메일 인증하세요.')
				
				return false
				
			}
			
       		return true
       	}
      
      
      </script>



<script>

$(document).ready(function(){
	
	$('#idCheck').click(function(){
		
		let id = $('#inputId').val()
		
		$.ajax({
			
			url : "<%=request.getContextPath()%>/login/IdCheck.jb",
				data : { id : id },
				dataType: "text",
				success : function(text){
					
					
					if(text.trim() == 'success'){
						
						$('#idCheck').val('사용가능')
						$('#idCheck').attr('disabled', true)
						$('#idCheck').css({'color' : 'black', 'background-color' : 'rgb(118, 118, 118)'})
						checkId = true; // 아이디 중복체크를 성공하는 경우, false로 되어있는 checkId를 변경함
						
					}else{
						// modal 수동 호출
						$('#idCheckModal').modal('show');
					}
					

				
			}, error:function(request,status,error){
	             alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	          }

			
		})
		
		
	})
	
	// 사용자가 입력한 번호와 동일한지 체크해야함
	let authNo = '' 
	
	// 문자인증 클릭
	$('#sendMessage').click(function(){
		
		
		$.ajax({
			
			url : "<%=request.getContextPath()%>/login/smsAuth.jb",
			data : { phoneNumber : $('#phoneNumber').val() },
			
			success : function(result){
				
				//사용자가 올바르게 입력해야할 인증번호
				authNo = result.trim()
				
				// 모달창으로 인증번호가 전송되었다고 알려주기
				$('#authCheckModal').modal('show')
				$('#sendMessage').val('재전송')
				$('#sendMessage').attr('disabled',true)
				$('#sendMessage').css({'color' : 'black', 'background-color' : 'rgb(118, 118, 118)'})
				
			}, error:function(request,status,error){
	             alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	           }
			
			
			
			
		})
		
		
	}) 
	
	$('#InputAuthNo').keyup(function(){
		
		//keyup을 했을 때, 인증번호를 받지 않은 경우 멘트 필요함
		//인증버튼을 누르지 않아, authNo에 값이 없는 경우
		if(authNo == ''){
			$('#authCheck').html('문자 인증을 진행해주세요.')
			//$('#authCheck').val('인증필요')
			
		}else if(authNo == $('#InputAuthNo').val()){
			
			$('#authCheck').html('인증완료')
			//$('#authCheck').val('인증완료')
			$('#authCheck').css({'color' : 'blue'})
			checkSms = true; //인증완료 했을 때만 true로 바꾸면된다.. 나머지 일 때는 false로 두면 됨...
			
		}else{
			$('#authCheck').html('인증 번호를 잘못 입력하셨습니다.')
			//$('#authCheck').val('인증실패')
			
		}
		
	})
	
	
	let emailAuthNo = ''
	
	$('#sendEmail').click(function(){
		
		
		$.ajax({
			
			url : "<%=request.getContextPath()%>/login/emailAuth.jb",
			data : { emailAddr : $('#emailAddr').val()},
			
			success : function(result){
				
				// 사용자가 올바르게 입력해야할 인증번호
				emailAuthNo = result.trim()
				
				$('#authCheckModal').modal('show')
				$('#sendEmail').val('재전송')
				$('#sendEmail').attr('disabled',true)
				$('#sendEmail').css({'color' : 'black', 'background-color' : 'rgb(118, 118, 118)'})
			
			}, error:function(request,status,error){
	             alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	           }
			
			
		})
		
	})
	
	
	//emailCheck 인증 여부 메세지 띄워주는 span
	$('#InputEmailAuthNo').keyup(function(){
		
		if(emailAuthNo == ''){
			
			//span태그안에 넣어서 값을 나타내려면 .html로 넣어야했음
			//인증 유무에 따라 onsubmit()에서 분기해주려고 하니까 .html로 조건문이 안됐음
			//그래서 .val에 넣어서 if == '인증필요' 이런식으로 분기함, #emailCheck에 .val만 넣으니까 값은 안나타남 
			$('#emailCheck').html('이메일 인증을 진행해주세요.')
			//$('#emailCheck').val('인증필요')  
			
		}else if( emailAuthNo == $('#InputEmailAuthNo').val()){ // 실제 인증번호와 사용자가 입력한 인증번호가 동일한 경우
			
			$('#emailCheck').html('인증완료')
			//$('#emailCheck').val('인증완료')
			$('#emailCheck').css({'color' : 'blue'})
			checkEmail = true;
			
			
		}else{
			
			$('#emailCheck').html('인증번호를 잘못 입력하셨습니다.')
			//$('#emailCheck').val('인증실패')
		}
	})
	
	
	
	
	
})

</script>



</head>

<!-- body -->
<body class="main-layout inner_page">
	<!-- loader  -->
	<div class="loader_bg">
		<div class="loader">
			<img src="images/loading.gif" alt="" />
		</div>
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
					<form action="<%=request.getContextPath()%>/login/register.jb"
						id="request" class="main_form" name="registerForm"
						onsubmit="return doRegister()">
						<div class="row">
							<div class="col-md-12">
								<span> 
									<input class="contactus" id="inputId" placeholder="id" type="text" name="id" style="width: 80%; float: left"> 
									<input class="send_btn" id="idCheck" type="button" style="width: 20%; padding-top: 20px; ;height : 71px ;float: left;" value="중복확인" />
								</span>
							</div>
							<div class="col-md-12">
								<input class="contactus" placeholder="password" type="password"
									name="password">
							</div>
							<span class="col-md-12"> <input class="contactus" style="width: 50%; float: left" placeholder="SSN-Front" type="text" name="ssnf"> <input class="contactus"
								style="width: 50%; float: left" placeholder="SSN-Back"
								type="password" name="ssnb">
							</span>
							<div class="col-md-12">
								<input class="contactus" placeholder="name" type="text"
									name="name">
							</div>
							<div class="col-md-12">
								<span>
								<input class="contactus" id="phoneNumber" placeholder="phone" type="text" name="phone" style="width :80% ;float: left">
								<input class="send_btn" id="sendMessage" type="button" style="width: 20%; padding-top: 20px; ;height : 71px ;float: left;" value="문자인증" />
								</span>
							</div>
							<div class="col-md-12">
								<span><input class="contactus" id="InputAuthNo" placeholder="authentication No" type="text" required style="margin-bottom: 0px;"></span>
								<span id=authCheck style="color: red; font-size: 15px; padding-left: 15px;"></span>
							</div>
							<div class="col-md-12">
								<span>
									<input class="contactus" id="emailAddr" placeholder="email" type="text" name="email" style="margin-top: 25px; float: left; width: 80%">
									<input class="send_btn" id="sendEmail" type="button" style="width: 20%; padding-top: 20px; margin-top: 25px ;height : 71px ;float: left;" value="이메일 인증" />
								</span>
							</div>
							
							<div class="col-md-12">
								<span><input class="contactus" id="InputEmailAuthNo" placeholder="authentication Email" type="text" required style="margin-bottom: 0px;"></span>
								<span id=emailCheck style="color: red; font-size: 15px; padding-left: 15px;"></span>
							</div>
							<div class="col-md-12" style="margin-top: 25px;">
								<input class="send_btn" type="submit" value="회원가입" />
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	<!-- ID Check Modal  -->
	<div class="modal fade" id="idCheckModal" role="dialog" style="text-align: center;">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">

				<div class="modal-header">
					<!-- <button type="button" class="close" data-dismiss="modal"
						style="padding-top: 5px">×</button> -->
				</div>

				<div class="modal-body">
						사용하실 수 없는 아이디 입니다.
				</div>
				
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">확인</button>
				</div>
			</div>
		</div>
	</div>
	
	
	<!-- AuthNo Modal  -->
	<div class="modal fade" id="authCheckModal" role="dialog" style="text-align: center;">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">

				<div class="modal-header">
					<!-- <button type="button" class="close" data-dismiss="modal"
						style="padding-top: 5px">×</button> -->
				</div>

				<div class="modal-body">
						인증번호가 전송되었습니다.
				</div>
				
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">확인</button>
				</div>
			</div>
		</div>
	</div>
	
	
	<!-- Test modal -->
	<div class="modal fade" id="TestModal" role="dialog" style="text-align: center;">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">

				<div class="modal-header">
					<!-- <button type="button" class="close" data-dismiss="modal"
						style="padding-top: 5px">×</button> -->
				</div>

				<div class="modal-body" id="modal-body">
						<p></p>
				</div>
				
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">확인</button>
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