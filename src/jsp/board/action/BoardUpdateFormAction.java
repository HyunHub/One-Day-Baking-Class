package jsp.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.board.model.BoardBean;
import jsp.board.model.BoardDAO;
import jsp.common.util.Action;
import jsp.common.util.ActionForward;

public class BoardUpdateFormAction implements Action{
	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ActionForward forward = new ActionForward();
		
		String page = request.getParameter("page");
		String num = request.getParameter("num");
		String board_category = request.getParameter("board_category");
		int boardNum = Integer.parseInt(num);
		
		BoardDAO dao = BoardDAO.getInstance();
		BoardBean board = dao.getDetail(boardNum);
		
		request.setAttribute("board", board);
		request.setAttribute("pageNum", page);
		request.setAttribute("board_category", board_category);
		
		System.out.println("수정 board_category : " + board_category);
		System.out.println("수정  pageNum : " + page);
		
		forward.setRedirect(false);
		forward.setNextPath("BoardUpdateForm.bo?board_category="+board_category+"&pageNum="+page);
		
		return forward;
	}

}
