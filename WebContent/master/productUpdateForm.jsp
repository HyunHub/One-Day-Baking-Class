<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import = "java.sql.Timestamp" %>
<%@ page import="jsp.bakerymaster.model.*" %>
<%-- <%@ include file="../masterSessionChk.jsp" %> --%>

<%
	int product_id = Integer.parseInt(request.getParameter("product_id"));
    ProductDao productProcess = ProductDao.getInstance();
    Product product =  productProcess.getProduct(product_id);
%>
<!DOCTYPE html><html><head><title>Edit Product</title>
<style>
.btn-wrap{
	text-align : center;
	margin-top : 20px;
}
#btn{
	dispaly : inline-block;
	border : none;
	background-color : #FBF8EF;
	color : #3A2F0B;
	font-size : 15px;
}
</style>
</head>
<body>  <div align="center">
<br><br>
<h3 align="center">상품 수정</h3>
<br><br>
<form method="post" name="writeform" action="ProductUpdateAction.co?product_id=<%=product_id %>"  enctype="multipart/form-data">
<table width="700" border="1">
  <tr><td align="center">Product Name</td><td>
        <input type="text" size="50" maxlength="50" name="product_name" 
       		value="<%=product.getProduct_name() %>"></td>
  </tr>
  <tr> <td align="center">Price</td>  <td>
        <input type="text" size="10" maxlength="9" name="product_price" 
        value="<%=product.getProduct_price() %>"></td>
  </tr>
  <tr> <td align="center">Stock</td><td>
        <input type="text" size="10" maxlength="10" name="product_stock" 
        value="<%=product.getProduct_stock() %>"></td>
  </tr>
  <tr> <td align="center">Image</td> <td>
        <input type="file" name="product_img" value="<%=product.getProduct_img() %>"></td>
  </tr>
  <tr><td align="center">Content</td> <td>
     <textarea name="product_content" rows="13" cols="40"><%=product.getProduct_content() %></textarea> </td>
  </tr>
  
 <tr><td align="center">State</td> <td>
        <input type="text" size="5" maxlength="2" name="product_state" value="<%=product.getProduct_state() %>"></td>
  </tr>
</table>
<div class="btn-wrap">
  <input id="btn" type="submit" value="등록" >  
  <input id="btn" type="reset" value="취소" >
  <input id="btn" type="button" value="뒤로가기" onclick="history.back();">
</div>
 </form> </div>
</body>
</html>