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
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import DAO.UserDAO;
import VO.UserVO;

/**
 * Servlet implementation class userController
 */
@WebServlet("/user/*")
public class userController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    UserDAO userDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		userDAO = new UserDAO();
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

	@SuppressWarnings({ "unused" })
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String nextPage = null;
		String action = request.getPathInfo();
		
		if(action.equals("/login.do")) {
			Login(request, response);
		} else if(action.equals("/update.do")) {
			ChangeInfo(request, response);
		} else if(action.equals("/logout.do")) {
			Logout(request, response);
		} else if(action.equals("/add.do")) {
			Signup(request, response);
		} else if(action.equals("/delete.do")) {
			Signout(request, response);
		}
		if(nextPage != null) {
			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);	
		}
	}
	
	private void Signout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = request.getParameter("signout_id");
		String pw = request.getParameter("signout_pw");
		String sessionId = (String) session.getAttribute("sessionId");
		String sessionPw = (String) session.getAttribute("sessionPw");
		
		if(id.equals(sessionId) || pw.equals(sessionPw)) {			
			UserVO vo = new UserVO();
			vo.setId(id);
			vo.setPw(pw);
			userDAO.deleteUser(vo);
			session.invalidate();
		}
	}

	private void Signup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("signup_id");
		String pw = request.getParameter("signup_pw");
		String name = request.getParameter("signup_name");
		String address = request.getParameter("signup_address");
		String email = request.getParameter("signup_email");
		String phone = request.getParameter("signup_phone");
		String interests = request.getParameter("signup_interests");
		
		UserVO vo = new UserVO();
		vo.setId(id);
		vo.setPw(pw);
		vo.setName(name);
		vo.setAddress(address);
		vo.setEmail(email);
		vo.setPhone(phone);
		vo.setInterests(interests);
		userDAO.addUser(vo);
	}

	private void Logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		
	}

	private void ChangeInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("sessionId");
		String pw = request.getParameter("Cpw");
		String name = request.getParameter("Cname");
		String address = request.getParameter("Caddress");
		String email = request.getParameter("Cemail");
		String phone = request.getParameter("Cphone");
		String interests = request.getParameter("Cinterests");
		
		UserVO vo = new UserVO();
		vo.setId(id);
		vo.setPw(pw);
		vo.setName(name);
		vo.setAddress(address);
		vo.setEmail(email);
		vo.setPhone(phone);
		vo.setInterests(interests);
		userDAO.updateUser(vo);
		
		session.setAttribute("sessionPw", pw);
		session.setAttribute("sessionName", name);
		session.setAttribute("sessionAddress", address);
		session.setAttribute("sessionEmail", email);
		session.setAttribute("sessionPhone", phone);
		session.setAttribute("sessionInterests", interests);
	}

	@SuppressWarnings({ "unchecked" })
	private void Login (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login_id = request.getParameter("id");
		String login_pw = request.getParameter("pw");
		
		UserVO vo = new UserVO();
		vo.setId(login_id);
		vo.setPw(login_pw);
		
		List<UserVO> result = userDAO.loginCheck(vo);

		if(result.size() != 0) {
			vo = (UserVO) result.get(0);
			String id = vo.getId();
			String pw = vo.getPw();
			String name = vo.getName();
			String address = vo.getAddress();
			String email = vo.getAddress();
			String phone = vo.getPhone();
			String interests = vo.getInterests();
			int grade = vo.getGrade();
			
			HttpSession session = request.getSession();
			session.setAttribute("isLogin", true);
			session.setAttribute("sessionId", id);
			session.setAttribute("sessionPw", pw);
			session.setAttribute("sessionName", name);
			session.setAttribute("sessionAddress", address);
			session.setAttribute("sessionEmail", email);
			session.setAttribute("sessionPhone", phone);
			session.setAttribute("sessionInterests", interests);
			session.setAttribute("sessionGrade", grade);
			
			JSONObject obj = new JSONObject();
			obj.put("result", "success");
			response.setContentType("application/x-json; charset=UTF-8");
			response.getWriter().print(obj);
		} else {
			JSONObject obj = new JSONObject();
			obj.put("result", "fail");
			response.setContentType("application/x-json; charset=UTF-8");
			response.getWriter().print(obj);
		}
	}
}
