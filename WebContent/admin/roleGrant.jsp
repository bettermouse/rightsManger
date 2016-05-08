<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	var resourceTree;
	$(function() {
		$.messager.progress('close');
		resourceTree = $('#resourceTree').tree({
			url : '${pageContext.request.contextPath}/resourceController/allTree',
			parentField : 'pid',
			//lines : true,
			checkbox : true,
			onClick : function(node) {
				
			},
			onLoadSuccess : function(node, data) {
				//选择该节点本来的资源
				var ids = $.stringToList('${role.resourcesId}');
				for ( var i = 0; i < ids.length; i++) {
				
					if (resourceTree.tree('find', ids[i])){
						resourceTree.tree('check', resourceTree.tree('find', ids[i]).target);
					}
				}

			},
			cascadeCheck : false
		});

		$('#form').form({
			url : '${pageContext.request.contextPath}/roleController/grant',
			onSubmit : function() {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				var isValid = $(this).form('validate');
				if (!isValid) {
					parent.$.messager.progress('close');
				}
				var checknodes = resourceTree.tree('getChecked');
				var ids = [];
				if (checknodes && checknodes.length > 0) {
					
					for ( var i = 0; i < checknodes.length; i++) {
					
						ids.push(checknodes[i].id);
					}
				}
			
				$('#resourcesId').val(ids);
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
<div id="roleGrantLayout" class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'west'" title="系统资源" style="width: 300px; padding: 1px;">
		
			<form id="form" method="post">
				<input name="id"   value="${role.id}" readonly="readonly" type="hidden">
				<input name="resourcesId" value="${role.resourcesId}"  id="resourcesId" type="hidden">
				<ul id="resourceTree"></ul>
				
			</form>
		
	</div>
	<div data-options="region:'center'">
		<input type="button"  value="级联" onclick="resourceTree.tree({cascadeCheck:true});">
		<input type="button"  value="不级联" onclick="resourceTree.tree({cascadeCheck:false});">
	</div>

</div>