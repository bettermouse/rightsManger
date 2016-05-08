<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(function() {
	
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/roleController/add',
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
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
		<form id="form" method="post">
			<table class="table table-hover table-condensed">
				<tr>
					<th>角色名称</th>
					<td><input name="name" type="text" placeholder="请输入角色名称" class="easyui-validatebox" data-options="required:true" value="" style="height:40px"></td>
				</tr>
				<tr>
					<th>排序</th>
					<td><input name="seq" value="10" class="easyui-numberspinner" style="width: 140px; height: 29px;" required="required" data-options="editable:false,min:0"></td>
				</tr>
				<tr>
					<th>备注</th>
					<td colspan="3"><textarea name="description" rows="" cols="" ></textarea></td>
				</tr>
			</table>
		</form>
	</div>
</div>