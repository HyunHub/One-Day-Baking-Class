<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import = "java.sql.Timestamp" %>
<%-- <%@ include file="../masterSessionChk.jsp" %> --%>
<!DOCTYPE html><html><head><title>Product Register</title>
<script type="text/javascript" src="checkScript.js"></script>
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
</head><body>  
<br><br>
<div align="center">
<h3 align="center">상품 등록</h3>
<br><br>
<form method="post" name="writeform" action="ProductResigterAction.co" enctype="multipart/form-data">
<table width="700" border="1">
<tr><td align="center">Product name</td><td>
     <input type="text" size="50" maxlength="50" name="product_name"></td>  </tr>  
  <tr><td align="center">price</td><td>
      <input type="text" size="10" maxlength="9" name="product_price"></td>  </tr>         
   <tr><td align="center">Stock</td><td>
        <input type="text" size="10" maxlength="10" name="product_stock"></td>
  </tr>
  <tr>
    <td align="center">Image</td><td><input type="file" name="product_image"></td>
  </tr>
  <tr><td align="center">Content</td><td>
     <textarea name="product_content" rows="13" cols="40"></textarea> </td>
  </tr>
 <tr><td align="center">State</td><td>
       <input type="text" size="20" maxlength="2" name="product_state" placeholder="0은 등록 2는 품절입니다."></td>
  </tr>
<tr></table>
<div class="btn-wrap">
  <input id="btn" type="submit" value="등록" >  
  <input id="btn" type="reset" value="취소" >
</div>
</form></div> 
</body></html>