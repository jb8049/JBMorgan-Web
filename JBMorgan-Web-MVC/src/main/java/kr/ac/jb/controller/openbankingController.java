package kr.ac.jb.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.jb.account.accountDAO;
import kr.ac.jb.account.accountVO;
import kr.ac.jb.member.memberDAO;
import kr.ac.jb.member.memberVO;

public class openbankingController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		memberVO userVO = (memberVO) session.getAttribute("userVO");	
		
		String id = userVO.getId();
		
		memberDAO dao = new memberDAO();
		
		// 오픈뱅킹 동의o => 'Y'로 agreement를 업데이트하고, session에 있는 userVO도 업데이트해야함
		
		dao.openBankingAgree(id);
		
		// 업데이트된 동의여부를 가져와서 session의 agreement를 최신화
		String agreement = dao.agreeMember(id);
		userVO.setAgreement(agreement);
		
		memberVO member = dao.searchOneMember(id);
		
		String ssn = member.getSsn();
		
		
		accountDAO accountDao = new accountDAO();
		List<accountVO> openbankingAccountList = accountDao.openbankingAccount(ssn);
		
		request.setAttribute("openbankingAccountList", openbankingAccountList);
		
		return "/bank/openbanking.jsp";
	}

}
