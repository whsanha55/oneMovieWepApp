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
	#table{
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
	    $(document).ready(function() {
	    	
	    	$(':checkbox[name=all]').on('change', function() {		
	    		if($(this).prop('checked'))  {
					$(':checkbox[name=selected]').prop('checked', true)
				} else {
					$(':checkbox[name=selected]').prop('checked', false)
				}
	    	});
	    	
	    	//검색
			$('#findBtn').on('click', function() {
				$.ajax({
					url: '${pageContext.request.contextPath}/user/movie/findMovie.do'
					,
					method: 'GET'
					,
					data: $('#form').serialize()
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
					}
					, 
					error: function(jqXHR) {
						alert('Error : ' + jqXHR.status);							
					}				
					
				});
			});
			
			//전체 조회
			$('#selectAllBtn').on('click', function() {
				$.ajax({
					url: '${pageContext.request.contextPath}/user/movie/listAllMovie.do'
					,
					method: 'GET'
					,
					data: $('#form1').serialize()
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
					}
					, 
					error: function(jqXHR) {
						alert('Error : ' + jqXHR.status);							
					}				
					
				});
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
  
	<table border="1" id="table"></table><br><br>
 </body>
</html>