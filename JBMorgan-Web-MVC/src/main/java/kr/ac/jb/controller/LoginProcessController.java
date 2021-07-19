package kr.ac.jb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.jb.member.memberDAO;
import kr.ac.jb.member.memberVO;

public class LoginProcessController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String url = "";
		String msg = "";
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		memberVO member = new memberVO();
		
		member.setId(id);
		member.setPassword(password);
		
		memberDAO dao = new memberDAO();
		
		memberVO userVO = dao.login(member);
		
		// 로그인이 안된 경우 userVO == null
		
		if(userVO == null) {
			msg = "아이디와 비밀번호를 잘못입력하셨습니다.";
			url = "redirect:/login/loginForm.jb";
			
			HttpSession session = request.getSession();
			session.setAttribute("msg", msg); 
			
		}else {
			
			url = "redirect:/";
			
			HttpSession session = request.getSession();
			session.setAttribute("userVO", userVO);
		}
		
		
		return url;
	}

}
