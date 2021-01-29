package jsp.member.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.common.util.Action;
import jsp.common.util.ActionForward;
import jsp.member.model.CustomerBean;
import jsp.member.model.CustomerDAO;

public class MemberListAction implements Action{

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        ActionForward forward = new ActionForward();
        
        // 회원정보를 가져온다.
        CustomerDAO dao = CustomerDAO.getInstance();
        ArrayList<CustomerBean> memberList = dao.getMemberList();
        
        // MemberListForm.jsp에 회원정보를 전달하기 위해 request에 MemberBean을 세팅한다.
        request.setAttribute("memberList", memberList);
 
        // request를 유지해야 하므로 setRedirect(false)로 지정한다.
        forward.setRedirect(false);
        forward.setNextPath("MemberListForm.do");
        
        return forward;
    }
}
