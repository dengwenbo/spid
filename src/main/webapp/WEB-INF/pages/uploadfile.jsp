<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传文件测试</title>
<script src='./js/plugins/jquery-3.3.1.min.js'></script>
<link rel="stylesheet" href="./css/base.css">
</head>
<body>
 <form action="./uploadtext.do" enctype="multipart/form-data" method="post">
 	 <div class="form-item">
 	  <span>文件描述：</span>
	 <input id="description" name="description"></input>
	 </div>
 	<div class="form-item">
 	<input id="file" name="file" type="file"/>   
 	</div>
 	
 </form>
</body>
</html>