package kr.ac.jb.transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.ac.jb.util.ConnectionFactory;

public class transactionDAO {
	
	/**
	 * // 계좌 상세 모달창에 들어갈 계좌의 거래내역
	 * @param acct_no
	 * @return List<transactionVO>
	 */
	
	public List<transactionVO> searchTransaction(String acct_no) {
		
		List<transactionVO> transactionList = new ArrayList<>();
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select TRANSACTION_NO, AMOUNT, COUNTERPART_ACCT_NO, COUNTERPART, TYPE, ");
		sql.append(" COUNTERPART_BANK,BALANCE, ACCT_NO, TRANSACTION_DATE from bank_transaction ");
		sql.append(" WHERE ACCT_NO =? ");
		
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				) {
			
				pstmt.setString(1, acct_no);
			
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {
					
				   transactionVO transaction = new transactionVO();
				   
				   transaction.setTransactionNo(rs.getInt("TRANSACTION_NO"));
				   transaction.setAmount(rs.getInt("AMOUNT"));
				   transaction.setCounterpartAccountNo(rs.getString("COUNTERPART_ACCT_NO"));
				   transaction.setCounterpartName(rs.getString("COUNTERPART"));
				   transaction.setType(rs.getString("TYPE"));
				   transaction.setCounterpartBank(rs.getString("COUNTERPART_BANK"));
				   transaction.setBalance(rs.getInt("BALANCE"));
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
	 * 내부 - 내부 거래내역 기록 트랜잭션
	 * @param transaction
	 */
	
	public void writeTransaction(transactionVO transaction) {
		

		StringBuilder mySql = new StringBuilder();
		StringBuilder counterSql = new StringBuilder();
		
		
		mySql.append(" INSERT INTO BANK_TRANSACTION(TRANSACTION_NO, AMOUNT, COUNTERPART_ACCT_NO, TYPE, ");
		mySql.append(" COUNTERPART, COUNTERPART_BANK, BALANCE, ACCT_NO) ");
		mySql.append(" VALUES(TRANSACTION_SEQ.NEXTVAL, ?, ?, '출금', ?, ?, ?, ?)" );
		
		counterSql.append(" INSERT INTO BANK_TRANSACTION(TRANSACTION_NO, AMOUNT, COUNTERPART_ACCT_NO, TYPE, ");
		counterSql.append(" COUNTERPART, COUNTERPART_BANK, BALANCE, ACCT_NO) ");
		counterSql.append(" VALUES(TRANSACTION_SEQ.NEXTVAL, ?, ?, '입금', ?, ?, ?, ?)" );
		
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement mySqlPstmt = conn.prepareStatement(mySql.toString());
				PreparedStatement counterSqlPstmt = conn.prepareStatement(counterSql.toString());
				) {
				
				
				
			mySqlPstmt.setInt(1, transaction.getAmount());
			mySqlPstmt.setString(2, transaction.getCounterpartAccountNo());
			mySqlPstmt.setString(3, transaction.getCounterpartName());
			mySqlPstmt.setString(4, transaction.getCounterpartBank());
			mySqlPstmt.setInt(5, transaction.getBalance());
			mySqlPstmt.setString(6, transaction.getAccountNo());
				
			mySqlPstmt.executeUpdate();
			
			counterSqlPstmt.setInt(1, transaction.getAmount());
			counterSqlPstmt.setString(2, transaction.getAccountNo());
			counterSqlPstmt.setString(3, transaction.getHolder());
			counterSqlPstmt.setString(4, transaction.getCounterpartBank());
			counterSqlPstmt.setInt(5, transaction.getCounterBalance());
			counterSqlPstmt.setString(6, transaction.getCounterpartAccountNo());
			
			counterSqlPstmt.executeUpdate();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		
		
	}
	
}

