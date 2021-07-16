package kr.ac.jb.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.jb.account.accountDAO;
import kr.ac.jb.account.accountVO;
import kr.ac.jb.member.memberVO;

public class searchAccountController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 로그인한 회원이 가진 계좌를 조회, balance도 볼 수 있음, 모든 계좌의 총 잔액, 즐겨찾기 기능
		// 계좌번호 잔액 => 누르면 계좌 상세, 잔액, 거래 내역(최신 순, 거래내역 보고 즐겨찾기 가능?), 계좌 이체
		
		// 우선, 회원이 가지고 있는 전체 계좌를 조회한다
		
		List<accountVO> accountList = new ArrayList<>();
		
		HttpSession session = request.getSession();
		
		memberVO userVO = (memberVO)session.getAttribute("userVO");
		
		String id = userVO.getId();
		
		accountDAO dao = new accountDAO();
		
		accountVO account = new accountVO();
		
		//아이디에 해당하는 계좌 정보를 얻어옴
		accountList = dao.searchAllAccount(id);
		
		//공유영역에 등록해서 searchAccount.jsp에서 정보를 조회할 수 있음
		request.setAttribute("accountList", accountList);
		
		return "/bank/searchAccount.jsp";
	}

}
