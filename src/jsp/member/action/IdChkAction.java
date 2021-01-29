package jsp.member.action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.common.util.Action;
import jsp.common.util.ActionForward;
import jsp.member.model.CustomerBean;
import jsp.member.model.CustomerDAO;

public class IdChkAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = new ActionForward();
		
		String customer_id = request.getParameter("customer_id");
		CustomerDAO cd = CustomerDAO.getInstance();
		CustomerBean customer = cd.select(customer_id);
		String msg = "";
		
		if (customer == null) {
			msg="사용가능한 아이디입니다.";
		}else{
			msg="중복되는 아이디 입니다. 다른 아이디를 입력해주세요.";
		}		
		request.setAttribute("msg", msg);
		
		forward.setRedirect(false);
		forward.setNextPath("idChk.do?msg="+msg+"&customer_id="+customer_id);
		return forward;
	}
}