package kr.ac.jb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.jb.member.memberDAO;
import kr.ac.jb.member.memberVO;

public class IdCheckController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String id = request.getParameter("id");
		
		memberDAO dao = new memberDAO();
		
		memberVO userVO = dao.idCheck(id);
		
		
		request.setAttribute("userVO", userVO);
		
		return "/login/idCheckParam.jsp";
	}

}
