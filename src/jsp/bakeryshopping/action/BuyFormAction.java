package jsp.bakeryshopping.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.bakerymaster.model.Product;
import jsp.bakerymaster.model.ProductDao;
import jsp.bakeryshopping.model.Cart;
import jsp.bakeryshopping.model.CartDao;
import jsp.bakeryshopping.model.OrderDao;
import jsp.common.util.Action;
import jsp.common.util.ActionForward;
import jsp.member.model.CustomerBean;
import jsp.member.model.CustomerDAO;

public class BuyFormAction implements Action{

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ActionForward forward = new ActionForward();
		
		String buyer = request.getParameter("buyer");
		
		ProductDao pd = ProductDao.getInstance();
		
		ArrayList<Cart> cartLists = null;
		ArrayList<String> accountLists = null;
		ArrayList<Product> productLists = null;
		
		Cart cartList = null;

		CustomerBean customer= null;
		int number = 0;
		int total = 0;

		CartDao cartProcess = CartDao.getInstance();
	    cartLists = cartProcess.getCart(buyer);
	    
	    CustomerDAO customerProcess = CustomerDAO.getInstance();
	    customer = customerProcess.select(buyer);
	    
	    OrderDao orderProcess = OrderDao.getInstance();
	    accountLists = orderProcess.getAccount();
	    
	    System.out.println("buyer = " + buyer);
	    forward.setRedirect(false);
	    forward.setNextPath("buyForm.co?customer_id=" + buyer);

		return forward;
	}
}
