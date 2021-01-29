package jsp.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.common.util.Action;
import jsp.common.util.ActionForward;
import jsp.qna.model.QnaBean;
import jsp.qna.model.QnaDAO;

public class BoardQnaReplyAction implements Action{

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();
		
		QnaDAO dao = QnaDAO.getInstance();
		QnaBean qna = new QnaBean();
		
		//답글 작성후 원래 페이지로 돌아가기 위한 페이지 넘버
		String pageNum = request.getParameter("page");
		
		String id = request.getParameter("qna_id");
		String subject = request.getParameter("qna_subject");
		String content = request.getParameter("qna_content");
		int ref = Integer.parseInt(request.getParameter("qna_re_ref"));
		int lev = Integer.parseInt(request.getParameter("qna_re_lev"));
		int seq = Integer.parseInt(request.getParameter("qna_re_seq"));
		
		//답글 중 최신답글이 맨 위로 올라가기 위해 seq+1
		qna.setQna_re_ref(ref);
		qna.setQna_re_seq(seq);
		dao.updateReSeq(qna);
		
		//답글 저장하기
		qna.setQna_num(dao.getSeq());
		qna.setQna_id(id);
		qna.setQna_subject(subject);
		qna.setQna_content(content);
		qna.setQna_re_ref(ref);
		qna.setQna_re_lev(lev+1);
		qna.setQna_re_seq(seq+1);
		
		boolean result = dao.boardInsert(qna);
		
		if(result) {
			forward.setRedirect(false);
			forward.setNextPath("BoardQnaListAction.bo?page="+pageNum);
		}		
		return forward;
	}
}
