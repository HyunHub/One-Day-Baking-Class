package jsp.member.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp.Book.model.BookBean;
import jsp.Book.model.BookDAO;
import jsp.common.util.Action;
import jsp.common.util.ActionForward;
import jsp.member.model.CustomerBean;
import jsp.member.model.CustomerDAO;

public class MemberInfoAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = new ActionForward();

		// 세션이 가지고있는 로그인한 ID 정보를 가져온다
		HttpSession session = request.getSession();
		String customer_id = session.getAttribute("sessionID").toString();

		// 그 아이디 해당하는 회원정보를 가져온다.
		CustomerDAO dao = CustomerDAO.getInstance();
		CustomerBean customer = dao.getUserInfo(customer_id);
		
		BookDAO bdao = BookDAO.getInstance();
		List<BookBean> list = bdao.selectmylist(customer_id);
		
		request.setAttribute("list", list);		
		
		System.out.println("size = "+ list.size());
		System.out.println("customer : " + customer);
		
		request.setAttribute("list", list);
		request.setAttribute("memberInfo", customer);

		forward.setRedirect(false);
		forward.setNextPath("UserInfoForm.do");

		return forward;
	}
}
