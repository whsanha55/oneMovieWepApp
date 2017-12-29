<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
	#listHrefDiv{
		margin-top: 20px;
		margin-bottom: 20px;
	}
	#listHrefDiv{
		text-decoration: underline;
	}
	#listHrefDiv a:hover {
		color: black;
		margin: 20px;
	}
	#listTable {
		border: 1px solid black;
		text-align:center;
		width:100%;
		
	}
	#listTable td, #listTable th {
		border: 1px solid black;
	}
	
	#listTable th:first-child {
		width: 30px;
	}
	#listTable th:nth-child(2) {
		width:100px;
	}
	

	#pagingDiv {
		text-align: center;
	}
</style>
<script>
	$(document).ready(function() {
	
		
		$('.refundBtn').on('click',function(event) {
			var ticketNo = $(this).closest('tr').find(':nth-child(2)').text();
			//event.preventDefault();
			//console.log($(this).closest('tr').find(':nth-child(5)').text());
			$.ajax({
				url : '${pageContext.request.contextPath}/memberBookingIsRefundableAjax.do',
				method : 'POST',
				data : {
					ticketNo : ticketNo
					
				},
				dataType : 'json',
				success : function(data) {
					console.log(data);
					console.log(data.success);
					if(data.success) {
						var isRefund = confirm("영화 예매는 30분전까지 취소 가능합니다. \n정책에 따라 환불은 24시간 후 100%의 금액으로 환불됩니다. \n예매를 취소 하시겠습니까?");
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
									location.href = "${pageContext.request.contextPath }/auth/memberBookingListCancel.do";
									
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


	<h3>MY 예매내역</h3>
	<div>예매확인/취소 예매하신 영화 내역과 취소내역을 확인할 수 있습니다.</div>
	
	<div id='listHrefDiv'>
	<a href="${pageContext.request.contextPath }/auth/memberBookingListCurr.do">My 예매내역</a>
	<a href="${pageContext.request.contextPath }/auth/memberBookingListPast.do">지난 내역</a>
	<a href="${pageContext.request.contextPath }/auth/memberBookingListCancel.do">취소 내역</a>
	</div>
	<table id='listTable'>
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
			<td>${requestScope.paging.num + pageScope.loop1.count }</td>
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
		</tr>
	</c:forEach>
	</table>
	
	<div id='pagingDiv'>
		<%-- 페이지 네비게이션 처리 --%>
	<c:if test="${requestScope.paging.prevPage >= 1 }">
		<c:url var="prevUrl" value="memberBookingListCurr.do" scope="page">
			<c:param name="currentPage" value="${requestScope.paging.prevPage }"/>
		</c:url>
		<a href="${pageScope.prevUrl }">[이전]</a>&nbsp;&nbsp;
	</c:if>	
	<c:if test="${requestScope.paging.prevPage < 1 }">
		[이전] &nbsp;&nbsp;
	</c:if>

	<c:forEach var="i" begin="${requestScope.paging.startPage }" end="${requestScope.paging.endPage }" step="1">
		<c:if test="${requestScope.paging.currentPage == pageScope.i }">
			${pageScope.i }&nbsp;&nbsp;		
		</c:if>
		<c:if test="${requestScope.paging.currentPage != pageScope.i }">
			<c:url var="currentPageUrl" value="memberBookingListCurr.do" scope="page">
				<c:param name="currentPage" value="${pageScope.i }"/>		
			</c:url>
			<a href="${pageScope.currentPageUrl }">${pageScope.i }</a>&nbsp;&nbsp;		
		</c:if>
	</c:forEach>
	<c:if test="${requestScope.paging.pageBlock < requestScope.paging.totalPage }">
		<c:url var="nextUrl" value="memberBookingListCurr.do" scope="page">
			<c:param name="currentPage" value="${requestScope.paging.nextPage }"/>
		</c:url>
		<a href="${pageScope.nextUrl }">[다음]</a>&nbsp;&nbsp;
	</c:if>	
	<c:if test="${requestScope.paging.pageBlock >= requestScope.paging.totalPage }">
		[다음] &nbsp;&nbsp;
	</c:if>
	</div>