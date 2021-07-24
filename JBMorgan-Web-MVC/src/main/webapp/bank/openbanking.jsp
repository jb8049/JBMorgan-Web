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
                     <h2>오픈뱅킹 서비스</h2>
                  </div>
                     <div class="row">
                        <div class="col-md-12" align="center">
                           <table border=1px solid width="370px">
                           		<tr>
                           			<th>계좌번호</th>
                           			<th width="80px">은행명</th>
                           			<th width="80px">계좌명</th>
                           			<th width="90px">잔액</th>
                           		</tr>
                           		
                           		<c:forEach items="${ openbankingAccountList }" var="openbankingAccount">
                           			<tr>
                           				<td><a href="<%= request.getContextPath()%>/bank/openbankingDetail.jb?acctNo=${ openbankingAccount.acct_no }&bankCode=${openbankingAccount.bankCode}"><c:out value='${openbankingAccount.acct_no}'/></a></td>	
                           			<c:choose>
                           				<c:when test="${ openbankingAccount.bankCode eq 'J'}">
                           					<td><c:out value="JBMorgan"/></td>
                           				</c:when>
                           				<c:when test="${ openbankingAccount.bankCode eq 'S'}">
                           					<td><c:out value="SeJin"/></td>
                           				</c:when >
                           				<c:when test="${ openbankingAccount.bankCode eq 'D'}">
                           					<td><c:out value="DonJo"/></td>
                           				</c:when>
                           				<c:when test="${ openbankingAccount.bankCode eq 'Y'}">
                           					<td><c:out value="YGBANK"/></td>
                           				</c:when>
                           			</c:choose>
                           				<td><c:out value='${ openbankingAccount.acct_name }'  /></td>
                           				<td><c:out value='${ openbankingAccount.balance }'  /></td>
                           			</tr>
                           		</c:forEach>
                           		
                           </table>                         
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