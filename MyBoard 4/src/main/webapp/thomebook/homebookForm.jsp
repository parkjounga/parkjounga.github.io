<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"
   isELIgnored="false" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"  />     
<!DOCTYPE html>
<link rel ="stylesheet" href="/MyBoard/css/bootstrap.css">
<%@include file="../Top.jsp"%>
<html>
<style>
  body{

background-size: cover;
background-repeat :no-repeat;

}
</style>
<head>
   <meta charset="UTF-8">
   <title>가계부 등록창</title>
<body>


<form method="post"   action="${contextPath}/homebook/addHomebook.do">
<h1  style="text-align:center">가계부등록</h1>
<table  align="center">
    <tr>
       <td width="200"><p align="right">수지구분</td>
       <td width="400"><input type="text" name="section"></td>
    </tr>
    <tr>
        <td width="200"><p align="right">계정과목</td>
        <td width="400"><input type="text"  name="accounttitle"></td>
    </tr>
    <tr>
        <td width="200"><p align="right">적요</td>
        <td width="400"><p><input type="text"  name="remark"></td>
    </tr>
    <tr>
        <td width="200"><p align="right">수입</td>
        <td width="400"><p><input type="text"  name="revenue"></td>
    </tr>
    <tr>
        <td width="200"><p align="right">지출</td>
        <td width="400"><p><input type="text"  name="expense"></td>
    </tr><tr>
        <td width="200"><p align="right">회원아이디</td>
        <td width="400"><p><input type="text"  name="mem_id"></td>
    </tr>
      
    <tr>
        <td width="200"><p>&nbsp;</p></td>
        <td width="400">
          <input type="submit" class="btn btn-primary"  value="기록하기">
          <input type="reset"  class="btn btn-primary" value="다시입력">
       </td>
    </tr>
</table>
</form>
</body>
</html>