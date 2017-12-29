<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
	
	form {
		text-align: center;
		margin: 30px;
	}
	form input[name^=serialCardNum] {
		width:40px;
	}

	#bookingBtn {
		margin-left: 20px;
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

	<form action="${pageContext.request.contextPath }/auth/memberExecuteBooking.do" method="post">
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
		<button id="bookingBtn">결제하기</button>
		
	
	
	</form>
	
	
	
	<table id='resultTable'>
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
     		<td>${sessionScope.bookingSn.seatSn }</td>
     	</tr>
     	<tr>
     		<td>가격</td>
     		<td>${sessionScope.bookingSn.priceSn }</td>
     	</tr>
     	
	</table>
