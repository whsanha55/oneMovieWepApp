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
	
	#form2 > #deleteBtn{
		position: fixed;
		left: 500px;
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
						$('#table').find('tr:not(:first)').remove();
						var htmlStr ="";
						for(var i=0; i<data.length; i++) {
							htmlStr += "<tr>";
							htmlStr += "<td><input type='checkbox' name='selected' value='" + data[i].selected + "'></td>";
							htmlStr += "<td><a href='${pageContext.request.contextPath}/user/movie/detailMovie.do?movieNo=" +data[i].movieNo +" '>" + data[i].movieTitle + "</a></td>";
							htmlStr += "<td>" + data[i].runningTime + "</td>";
							htmlStr += "<td>" + data[i].director + "</td>";
							htmlStr += "<td>" + data[i].gradeAge+ "</td>";
							htmlStr += "<td>" + data[i].nationName+ "</td>";
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
							url: '${pageContext.request.contextPath}/user/movie/removeMovie.do'
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
			
			//전체 조회
			$('#selectAllBtn').on('click', function() {
				var html ="";
				html += "<tr>";
				html += "<th><input type='checkbox' name='all'></th>"; 
				html += "<th>사진</th>"; 
				html += "<th>제목</th>"; 
				html += "<th>상영시간</th>"; 
				html += "<th>감독</th>";
				html += "<th>등급</th>";
				html += "<th>국가</th>";
				html += "</tr>";
				$(html).appendTo('#table');
				
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
						$('#table').find('tr:not(:first)').remove();
						var htmlStr ="";
						for(var i=0; i<data.length; i++) {
							htmlStr += "<tr>";
							htmlStr += "<td><input type='checkbox' name='selected' value='" + data[i].selected + "'></td>";
							htmlStr += "<td><a href='${pageContext.request.contextPath}/user/movie/detailMovie.do?movieNo=" +data[i].movieNo +" '>" + data[i].movieTitle + "</a></td>";
							htmlStr += "<td>" + data[i].runningTime + "</td>";
							htmlStr += "<td>" + data[i].director + "</td>";
							htmlStr += "<td>" + data[i].gradeAge+ "</td>";
							htmlStr += "<td>" + data[i].nationName+ "</td>";
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
  <h3>영화 목록 조회 화면</h3>
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
    </form>
    
	<table border="1" id="table"></table><br><br>
   
   <form id="form2">
   			<button id="deleteBtn" type="button">삭제</button>
   </form>
 </body>
</html>