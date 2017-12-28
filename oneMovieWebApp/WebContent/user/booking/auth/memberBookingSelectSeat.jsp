<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<script>
	$(document).ready(function() {
		var seatNum = 0;
		var selectedSeatNum = 0;
		var selectedSeatNo = [];
		var selectedSeatName = [];
		
		$('#bookingNumVal').on('change',function() {
			
			if(seatNum <= $(this).val()) {
				seatNum = $(this).val();
				$('#resultTable').find('tr:nth-child(6)').find('td:nth-child(2)').text(seatNum * 10000);
				
				
			} else {
				seatNum = $(this).val();
				selectedSeatNum = 0;
				selectedSeatNo = [];
				selectedSeatName = [];
				$('#resultTable').find('tr:nth-child(5)').find('td:nth-child(2)').text("");
				$('#resultTable').find('tr:nth-child(6)').find('td:nth-child(2)').text(seatNum * 10000);
				$('.seatNo').removeAttr('style');
				
				
			}
		});
		
		$('.seatNo').on('click',function(event) {
			if(seatNum == 0) {
				alert("인원을 선택해 주세요!!!");
				return false;
			} else if(seatNum > selectedSeatNum) {
				selectedSeatNum++;
				$(this).css('backgroundColor','black');
				//$(this).off('click');
				
				selectedSeatNo.push($(this).find('a').attr('name'));
				selectedSeatName.push($(this).find('span').text());
				selectedSeatName.sort();
				
				$('#resultTable').find('tr:nth-child(5)').find('td:nth-child(2)').text(selectedSeatName.join());
			} else {
				alert("이미 좌석을 모두 선택하셨습니다!!");
				return false;
			}
		});
		
		$('#payFormButton').on('click',function() {
			
			if(seatNum == 0) {
				alert("인원을 선택해 주세요!!!");
				event.preventDefault();
			} else if (seatNum != selectedSeatNum) {
				alert("선택하신 인원과 좌석 선택 수가 일치하지 않습니다!!");
				event.preventDefault();
			} else {
				$('form').prepend("<input type='hidden' name='bookingSeats' value='"+ selectedSeatNo.join() + "'>");
				$('form').prepend("<input type='hidden' name='seatSelectedName' value='"+ selectedSeatName.join() + "'>");
				$('form').prepend("<input type='hidden' name='priceSelectedName' value='"+ seatNum * 10000 + "'>");
			}

		});
	});
	

</script>

	<select id="bookingNumVal">
		<option value="0"> 인원을 선택하세요
		<option value="1"> 1명
		<option value="2"> 2명
		<option value="3"> 3명
		<option value="4"> 4명
	</select>
	
	<div id="selectBookingSeat">
		<div> SCREEN</div>
		<div>
			<c:forEach var="seat" items="${requestScope.seatList}">
			
				<c:if test="${pageScope.seat.firstSeatLine }">
					<span>${fn:substring(pageScope.seat.seatName,0,1) }열</span>	
				</c:if>
				
				<c:choose>
					<c:when test="${pageScope.seat.booked }">
						<span>${pageScope.seat.seatName }</span>
					</c:when>
					<c:when test="${!pageScope.seat.booked }">
						<span class="seatNo">
							<a href="#" onclick="return false;" name="${pageScope.seat.seatNo }">
								<span>${pageScope.seat.seatName }</span>
							</a>
						</span>
					</c:when>
				</c:choose>
				<c:if test="${pageScope.seat.lastSeatLine }">
					<br>
				</c:if>			
			</c:forEach>
		</div>
	
	</div>
	
	<div id="bookingInfos" >
		<div id="bookingSeats"></div>
	</div>
	
	
	<form action="${pageContext.request.contextPath }/auth/memberBookingPayForm.do" method="post"  >
		
		<button id="payFormButton">결제</button>
	</form>
	
	<table border="1" id="resultTable">
		<tr>
     		<td>영화</td>
     		<td>${sessionScope.bookingSn.movieSn }</td>
     	</tr>
     	<tr>
     		<td>극장</td>
     		<td>${sessionScope.bookingSn.theaterSn }</td>
     	</tr>
     	<tr>
     		<td>시간</td>
     		<td>${sessionScope.bookingSn.dateSn }</td>
     	</tr>
     	<tr>
     		<td>회차</td>
     		<td>${sessionScope.bookingSn.turnSn }</td>
     	</tr>
     	<tr>
     		<td>좌석</td>
     		<td></td>
     	</tr>
     	<tr>
     		<td>가격</td>
     		<td></td>
     	</tr>
     	
	</table>
	
