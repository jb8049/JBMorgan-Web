<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<script>
	
	function docheck(no){
		
		<c:choose>
			<c:when test="${ not empty userVO}">
				
				switch (no) {
				  case 1: //계좌개설
				    location.href = '<%= request.getContextPath()%>/bank/createAccountForm.jb'
				    break;
				  case 2: //계좌조회
					location.href = '<%= request.getContextPath()%>/bank/searchAccount.jb'
				    break;
				  case 3: //오픈뱅킹
                  	location.href = '<%= request.getContextPath()%>/bank/openbanking.jb'
				  	break; 
				  case 4: //고객문의
					location.href = '<%= request.getContextPath()%>/board/boardList.jb'
				  	break; 
				}
			</c:when>
			<c:otherwise>
			if(confirm('로그인이 필요한 서비스입니다\n로그인페이지로 이동하시겠습니까?'))
				location.href = '<%= request.getContextPath()%>/login/loginForm.jb'
			</c:otherwise>
		</c:choose>
		
	}


</script>


    <!-- openBanking Modal  -->

	<div class="modal fade" id="agreeModal" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<h2 class="modal-title">개인(신용)정보 수집·이용 및 제공 동의서</h2>
					<button type="button" class="close" data-dismiss="modal"
						style="padding-top: 5px">×</button>
				</div>
				
				<div class="modal-body">

					<form action="<%=request.getContextPath() %>/bank/openbanking.jb" method="post">
						<input type="hidden" name="id" value="${ userVO.id }">
						<h4>개인(신용)정보 수집·이용에 관한 사항</h4>
							
						-오픈뱅킹서비스 연결계좌의 거래정보 제공사실 통보<br>
						※『금융실명거래 및 비밀보장에 관한 법률』 제4조의 2에 따른 법령상 의무<br>
							
						-개인식별정보(CI값, 성명, 생년월일), 연결계좌번호, 이메일 주소<br>
							
						-위 개인(신용)정보는 오픈뱅킹서비스 이용일로부터 5년 이내 보유·이용하고, 서비스 해지 후에는 위에 기재된 이용 목적과 관련된 금융사고 조사, 분쟁해결, 민원처리, 법령상 의무이행을 위하여 필요한 범위 내에서만 보유·이용됩니다.<br>
						
						-당사가 위와 같이 본인의 개인(신용)정보를 수집·이용하는 것에 동의합니다.
						
						<div class="modal-footer">
							<input type="submit" class="btn btn-default" value="동의">
							<button type="button" class="btn btn-default" data-dismiss="modal">동의하지 않음</button>
						</div>
					</form>
					
				</div>


			</div>
		</div>

	</div>

	<!-- openAgree Modal end  -->
    
         <div class="header">
            <div class="container-fluid">
               <div class="row d_flex">
                  <div class=" col-md-2 col-sm-3 col logo_section">
                     <div class="full">
                        <div class="center-desk">
                           <div class="logo">
                              <a href="<%=request.getContextPath()%>/"><img src="/JBMorgan-Web-MVC/resources/images/logo.png" alt="#" height="75px"/></a>
                           </div>
                        </div>
                     </div>
                  </div>
                  <div class="col-md-8 col-sm-9" style="left: 214px; margin-bottom: 8px;">
                     <nav class="navigation navbar navbar-expand-md navbar-dark ">
                        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample04" aria-controls="navbarsExample04" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                        </button>
                        <div class="collapse navbar-collapse" id="navbarsExample04">
                           <ul class="navbar-nav mr-auto">
                              <li class="nav-item">
                                 <a class="nav-link" href="javascript:docheck(1)">계좌개설</a>
                              </li>
                              <li class="nav-item">
                              	<a class="nav-link" href="javascript:docheck(2)">계좌조회</a>
                              </li>
                           
	                          <li class="nav-item">
	                          	<c:choose>
	                          		<c:when test="${not empty userVO && userVO.agreement eq 'N' }">
	                          			<a class="nav-link" href="#" data-toggle="modal" data-target="#agreeModal">오픈뱅킹</a>
	                          		</c:when>
	                          		<c:otherwise>
	                          			<a class="nav-link" href="javascript:docheck(3)">오픈뱅킹</a>
	                          		</c:otherwise>
	                          	</c:choose>
	                           </li>
	                              
							   <li class="nav-item">
                                 <a class="nav-link" href="javascript:docheck(4)">고객문의</a>
                              </li>
                              <c:if test="${empty userVO}" >
	                              <li class="nav-item">
	                                 <a class="nav-link" href="<%= request.getContextPath()%>/login/loginForm.jb">로그인</a>
	                              </li>
                              </c:if>
                              <c:if test="${ not empty userVO }">
	                              <li class="nav-item">
	                                  <a class="nav-link" href="<%= request.getContextPath()%>/logout/logout.jb">로그아웃</a>
	                              </li>
                              </c:if>
                              
                           </ul>
                        </div>
                     </nav>
                  </div>
               </div>
            </div>
         </div>