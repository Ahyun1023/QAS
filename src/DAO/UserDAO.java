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
				String address = rs.getString("address");
				String email = rs.getString("email");
				String phone = rs.getString("phone");
				String interests = rs.getString("interests");
				int grade = rs.getInt("grade");
				
				vo.setId(id);
				vo.setPw(pw);
				vo.setName(name);
				vo.setAddress(address);
				vo.setEmail(email);
				vo.setPhone(phone);
				vo.setInterests(interests);
				vo.setGrade(grade);
				list.add(vo);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void addUser(UserVO vo) {
		String interests = vo.getInterests();
		System.out.println(interests);
		try {
			con = dataFactory.getConnection();
			String id = vo.getId();
			String pw = vo.getPw();
			String name = vo.getName();
			String address = vo.getAddress();
			String email = vo.getEmail();
			String phone = vo.getPhone();
			//String interests = vo.getInterests();
			
			String query = "INSERT INTO users (id, pw, name, address, email, phone, interests, grade, created)";
			query += " VALUES(?, ?, ?, ?, ?, ?, ?, ?, now())";
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.setString(3, name);
			pstmt.setString(4, address);
			pstmt.setString(5, email);
			pstmt.setString(6, phone);
			pstmt.setNString(7, interests);
			pstmt.setInt(8, 10);
			pstmt.executeUpdate();
			pstmt.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void updateUser(UserVO vo) {
		try {
			con = dataFactory.getConnection();
			
			String id = vo.getId();
			String pw = vo.getPw();
			String name = vo.getName();
			String address = vo.getAddress();
			String email = vo.getEmail();
			String phone = vo.getPhone();
			String interests = vo.getInterests();
			
			String query = "UPDATE users SET pw=?, name=?, address=?, email=?, phone=?, interests=? WHERE id=?";
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, pw);
			pstmt.setString(2, name);
			pstmt.setString(3, address);
			pstmt.setString(4, email);
			pstmt.setString(5, phone);
			pstmt.setString(6, interests);
			pstmt.setString(7, id);
			pstmt.executeUpdate();
			pstmt.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteUser(UserVO vo) {
		try {
			con = dataFactory.getConnection();
			String id = vo.getId();
			String pw = vo.getPw();
			
			String query = "DELETE FROM users where id = ? AND pw = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.executeUpdate();
			pstmt.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
