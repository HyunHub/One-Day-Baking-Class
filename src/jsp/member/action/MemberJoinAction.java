package jsp.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.common.util.Action;
import jsp.common.util.ActionForward;
import jsp.member.model.CustomerBean;
import jsp.member.model.CustomerDAO;

public class MemberJoinAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ActionForward forward = new ActionForward();
		
		CustomerDAO dao = CustomerDAO.getInstance();
		
		CustomerBean customer = new CustomerBean();
		customer.setCustomer_id(request.getParameter("id"));
		customer.setCustomer_pw(request.getParameter("password"));
		customer.setCustomer_name(request.getParameter("name"));
		customer.setCustomer_email(request.getParameter("email"));
		customer.setCustomer_phone(request.getParameter("phone"));
		customer.setCustomer_address(request.getParameter("address"));

        // 회원가입 실행
        dao.insertMember(customer);
        
        // 가입성공
        forward.setRedirect(true);
        forward.setNextPath("ResultForm.do");
        
		// 가입성공 메시지를 세션에 담는다.
		request.getSession().setAttribute("msg", "1");

		return forward;
		
	}
}
