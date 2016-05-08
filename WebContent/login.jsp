<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="chrome=1">
        <title>湖北</title>
        <link id="easyuiTheme" rel="stylesheet" href="jslib/jquery-easyui-1.3.3/themes/<c:out value="${cookie.easyuiThemeName.value}" default="bootstrap"/>/easyui.css" type="text/css"></link>
        <link rel="stylesheet" href="jslib/jquery-easyui-1.3.3/themes/icon.css" type="text/css">
        <script src="jslib/jquery-1.8.3.js" type="text/javascript" charset="utf-8"></script>
        <script type="text/javascript" src="jslib/jquery-easyui-1.3.3/jquery.easyui.min.js" charset="utf-8"></script>
		<script type="text/javascript" src="jslib/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js" charset="utf-8"></script>
        <script type="text/javascript" src="jslib/md5.js" charset="utf-8"></script>
     
    </head>
 	<body>
 	 <div id="login">
	<form id="loginForm">
	<p>帐   &nbsp; &nbsp;号：<input type="text"  name="username"></p>
	<p>密    &nbsp; &nbsp;码：<input type="password" name="password" ></p>
	点击刷新<img src="" width="58" border="0" title="点击刷新";height="119px" id="cCodeImg" />
	<p>验证码：<input  name="inputValNumber" ></p>
	 <div id="msgTip" style="position:relative;height:50px;width:255px;font-size:15px;font-style:italic">欢迎回来</div>
	</form>
</div>

<div id="btn">
	<a href="#" class="easyui-linkbutton">登录</a>
</div>

	<script type="text/javascript">

	$(function(){
		$('#login').dialog({
		title : '登录',
		width : 500,
		height : 380,
		modal : true,
		left:300,
		top:100,
		iconCls : 'icon-login',
		buttons : '#btn',
	});
	
	
		var new_rand_url = 'valNumberAction/createValNumber?sign='+Math.round(Math.random()*10000000000)+'000000000000000000000000000000';
		$('#cCodeImg').attr("src",new_rand_url);
		$('#cCodeImg').bind("click",function(){
			var new_rand_url = 'valNumberAction/createValNumber?sign='+Math.round(Math.random()*10000000000)+'000000000000000000000000000000';
			$('#cCodeImg').attr("src",new_rand_url);
		});
		
		$('#btn a').click(function () {
			$.messager.progress({title:'请等待',msg:'登录中.....'
			});
			  
			$.post("userController/login",$('#loginForm').serialize(),
			   function(data){
			   if(data.success)
			   {
				   window.location="index.jsp"
				   $.messager.progress('close');
			   }
			   else
			   {
				   $.messager.progress('close');
				 /*   alert(data.msg);
				   console.info(parent.$('#msgTip')) */
				 	parent.$('#msgTip').html(data.msg);
			   }	
			   }, "json");
			
		});
	});

	</script> 
 	
 	</body>
   
</html>
