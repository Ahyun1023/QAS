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
 * Servlet implementation class MypageController
 */
@WebServlet("/mypage")
public class MypageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SearchDAO searchDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MypageController() {
        super();
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
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("sessionId");
		
		SearchVO vo = new SearchVO(userId);
		vo.setUserId(userId);
		
		List<ArrayList<SearchVO>> AllMypageQuestions =  new ArrayList<ArrayList<SearchVO>>();
		List<SearchVO> myQuestions = new ArrayList<SearchVO>();
		List<SearchVO> answeringQuestions = new ArrayList<SearchVO>();
		List<SearchVO> answerings = new ArrayList<SearchVO>();
		List<SearchVO> selectedQuestions = new ArrayList<SearchVO>();
		
		AllMypageQuestions = searchDAO.mypageSearch(vo);
		
		myQuestions.addAll(AllMypageQuestions.get(0));
		answeringQuestions.addAll(AllMypageQuestions.get(1));
		answerings.addAll(AllMypageQuestions.get(2));
		selectedQuestions.addAll(AllMypageQuestions.get(3));
		
		request.setAttribute ("myQuestions", myQuestions);
		request.setAttribute("answeringQuestions", answeringQuestions);
		request.setAttribute("answerings", answerings);
		request.setAttribute("selectedQuestions", selectedQuestions);
				
		RequestDispatcher dispatcher = request.getRequestDispatcher("/mypage.jsp");
		dispatcher.forward(request, response);
	}

}
