package jsp.board.action;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import jsp.common.util.Action;
import jsp.common.util.ActionForward;
import jsp.event.model.EventBean;
import jsp.event.model.EventDAO;

public class BoardEventWriteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		
		request.setCharacterEncoding("utf-8");
		
		//업로드할 파일 사이즈
		int fileSize = 5 * 1024 * 1024;
		//업로드할 파일 경로
		String uploadPath = request.getServletContext().getRealPath("/UploadFolder");
		
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
			
			EventDAO dao = EventDAO.getInstance();
			EventBean event = new EventBean();
			
			event.setEvent_num(dao.getSeq()); //시퀀스 값
			event.setEvent_id(multi.getParameter("event_id")); //히든값
			//board.setBoard_file(multi.getParameter("board_file"));
			event.setEvent_file(fileName);
			
			System.out.println("이벤트 file_upload : " + event.getEvent_file());
			
			boolean result = dao.boardInsert(event);
			
			System.out.println("result="+result);
			
			if(result) {
				
				forward.setRedirect(true);
				forward.setNextPath("BoardEventListAction.bo?event_category=1");
			}
		}catch(Exception e) {
			System.out.println("이벤트 글 작성 오류 : " + e.getMessage());
		}
	
		return forward;
	}
}
