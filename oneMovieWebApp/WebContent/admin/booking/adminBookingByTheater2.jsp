<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="${pageContext.request.contextPath }/js/jquery-ui.min.css">
<script src="${pageContext.request.contextPath }/js/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath }/js/jquery-ui.min.js"></script>
<script>


	$(document).ready(function() {
		
		$("#datepicker").datepicker({
	    	dateFormat: 'yy년mm월dd일',
	        prevText: '이전 달',
	        nextText: '다음 달',
	        monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
	        monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
	        dayNames: ['일', '월', '화', '수', '목', '금', '토'],
	        dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
	        dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
	        showMonthAfterYear: true,
	        yearSuffix: '년' ,
	        currentText: '오늘 날짜' ,

	        onSelect: function(value) {
	        	$('#screenCheckboxDiv').find('input').prop("checked",false);
	        	$('#turnCheckboxDiv').empty();
	        	
	        }
	    });
				
		
		//지점선택시-> 상영관 
		$(':checkbox[name=theaterNo]').on('change', function() {
			var theaterNo = $(this).val();
			var text = "<span>" + $(this).parent().text() + " :</span>";
			if($(this).prop('checked')) {
				$.ajax({
					url : '${pageContext.request.contextPath}/adminBookingAjax2.do' ,
					method : 'POST',
					data : {
						theaterNo : $(this).val()
					},
					dataType : 'json',
					success : function(data) {
						for(var i=0;i<data.screenList.length;i++) {
							text += " <label><input type='checkbox' name='screenNo' value='" 
										+ data.screenList[i].screenNo + "'>" + data.screenList[i].screenName + "</label> ";
							
						}
							$('#screenCheckboxDiv').append("<div class='screen' id='theater_" + theaterNo + "'>" +  text + "</div> ");
							
						
					},
					error : function(jqXHR) {
						alert(jqXHR.status);
						console.log(jqXHR);
					}
				});
			} else {
				
				$('#screenCheckboxDiv').find('div[id=theater_' + $(this).val() +']').remove();
			}
		});
		
		//date->yyyy/mm/dd string타입으로 변환시켜주는 함수 2개
		function pad(num) {
            num = num + '';
            return num.length < 2 ? '0' + num : num;
        }
		
		function convertDate(date) {
        	return date.getFullYear() + "/" + pad((date.getMonth() + 1)) + "/" + pad(date.getDate());

		}
		
		//상영관->회차
		$('#screenCheckboxDiv').on('change',':checkbox[name=screenNo]', function() {

			var screenNo = $(this).val();
			var text = "<span>" + $(this).closest('span').text() + $(this).parent().text() + " :</span>";
			if($('#datepicker').datepicker("getDate") == null) {
				alert("날짜를 선택하세요!!");
				$(this).prop("checked",false);
				
			} else {
				if($(this).prop('checked')) {
					 
					$.ajax({
						url : '${pageContext.request.contextPath}/adminBookingAjax3.do' ,
						method : 'POST',
						data : {
							screenNo : screenNo,
							screenDate : convertDate($('#datepicker').datepicker("getDate"))
							
						},
						dataType : 'json',
						success : function(data) {
							for(var i=0;i<data.length;i++) {
								text += " <label><input type='checkbox' name='turnNo' value='" 
											+ data[i].turnNo + "'>" + data[i].startTime + "~" + data[i].endTime + "</label> ";
								
							}
								$('#turnCheckboxDiv').append("<div class='screen' id='screen_" + screenNo + "'>" +  text + "</div> ");
						} ,
						error : function(jqXHR) {
							alert(jqXHR.status);
							console.log(jqXHR);
						}
					});
					
				} else {
					$('#turnCheckboxDiv').find('div[id=screen_' + $(this).val() +']').remove();
				}
			}
		});
		
		$('#btn').on('click',function() {
			
			if($('#screenCheckboxDiv').find('input:checked').length == 0) {
				alert('상영관 또는 회차를 선택하셔야 조회 가능합니다!');
			} else {
				var turnNoArray = [];
				var screenNoArray = [];
				
				$('#turnCheckboxDiv').find('input:checked').each(function() {
					turnNoArray.push($(this).val());
				});
				
				if(turnNoArray.length ==0) {
					$('#screenCheckboxDiv').find('input:checked').each(function() {
						screenNoArray.push($(this).val());
					});
				}
				
				//console.log('1   ' + turnNoArray);
				//console.log('2   ' +screenNoArray);
				$.ajax({
					url : '${pageContext.request.contextPath}/adminBookingAjax4.do' ,
					method : 'POST',
					data : {
						screenNoArray : screenNoArray.join() ,
						turnNoArray : turnNoArray.join() ,
						screenDate : convertDate($('#datepicker').datepicker("getDate"))
						
					},
					dataType : 'json',
					success : function(data) {
						$('#bookingListTable').find('.trBookingList').remove();
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
							
							$('#bookingListTable').append("<tr class='trBookingList'>" +text + "</tr>");
							
						}
					} ,
					error : function(jqXHR) {
						alert(jqXHR.status);
						console.log(jqXHR);
					}
				});
				
			}
			
		});
		
	});
</script>

	<div><a href="${pageContext.request.contextPath }/adminBookingByMember.do">회원 검색</a></div>
	<div><a href="${pageContext.request.contextPath }/adminBookingByTheater.do">극장  검색</a></div>
	
	<br>
	<br>
	<br>
	<fieldset id="dateFieldSet">
	<legend>날짜</legend>
	<input type="text" id="datepicker" placeholder="날짜를 선택하세요">  
	</fieldset>
	
	<fieldset id="theaterFieldSet">
	<legend>극장</legend>
	<div id="theaterCheckboxDiv">
		<span>지점 :</span> 
		<c:forEach var="theater" items="${requestScope.theaterList }">
			<label><input type="checkbox" name="theaterNo" value="${pageScope.theater.theaterNo }">${pageScope.theater.theaterName }</label>
		</c:forEach>
	</div>
	<div id="screenCheckboxDiv">
		
	</div>
	<div id="turnCheckboxDiv">
	
	</div>
	</fieldset>
	
	<button id="btn">조회하기</button>
	
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
            </tr>
            
        </table>