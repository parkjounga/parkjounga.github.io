<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	isELIgnored="false" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"  />     
<!DOCTYPE html>
<html>
<link rel ="stylesheet" href="/MyBoard/css/bootstrap.css">
<%@include file="../Top.jsp"%>
<style>
table {
        font-size:21px;
        align="center"
      }
button{
   background-color: #5882FA; 
}

</style>
<head>
   <meta charset="UTF-8">
   <title>회원 가입창</title>
<body>

<form method="post"   action="${contextPath}/member/addMember.do">
<h1  style="text-align:center">회원 가입창</h1>
<table  align="center">
    <tr>
       <td width="200"><p align="right">아이디</td>
       <td width="400"><input type="text" name="id"></td>
    </tr>
    <tr>
        <td width="200"><p align="right">비밀번호</td>
        <td width="400"><input type="password"  name="pwd"></td>
    </tr>
    <tr>
        <td width="200"><p align="right">이름</td>
        <td width="400"><p><input type="text"  name="name"></td>
    </tr>
    <tr>
        <td width="200"><p align="right">이메일</td>
        <td width="400"><p><input type="text"  name="email"></td>
    </tr>
    <tr>
        <td width="200"><p>&nbsp;</p></td>
        <td width="400">
	       <button type="submit"  class="btn btn-primary">가입하기</button>
	       <button type="reset"class="btn btn-primary">다시입력</button>


	        
       </td>
    </tr>
</table>
</form>
</body>
</html>