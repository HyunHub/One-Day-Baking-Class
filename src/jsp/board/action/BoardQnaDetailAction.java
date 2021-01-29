package jsp.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.common.util.Action;
import jsp.common.util.ActionForward;
import jsp.qna.model.QnaBean;
import jsp.qna.model.QnaDAO;

public class BoardQnaDetailAction implements Action{

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ActionForward forward = new ActionForward();
		
		//글 번호 받기
		String num = request.getParameter("num");
		int qnaNum = Integer.parseInt(num);
		String pageNum = request.getParameter("pageNum");
		
		QnaDAO dao = QnaDAO.getInstance();
		QnaBean qna = dao.getDetail(qnaNum);
		
		request.setAttribute("qna", qna);
		request.setAttribute("pageNum", pageNum);
		
		System.out.println("글 상세보기 : " + pageNum);
		
		forward.setRedirect(false);
		forward.setNextPath("BoardQnaDetailForm.bo");
		
		return forward;
	}
}
