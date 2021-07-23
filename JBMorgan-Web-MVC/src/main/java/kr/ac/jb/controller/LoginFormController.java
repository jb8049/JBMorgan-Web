package kr.ac.jb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFormController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String msg = (String) session.getAttribute("msg");
		
		session.removeAttribute("msg");
		request.setAttribute("msg", msg);
		
		
		return "/login/loginForm.jsp" ;
	}

}
