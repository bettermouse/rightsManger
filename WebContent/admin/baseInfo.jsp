<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>用户资源管理</title>
<jsp:include page="../inc.jsp"></jsp:include>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'center',border:false" title=""
			style="overflow: hidden;">
			<form id="baseInfoForm" style="margin-left: 200px" method="post">
				<table class="tableForm">
					<tr>
						<td>网站名称:</td>
						<td><input value="${baseInfo.name}" name="name"
							class="easyui-validatebox" data-options="required:true"></td>
					</tr>
					<tr>
						<td>网站地址</td>
						<td><input value="${baseInfo.address}" name="address"
							class="easyui-validatebox" data-options="required:true"></td>
					</tr>
					<tr>
						<td>邮政编码</td>
						<td><input value="${baseInfo.zipCode}" name="zipCode"
							class="easyui-validatebox" data-options="required:true"></td>
					</tr>
					<tr>
						<td>备案号</td>
						<td><input value="${baseInfo.recordCode}" name="recordCode"
							class="easyui-validatebox" data-options="required:true"></td>
					</tr>
					<tr>
						<td>电话</td>
						<td><input value="${baseInfo.phone}" name="phone"
							class="easyui-validatebox" data-options="required:true"></td>
					</tr>
					<tr>
						<td>邮箱</td>
						<td><input value="${baseInfo.email}" name="email"
							class="easyui-validatebox"
							data-options="required:true,validType:'email'"></td>
					</tr>
					<tr>
						<td>网站域名</td>
						<td><input value="${baseInfo.domainName}" name="domainName"
							class="easyui-validatebox" data-options="required:true"></td>
					</tr>
					<tr>
						<td></td><td>
						<c:if
							test="${fn:contains(sessionInfo.resourceList, '/baseInfoController/update')}">
							<input type="submit" value="确认修改">
						</c:if>
						</td>
					</tr>
				</table>
			</form>

		</div>
	</div>
	<script type="text/javascript">
		$(function() {
			$('#baseInfoForm')
					.form(
							{
								url : '${pageContext.request.contextPath}/baseInfoController/update',
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
										parent.$.messager.show({
											title : '提示',
											msg : '修改成功',
											timeout : 5000,
											showType : 'slide'
										});
									}
								}
							});
		});
	</script>
</body>
</html>