var grid,dialog,com_opt,combo;

$(function(){
	createGrid();
	initEvent();
});

function createGrid(){
	var	w = $(".main").width();
	var h = parent.iframe_h - $(".newAdmin").height() - 80;
	var columns = [
			{label:'adminId', name:'adminId', index:'adminId', hidden: true, key: true},
			{label:'pwd', name:'password', index:'password', hidden: true},
			{label:'用户名', name:'adminName', index:'adminName', width:100, align:'center'},
			{label:'注册审核', name:'rsgCheck', index:'rsgCheck',width:80,align:'center', formatter: checkFormat, unformat:unFormat},
			{label:'升级用户', name:'updateClass', index:'updateClass',width:80,align:'center', formatter: checkFormat, unformat:unFormat},
			{label:'用户禁言', name:'shieldUser', index:'shieldUser',width:80,align:'center', formatter: checkFormat, unformat:unFormat},
			{label:'修改问题类型', name:'qtype', index:'qtype',width:80,align:'center', formatter: checkFormat, unformat:unFormat},
			{label:'帖子管理', name:'postsManage', index:'postsManage',width:80,align:'center', formatter: checkFormat, unformat:unFormat},
			{label:'新增管理员', name:'addAdmin', index:'addAdmin',width:80,align:'center', formatter: checkFormat, unformat:unFormat},
			{label:'操作', width:150,align:'center',formatter: btnFormat}
		];
	$.post(url + '/admin_findByAdminId.action', {"parentId": $("#adminId").val()}, function(data){
		grid = $("#r_tb").jqGrid({
			colModel: columns,
			width:w,
			height:h,
			datatype: 'local',
			rownumbers: true,
			data:data.adminList,
			autowidth:true
		});
	});
}

function btnFormat(cellvalue, options, rowObject){
	return ['<div class="btn-format"><span class="icon-d" title="删除">&#xe9ac;</span>',
			'&nbsp;<span class="icon-e" title="编辑" data-robj="',rowObject.userId,'">&#xe92d;</span></div>'].join('');
}

//设置权限图标
function checkFormat(cellvalue, options, rowObject){
	var str = cellvalue == 1 ? '<span class="right check">1</span>' : '<span class="right cross">0</span>';
	return str;
}

function unFormat(cellvalue){
	if(cellvalue == '1'){
		return 1;
	}else{
		return 0;
	}
}
function createCombo(rowdata, str){
	console.log(updateClass);
	var btn_val = !str ? "添加" : "确定";
	var _html = ['<form class="sub-form" action="" method="post">',
				'<div class="newAdmin">',
				'<input type="text" placeholder="请输入弟子的法号" class="add-input" name="adminName">',
				' <input type="button" class="add-admin" value="',btn_val,'" id="sub_add" name="admin_qxs"></div>',
				'<input type="checkbox" name="rsgCheck" value="',rsgCheck,'"><label>注册审核</label><br>',
				'<input type="checkbox" name="updateClass" value="',updateClass,'"><label>升级用户</label><br>',
				'<input type="checkbox" name="shieldUser" value="',shieldUser,'"><label>用户禁言</label><br>',
				'<input type="checkbox" name="qtype" value="',qtype,'"><label>修改问题类型</label><br>',
				'<input type="checkbox" name="postsManage" value="',postsManage,'"><label>帖子管理</label><br>',
				'<input type="checkbox" name="addAdmin" value="',addAdmin,'"><label>添加管理员</label><br>',
				'<input type="text" style="display:none;">',
				'<input type="hidden" name="adminId" value="">',
				'<input type="hidden" name="password" value="">',
				'<input type="hidden" name="parentId" value="">',
				'</form>'].join('');
		dialog = $.ligerDialog.open({
			width:300,
			height:300,
			title: !str ? '新增管理员' : str,
			content: _html
		});

	$.post(url + "/user_findDiscipleList.action", {}, function(datas){
		com_opt = {
			width:150,
			height:25,
			valueField: 'userId',
			textField: 'username',
			data:datas.u,
			is_clear:true,
			Illegal_input:function(){//非法输入提示
	//   				$(".newAdmin").append("<div class='no-dizi' style='color:red;'>请输入弟子的法号</div>")
			},
			onSelected:function(value,text){
				$("input[name=adminId]").val(value);
			},
			autocomplete: function(e) {
				$(".l-box-select").find("tr").show();
				var data = datas.u, 
					tr_val = [],//过滤后的数据
					_key = $.trim(e.key);
				if(_key != "") {
					for(var i = 0; i < data.length; i++) {
						var str = data[i]['username'];
						var index = str.indexOf(_key);
						if(index != -1) {
							tr_val.push(data[i]['userId']);
	// 							$(".l-box-select").find("tr[value='" + tr_val + "'] span").addClass("l-highLight");
						}else{
							$(".l-box-select").find("tr[value='" + data[i]['userId'] + "']").hide();
						}
					}
				} 
				for(var j = 0; j < tr_val.length; j++){
					$(".l-box-select").find("tr[value='" + tr_val[j] + "']").show();
				}
				e.show();
			}
		};
		combo = $(".add-input").ligerComboBox(com_opt);
		if(rowdata){
			$(".add-input").val(rowdata.adminName);
			$("input[name=password]").val(rowdata.password);
			combo.setDisabled(true);
			$("input[name='adminId']").val(rowdata.adminId);
			$("input[name='rsgCheck']").val(rowdata.rsgCheck).attr("checked", rowdata.rsgCheck == "1");
			$("input[name='updateClass']").val(rowdata.updateClass).attr("checked", rowdata.updateClass == "1");
			$("input[name='shieldUser']").val(rowdata.shieldUser).attr("checked", rowdata.shieldUser == "1");
			$("input[name='qtype']").val(rowdata.qtype).attr("checked", rowdata.qtype == "1");
			$("input[name='postsManage']").val(rowdata.postsManage).attr("checked", rowdata.postsManage == "1");
			$("input[name='addAdmin']").val(rowdata.addAdmin).attr("checked", rowdata.addAdmin == "1");
			setDisabled();
			$(".add-input").attr("readonly", true);
		}else{
			$("input[name='rsgCheck']").attr("checked", rsgCheck == "1");
			$("input[name='updateClass']").attr("checked", updateClass == "1");
			$("input[name='shieldUser']").attr("checked", shieldUser == "1");
			$("input[name='qtype']").attr("checked", qtype == "1");
			$("input[name='postsManage']").attr("checked", postsManage == "1");
			$("input[name='addAdmin']").attr("checked", addAdmin == "1");
			setDisabled();
		}
	});
}

