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
	
ul.pageNo li > .presentPage{   /*pageNo 클래스를 가진 ul, ul 아래에 있는 li li하위에 있는 클래스가 presentPage인 것 */

	color: red;	
}


}



</style>


<script>

	$(document).ready(function(){
		
		$('#addBtn').click(function(){
			
			location.href ="<%= request.getContextPath()%>/board/writeForm.jb"
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
	<div class="contact" style="padding-bottom: 10px" >
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
										<th width="7%" style="text-align: center; color:#ffffff ; background-color: rgba(39, 43, 44, 0.87) ;">번호</th>
										<th style="text-align: center; color:#ffffff ; background-color: rgba(39, 43, 44, 0.87) ;">제목</th>
										<th width="16%" style="text-align: center; color:#ffffff ; background-color: rgba(39, 43, 44, 0.87) ;">작성자</th>
										<th width="20%" style="text-align: center; color:#ffffff ; background-color: rgba(39, 43, 44, 0.87) ;">등록일</th>
									</tr>
									
									<c:forEach items="${ boardList }" var="board">
										<tr>
											<td><c:out value="${ board.boardNo }" /></td>
										<c:choose>
											<c:when test="${ board.indent == 0}">
												<td>
													<a href="<%= request.getContextPath()%>/board/boardDetail.jb?boardNo=${board.boardNo}"><c:out value="${ board.title }" /></a>
												</td>
											</c:when>
											<c:otherwise>
												<td>
													<a href="<%= request.getContextPath()%>/board/boardDetail.jb?boardNo=${board.boardNo}" style="margin-left: ${board.indent * 10}px"><c:out value="[re]:${ board.title }" /></a>									
												</td>
											</c:otherwise>
										</c:choose>
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
	
	<!-- 넘겨주는 정보가 페이지의 번호 => Controller로 보내야함  -->
	<!-- 블록별 시작페이지 , 끝 페이지   -->
	<!-- 페이징  -->
	<div style="text-align: center; ">
		
		<ul class="pageNo">
			<c:choose>
				<c:when test="${endPage eq 5 }">
					<button disabled="disabled">Prev</button>
				</c:when>
				<c:otherwise>
					<li>	
						<a href="<%= request.getContextPath()%>/board/boardList.jb?page=${ endPage - 5}">Prev</a>
					</li>
				</c:otherwise>
			</c:choose>
			
			
			<c:forEach begin="${ startPage }" end = "${ endPage }" var="i">
					
					<c:choose>
						<c:when test="${curPage eq i }">
							<li>
								<a class="presentPage"  href="<%= request.getContextPath()%>/board/boardList.jb?page=${i}">[${i}]</a>
							</li>
						</c:when>
						<c:otherwise>
							<li>
								<a class="otherPage" href="<%= request.getContextPath()%>/board/boardList.jb?page=${i}">[${i}]</a>
							</li>
						</c:otherwise>
					</c:choose>
					
					
			</c:forEach>
			
			
			<c:choose>
				<c:when test="${ endPage eq totalPage }" >
					<button disabled="disabled">Next</button>
				</c:when>
				<c:otherwise>
					<li>	
						<a href="<%= request.getContextPath()%>/board/boardList.jb?page=${ startPage + 5}">Next</a>
					</li>
				</c:otherwise>
			</c:choose>
			
			
			
			
		</ul>
	
	</div>
	
	

	
	<!-- contact -->

	
	<!-- footer -->
	<footer>
		<jsp:include page="/include/bottom.jsp" />
	</footer>
	<!-- end footer -->

</body>
</html>