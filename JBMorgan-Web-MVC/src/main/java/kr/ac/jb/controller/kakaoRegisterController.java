package kr.ac.jb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.jb.member.memberDAO;
import kr.ac.jb.member.memberVO;

public class kakaoRegisterController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession() ;
		String kakao_id = (String) session.getAttribute("kakao_id");
		
		String name = request.getParameter("name") ;
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String ssnf = request.getParameter("ssnf");
		String ssnb = request.getParameter("ssnb");
		
		String ssn = ssnf + ssnb; //인서트 되는 SSN
		
		memberVO member = new memberVO();
		
		member.setId(kakao_id);
		member.setPassword(null); //카카오 통합 회원의 비밀번호는 null이 들어가야함
		member.setName(name);
		member.setPhone(phone);
		member.setEmail(email);
		member.setSsn(ssn);
		
		memberDAO dao = new memberDAO();
		
		dao.register(member);
		
		
		return "redirect:/";
	}

}
