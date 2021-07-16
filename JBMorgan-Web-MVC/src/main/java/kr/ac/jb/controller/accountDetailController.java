package kr.ac.jb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.jb.account.accountDAO;
import kr.ac.jb.account.accountVO;

public class accountDetailController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 전달받은 계좌번호
		
		String acct_no = request.getParameter("acct_no");
		
		// 거래내역도 받아와야함
		
		accountDAO dao = new accountDAO();
		
		accountVO account = dao.searchOneAccount(acct_no);
		
		request.setAttribute("account", account);
		
		return "/bank/accountDetail.jsp";
	}

}