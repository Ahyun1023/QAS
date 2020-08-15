package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.AnswerDAO;
import VO.AnswerVO;

/**
 * Servlet implementation class AnswerController
 */
@WebServlet("/answer/*")
public class AnswerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AnswerDAO answerDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnswerController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		answerDAO = new AnswerDAO();
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
		
		if(action.equals("/add.do")) {
			addAnswer(request, response);
		} else if(action.equals("/update.do")) {
			updateAnswer(request, response);
		} else if(action.equals("/delete.do")) {
			deleteAnswer(request, response);
		}
		if(nextPage != null) {
			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);
		}
	}

	private void deleteAnswer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int Aid = Integer.parseInt(request.getParameter("deleteAid"));
		
		AnswerVO vo = new AnswerVO(Aid);
		vo.setId(Aid);
		answerDAO.deleteAnswer(vo);
		
	}

	private void addAnswer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		int qId = Integer.parseInt(request.getParameter("answerQid"));
		String userId = (String)session.getAttribute("sessionId");
		String title = request.getParameter("answerTitle");
		String content = request.getParameter("answerContent");
		
		AnswerVO vo = new AnswerVO(qId, userId, title, content);
		vo.setqId(qId);
		vo.setUserId(userId);
		vo.setTitle(title);
		vo.setContent(content);
		answerDAO.addAnswer(vo);
	}
	
	private void updateAnswer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("updateAnswerId"));
		String title = request.getParameter("updateTitle");
		String content = request.getParameter("updateContent");
		
		AnswerVO vo = new AnswerVO(id, title, content);
		vo.setId(id);
		vo.setTitle(title);
		vo.setContent(content);
		answerDAO.updateAnswer(vo);
		
	}

}
