package kr.ac.jb.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.ac.jb.transaction.transactionVO;
import kr.ac.jb.util.ConnectionFactory;

public class accountDAO {
	
	// 계좌 조회
	public List<accountVO> searchAllAccount(String id) {
		
		List<accountVO> accountList = new ArrayList<>();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select acct_no, acct_name, balance from bank_account where member_id=? ");
		
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				) {
				
				pstmt.setString(1, id);
				
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {
					
					accountVO account = new accountVO();
					
					account.setAcct_no(rs.getString("acct_no"));
					account.setAcct_name(rs.getString("acct_name"));
					account.setBalance(rs.getInt("balance"));

					accountList.add(account);
				}
			 	
				
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return accountList;
		
	}
	
	public accountVO searchOneAccount(String no) {
		
		accountVO account = null;
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select acct_no, acct_pwd, acct_name, balance, reg_date from bank_account where acct_no =? ");
		
		
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				
				) {
			
				pstmt.setString(1, no);
				
				ResultSet rs = pstmt.executeQuery();
				
				if(rs.next()) {
					
					account = new accountVO();
					
					account.setAcct_pwd(rs.getString("acct_pwd"));
					account.setAcct_no(rs.getString("acct_no"));
					account.setAcct_name(rs.getString("acct_name"));
					account.setBalance(rs.getInt("balance"));
					account.setReg_date(rs.getString("reg_date"));					
				}
					
				
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return account;	
	}
	
	
	// 내부-내부 이체했을 경우, 계좌 balance 업데이트
	public void updateAccount(transactionVO transaction) {
		
		// 송금할 금액을 받아야할듯?
		
		//필요한 정보는 내 계좌, 상대방 계좌, 송금한 금액
		
		
	}
	
	
}

	