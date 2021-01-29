package jsp.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.common.util.Action;
import jsp.common.util.ActionForward;
import jsp.qna.model.QnaBean;
import jsp.qna.model.QnaDAO;

public class BoardQnaUpdateFormAction implements Action{
	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ActionForward forward = new ActionForward();
		
		String pageNum = request.getParameter("page");
		String num = request.getParameter("num");
		int qnaNum = Integer.parseInt(num);
		
		QnaDAO dao = QnaDAO.getInstance();
		QnaBean qna = dao.getDetail(qnaNum);
		
		request.setAttribute("qna", qna);
		request.setAttribute("pageNum", pageNum);
		
		forward.setRedirect(false);
		forward.setNextPath("BoardQnaUpdateForm.bo?pageNum="+pageNum);
		
		return forward;
	}
}
