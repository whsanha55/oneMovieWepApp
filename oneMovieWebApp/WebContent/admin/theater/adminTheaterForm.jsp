<%-- adminTheaterForm.jsp --%>
<%-- 관리자 극장관리 메인 페이지 --%>
<%-- 검색 선택 시, listScheduleTurnForm.do로 이동 --%>
<%-- 추가 선택 시, addScheduleForm.do로 이동 --%>
<%@page contentType="text/html; charset=utf-8" %>
<%@page import="java.util.ArrayList"%>
<%@page import="domain.*"%>
<%@taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!doctype html>
<html>
 <head>
  <meta charset="UTF-8">
  <title>상영일정관리 메인 화면</title>
  <script src ="${pageContext.request.contextPath }/js/jquery-3.2.1.min.js"></script>
  <script>
	  $(document).ready(function(){
		  $("#searchBtn").on("click",function(){
			  $.ajax({
					 url:"${pageContext.request.contextPath}/admin/theater/adminTheaterView.do",
					 method:"GET",
					 async:true,
					 dataType:"json",
					 data: $("#form").serialize(),
					 success:function(data){
							var htmlStr = "";
							for(var i= 0;i<data.length;i++){
								htmlStr +="<table border ="+1+"><tr>";
								htmlStr +="<td><input type=" + "checkbox" + " id = " + "selectScheduleTurn" + "></td>";
								htmlStr +="<td>" + data[i].TheaterName +"</td>";
								htmlStr +="<td>" + data[i].ScreenDate +"</td>";
								htmlStr +="<td>" + data[i].ScreenName +"</td>";
								htmlStr +="<td>" + data[i].MovieTitle +"</td>";
								htmlStr +="<td>" + data[i].StartTime +"</td>";
								htmlStr +="<td>" + data[i].EndTime +"</td>";
								htmlStr +="</tr></table>";
								$(htmlStr).appendTo("#div");
							
								console.log(htmlStr);
								htmlStr ="";
							}
					 },
					 error:function(jqXHR){
						 alert(jqXHR.status);
					 }
				})
			  /*
			  if($(this).attr("id") == "searchBtn"){
				  $('#form').attr('action', '${pageContext.request.contextPath }/admin/theater/listScheduleTurnForm.do');
				   
			  } else  {
				  $('#form').attr('action', '${pageContext.request.contextPath }/admin/theater/addScheduleForm.do');
			  }			  
			  $('#form').submit(); 
			  이 코드는 버튼 선택시 페이지 이동임
			  지금 필요한 것은 버튼 선택시 ajax처리 해야함 */
		  });
	  });
  </script>

 </head>
 <body>
   	 <h3>상영일정</h3> 
   <form method="get" id="form">
     <select name="keyfield">
       <option value="theaterName">지점명</option>
	   <option value="movieName">영화명</option>
     </select>
	   <input name = "keyword" type="text" >	   
	   <button id = "searchBtn" type="button" >검색</button>
	   <button id = "addBtn" type="button" >추가</button>
   </form>
   <div id = "div"></div>
 </body>
</html>

