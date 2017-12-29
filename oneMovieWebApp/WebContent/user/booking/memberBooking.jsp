<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
	#movieFieldSet, #theaterFieldSet {
		display: inline;
		width: 100px;
		margin-bottom: 10px;
	}
	#dateFieldSet {
		displaty: inline-block;
		float: right;
		margin-right: 20px;
	}
	
	#selectOptionMovie, #selectOptionTheater, #dateFileSet div {
		width: 180px;
		height: 242.32px;
	}
	
	#movieFieldSet select, #theaterFieldSet select {
		text-align:center;
	}
	
	#theaterFieldSet option {
		height: 50px;
		font-size: 18px;
	}
	
	#turnFieldSet {
		margin-top:20px;
		text-align: left;
	}
	
	#turnFieldSet > div {
	border-bottom : 5px dotted darkgrey;
	}
	
	#turnFieldSet div:last-child {
		border: none;
	}
	#turnFieldSet span {
		font-size : 20px;
		float: left;
		
	}
	#turnFieldSet>span {
		margin-left: 230px;
	}
	
	#turnFieldSet button {
		margin-left: 20px;
		margin-top: 5px;
		margin-bottom:5px;
	}
	#turnFieldSet div:last-child button {
		margin-bottom:0px;
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
	
	legend {
		text-align: center;
		font-size : 20px;
	}
	
</style>

<script>
	var movieSelectedName = "";
	var theaterSelectedName = "";
	var dateSelectedName = "";
	var turnSelectedName = "";
	
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
					if(data.movieList[i].movieNo == "${requestScope.movieNo}") {
						movieText += "<option value='" + data.movieList[i].movieNo +"' selected>" + data.movieList[i].movieTitle ;
						movieSelectedName = data.movieList[i].movieTitle;
						console.log(movieSelectedName);
						
					} else {
						movieText += "<option value='" + data.movieList[i].movieNo +"'>" + data.movieList[i].movieTitle ;
						
					}
				}
				movieText += "</select>";
				
				$('#movieFieldSet').html(movieText); 
				$('#resultTable').find('tr:nth-child(1)').find('td:nth-child(2)').text(movieSelectedName);
				
				//한번에 영화,지점,날짜를 다 가지고 페이지 접근시
				if(movieSelectedName!="" && theaterSelectedName!="" && dateSelectedName!="") {
					getTurns($('#selectOptionMovie').val(), $('#selectOptionTheater').val(),dateSelectedName );
					console.log(1);
				}
				
			
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
				theaterText += "<select id='selectOptionTheater' size='3'>";
				for(var i = 0; i<data.theaterList.length;i++) {
					if(data.theaterList[i].theaterNo == "${requestScope.theaterNo}") {
						theaterText += "<option value='" + data.theaterList[i].theaterNo +"' selected>" + data.theaterList[i].theaterName ;
						theaterSelectedName = data.theaterList[i].theaterName ;
					} else {
						theaterText += "<option value='" + data.theaterList[i].theaterNo +"'>" + data.theaterList[i].theaterName ;
					}
				}
				theaterText += "</select>";
				$('#theaterFieldSet').html(theaterText);
				
				$('#resultTable').find('tr:nth-child(2)').find('td:nth-child(2)').text(theaterSelectedName);
				
				//한번에 영화,지점,날짜를 다 가지고 페이지 접근시
				if(movieSelectedName!="" && theaterSelectedName!="" && dateSelectedName!="") {
					getTurns($('#selectOptionMovie').val(), $('#selectOptionTheater').val(),dateSelectedName );
					console.log(1);
				}
				
			},
			error : function(jqXHR) {
				alert(jqXHR.status);
				console.log(jqXHR);
			}
		
		});
		
	}
	
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
				dateSelectedName = "${requestScope.screenDate}";
				
				$('#resultTable').find('tr:nth-child(3)').find('td:nth-child(2)').text(dateSelectedName);
				
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
			        defaultDate : "${requestScope.screenDate}" ,
			        onSelect: function(value) {
			        	if(dateSelectedName != value) {
							
					        dateSelectedName = value;
							$('#resultTable').find('tr:nth-child(3)').find('td:nth-child(2)').text(dateSelectedName);
				        	
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
				        	
				        	
			        	}
			       	 } , 
			    	beforeShowDay: function(date){
						if(data.dateList.length == 0) {
							return [false];
						}
			    		
			    		for (var i = 0; i < data.dateList.length; i++) {
			    			
			    			if($.inArray(convertDate(date),data.dateList) == -1) {
			    				return [false];
			    			}
			    		}
			    		return [true];
						
					}	
		       
			    });
				
				//한번에 영화,지점,날짜를 다 가지고 페이지 접근시
				if(movieSelectedName!="" && theaterSelectedName!="" && dateSelectedName!="") {
					getTurns($('#selectOptionMovie').val(), $('#selectOptionTheater').val(),dateSelectedName );
					console.log(1);
				}
				
			},
			error : function(jqXHR) {
				alert(jqXHR.status + "dateList");
				console.log(jqXHR);
			}
		
		});
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
	
	//date->yyyy/mm/dd string타입으로 변환시켜주는 함수 2개
	function pad(num) {
        num = num + '';
        return num.length < 2 ? '0' + num : num;
    }
	
	function convertDate(date) {
    	return date.getFullYear() + "/" + pad((date.getMonth() + 1)) + "/" + pad(date.getDate());

	}
	
	
	/////////////////////////////////////////////////////////////////
	$(document).ready(function() {

		
		movieTag(theaterSelectedName, dateSelectedName);
		theaterTag(movieSelectedName, dateSelectedName);
		dateTag(movieSelectedName, theaterSelectedName);
	
		
			
		
		 $('#movieFieldSet').on('click','select',function() {

			//빈공간 클릭 방지  및 같은 값 반복 클릭시 방지용 if
			if(movieSelectedName != $(this).find('option:selected').text()) {
				
					movieSelectedName = $(this).find('option:selected').text();
					
				if(theaterSelectedName != "" && dateSelectedName != "") {
					var date = convertDate($('#datepicker1').datepicker("getDate"));
					dateTag($(this).val(),$('#selectOptionTheater').val());
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
					movieTag($(this).val() , date);
					
				} else {
					movieTag($(this).val());
					$('#datepicker1').datepicker("destroy");
					dateTag(null,$(this).val());
					
				}
				
				$('#resultTable').find('tr:nth-child(2)').find('td:nth-child(2)').text(theaterSelectedName);
				
			}
		});
		
		
		
		
		
		
	
		
		
		
		
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
			
		
			$('input[name=movieSelectedName]').val(movieSelectedName);
			$('input[name=theaterSelectedName]').val(theaterSelectedNameWithScreenName);
			$('input[name=dateSelectedName]').val(dateSelectedName);
			$('input[name=turnSelectedName]').val(turnSelectedName);
			
			
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
     	<span>영화, 지점, 날짜를 선택해 주세요</span>
     </fieldset>
     
     <form  action="${pageContext.request.contextPath }/auth/memberBookingSelectSeat.do" method="post">
     	<input type="hidden" name="turnNo" >
     	<input type="hidden" name="movieSelectedName" >
     	<input type="hidden" name="theaterSelectedName" >
     	<input type="hidden" name="dateSelectedName" >
     	<input type="hidden" name="turnSelectedName" >
     	<input type="submit" value="좌석선택하기"> 
     </form>
  
     <table id="resultTable">
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
