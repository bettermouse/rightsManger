<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>角色管理</title>
<jsp:include page="../inc.jsp"></jsp:include>
<c:if
	test="${fn:contains(sessionInfo.resourceList, '/roleController/grant')}">
	<script type="text/javascript">
		$.canGrant = true;
	</script>
</c:if>
<c:if
	test="${fn:contains(sessionInfo.resourceList, '/roleController/edit')}">
	<script type="text/javascript">
		$.canEdit = true;
	</script>
</c:if>
<c:if
	test="${fn:contains(sessionInfo.resourceList, '/roleController/delete')}">
	<script type="text/javascript">
		$.canDelete = true;
	</script>
</c:if>
<script type="text/javascript">
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid')
				.datagrid(
						{
							url : '${pageContext.request.contextPath}/roleController/dataGrid',
							idField : 'id',
							fit : true,
							fitColumns : false,
							border : false,
							nowrap : true,
							pagination : true,
							singleSelect : true,
							frozenColumns : [ [ {
								title : '编号',
								field : 'id',
								width : 150,
								hidden : true
							}, {
								field : 'name',
								title : '角色名称',
								width : 150
							} ] ],
							columns : [ [
									{
										field : 'seq',
										title : '排序',
										width : 40
									}/*,  {
													field : 'resourceIds',
													title : '拥有资源',
													width : 250,
													formatter : function(value, row) {
														if (value) {
															return row.resourceNames;
														}
														return '';
													}
												} */,
									{
										field : 'resourcesName',
										title : '拥有资源名称',
										width : 250
									},
									{
										field : 'description',
										title : '备注',
										width : 150
									},
									{
										field : 'action',
										title : '操作',
										width : 70,
										formatter : function(value, row, index) {
											var str = '';
											if ($.canEdit) {
												str += $
														.formatString(
																'<img onclick="editFun(\'{0}\');" src="{1}" title="编辑"/>',
																row.id,
																'${pageContext.request.contextPath}/style/images/extjs_icons/pencil.png');
											}
											str += '&nbsp;';
											if ($.canGrant) {
												str += $
														.formatString(
																'<img onclick="grantFun(\'{0}\');" src="{1}" title="授权"/>',
																row.id,
																'${pageContext.request.contextPath}/style/images/extjs_icons/key.png');
											}
											str += '&nbsp;';
											if ($.canDelete) {
												str += $
														.formatString(
																'<img onclick="deleteFun(\'{0}\');" src="{1}" title="删除"/>',
																row.id,
																'${pageContext.request.contextPath}/style/images/extjs_icons/cancel.png');
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

	function deleteFun(id) {
		parent.$.messager
				.confirm(
						'询问',
						'您是否要删除当前角色？',
						function(b) {
							if (b) {
								parent.$.messager.progress({
									title : '提示',
									text : '数据处理中，请稍后....'
								});
								$
										.post(
												'${pageContext.request.contextPath}/roleController/delete',
												{
													id : id
												},
												function(result) {
													if (result.success) {
														parent.$.messager
																.alert(
																		'提示',
																		result.msg,
																		'info');
														dataGrid
																.datagrid('reload');
													} else {
														parent.$.messager
																.alert(
																		'提示',
																		result.msg,
																		'info');
													}
													parent.$.messager
															.progress('close');
												}, 'JSON');
							}
						});

	}

	function editFun(id) {

		parent.$
				.modalDialog({
					title : '编辑角色',
					width : 500,
					height : 300,
					href : '${pageContext.request.contextPath}/roleController/editPage?id='
							+ id,
					buttons : [ {
						text : '编辑',
						handler : function() {

							parent.$.modalDialog.dataGrid = dataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
							var f = parent.$.modalDialog.handler.find('#form');
							f.submit();
						}
					} ]
				});
	}

	function addFun() {

		parent.$.modalDialog({
			title : '添加角色',
			width : 500,
			height : 300,
			href : '${pageContext.request.contextPath}/roleController/addPage',
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

	function grantFun(id) {

		parent.$
				.modalDialog({
					title : '角色授权',
					width : 500,
					height : 500,
					href : '${pageContext.request.contextPath}/roleController/grantPage?id='
							+ id,
					buttons : [ {
						text : '授权',
						handler : function() {
							parent.$.modalDialog.dataGrid = dataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
							var f = parent.$.modalDialog.handler.find('#form');
							f.submit();
						}
					} ]
				});
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
			test="${fn:contains(sessionInfo.resourceList, '/roleController/add')}">
			<a onclick="addFun();" href="javascript:void(0);"
				class="easyui-linkbutton"
				data-options="plain:true,iconCls:'pencil_add'">添加</a>
		</c:if>

		<a onclick="dataGrid.datagrid('reload');" href="javascript:void(0);"
			class="easyui-linkbutton"
			data-options="plain:true,iconCls:'transmit'">刷新</a>
	</div>
</body>
</html>