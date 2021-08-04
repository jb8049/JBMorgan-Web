package kr.ac.jb.util;

public class Pagination {

	private int rowSize = 5; // 한 페이지 글 개수
	private int blockSize = 3; // 페이지 블록 개수
	private int curPage; // 현재 페이지
	private int totalRow; // 전체 레코드 개수
	private int startRow; // 페이지별 시작 글 번호
	private int endRow; // 페이지별 끝 글 번호
	private int curBlock; // 페이지의 블록 개수
	private int startPage; // 시작 페이지
	private int endPage; // 끝 페이지
	
	public Pagination() {
		
		
	}

	
	public Pagination(int totalRow, int curPage) {
		
		this.totalRow = totalRow;
		this.curPage = curPage;
		// 생성자에서 받아온 curPage(현재페이지), startRow는 시작할 레코드
		this.startRow = getStartRow(this.curPage, rowSize); // 현재 페이지에서 5개의 글을 보여주어야함
		this.endRow = getEndRow(this.curPage, rowSize, this.totalRow);
	}
	
	//선택한 페이지의 시작하는 글번호
	public int getStartRow(int curPage, int rowSize) {
		// 한 페이지에 5개의 글씩 보여준다 => 1~5번 레코드 1페이지에서 보여줫음
		// 2페이지에서 6~10 레코드를 2페이지에서 보여줘야함
		return (curPage - 1) * rowSize + 1;
	}
	
	public int getEndRow(int curPage, int rowSize, int totalRow) {
		
		// 페이지에 출력될 마지막 레코드
		int endRow = curPage * rowSize;
		// 3* 5 = 15, 총 레코드는 16 (페이지 블록 3개 한 페이지에 5개 출력)
		// 4 * 5 = 20, 총 레코드는 16 => 이 때 마지막 endRow는 totalRow
		
		if(endRow > totalRow) {
			
			endRow = totalRow;
		}
		
		return endRow;
	}
	
}


