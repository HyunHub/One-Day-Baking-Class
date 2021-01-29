package jsp.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.board.comment.model.CommentDAO;
import jsp.common.util.Action;
import jsp.common.util.ActionForward;

public class BoardCommentDeleteAction implements Action{
	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ActionForward forward = new ActionForward();
		
		int cmNum = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("page");
		String board_category = request.getParameter("board_category");
		int comment_board = Integer.parseInt(request.getParameter("comment_board"));
		
		request.setAttribute("board_category", board_category);
		request.setAttribute("commend_board", comment_board);
		
		CommentDAO dao = CommentDAO.getInstance();
		
		boolean result = dao.deleteComment(cmNum);
		
		if(result) {
			forward.setRedirect(true);
			forward.setNextPath("BoardDetailAction.bo?page="+pageNum+"&board_category="+board_category+"&num="+comment_board);
		}
		return forward;
		
	}
}
