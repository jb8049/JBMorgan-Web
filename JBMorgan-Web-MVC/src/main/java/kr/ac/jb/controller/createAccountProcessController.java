package kr.ac.jb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.jb.account.accountDAO;
import kr.ac.jb.account.accountVO;
import kr.ac.jb.member.memberDAO;
import kr.ac.jb.member.memberVO;

public class createAccountProcessController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
		//계좌개설 form에서 입력한 값이 넘어옴
		String id = request.getParameter("id");
		String AccountMsg ="";
		String url="";
		
		memberVO member = new memberVO();
		memberDAO memberDao = new memberDAO();
		
		// 로그인한 회원의 id로 비교 정보 조회
		member = memberDao.searchOneMember(id);
		
		// 회원이 입력한 정보와 id로 조회한 DB에 저장된 정보와 비교해야함
		String phone = request.getParameter("phone");
		String ssnf = request.getParameter("ssnf");
		String ssnb = request.getParameter("ssnb");
		String ssn = ssnf + ssnb;
		
		// 계좌 테이블에 INSERT
		String accountName = request.getParameter("accountName");
		String accountPassword = request.getParameter("accountPassword");
		String name = request.getParameter("name");
		
		if(member.getPhone().equals(phone) && member.getSsn().equals(ssn)) {
			
			accountVO account = new accountVO();
			accountDAO accountDao =new accountDAO();
			
			account.setAcct_pwd(accountPassword);
			account.setAcct_name(accountName);
			account.setHolder(name);
			account.setId(id);
			
			accountDao.createAccount(account);
			
			url = "redirect:/";
			
		}else {
			AccountMsg ="계좌 개설을 실패했습니다.";
			url = "redirect:/bank/createAccountForm.jb";
		}
		
		request.setAttribute("AccountMsg", AccountMsg);
		
		
		return url;
	}

}
