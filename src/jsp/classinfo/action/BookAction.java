package jsp.classinfo.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.Book.model.BookBean;
import jsp.Book.model.BookDAO;
import jsp.common.util.Action;
import jsp.common.util.ActionForward;

public class BookAction implements Action{

    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("utf-8");
		
		int result = 0;
		String cname = request.getParameter("cname");
		int people = Integer.parseInt(request.getParameter("people"));
		String bday = request.getParameter("bday");
		String customer_id = request.getParameter("id");
		
		BookDAO bd = BookDAO.getInstance();
		BookBean book = bd.select(cname);
		if (book == null) {
			book= new BookBean ();
			book.setCname(cname);
			book.setPeople(people);
			book.setBday(bday);
			book.setCustomer_id(customer_id);
			
			result = bd.insert(book);
		} else result = -1;
		
		request.setAttribute("result", result);
		if(result>0) {
			forward.setRedirect(true);
			forward.setNextPath("classBook.ao");
		}else {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('This class has already been applied.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		}
			
		return forward;
		
    }
}
