<%-- addTurnForm.jsp --%>
<%-- 여기에서 addSchedule.do로 이동해야함 --%>
<%@page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>회차 일정 추가</title>
<script src ="${pageContext.request.contextPath }/js/jquery-3.2.1.min.js"></script>
  <script>
	  $(document).ready(function(){
		  $("button").on("click",function(){
			  if($(this).attr("id") == "enrollTurn"){
				  $("#form").attr("action", "${pageContext.request.contextPath }/admin/theater/addSchedule.jsp");
			  } 		  
			  $("#form").submit();
		  });
	  });
  </script>
</head>
<body>
	<div>회차</div><br>
	<form id = "form" method ="get">
	<span id ="location">강남
		<table border="1">
			<tr>
				<td>날짜 들어가야 함</td>
				<td>회차 추가 하는 부분</td>
			</tr>
		</table>
		<button type = "button" id = "enrollTurn">등록</button>
		</span>
	</form>
</body>
</html>