<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" href="js/jquery-ui.min.css">
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/jquery-ui.min.js"></script>


</head>
<body>
<script>
  $(function() {
    $("#datepicker1").datepicker({
    	dateFormat: 'yy-mm-dd'
    });
  });
  console.log($('#a').val());
</script>

<div id='datepicker1'></div>
</body>
</html>