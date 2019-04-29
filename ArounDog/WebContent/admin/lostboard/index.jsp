<%@page import="com.aroundog.model.domain.LostBoard"%>
<%@page import="com.aroundog.model.domain.FreeBoard"%>
<%@page import="java.util.List"%>
<%@page import="com.aroundog.model.domain.Admin"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
   Admin admin=(Admin)request.getSession().getAttribute("admin");
   List<LostBoard> lostboardList = (List)request.getAttribute("lostboardList");
%>
<!DOCTYPE html>
<html>
<head>
<title>게시판 관리</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" 
integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">

<!-- 테이블&버튼 관련한 스타일 시트 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>


<style>
<%@ include file="/admin/inc/maincss.jsp" %>
#AdoptManager {background-color: white;}
</style>
</head>
<script>
<%@ include file="/admin/inc/pagechange.jsp" %>

console.log("lostboardList 사이즈는 "+lostboardList.size());
function search(){
   
}
</script>
<body>
<form>
<div class="loginName" style="text-align:right"><%=admin.getId() %>님 로그인중</div>
<button class="tablink" type="button"><i class="fas fa-user-friends" style="font-size:20px"></i>  회원관리</button> 
<button class="tablink" type="button"><i class="fas fa-bullhorn" style="font-size:20px"></i>  제보관리</button>
<button class="tablink" type="button"><i class="far fa-edit" style="font-size:20px"></i>  입양신청관리</button>
<button class="tablink" type="button"><i class="far fa-comment-alt"    style="font-size:20px"></i>  게시판관리</button>
<button class="tablink" type="button"><i class="fas fa-dog" style="font-size:20px"></i>  입양게시물관리</button>
<button class="tablink" type="button"><i class="fas fa-dog" style="font-size:20px"></i>  임보게시판관리</button>
</form>

<div id="AdoptManager" class="tabcontent">
 
<div class="container">
  <h2 style="color:gray">임시보호 게시글 관리</h2>
  <br>
  <form class="form-inline" action="/action_page.php">
  <label class="mb-2 mr-sm-2" style="color:black">이름검색:</label>
  <input type="text" class="form-control mb-2 mr-sm-2" width="30%" placeholder="이름을 입력해주세요" name="user-name">
  <button type="button" class="btn btn-primary mb-2" onClick="search()">검색</button>
  </form>
  
  <table class="table table-striped">
    <thead>
      <tr>
        <th>Firstname</th>
        <th>Lastname</th>
        <th>Email</th>
        <th>삭제하기</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>John</td>
        <td>Doe</td>
        <td>john@example.com</td>
        <td><button class="btn btn-light">삭제</button></td>
      </tr>
    </tbody>
  </table>
</div>
      

</div>




   
</body>
</html> 