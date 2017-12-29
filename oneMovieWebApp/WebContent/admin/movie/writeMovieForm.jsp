
<%-- writeArticleForm.jsp --%>
<%@ page contentType="text/html; charset=utf-8" %>
 
<!doctype html>
<html>
 <head>
  <meta charset="UTF-8">
  <title>영화 입력 화면</title>
  <style>
     table {
        border-collapse: collapse;
     }
     
     p {
        width: 400px;
        padding: 15px;
        border: 1px solid;
        
     }
     
     #actorAddBtn, #photoAddBtn {
        margin: 10px 10px 1px 10px;
        
     }
  
  </style>
  <script src="js/jquery-3.2.1.min.js"></script> 
  <script>
   $(document).ready(function() {   

      
      $('#add').on('click', function() {
         var str = '<button id="add2">추가하기</button>';

         $('#area').append(str);
      });


   });
  </script>
 </head>
 
 <body>
    <form action="${pageContext.request.contextPath }/admin/movie/writeMovie"   
    enctype="multipart/form-data" method="post">       
       
      <br>영화 기본 정보<br><br>
      <table border=1>
       <tr>
          <th>제    목</th>
          <td><input type="text" name="movieTitle" size="20"></td>
        </tr>
        <tr>
          <th>장    르</th>
          <td> 
               <input type="checkbox" name="genreNo" value="1">액션
                 <input type="checkbox" name="genreNo" value="2">로맨스
                 <input type="checkbox" name="genreNo" value="3">코미디
                 <input type="checkbox" name="genreNo" value="4">공포
                 <input type="checkbox" name="genreNo" value="5">SF
                 <input type="checkbox" name="genreNo" value="6">판타지
                 <input type="checkbox" name="genreNo" value="7">애니
            </td>
        </tr>
        <tr>
          <th>상영시간</th>
          <td><input type="number" name="runningTime" size="20"></td>
        </tr>
        <tr>
          <th>감    독</th>
          <td><input type="text" name="director" size="20"></td>
        </tr>
        <tr>
          <th>등    급</th>
          <td>
             <select name="gradeNo">
                   <option value="1">전체 관람가</option>
                   <option value="2">12세 관람가</option>
                   <option value="3">15세 관람가</option>
                   <option value="4">19세 관람가</option>
                </select>
          </td>
        </tr>
        <tr>
          <th>국    가</th>
          <td>
             <select name="nationNo">
                   <option value="1">미국</option>
                   <option value="2">한국</option>
                   <option value="3">영국</option>
                   <option value="4">일본</option>
                   <option value="5">대만</option>
                </select>
          </td>
        </tr>
      </table>
      <br><br><br>  
      
        배우  <br>
      <button id="actorAddBtn">배우추가하기</button>
      <p id="area">          
        배우사진 : <input type="file" name="uploadactor"><br>
        이    름 : <input type="text" name="actorName" size="20"><br> 
        역    할 : <select name="roleNo">
                   <option value="1">주연</option>
                 <option value="2">조연</option>
                 <option value="3">엑스트라</option>
                </select><br>
        캐릭터이름 : <input type="text" name="characterName" size="20"></p>
      
        
       <p>          
        배우사진 : <input type="file" name="uploadactor"><br>
        이    름 : <input type="text" name="actorName" size="20"><br> 
        역    할 : <select name="roleNo">
                   <option value="1">주연</option>
                 <option value="2">조연</option>
                 <option value="3">엑스트라</option>
                </select><br>
        캐릭터이름 : <input type="text" name="characterName" size="20"></p>
       <br><br>
       
       영화사진 
       <button id="photoAddBtn">사진추가하기</button><br><br>
       <input type="file" name="upload"><br><br>
       <input type="file" name="upload"><br><br>
       <input type="file" name="upload"><br><br><br>
        
        
       <button type="submit">영화 등록</button>&nbsp;
       <button type="reset">취소</button>   
     </form>   
 </body>
</html>






