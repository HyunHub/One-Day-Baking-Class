package jsp.bakeryshopping.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.bakeryshopping.model.Cart;
import jsp.bakeryshopping.model.CartDao;
import jsp.common.util.Action;
import jsp.common.util.ActionForward;

public class CartInsertAction implements Action{

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ActionForward forward = new ActionForward();
		
		request.setCharacterEncoding("utf-8"); 
		
		String buyer = request.getParameter("customer_id");
		String product_id = request.getParameter("product_id");
		int cart_count = Integer.parseInt(request.getParameter("cart_count"));
		
		
	    CartDao productProcess = CartDao.getInstance();
	    Cart cart = new Cart();
	    
	    cart.setCustomer_id(buyer);
	    cart.setProduct_id(Integer.parseInt(request.getParameter("product_id")));
	    cart.setCart_count(cart_count);
	    
	    
	    System.out.println("customer id : " + buyer);
	    System.out.println("product id : " + product_id);
	    System.out.println("cart_count" + cart_count);
	    
	    int result = productProcess.insertCart(cart);

	    if (result > 0 ) {
	    	forward.setRedirect(true);
	    	forward.setNextPath("CartListAction.co?customer_id="+buyer);
	       //response.sendRedirect("shopMain.jsp?pgm=cartList.jsp");
	    }
		
		return forward;
	}
}
