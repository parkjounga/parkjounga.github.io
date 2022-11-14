<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<mata name ="viewport" content="width-device-width", initial-scale="1">
<link rel ="stylesheet" href="css/bootstrap.css">
<title>가계부</title>
<style>
table {
background-color:white;
text-align:cneter;
line-height:center;
}
html,body{
width:100%;
height:100%;
}
.h1{
  padding: 10px 10px 2px 50px;
}
</style>
<%
   String id = (String) session.getAttribute("login.id");
   if (id == null) {
      id = "GUEST";
   }
%>

</head>
<body>

<table width="100%" border="0">
      <tr height="70" class="top">
         <td><a href="main" style="text-decoration: none"> 
         </a></td>
         <td colspan="5">
            <div align="center">
               <h1 ><a href ="/MyBoard/Main.jsp">회원제 가계부</a></h1>   
            </div>
         </td>
         
         <td align="center" width="200"><h3 class="y"><%=id%>님
            </h3> <%
    if (id.equals("GUEST")) {
 %>
            <button class="btn btn-primary" onclick="location.href='/MyBoard/login/formLogin.jsp'">로그인</button>
            <%
               } else {
            %>
            <button class="btn btn-primary" onclick="location.href='login'">로그아웃</button>
            <%
               }
            %></td>
         
         </tr>
         </table>
   
         
         
         
         
<!--  네비게이션바 -->
<nav class= "navbar navbar-default">
<!-- 네이게이션 상단바  -->
<div class="navbar-header"> 
<!-- 네비게이션 상단 박스영역  -->
 <button type="button" class="navbar-toggle collapsed"
         data-target="#bs-example-navbar-collapse-1"
         aria-expanded="false">

 <!--  화면이좁아지면 버튼이 우측에 나타난다 -->        
   <span class ="icon-bar"></span>
   <span class ="icon-bar"></span>
   <span class ="icon-bar"></span>
  </button>
 </div>

<!-- 메뉴영역 -->
<div class="collapse navbar-collapse" id ="bs-example-navbar-collapse-1">
<ul class="nav navbar-nav">
 <li class ="action"><a href="/MyBoard/Main.jsp"><h4>메인</h4></a></li>
 <li><a href="/MyBoard/member/memberForm.do"><h4>회원가입</h4></a></li>
 <li><a href="/MyBoard/member/modMemberForm.do"><h4>본인정보</h4></a></li>
 <li><a href="/MyBoard/homebook/homebookForm.do"><h4>가계부입력</h4></a></li>
 <li><a href="/MyBoard/homebook/listHomebooks.do"><h4>회원자료</h4></a></li>
<li><a href="/MyBoard/member/listMembers.do"><h4>모든회원</h4></a></li>
<li><a href="/MyBoard/board/listArticles.do"><h4>게시판</h4></a></li>
</ul>
 
</div>
</nav>

<!-- 부트스트랩 참조 영역 -->


<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="/js/bootstrap.js"></script>

</body>
</html>