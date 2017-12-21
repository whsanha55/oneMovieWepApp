<%-- listScheduleTurnForm.jsp --%>
<%-- 삭제 버튼시 removeSchedule.do로 이동 --%>
<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix = "fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>상영 일정 조회</title>

</head>
<body>
	<table border = "1" id = "scheduelTurnListTable">
		<tr>
			<th><input type ="checkbox" id = "selectAllScheduleTurn"></th>
			<th>영화관 지점</th>
			<th>상영일</th>
			<th>상영관</th>
			<th>영화제목</th>
			<th>회차</th>
		</tr>
		 <c:forEach var ="schedule" items ="${requestScope.screenSchedule }" varStatus="loop"> 
			<tr>
				<td><input type ="checkbox" id ="selectScheduleTurn>"></td>
				<td>${ pageScope.schedule.theaterName} </td>	
				<td>${ pageScope.schedule.screenDate} </td>	
				<td>${ pageScope.schedule.screenName} </td>	
				<td>${ pageScope.schedule.movieTitle} </td>
				<td>
				<c:forEach var="turn" items="${ pageScope.schedule.turns}">
				 	
					{${ pageScope.turn.startTime} ~ ${ pageScope.turn.endTime}} 
				</c:forEach> 
				</td>
			</tr>
		</c:forEach>
		
	</table>
	<button type = "button" id = "removeTurn">삭제</button>
</body>
</html>