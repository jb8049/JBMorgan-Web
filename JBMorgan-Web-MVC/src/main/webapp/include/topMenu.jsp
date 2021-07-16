<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
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
                                 <a class="nav-link" href="about.html">계좌개설</a>
                              </li>
                              <li class="nav-item">
                                 <a class="nav-link" href="<%= request.getContextPath()%>/bank/searchAccount.jb">계좌조회</a>
                              </li>
                              <li class="nav-item">
                                 <a class="nav-link" href="contact.html">오픈뱅킹</a>
                              </li>
							   <li class="nav-item">
                                 <a class="nav-link" href="contact.html">고객센터</a>
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