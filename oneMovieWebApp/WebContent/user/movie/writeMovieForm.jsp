
<%-- writeArticleForm.jsp --%>
<%@ page contentType="text/html; charset=utf-8" %>

<!doctype html>
<html>
 <head>
  <meta charset="UTF-8">
  <title>영화 입력 화면</title>
 </head>
 <body>
 	<form action="${pageContext.request.contextPath }/user/movie/writeMovie"	
 	enctype="multipart/form-data" method="post">
 		파    일 : <input type="file" name="upload"><br>
 		파    일 : <input type="file" name="upload"><br>
 		파    일 : <input type="file" name="upload"><br>
 		제    목 : <input type="text" name="movieTitle" size="20"><br>
 		상영시간 : <input type="number" name="runningTime" size="20"><br>
 		감    독 : <input type="text" name="director" size="20"><br>
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
	      		 
	     파    일 : <input type="file" name="uploadactor"><br>
	     이    름 : <input type="text" name="actorName" size="20"><br> 
	     역    할 : <select name="roleNo">
			          <option value="1">주연</option>
					  <option value="2">조연</option>
					  <option value="3">엑스트라</option>
	      		 </select><br>
	     캐릭터이름 : <input type="text" name="characterName" size="20"><br><br><br>
	     
 		<button type="submit">영화 등록</button>&nbsp;
 		<button type="reset">취소</button>	
  	</form>	
 </body>
</html>








