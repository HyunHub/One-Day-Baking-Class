package jsp.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.common.util.Action;
import jsp.common.util.ActionForward;
import jsp.event.model.EventBean;
import jsp.event.model.EventDAO;

public class BoardEventUpdateFormAction implements Action{
	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ActionForward forward = new ActionForward();
		
		String num = request.getParameter("num");
		String event_category = request.getParameter("event_category");
		
		int eventNum = Integer.parseInt(num);
		
		System.out.println("수정 event_num : " + eventNum);
		System.out.println("수정 event_category : " + event_category);
		
		EventDAO dao = EventDAO.getInstance();
		EventBean event = dao.getDetail(eventNum);
		
		request.setAttribute("event", event);
		
		forward.setRedirect(false);
		forward.setNextPath("BoardEventUpdateForm.bo");
		
		return forward;
	}

}
