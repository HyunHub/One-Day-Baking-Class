package jsp.bakeryshopping.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.bakeryshopping.model.CartDao;
import jsp.common.util.Action;
import jsp.common.util.ActionForward;

public class UpdateCartAction implements Action{

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ActionForward forward = new ActionForward();
		
	 	int result = 0;
	    String cart_no = request.getParameter("cart_no");
	    String cart_count = request.getParameter("cart_count");
	    String buyer = request.getParameter("buyer");

	 	CartDao productProcess = CartDao.getInstance();
	    result = productProcess.updateCount(Integer.parseInt(cart_no),Integer.parseInt(cart_count));

	    System.out.println(buyer);
	    if (result > 0) {
	        response.sendRedirect("cartList.co?customer_id="+buyer);
	    }
	
		return forward;
	}
}
