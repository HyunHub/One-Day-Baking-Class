package jsp.board.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.board.comment.model.CommentBean;
import jsp.board.comment.model.CommentDAO;
import jsp.board.model.BoardBean;
import jsp.board.model.BoardDAO;
import jsp.common.util.Action;
import jsp.common.util.ActionForward;

public class BoardDetailAction implements Action{

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ActionForward forward = new ActionForward();
		
		//글 번호 받기
		String num = request.getParameter("num");
		int boardNum = Integer.parseInt(num);
		String page = request.getParameter("page");
		String board_category = request.getParameter("board_category");
		
		BoardDAO dao = BoardDAO.getInstance();
		BoardBean board = dao.getDetail(boardNum);
		boolean result = dao.updateCount(boardNum);
		
		CommentDAO cmdao = CommentDAO.getInstance();
		ArrayList<CommentBean> commentList = cmdao.getCommentList(boardNum);
		
		System.out.println("댓글 길이 : " + commentList.size());
		
		if(commentList.size()>0) {
			request.setAttribute("commentList", commentList);
		}
		
		request.setAttribute("board", board);
		request.setAttribute("pageNum", page);
		request.setAttribute("board_category", board_category);
		System.out.println("수정후 페이지 넘버 : " + page);
		
		if(result) {
			forward.setRedirect(false);
			forward.setNextPath("BoardDetailForm.bo");
		}
		
		return forward;
	}
}
