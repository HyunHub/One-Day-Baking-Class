<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%> 
<%@ page import="jsp.bakerymaster.model.*" %>
<%@ page import="java.util.List,java.text.SimpleDateFormat" %>
 
<%-- <%@ include file="../masterSessionChk.jsp" %> --%>
<%
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");	 
	List <Product> productList = null; int number = 0;
 
    ProductDao productProcess = ProductDao.getInstance();
    int count = productProcess.getProductCount();
    
    if(count >0) {
    	productList = productProcess.getProducts();
    }

%>
<html><head><title>List</title>
<style>
 #list{
 	background-color : #F8ECE0;
 }
 .table{
 	text-align : center;
 	border : 1px solid #F8ECE0;
 }
</style>
</head>
<body>
<br><br>
<h3 align="center">상품 수정/삭제</h3>
<br><br>
<!-- <table width="800px" align="center">
<tr> <td align="right">
       <a href="productRegisterForm.co">Register Product</a> 
 </td></tr></table> -->
<table class="table table-hover" border="1" width="900px" align="center"> 
    <tr height="30">    
      <td id="list" align="center"><b>Num</b></td>
      <td id="list" align="center"><b>Name</b></td>
      <td id="list" align="center"><b>Price</b></td>
      <td id="list" align="center"><b>Stock</b></td>
      <td id="list" align="center"><b>State</b></td>
      <td id="list" align="center"><b>Image</b></td>
      <td id="list" align="center"><b>Content</b></td>
      <td id="list" align="center"><b>Edit</b></td>
      <td id="list" align="center"><b>Delete</b></td>
    </tr>
	<% for (int i = 0 ; i <productList.size() ; i++) {
      	Product product = (Product)productList.get(i);
      	%>

   <tr height="30">
      <td><%=++number%></td> 
      <td><%=product.getProduct_name()%></td> 
      <td><%=product.getProduct_price()%></td>
      <td><%=product.getProduct_stock()%></td> 
      <td>
      <% if(product.getProduct_stock() <= 0) {
            product.setProduct_state(2); %>
      <%=product.getProduct_state()%>
            <% } else { %>
      <%=product.getProduct_state()%> <% } %></td> 
      <td><%=product.getProduct_img()%></td>
      <td align="left"><%=product.getProduct_content()%></td>
      <td>
         <a href="productUpdateForm.co?product_id=<%=product.getProduct_id()%>">Edit</a></td>
      <td width="50">
         <a href="productDeleteForm.co?product_id=<%=product.getProduct_id()%>">Delete</a></td>    
  </tr> <% } %>
</table>
</body></html>