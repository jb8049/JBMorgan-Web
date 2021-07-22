package kr.ac.jb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.jb.member.memberDAO;
import kr.ac.jb.member.memberVO;

public class kakaologinProcessController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String msg = "";
		String url = "";
		
		//HttpSession session = request.getSession();
		
		
		
		// 카카오톡 로그인 선택 => 동의 => id 추출
		String kakao_id = request.getParameter("kakao_id");
		
		HttpSession session = request.getSession();
		session.setAttribute("kakao_id", kakao_id);
		
		
		
		memberDAO mDao = new memberDAO();
		
		memberVO userVO = mDao.searchKakoId(kakao_id);
		// userVO에 저장된 값은 아이디, 이름, 동의여부,, 비밀번호도 저장함(null인 카카오 아이디 구분하기 위해)
		
		
		if(userVO == null) {
			
			//userVO가 존재하지 않으면, 통합 회원 로그인창으로 이동함
			url = "/login/IntegratedRegisterForm.jb";
			
			
		}else {
			// 이미 카카오톡으로 통합 회원가입을 한 회원임
			url = "redirect:/";
			
			// 로그인한 회원의 정보 userVO에 등록 (id, name, password, agreement)
			// HttpSession session = request.getSession();
			
			session.setAttribute("userVO", userVO);
			
			//패스워드를 request로 등록해서 자바스크립트(transferSuccess.jsp)에서 호출해보자
			
		}
		
		return url;
	}

}
