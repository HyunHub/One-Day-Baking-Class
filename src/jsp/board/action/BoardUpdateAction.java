package jsp.board.action;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import jsp.board.model.BoardBean;
import jsp.board.model.BoardDAO;
import jsp.common.util.Action;
import jsp.common.util.ActionForward;

public class BoardUpdateAction implements Action{
	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ActionForward forward = new ActionForward();
		
		String page = request.getParameter("page");
		
		int fileSize = 5*1024*1024;
		String uploadPath = request.getServletContext().getRealPath("/UploadFolder");
		
		try {
			MultipartRequest multi = new MultipartRequest(request, uploadPath, fileSize, "utf-8", new DefaultFileRenamePolicy());
			
			int num = Integer.parseInt(multi.getParameter("board_num"));
			String subject = multi.getParameter("board_subject");
			String content = multi.getParameter("board_content");
			String existFile = multi.getParameter("existing_file");
			String category = multi.getParameter("board_category");
			
			BoardBean board = new BoardBean();
			board.setBoard_num(num);
			board.setBoard_subject(subject);
			board.setBoard_content(content);
			board.setBoard_category(category);
			
			//글 수정시 업로드된 파일 가져오기
			Enumeration<String> fileNames = multi.getFileNames();
			if(fileNames.hasMoreElements()) {
				String fileName = fileNames.nextElement();
				String updateFile = multi.getFilesystemName(fileName);
				
				if(updateFile == null) {
					board.setBoard_file(existFile);
				}else {
					board.setBoard_file(updateFile);
				}
				
				BoardDAO dao = BoardDAO.getInstance();
				boolean result = dao.updateBoard(board);
				
				if(result) {
					forward.setRedirect(true);
					//forward.setNextPath("BoardListAction.bo?page="+pageNum+"&board_category="+category);
					forward.setNextPath("BoardDetailAction.bo?num="+num+"&page="+page);
				}
			}
		}catch(Exception e) {
			System.out.println("글 수정 오류 : " + e.getMessage());
		}
		
		return forward;
	}

}
