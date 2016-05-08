<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>数据备份管理</title>
<jsp:include page="../inc.jsp"></jsp:include>
<c:if
	test="${fn:contains(sessionInfo.resourceList, '/backupController/delete')}">
	<script type="text/javascript">
		$.canDelete = true;
	</script>
</c:if>
<c:if
	test="${fn:contains(sessionInfo.resourceList, '/backupController/resume')}">
	<script type="text/javascript">
		$.canResume = true;
	</script>
</c:if>
<script>
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid')
				.datagrid(
						{
							url : '${pageContext.request.contextPath}/backupController/dataGrid',
							idField : 'name',
							fit : true,
							fitColumns : false,
							border : false,
							nowrap : true,
							singleSelect : true,
							columns : [ [
									{
										field : 'name',
										title : '文件名称',
										width : 250
									},
									{
										field : 'time',
										title : '备份时间',
										width : 150
									},
									{
										field : 'size',
										title : '备份大小(kb)',
										width : 250
									},
									{
										field : 'filetype',
										title : '备份类型',
										width : 150
									},
									{
										field : 'action',
										title : "操作",
										width : 150,
										formatter : function(value, row, index) {
											var str = '';
											if($.canDelete){ 
											str += $
													.formatString(
															'<img onclick="deleteFun(\'{0}\');" src="{1}" title="删除"/>',
															row.name,
															'${pageContext.request.contextPath}/style/images/extjs_icons/cancel.png');
											 	} 
											str += '&nbsp;';
													if($.canResume){
											str += $
													.formatString(
															'<img onclick="resume(\'{0}\');" src="{1}" title="恢复"/>',
															row.name,
															'${pageContext.request.contextPath}/style/images/extjs_icons/lock/lock_edit.png');
											 	} 
											return str;
										}

									} ] ],
							toolbar : '#toolbar',
							onLoadSuccess : function() {
								parent.$.messager.progress('close');
								$(this).datagrid('tooltip');
							}
						});
	});
	function addFun() {
		parent.$
				.modalDialog({
					title : '添加备份',
					width : 500,
					height : 300,
					href : '${pageContext.request.contextPath}/backupController/backupAddPage',
					buttons : [ {
						text : '添加',
						handler : function() {
							parent.$.modalDialog.dataGrid = dataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
							var f = parent.$.modalDialog.handler.find('#form');
							f.submit();
						}
					} ]
				});
	}

	function deleteFun(name) {
		if (name != undefined) {
			parent.$.messager
					.confirm(
							'询问',
							'您是否要删除当前备份？',
							function(b) {
								if (b) {
									parent.$.messager.progress({
										title : '提示',
										text : '数据处理中，请稍后....'
									});
									$
											.post(
													'${pageContext.request.contextPath}/backupController/delete',
													{
														"name" : name
													},
													function(data) {
														if (data.success) {
															dataGrid
																	.datagrid('load');
															parent.$.messager
																	.show({
																		title : '提示',
																		msg : '删除备份成功',
																		timeout : 5000,
																		showType : 'slide'
																	});

														} else {
															parent.$.messager
																	.alert(
																			'提示',
																			'删除备份失败！',
																			'info');

														}
													}, "json");

								}
							})

		}
	}

	function resume(name) {
		if (name != undefined) {
			parent.$.messager
					.confirm(
							'询问',
							'您是否要恢复？',
							function(b) {
								if (b) {
									parent.$.messager.progress({
										title : '提示',
										text : '数据处理中，请稍后....'
									});
									$
											.post(
													'${pageContext.request.contextPath}/backupController/resume',
													{
														"name" : name
													},
													function(data) {
														if (data.success) {
															dataGrid
																	.datagrid('load');
															parent.$.messager
																	.show({
																		title : '提示',
																		msg : '恢复备份成功',
																		timeout : 5000,
																		showType : 'slide'
																	});

														} else {
															parent.$.messager
																	.alert(
																			'提示',
																			'恢复备份失败！',
																			'info');

														}
													}, "json");

								}
							})

		}
	}
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'center',border:false" title=""
			style="overflow: hidden;">
			<table id="dataGrid"></table>
		</div>
	</div>
	<div id="toolbar" style="display: none;">
		<c:if
			test="${fn:contains(sessionInfo.resourceList, '/backupController/add')}">
			<a onclick="addFun();" href="javascript:void(0);"
				class="easyui-linkbutton"
				data-options="plain:true,iconCls:'pencil_add'">添加</a>
			</c:if>	
	</div>
</body>
</html>