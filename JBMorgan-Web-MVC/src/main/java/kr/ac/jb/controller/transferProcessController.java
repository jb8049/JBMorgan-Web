package kr.ac.jb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.jb.account.accountDAO;
import kr.ac.jb.account.accountVO;
import kr.ac.jb.transaction.transactionDAO;
import kr.ac.jb.transaction.transactionVO;

public class transferProcessController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		String msg= "";
		
		// 내 계좌
		String acctNo = request.getParameter("acct_no");
		
		// 상대방 은행 종류
		String bankCode = request.getParameter("bankCode");
		
		// 상대방 계좌
		String counterAcctNo = request.getParameter("counterAcctNo");
		
		accountDAO CounterAcctDao = new accountDAO();
		accountVO counterAccount = CounterAcctDao.searchOneAccount(counterAcctNo);
		
		// 송금할 금액
		int transferBalance = Integer.parseInt (request.getParameter("transferBalance"));
		
		// 본인 계좌 비밀번호
		String acctPassword = request.getParameter("acctPassword"); 
		
		accountDAO myAccountDAO = new accountDAO();
		accountVO myAccount = myAccountDAO.searchOneAccount(acctNo);
		
		if(counterAccount != null) {
			
			if(myAccount.getAcct_pwd().equals(acctPassword)) {
				
				if(myAccount.getBalance() >= transferBalance) {
					
					transactionVO transaction = new transactionVO();
					
					transaction.setAccountNo(acctNo);
					transaction.setCounterpartAccountNo(counterAcctNo);
					transaction.setAmount(transferBalance);
					transaction.setCounterpartBank(bankCode);
					transaction.setHolder(myAccount.getHolder());
					transaction.setCounterpartName(counterAccount.getHolder());
					
					myAccountDAO.updateAccount(transaction);
					
					// 업데이트 된 계좌들의 balance를 받아오는 VO
					accountVO accountBalance = myAccountDAO.searchBalance(transaction);
					
					transaction.setBalance(accountBalance.getBalance());
					transaction.setCounterBalance(accountBalance.getCounterBalance());
					
					
					//거래내역 기록에 필요한 정보
					// 내 계좌번호, 상대방 계좌번호, 이체할 잔액, 은행코드, 내 이름, 상대방 이름
					// 내 계좌의 balacnce, 상대방 계좌 balance
					
					transactionDAO tDao = new transactionDAO();
					
					tDao.writeTransaction(transaction);
					
					
					
					msg = "이체가 성공적으로 완료되었습니다.";
					
					
				}else {
					
					msg = "계좌의 잔액이 부족합니다.";
					
					
				}
				
			}else {
				
				msg = "계좌의 비밀번호가 틀렸습니다.";
			}
			
		}else {
			
			msg = "이체할 계좌가 존재하지 않습니다.";
			
		}
		
		request.setAttribute("msg", msg);
		
		// 다시 해당 계좌 상세로 돌아오기 위해
		request.setAttribute("acctNo", acctNo);
		
		return "/bank/transferProcess.jsp";
	}

}
