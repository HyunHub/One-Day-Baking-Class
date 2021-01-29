package jsp.bakeryshopping.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.bakeryshopping.model.Cart;
import jsp.bakeryshopping.model.CartDao;
import jsp.bakeryshopping.model.OrderDao;
import jsp.common.util.Action;
import jsp.common.util.ActionForward;

public class BuyAction implements Action{

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ActionForward forward = new ActionForward();
		
		request.setCharacterEncoding("utf-8");
		
	    String account = request.getParameter("account");
	    String deliveryName = request.getParameter("deliveryName");
	    String deliveryPhone = request.getParameter("deliveryPhone");
	    String deliveryAddess = request.getParameter("deliveryAddess");
	    String buyer = request.getParameter("customer_id");  
	    
	    CartDao cartProcess = CartDao.getInstance();
	    ArrayList<Cart> cartLists = cartProcess.getCart(buyer);
	      
	    OrderDao orderProcess = OrderDao.getInstance();   
	    orderProcess.insertOrder(cartLists,buyer,account, deliveryName, deliveryPhone, deliveryAddess);
	   
	    response.sendRedirect("buyList.co?customer_id="+buyer);
	    
		return forward;
	}
}
