<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="book.do" method="post">
	<table> 
		<tr>
			<th>클래스 선택</th>
			<td>
				<select name="cname" required="required">
	      	<option value="1">Cake</option>
	      	<option value="2">Macaroon</option>
	      	<option value="3">Bread</option>
				</select>
			</td>
		</tr>
		<tr>     
	  	<th>인원 선택</th>
	  	<td>
	  		<select name="people" required="required">
	      	<option value="1">1명</option>
	      	<option value="2">2명</option>
	        <option value="3">3명</option>
	        <option value="4">4명</option>
				</select>
			</td>
		</tr>
		<tr>
			<th>날짜 선택</th>
			<td>
				<input type="date" name="bday" required="required">
			</td>
		</tr>
		<tr>     
	  	<th>신청자</th>
	  	<td>
	  		<select name="id" required="required">
	      	<option> ${customer.customer_id } </option>
				</select>
			</td>
		</tr>
		<tr>
			<th colspan="2"><input type="submit" value="클래스 신청하기"></th>
		</tr>
	</table>
</form>
</body> 
</html>