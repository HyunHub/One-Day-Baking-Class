package jsp.classinfo.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.Book.model.BookBean;
import jsp.Book.model.BookDAO;
import jsp.common.util.Action;
import jsp.common.util.ActionForward;

public class BookListAction implements Action{

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ActionForward forward = new ActionForward();
		
        BookDAO dao = BookDAO.getInstance();
        ArrayList<BookBean> bookList = dao.getBookList();
        
        request.setAttribute("bookList", bookList);
        
        System.out.println("size : " +bookList.size());
 
        forward.setRedirect(false);
        forward.setNextPath("bookListForm.ao");
	
		return forward;
	}
}
