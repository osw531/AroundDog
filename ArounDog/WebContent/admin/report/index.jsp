<%@page import="com.aroundog.model.domain.Report"%>
<%@page import="com.aroundog.model.domain.FreeBoard"%>
<%@page import="java.util.List"%>
<%@page import="com.aroundog.model.domain.Admin"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%!//Pager pager = new Pager();%>
<%
	Admin admin = (Admin) request.getSession().getAttribute("admin");
%> 
<!DOCTYPE html>
<html>
<head>
<title>게시판 관리</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
	integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ"
	crossorigin="anonymous">
	  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<style>
<%@ include file ="/admin/inc/maincss.jsp" %> 
#Report {
	background-color: white;
}
</style>
</head>
<script>
<%@ include file="/admin/inc/pagechange.jsp" %>	
</script>
<body>
	<form>
		<div class="loginName" style="text-align: right"><%=admin.getName()%>님
			로그인중
		</div>
		<button class="tablink" type="button">
			<i class="fas fa-user-friends" style="font-size: 20px"></i> 회원관리
		</button>
		<button class="tablink" type="button">
			<i class="fas fa-bullhorn" style="font-size: 20px"></i> 제보관리
		</button>
		<button class="tablink" type="button">
			<i class="far fa-edit" style="font-size: 20px"></i> 입양신청관리
		</button>
		<button class="tablink" type="button">
			<i class="far fa-comment-alt" style="font-size: 20px"></i> 게시판관리
		</button>
		<button class="tablink" type="button">
			<i class="fas fa-dog" style="font-size: 20px"></i> 입양게시물관리
		</button>
	</form>
	<div id="Report" class="tabcontent">
 		<table class="table table-bordered table-sm" style="text-align: center">
 			<tr>
 				<th>No</th>
				<th>제목</th>
				<th>이메일</th>
				<th>제보날짜</th>
				<th>전화번호</th>
				<th>확인여부</th>
 			</tr>
 			<c:set var="curPos" value="${pager.curPos }"/>
 			<c:set var="num" value="${pager.num}"/>
 			<c:forEach var="report" items="${reportList}" begin="${curPos}" end="${pager.curPos+pager.pageSize-1}">
 				<tr>
	 				<td width="30px" height="80px" style="vertical-align: middle">${num}</td>
	 				<td style="vertical-align: middle"><a href="/reports/${report.report_id}">${report.title}</a></td>
	 				<td style="vertical-align: middle">${report.email}</td>
	 				<td style="vertical-align: middle">${report.regdate}</td> 				
	 				<td style="vertical-align: middle">${report.phone}</td>
	 				<td style="vertical-align: middle">
	 					<c:if test="${report.checking == 1}">
	 						확인완료
	 					</c:if>
	 					<c:if test="${report.checking != 1}">
	 						미확인
	 					</c:if>
	 				</td>
	 			</tr>
	 			<c:set var="num" value="${num-1}"/>	
 			</c:forEach>
 			<tr>
 				<td colspan="6" align="center">
 				<c:if test="${pager.firstPage-1>0}">
 					<a href="/reports?currentPage=${pager.firstPage-1}">◀</a>
 				</c:if>
 				<c:if test="${pager.firstPage-1<=0}">
 					<a href="javascript:alert('첫번째 페이지입니다!');">◀</a>
 				</c:if>
 					<c:forEach var="i" begin="${pager.firstPage}" end="${pager.lastPage}">
 						<c:if test="${i<=pager.totalPage }">
 							<a href="/reports?currentPage=${i}">[${i}]</a>
 						</c:if>			
 					</c:forEach>
 					<c:if test="${pager.lastPage+1<pager.totalPage}">
 						<a href="/reports?currentPage=${pager.lastPage+1 }">▶</a>
 					</c:if>
 					<c:if test="${pager.lastPage+1>pager.totalPage }">
 						<a href="javascript:alert('마지막 페이지입니다!');">▶</a>
 					</c:if>
 				</td>
 			</tr>
		</table>
	</div>
</body>
</html>
