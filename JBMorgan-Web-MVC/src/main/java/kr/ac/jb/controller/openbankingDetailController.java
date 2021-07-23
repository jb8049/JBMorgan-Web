package kr.ac.jb.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.jb.account.accountDAO;
import kr.ac.jb.account.accountVO;
import kr.ac.jb.transaction.transactionDAO;
import kr.ac.jb.transaction.transactionVO;

public class openbankingDetailController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 사용자가 선택한 계좌의 계좌번호와 은행코드가 넘어옴
		// 모달창에 거래내역을 띄우기 위해 list필요
		
		String acctNo = request.getParameter("acctNo");
		String bankCode = request.getParameter("bankCode");
		
		// 은행별 계좌의 정보를 받아옴
		accountVO account = new accountVO();
		accountDAO dao = new accountDAO();
		transactionDAO tDao = new transactionDAO();
		//각 은행별 거래내역 리스트
		List<transactionVO> transactionList = new ArrayList<>();
		
		//은행 코드에 따라 어느 은행으로 가서 거래내역을 들고올지
		switch(bankCode) {
	    	 
		case "J":
			 account = dao.searchJBMorganAccount(acctNo);
			 transactionList = tDao.searchJBMorganTransaction(acctNo);
	         break;
	    case "S":
	    	 account = dao.searchSejinAccount(acctNo);
	    	 
	         break;
	    case "D":
	    	 account = dao.searchDonJoAccount(acctNo);
	    	 transactionList = tDao.searchDonJoTransaction(acctNo);
	    	 break;
	    case "Y":
	    	
	    	break;
		}
		
		request.setAttribute("transactionList", transactionList);
		request.setAttribute("account", account);
		
		return "/bank/openbankingDetail.jsp";
	}

}
