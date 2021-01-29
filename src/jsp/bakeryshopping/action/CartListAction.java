package jsp.bakeryshopping.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.bakeryshopping.model.Cart;
import jsp.bakeryshopping.model.CartDao;
import jsp.common.util.Action;
import jsp.common.util.ActionForward;

public class CartListAction implements Action{

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ActionForward forward = new ActionForward();
		
		String buyer = request.getParameter("customer_id");
		String product_id = request.getParameter("product_id");
		
		CartDao productProcess = CartDao.getInstance();
		ArrayList<Cart> cartLists = productProcess.getCart(buyer);
		 
		 int count = 0;
		 int number = 0;
		 int total = 0;

	     count = productProcess.getListCount(buyer);
	     //cartLists = productProcess.getCart(buyer);
	     
	     request.setAttribute("cartLists", cartLists);
	     
	     System.out.println("cutomer_id : " + buyer);
	     
	     forward.setRedirect(false);
	     forward.setNextPath("cartList.co?buyer="+buyer);

		return forward;
	}
}
