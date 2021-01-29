package jsp.board.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.common.util.Action;
import jsp.common.util.ActionForward;
import jsp.qna.model.QnaBean;
import jsp.qna.model.QnaDAO;

public class BoardQnamyAction implements Action{
	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		
		String qna_id = request.getParameter("qna_id");
		
		System.out.println("qna_id : " + qna_id);
		
		QnaDAO dao = QnaDAO.getInstance();
		List<QnaBean> list = dao.selectmylist(qna_id);
		
		
		System.out.println("size = "+ list.size());
		
		request.setAttribute("list", list);
		
		forward.setRedirect(false);
		forward.setNextPath("BoardQnaListForm.bo");
		
		return forward;
	}
}
