<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style >
	
</style>
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
	        onSelect: function(value) {
	        	console.log(value);
	        }
	    });
		
		//지점선택시-> 상영관 
		$(':checkbox[name=theaterNo]').on('change', function() {
			var theaterNo = $(this).val();
			if($(this).prop('checked')) {
				$.ajax({
					url : '${pageContext.request.contextPath}/adminBookingAjax2.do' ,
					method : 'POST',
					data : {
						theaterNo : $(this).val()
						
					},
					dataType : 'json',
					success : function(data) {
						var text = "";
						for(var i=0;i<data.screenList.length;i++) {
							text += " <label><input type='checkbox' name='screenNo' value='" 
										+ data.screenList[i].screenNo + "'>" + data.screenList[i].screenName + "</label> ";
							
						}
						
							$('#screen').append("<div class='screen' id='theater_" + theaterNo + "'>" + text + "</div> ");
							
						
					},
					error : function(jqXHR) {
						alert(jqXHR.status);
						console.log(jqXHR);
					}
				});
			} else {
				
				$('#screen').find('div[id=theater_' + $(this).val() +']').remove();
			}
		});
		
		/* 
		//상영관--> 회차
		$('#screen').on('change','input[name=screenNo]' ,function() {
			
				if($(this).prop('checked') {
					$.ajax() {
						
					}
				} else {
					
				}
		});
	 */
		
	});
</script>
</head>
<body>
	<div><a href="${pageContext.request.contextPath }/adminBookingByMember.do">회원 검색</a></div>
	<div><a href="${pageContext.request.contextPath }/adminBookingByTheater.do">극장  검색</a></div>
	
	<br>
	<br>
	<br>
	
	<input type="text" id="datepicker" placeholder="날짜를 선택하세요">  
	<div id="theater">
		<span>지점</span> 
		<label><input type="checkbox" name="theaterNo" value="1"> 강남</label>
        <label><input type="checkbox" name="theaterNo" value="2"> 홍대</label>
        <label><input type="checkbox" name="theaterNo" value="3"> 삼성</label>
        
	</div>
	<div id="screen"><span>상영관</span></div>
	<div id="turn"><span>회차</span></div>
	hi~
	
</body>
</html>