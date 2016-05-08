$(function(){
	//右边菜单树
	$('#nav').tree({
		url: prjPath+'/resourceController/myMenuTree',
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

	$("#tabs").tabs({
		fit:true,
		border:false
	
	});
	
	function addTab(params) {
		var iframe = '<iframe   src="' + params.url + '" frameborder="0" style="border:0;width:100%;height:98%;"></iframe>';
		var t = $('#tabs');
		var opts = {
			title : params.title,
			closable : true,
			iconCls : params.iconCls,
			content : iframe,
			border : false,
			fit : true
		};
		if (t.tabs('exists', opts.title)) {
			t.tabs('select', opts.title);
			parent.$.messager.progress('close');
		} else {
			t.tabs('add', opts);
		}
	}

	$('.logout a').click(function(){
		$.post("userController/loginOut",
				   function(data){
				   if(data.success)
				   {
				   	
						location.href = 'login.jsp';
				   }
				   else
				   {
					   	$('#msgTip').html(obj.msg);
			    		$("#password").val("");//将密码置空
			    		$("#valNumber").val("");//将验证码置空
				   }	
				   }, "json");
				
			});

	
});
function editCurrentUserPwd() {
	parent.$.modalDialog({
		title : '修改密码',
		width : 400,
		height : 250,
		href : prjPath+'/userController/editCurrentUserPwdPage',
		buttons : [ {
			text : '修改',
			handler : function() {
				var f = parent.$.modalDialog.handler.find('#editCurrentUserPwdForm');
				f.submit();
			}
		} ]
	});
}
function editCurrentUserMesssage() {
	parent.$.modalDialog({
		title : '修改个人资料',
		width : 400,
		height : 250,
		href : prjPath+'/userController/editCurrentUserMessagePage',
		buttons : [ {
			text : '修改',
			handler : function() {
				var f = parent.$.modalDialog.handler.find('#editCurrentUserForm');
				f.submit();
			}
		} ]
	});
}
function showMyRole() {
	parent.$.modalDialog({
		title : '我的角色',
		width : 400,
		height : 250,
		href : prjPath+'/userController/myRolePage',
		
	});
}
function showMyResources() {
	parent.$.modalDialog({
		title : '我的资源',
		width : 400,
		height : 550,
		href : prjPath+'/userController/myResourcesPage',
	});
}

