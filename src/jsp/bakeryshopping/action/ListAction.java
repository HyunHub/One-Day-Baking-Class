package jsp.bakeryshopping.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.bakerymaster.model.Product;
import jsp.bakerymaster.model.ProductDao;
import jsp.common.util.Action;
import jsp.common.util.ActionForward;

public class ListAction implements Action{

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ActionForward forward = new ActionForward();
		
		String product_id = request.getParameter("product_id");
		
		String path = request.getContextPath();
        
	    ProductDao productProcess = ProductDao.getInstance(); 
	    //Product product2 = new Product();
	    
	    List<Product> list = productProcess.getProducts();
	    int count = productProcess.getProductCount();
	    
	    request.setAttribute("list", list);
	    request.setAttribute("count", count);
	    
	    int rowPerPage = 6;
		int pagePerBlock = 6;
		String pageNum = request.getParameter("pageNum");
		if (pageNum==null || pageNum.equals("")) {
			pageNum = "1";
		}
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1)*rowPerPage + 1;
		int endRow  = startRow + rowPerPage - 1;
		ProductDao pd = ProductDao.getInstance();

		List<Product> lists = pd.list(startRow, endRow);
		int tot = pd.total();
		int startPage = currentPage - (currentPage-1)%pagePerBlock;
		int endPage = startPage + pagePerBlock - 1;
		int totPage = (int)Math.ceil((double)tot/rowPerPage);
		if (endPage > totPage) endPage = totPage;
		
		request.setAttribute("lists", lists);
		
		forward.setRedirect(false);
		forward.setNextPath("list.co");
		
		return forward;
	}
}
