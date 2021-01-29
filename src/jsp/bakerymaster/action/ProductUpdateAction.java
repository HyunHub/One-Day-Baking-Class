package jsp.bakerymaster.action;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import jsp.bakerymaster.model.Product;
import jsp.bakerymaster.model.ProductDao;
import jsp.common.util.Action;
import jsp.common.util.ActionForward;

public class ProductUpdateAction implements Action{

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ActionForward forward = new ActionForward();
		
		  request.setCharacterEncoding("utf-8");
		   String realFolder = "";
		   String filename   ="";
		   MultipartRequest imageUp = null;

		   String saveFolder = "/imageFile"; 
		   String encType    = "utf-8"; 
		   int maxSize = 5*1024*1024; 
		   realFolder = request.getServletContext().getRealPath(saveFolder);  
		   imageUp = new MultipartRequest(request,realFolder,maxSize,
				  encType,new DefaultFileRenamePolicy());

		   Enumeration files = imageUp.getFileNames();   

		   while(files.hasMoreElements()){
		      String name = (String)files.nextElement();   
		      filename = imageUp.getFilesystemName(name);
		   }

		  Product product = new Product();
		  int product_id= Integer.parseInt(imageUp.getParameter("product_id"));
		  String product_name  = imageUp.getParameter("product_name");
		  String product_price = imageUp.getParameter("product_price");
		  String product_state = imageUp.getParameter("product_state");
		  String product_stock = imageUp.getParameter("product_stock");
		  String product_img = imageUp.getParameter("product_img");
		  String product_content  = imageUp.getParameter("product_content");
		 
		  
		  product.setProduct_name(product_name);
		  product.setProduct_price(Integer.parseInt(product_price));
		  product.setProduct_stock(Integer.parseInt(product_stock));
		  product.setProduct_img(filename);
		  product.setProduct_content(product_content);
		  product.setProduct_state(Integer.parseInt(product_state));
		  
		  int n = product.getProduct_stock();

		  if(n <= 0) {
			  product.setProduct_state(2);
		  } else product.setProduct_state(0);
		  
		  ProductDao productProcess = ProductDao.getInstance();
		  int result = productProcess.updateProduct(product, product_id); 
		  if (result > 0 ) {
			 forward.setRedirect(true);
			 forward.setNextPath("productList.co");
		  }
		  
		  return forward;
	}


}