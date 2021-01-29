package jsp.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp.common.util.Action;
import jsp.common.util.ActionForward;
import jsp.member.model.CustomerDAO;

public class MemberLoginAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		CustomerDAO dao = CustomerDAO.getInstance();
		int check = dao.loginCheck(id, password);
		
		if (check == 0) // 비밀번호 틀릴경우 -> 다시 로그인 화면으로 이동
		{
			// 로그인 실패시 메시지를 request에 담는다.
			request.setAttribute("fail", "0");
			forward.setRedirect(false);
			forward.setNextPath("LoginForm.do");
			System.out.println("비밀번호가 틀립니다.");
		} else if (check == -1) // 아이디가 없을 경우 -> 다시 로그인 화면으로 이동
		{
			request.setAttribute("fail", "-1");
			forward.setRedirect(false);
			forward.setNextPath("LoginForm.do");
			System.out.println("없는 아이디입니다.");
		} else {
			// 로그인 성공 -> 세션에 아이디를 저장
			session.setAttribute("sessionID", id);
			// 로그인 성공후 메인화면으로 이동
			forward.setRedirect(true);
			forward.setNextPath("MainForm.do");
			System.out.println("로그인 성공.");
		}
		return forward;
	}
}
