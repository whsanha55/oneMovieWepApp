<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="java.util.*" %>
<%@ page import="domain.member.MemberVO" %>



<!DOCTYPE html>
<html>
<head>
<title>회원 관리</title>
</head>
<script src="https://code.jquery.com/jquery-3.2.1.js"></script>
<script>
	$(document).ready(function(){
		
		$('#all').on('click', function(event){
			event.preventDefault();
			$('#keyfield').val(null);
			$('#keyword').val(null);
			$('#sortfield').val(null);
			memberList(1);
		});

		
		
		$('#search').on('click', function(event){
			event.preventDefault();
			$('#sortfield').val(null);
			memberList(1);
		});
		
		
		$('#asc').on('click', function(event){
			event.preventDefault();
			var sortword = $(this).val();
			memberList(1);
			
		});
		
		$('#desc').on('click', function(event){
			event.preventDefault();
			var sortword = $(this).val();
			memberList(1);
		});
		
		
		function memberList(currentPageNo) {
			var totalCount = 0;		//총 회원 수
			var countPerPage = 10;  //한 페이지당 보여주는 회원 수
			var pageSize = 10;		//페이지 리스트에 게시되는 페이지 수
			var startRow = 0;
			var endRow = 0;
			
			$.ajax({
				url: "${pageContext.request.contextPath}/manageMember.do" 
				,
				data: {
					keyfield: $('#keyfield :selected').val() ,
					keyword: $('#keyword').val()
				}
				,
				type: 'GET'
				,
				cache: false
				,
				dataType: 'json'
				,
				success: function (data, textStatus, jqXHR) {
					var html = "";
					if(data.searchCount > 0){ //총 회원 수가 1명 이상인 경우
						totalCount = data.searchCount;
						startRow = (currentPageNo - 1) * countPerPage + 1;
						endRow = currentPageNo * countPerpage;
						if(endRow > totalCount) {
							endRow = totalCount;
						}
						
						$.ajax({
							url: "${pageContext.request.contextPath}/manageMember.do" 
							,
							type: "GET"
							,
							dataType: 'json'
							,
							data: {
								keyfield: $('#keyfield').val() ,
								keyword: $('#keyword').val()	,
								startRow: startRow	,
								endRow: endRow
							},
							success: function(data, textStatus, jqXHR){
								//데이터 뿌리는 부분
								var htmlSr = "";
								for(var i=0; i<data.length; i++) {
									htmlStr += "<tr>";
									htmlStr += "<td>" + data[i].memberNo + "</td>"
									htmlStr += "<td>" + data[i].memberId + "</td>"
									htmlStr += "<td>" + data[i].memberPwd + "</td>"
									htmlStr += "<td>" + data[i].name + "</td>"
									htmlStr += "<td>" + data[i].gender + "</td>"
									htmlStr += "<td>" + data[i].phone + "</td>"
									htmlStr += "<td>" + data[i].email + "</td>"
									htmlStr += "<td>" + data[i].address1 + " " + data[i].address2 + "</td>"
									htmlStr += "<td>" + data[i].zipcode + "</td>"
									htmlStr += "<td>" + data[i].isWithdraw + "</td>"
									htmlStr += "<td>" + data[i].withdrawDay + "</td>"
									htmlStr += "</tr>";
									$(htmlStr).appendTo('table');
									htmlStr += "";
								}
								
								//페이징 처리
								jqueryPager({
									pageSize: countPerPage,
									pageBlock: pageSize,
									currentPage: currentPageNo,
									pageTotal: totalCount
								});
								
							},
							
							error: function(jqXHR, textStatus, error){
								alert("Error : " + jqXHR.status + "," + error);
								
							}
								
						}); //end of inner ajax
			

					} //end of if clause
				},
				
				error: function(jqXHR, textStatus, error) {
					alert("Error : " + jqXHR.status + " , " + error);	
				}
				
			}); //end of outer ajax
		
		} //end of memberSeach
		
		
		
		//페이징 처리
		function jqueryPager(subOption) {
			var currentPage = subOption.currentPage;
			var pageSize = subOption.pageSize;
			var pageBlock = subOption.pageBlock;
			var PageTotal = subOption.pageTotal;
		
			if(!pageSize) pageSize = 10;
			if(!pageBlock) pageBlock = 10;
			
			var pageTotalCnt = Math.ceil(pageTotal/pageSize);
			var PageBlockCnt = Math.ceil(currentPage/pageBlock);
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
				epage = pageBlockCnt * pageBlock;
			}
			
			if(sPage > 1) {
				html += '<a onclick="memberList(' + 1 + ');">[처음]		</a>';
				html += '<a onclick="memberList(' + pageTotalCnt + ');">[이전]	</a>';
			}
			
			$('#paging').empty().appen(html);
			
			
		
		}//end of jqueryPager
		
		
		
		
		
		
		
		
	});

</script> 
   
<body>
	<form>
		<select name="keyfield" id="keyfield">
			<option value="name">이름</option>
			<option value="memberId">아이디</option>
		</select>
		<input type="text" name="keyword" id="keyword"> &nbsp;
		<button id="search">검색</button> &nbsp;&nbsp;&nbsp;
		<button id="all">전체조회</button>
		
		
	</form>
	
	
	<form>
		<select name="sortfield" id="sortfield">
			<option value="memberNo" selected>회원번호</option>
			<option value="memberId">아이디</option>
			<option value="name">이름</option>
			<option value="email">이메일</option>
			<option value="address">주소</option>		
		</select> &nbsp;
		<button type="button" id="asc" value="asc">오름차순</button> &nbsp;
		<button type="button" id="desc" value="desc">내림차순</button>
	</form>
	
	<table>
		<tr>
			<th>회원번호</th>
			<th>아이디</th>
			<th>비밀번호</th>
			<th>이름</th>
			<th>성별</th>
			<th>전화번호</th>
			<th>이메일</th>
			<th>주소</th>
			<th>우편번호</th>
			<th>탈퇴여부</th>
			<th>탈퇴일자</th>
		</tr>
	</table>
	
	<!-- 페이징 처리 -->
	<div id="paging"></div>



</body>
</html>