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

import DAO.SearchDAO;
import DAO.UserDAO;
import VO.SearchVO;
import VO.UserVO;

/**
 * Servlet implementation class MypageController
 */
@WebServlet("/profile")
public class ProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDAO userDAO;
	SearchDAO searchDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileController() {
        super();
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		 userDAO = new UserDAO();
		 searchDAO = new SearchDAO();
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {}

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
		String userId = request.getParameter("userId");
		
		UserVO userVO = new UserVO();
		SearchVO searchVO = new SearchVO();
		
		userVO.setId(userId);
		searchVO.setUserId(userId);
		
		List<UserVO> userInfo = new ArrayList<UserVO>();
		
		List<ArrayList<SearchVO>> AllProfileQuestions =  new ArrayList<ArrayList<SearchVO>>();
		List<SearchVO> myQuestions = new ArrayList<SearchVO>();
		List<SearchVO> answeringQuestions = new ArrayList<SearchVO>();
		List<SearchVO> selectedQuestions = new ArrayList<SearchVO>();
		List<SearchVO> responseQuestions = new ArrayList<SearchVO>();
		
		userInfo = userDAO.findUserInfo(userVO);
		
		AllProfileQuestions = searchDAO.profileSearch(searchVO);
		
		myQuestions.addAll(AllProfileQuestions.get(0));
		answeringQuestions.addAll(AllProfileQuestions.get(1));
		selectedQuestions.addAll(AllProfileQuestions.get(2));
		responseQuestions.addAll(AllProfileQuestions.get(3));
		
		request.setAttribute("userInfo", userInfo);
		request.setAttribute ("myQuestions", myQuestions);
		request.setAttribute("answeringQuestions", answeringQuestions);
		request.setAttribute("selectedQuestions", selectedQuestions);
		request.setAttribute("responseQuestions", responseQuestions);
				
		RequestDispatcher dispatcher = request.getRequestDispatcher("/profile.jsp");
		dispatcher.forward(request, response);
	}

}