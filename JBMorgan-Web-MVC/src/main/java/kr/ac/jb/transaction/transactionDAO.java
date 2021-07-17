package kr.ac.jb.transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.ac.jb.util.ConnectionFactory;

public class transactionDAO {
	
	
	// detail페이지의 모달창에 들어갈 계좌의 거래내역
	public List<transactionVO> searchTransaction(String acct_no) {
		
		List<transactionVO> transactionList = new ArrayList<>();
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select TRANSACTION_NO, AMOUNT, COUNTERPART_ACCT_NO, COUNTERPART, ");
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
	
	// 내부-내부 거래내역 트랜잭션
	public void writeTransaction(transactionVO transaction) {
		
		// 거래내역을 insert함
		
	}
	
}
