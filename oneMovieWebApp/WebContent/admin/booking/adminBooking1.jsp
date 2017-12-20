<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script src="js/jquery-3.2.1.min.js"></script>
<script >
	$(document).ready(function() {
	
	$('#btn').on('click', function() {
			
		$.ajax({

				url : '${pageContext.request.contextPath}/adminBookingAjax.do',
				method : 'POST',
				data : {
					keyfield : $('#keyfield').val() ,
					keyword: $('#keyword').val()
					
				},
				dataType : 'json',
				success : function(data) {
					for(var i=0; i<data.bookingList.length;i++) {
						
						var text = "<td> " +data.bookingList[i].memberNo +  "</td>";
						text += "<td> " + data.bookingList[i].memberName + "</td>";
						text += "<td>" + data.bookingList[i].theaterName + "</td>";
						text += "<td>" + data.bookingList[i].screenName + "</td>";
						text += "<td>" + data.bookingList[i].movieTitle + "</td>";
						text += "<td>" + data.bookingList[i].screenDate + "</td>";
						text += "<td>" + data.bookingList[i].startTime + "~" + data.bookingList[i].endTime + "</td>";
						text += "<td>";
						for(var j=0;j<data.bookingList[i].seatNames.length;j++) {
							text+=data.bookingList[i].seatNames[j];
							if(j != data.bookingList[i].seatNames.length-1) {
								text += "/";
							}
							
						}
						text += "</td>";
						text += "<td>" + data.bookingList[i].ticketNo + "</td>";
						text += "<td>" + data.bookingList[i].paymentCode + "</td>";
						text += "<td>" + data.bookingList[i].withdrawDate + "</td>";
						
						
						$('#bookingListTable').append("<tr class='status" + data.bookingList[i].status + "'>" +text + "</tr>");
						
					}
				},
				error : function(jqXHR) {
					alert(jqXHR.status);
					console.log(jqXHR);
				}
			});
		});
	});
</script>
<body>
	<div><a href="#">회원으로 검색</a></div>
	<div><a href="#">조건 검색</a></div>
	
	<br>
	<br>
	<br>
	
     
           <select id="keyfield" >
            <option value="memberNo">회원번호</option>
            <option value="memberName">회원이름</option>
            </select>
            <input type="text" id="keyword">
            <button type="button" id="btn">조회</button>
      
        
        <table border="1" id="bookingListTable">
        
            <tr>
                <th>회원번호</th>
                <th>이름</th>
                <th>지점</th>
                <th>상영관</th>
                <th>영화제목</th>
                <th>상영날짜</th>
                <th>회차시간</th>
                <th>좌석번호</th>
                <th>예매번호</th>
                <th>승인코드</th>
                <th>취소일</th>         
            </tr>
            
        </table>
        
        
</body>
</html>