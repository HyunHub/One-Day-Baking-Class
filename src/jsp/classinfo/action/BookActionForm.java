package jsp.classinfo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.classinfo.model.BClassBean;
import jsp.classinfo.model.BClassDAO;
import jsp.common.util.Action;
import jsp.common.util.ActionForward;

public class BookActionForm implements Action{

    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
		ActionForward forward = new ActionForward();
		
		String cname = request.getParameter("cname");
		
		BClassDAO dao = BClassDAO.getInstance();
		BClassBean bclass = dao.getDetail(cname);
		
		request.setAttribute("bclass", bclass);
		request.setAttribute("cname", cname);
		
		System.out.println(cname);
				
		forward.setRedirect(false);
		forward.setNextPath("classBookForm.ao?cname="+cname);
		
		return forward;
    }
}
