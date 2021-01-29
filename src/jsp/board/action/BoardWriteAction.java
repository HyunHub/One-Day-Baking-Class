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

public class BoardWriteAction implements Action{

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		
		request.setCharacterEncoding("utf-8");
		
		//업로드할 파일 사이즈
		int fileSize = 5 * 1024 * 1024;
		
		//업로드할 파일 경로
		String uploadPath = request.getServletContext().getRealPath("/UploadFolder");
		//String board_category = request.getParameter("board_category");
		
		try {
			//파일 업로드
			MultipartRequest multi = new MultipartRequest(request, uploadPath, fileSize,"utf-8", new DefaultFileRenamePolicy());
			
			//파일 가져오기
			String fileName = "";
			Enumeration<String> names = multi.getFileNames();
			
			if(names.hasMoreElements())
			{
				String name = names.nextElement();
				fileName = multi.getFilesystemName(name);
			}
			
			BoardDAO dao = BoardDAO.getInstance();
			BoardBean board = new BoardBean();
			
			board.setBoard_num(dao.getSeq()); //시퀀스 값
			board.setBoard_id(multi.getParameter("board_id")); //히든값
			board.setBoard_subject(multi.getParameter("board_subject"));
			board.setBoard_content(multi.getParameter("board_content"));
			board.setBoard_file(fileName);
			board.setBoard_category(multi.getParameter("board_category"));
			
			System.out.println("글쓰기 board_category : "+ board.getBoard_category());
			System.out.println("글쓰기 file_upload : " + board.getBoard_file());
			
			boolean result = dao.boardInsert(board);
			
			System.out.println("result="+result);
			
			if(result) {
				
				forward.setRedirect(true);
				forward.setNextPath("BoardListAction.bo?board_category=" + board.getBoard_category());
				
			}
		}catch(Exception e) {
			System.out.println("글 작성 오류 : " + e.getMessage());
		}
		return forward;
	}

	
}
