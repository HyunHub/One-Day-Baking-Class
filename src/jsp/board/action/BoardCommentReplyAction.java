package jsp.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.board.comment.model.CommentBean;
import jsp.board.comment.model.CommentDAO;
import jsp.common.util.Action;
import jsp.common.util.ActionForward;

public class BoardCommentReplyAction implements Action{

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ActionForward forward = new ActionForward();
		
		request.setCharacterEncoding("utf-8");
		
		int comment_num = Integer.parseInt(request.getParameter("comment_num"));
		int comment_board = Integer.parseInt(request.getParameter("comment_board"));
		String comment_id = request.getParameter("comment_id");
		String comment_content = request.getParameter("comment_content");
		
		String page = request.getParameter("pageNum");
		String board_category = request.getParameter("board_category");
		
		request.setAttribute("page", page);
		request.setAttribute("board_category", board_category);
		
		CommentDAO dao = CommentDAO.getInstance();
		CommentBean comment = new CommentBean();
		
		comment.setComment_num(dao.getSeq());
		comment.setComment_board(comment_board);
		comment.setComment_id(comment_id);
		comment.setComment_content(comment_content);
		comment.setComment_parent(comment_num);
		
		System.out.println(comment_num);
		System.out.println(comment_board);
		System.out.println(comment_id);
		System.out.println(comment_content);
		
		boolean result = dao.insertComment(comment);
		
		if(result) {
			forward.setRedirect(true);
			forward.setNextPath("BoardDetailAction.bo?num="+comment_board+"&board_category="+board_category+"&page="+page);
		}
		
		return forward;
	}
}
