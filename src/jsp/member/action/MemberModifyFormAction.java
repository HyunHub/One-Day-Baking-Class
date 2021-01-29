package jsp.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp.common.util.Action;
import jsp.common.util.ActionForward;
import jsp.member.model.CustomerBean;
import jsp.member.model.CustomerDAO;

public class MemberModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = new ActionForward();

		// 세션이 가지고있는 로그인한 ID 정보를 가져온다
		HttpSession session = request.getSession();
		String id = session.getAttribute("sessionID").toString();

		// 수정할 회원정보를 가져온다.
		CustomerDAO dao = CustomerDAO.getInstance();
		CustomerBean member = dao.getUserInfo(id);

		// ModifyFrom.jsp에 회원정보를 전달하기 위해 request에 MemberBean을 세팅한다.
		request.setAttribute("memberInfo", member);

		forward.setRedirect(false);
		forward.setNextPath("ModifyForm.do");

		return forward;
	}
}
