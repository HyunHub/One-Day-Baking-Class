package jsp.classinfo.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.classinfo.model.BClassBean;
import jsp.classinfo.model.BClassDAO;
import jsp.common.util.Action;
import jsp.common.util.ActionForward;

public class ClassListAction implements Action{

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ActionForward forward = new ActionForward();
		
		//String event_category = request.getParameter("event_category");
		
		BClassDAO dao = BClassDAO.getInstance();
		List<BClassBean> list = dao.selectList();
		
		request.setAttribute("list", list);
		
		System.out.println(list.size());
		
		if(list.size()>0) {
			forward.setRedirect(false);
			forward.setNextPath("classListForm.ao");
		}		
		return forward;
	}

}
