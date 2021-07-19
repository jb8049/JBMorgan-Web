package kr.ac.jb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.jb.member.memberDAO;
import kr.ac.jb.member.memberVO;

public class openbankingController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		String id = request.getParameter("id");
		
		
		memberDAO dao = new memberDAO();
		
		
		// 오픈뱅킹 동의 o => 'Y'로 agreement를 업데이트하고, session에 있는 userVO도 업데이트해야함
		dao.openBankingAgree(id);
		
		HttpSession session = request.getSession();
		memberVO userVO = new memberVO();
		
		String agreement = dao.agreeMember(id);
		
		// 로그인할 때 저장한 userVO를 최신화하기 위해 로그인할 때 저장된 userVO를 들고옴
		userVO = (memberVO) session.getAttribute("userVO");
		
		System.out.println("확인 : " + agreement);
		
		userVO.setAgreement(agreement);
		
		
		return "/bank/openbanking.jsp";
	}

}
