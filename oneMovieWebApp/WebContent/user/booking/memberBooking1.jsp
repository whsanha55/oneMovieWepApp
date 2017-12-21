<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/jquery-ui.min.css">
<script src="${pageContext.request.contextPath }/js/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath }/js/jquery-ui.min.js"></script>
<script>

$(function() {
    $("#datepicker1").datepicker({
    	dateFormat: 'yy-mm-dd',
        prevText: '이전 달',
        nextText: '다음 달',
        monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
        monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
        dayNames: ['일', '월', '화', '수', '목', '금', '토'],
        dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
        dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
        showMonthAfterYear: true,
        yearSuffix: '년' ,
        minDate : 0 ,
        onSelect: function(value) {
        	alert(value);
        } , 
    	beforeShowDay: function(date){
			
			var day = date.getDay();

			console.log(day);
			return [(day != 0 && day != 6)];
			

	}	
    });
    
   
    
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
           
    	 <div id='datepicker1'></div>
    	 <div id='datepicker2'></div>
     </fieldset>
     

</body>
</html>