package jsp.bakerymaster.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.common.util.Action;
import jsp.common.util.ActionForward;

public class BakeryFormChangeAction implements Action{

	private String form = "ShopMainForm.jsp?contentPage=master/";
	private String path;
	
	
	public void setCommand(String command){
		int idx = command.indexOf(".");
		path = command.substring(0, idx)+".jsp";
	}

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		
		//메인화면일 경우 MainForm.jsp만 경로로 지정한다.
		if(path.equals("ShopMainForm.jsp"))
			forward.setNextPath(path);
		else
			forward.setNextPath(form+path);
		
		return forward;
	}
}
