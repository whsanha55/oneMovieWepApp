<%-- detailArticle.jsp --%>
<%@ page contentType="text/plain; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
<!doctype html>
<html>
 <head>
  <meta charset="UTF-8">
  <title>게시글 상세조회 화면</title>
 </head>
 <body> <script src="../../js/jquery-3.2.1.min.js"></script> 
<script>
	    $(document).ready(function() {
	    	
			$('#sendBtn').on('click', function() {
				$.ajax({
					url: '${pageContext.request.contextPath}/user/movie/findMovie.do'
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
							htmlStr += "<td>" + data[i].movieTitle + "</td>";
							htmlStr += "<td>" + data[i].runningTime + "</td>";
							htmlStr += "<td>" + data[i].director + "</td>";
							htmlStr += "<td>" + data[i].gradeAge+ "</td>";
							htmlStr += "<td>" + data[i].nationName+ "</td>";
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
 			<td>이름</td> 	
 			<td>감독</td>	
 			<td>상영시간</td> 
 			<td>등급</td>	
 			<td>국가</td>	
 			<td>줄거리</td>		
 			<td>${requestScope.movie.movieTitle }</td>
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
 					<td>${pageScope.actor.actorName }<br>${pageScope.actor.role.roleName} | ${pageScope.actor.characterName }역</td>
 					
 			</c:forEach>
 		</tr>
 		</table> 	
 	</c:if>
 </body>
</html>
