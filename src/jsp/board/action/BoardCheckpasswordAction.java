package jsp.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.common.util.Action;
import jsp.common.util.ActionForward;

public class BoardCheckpasswordAction implements Action{

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ActionForward forward = new ActionForward();
		
		String num = request.getParameter("num");
		int qnaNum = Integer.parseInt(num);
		String pageNum = request.getParameter("pageNum");
		
		forward.setRedirect(false);
		forward.setNextPath("BoardQnaDetailAction.bo?num="+qnaNum+"&pageNum="+pageNum);
		
		return forward;
	}
}
