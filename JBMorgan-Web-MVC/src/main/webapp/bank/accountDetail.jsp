<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
<link rel="stylesheet" href="/JBMorgan-Web-MVC/resources/css/style.css">
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

<!-- Modal  -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<style>
      
      td, th {
    		padding: 5px;
      }
      
      table th {
			background-color: rgba(39, 43, 44, 0.87);;
			color: #ffffff;
	  }
       
</style>


<script>
	
	
	$(document).ready(function(){
		
		$('#historyBtn').click(function(){
		
			// 거래내역을 얻어올 계좌번호
			let accountNo = $('#accountNo').html() 
			
			$.ajax({
				
				url : "<%=request.getContextPath()%>/bank/TransactionList.jb",
				data : { accountNo : accountNo },
				// url, data를 전달한 결과값을 받아온 result
				// 거래내역 모달창 테이블에 들어갈 태그들이 들어가 있음
				success : function(result){
					
					// table에 이미 있는 요소의 다음에 넣어주려면 append 사용하면됨, 이미 있는 요소 앞에 삽입하려면 prepend
					
					$("#transactionListModal").empty()
					$("#transactionListModal").append(result)
					
					// ajax로 불러온 뒤, 불러온 jsp 태그를 인식할 수 있음
					// 가져오기 전에 c태그에 style로 변경해서 가져오기
					
				}, error:function(request,status,error){
		             alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		          }

				
			})
			
			
		})
		
		
		
	})


</script>

</head>

<!-- body -->
<body class="main-layout inner_page">
	<!-- loader  -->
	<div class="loader_bg">
		<div class="loader">
			<img src="/JBMorgan-Web-MVC/resources/images/loading.gif" alt="" />
		</div>
	</div>
	<!-- end loader -->

	<!-- header -->
	<header>
		<jsp:include page="/include/topMenu.jsp" />
	</header>
	<!-- end header -->

	<!-- 거래내역 Modal  -->

	<div class="modal fade" id="historyModal" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->

			<div class="modal-content">

				<div class="modal-header">

					<h4 class="modal-title">거래내역</h4>
					<button type="button" class="close" data-dismiss="modal"
						style="padding-top: 5px">×</button>

				</div>

				<div class="modal-body">
						<table id="transactionListModal" border=1px solid>
							<!-- ajax로 불러온 정보 출력  -->
						</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">확인</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 거래내역 Modal end  -->

	<!-- 이체 Modal  -->

	<div class="modal fade" id="transferModal" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">이체</h4>
					<button type="button" class="close" data-dismiss="modal"
						style="padding-top: 5px">×</button>
				</div>
				<div class="modal-body">

					<table border=1px solid>
						<tr>
							<th>계좌명</th>
							<th>계좌번호</th>
							<th>잔액</th>
						</tr>
						<tr>
							<td><c:out value="${ account.acct_name }" /></td>
							<td><c:out value="${ account.acct_no }" /></td>
							<td><c:out value="${ account.balance }" /></td>
						</tr>
					</table>
					<br>
					
					<form action="<%= request.getContextPath()%>/bank/transferProcess.jb" method="post">
						<input type="hidden" name=acct_no value="${ account.acct_no }">
						
						<table border=1px solid>
							<tr>
								<th>송금할 은행</th>
								<td>
								<input type="radio" name="bankCode" value="J" checked="checked">JBMorgan
								<input type="radio" name="bankCode" value="D">DonJo
								<input type="radio" name="bankCode" value="Y">YG
								<input type="radio" name="bankCode" value="S">SeJin
								</td>
							</tr>
							<tr>
								<th>상대방 계좌번호</th>
								<td><input type="text" name="counterAcctNo" autocomplete="off"></td>
							</tr>
							<tr>
								<th>송금할 금액</th>
								<td><input type="text" name="transferBalance" autocomplete="off"></td>
							</tr>
							<tr>
								<th>계좌 비밀번호</th>
								<td><input type="password" name="acctPassword"></td>
							</tr>
						</table>
						<div class="modal-footer" style="margin-top: 75px">
							<input type="submit" class="btn btn-default" value="이체">
							<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
						</div>
					</form>
				</div>


			</div>
		</div>

	</div>

	<!-- 이체 Modal end  -->

	<!-- section -->

	<div class="contact">
		<div class="container">
			<div class="row ">
				<div class="col-md-8 offset-md-2">
					<div class="titlepage text_align_left">
						<h2 style="margin-top: 4px">계좌상세</h2>
					</div>
					<div class="row">
						<div class="col-md-12" align="center"
							style="color: #666666 !important; font-size: 14px; font-family: 'Poppins', sans-serif; line-height: 1.80857; font-weight: normal;">
							<table border=1px solid style="width: 300px">
								<tr>
									<th width="100px">계좌번호</th>
									<td id="accountNo"><c:out value="${ account.acct_no }" /></td>
								</tr>
								<tr>
									<th>계좌명</th>
									<td><c:out value="${ account.acct_name }" /></td>
								</tr>
								<tr>
									<th>잔액</th>
									<td><c:out value="${ account.balance }" /></td>
								</tr>
								<tr>
									<th>개설일자</th>
									<td><c:out value="${ account.reg_date }" /></td>
								</tr>
							</table>
						</div>
						<div class="col-md-12" align="center" style="margin-top: 10px">
							<button type="button" data-toggle="modal" data-target="#transferModal">이체</button>
							<button id="historyBtn" type="button" data-toggle="modal"
								data-target="#historyModal">거래내역</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- section -->

	<!-- footer -->
	<footer>
		<jsp:include page="/include/bottom.jsp" />
	</footer>
	<!-- end footer -->

</body>
</html>