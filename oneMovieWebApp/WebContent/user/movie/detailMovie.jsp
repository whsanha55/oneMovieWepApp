<%-- detailArticle.jsp --%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
<!doctype html>
<html>
 <head>
  <meta charset="UTF-8">
  <title>게시글 상세조회 화면</title>
 </head>
 <body>
 	<table border="1">
 		<tr>
 			<td>번호</td>
 			<td>${requestScope.movie.movieNo }</td>
 		</tr>
 		<tr>
 			<td>이름</td> 			
 			<td>${requestScope.movie.movieTitle }</td>
 		</tr>
 		<tr>
 			<td>감독</td>
 			<td>${requestScope.movie.director}</td>
 		</tr>
 		<tr>
 			<td>상영시간</td> 			
 			<td>${requestScope.movie.runningTime}분</td>
 		</tr>
 		<tr>
 			<td>등급</td>
 			<td>${requestScope.movie.grade.gradeAge }</td>
 		</tr>
 		<tr>
 			<td>국가</td>
 			<td>${requestScope.movie.nation.nationName }</td>
 		</tr>
 		<tr height="100">
 			<td>줄거리</td> 		 			
 			<td colspan="3">${requestScope.movie.story}</td> 			
 		</tr>
 	</table> 		
 	<br><br>
 	<c:if test="${fn:length(requestScope.movie.actors) > 0 }">
 		<table border="1">
 		<tr> 
 			<c:forEach var="actor" items="${requestScope.movie.actors }" varStatus="loop">
 					 <c:if test="${loop.index%3==0}">
 					 		</tr><tr>
 					 </c:if>
 					 <td><img src = "${pageContext.request.contextPath}/${contextPath}/image/park2.jpg"></td>
 					<td>${pageScope.actor.role.roleName}, ${pageScope.actor.actorName }, ${pageScope.actor.characterName }</td>
 					
 			</c:forEach>
 		</tr>
 		</table> 	
 	</c:if>
 </body>
</html>















