<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript" src="${applicationContextPath}/jslib/extBrowser.js" charset="utf-8"></script>

<!-- 引入my97日期时间控件 -->
<script type="text/javascript" src="${applicationContextPath}/jslib/My97DatePicker4.8b3/My97DatePicker/WdatePicker.js" charset="utf-8"></script>

<!-- 引入kindEditor插件 -->
<link rel="stylesheet" href="${applicationContextPath}/jslib/kindeditor-4.1.7/themes/default/default.css">
<script type="text/javascript" src="${applicationContextPath}/jslib/kindeditor-4.1.7/kindeditor-all-min.js" charset="utf-8"></script>

<!-- 引入jQuery -->
<script src="${applicationContextPath}/jslib/jquery-1.8.3.js" type="text/javascript" charset="utf-8"></script>

<!-- 引入Highcharts -->
<script src="${applicationContextPath}/jslib/Highcharts-3.0.1/js/highcharts.js" type="text/javascript" charset="utf-8"></script>

<!-- 引入bootstrap样式 -->
<link href="${applicationContextPath}/jslib/bootstrap-2.3.1/css/bootstrap.min.css" rel="stylesheet" media="screen">
<script type="text/javascript" src="${applicationContextPath}/jslib/bootstrap-2.3.1/js/bootstrap.min.js"></script>
<%--<link href="${applicationContextPath}/jslib/bootstrap-2.3.1/css/bootstrap.css" rel="stylesheet" media="screen">
<link href="${applicationContextPath}/jslib/bootstrap-2.3.1/css/bootstrap-switch.css" rel="stylesheet" media="screen">
<link href="${applicationContextPath}/jslib/bootstrap-2.3.1/css/bootstrap-switch.min.css" rel="stylesheet" media="screen">
<script charset="utf-8" src="${applicationContextPath}/jslib/bootstrap-2.3.1/js/bootstrap-switch.js" charset="utf-8"></script>
<script charset="utf-8" src="${applicationContextPath}/jslib/bootstrap-2.3.1/js/bootstrap-switch.min.js" charset="utf-8"></script>

<link rel="stylesheet" type="text/css" href="${applicationContextPath}/jslib/bootstrap/css/bootstrapSwitch.css">
<script type="text/javascript" src="${applicationContextPath}/jslib/bootstrap/js/jquery.js"></script>
<script type="text/javascript" src="${applicationContextPath}/jslib/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${applicationContextPath}/jslib/bootstrap/js/bootstrapSwitch.js"></script>
	
--%>


<!-- 引入EasyUI -->
<link id="easyuiTheme" rel="stylesheet" href="${applicationContextPath}/jslib/jquery-easyui-1.3.3/themes/<c:out value="${cookie.easyuiThemeName.value}" default="bootstrap"/>/easyui.css" type="text/css">
<!-- <link rel="stylesheet" href="${applicationContextPath}/jslib/jquery-easyui-1.3.3/themes/icon.css" type="text/css"> -->
<script type="text/javascript" src="${applicationContextPath}/jslib/jquery-easyui-1.3.3/jquery.easyui.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${applicationContextPath}/jslib/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js" charset="utf-8"></script>
<!-- 修复EasyUI1.3.3中layout组件的BUG -->
<script type="text/javascript" src="${applicationContextPath}/jslib/jquery-easyui-1.3.3/plugins/jquery.layout.js" charset="utf-8"></script>

<!-- 引入EasyUI Portal插件 -->
<link rel="stylesheet" href="${applicationContextPath}/jslib/jquery-easyui-portal/portal.css" type="text/css">
<script type="text/javascript" src="${applicationContextPath}/jslib/jquery-easyui-portal/jquery.portal.js" charset="utf-8"></script>

<!-- 扩展EasyUI -->
<script type="text/javascript" src="${applicationContextPath}/jslib/extEasyUI.js?v=201305241044" charset="utf-8"></script>

<!-- 扩展EasyUI Icon -->
<link rel="stylesheet" href="${applicationContextPath}/style/extEasyUIIcon.css?v=201305301906" type="text/css">

<!-- 扩展jQuery -->
<script type="text/javascript" src="${applicationContextPath}/jslib/extJquery.js?v=201305301341" charset="utf-8"></script>
<link rel="stylesheet" href="${applicationContextPath}/style/css.css" type="text/css"></link>

<!-- xhEditor插件库 -->
<script type="text/javascript" src="${applicationContextPath}/jslib/xheditor-1.1.14/xheditor-1.1.14-zh-cn.min.js" charset="utf-8"></script>

<style>
	fieldset{padding:8px;margin:16px;};
	input{
		width: 175px !important;
		height: 28px !important;
	};
</style>
