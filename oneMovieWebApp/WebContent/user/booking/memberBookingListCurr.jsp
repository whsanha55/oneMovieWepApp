<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<<<<<<< HEAD
</head>
<body>

	<div>예매확인/취소 예매하신 영화 내역과 취소내역을 확인할 수 있습니다.</div>
	
	<div>
	<a href="${pageContext.request.contextPath }/memberBookingListCurr.do">My 예매내역</a>
	<a href="${pageContext.request.contextPath }/memberBookingListPast.do">지난 내역</a>
	<a href="${pageContext.request.contextPath }/memberBookingListCancel.do">취소 내역</a>
	</div>
	<table border="1">
	<tr>
		<th>NO</th>
		<th>예매번호</th>
		<th>영화명</th>
		<th>상영관</th>
		<th>좌석번호</th>
		<th>상영일시</th>
		<th>예매취소</th>
		
	</tr>
	<c:forEach var="booking" items="${requestScope.bookingList}" varStatus="loop1">
		<tr>
			<td>${pageScope.loop1.count }</td>
			<td>${pageScope.booking.ticketNo }</td>
			<td>${pageScope.booking.movieTitle }</td>
			<td>${pageScope.booking.theaterName } ${pageScope.booking.screenName }</td>
			<td>
			<c:forEach var="seat" items="${pageScope.booking.seatNames }" varStatus="loop2">
				${pageScope.seat }
				<c:if test="${!pageScope.loop2.last }">
					,
				</c:if>
			</c:forEach>
			</td>
			<td>${pageScope.booking.screenDate } ${pageScope.booking.startTime }</td>
			<td><button>예매취소</button></td>
=======
<script src="${pageContext.request.contextPath }/js/jquery-3.2.1.min.js"></script>
<script>
	$(document).ready(function() {
	
		
		$('.refundBtn').on('click',function(event) {
			var ticketNo = $(this).closest('tr').find(':nth-child(2)').text();
			//event.preventDefault();
			console.log($(this).closest('tr').find(':nth-child(5)').text());
			$.ajax({
				url : '${pageContext.request.contextPath}/memberBookingIsRefundableAjax.do',
				method : 'POST',
				data : {
					ticketNo : ticketNo
					
				},
				dataType : 'json',
				success : function(data) {
					
					if(data.success) {
						var isRefund = confirm("영화 예매는 30분전까지 취소 가능합니다. \n예매를 취소 하시겠습니까?");
						if(isRefund) {
							console.log("취소를 진행합니다");
							
							$.ajax({
								url : '${pageContext.request.contextPath}/memberBookingRefundAjax.do',
								method : 'POST',
								data : {
									ticketNo : ticketNo
									
								},
								dataType : 'json',
								success : function(data) {
									alert("예매취소가 완료되었습니다 \n 확인을 누르시면 취소 내역으로 이동합니다");
									location.href = "${pageContext.request.contextPath }/memberBookingListCancel.do";
									
								},
								error : function(jqXHR) {
									alert(jqXHR.status);
									console.log(jqXHR);
								}
							});
							
						} else {
							
						}
					} else {
						alert("상영시간 30분전부터 취소는 불가능합니다!!");
						
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
</head>
<body>
	<h3>MY 예매내역</h3>
	<div>예매확인/취소 예매하신 영화 내역과 취소내역을 확인할 수 있습니다.</div>
	
	<div>
	<a href="${pageContext.request.contextPath }/memberBookingListCurr.do">My 예매내역</a>
	<a href="${pageContext.request.contextPath }/memberBookingListPast.do">지난 내역</a>
	<a href="${pageContext.request.contextPath }/memberBookingListCancel.do">취소 내역</a>
	</div>
	<table border="1">
	<tr>
		<th>NO</th>
		<th>예매번호</th>
		<th>영화명</th>
		<th>상영관</th>
		<th>좌석번호</th>
		<th>상영일시</th>
		<th>예매취소</th>
		
	</tr>
	<c:forEach var="booking" items="${requestScope.bookingList}" varStatus="loop1">
	
		<tr>
			<td>${pageScope.loop1.count }</td>
			<td>${pageScope.booking.ticketNo }</td>
			<td>${pageScope.booking.movieTitle }</td>
			<td>${pageScope.booking.theaterName } ${pageScope.booking.screenName }</td>
			<td>
			<c:forEach var="seat" items="${pageScope.booking.seatNames }" varStatus="loop2">
				${pageScope.seat }
				<c:if test="${!pageScope.loop2.last }">
					,
				</c:if>
			</c:forEach>
			</td>
			<td>${pageScope.booking.screenDate } ${pageScope.booking.startTime }</td>
			<td>
	
			<button class="refundBtn">예매취소</button>
		
			</td>
>>>>>>> refs/remotes/origin/master
		</tr>
	</c:forEach>
	</table>
</body>
</html>