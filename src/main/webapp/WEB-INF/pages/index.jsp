<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网络爬虫</title>
<script src='./js/plugins/jquery-3.3.1.min.js'></script>
<link rel="stylesheet" href="./css/base.css">
</head>
<body>
   <div>
         <form id="loginForm" action="">
             <div>
             <span>用户名</span>
              <input type="text" name="username"  placeholder ="请填写您的用户名">
             </div>
             <div>
             <span>密码</span>
              <input type="password" name="password" placeHolder="请填入您的密码">
             </div>
            <input type="button" id="submit" value="登录"/>
            <input type="button" value="新用户注册">
      	</form>
   </div>
  
</body>
<script type="text/javascript">
 $(function(){
	 console.log("123");
	 $(document).on("click","#submit",function(){
		console.log("456");
		 var data =$("#loginForm").serializeObject(); 
		 data["test"]="aaaaa11111";
		 console.log(data["username"]); 
	 })
 })
$.fn.serializeObject = function()  
{  
   var o = {};  
   var a = this.serializeArray();  
   $.each(a, function() {  
       if (o[this.name]) {  
           if (!o[this.name].push) {  
               o[this.name] = [o[this.name]];  
           }  
           o[this.name].push(this.value || '');  
       } else {  
           o[this.name] = this.value || '';  
       }  
   });  
   return o;  
};  


</script>
</html>