package jsp.bakeryshopping.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.bakerymaster.model.Product;
import jsp.bakerymaster.model.ProductDao;
import jsp.bakeryshopping.model.Order;
import jsp.bakeryshopping.model.OrderDao;
import jsp.common.util.Action;
import jsp.common.util.ActionForward;

public class OrderedListAction implements Action{

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ActionForward forward = new ActionForward();
		
		Order order = new Order();
		
		OrderDao orderProcess = OrderDao.getInstance();
		int count = orderProcess.getListCount();
		ArrayList<Order> orderLists = orderProcess.getOrderList();
		
		ProductDao pd = ProductDao.getInstance();
    	Product product = pd.getProduct(order.getProduct_id());
		
		if(count>0 ) {
			forward.setRedirect(false);
			forward.setNextPath("orderedList.co");
		}
		
		return forward;
	}
}
