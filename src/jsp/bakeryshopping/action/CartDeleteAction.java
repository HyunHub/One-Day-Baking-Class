package jsp.bakeryshopping.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.bakeryshopping.model.CartDao;
import jsp.common.util.Action;
import jsp.common.util.ActionForward;

public class CartDeleteAction implements Action{

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ActionForward forward = new ActionForward();
		
	 	String list = request.getParameter("list");
	    String buyer = request.getParameter("buyer");
	    int result = 0;
	    
	    System.out.println("customer_id : " +buyer);
	    
	    CartDao productProcess = CartDao.getInstance();	   
	    if(list.equals("all")){
	    	result = productProcess.deleteAll(buyer);
	    }else{
    		result = productProcess.deleteList(Integer.parseInt(list));
	    }
	    forward.setRedirect(true);
	    forward.setNextPath("cartList.co?customer_id=" + buyer);
	    
		return forward;
	}
}
