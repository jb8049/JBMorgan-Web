package kr.ac.jb.util;

public class Pagination {

	private int rowSize = 10; // 한 페이지 글 개수, page 당 10개 출력
	private int blockSize = 5; // 페이지 블록 개수, 5page씩 출력
	private int curPage; // 현재 페이지
	private int totalRow; // 전체 레코드 개수
	
	private int totalPage; //전체 페이지 개수
	
	private int startRow; // 페이지별 시작 글 번호
	private int endRow; // 페이지별 끝 글 번호
	private int curBlock; // 사용자가 현재 속한 블록
	
	// [1] [2] [3] [4] [5] => 1번 째 블록
	// [6] [7] [8] [9] [10] => 2번 째 블록
 	
	private int startPage; // 블록별 시작 페이지
	private int endPage; // 블록별 끝 페이지
	
	public Pagination() {
		
		
	}

	// 총 게시글 수, 사용자가 속한 페이지를 받음
	public Pagination(int totalRow, int curPage) {
		
		this.totalRow = totalRow;
		this.curPage = curPage;
		
		this.startRow = getStartRow(this.curPage, rowSize); // 현재 페이지에서 5개의 글을 보여주어야함
		this.endRow = getEndRow(this.curPage, rowSize, this.totalRow);
		
		// 총 게시글 수를 한 페이지에 보여줄 게시글 수로 나누면, 필요한 총 페이지 수
		this.totalPage = getTotalPage(totalRow, rowSize);
		
		// 사용자가 현재 속해있는 블록
		this.curBlock = getCurBlock(this.curPage, blockSize);
		
		// 사용자가 속해있는 블록 시작 페이지, 블록 끝 페이지
		this.startPage = getStartPage(this.curBlock);
		this.endPage = getEndPage(this.startPage, this.totalPage);
		
	}
	
	//선택한 페이지의 시작하는 글번호
	public int getStartRow(int curPage, int rowSize) {
		// 한 페이지에 10개의 글씩 보여준다 => 1~10번 레코드 1페이지에서 보여줫음
		// 2페이지에서 11~20 레코드를 2페이지에서 보여줘야함
		return (curPage - 1) * rowSize + 1;
	}
	
	public int getEndRow(int curPage, int rowSize, int totalRow) {
		
		// 페이지에 출력될 마지막 레코드
		int endRow = curPage * rowSize;
		// 3 * 5 = 15, 총 레코드는 16 (페이지 블록 3개, 한 페이지에 5개 출력)
		// 4 * 5 = 20, 총 레코드는 16 => 이 때 마지막 endRow는 16이 되야함
		
		if(endRow > totalRow) {
			
			endRow = totalRow;
		}
		
		return endRow;
	}
	
	// 전체 페이지 수
	public int getTotalPage(int totalRow, int rowSize) {
		
		// 총 레코드 16개 / 한 페이지 글 개수 5개
		// 3.2 => 크거나 같은, 정수값 4를 doble형으로 반환함 => 4page까지 있어야함ㅁ
		return (int) Math.ceil(totalRow * 1.0 / rowSize);
	}
	
	// 현재 속한 블록 번호
	// 3페이지에 있으면, 1번 째 블록에 있음
	// 6페이지에 있으면, 2번 째 블록에 있음
	//Math.round() 소수점 첫째자리에서 반올림하여 정수로 반환하는 함수
	public int getCurBlock(int curPage, int blockSize) {
		
		int curBlock =(int) Math.round((curPage - 1 + blockSize) / blockSize);

		return curBlock;
	}
	
	// 현재 속한 블록의 시작 블록 번호
	public int getStartPage(int curBlock) {
		int startPage = (curBlock - 1) * blockSize + 1;
		return startPage;
		
	}
	
	
	// 현재 속한 블록의 끝 블록 번호
	public int getEndPage(int startPage, int totalPage) {
		
		int endPage = startPage + (blockSize - 1);
		
		// 끝 페이지가 총 페이지보다 큰 경우
		// [1] [2] [3] [4] [5]
		// [6] [7] [8]
		// 2번 째 블록에 속해있고, 현재 페이지가 7p인 경우
		// 블록 시작 번호 [6](startPage) => [6] + 4 => 10이됨...
		// 근데 총 페이지는 [8]까지 있다, endPage > totalPage이면은 교체해주어야함
		
		if(endPage > totalPage) {
			
			endPage = totalPage;
		}
		
		return endPage;
		
	}


	public int getRowSize() {
		return rowSize;
	}


	public void setRowSize(int rowSize) {
		this.rowSize = rowSize;
	}


	public int getBlockSize() {
		return blockSize;
	}


	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}


	public int getCurPage() {
		return curPage;
	}


	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}


	public int getTotalRow() {
		return totalRow;
	}


	public void setTotalRow(int totalRow) {
		this.totalRow = totalRow;
	}


	public int getStartRow() {
		return startRow;
	}


	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}


	public int getEndRow() {
		return endRow;
	}


	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}


	public int getCurBlock() {
		return curBlock;
	}


	public void setCurBlock(int curBlock) {
		this.curBlock = curBlock;
	}


	public int getStartPage() {
		return startPage;
	}


	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}


	public int getEndPage() {
		return endPage;
	}


	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}


	public int getTotalPage() {
		return totalPage;
	}


	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}