//当用户没有改权限时设置复选框不可用
function setDisabled(){
	rsgCheck == "0" && $("input[name='rsgCheck']").attr("disabled", "disabled");
	updateClass == "0" && $("input[name='updateClass']").attr("disabled", "disabled");
	shieldUser == "0" && $("input[name='shieldUser']").attr("disabled", "disabled");
	qtype == "0" && $("input[name='qtype']").attr("disabled", "disabled");
	postsManage =="0" && $("input[name='postsManage']").attr("disabled", "disabled");
	addAdmin == "0" && $("input[name='addAdmin']").attr("disabled", "disabled");
}
function initEvent(){	
	$(".add-admin").click(function(){
		createCombo();
	});
	
	$("body").on("click", ".icon-e", function(){//编辑管理员权限
		var id = $(this).parents("tr").attr('id'),
			data = grid.getRowData(id);
		createCombo(data, "修改管理员权限");
	});
	
	$("body").on("click", ".icon-d", function(){
		var id = $(this).parents("tr").attr("id");
		if(confirm("确定要删除该管理员吗？")){
			$.post(url + '/admin_delete.action', {"adminId":id}, function(data){
				if(data == "success"){
					FadeTip("删除成功");
					grid.delRowData(id);
				}
			});
		}
	});
	
	$("body").on("click", "#sub_add", function(){//添加管理员
		var new_admin = $(".add-input").val();
		if( new_admin== ''){
			alert("请输入弟子的法号！");
		}else{
			var edit_add = $(this).val() == "确定" ? "edit" : "add",
				selected = combo.getSelected(),
				str = '';
			selected && $("input[name=password]").val(selected.password);
			$("input[name=parentId]").val($("#adminId").val());
			var obj = {
				url: edit_add == "edit" ? url + '/admin_edit.action' : url + '/admin_save.action',
				success:function(data){
					if(data == "success"){
						dialog.close();
						edit_add =="edit" ? str = "修改成功" : str = "添加管理员成功";
						$.post(url + '/admin_findByAdminId.action', {"parentId": $("#adminId").val()}, function(data){
							grid.setGridParam({
								data:data.adminList
							}).trigger('reloadGrid');
						});
						FadeTip(str);
					}else{
						alert("操作失败");
					}
				}
			};
			$(".sub-form").ajaxSubmit(obj);
		}
	});
	
	$("body").on("click", ".sub-form input[type=checkbox]", function(){
		var state = $(this).prop("checked");
		state ? $(this).attr("value", "1") : $(this).attr("value", "0");
	});
}

function FadeTip(str){//操作提示，500毫秒后消失
	$(".stop-tip").addClass("stop-success").find("span").html(str).addClass("icon-success");
	$(".stop-tip").fadeIn(500, function() {
		setTimeout(function() {
			$(".stop-tip").fadeOut(500, function() {
				$(".stop-tip").hide();
			});
		}, 1000);
	});
}