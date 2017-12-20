<%-- listArticle.jsp --%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="domain.movie.MovieVO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%--
	//request영역에 바인딩된 게시글 목록을 조회하다.
	ArrayList<ArticleVO> articles = (ArrayList<ArticleVO>)request.getAttribute("articles");
--%>

<!doctype html>
<html>
 <head>
  <meta charset="UTF-8">
  <title>영화 목록 조회 화면</title>
 </head>
 <body>
 <h3>영화 목록 조회 화면</h3>
<script src="js/jquery-3.2.1.min.js"></script> 
 <script>
	    $(document).ready(function() {
			$('#sendBtn').on('click', function() {
				$.ajax({
					url: '${pageContext.request.contextPath}/findMovie.do'
					,
					method: 'GET'
					,
					data: $('#form').serialize()
					, 
					dataType: 'json'
					,
					success: function(data) {
						$('#table').find('tr:not(:first)').remove();
						var htmlStr ="";
						for(var i=0; i<data.length; i++) {
							htmlStr += "<tr>";
							htmlStr += "<td>" + data[i].movieNo + "</td>";
							htmlStr += "<td>" + data[i].movieTitle + "</td>";
							htmlStr += "<td>" + data[i].director + "</td>";
							htmlStr += "<td>" + data[i].runningTime + "</td>";
							htmlStr += "<td>" + data[i].gradeNo + "</td>";
							htmlStr += "<td>" + data[i].nationNo + "</td>";
							htmlStr += "<td>" + data[i].genreNo + "</td>";
							htmlStr += "<td>" + data[i].actorNo + "</td>";
							htmlStr += "</tr>";
							
							$(htmlStr).appendTo('#table');
							htmlStr = "";
						}
					}
					, 
					error: function(jqXHR) {
						alert('Error : ' + jqXHR.status);							
					}				
					
				});
			});
		});
  </script>

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
		<c:url var="url" value="/detailArticle.do" scope="page" >
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
   <form id="form">
   		검색조건 : <select name="keyfield">
   						<option value="MovieTitle">제목</option>
   						<option value="Director">감독</option>
   						<option value="all">전체</option>
   					</select>
   					&nbsp;&nbsp;
   					<input type="text" name="keyword" size="20">
   					&nbsp;&nbsp;
   					<button id="sendBtn" type="button">검색</button>
   </form>

 </body>
</html>