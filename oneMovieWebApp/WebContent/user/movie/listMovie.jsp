<%-- listArticle.jsp --%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
  
<!doctype html>
<html>
 <head>
  <meta charset="UTF-8">
  <title>영화 목록 조회 화면</title>
 <style>
	#form, #form1 {
		display: inline;
	}
	
	#form {
 		  margin-left: 220px;
	}
	#form1 {
		  margin-right: 100px;
	}
	
	#form2 > #deleteBtn{
		position: fixed;
		left: 500px;
	}
	#table {
   		margin-left: auto;
  		margin-right: auto;  		
		border-collapse:collapse;
	}
	table td:not(.first) {
	   width: 500px;
	   padding-left: 5px;
	}
	tr, td {
		border: 0;
	}	
	 td.first {
		width: 100px;
		padding:10px; 
	 }
	td.last, td.first{	
	   border-bottom: 2px solid black;
	}
	
	.title {
	   font-weight: bold;
  	   font-size: 20px;
	}
	
	a {	
  	   text-decoration:none;
	}
  </style>
 <script src="../../js/jquery-3.2.1.min.js"></script> 
<script>

	//////////////////////////////페이징 처리  jqueryPager//////////////////////////////////////////////////		
	function jqueryPager(subOption) {		
		var key = subOption.key;
		var currentPage = subOption.currentPage;		
		var pageSize = subOption.pageSize;				
		var pageBlock = subOption.pageBlock;			
		var pageTotal = subOption.pageTotal;			
			
		if (!pageSize) pageSize = 10;					
		if (!pageBlock) pageBlock = 10;				
		
		var pageTotalCnt = Math.ceil(pageTotal/pageSize);
		var pageBlockCnt = Math.ceil(currentPage/pageBlock);
		var sPage, ePage;
		var html = "";
		
		if (pageBlockCnt > 1) {
			sPage = (pageBlockCnt-1)*pageBlock+1;
		} else {
			sPage = 1;
		}	
		
		if ((pageBlockCnt*pageBlock) >= pageTotalCnt) {
			ePage = pageTotalCnt;
		} else {
			ePage = pageBlockCnt*pageBlock;
		}
		
		if (sPage > 1) {
			html += '<a onclick="goPage(' + 1 + ', '+ key +');">[ 처음 ]   </a>';
			html += '<a onclick="goPage(' + (sPage-pageBlock) + ', '+ key +');">[ 이전 ]   </a>';
		}		
		
		for (var i=sPage; i<=ePage; i++) {						   
			if (currentPage == i) {
				html += "   " + i + "   ";
			} else {
				html += '<a onclick="goPage(' + i + ', '+ key +');">' + i + '</a>';
			}
		}					   
		
		if (ePage < pageTotalCnt) {
			html += '<a onclick="goPage(' + (ePage+1) + ', '+ key +');">   [ 다음 ]   </a>';
			html += '<a onclick="goPage(' + pageTotalCnt + ', '+ key +');">    [ 끝 ]</a>';
		}	
		
		$("#paging").empty().append(html);		
	
	} //end of jqueryPager 
			

    //////////////////////////////페이징 처리 goPage //////////////////////////////////////////////////
	function goPage(currentPageNo, key) {		
		var totalRecordCount = 0;				//총 게시글 수		
		var recordCountPerPage = 3;			//한 페이지당 게시되는 게시글 수
		var pageSize = 2;				   		//페이지 리스트에 게시되는 페이지 수 
		var startRow = 0;
		var endRow = 0;
		
		if(key == 1) {
			$.ajax({
				url: '${pageContext.request.contextPath}/user/movie/listAllMovieCount.do'
				,
				method: 'GET'
				,
				data: $('#form1').serialize()
				,
				cache: false
				,
				dataType: 'json'
				,
				success: function(data, textStatus, jqXHR) {
					if (data.totalRecordCount > 0) {	  //총 게시글 수가 1개 이상인 경우
					
						totalRecordCount = data.totalRecordCount;  
						console.log("총게시글수: " + totalRecordCount);
						
						startRow = (currentPageNo - 1) * recordCountPerPage + 1;				
						endRow =  currentPageNo * recordCountPerPage;									
						if (endRow > totalRecordCount) {
							endRow = totalRecordCount;
						}
						
						console.log("시작: " + startRow);
						console.log("끝: " + endRow);
												
						$.ajax({
							url: '${pageContext.request.contextPath}/user/movie/listAllMovie.do'
							,
							type: "get"
							,
							dataType: 'json' 
							,
							data: {
								startRow: startRow,			
								endRow: endRow	
							}
							,
							success: function(data, textStatus, jqXHR) {	
								//alert(data);
								$('#table').find('tr').remove();
								var htmlStr ="";
								for(var i=0; i<data.length; i++) {
									htmlStr += "<tr>";
									htmlStr += "<td rowspan='6' class='first'><img src='${pageContext.request.contextPath}/user/movie/upload/" + data[i].moviePhotoOriginalFileName + "'></td>";
									htmlStr += "<td class='title'><a href='${pageContext.request.contextPath}/user/movie/detailMovie.do?movieNo=" +data[i].movieNo +" '>" + data[i].movieTitle + "</a></td>";
									htmlStr += "</tr>";
									htmlStr += "<tr>";
									htmlStr += "<td>상영시간: " + data[i].runningTime + "분</td>";
									htmlStr += "</tr>";
									htmlStr += "<tr>";
									htmlStr += "<td>감독: " + data[i].director + "</td>";
									htmlStr += "</tr>";
									htmlStr += "<tr>";
									htmlStr += "<td>등급: " + data[i].gradeAge+ "</td>";
									htmlStr += "</tr>";
									htmlStr += "<tr>";
									htmlStr += "<td>국가: " + data[i].nationName+ "</td>";
									htmlStr += "</tr>";					
									htmlStr += "<tr>";
									htmlStr += "<td class='last'><a href='#'>예매하기</a> &nbsp;&nbsp;<a href='#'>상영시간표</a></td>";
									htmlStr += "</tr>";
									
									$(htmlStr).appendTo('#table');
									htmlStr = "";
								}
								
								/////////////// 페이징 처리 /////////////										
								jqueryPager({
									key: 1,
									pageSize: recordCountPerPage, 
									pageBlock: pageSize,
									currentPage: currentPageNo,
									pageTotal: totalRecordCount 
								});
							}
							,
							error: function(jqXHR, textStatus, error) {
								alert("Error : " + jqXHR.status + ", " + error);
								
							}	
						});
		
					}//end of if clause
					
				}
				, 
				error: function(jqXHR) {
					alert('Error : ' + jqXHR.status);							
				}				
				
			});
		} else if(key == 2) {
			$.ajax({
				url: '${pageContext.request.contextPath}/user/movie/findMovieCount.do'
				,
				method: 'GET'
				,
				data: $('#form').serialize()
				,
				cache: false
				,
				dataType: 'json'
				,
				success: function(data, textStatus, jqXHR) {
					if (data.totalRecordCount > 0) {	  //총 게시글 수가 1개 이상인 경우
					
						totalRecordCount = data.totalRecordCount;  
						console.log("총게시글수: " + totalRecordCount);
						
						startRow = (currentPageNo - 1) * recordCountPerPage + 1;				
						endRow =  currentPageNo * recordCountPerPage;									
						if (endRow > totalRecordCount) {
							endRow = totalRecordCount;
						}
						console.log("keyfield: " + data.keyfield);
						console.log("keyword: " + data.keyword);
						console.log("시작: " + startRow);
						console.log("끝: " + endRow);
												
						$.ajax({
							url: '${pageContext.request.contextPath}/user/movie/findMovie.do'
							,
							method: 'GET'
							,
							data: $('#form').serialize(),
								   startRow: startRow,			
								   endRow: endRow	
							, 
							dataType: 'json'
							,
							success: function(data) {
								$('#table').find('tr').remove();
								var htmlStr ="";
								for(var i=0; i<data.length; i++) {
									htmlStr += "<tr>";
									htmlStr += "<td rowspan='6' class='first'><img src='${pageContext.request.contextPath}/user/movie/upload/" + data[i].moviePhotoOriginalFileName + "'></td>";
									htmlStr += "<td class='title'><a href='${pageContext.request.contextPath}/user/movie/detailMovie.do?movieNo=" +data[i].movieNo +" '>" + data[i].movieTitle + "</a></td>";
									htmlStr += "</tr>";
									htmlStr += "<tr>";
									htmlStr += "<td>상영시간: " + data[i].runningTime + "분</td>";
									htmlStr += "</tr>";
									htmlStr += "<tr>";
									htmlStr += "<td>감독: " + data[i].director + "</td>";
									htmlStr += "</tr>";
									htmlStr += "<tr>";
									htmlStr += "<td>등급: " + data[i].gradeAge+ "</td>";
									htmlStr += "</tr>";
									htmlStr += "<tr>";
									htmlStr += "<td>국가: " + data[i].nationName+ "</td>";
									htmlStr += "</tr>";					
									htmlStr += "<tr>";
									htmlStr += "<td class='last'><a href='#'>예매하기</a> &nbsp;&nbsp;<a href='#'>상영시간표</a></td>";
									htmlStr += "</tr>";
									
									$(htmlStr).appendTo('#table');
									htmlStr = "";
								}			
								/////////////// 페이징 처리 /////////////										
								jqueryPager({
									key: 2,
									pageSize: recordCountPerPage, 
									pageBlock: pageSize,
									currentPage: currentPageNo,
									pageTotal: totalRecordCount 
								});
							}
							,
							error: function(jqXHR, textStatus, error) {
								alert("Error : " + jqXHR.status + ", " + error);
								
							}	
						});
		
					}//end of if clause
					
				}
				, 
				error: function(jqXHR) {
					alert('Error : ' + jqXHR.status);							
				}				
				
			});
		}//end of if
	
	} //end of goPage 
	
	
	
	$(document).ready(function() {
	    //체크박스 전체 체크 및 해제 처리
		$(':checkbox[name=all]').on('change', function() {		
    		if($(this).prop('checked'))  {
				$(':checkbox[name=selected]').prop('checked', true)
			} else {
				$(':checkbox[name=selected]').prop('checked', false)
			}
    	});
	    	
    	//검색
		$('#findBtn').on('click', function() {
			goPage(1, 2);		
		});
			
		//전체 조회
		$('#selectAllBtn').on('click', function() {
			goPage(1, 1);			
		});
				
	});	
	
  </script>
 </head>
 <body>
 <br><br>
  <form id="form">
   		검색조건 : <select name="keyfield">
   					  		<option value="MovieTitle">제목</option>
   							<option value="Director">감독</option>
   					</select>
   					<input type="text" name="keyword" size="20">
   					<button id="findBtn" type="button">검색</button>
   </form>
    
   <form id="form1">
   			&nbsp;&nbsp;
   			<button id="selectAllBtn" type="button">전체조회</button>
    </form><br><br>
  
  	<%--전체 조회 OR 검색 리스트 나타남!!!얍!!! --%>
	<table border="1" id="table" style="overflow-y:auto; overflow-x:hidden; display:block; max-height:800px;"></table><br><br>
	
	<%--페이징 처리 나타남!!!얍!!! --%>
    <div id="paging" style="margin:auto; text-align:center;"> </div>
 </body>
</html>