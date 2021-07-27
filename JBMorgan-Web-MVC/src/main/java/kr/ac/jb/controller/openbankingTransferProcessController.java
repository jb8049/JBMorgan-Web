package kr.ac.jb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.jb.account.accountDAO;
import kr.ac.jb.account.accountVO;
import kr.ac.jb.transaction.transactionDAO;
import kr.ac.jb.transaction.transactionVO;

public class openbankingTransferProcessController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
			
	
		String msg="";
		String url="";
		transactionVO transaction = new transactionVO();
		
		//넘어오는 파라미터 정리
		//callable statement dao로 넘겨줘야하는 변수는
		// 출금하는 내 은행코드, 입금하는 상대방 은행코드, 송금할 금액, 출금하는 내 은행 계좌, 입금하는 상대방 은행계좌
		
		//내 은행 코드
		String myBankCode = request.getParameter("myBankCode");
		
		// 상대방 은행 코드
		String counterBankCode = request.getParameter("counterBankCode");
		
		// 송금할 금액
		int transferBalance = Integer.parseInt (request.getParameter("transferBalance"));
		
		// 내 계좌
		String acctNo = request.getParameter("acct_no");
		
		// 상대방 계좌
		String counterAcctNo = request.getParameter("counterAcctNo");
		
		// 본인 계좌 비밀번호
		String acctPassword = request.getParameter("acctPassword");
		
		// 상대방 계좌가 존재하는지 확인
		accountVO counterAccount = new accountVO();
		accountDAO CounterAcctDao = new accountDAO();
		
		switch(counterBankCode) {
		case "J":
			counterAccount = CounterAcctDao.searchJBMorganAccount(counterAcctNo);
		    break;
		case "S":
			counterAccount = CounterAcctDao.searchSejinAccount(counterAcctNo);
		    break;
	    case "D":
	    	counterAccount = CounterAcctDao.searchDonJoAccount(counterAcctNo);
		    break;
		case "Y":
			counterAccount = CounterAcctDao.searchYGAccount(counterAcctNo);
		    break;
		}
		
		if(counterAccount != null) {
			
			// 내 은행 코드가 무엇인지에 따라 계좌 상세를 얻어오는 dao가 달라짐
			accountVO myAccount = new accountVO();
			accountDAO accountDao = new accountDAO();
			
			//은행 코드에 따라 어느 은행으로 가서 계좌에 대한 상세 정보를 얻어올지
			switch(myBankCode) {
				case "J":
					myAccount = accountDao.searchJBMorganAccount(acctNo);
				    break;
				case "S":
					myAccount = accountDao.searchSejinAccount(acctNo);
				    break;
			    case "D":
			    	myAccount = accountDao.searchDonJoAccount(acctNo);
			    	break;
				case "Y":
					myAccount = accountDao.searchYGAccount(acctNo);
				    break;
				}
			
			if(myAccount.getAcct_pwd().equals(acctPassword)) {
				
				
				if(myAccount.getBalance() >= transferBalance) {
					
					transaction.setAccountNo(acctNo); // 내 계좌
					transaction.setCounterpartAccountNo(counterAcctNo);  //상대방 계좌
					transaction.setAmount(transferBalance); // 이체할 금액
					transaction.setCounterpartBank(counterBankCode); // 상대방 은행 코드
					transaction.setHolder(myAccount.getHolder()); // 내 이름
					transaction.setCounterpartName(counterAccount.getHolder()); // 상대방 이름
					transaction.setMyBankCode(myBankCode);
					
					transactionDAO tDao = new transactionDAO();
					
					// 계좌이체 성공
					boolean bool = tDao.openbankingTransfer(transaction);
					
					if(bool) {
						msg = "성공적으로 이체되었습니다.";
					}else {
						msg ="계좌이체에 실패했습니다.";
					}
					
					url = "/bank/transferSuccess.jsp" ;
					
				}else {
					
					msg = "계좌의 잔액이 부족합니다.";
					url ="/bank/openbankingTransferProcess.jsp";
				}
				
			}else {
				
				msg = "계좌의 비밀번호가 틀렸습니다.";
				url ="/bank/openbankingTransferProcess.jsp";
			}
			
		}else {
			
			msg = "이체할 계좌가 존재하지 않습니다.";
			url ="/bank/openbankingTransferProcess.jsp";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("transaction", transaction);
		
		return url;
	}

}
