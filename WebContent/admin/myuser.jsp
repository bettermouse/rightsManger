<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../inc.jsp"></jsp:include>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理页面</title>
<c:if
	test="${fn:contains(sessionInfo.resourceList, '/userController/edit')}">
	<script type="text/javascript">
		$.canEdit = true;
	</script>
</c:if>
<c:if
	test="${fn:contains(sessionInfo.resourceList, '/userController/rePwd')}">
	<script type="text/javascript">
		$.canRePwd = true;
	</script>
</c:if>
<c:if
	test="${fn:contains(sessionInfo.resourceList, '/userController/status')}">
	<script type="text/javascript">
		$.canStatus = true;
	</script>
</c:if>
<c:if
	test="${fn:contains(sessionInfo.resourceList, '/userController/grant')}">
	<script type="text/javascript">
		$.canGrant = true;
	</script>
</c:if>
<c:if
	test="${fn:contains(sessionInfo.resourceList, '/userController/delete')}">
	<script type="text/javascript">
		$.canDelete = true;
	</script>
</c:if>
<script type="text/javascript">
var userDataGrid;
	$(function(){
		
		 userDataGrid=$("#userDataGrid").datagrid({
			url:'${pageContext.request.contextPath}/userController/dataGrid',
			pagination:true,
			id:"id",
			fit:true,
			columns:[[{
				field:'id',
				title:"用户id",
				width:100
			},{
				field:'username',
				title:"用户名",
				width:150
			},{
				field:'createDate',
				title:"创建时间",
				width:150
			},{
				field:'phone',
				title:"联系电话",
				width:150
			},{
				field:'status',
				title:"用户状态",
				width:150,
				formatter:function(value,row,index){
					if(value==0){
						return "未激活";
					}else{
						return "激活";
					}
					
				}
			},{
				field:'action',
				title:"操作",
				width:150,
				formatter: function(value,row,index){
					var str = '';
					if($.canEdit){
						str += $.formatString('<img onclick="editFun(\'{0}\');" src="{1}" title="编辑"/>', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/pencil.png');
					}
					
					str += '&nbsp;';
					if($.canGrant){
						str += $.formatString('<img onclick="grantFun(\'{0}\');" src="{1}" title="授权"/>', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/key.png');
					}
					
					str += '&nbsp;';
					if($.canDelete){
						str += $.formatString('<img onclick="deleteFun(\'{0}\');" src="{1}" title="删除"/>', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/cancel.png');
					}
					
					str += '&nbsp;';
					if($.canStatus){
						str += $.formatString('<img onclick="statusFun(\'{0}\');" src="{1}" title="切换用户状态"/>', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/exclamation.png');

					}
					
					str += '&nbsp;';
					if($.canRePwd){
						str += $.formatString('<img onclick="rePwdFun(\'{0}\');" src="{1}" title="重置密码"/>', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/lock/lock_edit.png');
					}
					
					return str;
				}

			}]],
			toolbar:[{
				text:'增加',
				iconCls:'pencil_add',
				handler:function(){
					addUser();
				}
			},{
				text:'刷新',
				iconCls:'transmit',
				handler:function(){
					userDataGrid.datagrid('load');
				}
			}/* ,{
				text:'删除',
				iconCls:'pencil_delete',
				handler:function(){
					console.info($.fn.datagrid.defaults.editors);
				}
			},{
				text:'修改',
				iconCls:'pencil',
				handler:function(){
					console.info($)
				}
			},{
				text:'保存',
				iconCls:'database_save',
				handler:function(){
					userDataGrid.datagrid('endEdit',0); 
				} 
			}*/]
		});
	})
	
	function addUser(){
		parent.$.modalDialog({
			title : '添加用户',
			width : 550,
			height : 350,
			href : '${pageContext.request.contextPath}/userController/addPage',
			buttons : [ {
				text : '添加',
				handler : function() {
					parent.$.modalDialog.dataGrid = userDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#form');
					f.submit();
				}
			} ]
		});
	}
	
	function deleteFun(id){
		if(id!=undefined){
			$.post('${pageContext.request.contextPath}/userController/delete', { "id": id},
					   function(data){
					    if(data.success){
					    	userDataGrid.datagrid('load');
					    	parent.$.messager.show({
					    		title:'提示',
					    		msg:'删除成功',
					    		timeout:5000,
					    		showType:'slide'
					    	});

					    }else{
					    	parent.$.messager.alert('提示','删除失败！','info');


					    }
					   }, "json");
		}
	}
	
	function statusFun(id){
		if(id!=undefined){
			$.post('${pageContext.request.contextPath}/userController/status', { "id": id},
					   function(data){
					    if(data.success){
					    	userDataGrid.datagrid('load');
					    	parent.$.messager.show({
					    		title:'提示',
					    		msg:'用户状态切换成功',
					    		timeout:5000,
					    		showType:'slide'
					    	});

					    }else{
					    	parent.$.messager.alert('提示','用户状态切换失败！','info');


					    }
					   }, "json");
		}
	}
	
	function rePwdFun(id){
		if(id!=undefined){
			$.post('${pageContext.request.contextPath}/userController/rePwd', { "id": id},
					   function(data){
					    if(data.success){
					    	userDataGrid.datagrid('load');
					    	parent.$.messager.show({
					    		title:'提示',
					    		msg:'成功',
					    		timeout:5000,
					    		showType:'slide'
					    	});

					    }else{
					    	parent.$.messager.alert('提示','失败！','info');
					    }
					   }, "json");
		}
		
	}
	
	function editFun(id){
		parent.$.modalDialog({
			title : '修改用户',
			width : 550,
			height : 350,
			href : '${pageContext.request.contextPath}/userController/editPage?id='+id,
			buttons : [ {
				text : '修改',
				handler : function() {
					parent.$.modalDialog.dataGrid = userDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#form');
					f.submit();
				}
			} ]
		});
	}
	
	function  grantFun(id){
		parent.$.modalDialog({
			title : '用户授权',
			width : 550,
			height : 350,
			href : '${pageContext.request.contextPath}/userController/grantPage?id='+id,
			buttons : [ {
				text : '修改',
				handler : function() {
					parent.$.modalDialog.dataGrid = userDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#form');
					f.submit();
				}
			} ]
		});
	}
	function serach(){
		userDataGrid.datagrid('load',{
			username:$('#user_search').find('[name=username]').val(),
			createDate:$('#createDate').datebox('getValue'),
			endDate:$('#endDate').datebox('getValue') 
		});
	}
	function clear(){
		
		alert(1);
	/* 	$('#user_search').find('[name=username]').val("");
		$('#createDate').val("");
		$('#endDate').val(""); */
	}
</script>
</head>


<body>   
<div  class="easyui-layout" fit="true" >
	<div data-options="region:'north'" title="查询条件" style="height: 100px">
			<form id="user_search">
				<table>
					<tr>
						<th>用户名</th><td><input name="username"></td>
					</tr>
					<tr>
						<th>创建时间</th><td><input class="easyui-datebox"  id="createDate"  editable="false">至<input class="easyui-datebox"  id="endDate"  editable="false">
						<a class="easyui-linkbutton"  onclick="serach();">查询</a>
						<a class="easyui-linkbutton" onclick="clear();">清空</a>
						</td>
					
					</tr>
				</table>
			</form>
	</div>
	<div data-options="region:'center'" >
			<table id="userDataGrid"></table>
	</div>
</div>
  
</body>
</html>