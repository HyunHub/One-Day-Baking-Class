package jsp.bakerymaster.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.bakerymaster.model.ProductDao;
import jsp.common.util.Action;
import jsp.common.util.ActionForward;

public class ProductDeleteAction implements Action{

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ActionForward forward = new ActionForward();
		
		request.setCharacterEncoding("utf-8");
	    int product_id = Integer.parseInt(request.getParameter("product_id"));
	  
	    ProductDao productProcess = ProductDao.getInstance();
	    int result = productProcess.deleteProduct(product_id);
	    if (result > 0 ){ 
		    response.sendRedirect("productList.co");
	    }
	    
		return forward;
	}
}
