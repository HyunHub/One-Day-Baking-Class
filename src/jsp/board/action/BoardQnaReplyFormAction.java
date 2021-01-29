package jsp.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.common.util.Action;
import jsp.common.util.ActionForward;
import jsp.qna.model.QnaBean;
import jsp.qna.model.QnaDAO;

public class BoardQnaReplyFormAction implements Action{

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ActionForward forward = new ActionForward();
		
		QnaDAO dao = QnaDAO.getInstance();
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("page");
		
		QnaBean qna = dao.getDetail(num);
		request.setAttribute("qna", qna);
		request.setAttribute("page", pageNum);
		
		forward.setRedirect(false);
		forward.setNextPath("BoardQnaReplyForm.bo");
		
		return forward;
	}
}
