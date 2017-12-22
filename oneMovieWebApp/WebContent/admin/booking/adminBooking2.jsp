<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style >
	
</style>
<script src="${pageContext.request.contextPath }/js/jquery-3.2.1.min.js"></script>
<script>
	$(document).ready(function() {

		
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
	<div><a href="#">회원으로 검색</a></div>
	<div><a href="#">조건 검색</a></div>
	
	<br>
	<br>
	<br>
	
	<input type="date" name="screenDate" >
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