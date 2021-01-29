package jsp.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.common.util.Action;
import jsp.common.util.ActionForward;
import jsp.event.model.EventBean;
import jsp.event.model.EventDAO;

public class BoardEventDetailAction implements Action{

public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ActionForward forward = new ActionForward();
		
		//글 번호 받기
		String num = request.getParameter("num");
		int eventNum = Integer.parseInt(num);
		
		EventDAO dao = EventDAO.getInstance();
		EventBean event = dao.getDetail(eventNum);
		
		request.setAttribute("event", event);
		
		forward.setRedirect(false);
		forward.setNextPath("BoardEventDetailForm.bo");
	
		return forward;
	}
}
