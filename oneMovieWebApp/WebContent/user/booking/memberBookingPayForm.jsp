<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="${pageContext.request.contextPath }/js/jquery-3.2.1.min.js"></script>
<script>
		
	$(document).ready(function() {
		
		$(':text').on('input',function() {
			$(this).val($(this).val().replace(/[^0-9]/g,""));
			if($(this).val().length >4) {
				$(this).val($(this).val().substring(0,4));
			}
		});
		
		
		$('#bookingBtn').on('click',function() {
			if($('select[name=cardCompany]').val() != 0) {
				if(confirm("결제하시겠습니까?")) {
					return true;
				} else {
					return false;
				}
			} else {
				alert("카드사를 선택해주세요!!");
				return false;
			}
		});
		
		
	});
	
</script>
</head>
<body>
	<form action="memberExecuteBooking.do" method="post">
		<div>
		<span>카드사 선택</span>
		<select name="cardCompany">
			<option value="0"> 카드사 선택 
			<option value="1"> 신한 
			<option value="2"> 국민 
			<option value="3"> 농협 
			<option value="4"> 우리
		</select>
		은행 
		<br>
		<span>카드번호</span>
		<input type="text" name="serialCardNum1" > - 
		<input type="text" name="serialCardNum2"> -
		<input type="text" name="serialCardNum3"> -
		<input type="text" name="serialCardNum4"> 
		<input type="hidden" name="price" value="20000">
		
		</div>
	
	
	<button id="bookingBtn">결제</button>
	</form>
	
	<div>
		좌석번호 : 
		<c:forEach var="seat" items="${sessionScope.bookingVO.bookingSeats }" >
			${pageScope.seat.seatNo}
		</c:forEach>
	</div>
</body>
</html>