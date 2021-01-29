package jsp.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp.common.util.Action;
import jsp.common.util.ActionForward;
import jsp.member.model.CustomerDAO;

public class MemberDeleteAction implements Action {
	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = new ActionForward();

		// 세션이 가지고있는 로그인한 ID 정보를 가져온다
		HttpSession session = request.getSession();
		String customer_id = session.getAttribute("sessionID").toString();
		String customer_pw = request.getParameter("customer_pw");

		CustomerDAO dao = CustomerDAO.getInstance();
		int check = dao.deleteMember(customer_id);

		if (check == 1) {
			session.invalidate(); // 회원정보 담긴 세션 삭제
			forward.setRedirect(true);
			forward.setNextPath("ResultForm.do");
		} else {
			System.out.println("회원 삭제 실패");
			return null;
		}

		return forward;
	}
}
