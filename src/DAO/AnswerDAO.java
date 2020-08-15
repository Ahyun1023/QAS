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

import VO.AnswerVO;

public class AnswerDAO {
	private PreparedStatement pstmt;
	private Connection con;
	private DataSource dataFactory;
	
	public AnswerDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/mysql");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<AnswerVO> readAnswer(AnswerVO vo){
		List<AnswerVO> answer = new ArrayList<AnswerVO>();
		try {
			con = dataFactory.getConnection();
			int qid = vo.getId();
			
			String query = "SELECT * FROM answer WHERE qId = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, qid);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String userId = rs.getString("userId");
				String title = rs.getString("title");
				Date created = rs.getDate("created");
				String content = rs.getNString("content");
				
				AnswerVO answerVO = new AnswerVO(id, userId, title, content, created);
				answer.add(answerVO);
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return answer;
	}

	public void addAnswer(AnswerVO vo) {
		try {
			con = dataFactory.getConnection();
			int qId = vo.getqId();
			String userId = vo.getUserId();
			String title = vo.getTitle();
			String content = vo.getContent();
			
			String query = "INSERT INTO answer (qId, userId, title, content, created)";
			query += " VALUES (?, ?, ?, ?, NOW())";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, qId);
			pstmt.setString(2, userId);
			pstmt.setString(3, title);
			pstmt.setString(4, content);
			pstmt.executeUpdate();
			pstmt.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteAnswer(AnswerVO vo) {
		try {
			con = dataFactory.getConnection();
			int aId = vo.getId();
			
			String query = "DELETE FROM answer WHERE id = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, aId);
			pstmt.executeUpdate();
			pstmt.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateAnswer(AnswerVO vo) {
		try {
			con = dataFactory.getConnection();
			int id = vo.getId();
			String title = vo.getTitle();
			String content = vo.getContent();
			
			String query = "UPDATE answer SET title=?, content=? WHERE id=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, id);
			pstmt.executeUpdate();
			pstmt.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
