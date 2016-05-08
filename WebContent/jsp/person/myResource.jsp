<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#myResource').tree({
			url: prjPath+'/resourceController/allMyTree',
				parentField:'pid',
				lines:false,
				onClick:function(node)
				{
					if (node.attributes && node.attributes.url) {
					
						var url = prjPath + node.attributes.url;
						addTab({
							url : url,
							title : node.text,
							iconCls : node.iconCls
						});
					}
					

				}
			});

	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
			<table class="tableForm">
					<ul id="myResource"></ul></td>
	</div>
</div>