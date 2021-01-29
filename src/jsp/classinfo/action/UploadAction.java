package jsp.classinfo.action;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import jsp.classinfo.model.BClassBean;
import jsp.classinfo.model.BClassDAO;
import jsp.common.util.Action;
import jsp.common.util.ActionForward;

public class UploadAction implements Action{

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
			
			BClassDAO dao = BClassDAO.getInstance();
			BClassBean bclass = new BClassBean();
		
			bclass.setCname(multi.getParameter("cname"));
			bclass.setTeacher(multi.getParameter("teacher"));
			bclass.setImage(fileName);
			bclass.setBday(multi.getParameter("bday"));
			
			System.out.println(bclass.getBday());
			System.out.println(bclass.getCname());
			System.out.println(bclass.getImage());
			System.out.println(bclass.getTeacher());
			
			int result = 0;
			result = dao.insert(bclass);
			
			System.out.println("result="+result);
			
			if(result>0) {
				forward.setRedirect(true);
				forward.setNextPath("ClassListAction.ao");
			}
		}catch(Exception e) {
			System.out.println("이벤트 글 작성 오류 : " + e.getMessage());
		}
	
		return forward;
    }
}
