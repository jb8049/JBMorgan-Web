<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
	<c:when test="${not empty userVO.id }">
		fail 
	</c:when>
	<c:otherwise>
		success
	</c:otherwise>
</c:choose>