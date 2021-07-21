package kr.ac.jb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class createAccountFormController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String AccountMsg = (String) session.getAttribute("AccountMsg");
		
		//remove안해주면, 계좌개설 페이지로 들어갈 때마다 msg가 출력됨
		session.removeAttribute("AccountMsg");
		
		request.setAttribute("AccountMsg", AccountMsg);
		
		return "/bank/createAccountForm.jsp";
	}

}
