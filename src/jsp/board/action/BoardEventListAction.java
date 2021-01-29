package jsp.board.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.common.util.Action;
import jsp.common.util.ActionForward;
import jsp.event.model.EventBean;
import jsp.event.model.EventDAO;

public class BoardEventListAction implements Action{

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ActionForward forward = new ActionForward();
		
		String event_category = request.getParameter("event_category");
		
		EventDAO dao = EventDAO.getInstance();
		List<EventBean> list = dao.selectList();
		
		request.setAttribute("list", list);
		request.setAttribute("event_category", event_category);
		
		System.out.println("event_category : " + event_category);
		
		if(event_category.equals("1")) {
			forward.setRedirect(false);
			forward.setNextPath("BoardEventForm.bo");
		}else if(event_category.equals("2")) {
			forward.setRedirect(false);
			forward.setNextPath("BoardEventListForm.bo");
		}
		
		return forward;
	}
}
