package jsp.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.board.comment.model.CommentBean;
import jsp.board.comment.model.CommentDAO;
import jsp.common.util.Action;
import jsp.common.util.ActionForward;

public class BoardCommentReplyFormAction implements Action{

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ActionForward forward = new ActionForward();
		
		int comment_num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		String board_category = request.getParameter("board_category");
		
		CommentDAO dao = CommentDAO.getInstance();
		CommentBean comment = dao.getComment(comment_num);
		
		request.setAttribute("comment", comment);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("board_category", board_category);
		
		forward.setRedirect(false);
		forward.setNextPath("BoardCommentReplyForm.bo?board_category="+board_category+"&page="+pageNum);
		
		return forward;
	}
}
