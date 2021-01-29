package jsp.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.common.util.Action;
import jsp.common.util.ActionForward;
import jsp.qna.model.QnaBean;
import jsp.qna.model.QnaDAO;

public class BoardQnaWriteAction implements Action{

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		
		request.setCharacterEncoding("utf-8");
		
		try {
			QnaDAO dao = QnaDAO.getInstance();
			QnaBean qna = new QnaBean();
			
			qna.setQna_num(dao.getSeq()); //시퀀스 값
			qna.setQna_id(request.getParameter("qna_id")); //히든값
			qna.setQna_subject(request.getParameter("qna_subject"));
			qna.setQna_content(request.getParameter("qna_content"));
			qna.setQna_category(request.getParameter("qna_category"));
			qna.setQna_pw(request.getParameter("qna_pw"));
			
			System.out.println("글쓰기 qna_category : "+ qna.getQna_category());
			
			boolean result = dao.boardInsert(qna);
			
			System.out.println("result="+result);
			
			if(result) {
				forward.setRedirect(true);
				forward.setNextPath("BoardQnaListAction.bo");
			}
		}catch(Exception e) {
			System.out.println("QnA 작성 오류 : " + e.getMessage());
		}
		
		return forward;
	}

}
