package kr.ac.jb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.jb.account.accountDAO;
import kr.ac.jb.account.accountVO;
import kr.ac.jb.transaction.transactionDAO;
import kr.ac.jb.transaction.transactionVO;

public class transferProcessController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String url =""; 
		String msg= "";
		
		// 내 계좌
		String acctNo = request.getParameter("acct_no");
		
		// 상대방 은행 종류
		String bankCode = request.getParameter("bankCode");
		
		// 상대방 계좌
		String counterAcctNo = request.getParameter("counterAcctNo");
		
		accountDAO CounterAcctDao = new accountDAO();
		accountVO counterAccount = CounterAcctDao.searchJBMorganAccount(counterAcctNo);
		
		// 송금할 금액
		int transferBalance = Integer.parseInt (request.getParameter("transferBalance"));
		
		// 본인 계좌 비밀번호
		String acctPassword = request.getParameter("acctPassword");
		
		accountDAO myAccountDAO = new accountDAO();
		// 내 계좌에 대한 정보를 얻어옴
		accountVO account = myAccountDAO.searchJBMorganAccount(acctNo);
		
		
		transactionVO transaction = new transactionVO();
		
		
		if(counterAccount != null) {
			
			if(account.getAcct_pwd().equals(acctPassword)) {
				
				if(account.getBalance() >= transferBalance) {
					
					transaction.setAccountNo(acctNo);
					transaction.setCounterpartAccountNo(counterAcctNo);
					transaction.setAmount(transferBalance);
					transaction.setCounterpartBank(bankCode);
					transaction.setHolder(account.getHolder());
					transaction.setCounterpartName(counterAccount.getHolder());
					
//					트랜잭션 dao에서 계좌 테이블 update, 트랜잭션 테이블 insert
					transactionDAO tDao = new transactionDAO();
					tDao.transfer(transaction);
					
//					업데이트 된 계좌들의 balance를 받아오는 VO
//					accountVO accountBalance = myAccountDAO.searchBalance(transaction);
//					
//					transaction.setBalance(accountBalance.getBalance());
//					transaction.setCounterBalance(accountBalance.getCounterBalance());
					
					
					//거래내역 기록에 필요한 정보
					// 내 계좌번호, 상대방 계좌번호, 이체할 잔액, 은행코드, 내 이름, 상대방 이름
					// 내 계좌의 balacnce, 상대방 계좌 balance
					
					// msg = "이체가 성공적으로 완료되었습니다." ;
					
					url = "/bank/transferSuccess.jsp" ;
					
					
				}else {
					
					msg = "계좌의 잔액이 부족합니다.";
					url = "/bank/searchAccount.jb";
				}
				
			}else {
				
				msg = "계좌의 비밀번호가 틀렸습니다.";
				url = "/bank/searchAccount.jb";
			}
			
		}else {
			
			msg = "이체할 계좌가 존재하지 않습니다.";
			url = "/bank/searchAccount.jb";
			
		}
		
		// 계좌 실패 msg, 성공하는 경우 출력하지 않음
		request.setAttribute("msg", msg);
		// transferSuccess.jsp에서 사용할 transaction 등록
		request.setAttribute("transaction", transaction);
		
		HttpSession session = request.getSession();
		session.setAttribute("msg", msg);
		
		// 다시 해당 계좌 상세로 돌아오기 위해 썼지만, 필요없어짐
		// request.setAttribute("acctNo", acctNo);
		
		return url;
	}

}
