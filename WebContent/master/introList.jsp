<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="jsp.bakerymaster.model.*" %>
<%@ page import = "java.text.NumberFormat" %>
<html><head><title>Shopping Mall</title>
<link rel="stylesheet" type="text/css" href="css/shop-list.css"></head>
<body>
<br>
<h3 align="center"><img alt="" src="image/best.png">신상품 </h3>
<%
  Product productLists[] = null;
  int number =0;
 
  ProductDao productProcess = ProductDao.getInstance();
  for(int i = 1; i <= 3; i++){
	  productLists = productProcess.getProducts(i);
     }
%>
<div align="center">
&nbsp;&nbsp;<a href="ListAction.co">더 많은 상품 보러가기</a>
</div>
<div class="list-wrap">
<%             
     for(int j=0;j<productLists.length;j++){
        if (productLists[j] ==null) continue;%>

	<div class="list-item">
		<a href="ProductContentAction.co?product_id=<%=productLists[j].getProduct_id()%>">
        <img id="list-img" src="imageFile/<%=productLists[j].getProduct_img()%>"></a><br><br>
        <div style="padding-left : 60px;"><%=productLists[j].getProduct_name() %></div>
        <div style="padding-left : 60px;">Price :<%=productLists[j].getProduct_price()%></div>
	</div>

<% } %>
</div>
</body></html>