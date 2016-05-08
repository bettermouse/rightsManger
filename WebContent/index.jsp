<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<meta charset="utf-8">
	<title>欢迎</title>
	<jsp:include page="inc.jsp"></jsp:include>
	<script type="text/javascript" src="${applicationContextPath}/jslib/index.js?randomId=<%=Math.random()%>"></script>
	<link rel="stylesheet" href="${applicationContextPath}/style/index.css">
	<script type="text/javascript">
	function logoutFun() {
		$.getJSON('${pageContext.request.contextPath}/userController/loginOut', {
			t : new Date()
		}, function(result) {
		
			location.replace('${pageContext.request.contextPath}/login.jsp');
		});
	}

	</script>
</head>

<body class="easyui-layout"> 
		<script type="text/javascript">
		 		prjPath ='${pageContext.request.contextPath}';
		</script>
    <div data-options="region:'north',title:'North Title',split:true,noheader:true" style="height:60px;background:#666;">
					<div class="logo">${myBaseInfo.name}</div>
					<div class="logout">[<strong>${sessionInfo.username}</strong>您好| <a href="#" onclick="logoutFun();">退出</a></div>
					<div style="position: absolute; right: 140px; bottom: 0px;">
			
			
			<a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#layout_north_kzmbMenu',iconCls:'cog'">控制面板</a> 
			
		</div>
	</div>   
    <div data-options="region:'south',title:'footer',split:true,noheader:true" style="height:35px;line-height:30px;text-align:center">
			联系我们:${myBaseInfo.email}
	</div>   

    <div data-options="region:'west',title:'导航',split:true,iconCls:'icon-world'" style="width:100px;width:180px;padding:10px">
    	<ul id="nav"></ul>
    </div>   
    <div data-options="region:'center'" style="overflow:hidden;">
		<div id="tabs"  style="overflow: hidden;">
			<div title="起始页" iconCls="icon-house" style="padding: 0 10px;display:block">
				欢迎您  登陆
			</div>
		</div>
	</div>   
</body>  

<div id="layout_north_kzmbMenu" style="width: 100px; display: none;">
	<div onclick="editCurrentUserPwd();">修改密码</div>
	<div class="menu-sep"></div>
	<div onclick="editCurrentUserMesssage();">修改个人资料</div>
	<div class="menu-sep"></div>
	<div onclick="showMyRole();">我的角色</div>
	<div class="menu-sep"></div>
	<div onclick="showMyResources();">我的权限</div>

</div>
</html>
