<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/userController/add',
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
				parent.$.modalDialog.dataGrid.datagrid('load');
				//console.info(parent.$.modalDialog.dataGrid)
				if (result.success) {
					//$.messager.alert('警告','警告消息');  
					//.datagrid('load');//之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
					parent.$.modalDialog.handler.dialog('close');
				} else {
					parent.$.messager.alert('错误', result.msg, 'error');
				}
			}
		});
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
		<form id="form" method="post">
			<table class="tableForm">
				<tr>
					<th>用户名</th>
					<td><input name="username" class="easyui-validatebox"  data-options="required:true"></td>
				</tr>
				<tr>	
					<th>密码</th>
					<td><input value="默认密码为123456"  readonly="true"></td>
				</tr>
				<tr>	
					<th>联系电话</th>
					<td><input  name="phone"  class="easyui-validatebox"></td>
				</tr>
				
			</table>
		</form>
	</div>
</div>