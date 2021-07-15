package kr.ac.jb.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.ac.jb.util.ConnectionFactory;

public class memberDAO {
	
	//회원가입
	public void register(memberVO member) {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" insert into bank_member (MEMBER_ID,PASSWORD,NAME, PHONE, EMAIL) ");
		sql.append(" VALUES(?,?,?,?,?) ");
		
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
				
				pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
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
	
}