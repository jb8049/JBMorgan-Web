package kr.ac.jb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.jb.member.memberDAO;
import kr.ac.jb.member.memberVO;

public class RegisterController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		
		memberVO member = new memberVO();
		
		member.setId(id);
		member.setPassword(password);
		member.setName(name);
		member.setPhone(phone);
		member.setEmail(email);
		
		memberDAO dao = new memberDAO();
		dao.register(member);
		
		
		
		return "redirect:/";  // 회원가입 후 forward할 필요없이, index.jsp로 sendRedirect
	}

}
