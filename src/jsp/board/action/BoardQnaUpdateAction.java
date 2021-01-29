package jsp.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.common.util.Action;
import jsp.common.util.ActionForward;
import jsp.qna.model.QnaBean;
import jsp.qna.model.QnaDAO;

public class BoardQnaUpdateAction implements Action{
	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ActionForward forward = new ActionForward();
		
		request.setCharacterEncoding("utf-8");
		
		String pageNum = request.getParameter("page");
		
		try {
			QnaDAO dao = QnaDAO.getInstance();
			QnaBean qna = new QnaBean();
			
			int num = Integer.parseInt(request.getParameter("qna_num"));
			String subject = request.getParameter("qna_subject");
			String content = request.getParameter("qna_content");
			String category = request.getParameter("qna_category");
			
			qna.setQna_num(num);
			qna.setQna_subject(subject);
			qna.setQna_content(content);
			qna.setQna_category(category);

			boolean result = dao.updateBoard(qna);
			
			System.out.println(qna.getQna_id());
			System.out.println(qna.getQna_subject());
			System.out.println(qna.getQna_content());
			System.out.println(qna.getQna_category());
			System.out.println(qna.getQna_num());
			
			if(result) {
				forward.setRedirect(true);
				forward.setNextPath("BoardQnaDetailAction.bo?num="+num+"&pageNum="+pageNum);
			}
			
		}catch(Exception e) {
			System.out.println("QnA 글 수정 오류 : " + e.getMessage());
		}
		
		return forward;
	}
}
