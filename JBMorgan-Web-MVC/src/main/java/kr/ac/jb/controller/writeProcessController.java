package kr.ac.jb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.jb.board.boardDAO;
import kr.ac.jb.board.boardVO;

public class writeProcessController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 새글등록을 통해 등록되는 글
		
		String id = request.getParameter("id");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		boardVO board = new boardVO();
		
		board.setId(id);
		board.setTitle(title);
		board.setContent(content);
		
		boardDAO dao = new boardDAO();
		
		dao.writerNew(board);
		
		return "redirect:/board/boardList.jb";
	}

}
