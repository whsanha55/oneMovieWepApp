<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script src="${pageContext.request.contextPath }/js/jquery-3.2.1.min.js"></script>
<script>
	var seatNum = 0;
	var selectedSeatNum = 0;
	var selectedSeatNo = [];
	var selectedSeatName = [];
	$(document).ready(function() {
		$('#bookingNumVal').on('change',function() {
			seatNum = $(this).val();	
		});
		
		$('.seatNo').on('click',function(event) {
			if(seatNum == 0) {
				alert("�ο��� ������ �ּ���!!!");
				return false;
			} else if(seatNum > selectedSeatNum) {
				selectedSeatNum++;
				$(this).css('backgroundColor','black');
				$(this).off('click');
				selectedSeatNo.push($(this).find('a').attr('name'));
				selectedSeatName.push($(this).find('span').text());
				console.log(selectedSeatNo.join());
				$('input[name=bookingSeats]').val(selectedSeatNo.join());
				$('#bookingSeats').text(selectedSeatName.sort().join());
			} else {
				alert("�̹� �¼��� ��� �����ϼ̽��ϴ�!!");
				return false;
			}
		});
	});
	
	function clickNextPage() {
		if(seatNum == 0) {
			alert("�ο��� ������ �ּ���!!!");
			return false;
		} else if (seatNum != selectedSeatNum) {
			alert("�����Ͻ� �ο��� �¼� ���� ���� ��ġ���� �ʽ��ϴ�!!");
			return false;
		}
	}
</script>
</head>
<body>
	<select id="bookingNumVal">
		<option value="0"> �ο��� �����ϼ���
		<option value="1"> 1��
		<option value="2"> 2��
		<option value="3"> 3��
		<option value="4"> 4��
	</select>
	
	<div id="selectBookingSeat">
		<div> SCREEN</div>
		<div>
			<c:forEach var="seat" items="${requestScope.seatList}">
			
				<c:if test="${pageScope.seat.firstSeatLine }">
					<span>${fn:substring(pageScope.seat.seatName,0,1) }��</span>	
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
	
	
	<form action="memberBookingPayForm.do" method="post" onClick="return clickNextPage()" >
		<input type="hidden" name="bookingSeats" >
		
		<button>����</button>
	</form>
</body>
</html>