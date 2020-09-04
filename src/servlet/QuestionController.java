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

import DAO.AnswerDAO;
import DAO.QuestionDAO;
import VO.AnswerVO;
import VO.QuestionVO;

/**
 * Servlet implementation class QuestionController
 */
@WebServlet("/question/*")
public class QuestionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	QuestionDAO questionDAO;
	AnswerDAO answerDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionController() {
        super();
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		questionDAO = new QuestionDAO();
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
			AddQuestion(request, response);
		} else if(action.equals("/update.do")) {
			UpdateQuestion(request, response);
		} else if(action.equals("/read.do")) {
			ReadQuestion(request, response);
		} else if(action.equals("/delete.do")) {
			DeleteQuestion(request, response);
		} else if(action.equals("/select.do")) {
			SelectAnswer(request, response);
		} else if(action.equals("/request.do")){
			RequestQuestion(request, response);
		}
		if(nextPage != null) {
			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);
		}
	}

	private void RequestQuestion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String userId = (String)session.getAttribute("sessionId");
		String request_user = request.getParameter("requestId");
		String title = request.getParameter("save_title");
		String category = request.getParameter("save_category");
		String content = request.getParameter("save_content");
		
		QuestionVO vo = new QuestionVO(userId, request_user, title, category, content);
		vo.setUserId(userId);
		vo.setRequest_user(request_user);
		vo.setTitle(title);
		vo.setCategory(category);
		vo.setContent(content);
		
		questionDAO.requestAddQuestion(vo);
	}

	private void SelectAnswer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String select_userId = request.getParameter("selectAuserId");
		int id = Integer.parseInt(request.getParameter("qId"));
		String request_user = request.getParameter("request_user");
		
		QuestionVO vo = new QuestionVO(id);
		vo.setId(id);
		vo.setSelect_userId(select_userId);
		vo.setRequest_user(request_user);
		questionDAO.selectAnswer(vo);
	}

	private void DeleteQuestion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("deleteQid"));
		QuestionVO vo = new QuestionVO(id);
		vo.setId(id);
		questionDAO.deleteQuestion(vo);
	}

	private void ReadQuestion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int id = Integer.parseInt(request.getParameter("qid"));
		String sessionUserId = (String)session.getAttribute("sessionId");
		
		QuestionVO Qvo = new QuestionVO(id);
		AnswerVO Avo = new AnswerVO(id);
		Qvo.setId(id);
		Qvo.setUserId(sessionUserId);
		Avo.setId(id);
		List<QuestionVO> question = questionDAO.readQuestion(Qvo);
		List<AnswerVO> answer = answerDAO.readAnswer(Avo);
		
		request.setAttribute("question", question);
		request.setAttribute("answer", answer);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/question.jsp");
		dispatcher.forward(request, response);
	}

	private void UpdateQuestion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("updateQuestionId"));
		String title = request.getParameter("updateTitle");
		String content = request.getParameter("updateContent");

		QuestionVO vo = new QuestionVO(id, title, content);
		vo.setId(id);
		vo.setTitle(title);
		vo.setContent(content);
		questionDAO.updateQuestion(vo);
	}

	private void AddQuestion (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String userId = (String)session.getAttribute("sessionId");
		String title = request.getParameter("save_title");
		String category = request.getParameter("save_category");
		String content = request.getParameter("save_content");
		
		QuestionVO vo = new QuestionVO(userId, title, category, content);
		vo.setUserId(userId);
		vo.setTitle(title);
		vo.setCategory(category);
		vo.setContent(content);
		questionDAO.addQuestion(vo);
	}
}
