<%-- addScheduleForm.jsp --%>
<%-- 여기에서 addTurnForm.do로 이동해야함 --%>
<%@page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>상영 일정 추가</title>
 <script src ="${pageContext.request.contextPath }/js/jquery-3.2.1.min.js"></script>
  <script>
	  $(document).ready(function(){
		  $("button").on("click",function(){
			  if($(this).attr('id') == 'saveBtn'){
				  $('#form').attr('action', '${pageContext.request.contextPath }/admin/theater/addTurnForm.do');
			  } 		  
			  $('#form').submit();
		  });
	  });
  </script>
</head>
<body>
	<div>영화제목</div>
	<form id = "form" method = "get">
		<select id = "movieTitle">
			<option>a</option>
		</select>
		
		<fieldset>
		<legend id= "legend1">지점</legend>
			<input name = "selectAll" type = "checkbox">전체 선택<br>
			<input name = "gangnam" type = "checkbox">강남
			<input name = "samsung" type = "checkbox">삼성
			<input name = "hongik" type = "checkbox">홍대
		</fieldset>
		
		<div>상영관</div><%--지점 부분 ajax처리 해줘야 한다. 또한, 지금으 --%>
		<fieldset>
		<legend id= "legend2">지점</legend>
			<input id = "selectAll" type = "checkbox">전체 선택<br>
			<input name = "1" type = "checkbox">1
			<input name = "2" type = "checkbox">2
			<input name = "3" type = "checkbox">3
			<input name = "4" type = "checkbox">4
			<input name = "5" type = "checkbox">5
		</fieldset>
		상영 시작일<input type = "date" name ="startDate"><br>
		상영 종료일<input type = "date" name ="endDate"><br><br>
		
		  <button type="button" id="saveBtn">저장하기</button>
	  </form>
</body>
</html>