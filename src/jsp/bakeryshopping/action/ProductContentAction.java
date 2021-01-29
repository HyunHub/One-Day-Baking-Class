package jsp.bakeryshopping.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.bakerymaster.model.Product;
import jsp.bakerymaster.model.ProductDao;
import jsp.common.util.Action;
import jsp.common.util.ActionForward;

public class ProductContentAction implements Action{

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ActionForward forward = new ActionForward();
		
	    String product_id = request.getParameter("product_id");
	    
		ProductDao productProcess = ProductDao.getInstance(); 
		Product product = productProcess.getProduct(Integer.parseInt(product_id));
		
		request.setAttribute("product", product);
		
		forward.setRedirect(false);
		forward.setNextPath("productContent.co?product_id="+product_id);
		
		return forward;
	}
}
