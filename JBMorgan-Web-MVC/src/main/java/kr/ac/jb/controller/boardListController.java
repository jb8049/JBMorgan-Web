package kr.ac.jb.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.jb.board.boardDAO;
import kr.ac.jb.board.boardVO;
import kr.ac.jb.util.Pagination;

public class boardListController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		// 페이징 적용 전 boardList
		//List<boardVO> boardList = new ArrayList<>();
		
		//boardDAO dao = new boardDAO();
		
		//boardList = dao.searchBoardList();
		
		//boardList가 가지고 있는 값 중 indent가 0이 아닌 것은 
		//request.setAttribute("boardList",boardList);
		
		
		boardDAO dao = new boardDAO();
		
		// 레코드 총 개수(게시판에 기록된 게시글 총 개수)
		int totalRow = dao.getTotalRecord();
		
		// 시작, 현재 페이지, 페이지에 들어오면 1페이지부터 보여주니까
		int curPage = 1;
		
		// 사용자가 보기 원하는 페이지(boardList.jsp에서 사용자가 원하는 페이지 클릭했을 때, 넘어온 파라미터)
		if(request.getParameter("page") != null) {
			
			curPage = Integer.parseInt(request.getParameter("page"));
		}
		
		// 총 레코드 개수와 현재 페이지, 생성자를 활용해 필요한 정보 셋팅
		Pagination pagination = new Pagination(totalRow, curPage);
		
		
		// 총 페이지 수
		int totalPage = pagination.getTotalPage();
		
		// 해당 페이지의 시작 레코드와 끝 레코드를 조회
		int startRow = pagination.getStartRow();
		int endRow = pagination.getEndRow();
		
		// 사용자가 선택한 페이지의 리스트를 받아옴
		// 페이지마다 시작 레코드와 끝 레코드가 다름
		List<boardVO> boardList = dao.boardListPaging(startRow, endRow);
		
		request.setAttribute("boardList", boardList);
		
		// 블록별 시작 페이지, 블록별 끝 페이지, 블록에 대한 설정을 위한 것
		int startPage = pagination.getStartPage();
		int endPage = pagination.getEndPage();
		
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		
		// 총 페이지 수에 따른 Prev, Next 버튼 활성화를 위해
		request.setAttribute("totalPage", totalPage);
		
		// 내가 선택한 현재 페이지에 표시해주기 위해
		request.setAttribute("curPage", curPage);
		
		return "/board/boardList.jsp";
	}

}
