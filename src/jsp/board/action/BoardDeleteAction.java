package jsp.board.action;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.board.model.BoardBean;
import jsp.board.model.BoardDAO;
import jsp.common.util.Action;
import jsp.common.util.ActionForward;

public class BoardDeleteAction implements Action{
	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ActionForward forward = new ActionForward();
		
		String num = request.getParameter("num");
		String board_category = request.getParameter("board_category");
		int boardNum = Integer.parseInt(num);
		String pageNum = request.getParameter("page");
		
		BoardBean board = new BoardBean(); 
		board.setBoard_category("board_category");
		
		request.setAttribute("board_category", board_category);
		
		BoardDAO dao = BoardDAO.getInstance();
		//삭제할 글에 있는 파일 정보 가저오기
		String fileName = dao.getFileName(boardNum);
		//글 삭제
		boolean result = dao.deleteBoard(boardNum);
		
		//파일 삭제
		if(fileName != null) {
			String folder = request.getServletContext().getRealPath("UploadFolder");
			String filePath = folder + "/" + fileName;
			
			File file = new File(filePath);
			if(file.exists()) {
				file.delete();
			}
		}
		if(result) {
			forward.setRedirect(true);
			forward.setNextPath("BoardListAction.bo?page="+pageNum+"&board_category="+board_category);

		}else {
			return null;
		}
		return forward;
	}

}
