package kr.ac.jb.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.ac.jb.util.ConnectionFactory;

public class memberDAO {
	
	/**
	 * 회원가입
	 * @param member
	 */
	public void register(memberVO member) {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" insert into bank_member (MEMBER_ID,PASSWORD,NAME, PHONE, EMAIL, SSN) ");
		sql.append(" VALUES(?,?,?,?,?,?) ");
		
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				) {
			
				
				int loc = 1;
				
				pstmt.setString(loc++, member.getId());
				pstmt.setString(loc++, member.getPassword());
				pstmt.setString(loc++, member.getName());
				pstmt.setString(loc++, member.getPhone());
				pstmt.setString(loc++, member.getEmail());
				pstmt.setString(loc++, member.getSsn());
				
				pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * login
	 * @param member
	 * @return userVO
	 */
	public memberVO login(memberVO member) {
		
		memberVO userVO = null;
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select member_id, name, agreement from bank_member ");
		sql.append(" where member_id=? and password=? ");
		
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				) {
			
				int loc = 1;
				pstmt.setString(loc++, member.getId());
				pstmt.setString(loc++, member.getPassword());
				
				ResultSet rs = pstmt.executeQuery();
				
				
				if(rs.next()) {
					
					userVO = new memberVO();
					
					userVO.setId(rs.getString("member_id"));
					userVO.setName(rs.getString("name"));
					userVO.setAgreement(rs.getString("agreement"));
				}
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return userVO;
	}
	
	
	/**
	 * 카카오 로그인 member_id 조회
	 * @param kakao_id
	 * @return memberVO
	 */
	
	public memberVO searchKakoId(String kakao_id) {
		
		memberVO userVO = null;
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select member_id, name, agreement from bank_member ");
		sql.append(" where member_id=? ");
		
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				) {
			
				pstmt.setString(1, kakao_id);
				
				ResultSet rs = pstmt.executeQuery();
				
				if(rs.next()) {
					
					userVO = new memberVO();
					
					userVO.setId(rs.getString("member_id"));
					userVO.setName(rs.getString("name"));
					userVO.setAgreement(rs.getString("agreement"));
				}
					
		} catch (Exception e) {
			e.printStackTrace();
		}

		return userVO;
	}
	
	/**
	 * 오픈뱅킹 이용약관 동의 O, agreement 업데이트
	 */
	
	public void openBankingAgree(String id) {
		
		
		StringBuilder sql = new StringBuilder();
		sql.append(" update bank_member set ");
		sql.append(" agreement = 'Y' where member_id =? ");
		
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString()); 
				
				) {
			
				pstmt.setString(1, id);
				pstmt.executeUpdate(); 

				/*
				 * if(pstmt.executeUpdate() != 0 ) { bool = true; }
				 */
				
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		 return bool;
	}
	
	/**
	 * userVO session 최신화 
	 * @param id
	 * @return String
	 */
	public String agreeMember(String id) {
		
		String agree = "";
		StringBuilder sql = new StringBuilder();
		sql.append(" select agreement from bank_member where member_id =? ");
 		
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString()); 
				
				) {
			
				pstmt.setString(1, id);
				ResultSet rs = pstmt.executeQuery(); 
				
				if(rs.next()) {
					
					agree = rs.getString("agreement");
					
				}
				
				
				
				/*
				 * if(pstmt.executeUpdate() != 0 ) { bool = true; }
				 */
				
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return agree;
	}
	
	/**
	 * 회원 상세 조회
	 * @param id
	 * @return
	 */
	public memberVO searchOneMember(String id) {
		
		memberVO member = new memberVO();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select member_id, password, name, phone, email, ssn ");
		sql.append(" from bank_member where member_id=? ");
		
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				) {
				
				pstmt.setString(1, id);
				
				ResultSet rs = pstmt.executeQuery();
				
				if(rs.next()) {
					
					member.setPassword(rs.getString("password"));
					member.setSsn(rs.getString("ssn"));
					member.setPhone(rs.getString("phone"));
				}
				
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return member;
	}
	
	
}