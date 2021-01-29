package jsp.board.action;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.board.model.BoardBean;
import jsp.board.model.BoardDAO;
import jsp.common.util.Action;
import jsp.common.util.ActionForward;

public class BoardNoticeAction implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		//현재 페이지 번호
		int spage = 1;
		String page = request.getParameter("page");
		
		if(page != null)
			spage = Integer.parseInt(page);
		
		//검색 조건과 검색내용
		String opt = request.getParameter("opt");
		String condition = request.getParameter("condition");
		String board_category = request.getParameter("board_category");
		
		//검색 조건과 검색 내용을 map에 담는다
		HashMap<String, Object> listOpt = new HashMap<String, Object>();
		listOpt.put("opt", opt);
		listOpt.put("condition", condition);
		listOpt.put("start", spage*10-9);
		listOpt.put("board_category", board_category);
		
		System.out.println("board_category : " + board_category);
		
		BoardDAO dao = BoardDAO.getInstance();
		int listCount = dao.getBoardListCount(listOpt);
		ArrayList<BoardBean> list =  dao.getBoardList(listOpt);
		
		System.out.println("size = "+ list.size());
		System.out.println("notice listCount : " + listCount);
		
		int maxPage = (int)(listCount/10.0 + 0.9);
		int startPage = (int)(spage/5.0 + 0.8) * 5 - 4;
		int endPage = startPage + 4;
		if(endPage > maxPage)	endPage = maxPage;
		
		int tot = listCount - startPage + 1;

		request.setAttribute("spage", spage);
		request.setAttribute("maxPage", maxPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("board_category", board_category);
		request.setAttribute("tot",tot);
		
		request.setAttribute("list", list);

		forward.setRedirect(false);
		forward.setNextPath("BoardNoticeForm.bo");
		
		return forward;
	}

}