<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="chrome=1">
        <title>武汉汇通公路港信息平台登录</title>
        <link href="style/login.css" media="all" type="text/css" rel="stylesheet"/>
        <link id="easyuiTheme" rel="stylesheet" href="jslib/jquery-easyui-1.3.3/themes/<c:out value="${cookie.easyuiThemeName.value}" default="bootstrap"/>/easyui.css" type="text/css"></link>
        <link rel="stylesheet" href="jslib/jquery-easyui-1.3.3/themes/icon.css" type="text/css">
        <script src="jslib/jquery-1.8.3.js" type="text/javascript" charset="utf-8"></script>
        <script type="text/javascript" src="jslib/jquery-easyui-1.3.3/jquery.easyui.min.js" charset="utf-8"></script>
		<script type="text/javascript" src="jslib/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js" charset="utf-8"></script>
        <script type="text/javascript" src="jslib/login.js"></script>
        <script type="text/javascript" src="jslib/md5.js" charset="utf-8"></script>
     
    </head>
    <body>
    <div id="loading-mask">
        <div id="loading" class="loding">
           <div class="loading-indicator">
                <img src="resource/images/loading.gif"/><span id="loading-info">武汉汇通公路港信息平台登录</span><span id="loading-msg"></span>
           </div>
         </div>
    </div>
         <div id="wrap" style="width:400px;height:245px;top:200px;position:relative;margin: 0 auto;">
            <div id="bd" class="bd">
                <div id="bdwrap" class="bdwrap">
                	<div id="login" class="login">
	                	<form  id="loginForm" action="userController/login" method="post">
	                    	<div id="loginTable">
                                <div id="msgTip" style="position:relative;top:206px;left:50px;height:50px;width:115px;font-size:15px;font-style:italic">欢迎回来</div>
                                <input style="position:relative;top:63px;left:263px;height:18px;width:110px;font-size:13px;" type="text" id="username" name="loginName" class="uname" value="" onkeydown="javascript:unameKeydown(this,event);" onclick="javascript:unameKeydown(this,event);" onblur="javascript:unameBlur(this,event);" tabindex="1"/>
                                <input style="position:relative;top:95px;left:150px;height:18px;width:112px;" id="password" name="pwd" type="password" class="pass" value="" onkeydown="javascript:passKeydown(this,event);" onclick="javascript:passKeydown(this,event);" tabindex="2"/>
                                <input style="position:relative;top:125px;left:34px;height:18px;width:60px;" id="valNumber" name="valNumber" type="text" class="pass" value="" onkeydown="javascript:valNumberKeydown(this,event);" onclick="javascript:valNumberKeydown(this,event);" tabindex="3"/>
                                <div style="position:relative;top:106px;left:322px;height:19px;width:58px;border:1px #ccc solid">
                                	<img src="" width="58" border="0" title="点击刷新";height="19" id="cCodeImg" />
                                </div>
                                <button style="position:relative;top:113px;left:257px;" type="button" id="loginbtn" class="loginbtn" onclick="javascript:check();" tabindex="4"></button>
                               </div> 
                            </form>   
	                  </div>
                </div>
	    </div>
	   </div>
	   <div id="ft"><a href="http://www.wuhanhuitong56g.com" target=_blank style="color:orange;">Developed by huitong Tech</a></div>
	</body>
    <script>
        document.getElementById('username').value = '';
        document.getElementById('username').style.color = '';
        //document.getElementById('username').focus();
       $("#bdwrap").show();
    </script>
</html>
