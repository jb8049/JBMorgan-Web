package kr.ac.jb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.jb.member.memberDAO;

public class openbankingController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		String id = request.getParameter("id");
		
		
		memberDAO dao = new memberDAO();
		dao.openBankingAgree(id);
		
		
		
		return "/bank/openbanking.jsp";
	}

}
