<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="js/jquery-3.2.1.min.js"></script>
<script>
	//윤년여부를 체크한다
	$(document).ready(function() {
	 function isLeapYear(year) {
		
			if( (0 == (year % 4) && 0 != (year %100)) || 0 == year%400 ) {
				return true;
			} else {
				return false;
			}
		 }
		
			var days = [31,28,31,30,31,30,31,31,30,31,30,31];
			if(isLeapYear(new Date().getFullYear())) {
				days[1] = 29;
			}
	
			var day = new Date(new Date().getFullYear(), new Date().getMonth(), 1).getDay();
			console.log(day);
			//에러 뿜!!!!!!!!!!!!!!!!!!!!!!!!
			console.log(new Date(new Date().getFullYear(), new Date().getMonth(), 1));
			var count = 0;
			var htmlStr = "";
			htmlStr += "<table border='1'>";
			htmlStr += "<tr>";
			htmlStr += "<th>일</th><th>월</th><th>화</th><th>수</th><th>목</th><th>금</th><th>토</th>";
			htmlStr += "</tr>";
	
			htmlStr += "<tr>";
	
	
	
			for(var i = 0; i<day;i++) {
				htmlStr += "<td></td>";
				count++;
			}
			for(var j = 1; j<=days[new Date().getMonth()]; j++) {
				
					htmlStr += "<td>" + j + "</td>";
				
				count++;
				if(count == 7) {
					htmlStr += "</tr><tr>";
					count = 0;
				}
			}
	
			for(var k = count; k<7 && k!=0; k++) {
				htmlStr += "<td></td>";
			}
			htmlStr += "</tr></table>";
			
		$('#calendar').append(htmlStr);
				

		
	});
 </script>
</head>
<body>
	
     <fieldset id="movieFieldSet" >
            <legend>영화제목</legend>
           <select id="movie" size="10">
          	  <c:forEach var="movie" items="${requestScope.movieList }" >
          	  		<option value="${pageScope.movie.movieNo }"> ${pageScope.movie.movieTitle }
          	  </c:forEach>
    	  </select> 
     </fieldset>
     <fieldset id="screenFieldSet" >
            <legend>지점</legend>
           <select id="screen" size="10">
          	  <c:forEach var="theater" items="${requestScope.theaterList }" >
          	  		<option value="${pageScope.theater.theaterNo }"> ${pageScope.theater.theaterName }
          	  </c:forEach>
    	  </select> 
     </fieldset>
     <fieldset id="dateFieldSet" >
            <legend>날짜</legend>
           <select id="date" size="10">
          	  <option value="memberNo">회원번호</option>
            	<option value="memberName">회원이름</option>
            	
    	  </select> 
    	  <div id="calendar"></div>
     </fieldset>
     
     
</body>
</html>