<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import = "jsp.bakerymaster.model.*,java.util.*,java.text.NumberFormat" %>
<%
   String product_id = request.getParameter("product_id");
%>
<html><head><title>Book Shopping Mall</title>
</head>
<body>     

<% 	String path = request.getContextPath();
                    
    ProductDao productProcess = ProductDao.getInstance(); 
    Product product2 = new Product();
    List <Product> list = null;
    int count = productProcess.getProductCount();
    
    
    for(int i = 0; i < count; i++) {
    	list = productProcess.getProducts();	}

%>
<br>
<h3 align="center">상품 전체 보기</h3>
<div align="center">
<a href="shopMain.co">신상품 </a>
</div>
<br><br>
<%	int rowPerPage = 6;
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
	for (Product product : lists) {
%>

     <table width="1000" align="center"> 
       <tr height="50"> 
         <td rowspan="4"  width="100" align="center" valign="middle">
             <a href="ProductContentAction.co?product_id=<%=product.getProduct_id()%>">
             <img src="<%=path %>/imageFile/<%=product.getProduct_img()%>" border="0" width="100" height="100"></a></td> 
         <td width="350"><font size="5"><b>
             <a href="ProductContentAction.co?product_id=<%=product.getProduct_id()%>"><%=product.getProduct_name() %></a></b></font></td> 
         <td rowspan="4" width="100"  align="center"  valign="middle">
             <%if(product.getProduct_state()==2){ %>
                     <b>품절</b>
             <%}else{ %>      &nbsp;    <%} %>
         </td>
       </tr>        
       <tr height="30" > 
         <td><%=product.getProduct_content()%></td> 
       </tr>

       <tr height="30" > 
         <td width="350" >Price : <%=product.getProduct_price()%><br></td>
       </tr>        
     </table> 
     <%
    }
   //  } %>
     <div align="center">
	<%  if (startPage > pagePerBlock) { %>
		<a href="list.co?pageNum=<%=startPage-1%>">이전</a>
	<% 	}
		for (int i = startPage; i <= endPage; i++) { %>
		<a href="list.co?pageNum=<%=i%>"><%=i%></a>
	<%  } 
		if (endPage < totPage) {  %>
		<a href="list.co?pageNum=<%=endPage+1%>">다음</a>
	<%  } %>
</div>

</body></html>