<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
			<tr>
								<th>거래시간</th>
								<th>상대방 계좌</th>
								<th>상대방 이름</th>
								<th>유형</th>
								<th>은행명</th>
								<th>금액</th>
			</tr>
			<c:forEach items="${ transactionList }" var="list">
			<tr>
					<td><c:out value="${ list.date }" /></td>
					<td><c:out value="${ list.counterpartAccountNo }" /></td>
					<td><c:out value="${ list.counterpartName }" /></td>
					<td><c:out value="${ list.type }"/></td>
					
					<c:choose>
						<c:when test="${ list.counterpartBank eq 'J' }">
							<td><c:out value="JBMorgan" /></td>
						</c:when>
						<c:when test="${ list.counterpartBank eq 'D' }"> 
							<td><c:out value="DonJo" /></td>
						</c:when>
						<c:when test="${ list.counterpartBank eq 'Y' }">
							<td><c:out value="YG" /></td>
						</c:when>
						<c:when test="${ list.counterpartBank eq 'S' }">
							<td><c:out value="SeJin" /></td>
						</c:when>
					</c:choose>
					<td><c:out value= "${ list.amount }" /></td>
			</tr>
			</c:forEach>