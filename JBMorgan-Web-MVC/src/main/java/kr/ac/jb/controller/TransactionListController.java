package kr.ac.jb.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.jb.transaction.transactionDAO;
import kr.ac.jb.transaction.transactionVO;

public class TransactionListController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 거래내역 뽑아서 transactionParam.jsp 호출하기
		String accountNo = request.getParameter("accountNo");
		
		transactionDAO tDao = new transactionDAO();
		List<transactionVO> transactionList = tDao.searchJBMorganTransaction(accountNo);
		
		// 여기서 저장해줘야 리턴한 jsp에서 사용가능
		request.setAttribute("transactionList", transactionList);
		
		return "/bank/transactionParam.jsp";
	}

}
