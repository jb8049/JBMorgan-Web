package kr.ac.jb.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.jb.account.accountDAO;
import kr.ac.jb.account.accountVO;
import kr.ac.jb.transaction.transactionDAO;
import kr.ac.jb.transaction.transactionVO;

public class accountDetailController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		accountVO account = new accountVO();
		String acct_no = request.getParameter("acct_no");
		
		accountDAO dao = new accountDAO();
		
		account = dao.searchJBMorganAccount(acct_no);
		
		// 모달창으로 detail 페이지에 거래내역을 보여주기 위해 해당 계좌의 거래내역을 불러옴		
		transactionDAO tDao = new transactionDAO();
		
		List<transactionVO> transactionList = tDao.searchJBMorganTransaction(acct_no);
		
		request.setAttribute("transactionList", transactionList);
		request.setAttribute("account", account);
		
		return "/bank/accountDetail.jsp";
		
		
	}

}