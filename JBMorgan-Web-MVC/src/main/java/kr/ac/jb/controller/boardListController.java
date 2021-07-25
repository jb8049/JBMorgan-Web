package kr.ac.jb.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.jb.board.boardDAO;
import kr.ac.jb.board.boardVO;

public class boardListController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 등록된 글을 가져와서 request영역에 저장
		
		List<boardVO> boardList = new ArrayList<>();
		
		boardDAO dao = new boardDAO();
		boardVO board = new boardVO();
		
		boardList = dao.searchBoardList();
		
		//boardList가 가지고 있는 값 중 indent가 0이 아닌 것은 
		
		
		request.setAttribute("boardList",boardList);
		
		return "/board/boardList.jsp";
	}

}
