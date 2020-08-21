package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import VO.UserVO;

public class UserDAO {
	private PreparedStatement pstmt;
	private Connection con;
	private DataSource dataFactory;
	
	public UserDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/mysql");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean idCheck(UserVO vo) {
		boolean isTrue = false;
		String id = vo.getId();
		try {
			con = dataFactory.getConnection();
			String query = "SELECT EXISTS (SELECT * FROM users WHERE id=?) AS isTrue";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				isTrue = rs.getBoolean("isTrue");
			}
			con.close();
			pstmt.close();
			rs.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return isTrue;
	}
	
	public List<UserVO> loginCheck(UserVO vo){
		List<UserVO> list = new ArrayList<UserVO>();
		String login_id = vo.getId();
		String login_pw = vo.getPw();

		try {
			con = dataFactory.getConnection();
			String query = "SELECT * FROM users WHERE id=? AND pw=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, login_id);
			pstmt.setString(2, login_pw);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String id = rs.getString("id");
				String pw = rs.getString("pw");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String emailForm = rs.getString("emailForm");
				String interests = rs.getString("interests");
				int grade = rs.getInt("grade");
				
				vo.setId(id);
				vo.setPw(pw);
				vo.setName(name);
				vo.setEmail(email);
				vo.setEmailForm(emailForm);
				vo.setInterests(interests);
				vo.setGrade(grade);
				list.add(vo);
			}
			con.close();
			pstmt.close();
			rs.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void addUser(UserVO vo) {
		String id = vo.getId();
		String pw = vo.getPw();
		String name = vo.getName();
		String email = vo.getEmail();
		String emailForm = vo.getEmailForm();
		String interests = vo.getInterests();
		try {
			con = dataFactory.getConnection();
			
			String query = "INSERT INTO users (id, pw, name, email, emailForm, interests, grade, created)";
			query += " VALUES(?, ?, ?, ?, ?, ?, now())";
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			pstmt.setString(5, emailForm);
			pstmt.setString(6, interests);
			pstmt.setInt(7, 10);
			pstmt.executeUpdate();
			
			pstmt.close();
			con.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void updateUser(UserVO vo) {
		String id = vo.getId();
		String pw = vo.getPw();
		String name = vo.getName();
		String email = vo.getEmail();
		String emailForm = vo.getEmailForm();
		String interests = vo.getInterests();
		
		try {
			con = dataFactory.getConnection();
			
			String query = "UPDATE users SET pw=?, name=?, email=?, emailForm=?, interests=? WHERE id=?";
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, pw);
			pstmt.setString(2, name);
			pstmt.setString(3, email);
			pstmt.setString(4, emailForm);
			pstmt.setString(5, interests);
			pstmt.setString(6, id);
			pstmt.executeUpdate();
			
			con.close();
			pstmt.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteUser(UserVO vo) {
		String id = vo.getId();
		
		try {
			con = dataFactory.getConnection();
			//나중에 더 짧게 쓸 수 있도록 해보기 TODO
			String query = "DELETE FROM users WHERE id=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			query = "UPDATE answer, question SET answer.userId = \"(삭제된 이용자)\", question.userId=\"(삭제된 이용자)\" WHERE answer.userId = ? AND question.userId = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, id);
			pstmt.executeUpdate();
			query = "UPDATE question SET select_userId=\"(삭제된 이용자)\" WHERE select_userId = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			
			pstmt.close();
			con.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
