package kr.ac.jb.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
	
	public accountVO searchOneAccount(String acct_no) {
		
		accountVO account = new accountVO();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select acct_no, acct_name, balance, reg_date from bank_account where acct_no =? ");
		
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				
				) {
				
				pstmt.setString(1, acct_no);
				
				ResultSet rs = pstmt.executeQuery();
				
				rs.next();
				
				account.setAcct_no(rs.getString("acct_no"));
				account.setAcct_name(rs.getString("acct_name"));
				account.setBalance(rs.getInt("balance"));
				account.setReg_date(rs.getString("reg_date"));
				
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return account;
		
	}
	
	
	
}
