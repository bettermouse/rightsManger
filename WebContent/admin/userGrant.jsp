<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
	parent.$.messager.progress('close');
	$('#roleIds').combotree({
		url : '${pageContext.request.contextPath}/roleController/tree',
		parentField : 'pid',
		lines : true,
		panelHeight : 'auto',
		multiple : true,
		onLoadSuccess : function() {
			parent.$.messager.progress('close');
		},
		cascadeCheck : false  ,
		value : $.stringToList('${user.roleIds}') 
		
	});
	$('#form').form({
		url : '${pageContext.request.contextPath}/userController/grant',
		onSubmit : function() {
			parent.$.messager.progress({
				title : '提示',
				text : '数据处理中，请稍后....'
			});
			var isValid = $(this).form('validate');
			if (!isValid) {
				parent.$.messager.progress('close');
			}
			
			return isValid;
		},
		success : function(result) {
			parent.$.messager.progress('close');
			result = $.parseJSON(result);
			if (result.success) {
				parent.$.modalDialog.dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_treeGrid这个对象，是因为role.jsp页面预定义好了
				parent.$.modalDialog.handler.dialog('close');
			}
		}
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
		<form id="form" method="post">
			<table class="tableForm">
				<tr>
					<th>编号</th>
				<td><input name="id" type="text" value="${user.id}" readonly="readonly"></td>
				</tr>
				<tr>
					<th>所属角色</th>
					<td><select id="roleIds" name="roleIds"   style="width: 200px" value="${user.roleIds}" ></select><img src="${pageContext.request.contextPath}/style/images/extjs_icons/cut_red.png" onclick="$('#roleIds').combotree('clear');" /></td> 
				</tr>
			</table>
		</form>
	</div>
</div>