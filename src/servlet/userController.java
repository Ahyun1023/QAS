package servlet;

import java.io.IOException;
import java.security.MessageDigest;
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
		
		if(action.equals("/idCheck.do")) {
			idCheck(request, response);
		} else if(action.equals("/login.do")) {
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
	
	@SuppressWarnings("unchecked")
	private void idCheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("checkId");
		boolean isTrue = false;
		UserVO vo = new UserVO();
		vo.setId(id);
		isTrue = userDAO.idCheck(vo);
		
		request.setAttribute("isTrue", isTrue);
		
		JSONObject obj = new JSONObject();
		obj.put("isTrue", isTrue);
		response.setContentType("application/x-json; charset=UTF-8");
		response.getWriter().print(obj);
		
	}

	@SuppressWarnings("unchecked")
	private void Signout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			String id = request.getParameter("signout_id");
			String pw = sha256(request.getParameter("signout_pw"));
			String sessionId = (String) session.getAttribute("sessionId");
			String sessionPw = (String) session.getAttribute("sessionPw");
			
			if(id.equals(sessionId) && pw.equals(sessionPw)) {
				UserVO vo = new UserVO();
				vo.setId(id);
				userDAO.deleteUser(vo);
				
				session.removeAttribute("isLogin");
				session.removeAttribute("sessionId");
				session.removeAttribute("sessionPw");
				session.removeAttribute("sessionName");
				session.removeAttribute("sessionEmail");
				session.removeAttribute("sessionEmailForm");
				session.removeAttribute("sessionInterests");
				session.removeAttribute("sessionGrade");
				session.removeAttribute("sessionIntroduce");
				//session.invalidate();
			} else {
				JSONObject obj = new JSONObject();
				obj.put("result", "fail");
				response.setContentType("application/x-json; charset=UTF-8");
				response.getWriter().print(obj);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	private void Signup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String id = request.getParameter("signup_id");
			String pw = sha256(request.getParameter("signup_pw"));
			String name = request.getParameter("signup_name");
			String email = request.getParameter("signup_email");
			String emailForm = request.getParameter("signup_emailForm");
			String interests = request.getParameter("signup_interests");
			String introduce = request.getParameter("signup_introduce");
			
			UserVO vo = new UserVO();
			vo.setId(id);
			vo.setPw(pw);
			vo.setName(name);
			vo.setEmail(email);
			vo.setEmailForm(emailForm);
			vo.setInterests(interests);
			vo.setIntroduce(introduce);
			userDAO.addUser(vo);	
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void Logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("isLogin");
		session.removeAttribute("sessionId");
		session.removeAttribute("sessionPw");
		session.removeAttribute("sessionName");
		session.removeAttribute("sessionEmail");
		session.removeAttribute("sessionEmailForm");
		session.removeAttribute("sessionInterests");
		session.removeAttribute("sessionGrade");
		session.removeAttribute("sessionIntroduce");
		
		//session.invalidate();
		
	}

	private void ChangeInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			String id = (String)session.getAttribute("sessionId");
			String pw = sha256(request.getParameter("Cpw"));
			String name = request.getParameter("Cname");
			String email = request.getParameter("Cemail");
			String emailForm = request.getParameter("CemailForm");
			String interests = request.getParameter("Cinterests");
			String introduce = request.getParameter("Cintroduce");
			
			UserVO vo = new UserVO();
			vo.setId(id);
			vo.setPw(pw);
			vo.setName(name);
			vo.setEmail(email);
			vo.setEmailForm(emailForm);
			vo.setInterests(interests);
			vo.setIntroduce(introduce);
			userDAO.updateUser(vo);
			
			session.setAttribute("sessionPw", pw);
			session.setAttribute("sessionName", name);
			session.setAttribute("sessionEmail", email);
			session.setAttribute("sessionEmailForm", emailForm);
			session.setAttribute("sessionInterests", interests);
			session.setAttribute("sessionIntroduce", introduce);	
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "unchecked" })
	private void Login (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String login_id = request.getParameter("id");
			String login_pw = sha256(request.getParameter("pw"));
			
			UserVO vo = new UserVO();
			vo.setId(login_id);
			vo.setPw(login_pw);
			
			List<UserVO> result = userDAO.loginCheck(vo);
			
			if(result.size() != 0) {
				vo = (UserVO) result.get(0);
				String id = vo.getId();
				String pw = vo.getPw();
				String name = vo.getName();
				String email = vo.getEmail();
				String emailForm = vo.getEmailForm();
				String interests = vo.getInterests();
				int grade = vo.getGrade();
				String introduce = vo.getIntroduce();
				
				HttpSession session = request.getSession();
				session.setAttribute("isLogin", true);
				session.setAttribute("sessionId", id);
				session.setAttribute("sessionPw", pw);
				session.setAttribute("sessionName", name);
				session.setAttribute("sessionEmail", email);
				session.setAttribute("sessionEmailForm", emailForm);
				session.setAttribute("sessionInterests", interests);
				session.setAttribute("sessionGrade", grade);
				session.setAttribute("sessionIntroduce", introduce);
			
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
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private String sha256(String msg) throws Exception {
	    MessageDigest md = MessageDigest.getInstance("SHA-256");
	    md.update(msg.getBytes());
	    StringBuilder builder = new StringBuilder();
	    for (byte b: md.digest()) {
		      builder.append(String.format("%02x", b));
		    }
	    return builder.toString();
	}
}
