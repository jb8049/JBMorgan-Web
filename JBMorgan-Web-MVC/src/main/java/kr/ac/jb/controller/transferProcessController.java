package kr.ac.jb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.jb.account.accountDAO;
import kr.ac.jb.account.accountVO;
import kr.ac.jb.transaction.transactionVO;

public class transferProcessController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		String msg= "";
		
		// 내 계좌
		String acctNo = request.getParameter("acct_no");
		
		// 은행종류
		String bank = request.getParameter("bank");
		
		// 상대방 계좌
		String counterAcctNo = request.getParameter("counterAcctNo");
		
		accountDAO acctDao = new accountDAO();
		accountVO counterAccount = acctDao.searchOneAccount(counterAcctNo);   // 상대방 계좌가 존재하는지 확인
		
		// 송금할 금액
		int transferBalance = Integer.parseInt (request.getParameter("transferBalance"));
		
		// 계좌 비밀번호
		String acctPassword = request.getParameter("acctPassword"); 
		
		// 내 계좌의 상세 정보 중 pwd를 받아옴
		accountVO myAccount = acctDao.searchOneAccount("acctNo");
		
		if(counterAccount != null) {
			
			if(myAccount.getAcct_pwd().equals(acctPassword)) {
				
				if(myAccount.getBalance() >= transferBalance) {
					
					// 모든 조건을 만족하는 경우에,
					// counterAcctNo 상대방 계좌 accoutVO
					// myAccount 내 계좌 accountVO
					// 트랜잭션VO를 이용해서 account 테이블도 업데이트하면 될 듯?
					
					transactionVO transaction = new transactionVO();
					
					transaction.setAccountNo(acctNo);
					transaction.setCounterpartAccountNo(counterAcctNo);
					transaction.setAmount(transferBalance);
					transaction.setCounterpartBank(bank);
					// 상대방 이름?
					transaction.setCounterpartName(counterAccount.getHolder());
					
					acctDao.updateAccount(transaction);
					
					
					
//					private int transactionNo;
//					private String date;
//					private int amount;


//					private String counterpartName;


					
					
				}else {
					
					msg = "계좌의 잔액이 부족합니다.";
					
					
					
					
				}
				
			}else {
				
				msg = "계좌의 비밀번호가 틀렸습니다.";
			}
			
		}else {
			
			msg = "계좌가 존재하지 않습니다.";
			
		}
		
		// 계좌 이체를 실패한 경우의 msg
		request.setAttribute("msg", msg);
		
		
		// 이체 성공한 경우, 이체 실패한 경우
		return "/bank/transferProcess.jsp";
	}

}
