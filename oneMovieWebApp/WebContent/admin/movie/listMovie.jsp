<%-- listArticle.jsp --%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
  
<!doctype html>
<html>
 <head>
  <meta charset="UTF-8">
  <title>영화 상세조회 화면</title>
 <style>
	#form, #form1 {
		display: inline;
	}	
	#form:not(#keyfield) {
 		  margin-left: 300px;
	}
	#form1, #writeMovieBtn {
		  margin-right: 5px;
	}
	#findBtn, #selectAllBtn, #writeMovieBtn {
		border-radius: 3px;
	  font-weight: 300;
	  border-color: transparent;
	  font-size: 15px;
	  background: #00bfff;
	  color: #fff;
	  cursor: pointer;
	}
	
	#form2 > #deleteBtn{
	  right: 350px;
	  position: absolute;
	  width: 50px;
	  height: 30px;
	  border-radius: 3px;
	  font-weight: 300;
	  border-color: transparent;
	  font-size: 15px;
	  background: #9e9e9e;
	  color: #fff;
	  cursor: pointer;
	}
	#keyfield {
		position: absolute;
		top:107px;
		left: 770px;
	}
	#keyfield, #keyword {
	    padding-left: 10px;
	    font-size: 15px;
	    color: #006fff;
	    border: 1px solid #b0e0e6;
	    border-radius: 3px;
	}
	
	#table {
   		width: 700px;		
   		margin: auto;
		border-collapse:collapse;
		border: 3px solid #2196f32e;
		background-color: #ffffff;
	}
	table th:not(#first) {
	   width: 500px;
	}
	
	.first {
		background-color: #2196f32e;
	} 
	#paging {
		margin:auto; 
		text-align:center;
	}
	#paging > a{
		font-size: 20px;
	}
	a {	
  	   text-decoration:none;
  	   color: black;
	}
	
	#last {   
        font-size: 20px;     
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
							url: '${pageContext.request.contextPath}/admin/movie/listAllMovie.do'
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
								
								$('#table').find('tr:not(:first)').remove();
								var htmlStr ="";
								for(var i=0; i<data.length; i++) {
									htmlStr += "<tr>";
									htmlStr += "<td><input type='checkbox' name='selected' value='" + data[i].selected + "'></td>";
									htmlStr += "<td>" + data[i].movieTitle + "</td>";
									htmlStr += "<td>" + data[i].director + "</td>";
									htmlStr += "<td><a id='last' href='${pageContext.request.contextPath}/admin/movie/detailMovie.do?movieNo=" +data[i].movieNo +" '>상세정보보기</a></td>";
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
							url: '${pageContext.request.contextPath}/admin/movie/findMovie.do'
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
								$('#table').find('tr:not(:first)').remove();
								var htmlStr ="";
								for(var i=0; i<data.length; i++) {
									htmlStr += "<tr>";
									htmlStr += "<td><input type='checkbox' name='selected' value='" + data[i].selected + "'></td>";
									htmlStr += "<td>" + data[i].movieTitle + "</td>";
									htmlStr += "<td>" + data[i].director + "</td>";
									htmlStr += "<td><a id='last' href='${pageContext.request.contextPath}/admin/movie/detailMovie.do?movieNo=" +data[i].movieNo +" '>상세정보보기</a></td>";
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
		//삭제
		$('#deleteBtn').on('click', function() {			
			var arr =[];
			if($("input[name=selected]").is(':checked') == false) {
				alert("삭제할 항목을 체크해주세요.");
			} else {
				$(':checkbox[name=selected]').each(function() {
					if($(this).is(':checked')) {
						arr.push($(this).val());
						console.log(arr.join());
					}
				});
				if(confirm("삭제하시겠습니까?")) {
					$.ajax({
						url: '${pageContext.request.contextPath}/admin/movie/removeMovie.do'
						,
						method: 'GET'
						,
						data: { movieNo : arr.join()}
						, 
						dataType: 'json'
						,
						success: function(data) {
							
							if(data.success == true) {
								alert("삭제되었습니다.");
							}
						
						}
						, 
						error: function(jqXHR) {
							alert('Error : ' + jqXHR.status);
						}	 			
						
					});	
				}
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
		

	    //체크박스 전체 체크 및 해제 처리
		$(':checkbox[name=all]').on('change', function() {		
    		if($(this).prop('checked'))  {
    			console.log($(this).val());
				$(':checkbox[name=selected]').prop('checked', true)
				console.log("전체!!!!");
			} else {
				$(':checkbox[name=selected]').prop('checked', false)
			}
    	});
	    
	    //추가하기
		$('#writeMovieBtn').click(function() {
	         location.href="${pageContext.request.contextPath}/admin/layoutAdmin.jsp?article=/admin/movie/writeMovieForm.jsp";
	      });
	});	
	
  </script>
 </head>
 <body>
 <br><br>
  <form id="form">
   		<select name="keyfield" id="keyfield">
   			<option value="MovieTitle">제목</option>
   			<option value="Director">감독</option>
   		</select>
   		<input type="text" name="keyword" id="keyword" size="20">
   		<button id="findBtn" type="button">검색</button>
   </form>
    
   <form id="form1">
   			&nbsp;&nbsp;
   			<button id="selectAllBtn" type="button">전체조회</button>
   			&nbsp;
            <button id="writeMovieBtn" type="button">추가하기</button>
    </form><br><br>
  	
	<table border="1" id="table" >
	
		<tr class='first'>
			<th id='first'><input type='checkbox' name='all'></th>
			<th>제목</th>
			<th>감독</th>
			<th></th>
		</tr>
	</table><br><br>
	
	 <form id="form2">
   			<button id="deleteBtn" type="button">삭제</button>
  	 </form>
  	 
    <div id="paging" > </div>
 </body>
</html>