<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/paper/add',
			onSubmit : function() {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				/* var isValid = $(this).form('validate');
				if (!isValid) {
					parent.$.messager.progress('close');
				}
				return isValid; */
				return true;
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
		<form id="form" method="post"  enctype="multipart/form-data" action="http://localhost:8080/rightsManger/paper/add" >
			<table class="tableForm">
				<tr>
					<th>title</th>
					<td><input  name="title"></td>
				</tr>
				<tr>	
					<th>journalName</th>
					<td><input  name="journalName"></td>
				</tr>
				<tr>	
					<th>publishDate</th>
					<td><input  name="publishDate"></td>
				</tr>
				<tr>	
					<th>journalType</th>
					<td><input  name="journalType"></td>
				</tr>
				<tr>	
					<th>上传1</th>
 					<td><input type="file" name="paperAttachmentFile"></td>
 				</tr>	
				<tr>	
					<th>上传2</th>
					<td><input  type="file"  name="serachFile"></td>
				</tr>
				
			</table>
		</form>
	</div>
</div>