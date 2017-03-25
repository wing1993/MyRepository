var grid,dialog,com_opt,combo;

$(function(){
	createGrid();
	initEvent();
	
});

function createGrid(){
	var d = [{"userId":1,"username":"kkk","q1":"ss","q2":"rr"}];
	var	w = $(".main").width();
	var h = parent.iframe_h - $(".newAdmin").height() - 80;
	var columns = [
			{label:'userId', name:'userId', index:'userId', hidden: true, key: true},
			{label:'用户名', name:'username', index:'username', width:100, align:'center'},
			{label:'权限1', name:'q1', index:'q1',width:100,align:'center'},
			{label:'权限2', name:'q2', index:'q2',width:100,align:'center'},
			{label:'操作', width:150,align:'left',formatter: btnFormat}
		];
	grid = $("#r_tb").jqGrid({
		colModel: columns,
		width:w,
		height:h,
		datatype: 'local',
		rownumbers: true,
		data:d,
		autowidth:true
	});
	
}

function btnFormat(cellvalue, options, rowObject){
	return ['<div class="btn-format"><span class="icon-d" title="删除">&#xe9ac;</span>',
			'&nbsp;<span class="icon-e" title="编辑" data-robj="',rowObject.userId,'">&#xe92d;</span></div>'].join('');
}

function createCombo(data, str){
	var btn_val = !str ? "添加" : "确定";
	var _html = ['<form class="sub-form" action="">',
				'<div class="newAdmin">',
				'<input type="text" placeholder="请输入弟子的法号" class="add-input" name="adminName">',
				' <input type="button" class="add-admin" value="',btn_val,'" id="sub_add" name="admin_qxs"></div>',
				'<input type="checkbox" checked="checked" name="" value=""><label>权限1</label><br>',
				'<input type="checkbox" checked="checked" name="" value=""><label>权限2</label><br>',
				'<input type="checkbox" checked="checked" name="" value=""><label>权限3</label><br>',
				'<input type="text" style="display:none;">',
				'<input type="hidden" name="userId" value="">',
				'</form>'].join('');
		dialog = $.ligerDialog.open({
			width:300,
			height:250,
			title: !str ? '新增管理员' : str,
			content: _html
		});

	$.post("/Taoism/user_findDiscipleList.action", {}, function(datas){
		var Data = [{"id":1,"username":"nmae"},{"id":2,"username":"hhh"},{"id":3,"username":"uii"},{"id":4,"username":"uyyi"}];//测试数据
		com_opt = {
			width:150,
			height:25,
			valueField: 'userId',
			textField: 'username',
			data:datas.u,
			is_clear:true,
			Illegal_input:function(){
	//   				$(".newAdmin").append("<div class='no-dizi' style='color:red;'>请输入弟子的法号</div>")
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
	});
	if(data){
		combo.setValue(data.userId);
		combo.setDisabled(true);
		$("input[name='userId']").val(data.userId);
		$(".add-input").attr("disabled", true);
	}
}

function initEvent(){
	$(".existed").on("click", ".icon-d", function(){
		if(confirm("确定要删除该管理员吗？")){
			var id = $(this).prev().attr('id');
			$.post('', {userid: id}, function(data){
				if(1){ //测试
					$(this).parents('li').remove();
				}
			});
		}
	});
	
	$(".add-admin").click(function(){
		createCombo();
	});
	
	$("body").on("click", ".icon-e", function(){//编辑管理员权限
		var id = $(this).data("robj"),
			data = grid.getRowData(id);
		createCombo(data, "修改管理员权限");
	});
	
	$("body").on("click", ".icon-d", function(){
		if(confirm("确定要删除该管理员吗？")){
			
		}
	});
	
	$("body").on("click", "#sub_add", function(){//添加管理员
		var new_admin = $(".add-input").val();
		if( new_admin== ''){
			alert("请输入弟子的法号！");
		}else{
			$(".sub-form").submit();
		}
	});
}