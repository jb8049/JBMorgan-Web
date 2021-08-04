package kr.ac.jb.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.jb.board.boardDAO;
import kr.ac.jb.board.boardVO;
import kr.ac.jb.util.Pagination;

public class boardListController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		
		
		
		List<boardVO> boardList = new ArrayList<>();
		
		boardDAO dao = new boardDAO();
		
		boardList = dao.searchBoardList();
		
		//boardList가 가지고 있는 값 중 indent가 0이 아닌 것은 
		request.setAttribute("boardList",boardList);
		
		// 레코드 총 개수(총 게시글)
		int totalRow = dao.getTotalRecord();
		
		// 시작, 현재 페이지, 페이지에 들어오면 1페이지부터 보여주니까ㅣ
		int curPage = 1;
		
		// 사용자가 보기 원하는 페이지
		if(request.getParameter("page") != null) {
			
			curPage = Integer.parseInt(request.getParameter("page"));
		}
		
		Pagination pagination = new Pagination(totalRow, curPage);
		
		
		
		
		return "/board/boardList.jsp";
	}

}
