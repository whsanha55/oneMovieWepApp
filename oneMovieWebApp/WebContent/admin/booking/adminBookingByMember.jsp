<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script>
	$(document).ready(function() {
	
	$('#btn').on('click', function() {
			if($('#keyword').val() == "") {
				alert("키워드를 입력하세요");
			} else {
				goPage(1);
			}
		});
	
		function goPage(currentPageNo) {
			$.ajax({
				
				url : '${pageContext.request.contextPath}/adminBookingAjax.do',
				method : 'POST',
				data : {
					keyfield : $('#keyfield').val() ,
					keyword: $('#keyword').val()
					
				},
				dataType : 'json',
				success : function(data) {
					$('#bookingListTable').find('.trBookingList').remove();
					for(var i=0; i<data.bookingList.length;i++) {
						
						var text = "<td> " + (i+1) +  "</td>";
						text += "<td> " +data.bookingList[i].memberNo +  "</td>";
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
						
						$('#bookingListTable').append("<tr class='trBookingList'>" +text + "</tr>");
						
					}
				},
				error : function(jqXHR) {
					alert(jqXHR.status);
					console.log(jqXHR);
				}
			});
		}
	});
</script>
	<div><a href="${pageContext.request.contextPath }/adminBookingByMember.do">회원 검색</a></div>
	<div><a href="${pageContext.request.contextPath }/adminBookingByTheater.do">극장  검색</a></div>
	
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
                <th>순번</th>
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
        
 