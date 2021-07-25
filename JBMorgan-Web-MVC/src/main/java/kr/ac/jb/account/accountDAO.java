package kr.ac.jb.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.ac.jb.util.ConnectionFactory;

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
	 * JBMorgan 계좌 상세 조회
	 * @param no
	 * @return accountVO
	 */
	
	public accountVO searchJBMorganAccount(String no) {

		accountVO account = null;

		StringBuilder sql = new StringBuilder();

		sql.append(" select holder, acct_no, acct_pwd, acct_name, balance, reg_date from bank_account where acct_no =? ");

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
	 * DonJo 계좌 상세 조회
	 * @param no
	 * @return accountVO
	 */
	
	public accountVO searchDonJoAccount(String no) {
		
		accountVO account = null;

		StringBuilder sql = new StringBuilder();
		sql.append(" select M.NAME, A.ACCOUNT, A.BALANCE, A.ENROLL_DT, A.ACCALIAS, A.ACCPWD"); 
		sql.append(" from  ACCOUNTDB@DONJO_link A, USERINFO@DONJO_link M  ");
		sql.append(" WHERE A.ID = M.ID AND ACCOUNT =? ");
		
		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());

		) {

			pstmt.setString(1, no);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {

				account = new accountVO();

				account.setAcct_pwd(rs.getString("ACCPWD"));
				account.setAcct_no(rs.getString("ACCOUNT"));
				account.setAcct_name(rs.getString("ACCALIAS"));
				account.setBalance(rs.getInt("BALANCE"));
				account.setReg_date(rs.getString("ENROLL_DT"));
				account.setHolder(rs.getString("NAME"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return account;
		
	}
	
	/**
	 * SeJin 계좌 상세 조회
	 * @param no
	 * @return accountVO
	 */
	public accountVO searchSejinAccount(String no) {

		accountVO account = null;

		StringBuilder sql = new StringBuilder();

		sql.append(" select m.name holder, a.account_number as acct_no, a.account_password acct_pwd, a.nickname as acct_name, ");
		sql.append(" a.balance as balance, to_char(a.reg_date,'yyyy-mm-dd') as reg_date ");
		sql.append(" from sj_account_info@sejinBank_link a, sj_member@sejinBank_link m ");
		sql.append(" where m.id = a.member_id ");
		sql.append(" and a.account_number =? ");
		
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
	 * YG 계좌 조회
	 * @param no
	 * @return
	 */
	
	public accountVO searchYGAccount(String no) {
		
		accountVO account = null;

		StringBuilder sql = new StringBuilder();

		sql.append(" SELECT ");
		sql.append(" CUSTOMER_NAME AS HOLDER, ");
		sql.append(" ACCOUNT_NUMBER AS ACCT_NO, ");
		sql.append(" CUSTOMER_ACCOUNT_PWD AS ACCT_PWD, ");
		sql.append(" CUSTOMER_ACCOUNT_ALIAS AS ACCT_NAME, ");
		sql.append(" CUSTOMER_ACCOUNT_CHANGE AS BALANCE, ");
		sql.append(" CUSTOMER_ACCOUNT_INPUT_DATE AS REG_DATE FROM ");
		sql.append(" CUSTOMER_TB@YG_LINK T, CUSTOMER_ACCOUNT@YG_LINK C ");
		sql.append(" WHERE ");
		sql.append(" T.CUSTOMER_SQ = C.CUSTOMER_SQ AND ACCOUNT_NUMBER=? ");
		
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
	 * 신규 계좌 개설
	 * @param account
	 */
	
	public void createAccount(accountVO account) {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" insert into bank_account(ACCT_NO, ACCT_PWD, ACCT_NAME, ");
		sql.append(" BALANCE, MEMBER_ID, HOLDER ) "); 
		sql.append(" values(to_char(sysdate, 'yyyymmdd')||to_char(systimestamp, 'ff6' ), ?, ? ");
		sql.append(" , 0, ?, ?) ");
 		
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				) {
			
				int loc = 1;
				pstmt.setString(loc++, account.getAcct_pwd());
				pstmt.setString(loc++, account.getAcct_name());
				pstmt.setString(loc++, account.getId());
				pstmt.setString(loc++, account.getHolder());
				
				pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
			
	}
	
	public List<accountVO> openbankingAccount(String ssn) {
		
		List<accountVO> openbankingAccountList = new ArrayList<>();
		
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT A.ACCT_NO, A.ACCT_PWD,A.BALANCE, A.ACCT_NAME, M.NAME, (select bank_code from bank where bank_name ='JBMorgan') AS BANK ");
		sql.append(" FROM BANK_MEMBER M, BANK_ACCOUNT A ");
		sql.append(" WHERE M.MEMBER_ID = A.MEMBER_ID AND M.SSN =? ");
		sql.append(" UNION ");
		sql.append(" select A.ACCOUNT_NUMBER, A.ACCOUNT_PASSWORD, A.BALANCE, A.NICKNAME, M.NAME, (select bank_code from bank where bank_name ='Sejin') ");
		sql.append(" from SJ_MEMBER@sejinBank_link M, SJ_ACCOUNT_INFO@sejinBank_link A ");
		sql.append(" where M.ID = A.MEMBER_ID AND M.JUMIN_NO=? ");
		sql.append(" UNION ");
		sql.append(" select A.ACCOUNT, A.ACCPWD, A.BALANCE, A.ACCALIAS, M.NAME, (select bank_code from bank where bank_name ='DonJo') AS BANK ");
		sql.append(" from USERINFO@DONJO_link M, ACCOUNTDB@DONJO_link A ");
		sql.append(" where M.ID = A.ID AND M.RES_CODE=? ");
		sql.append(" UNION ");
		sql.append(" SELECT A.ACCOUNT_NUMBER, A.CUSTOMER_ACCOUNT_PWD,A.CUSTOMER_ACCOUNT_CHANGE, A.CUSTOMER_ACCOUNT_ALIAS, M.CUSTOMER_NAME, (select bank_code from bank where bank_name ='YG') AS BANK ");
		sql.append(" FROM CUSTOMER_TB@YG_LINK M, CUSTOMER_ACCOUNT@YG_LINK A ");
		sql.append(" WHERE M.CUSTOMER_SQ = A.CUSTOMER_SQ AND M.CUSTOMER_JUMIN_NO =? ");
		
		
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				) {
			
				
				int loc = 1;
				
				pstmt.setString(loc++, ssn);
				pstmt.setString(loc++, ssn);
				pstmt.setString(loc++, ssn);
				pstmt.setString(loc++, ssn);
			
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {
					
					accountVO account = new accountVO();
					
					account.setAcct_no(rs.getString("acct_no"));
					account.setAcct_pwd(rs.getString("acct_pwd"));
					account.setBalance(rs.getInt("balance"));
					account.setAcct_name(rs.getString("acct_name"));
					account.setHolder(rs.getString("name"));
					account.setBankCode(rs.getString("bank"));
					
					openbankingAccountList.add(account);
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return openbankingAccountList;
		
		
	}
	
	
}
