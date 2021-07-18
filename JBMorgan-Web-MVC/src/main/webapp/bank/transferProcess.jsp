<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<script>

	alert('${ msg }')
	location.href = "<%= request.getContextPath()%>/bank/accountDetail.jb?acct_no=${acctNo}"
	
</script>

