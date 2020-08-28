package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import VO.QuestionVO;

public class QuestionDAO {
	private PreparedStatement pstmt;
	private Connection con;
	private DataSource dataFactory;
	
	public QuestionDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/mysql");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void requestAddQuestion(QuestionVO vo) {
		try {
			con = dataFactory.getConnection();
			String userId = vo.getUserId();
			String request_user = vo.getRequest_user();
			String title = vo.getTitle();
			String category = vo.getCategory();
			String content = vo.getContent();
			
			String query = "INSERT INTO question (userId, category, title, content, view, created, request_user, selection)";
			query += " VALUES(?, ?, ?, ?, 0, NOW(), ?, 0)";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, category);
			pstmt.setString(3, title);
			pstmt.setString(4, content);
			pstmt.setString(5, request_user);
			pstmt.executeUpdate();
			
			pstmt.close();
			con.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addQuestion(QuestionVO vo) {
		try {
			con = dataFactory.getConnection();
			String userId = vo.getUserId();
			String title = vo.getTitle();
			String category = vo.getCategory();
			String content = vo.getContent();
			
			String query = "INSERT INTO question (userId, category, title, content, view, created, selection)";
			query += " VALUES(?, ?, ?, ?, 0, NOW(), 0)";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, category);
			pstmt.setString(3, title);
			pstmt.setString(4, content);
			pstmt.executeUpdate();
			
			pstmt.close();
			con.close();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void updateQuestion(QuestionVO vo) {
		try {
			con = dataFactory.getConnection();
			int id = vo.getId();
			String title = vo.getTitle();
			String content = vo.getContent();
			
			String query = "UPDATE question SET title=?, content=? WHERE id = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, id);
			pstmt.executeUpdate();
			
			pstmt.close();
			con.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<QuestionVO> readQuestion(QuestionVO vo) {
		List<QuestionVO> question = new ArrayList<QuestionVO>();
		try {
			con = dataFactory.getConnection();
			int qid = vo.getId();
			
			String query = "UPDATE question SET view = view + 1 WHERE id = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, qid);
			pstmt.execute();
			
			query = "SELECT * FROM question WHERE id = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, qid);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String userId = rs.getString("userId");
				String category = rs.getString("category");
				String title = rs.getString("title");
				int view = rs.getInt("view");
				Date created = rs.getDate("created");
				String content = rs.getNString("content");
				int selection = rs.getInt("selection");
				String select_userId = rs.getString("select_userId");
				String request_user = rs.getString("request_user");
				
				vo.setId(id);
				vo.setUserId(userId);
				vo.setCategory(category);
				vo.setTitle(title);
				vo.setView(view);
				vo.setCreated(created);
				vo.setContent(content);
				vo.setSelection(selection);
				vo.setSelect_userId(select_userId);
				vo.setRequest_user(request_user);
				question.add(vo);
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return question;
	}
	
	public void deleteQuestion(QuestionVO vo) {
		try {
			con = dataFactory.getConnection();
			int id = vo.getId();
			
			String query = "DELETE FROM question WHERE id = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			pstmt.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void selectAnswer(QuestionVO vo) {
		try {
			con = dataFactory.getConnection();
			int id = vo.getId();
			String select_userId = vo.getSelect_userId();
			String request_user = vo.getRequest_user();
			int point = 0;
			
			String query = "UPDATE question SET selection = 1, select_userId=? WHERE id=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, select_userId);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
			
			if(select_userId.equals(request_user)) {
				query = "UPDATE users SET point = point + 15 WHERE id=?";
			} else {
				query = "UPDATE users SET point = point + 10 WHERE id=?";
			}
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, select_userId);
			pstmt.executeUpdate();
			
			query = "SELECT point FROM users WHERE id=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, select_userId);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				point = rs.getInt("point");
			}
			
			int grade = 10;
			if (point >= 10){
				grade = 9;
			} if(point >= 20) {
				grade = 8;
			} if(point >= 35) {
				grade = 7;
			} if(point >= 55) {
				grade = 6;
			} if(point >= 80) {
				grade = 5;
			} if(point >= 110) {
				grade = 4;
			} if(point >= 155) {
				grade = 3;
			} if(point >= 195) {
				grade = 2;
			} if(point >= 250) {
				grade = 1;
			}
			
			query = "UPDATE users SET grade = ? WHERE id=?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, grade);
			pstmt.setString(2, select_userId);
			pstmt.executeUpdate();
			
			pstmt.close();
			con.close();
			rs.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
