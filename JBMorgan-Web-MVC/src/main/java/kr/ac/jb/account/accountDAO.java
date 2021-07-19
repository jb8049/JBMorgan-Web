package kr.ac.jb.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.ac.jb.transaction.transactionVO;
import kr.ac.jb.util.ConnectionFactory;
import kr.ac.jb.util.JDBCClose;

public class accountDAO {

	/**
	 * 계좌 조회
	 * @param id
	 * @return List<accountVO>
	 */
	
	public List<accountVO> searchAllAccount(String id) {

		List<accountVO> accountList = new ArrayList<>();

		StringBuilder sql = new StringBuilder();

		sql.append(" select acct_no, acct_name, balance from bank_account where member_id=? ");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {

			pstmt.setString(1, id);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

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

	/**
	 * 상세 계좌 조회
	 * @param no
	 * @return accountVO
	 */
	
	public accountVO searchOneAccount(String no) {

		accountVO account = null;

		StringBuilder sql = new StringBuilder();

		sql.append(
				" select holder, acct_no, acct_pwd, acct_name, balance, reg_date from bank_account where acct_no =? ");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());

		) {

			pstmt.setString(1, no);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {

				account = new accountVO();

				account.setAcct_pwd(rs.getString("acct_pwd"));
				account.setAcct_no(rs.getString("acct_no"));
				account.setAcct_name(rs.getString("acct_name"));
				account.setBalance(rs.getInt("balance"));
				account.setReg_date(rs.getString("reg_date"));
				account.setHolder(rs.getString("holder"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return account;
	}

	
	/**
	 * 내부 - 내부 이체했을 경우, 계좌 balance 업데이트
	 * @param transaction
	 */
	
	public void updateAccount(transactionVO transaction) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			
			conn = new ConnectionFactory().getConnection();
			conn.setAutoCommit(false);
			
			StringBuilder deposit_sql = new StringBuilder();
			StringBuilder withdraw_sql = new StringBuilder();
			
			// 내 계좌
			deposit_sql.append(" update bank_account set balance = balance - ? where acct_no = ? ");

			pstmt.setInt(1, transaction.getAmount());
			pstmt.setString(2, transaction.getAccountNo());
			
			pstmt.executeUpdate();
			
			// 상대방 계좌
			withdraw_sql.append(" update bank_account set balance = balance + ? where acct_no = ? ");

			pstmt.setInt(1, transaction.getAmount());
			pstmt.setString(2, transaction.getCounterpartAccountNo());

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
	 * 계좌 이체 후, 계좌 테이블에 내 계좌 / 상대방 계좌 balance 업데이트 => 업데이트 된 계좌의 balance를 들고오기 위한 것
	 */
	
	public accountVO searchBalance(transactionVO transaction) {

		StringBuilder myAccountsql = new StringBuilder();
		StringBuilder counterAccoubtSql = new StringBuilder();

		myAccountsql.append(" select balance from bank_account where acct_no =? ");
		counterAccoubtSql.append(" select balance from bank_account where acct_no =? ");

		accountVO accountBalance = new accountVO();

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement myAccountsql_pstmt = conn.prepareStatement(myAccountsql.toString());
				PreparedStatement counterAccoubtSql_pstmt = conn.prepareStatement(counterAccoubtSql.toString());

		) {
			// select를 따로 하고 rs를 따로 만든 이유는, select했을 때 내 계좌와 상대 계좌 구분이 어려울 수 있어서
			myAccountsql_pstmt.setString(1, transaction.getAccountNo());

			ResultSet myRs = myAccountsql_pstmt.executeQuery();

			myRs.next();

			accountBalance.setBalance(myRs.getInt("balance"));

			counterAccoubtSql_pstmt.setString(1, transaction.getCounterpartAccountNo());

			ResultSet counterRs = counterAccoubtSql_pstmt.executeQuery();

			counterRs.next();

			accountBalance.setCounterBalance(counterRs.getInt("balance"));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return accountBalance;

	}

}
