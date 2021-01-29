package jsp.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.common.util.Action;
import jsp.common.util.ActionForward;
import jsp.qna.model.QnaDAO;

public class BoardQnaDeleteAction implements Action{
	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ActionForward forward = new ActionForward();
		
		String num = request.getParameter("num");
		int qnaNum = Integer.parseInt(num);
		String pageNum = request.getParameter("page");
		
		QnaDAO dao = QnaDAO.getInstance();
		
		boolean result = dao.deleteQna(qnaNum);
		
		if(result) {
			forward.setRedirect(true);
			forward.setNextPath("BoardQnaListAction.bo?page="+pageNum);
		}
		
		return forward;
	}

}
