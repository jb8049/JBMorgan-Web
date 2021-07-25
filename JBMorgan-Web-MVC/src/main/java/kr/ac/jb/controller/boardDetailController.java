package kr.ac.jb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.jb.board.boardDAO;
import kr.ac.jb.board.boardVO;

public class boardDetailController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		
		boardDAO dao = new boardDAO();
		boardVO board = dao.boardDetail(boardNo);
		
		request.setAttribute("board", board);
		
		return "/board/boardDetail.jsp";
	}

}
