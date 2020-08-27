package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.SearchDAO;
import VO.SearchVO;

/**
 * Servlet implementation class MyinfoController
 */
@WebServlet("/userinfo/*")
public class UserInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    SearchDAO searchDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserInfoController() {
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
	public void destroy() {
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
		request.setCharacterEncoding("utf-8");
		String action = request.getPathInfo();

		findInfoList(request, response, action);
	}

	private void findInfoList(HttpServletRequest request, HttpServletResponse response, String action) throws ServletException, IOException {
		String userId= request.getParameter("userId");
		
		SearchVO vo = new SearchVO();
		vo.setUserId(userId);
		
		List<SearchVO> infoList = searchDAO.findInfoList(vo, action);
		
		request.setAttribute("infoList", infoList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/userInfoList.jsp");
		dispatcher.forward(request, response);
	}

}
