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
		
		//버튼클릭 조회
		$('#btn').on('click',function() {
			
			if($('#screenCheckboxDiv').find('input:checked').length == 0) {
				alert('상영관 또는 회차를 선택하셔야 조회 가능합니다!');
			} else {
				
				
				$('#turnCheckboxDiv').find('input:checked').each(function() {
					turnNoArray.push($(this).val());
				});
				
				if(turnNoArray.length ==0) {
					$('#screenCheckboxDiv').find('input:checked').each(function() {
						screenNoArray.push($(this).val());
					});
				}
				
				bookingList(1);
				
				
				
			}
			
		});
		
	});
	
	
	var turnNoArray = [];
	var screenNoArray = [];
	
	//date->yyyy/mm/dd string타입으로 변환시켜주는 함수 2개
	function pad(num) {
        num = num + '';
        return num.length < 2 ? '0' + num : num;
    }
	
	function convertDate(date) {
    	return date.getFullYear() + "/" + pad((date.getMonth() + 1)) + "/" + pad(date.getDate());

	}
	
	
	
	function bookingList(currentPageNo) {
		var totalCount = 0;		//총 회원 수
		var countPerPage = 10;   //한 페이지당 보여주는 회원 수
		var pageSize = 2;		//페이지 리스트에 게시되는 페이지 수
		var startRow = 0;
		var endRow = 0;
		$.ajax({
			url: '${pageContext.request.contextPath}/adminBookingCountAjax2.do' 
			,
			data: {
				screenNoArray : screenNoArray.join() ,
				turnNoArray : turnNoArray.join() ,
				screenDate : convertDate($('#datepicker').datepicker("getDate"))
			}
			,
			type: 'POST'
			,
			cache: false
			,
			dataType: 'json'
			,
			success: function (data, textStatus, jqXHR) {
				var html = "";

				if(data.countList > 0){ //총 회원 수가 1명 이상인 경우
					totalCount = data.countList;
					startRow = (currentPageNo - 1) * countPerPage + 1;
					endRow = currentPageNo * countPerPage;
					if(endRow > totalCount) {
						endRow = totalCount;
					}	
				}
				
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
						//페이징 처리
						jqueryPager({
							pageSize: countPerPage,
							pageBlock: pageSize,
							currentPage: currentPageNo,
							pageTotal: totalCount
						});
					} ,
					error : function(jqXHR) {
						alert(jqXHR.status);
						console.log(jqXHR);
					}
				});
				
			} ,
			error: function(jqXHR) {
				alert('에러!!:' + jqXHR.status);
			}
		});
		
		
		//페이징 처리
		function jqueryPager(subOption) {
			
			var currentPage = subOption.currentPage;   //총 페이지수(1)
			var pageSize = subOption.pageSize;         //한 페이지에 보여줄 게시글 수(3)
			var pageBlock = subOption.pageBlock;       //페이지 블록 수(2)
			var pageTotal = subOption.pageTotal;       //총 게시글 수 (9)
			
			if(!pageSize) pageSize = 3;
			if(!pageBlock) pageBlock = 2;
			
			var pageTotalCnt = Math.ceil(pageTotal/pageSize);
			var pageBlockCnt = Math.ceil(currentPage/pageBlock);
			var sPage, ePage;
			
			var html ="";
			
			if(pageBlock > 1) {
				sPage = (pageBlockCnt-1) * pageBlock + 1;
			} else {
				sPage = 1;
			}
			
			if((pageBlockCnt * pageBlock) >= pageTotalCnt) {
				ePage = pageTotalCnt;
			} else {
				ePage = pageBlockCnt * pageBlock;
			}
			

			if(sPage > 1) {
				html += '<a onclick="bookingList(' + 1 + ');">[처음]		</a>';
				html += '<a onclick="bookingList(' + (sPage - pageBlock) + ');">[이전]	</a>';
			}
			
			for(var i=sPage; i<=ePage; i++) {
				if(currentPage == i) {
					html += "     " + i + "     ";
				} else {
					html += '<a onclick="bookingList(' + i + ');">' + i + '</a>';
				}
			}				

			if (ePage < pageTotalCnt) {
				html += '<a onclick="bookingList(' + (ePage+1) + ');">   [ 다음 ]   </a>';
				html += '<a onclick="bookingList(' + pageTotalCnt + ');">    [ 끝 ]</a>';
			}		
			
			
			$('#paging').empty().append(html);
		
		}//end of jqueryPager
	}
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
                
        <div id="paging"></div>