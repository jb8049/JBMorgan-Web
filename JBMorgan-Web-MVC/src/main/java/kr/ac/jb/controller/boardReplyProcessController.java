package kr.ac.jb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.jb.board.boardDAO;
import kr.ac.jb.board.boardVO;

public class boardReplyProcessController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
		int parentBoardNo = Integer.parseInt(request.getParameter("parentBoardNo"));
		
		/*
		 * 1. 부모 게시글에 대한 정보를 받아온다
		 * 2. 인서트 해줄 때, 부모 group_no와 같아야 하는 것들, 부모 + 1 해줘야 할 것들
		 * 3. 설정하고 인서트 한다
		 */
		
		boardDAO parentDao = new boardDAO();
		
		// 부모 게시글에 대한 정보를 받아옴
		boardVO parentBoard = parentDao.boardDetail(parentBoardNo);
		
		int parentgroupNo = parentBoard.getGroupNo();
		int parentDepth = parentBoard.getDepth();
		int parentIdent = parentBoard.getIndent();
		
//		family	원글의 family 와 같게
//		parent	부모글의 글번호
//		depth	(parentDepth) + 1
//		indent	(ident) + 1
		
// 		답글에 대한 정보, 이전에 입력한 파라미터 받아오기, id, title content
		String id = request.getParameter("id");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		boardVO replyBoard = new boardVO();
		
		replyBoard.setId(id);
		replyBoard.setTitle(title);
		replyBoard.setContent(content);
		replyBoard.setGroupNo(parentgroupNo);
		replyBoard.setDepth(parentDepth + 1);
		replyBoard.setIndent(parentIdent + 1);
		replyBoard.setParent(parentBoardNo);
		
		boardDAO replyDao = new boardDAO();
		
		// 해당 게시글에 대한 답글 Insert
		replyDao.writeReply(replyBoard);
		
		return "/board/boardList.jb";
	}

}
