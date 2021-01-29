package jsp.board.action;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.common.util.Action;
import jsp.common.util.ActionForward;
import jsp.event.model.EventDAO;

public class BoardEventDeleteAction implements Action{
	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ActionForward forward = new ActionForward();
		
		String num = request.getParameter("num");
		int eventNum = Integer.parseInt(num);
		
		EventDAO dao = EventDAO.getInstance();
		//삭제할 글에 있는 파일 정보 가저오기
		String fileName = dao.getFileName(eventNum);
		//글 삭제
		boolean result = dao.deleteBoard(eventNum);
		
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
			forward.setNextPath("BoardEventListAction.bo?event_category=2");

		}else {
			return null;
		}
		
		return forward;
	}
}
