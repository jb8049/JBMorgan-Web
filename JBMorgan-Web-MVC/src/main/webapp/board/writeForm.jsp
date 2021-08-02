<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>

<style>
      
      td, th {
    		padding: 5px;
      }
      
      table th {
			background-color: rgba(39, 43, 44, 0.87);;
			color: #ffffff;
	  }
       
</style>
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
	
	$('#goListBtn').click(function(){
									
		location.href ="<%= request.getContextPath()%>/board/boardList.jb" 
	})
})

function doWrite(){
	
	let f = document.writeForm
	
	if(f.title.value == ''){    // title에 입력한 값 => value
		
		alert('제목을 입력하세요.')
		f.title.focus()
		return false
	}
	
	if(f.content.value ==''){
		
		alert('내용을 입력하세요')
		f.content.focus()
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
						<h2>문의 등록</h2>
					</div>
					<div class="row">
						<div class="col-md-12" align="center">

							<div align="center">
								<br>
								<section>

									<div align="center">		
										<form action="<%= request.getContextPath()%>/board/writeProcess.jb" method="post" name="writeForm"
											onsubmit="return doWrite()" style="margin-bottom: 51px;">
											<input type="hidden" name="id" value="${ userVO.id }">

											<table border="1" style="width: 80%">
												<tr>
													<th width="25%" style="text-align: center">제목</th>
													<td><input type="text" size="60" name="title"></td>
												</tr>
												<tr>
													<th width="25%" style="text-align: center">작성자</th>
													<td> 
														<c:out value="${ userVO.id }" />
													</td>
												</tr>
												<tr>
													<th width="25%" style="text-align: center">내용</th>
													<td><textarea rows="8" cols="60" name="content"></textarea></td>
												</tr>
											</table>
											<br> <input type="submit" value="등록"> 
											<input id=goListBtn type="button" value="목록">
										</form>
									</div>
								</section>


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