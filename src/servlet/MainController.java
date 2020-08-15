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
 * Servlet implementation class MainController
 */
@WebServlet("/main")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SearchDAO searchDAO;
	
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

	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainController() {
        super();
        // TODO Auto-generated constructor stub
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

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String interest = (String) session.getAttribute("sessionInterests");
		
		List<ArrayList<SearchVO>> AllMainQuestions =  new ArrayList<ArrayList<SearchVO>>();
		List<SearchVO> moreViewQuestions = new ArrayList<SearchVO>();
		List<SearchVO> lessViewQuestions = new ArrayList<SearchVO>();
		List<SearchVO> todayQuestions = new ArrayList<SearchVO>();
		List<SearchVO> myInterestQuestions = new ArrayList<SearchVO>();
		
		SearchVO vo = new SearchVO(interest);
		vo.setCategory(interest);
		
		AllMainQuestions = searchDAO.mainSearch(vo);
		
		moreViewQuestions.addAll(AllMainQuestions.get(0));
		lessViewQuestions.addAll(AllMainQuestions.get(1));
		todayQuestions.addAll(AllMainQuestions.get(2));
		myInterestQuestions.addAll(AllMainQuestions.get(3));
		
		request.setAttribute("moreViewQ", moreViewQuestions);
		request.setAttribute("lessViewQ", lessViewQuestions);
		request.setAttribute("todayQ", todayQuestions);
		request.setAttribute("myInterestQ", myInterestQuestions);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/main.jsp");
		dispatcher.forward(request, response);
	}

}
