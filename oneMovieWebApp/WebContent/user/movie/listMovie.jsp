<%-- listArticle.jsp --%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
<!doctype html>
<html>
 <head>
  <meta charset="UTF-8">
  <title>영화 목록 조회 화면</title>
 </head>
 <body>
 <h3>영화 목록 조회 화면</h3>
 <script src="../../js/jquery-3.2.1.min.js"></script> 

	<table border="1" id="table">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>상영시간</th>
			<th>감독</th>
			<th>등급</th>
			<th>국가</th>
		</tr>

	<c:forEach var="movie" items="${requestScope.movies }" varStatus="loop">
		<c:url var="url" value="/user/movie/detailMovie.do" scope="page" >
		<c:param name="movieNo" value="${pageScope.movie.movieNo}"/>
		</c:url>
		<tr>
			<td>${fn:length(requestScope.movies) - pageScope.loop.index}</td>
			<td>	
			<a href="${pageScope.url }">
			${pageScope.movie.movieTitle}</a></td>
			<td>${pageScope.movie.runningTime}</td>
			<td>${pageScope.movie.director}</td>
			<td>${pageScope.movie.gradeNo}</td>
			<td>${pageScope.movie.nationNo}</td>
		</tr>
	
</c:forEach>
  </table>  
   <br><br>
 </body>
</html>