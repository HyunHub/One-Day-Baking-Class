package jsp.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.board.comment.model.CommentBean;
import jsp.board.comment.model.CommentDAO;
import jsp.common.util.Action;
import jsp.common.util.ActionForward;

public class BoardCommentWriteAction implements Action{
		
		public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
			
			ActionForward forward = new ActionForward();
			
			request.setCharacterEncoding("utf-8");
			
			try {
				
				CommentDAO dao = CommentDAO.getInstance();
				CommentBean comment = new CommentBean();
				
				int comment_board = Integer.parseInt(request.getParameter("comment_board"));
				String comment_id = request.getParameter("comment_id");
				String comment_content = request.getParameter("comment_content");
				
				String pageNum = request.getParameter("pageNum");
				String board_category = request.getParameter("board_category");
				
				request.setAttribute("pageNum", pageNum);
				request.setAttribute("board_category", board_category);
				
				comment.setComment_num(dao.getSeq());
				comment.setComment_board(comment_board);
				comment.setComment_id(comment_id);
				comment.setComment_content(comment_content);
				
				System.out.println("comment_id : " + comment_id);
				System.out.println("comment_board : " + comment_board);
				System.out.println("comment_content : " + comment_content);
				System.out.println("pageNum : " + pageNum);
				
				boolean result = dao.insertComment(comment);
				
				if(result) {
					forward.setRedirect(true);
					forward.setNextPath("BoardDetailAction.bo?num="+comment_board+"&board_category="+board_category+"&page="+pageNum);
					/*
					 * response.setContentType("text/html;charset=utf-8"); PrintWriter out =
					 * response.getWriter(); out.println("1"); out.close();
					 */
				}
			}catch(Exception e) {
				System.out.println("글 작성 오류 : " + e.getMessage());
			}
			return forward;
		}
}
