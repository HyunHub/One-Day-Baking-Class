package jsp.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp.common.util.Action;
import jsp.common.util.ActionForward;
import jsp.member.model.CustomerBean;
import jsp.member.model.CustomerDAO;

public class MemberModifyAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = new ActionForward();

		CustomerDAO dao = CustomerDAO.getInstance();

		// 세션이 가지고있는 로그인한 ID 정보를 가져온다
		HttpSession session = request.getSession();
		String id = session.getAttribute("sessionID").toString();

		// 수정할 정보를 자바빈에 세팅한다.
		CustomerBean customer = new CustomerBean();
		customer.setCustomer_id(id);
		customer.setCustomer_pw(request.getParameter("password"));
		customer.setCustomer_name(request.getParameter("name"));
		customer.setCustomer_email(request.getParameter("email"));
		customer.setCustomer_phone(request.getParameter("phone"));
		customer.setCustomer_address(request.getParameter("address"));

		dao.updateMember(customer);

		forward.setRedirect(true);
		forward.setNextPath("ResultForm.do");

		// 회원정보 수정 성공 메시지를 세션에 담는다.
		session.setAttribute("msg", "0");

		return forward;
	}

}
