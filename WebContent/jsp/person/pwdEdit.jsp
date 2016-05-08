<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#editCurrentUserPwdForm').form({
			url : '${pageContext.request.contextPath}/userController/editCurrentUserPwd',
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
					parent.$.modalDialog.handler.dialog('close');
					parent.$.messager.show({
			    		title:'提示',
			    		msg:'修改密码成功',
			    		timeout:5000,
			    		showType:'slide'
			    	});
					
				} else {
					parent.$.messager.alert('错误', result.msg, 'error');
				}
			}
		});
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
		<form id="editCurrentUserPwdForm" method="post">
			<table class="tableForm">
				<tr>	
					<th>旧密码</th><td><input name="pwd" type="password" class="easyui-validatebox" data-options="required:true"></td>
				</tr>
				<tr>	
					<th>新密码</th><td><input type="password" class="easyui-validatebox" data-options="required:true"></td>
				</tr>
				<tr>	
					<th>重复新密码</th><td><input  name="newPwd" type="password" class="easyui-validatebox" data-options="required:true"></td>
				</tr>
				
			</table>
		</form>
	</div>
</div>