package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.SearchDAO;
import VO.SearchVO;

/**
 * Servlet implementation class searchController
 */
@WebServlet("/search/*")
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SearchDAO searchDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		searchDAO = new SearchDAO();
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	@SuppressWarnings("unused")
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String nextPage = null;
		String action = request.getPathInfo();

		if(action.equals("/main.do")) {
			mainQuestion(request, response);
		} else if(action.equals("/find.do")) {
			findQuestion(request, response);
		}
		if(nextPage != null) {
			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);
		}
	}

	private void findQuestion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchCategory = request.getParameter("category");
		String searchWord = request.getParameter("word");

		SearchVO vo = new SearchVO();
		vo.setCategory(searchCategory);
		vo.setSearchWord(searchWord);
		
		List<SearchVO> searchList = searchDAO.wordSearch(vo);
		
		if(searchList.size() != 0) {
			request.setAttribute("searchList", searchList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/search.jsp");
			dispatcher.forward(request, response);
		}else {
			request.setAttribute("searchList", null);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/search.jsp");
			dispatcher.forward(request, response);
		}
		
	}

	private void mainQuestion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String interest = (String) session.getAttribute("sessionInterests");
		
		List<ArrayList<SearchVO>> AllMainQuestions =  new ArrayList<ArrayList<SearchVO>>();
		List<SearchVO> moreViewQuestions = new ArrayList<SearchVO>();
		List<SearchVO> lessViewQuestions = new ArrayList<SearchVO>();
		List<SearchVO> todayQuestions = new ArrayList<SearchVO>();
		List<SearchVO> myInterestQuestions = new ArrayList<SearchVO>();
		
		SearchVO vo = new SearchVO();
		
		vo.setCategory(interest);
		AllMainQuestions = searchDAO.mainSearch(vo);

		moreViewQuestions.addAll(AllMainQuestions.get(0));
		lessViewQuestions.addAll(AllMainQuestions.get(1));
		todayQuestions.addAll(AllMainQuestions.get(2));
		myInterestQuestions.addAll(AllMainQuestions.get(3));

		request.setAttribute("moreViewQuestions", moreViewQuestions);
		request.setAttribute("lessViewQuestions", lessViewQuestions);
		request.setAttribute("todayQuestions", todayQuestions);
		request.setAttribute("myInterestQuestions", myInterestQuestions);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/main.jsp");
		dispatcher.forward(request, response);
	}
}
