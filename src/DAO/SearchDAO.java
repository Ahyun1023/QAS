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

import VO.SearchVO;

public class SearchDAO {
	private PreparedStatement pstmt;
	private Connection con;
	private DataSource dataFactory;
	
	public SearchDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/mysql");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<SearchVO> wordSearch(SearchVO vo){
		List<SearchVO> searchList = new ArrayList<SearchVO>();
		String searchCategory = vo.getCategory();
		String searchWord = vo.getSearchWord();
		String query = "";
		ResultSet rs;
		
		try {
			con = dataFactory.getConnection();
			if(searchCategory == "" || searchCategory==null) {
				query = "SELECT * FROM question WHERE title LIKE ?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, "%" + searchWord + "%");
				rs = pstmt.executeQuery();
			} else {
				query = "SELECT * FROM question WHERE category=? AND title LIKE ?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, searchCategory);
				pstmt.setString(2, "%" + searchWord + "%");
				rs = pstmt.executeQuery();
			}
			while(rs.next()) {
				int id = rs.getInt("id");
				String userId = rs.getString("userId");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int view = rs.getInt("view");
				Date created = rs.getDate("created");
				
				SearchVO searchVO = new SearchVO(id, userId, category, title, content, view, created);
				searchList.add(searchVO);
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return searchList;
	}

	public List<ArrayList<SearchVO>> mainSearch(SearchVO vo) {
		List<ArrayList<SearchVO>> AllMainQuestions =  new ArrayList<ArrayList<SearchVO>>();
		List<SearchVO> moreViewQuestions = new ArrayList<SearchVO>();
		List<SearchVO> lessViewQuestions = new ArrayList<SearchVO>();
		List<SearchVO> todayQuestions = new ArrayList<SearchVO>();
		List<SearchVO> myInterestQuestions = new ArrayList<SearchVO>();
		String interest = vo.getCategory();
		SearchVO searchVO;
		try {
			con = dataFactory.getConnection();
			//moreViewQuestion
			String query = "SELECT * FROM question"; //쿼리 수정 필요
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String userId = rs.getString("userId");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int view = rs.getInt("view");
				Date created = rs.getDate("created");
				
				searchVO = new SearchVO(id, userId, category, title, content, view, created);
				moreViewQuestions.add(searchVO);
			}
			//lessViewQuestion
			query = "SELECT * FROM question"; //쿼리 수정 필요
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String userId = rs.getString("userId");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int view = rs.getInt("view");
				Date created = rs.getDate("created");
				
				searchVO = new SearchVO(id, userId, category, title, content, view, created);
				lessViewQuestions.add(searchVO);
			}
			//todayQuestion
			query = " select * from question where created > CURRENT_DATE( )";
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String userId = rs.getString("userId");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int view = rs.getInt("view");
				Date created = rs.getDate("created");
				
				searchVO = new SearchVO(id, userId, category, title, content, view, created);
				todayQuestions.add(searchVO);
			}
			//myInterestQuestion
			query = "SELECT * FROM question WHERE category = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, interest);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String userId = rs.getString("userId");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int view = rs.getInt("view");
				Date created = rs.getDate("created");
				
				searchVO = new SearchVO(id, userId, category, title, content, view, created);
				myInterestQuestions.add(searchVO);
			}

			AllMainQuestions.add((ArrayList<SearchVO>) moreViewQuestions);
			AllMainQuestions.add((ArrayList<SearchVO>) lessViewQuestions);
			AllMainQuestions.add((ArrayList<SearchVO>) todayQuestions);
			AllMainQuestions.add((ArrayList<SearchVO>) myInterestQuestions);

			rs.close();
			pstmt.close();
			con.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return AllMainQuestions;
	}
}
