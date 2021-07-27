package kr.ac.jb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class boardReplyController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 댓글이 달릴 부모 게시판의 no 추출
		// 답글 쓰는 페이지로 이동해야함
		int parentBoardNo = Integer.parseInt(request.getParameter("boardNo"));
		
		request.setAttribute("parentBoardNo", parentBoardNo);
		
		return "/board/boardReplyForm.jsp";
	}

}
