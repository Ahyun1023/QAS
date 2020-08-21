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
				
				QuestionVO questionVO = new QuestionVO(id, userId, category, title, view, created, content, selection, select_userId);
				question.add(questionVO);
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
			
			String query = "UPDATE question SET selection = 1, select_userId=? WHERE id=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, select_userId);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
			
			pstmt.close();
			con.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
