<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>

<script>
	
	$(document).ready(function(){
		
		
		alert('이체가 완료되었습니다.')
		
		<c:if test="${empty userVO.password}">	
			if(confirm('이체내역을 공유하시겠습니까?') == true){
					$("#shareBtn").trigger('click');  /*이체내역 공유한다고 하니까, 디폴트 버튼이 뜨기만함  */
				}
		</c:if>
		
		location.href = "<%= request.getContextPath()%>/bank/openbanking.jb"
		<%-- location.href = "<%= request.getContextPath()%>/bank/searchAccount.jb" --%>
	})
	
	/* 계좌 이체를 성공한 경우에도 DB에 들릴 수 있도록 해야함, session에 등록한 msg를 remove해야하니까 */
	// 이체가 완료되었습니다 => 비밀번호가 null인 회원이면, 
	// 거래내역을 공유하시겠습니까? => 확인 누르면, 공유 메세지 발송창 실행,,,
	// 발송하거나 발송하지 않거나 => 계좌조회로 이동
	
	// 해당 회원의 비밀번호가 null인 경우에는 카카오톡 공유가능
	// 공유하시겠습니까? 물어보고, 안하면
	
	/* 이체가 성공적으로 완료되었습니다 일때만 '이체내역 공유하기' 메세지가 떠야함*/ 
	/* msg 변수를 다르게 해서 이런 메세지가 존재하면.. 이체 성공ok => 이체 내역을 공유하시겠습니까?  */
	// 카카오톡으로 로그인한 경우에만 가능함 (userVO의 pwd가 null일 때만 거래내역 공유가 가능함..))
	/* 카카오토 메세지를 전송한 후에 이동해야함 */
	
</script>

<script>
	
	try {
	  function sendLinkDefault() {
	    Kakao.init('bbe9ec6b57b56d8ef60c464ef2214a90')
	    Kakao.Link.sendDefault({
	      objectType: 'feed',
	      content: {
	        title: 'JBMorgan 이체내역',
	        description: '회원님의 '+${transaction.counterpartAccountNo}+' 계좌로 '+${transaction.amount} +'원이 입금되었습니다.',
	        imageUrl:'https://github.com/jb8049/0312_homework/blob/main/kakaologo.png?raw=true',
	        link: {
	          mobileWebUrl: 'https://developers.kakao.com',
	          webUrl: 'https://developers.kakao.com',
	        },
	      },
	      social: {
	      },
	      buttons: [
	        {
	          title: '자세히보기',
	          link: {
	            mobileWebUrl: 'https://www.naver.com',
	            webUrl: 'https://www.naver.com',
	          },
	        },
	      ],
	    })
	  }; 
	
	window.kakaoDemoCallback && window.kakaoDemoCallback() }
	catch(e) { window.kakaoDemoException && window.kakaoDemoException(e) }
</script>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>kakaoTalk</title>
<script type="text/JavaScript" src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
</head>
<body>
    <input type="button" id="shareBtn" onClick="sendLinkDefault();" value="#" style="display: none;"/> 
</body>
</html>