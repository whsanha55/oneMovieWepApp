<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/jquery-ui.min.css">
<script src="${pageContext.request.contextPath }/js/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath }/js/jquery-ui.min.js"></script>
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
				var movieText = "<select id='selectOptionMovie' size='10'>";
				for(var i = 0; i<data.movieList.length;i++) {
				movieText += "<option value='" + data.movieList[i].screenNo +"'> " + data.movieList[i].screenName ;
				}
				movieText += "</select>";
				$('#movieFieldSet').append(movieText);
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
				var theaterText = "<select id='selectOptionTheater' size='10'>";
				for(var i = 0; i<data.theaterList.length;i++) {
					theaterText += "<option value='" + data.theaterList[i].theaterNo +"'> " + data.theaterList[i].theaterName ;
				}
				theaterText += "</select>";
				$('#theaterFieldSet').append(theaterText);
			},
			error : function(jqXHR) {
				alert(jqXHR.status);
				console.log(jqXHR);
			}
		
		});
		
	}
	
	function dateTag(movieNo, theaterNo ) {
		function pad(num) {
            num = num + '';
            return num.length < 2 ? '0' + num : num;
        }
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
			    	dateFormat: 'yy-mm-dd',
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
			        	
			        	$(this).datepicker("option",{disabled: true});

			        	var tempdate = new Date(value);
			        	var convertDate = tempdate.getFullYear() + "/" + pad((tempdate.getMonth() + 1)) + "/" + pad(tempdate.getDate());
			        	console.log(convertDate);
			        	$('#selectOptionMovie').remove();
						movieTag(null,convertDate);
						
			        	$('#selectOptionTheater').remove();
			        	theaterTag(null,convertDate);
			        } , 
			    	beforeShowDay: function(date){
						
			    		var m = date.getMonth(), d = date.getDate(), y = date.getFullYear();
			    		for (i = 0; i < data.dateList.length; i++) {
			    			if($.inArray(y + '/' +pad(m+1) + '/' + pad(d),data.dateList) == -1) {
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
	
	
	$(document).ready(function() {
		movieTag();
		theaterTag();
		dateTag();
		
		$('#movieFieldSet').on('click','select',function() {
			console.log($(this).val());
			var theaterChecked = $('#selectOptionTheater').val();
			var dateChecked = $( "#datepicker1" ).datepicker( "isDisabled" );
			console.log(theaterChecked);
			
			if(theaterChecked) {
				$('#datepicker1').datepicker("destroy");
				dateTag($(this).val(),theaterChecked);
			} else if (dateChecked) {
				$('#selectOptionTheater').remove();
				theaterTag($(this).val(),null);
				
				/////해결하자~
			} else {
			
			$('#selectOptionTheater').remove();
			theaterTag($(this).val(),null);
			
			$('#datepicker1').datepicker("destroy");
			dateTag($(this).val(),null);
			
			}
		});
		
		
		$('#theaterFieldSet').on('click','select',function() {
			
			$('#selectOptionMovie').remove();
			movieTag($(this).val());
			
			$('#datepicker1').datepicker("destroy");
			dateTag(null, $(this).val());
		});
		
		
		
		//날짜는 ajax안에 있음
		
		
		
	});
</script>
</head>
<body>
	
     <fieldset id="movieFieldSet" >
            <legend>영화제목</legend>
           
     </fieldset>
     <fieldset id="theaterFieldSet" >
            <legend>지점</legend>
           
     </fieldset>
     <fieldset id="dateFieldSet" >
            <legend>날짜</legend>
           <div id="datepicker1"></div>
 

     </fieldset>
     

</body>
</html>