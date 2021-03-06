<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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

	function goBoardList(){
		
		location.href="<%=request.getContextPath()%>/board/boardList.jb"
	}
</script>

<script>

	function goReply(){
		
		location.href="<%=request.getContextPath()%>/board/boardReply.jb?boardNo=${ board.boardNo }"
	}


</script>

<style>

table, th, td {
	border: 1px solid rgba(39, 43, 44, 0.87);
	border-collapse: collapse;
	color: #555;
	font-family: verdana, sans-serif;
}

td, th {
	padding: 5px;
}

td a:hover {
	text-decoration: underline;
}

table th {
	background-color: rgba(39, 43, 44, 0.87);;
	color: rgba(39, 43, 44, 0.87);
}


</style>

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
	<div class="contact" >
		<div class="container">
			<div class="row ">
				<div class="col-md-8 offset-md-2">
					<div class="titlepage text_align_left">
						<h2>?????? ??????</h2>
					</div>
					<div class="row">
						<div class="col-md-12" align="center">

							<div align="center">
								<br>
								<table style="width: 100%" id="list">
									<tr>
										<th width="25%" style="color:#ffffff ; background-color: rgba(39, 43, 44, 0.87) ;">??????</th>
										<td><c:out value="${ board.boardNo }" /></td>
									</tr>
									<tr>
										<th width="25%" style="color:#ffffff ; background-color: rgba(39, 43, 44, 0.87) ;">??????</th>
										<td><c:out value="${ board.title }" /></td>
									</tr>
									<tr>
										<th width="25%" style="color:#ffffff ; background-color: rgba(39, 43, 44, 0.87) ;">?????????</th>
										<td><c:out value="${ board.id }" /></td>
									</tr>
									<tr>
										<th width="25%" style="color:#ffffff ; background-color: rgba(39, 43, 44, 0.87) ;">??????</th>
										<td><c:out value="${ board.content }" /></td>
									</tr>
									<tr>
										<th width="25%" style="color:#ffffff ; background-color: rgba(39, 43, 44, 0.87) ;">?????????</th>
										<td><c:out value="${ board.regDate }" /></td>
									</tr>
								</table>
								<br>
								<input type="button" onclick="goBoardList()" value="??????">
								<input type="button" onclick="goReply()" value="??????">
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




