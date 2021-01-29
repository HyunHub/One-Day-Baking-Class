package jsp.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.board.comment.model.CommentBean;
import jsp.board.comment.model.CommentDAO;
import jsp.common.util.Action;
import jsp.common.util.ActionForward;

public class BoardCommentUpdateAction implements Action{

    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
		ActionForward forward = new ActionForward();
		
		request.setCharacterEncoding("utf-8");
		
        int comment_num = Integer.parseInt(request.getParameter("comment_num"));
        String comment_content = request.getParameter("comment_content");
        int comment_board = Integer.parseInt(request.getParameter("comment_board"));
		String page = request.getParameter("pageNum");
		String board_category = request.getParameter("board_category");
        
        CommentDAO dao = CommentDAO.getInstance();
        CommentBean comment = new CommentBean();
        
        comment.setComment_num(comment_num);
        comment.setComment_content(comment_content);
               
		boolean result = dao.updateComment(comment);
		
		if(result) {
			forward.setRedirect(true);
			forward.setNextPath("BoardDetailAction.bo?num="+comment_board+"&board_category="+board_category+"&page="+page);
		}
		
		return forward;
       
    }
}
