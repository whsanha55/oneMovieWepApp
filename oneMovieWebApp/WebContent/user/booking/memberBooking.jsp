<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<script>
	function movieTag (theaterNo, date) {
		$.ajax({
			url : '${pageContext.request.contextPath}/movieByTheaterAndDateAjax.do',
			method : 'POST',
			data : {
				theaterNo : theaterNo ,
				date : date
				
			},
			dataType : 'json',
			success : function(data) {
				var movieText = "<legend>영화제목</legend>";
				movieText += "<select id='selectOptionMovie' size='10'>";
				for(var i = 0; i<data.movieList.length;i++) {
				movieText += "<option value='" + data.movieList[i].screenNo +"'>" + data.movieList[i].screenName ;
				}
				movieText += "</select>";
				$('#movieFieldSet').html(movieText);
			
			},
			error : function(jqXHR) {
				alert(jqXHR.status);
				console.log(jqXHR);
			}
		
		});
	}
	
	function theaterTag (movieNo, date) {
		$.ajax({
			url : '${pageContext.request.contextPath}/theaterByMovieAndDateAjax.do',
			method : 'POST',
			data : {
				movieNo : movieNo , 
				date : date
				
			},
			
			dataType : 'json',
			success : function(data) {
				var theaterText = " <legend>지점</legend>";
				theaterText += "<select id='selectOptionTheater' size='10'>";
				for(var i = 0; i<data.theaterList.length;i++) {
					theaterText += "<option value='" + data.theaterList[i].theaterNo +"'>" + data.theaterList[i].theaterName ;
				}
				theaterText += "</select>";
				$('#theaterFieldSet').html(theaterText);
			},
			error : function(jqXHR) {
				alert(jqXHR.status);
				console.log(jqXHR);
			}
		
		});
		
	}
	
	
	
	$(document).ready(function() {
		
		
		var movieSelectedName = "";
		var theaterSelectedName = "";
		var dateSelectedName = "";
		var turnSelectedName = "";
		movieTag();
		theaterTag();
		dateTag();
		
		$('#movieFieldSet').on('click','select',function() {

			//빈공간 클릭 방지  및 같은 값 반복 클릭시 방지용 if
			if(movieSelectedName != $(this).find('option:selected').text()) {
				
					movieSelectedName = $(this).find('option:selected').text();
				if(theaterSelectedName != "" && dateSelectedName != "") {
					var date = convertDate($('#datepicker1').datepicker("getDate"));
					getTurns($(this).val() , $('#selectOptionTheater').val() , date);
					
				} else if(theaterSelectedName != "") {
					$('#datepicker1').datepicker("destroy");
					dateTag($(this).val(),$('#selectOptionTheater').val());
					
				} else if(dateSelectedName != "") {
					var date = convertDate($('#datepicker1').datepicker("getDate"));
					theaterTag($(this).val(), date);
					
				} else {
					theaterTag($(this).val());
					$('#datepicker1').datepicker("destroy");
					dateTag($(this).val());
					
				}

				$('#resultTable').find('tr:nth-child(1)').find('td:nth-child(2)').text(movieSelectedName);
			}
				
			
		});
		
		
		$('#theaterFieldSet').on('click','select',function() {
			
			//빈공간 클릭 방지  및 같은 값 반복 클릭시 방지용 if
			if(theaterSelectedName != $(this).find('option:selected').text()) {
				
				theaterSelectedName = $(this).find('option:selected').text();
				
				if(movieSelectedName !="" && dateSelectedName != "") {
					var date = convertDate($('#datepicker1').datepicker("getDate"));
					getTurns($('#selectOptionMovie').val(), $(this).val() , date);
					
				} else if(movieSelectedName != "") {
					$('#datepicker1').datepicker("destroy");
					dateTag($('#selectOptionMovie').val() ,$(this).val() );
					
				} else if(dateSelectedName != "") {
					var date = convertDate($('#datepicker1').datepicker("getDate"));
					movieTag($(this).val() , date)
					
				} else {
					movieTag($(this).val());
					$('#datepicker1').datepicker("destroy");
					dateTag(null,$(this).val());
					
				}
				
				$('#resultTable').find('tr:nth-child(2)').find('td:nth-child(2)').text(theaterSelectedName);
				
			}
		});
		
		
		//날짜 ajax
		function dateTag(movieNo, theaterNo ) {
		
			$.ajax({
				url : '${pageContext.request.contextPath}/dateByMovieAndTheaterAjax.do',
				method : 'POST',
				data : {
					movieNo : movieNo , 
					theaterNo : theaterNo 
					
					
				},
				
				dataType : 'json',
				success : function(data) {
					
					
					$("#datepicker1").datepicker({
				    	dateFormat: 'yy/mm/dd',
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
				        	if(dateSelectedName != value) {
								
						        dateSelectedName = value;
					        	console.log('dateSelectedName : ' + dateSelectedName);
					        	
					        	if(movieSelectedName != "" && theaterSelectedName != "") {
					        		getTurns($('#selectOptionMovie').val() , $('#selectOptionTheater').val() , value);
					        		
					        	} else if(movieSelectedName != "") {
					        		theaterTag($('#selectOptionMovie').val(), value);
					        		
					        	} else if(theaterSelectedName != "") {
					        		movieTag($('#selectOptionTheater').val(), value);
					        		
					        	} else {
					        		movieTag(null,value);
					        		theaterTag(null,value);
					        		
					        	}
					        	$('#resultTable').find('tr:nth-child(3)').find('td:nth-child(2)').text(dateSelectedName);
				        	}
				        } , 
				    	beforeShowDay: function(date){
							
				    		
				    		for (var i = 0; i < data.dateList.length; i++) {
				    			
				    			if($.inArray(convertDate(date),data.dateList) == -1) {
				    				return [false];
				    			}
				    		}
				    		return [true];
							
						}	
			       
				    });
				},
				error : function(jqXHR) {
					alert(jqXHR.status + "dateList");
					console.log(jqXHR);
				}
			
			});
		}
		
		//date->yyyy/mm/dd string타입으로 변환시켜주는 함수 2개
		function pad(num) {
            num = num + '';
            return num.length < 2 ? '0' + num : num;
        }
		
		function convertDate(date) {
        	return date.getFullYear() + "/" + pad((date.getMonth() + 1)) + "/" + pad(date.getDate());

		}
		
		
		function getTurns(movieNo, theaterNo, date) {
			
			$.ajax({
				url : '${pageContext.request.contextPath}/getTurnListAjax.do',
				method : 'POST',
				data : {
					movieNo : movieNo ,
					theaterNo : theaterNo ,
					date : date
					
				},
				dataType : 'json',
				success : function(data) {
					var turnText = "<legend>상영시간</legend>";
					for(var i=0;i<data.length;i++) {
						turnText += "<div>";
						turnText += "<span>" + data[i].screenName + "</span>";
						for(var j=0;j<data[i].turn.length;j++) {
							turnText += "<button class='turnNo' value='" + data[i].turn[j].turnNo + "'>";
							turnText += data[i].turn[j].startTime  + "~" + data[i].turn[j].endTime + "</button>"; 
						}
						turnText += "</div>";
					}
					
					$("#turnFieldSet").html(turnText);
				},
				error : function(jqXHR) {
					alert(jqXHR.status);
					console.log(jqXHR);
				}
			});
		}
		
		
		$('#turnFieldSet').on('click','.turnNo',function() {
			turnSelectedName = $(this).text();

			$('.turnNo').removeAttr('style');
			$(this).css('backgroundColor','darkcyan');

			$('input[name=turnNo]').val($(this).val());
			console.log('영화' + movieSelectedName + ' 극장 ' + theaterSelectedName + ' 날짜 ' + dateSelectedName);
			console.log('회차' + $(this).text());
			
			var theaterSelectedNameWithScreenName =  theaterSelectedName + $(this).parent().find('span').text()
			$('#resultTable').find('tr:nth-child(2)').find('td:nth-child(2)').text(theaterSelectedNameWithScreenName);
			$('#resultTable').find('tr:nth-child(4)').find('td:nth-child(2)').text($(this).text());
			
			$('form').prepend("<input type='hidden' name='movieSelectedName' value='"+ movieSelectedName + "'>");
			$('form').prepend("<input type='hidden' name='theaterSelectedName' value='"+ theaterSelectedNameWithScreenName + "'>");
			$('form').prepend("<input type='hidden' name='dateSelectedName' value='"+ dateSelectedName + "'>");
			$('form').prepend("<input type='hidden' name='turnSelectedName' value='"+ turnSelectedName + "'>");
			
			
			
		});
		
		$('form').on('submit',function() {
			
			if($(this).find('[name=turnNo]').val() == "" ) {
				alert('상영 시간을 선택해주세요!!');
				event.preventDefault();
			} 
		});
		
	});
	

	
</script>

     <fieldset id="movieFieldSet" >
           
           
     </fieldset>
     <fieldset id="theaterFieldSet" >
            
           
     </fieldset>
     <fieldset id="dateFieldSet" >
            <legend>날짜</legend>
           <div id="datepicker1"></div>
 

     </fieldset>
     
     <fieldset id="turnFieldSet">
     	<legend>상영시간</legend>
     	위 3개를 고르거라 닝겐
     </fieldset>
     
     <form  action="memberBookingSelectSeat.do" method="post">
     	<input type="hidden" name="turnNo" >
     	<input type="submit" value="좌석선택하기"> 
     </form>
     
     <table border="1" id="resultTable">
     	<tr>
     		<td>영화</td>
     		<td></td>
     	</tr>
     	<tr>
     		<td>극장</td>
     		<td></td>
     	</tr>
     	<tr>
     		<td>시간</td>
     		<td></td>
     	</tr>
     	<tr>
     		<td>회차</td>
     		<td></td>
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
