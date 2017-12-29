<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<style>
	#selectPeople {
		margin-left: auto;
		margin-right: auto;
		margin-bottom: 20px;	
		text-align: center;	
	}
	
	#selectBookingSeat {
		margin-left: auto;
		margin-right: auto;
		width:80%;
		border: 3px groove burlywood;
		
		
	}
	#selectBookingSeat div {
		text-align: center;
	}
	#selectBookingSeat div:first-child {
		font-size: 50px;
		border-bottom : 10px solid black;
	}
	#selectBookingSeat div:last-child {
		font-weight: bolder;
		margin-right: 120px;
		font-size : 25px;
	}
	#selectBookingSeat span {
		margin-left : 10px;
	}
	.lineName {
		margin-right: 50px;
		width:60px;
		font-size: 35px;
	}
	
	#selectBookingSeat a {
		display:inline-block;
	}
	
	form {
		text-align:center;
		margin-top: 20px;
	}
	
	#resultTable {
		border: 1px solid black;
		text-align:center;
		width:60%;
		margin-left: auto;
		margin-right : auto;
		
	}
	#resultTable td {
		width:50%;
		border: 1px solid black;
	}
	
</style>
`	

<script>
	$(document).ready(function() {
		var seatNum = 0;		//희망하는 관람인원 수
		var selectedSeatNum = 0;		//실제 선택한 관람인원 수
		var selectedSeatNo = [];
		var selectedSeatName = [];
		
		$('#bookingNumVal').on('change',function() {
			
			if(seatNum <= $(this).val() || selectedSeatNum <= $(this).val()) {
				seatNum = $(this).val();
				$('#resultTable').find('tr:nth-child(6)').find('td:nth-child(2)').text(seatNum * 10000);
				
				
			} else {
				if(confirm('선택하신 좌석보다 적은 인원입니다 \n 계속 진행하시겠습니까? ')) {
					seatNum = $(this).val();
					selectedSeatNum = 0;
					selectedSeatNo = [];
					selectedSeatName = [];
					$('#resultTable').find('tr:nth-child(5)').find('td:nth-child(2)').text("");
					$('#resultTable').find('tr:nth-child(6)').find('td:nth-child(2)').text(seatNum * 10000);
					$('.seatNo').removeAttr('style');
				} else {
					$('#bookingNumVal').val(seatNum);					
				}
				
			}
		});
		
		$('.seatNo').on('click',function(event) {
			if(seatNum == 0) {
				alert("인원을 선택해 주세요!!!");
				return false;
			} else if(seatNum > selectedSeatNum) {
				if(!selectedSeatName.includes($(this).find('span').text()))  {
					
				selectedSeatNum++;
				$(this).css('backgroundColor','aqua');
				//$(this).off('click');
				
				selectedSeatNo.push($(this).find('a').attr('name'));
				selectedSeatName.push($(this).find('span').text());
				selectedSeatName.sort();
				
				$('#resultTable').find('tr:nth-child(5)').find('td:nth-child(2)').text(selectedSeatName.join());
				}
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
	<div id="selectPeople">
		관람인원
	<select id="bookingNumVal">
		<option value="0"> 인원을 선택하세요
		<option value="1"> 1명
		<option value="2"> 2명
		<option value="3"> 3명
		<option value="4"> 4명
	</select>
	</div>
	
	<div id="selectBookingSeat">
		<div> SCREEN</div>
		<div>
			<c:forEach var="seat" items="${requestScope.seatList}">
			
				<c:if test="${pageScope.seat.firstSeatLine }">
					<span class='lineName'>${fn:substring(pageScope.seat.seatName,0,1) }열</span>	
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
		
		<button id="payFormButton">결제하기</button>
	</form>
	
	<table  id="resultTable">
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
	
