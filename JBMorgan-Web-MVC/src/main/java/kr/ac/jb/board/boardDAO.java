package kr.ac.jb.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.ac.jb.util.ConnectionFactory;

public class boardDAO {
	
	
	/**
	 * 새글 등록 GROUP_NO = 0, DEPTH = 0, INDENT = 0
	 * @param board
	 */
	
	public void writerNew(boardVO board) {

		StringBuilder sql = new StringBuilder();

		sql.append(" insert into bank_board ");
		sql.append(" (BOARD_NO, GROUP_NO, DEPTH, INDENT, PARENT, TITLE, CONTENT, MEMBER_ID) ");
		sql.append(" values(BOARD_SEQ.nextval, BOARD_SEQ.nextval,0,0,0,?,?,?) ");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {

			int loc = 1;
			pstmt.setString(loc++, board.getTitle());
			pstmt.setString(loc++, board.getContent());
			pstmt.setString(loc++, board.getId());

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		}

	}
	
	/**
	 * 게시판 리스트 조회
	 * @return
	 */
	
	public List<boardVO> searchBoardList() {
		
		String title ="";
		List<boardVO> boardList = new ArrayList<>();
		StringBuilder sql = new StringBuilder();

		sql.append(" select BOARD_NO, GROUP_NO, DEPTH, INDENT, PARENT, TITLE, CONTENT, to_char(REG_DATE,'yyyy-mm-dd') as REG_DATE, MEMBER_ID ");
		sql.append(" from bank_board ");
		sql.append(" order by GROUP_NO desc, DEPTH asc ");

		try (	Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {

			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				boardVO board = new boardVO();
				
				board.setBoardNo(rs.getInt("BOARD_NO"));
				board.setGroupNo(rs.getInt("GROUP_NO"));
				board.setDepth(rs.getInt("DEPTH"));
				board.setIndent(rs.getInt("INDENT"));
				
				/*
				 * if(rs.getInt("INDENT") !=0) { title = " ㄴ" + rs.getString("TITLE"); }else {
				 * // 들여쓰기가 0인 parent 게시글 title = rs.getString("TITLE"); }
				 */
				
				board.setParent(rs.getInt("PARENT"));
				board.setTitle(rs.getString("TITLE"));
				board.setContent(rs.getString("CONTENT"));
				board.setRegDate(rs.getString("REG_DATE"));
				board.setId(rs.getString("MEMBER_ID"));
				
				boardList.add(board);
				
			}
			

		} catch (Exception e) {
			e.printStackTrace();

		}
		
		
		
		return boardList;
	}
	
	/**
	 * 게시글 상세
	 * 부모 게시글 데이터 받아올 때 사용
	 * @param boardNo
	 * @return
	 */
	
	public boardVO boardDetail(int boardNo) {
		
		boardVO board = new boardVO();
		
		List<boardVO> boardList = new ArrayList<>();
		StringBuilder sql = new StringBuilder();

		sql.append(" select BOARD_NO, GROUP_NO, DEPTH, INDENT, PARENT, TITLE, CONTENT, ");
		sql.append(" to_char(REG_DATE,'yyyy-mm-dd') as REG_DATE, MEMBER_ID from bank_board where board_no =? ");
		
		
		try (	Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			
			pstmt.setInt(1, boardNo);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				

				board.setBoardNo(rs.getInt("BOARD_NO"));
				board.setGroupNo(rs.getInt("GROUP_NO"));
				board.setDepth(rs.getInt("DEPTH"));
				board.setIndent(rs.getInt("INDENT"));
				board.setParent(rs.getInt("PARENT"));
				board.setTitle(rs.getString("TITLE"));
				board.setContent(rs.getString("CONTENT"));
				board.setRegDate(rs.getString("REG_DATE"));
				board.setId(rs.getString("MEMBER_ID"));
				
				boardList.add(board);
				
			}
			

		} catch (Exception e) {
			e.printStackTrace();

		}
		
		return board;
		
	}
	
	/**
	 * 답글 등록
	 * @param replyBoard
	 */
	
	public void writeReply(boardVO replyBoard) {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" insert into bank_board (BOARD_NO, GROUP_NO, DEPTH, INDENT, PARENT, TITLE, CONTENT, MEMBER_ID) ");
		sql.append(" values(BOARD_SEQ.nextval,? ,? ,? ,? ,? ,? ,?) ");
		
		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			
			int loc = 1;
			pstmt.setInt(loc++, replyBoard.getGroupNo());
			pstmt.setInt(loc++, replyBoard.getDepth());
			pstmt.setInt(loc++, replyBoard.getIndent());
			pstmt.setInt(loc++, replyBoard.getParent());
			pstmt.setString(loc++, replyBoard.getTitle());
			pstmt.setString(loc++, replyBoard.getContent());
			pstmt.setString(loc++, replyBoard.getId());

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		}
		
	}
	
	/**
	 * 게시판 총 레코드 개수 반환
	 */
	public int getTotalRecord() {
		
		int totalRecord = 0;
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select count(*) as totalRecord from bank_board ");
		
		try (	Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				totalRecord = rs.getInt("totalRecord");
			}
				
			

		} catch (Exception e) {
			e.printStackTrace();

		}
	
		
		return totalRecord ;
	}
}
