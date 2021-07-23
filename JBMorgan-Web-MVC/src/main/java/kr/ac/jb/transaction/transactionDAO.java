package kr.ac.jb.transaction;

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
		sql.append(" select t.balance,  t.myacc, t.tr_code, t.othacc, n.name, t.tran_dt, t.othbank ");
		sql.append(" from tranhistory@DONJO_link t, ");
		sql.append(" (select a.account as account, u.name as name ");
		sql.append(" from userinfo@DONJO_link u, accountdb@DONJO_link a ");
		sql.append(" where u.id = a.id ) n ");
		sql.append(" where  t.othacc = n.account and t.myacc=? ");
		
		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {

			pstmt.setString(1, acct_no);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				transactionVO transaction = new transactionVO();

				transaction.setAmount(rs.getInt("BALANCE"));
				transaction.setCounterpartAccountNo(rs.getString("OTHACC"));
				transaction.setCounterpartName(rs.getString("NAME"));
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

}
