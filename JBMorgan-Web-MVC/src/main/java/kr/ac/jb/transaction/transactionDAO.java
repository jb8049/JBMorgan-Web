package kr.ac.jb.transaction;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.ac.jb.util.ConnectionFactory;
import kr.ac.jb.util.JDBCClose;

public class transactionDAO {

	/**
	 * 계좌 상세 모달창 JBMorgan 거래내역
	 * @param acct_no
	 * @return List<transactionVO>
	 */
	public List<transactionVO> searchJBMorganTransaction(String acct_no) {

		List<transactionVO> transactionList = new ArrayList<>();

		StringBuilder sql = new StringBuilder();
		sql.append(" select TRANSACTION_NO, AMOUNT, COUNTERPART_ACCT_NO, COUNTERPART, TYPE, ");
		sql.append(" COUNTERPART_BANK, ACCT_NO, TRANSACTION_DATE from bank_transaction ");
		sql.append(" WHERE ACCT_NO =? ");
		sql.append(" ORDER BY TRANSACTION_DATE DESC ");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {

			pstmt.setString(1, acct_no);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				transactionVO transaction = new transactionVO();

				transaction.setTransactionNo(rs.getInt("TRANSACTION_NO"));
				transaction.setAmount(rs.getInt("AMOUNT"));
				transaction.setCounterpartAccountNo(rs.getString("COUNTERPART_ACCT_NO"));
				transaction.setCounterpartName(rs.getString("COUNTERPART"));
				transaction.setType(rs.getString("TYPE"));
				transaction.setCounterpartBank(rs.getString("COUNTERPART_BANK"));
				transaction.setAccountNo(rs.getString("ACCT_NO"));
				transaction.setDate(rs.getString("TRANSACTION_DATE"));

				transactionList.add(transaction);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return transactionList;

	}
	
	/**
	 * 계좌 상세 모달창 DonJo 거래내역
	 * @param acct_no
	 * @return List<transactionVO>
	 */
	public List<transactionVO> searchDonJoTransaction(String acct_no) {
		
		List<transactionVO> transactionList = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select balance,  myacc, tr_code, othacc, tran_dt, othbank ");
		sql.append(" from tranhistory@DONJO_link ");
		sql.append(" where myacc=? ");
		sql.append(" order by tran_dt desc ");
		
		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {

			pstmt.setString(1, acct_no);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				transactionVO transaction = new transactionVO();

				transaction.setAmount(rs.getInt("BALANCE"));
				transaction.setCounterpartAccountNo(rs.getString("OTHACC"));
				//transaction.setCounterpartName(rs.getString("NAME"));
				transaction.setType(rs.getString("TR_CODE"));
				transaction.setCounterpartBank(rs.getString("OTHBANK"));
				transaction.setAccountNo(rs.getString("MYACC"));
				transaction.setDate(rs.getString("TRAN_DT"));

				transactionList.add(transaction);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return transactionList;
		
	}
	
	/**
	 * 계좌 상세 모달창 YG 거래내역
	 * @param acct_no
	 * @return List<transactionVO>
	 */
	public List<transactionVO> searchYGTransaction(String acct_no) {
		
		List<transactionVO> transactionList = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT ");
		sql.append(" C.CUSTOMER_ACCOUNT_CHANGE AS AMOUNT, ");
		sql.append(" C.ACCOUNT_NUMBER AS COUNTERPART_ACCT_NO, ");
		sql.append(" H.HISTORY_TASK AS TYPE, ");
		sql.append(" H.RECEIVER_BANK AS COUNTERPART_BANK, ");
		sql.append(" H.RECEIVER_ACCOUNT AS ACCT_NO, ");
		sql.append(" H.HISTORY_DATE AS TRANSACTION_DATE ");
		sql.append(" FROM CUSTOMER_TB@YG_LINK T, CUSTOMER_ACCOUNT@YG_LINK C, HISTORY@YG_LINK H ");
		sql.append(" WHERE T.CUSTOMER_SQ = C.CUSTOMER_SQ AND ");
		sql.append(" C.CUSTOMER_ACCOUNT_SQ = H.CUSTOMER_ACCOUNT_SQ and RECEIVER_ACCOUNT=? ");
		
		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {

			pstmt.setString(1, acct_no);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				transactionVO transaction = new transactionVO();

				transaction.setAmount(rs.getInt("AMOUNT"));
				transaction.setCounterpartAccountNo(rs.getString("COUNTERPART_ACCT_NO"));
				//transaction.setCounterpartName(rs.getString("NAME"));
				transaction.setType(rs.getString("TYPE"));
				transaction.setCounterpartBank(rs.getString("COUNTERPART_BANK"));
				transaction.setAccountNo(rs.getString("ACCT_NO"));
				transaction.setDate(rs.getString("TRANSACTION_DATE"));

				transactionList.add(transaction);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return transactionList;
		
	}
	
	
	
	/**
	 * 계좌 상세 모달창 SeJin 거래내역 (세지은행 거래내역 안만듦)
	 * @param acct_no
	 * @return List<transactionVO>
	 */
	
	
	

	
	/**
	 * 내부 - 내부 거래내역 기록 트랜잭션
	 * 계좌 테이블 update, 거래내역 테이블 insert
	 * @param transactionVO
	 */

	public void transfer(transactionVO transaction) {

		Connection conn = null;
		PreparedStatement pstmt = null;

			try {
			
			conn = new ConnectionFactory().getConnection();
			
			//트랜잭션 실패 시 rollback을 위해 autoCommit False
			conn.setAutoCommit(false);
			
			StringBuilder withdraw_sql = new StringBuilder();
			StringBuilder deposit_sql = new StringBuilder();
			StringBuilder myTransactionSql = new StringBuilder();
			StringBuilder counterTransactionSql = new StringBuilder();
			
			// 내 계좌에서 발생한 출금
			withdraw_sql.append(" update bank_account set balance = balance - ? where acct_no = ? ");
			
			pstmt = conn.prepareStatement(withdraw_sql.toString());

			pstmt.setInt(1, transaction.getAmount());
			pstmt.setString(2, transaction.getAccountNo());
			
			pstmt.executeUpdate();
			
			// 상대방 계좌에 발생한 업데이트
			deposit_sql.append(" update bank_account set balance = balance + ? where acct_no = ? ");
			
			pstmt = conn.prepareStatement(deposit_sql.toString());
			
			pstmt.setInt(1, transaction.getAmount());
			pstmt.setString(2, transaction.getCounterpartAccountNo());

			pstmt.executeUpdate();
			
			// 거래내역 테이블에 기록
			myTransactionSql.append(" INSERT INTO BANK_TRANSACTION(TRANSACTION_NO, AMOUNT, COUNTERPART_ACCT_NO, TYPE, ");
			myTransactionSql.append(" COUNTERPART, COUNTERPART_BANK, ACCT_NO) ");
			myTransactionSql.append(" VALUES(TRANSACTION_SEQ.NEXTVAL, ?, ?, '출금', ?, ?, ?)");
			
			pstmt = conn.prepareStatement(myTransactionSql.toString());
			
			pstmt.setInt(1, transaction.getAmount());
			pstmt.setString(2, transaction.getCounterpartAccountNo());
			pstmt.setString(3, transaction.getCounterpartName());
			pstmt.setString(4, transaction.getCounterpartBank());
			pstmt.setString(5, transaction.getAccountNo());
			
			pstmt.executeUpdate();
			
			counterTransactionSql.append(" INSERT INTO BANK_TRANSACTION(TRANSACTION_NO, AMOUNT, COUNTERPART_ACCT_NO, TYPE, ");
			counterTransactionSql.append(" COUNTERPART, COUNTERPART_BANK, ACCT_NO) ");
			counterTransactionSql.append(" VALUES(TRANSACTION_SEQ.NEXTVAL, ?, ?, '입금', ?, ?, ?)");
			
			pstmt = conn.prepareStatement(counterTransactionSql.toString());
			
			pstmt.setInt(1, transaction.getAmount());
			pstmt.setString(2, transaction.getAccountNo());
			pstmt.setString(3, transaction.getHolder());
			pstmt.setString(4, transaction.getCounterpartBank());
			pstmt.setString(5, transaction.getCounterpartAccountNo());

			pstmt.executeUpdate();

			conn.commit();

		} catch (Exception e) {

			try {

				conn.rollback();

			} catch (Exception e2) {
				e2.printStackTrace();
			}

			e.printStackTrace();

		}finally {
			JDBCClose.close(conn, pstmt);
		}

	}
	
	/**
	 * 오픈뱅킹 계좌이체
	 * @param transaction
	 */
	public boolean openbankingTransfer(transactionVO transaction) {
		
		boolean bool = false;
		String sql ="{call test_transfer(?,?,?,?,?,?,?)";
		
		try(
				Connection conn = new ConnectionFactory().getConnection();
				CallableStatement cstmt = conn.prepareCall(sql);
				
				) {
				
				int loc = 1;
				cstmt.setString(loc++, transaction.getMyBankCode()); // 내 은행코드
				cstmt.setString(loc++, transaction.getCounterpartBank()); // 상대방 은행코드
				cstmt.setInt(loc++, transaction.getAmount()); // 이체할 금액
				cstmt.setString(loc++, transaction.getAccountNo()); // 출금하는 내 은행 계좌
				cstmt.setString(loc++, transaction.getCounterpartAccountNo()); // 입금하는 상대방 은행 계좌
				cstmt.setString(loc++, transaction.getHolder()); // 출금하는 계좌주(나)
				cstmt.setString(loc++, transaction.getCounterpartName()); // 상대방 계좌주
				
				int cnt = cstmt.executeUpdate();
				if(cnt == 1) {
					
					bool = true;
				}
				
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return bool;
		
		
	}

}
