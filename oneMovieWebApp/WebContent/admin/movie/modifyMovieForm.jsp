<%-- modifyMovieForm.jsp --%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="domain.movie.MovieVO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<!doctype html>
<html>
 <head>
  <meta charset="UTF-8">
  <title>게시글 수정 화면</title>
 </head>
 <body>
 	<form action="${pageContext.request.contextPath }/admin/movie/modifyMovie"	
 	enctype="multipart/form-data" method="post">
 		<input type="hidden" name="movieNo" value="${requestScope.movie.movieNo }">
 		영화사진 : <input type="file" name="upload"><br>
 		영화사진 : <input type="file" name="upload"><br>
 		영화사진 : <input type="file" name="upload"><br>
 		제    목 : <input type="text" name="movieTitle" size="20" value="${requestScope.movie.movieTitle }"><br>
 		<!-- 장    르 : <input type="checkbox" name="genreNo" value="1" >액션
			     <input type="checkbox" name="genreNo" value="2">로맨스
				 <input type="checkbox" name="genreNo" value="3">코미디
				 <input type="checkbox" name="genreNo" value="4">공포
				 <input type="checkbox" name="genreNo" value="5">SF
				 <input type="checkbox" name="genreNo" value="6">판타지
				 <input type="checkbox" name="genreNo" value="7">애니<br> -->
 		상영시간 : <input type="number" name="runningTime" size="20" value="${requestScope.movie.runningTime }"><br>
 		감    독 : <input type="text" name="director" size="20" value="${requestScope.movie.director }"><br>
 		등    급 : <select name="gradeNo">
			          <option value="1">전체 관람가</option>
					  <option value="2">12세 관람가</option>
					  <option value="3">15세 관람가</option>
					  <option value="4">19세 관람가</option>
	      		 </select><br>
 		국    가 : <select name="nationNo">
			          <option value="1">미국</option>
					  <option value="2">한국</option>
					  <option value="3">영국</option>
					  <option value="4">일본</option>
					  <option value="5">대만</option>
	      		 </select><br><br><br><br>
	      		 
	      		 
	    <%--  배우사진 : <input type="file" name="uploadactor"><br>
	     이    름 : <input type="text" name="actorName" size="20" value="${requestScope.movie.actors.actorName }"><br> 
	     역    할 : <select name="roleNo">
			          <option value="1">주연</option>
					  <option value="2">조연</option>
					  <option value="3">엑스트라</option>
	      		 </select><br>
	     캐릭터이름 : <input type="text" name="characterName" size="20"><br><br><br> --%>
 </body>
</html>





















