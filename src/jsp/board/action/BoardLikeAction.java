package jsp.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.board.model.BoardDAO;
import jsp.common.util.Action;
import jsp.common.util.ActionForward;
import jsp.recommend.model.LikeDAO;

public class BoardLikeAction implements Action {

public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();

		request.setCharacterEncoding("utf-8");
		
		String recommend_id = request.getParameter("recommend_id");
		//글번호 받아오기
		String num = request.getParameter("num");
		int boardNum = Integer.parseInt(num);
		String pageNum = request.getParameter("page");
		request.setAttribute("pageNum", pageNum);

		BoardDAO bddao = BoardDAO.getInstance();
		LikeDAO ldao = LikeDAO.getInstance();
		
		System.out.println("likeaction board_num : " + boardNum);
		System.out.println("likeaction recommend_id : " + recommend_id);


		int result = ldao.like(recommend_id, boardNum);
		
		if(result==1) {
			result = bddao.like(boardNum);
			if (result==1) {
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('complete recommend');");
				script.println("location.href='BoardDetailAction.bo?num="+boardNum+"&pageNum="+pageNum+"';");
				script.println("</script>");
				script.close();
				forward.setRedirect(true);
				//forward.setNextPath("BoardDetailAction.bo?num="+boardNum+"&pageNum="+pageNum);
				}else {
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('데이터베이스 오류가 발생했습니다.');");
				script.println("history.back();");
				script.println("</script>");
				script.close();
				}
		}
		else {
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('already recommend');");
				script.println("history.back();");
				script.println("</script>");
				script.close();
				}

		return forward;
	}
}
