<%-- listStateMovie.jsp --%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
  
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>상영상태에 따른 영화 조회 화면</title>
<style>
	#form, #form1 {
   		display: inline;
	}
		
	#form {
	   margin-left: 350px;
	}
	
	#form1 {
	   margin-right: 100px;
	}
	
	#keyfield {
	   position: absolute;
	   top: 155px;
	   left: 770px;
	}
	
	#findBtn, #selectAllBtn {
	   border-radius: 3px;
	   font-weight: 300;
	   border-color: transparent;
	   font-size: 15px;
	   background: #5c87b2;
	   color: #fff;
	   cursor: pointer;
	}
	
	#table {
	   max-height: 800px;
	   margin-left: auto;
	   margin-right: auto;
	   border-collapse: collapse;
	   border: 1px solid #666c;
	   overflow-y: auto;
	   overflow-x: hidden;
	   display: block;
	}
	
	td:not(.first) {
		width: 700px;
		padding-left:5px;
	}

	tr, td {
		border: 0;
	}
			
	td.first {
		width: 100px;
		padding:10px; 
	}
		 
	td.last, td.first {
	   border-bottom: 1px solid #666c;
	}
	
	.booking, .theater {
		display: inline-block;
		padding: 5px;
		border-radius: 10px;
		border-color: transparent;
		font-size: 12px;
		background: #bf0d0d;
		color: #fff;
		cursor: pointer;
	}

	.title {
	   font-weight: bold;
  	   font-size: 20px;
	}
	
	a {	
  	   text-decoration:none;
	}
	
	#paging {
		margin: auto; 
		text-align: center;
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
									htmlStr += "<td class='last'><a class='booking' href='${pageContext.request.contextPath}/memberBooking.do?movieNo=${pageScope.movie.movieNo}'>예매하기</a> &nbsp;&nbsp;<a class='theater' href='#'>상영시간표</a></td>";
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
									htmlStr += "<td class='last'><a class='booking' href='${pageContext.request.contextPath}/memberBooking.do?movieNo=${pageScope.movie.movieNo}'>예매하기</a> &nbsp;&nbsp;<a class='theater' href='#'>상영시간표</a></td>";
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
		//전체 조회
		$('#selectAllBtn').on('click', function() {
			goPage(1, 1);			
		});
	    	
		//검색
		$('#findBtn').on('click', function() {
			goPage(1, 2);		
		});		
	});	
	
 </script>
 </head>
 <body><br><br>
 <form id="form">
 	<select name="keyfield" id="keyfield">
		<option value="MovieTitle">제목</option>
   		<option value="Director">감독</option>
   	</select>
   	<input type="text" name="keyword" size="20">
   	<button id="findBtn" type="button">검색</button>
 </form>
 
 <form id="form1">&nbsp;&nbsp;
 	<button id="selectAllBtn" type="button">전체조회</button>
 </form><br><br>
 <table border="1" id="table" >
	<c:forEach var="movie" items="${requestScope.movies }" varStatus="loop">
		<tr>
			<td rowspan= "6" class= "first"><img src="${pageContext.request.contextPath}/user/movie/upload/${pageScope.movie.photo.moviePhotoOriginalFileName}"></td>
			<td class="title"><a href="${pageContext.request.contextPath}/user/movie/detailMovie.do?movieNo=${pageScope.movie.movieNo}">${pageScope.movie.movieTitle }</a></td>
		</tr>  
		<tr>
			<td>상영시간: ${pageScope.movie.runningTime}분</td>
		</tr>
		<tr>
			<td>감독: ${pageScope.movie.director }</td>
		</tr>
		<tr>
			<td>등급: ${pageScope.movie.grade.gradeAge }</td>
		</tr>
		<tr>
			<td>국가: ${pageScope.movie.nation.nationName }</td>
		</tr>					
		<tr>
			<td class='last'>
			<c:choose>
            <c:when test="${param.keyfield =='end' }">
                <a class='theater' href='#'>상영시간표</a>            
            </c:when>
            <c:otherwise>
               <a class='booking' href='${pageContext.request.contextPath}/memberBooking.do?movieNo=${pageScope.movie.movieNo}'>예매하기</a>
                &nbsp;&nbsp;<a class='theater' href='#'>상영시간표</a>
            </c:otherwise>
          </c:choose>
		 	</td>
		</tr>
	</c:forEach>
</table><br><br>

<div id="paging" > </div>
</body>
</html>