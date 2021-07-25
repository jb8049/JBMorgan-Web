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

<script>

	$(document).ready(function(){
		
		$('#addBtn').click(function(){
			
			location.href ="<%= request.getContextPath()%>/board/writeForm.jb"
		})
	})
	
	
</script>

<link rel="stylesheet" href="/JBMorgan-Web-MVC/resources/css/board.css">


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
	<div class="contact" style="margin-bottom: 200px">
		<div class="container">
			<div class="row ">
				<div class="col-md-8 offset-md-2">
					<div class="titlepage text_align_left">
						<h2>고객문의</h2>
					</div>
					<div class="row">
						<div class="col-md-12" align="center">

							<div align="center">
								<br>
								<table style="width: 100%" id="list">
									<tr>
										<th width="7%" style="text-align: center">번호</th>
										<th style="text-align: center;">제목</th>
										<th width="16%" style="text-align: center">작성자</th>
										<th width="20%" style="text-align: center">등록일</th>
									</tr>
									
									<c:forEach items="${ boardList }" var="board">
										<tr>
											<td><c:out value="${ board.boardNo }" /></td>
											<td>
												<a href="<%= request.getContextPath()%>/board/boardDetail.jb?boardNo=${board.boardNo}"><c:out value="${ board.title }" />
												</a>
											</td>
											<td><c:out value="${ board.id }" /></td>
											<td><c:out value="${ board.regDate }" /></td>
										</tr>
									</c:forEach>
								</table>
								<br>

								<button id="addBtn">새글등록</button>

							</div>

						</div>
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