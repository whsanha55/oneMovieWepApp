<%-- removeMovieForm.jsp --%>
<%@ page contentType="text/html; charset=utf-8" %>

<!doctype html>
<html>
 <head>
  <meta charset="UTF-8">
  <title>영화 삭제 화면</title>
 </head>
 <body>
 	<h3>삭제하고자 게시글 번호를 입력하세요</h3>
 	<form action="${pageContext.request.contextPath}/removeMovie.do" method="post"> 
 		<input type="hidden" name="no" value="${param.movie.movieNo }">	    
 		비밀빈호 : <input type="password" name="pwd" size="20"><br> 	
 		<button type="submit">삭제</button>&nbsp;
 		<button type="reset">취소</button>	
  	</form>	
 </body>.
</html>








