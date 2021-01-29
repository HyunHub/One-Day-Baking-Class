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

public class BoardEventUpdateAction implements Action{
	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ActionForward forward = new ActionForward();
		
		int fileSize = 5*1024*1024;
		String uploadPath = request.getServletContext().getRealPath("/UploadFolder");
		
		try {
			MultipartRequest multi = new MultipartRequest(request, uploadPath, fileSize, "utf-8", new DefaultFileRenamePolicy());
			
			int num = Integer.parseInt(multi.getParameter("event_num"));
			String existFile = multi.getParameter("existing_file");
			
			EventBean event = new EventBean();
			event.setEvent_num(num);
			
			//글 수정시 업로드된 파일 가져오기
			Enumeration<String> fileNames = multi.getFileNames();
			if(fileNames.hasMoreElements()) {
				String fileName = fileNames.nextElement();
				String updateFile = multi.getFilesystemName(fileName);
				
				if(updateFile == null) {
					event.setEvent_file(existFile);
				}else {
					event.setEvent_file(updateFile);
				}
				
				EventDAO dao = EventDAO.getInstance();
				boolean result = dao.updateBoard(event);
				
				if(result) {
					forward.setRedirect(true);
					forward.setNextPath("BoardEventDetailAction.bo?num="+num);
				}
			}
		}catch(Exception e) {
			System.out.println("이벤트 수정 오류 : " + e.getMessage());
		}
		
		return forward;
	}
}
