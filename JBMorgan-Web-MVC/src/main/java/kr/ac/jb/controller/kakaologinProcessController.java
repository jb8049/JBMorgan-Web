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
		
		// 카카오톡 로그인 선택 => 동의 => id 추출
		String kakao_id = request.getParameter("kakao_id");
		
		HttpSession session = request.getSession();
		session.setAttribute("kakao_id", kakao_id);
		
		memberDAO mDao = new memberDAO();
		
		memberVO userVO = mDao.searchKakoId(kakao_id);
		// userVO에 저장된 값은 아이디, 이름, 동의여부
		
		if(userVO == null) {
			
			//userVO가 존재하지 않으면, 통합 회원 로그인창으로 이동함
			url = "/login/IntegratedRegisterForm.jb";
			
			
		}else {
			// 이미 카카오톡으로 통합 회원가입을 한 회원임
			url = "redirect:/";
			
			// 로그인한 회원의 정보 userVO에 등록 (id, password, agreement)
			// HttpSession session = request.getSession();
			session.setAttribute("userVO", userVO);
		}
		
		/*
		 * 1. 카톡 로그인 누르면, 해당 res.id가 member_id에 존재하는지 확인
		 * 2. 존재하지 않는다 => 회원가입 페이지로 이동(아이디와 비밀번호를 입력받을 필요없음, 통합 회원가입)
		 * ---> name, phone, email, ssn
		 * 3. 회원가입 완료 후, index 페이지로 이동
		 * 4. 존재하면 바로 로그인됨
		 */
		
		
		
		return url;
	}

}
