<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
.linkDiv {
		display: inline-block;
		margin-right: 50px;
		margin-bottom: 50px;
	}

#divTable {
	height: 600px;
    overflow-x:scroll;

}
#bookingListTable {
	text-align: center;
	word-break: keep-all;
	border-collapse: collapse;
}
#bookingListTable th {
    background-color: lightseagreen;
    color: white;
 	   padding-left: 10px;
 	   padding-right: 10px;
 	   padding-top: 10px;
 	   padding-bottom: 10px;
}
#bookingListTable th:nth-child(6) {
		min-width:200px;
	}
#bookingListTable td {
		padding-left : 5px;
		padding-right : 5px;
	}

#paging {
    text-align: center;
    margin: auto;
}

</style>
<script>
	$(document).ready(function() {
		
		
		var attrmemberNo;
		if((attrmemberNo = "${requestScope.memberNo}") != "") {
			$('#keyword').val(attrmemberNo);
			bookingList(1);
		}
	
		$('#btn').on('click', function() {
				if($('#keyword').val() == "") {
					alert("키워드를 입력하세요");
				} else {
					bookingList(1);
				}
			});
	});
		
		function bookingList(currentPageNo) {
			var totalCount = 0;		//총 회원 수
			var countPerPage = 10;   //한 페이지당 보여주는 회원 수
			var pageSize = 2;		//페이지 리스트에 게시되는 페이지 수
			var startRow = 0;
			var endRow = 0;
			
			$.ajax({
				url: '${pageContext.request.contextPath}/adminBookingCountAjax.do' 
				,
				data: {
					keyfield: $('#keyfield').val() ,
					keyword: $('#keyword').val() 
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
						
						url : '${pageContext.request.contextPath}/adminBookingAjax.do',
						method : 'POST',
						data : {
							keyfield : $('#keyfield').val() ,
							keyword: $('#keyword').val() ,
							startRow: startRow	,
							endRow: endRow	
							
							
						},
						dataType : 'json',
						success : function(data) {
							$('#bookingListTable').find('.trBookingList').remove();
							for(var i=0; i<data.bookingList.length;i++) {
								
								var text = "<td> " + (i+1) +  "</td>";
								text += "<td> " +data.bookingList[i].memberNo +  "</td>";
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
								text += "<td>" + data.bookingList[i].withdrawDate + "</td>";
								
								$('#bookingListTable').append("<tr class='trBookingList'>" +text + "</tr>");
								
							}
							
							//페이징 처리
							jqueryPager({
								pageSize: countPerPage,
								pageBlock: pageSize,
								currentPage: currentPageNo,
								pageTotal: totalCount
							});
							
							$('#bookingListTable').find('.trBookingList').each(function() {
								
								var tempDate = new Date($(this).find(':nth-child(7)').text());
								tempDate.setHours($(this).find(':nth-child(8)').text().substring(0,2));
								tempDate.setMinutes($(this).find(':nth-child(8)').text().substring(3,5));
								
								if($(this).find('td:last-child').text() != "") {	//취소
									$(this).closest('tr').css('backgroundColor','#FF6699');
								} else if(tempDate > new Date()) {		//상영예정
									$(this).closest('tr').css('backgroundColor','aqua');										
								}
								
							});
						},
						error : function(jqXHR) {
							alert(jqXHR.status);
							console.log(jqXHR);
						}
					});
				}
				
				,
				error: function(jqXHR) {
					alert("에러: " + jqXHR.status);
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
	<div class='linkDiv'><a href="${pageContext.request.contextPath }/admin/adminBookingByMember.do">회원 검색</a></div>
	<div class='linkDiv'><a href="${pageContext.request.contextPath }/admin/adminBookingByTheater.do">극장  검색</a></div>

     
           <select id="keyfield" >
            <option value="memberNo">회원번호</option>
            <option value="memberName">회원이름</option>
            </select>
            <input type="text" id="keyword">
            <button type="button" id="btn">조회</button>
      
        <div id='divTable'>
        <table border="1" id="bookingListTable">
        
            <tr>
                <th>순번</th>
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
                <th>취소일</th>         
            </tr>
            
        </table>
        </div>
        
        
        	<div id="paging"></div>
 