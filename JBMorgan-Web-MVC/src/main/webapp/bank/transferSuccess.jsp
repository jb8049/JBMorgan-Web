<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>



<script>

	alert('${ msg }')
	
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
	
	location.href ="<%= request.getContextPath()%>/bank/searchAccount.jb"
	
	<%-- location.href = "<%= request.getContextPath()%>/bank/accountDetail.jb?acct_no=${acctNo}" --%>
	
</script>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>카톡 공유</title>
<script type="text/JavaScript" src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
</head>
<body>
    <input type="button" onClick="sendLinkDefault();" value="이체내역 공유하기"/>

<script type="text/javascript">
    function sendLinkCustom() {
        Kakao.init("bbe9ec6b57b56d8ef60c464ef2214a90");
        Kakao.Link.sendCustom({
            templateId: 57571
        });
    }
</script>

<script>
try {
  function sendLinkDefault() {
    Kakao.init('bbe9ec6b57b56d8ef60c464ef2214a90')
    Kakao.Link.sendDefault({
      objectType: 'feed',
      content: {
        title: 'JBMorgan 이체내역',
        description: '#케익 #딸기 #삼평동 #카페 #분위기 #소개팅',
        imageUrl:'',
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
            mobileWebUrl: 'https://developers.kakao.com',
            webUrl: 'https://developers.kakao.com',
          },
        },
      ],
    })
  }
; window.kakaoDemoCallback && window.kakaoDemoCallback() }
catch(e) { window.kakaoDemoException && window.kakaoDemoException(e) }
</script>
   
</body>
</html>

