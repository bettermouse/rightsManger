<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
		<form id="editCurrentUserForm" method="post">
			<table class="tableForm">
				<tr>	
					<th>我的角色:</th><td>
					<div  disabled="disabled">${user.roleNames}</div></td>
				</tr>

				
			</table>
		</form>
	</div>
</div>