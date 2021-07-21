package kr.ac.jb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFormController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String AccountMsg = (String) session.getAttribute("AccountMsg");
		
		session.removeAttribute("AccountMsg");
		request.setAttribute("AccountMsg", AccountMsg);
		
		
		return "/login/loginForm.jsp" ;
	}

}
