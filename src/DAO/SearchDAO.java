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
	
	public List<SearchVO> findInfoList(SearchVO vo, String action){
		List<SearchVO> infoList = new ArrayList<SearchVO>();
		String SearchuserId = vo.getUserId();
		String query = "";
		
		if(action.equals("/questionList.do")) {
			query = "SELECT * FROM question WHERE userId = ?";
		} else if(action.equals("/answerList.do")) {
			query = "SELECT * FROM question WHERE id IN (SELECT qId FROM answer WHERE userId = ?)";
		} else if(action.equals("/selectedList.do")) {
			query = "SELECT * FROM question WHERE id IN (SELECT qId FROM answer WHERE userId = ?) AND select_userId = ?";
		}else if(action.equals("/responseList.do")) {
			query = "SELECT * FROM question WHERE request_user=?";
		}
		
		try {
			con = dataFactory.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, SearchuserId);
			if(action.equals("/selectedList.do")) {
				pstmt.setString(2, SearchuserId);
			}
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String userId = rs.getString("userId");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int view = rs.getInt("view");
				Date created = rs.getDate("created");
				
				SearchVO searchVO = new SearchVO(id, userId, category, title, content, view, created);
				infoList.add(searchVO);
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return infoList;
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
		try {
			con = dataFactory.getConnection();
			//moreViewQuestion
			String query = "SELECT * FROM question WHERE userId NOT IN(\"(삭제된 이용자)\") AND selection = 0 ORDER BY view DESC LIMIT 3";
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
				
				vo = new SearchVO(id, userId, category, title, content, view, created);
				moreViewQuestions.add(vo);
			}
			//lessViewQuestion
			query = "SELECT * FROM question WHERE userId NOT IN(\"(삭제된 이용자)\") AND selection = 0 ORDER BY view ASC LIMIT 3;";
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
				
				vo = new SearchVO(id, userId, category, title, content, view, created);
				lessViewQuestions.add(vo);
			}
			//todayQuestion
			query = "SELECT * FROM question WHERE created > CURRENT_DATE() AND userId NOT IN(\"(삭제된 이용자)\") AND selection = 0 ORDER BY RAND() LIMIT 3";
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
				
				vo = new SearchVO(id, userId, category, title, content, view, created);
				todayQuestions.add(vo);
			}
			//myInterestQuestion
			query = "SELECT * FROM question WHERE category = ? AND userId NOT IN(\"(삭제된 이용자)\") AND selection = 0 ORDER BY RAND() LIMIT 3";
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
				
				vo = new SearchVO(id, userId, category, title, content, view, created);
				myInterestQuestions.add(vo);
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
	
	public List<ArrayList<SearchVO>> profileSearch(SearchVO vo) {
		List<ArrayList<SearchVO>> AllMypageQuestions =  new ArrayList<ArrayList<SearchVO>>();
		List<SearchVO> myQuestions = new ArrayList<SearchVO>();
		List<SearchVO> answeringQuestions = new ArrayList<SearchVO>();
		List<SearchVO> selectedQuestions = new ArrayList<SearchVO>();
		List<SearchVO> responseQuestions = new ArrayList<SearchVO>();
		String searchUserId = vo.getUserId();
		try {
			con = dataFactory.getConnection();
			String query = "SELECT * FROM question WHERE userId = ? LIMIT 5";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, searchUserId);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String userId = rs.getString("userId");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int view = rs.getInt("view");
				Date created = rs.getDate("created");
				
				vo = new SearchVO(id, userId, category, title, content, view, created);
				myQuestions.add(vo);
			}
			//answeringQuestions
			query = "SELECT * FROM question WHERE id IN (SELECT qId FROM answer WHERE userId = ?) LIMIT 5";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, searchUserId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String userId = rs.getString("userId");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int view = rs.getInt("view");
				Date created = rs.getDate("created");
				
				vo = new SearchVO(id, userId, category, title, content, view, created);
				answeringQuestions.add(vo);
			}
			//selectedQuestions
			query = "SELECT * FROM question WHERE select_userId=? LIMIT 5";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, searchUserId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String userId = rs.getString("userId");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int view = rs.getInt("view");
				Date created = rs.getDate("created");
			
				vo = new SearchVO(id, userId, category, title, content, view, created);
				selectedQuestions.add(vo);
			}
			//responseQuestions
			query = "SELECT * FROM question WHERE request_user=? AND userId NOT IN(\"(삭제된 이용자)\") ORDER BY RAND() LIMIT 3";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, searchUserId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String userId = rs.getString("userId");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int view = rs.getInt("view");
				Date created = rs.getDate("created");
				
				vo = new SearchVO(id, userId, category, title, content, view, created);
				responseQuestions.add(vo);
			}
			
			AllMypageQuestions.add((ArrayList<SearchVO>)myQuestions);
			AllMypageQuestions.add((ArrayList<SearchVO>)answeringQuestions);
			AllMypageQuestions.add((ArrayList<SearchVO>)selectedQuestions);
			AllMypageQuestions.add((ArrayList<SearchVO>)responseQuestions);
			
			rs.close();
			pstmt.close();
			con.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return AllMypageQuestions;
	}
}
