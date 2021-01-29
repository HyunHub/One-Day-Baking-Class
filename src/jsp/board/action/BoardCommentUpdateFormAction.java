package jsp.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.board.comment.model.CommentBean;
import jsp.board.comment.model.CommentDAO;
import jsp.common.util.Action;
import jsp.common.util.ActionForward;

public class BoardCommentUpdateFormAction implements Action{
	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ActionForward forward = new ActionForward();
		
		int comment_num = Integer.parseInt(request.getParameter("num"));
		String board_category = request.getParameter("board_category");
		String page = request.getParameter("page");
		
		CommentDAO dao = CommentDAO.getInstance();
		CommentBean comment = dao.getComment(comment_num);
		
		request.setAttribute("comment", comment);
		request.setAttribute("pageNum", page);
		request.setAttribute("board_category", board_category);
		
		forward.setRedirect(false);
		forward.setNextPath("BoardCommentUpdateForm.bo?board_category="+board_category+"&page="+page);
		
		return forward;
	}
}
