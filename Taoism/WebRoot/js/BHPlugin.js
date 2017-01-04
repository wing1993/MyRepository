/**
 * BHTips
 * 2016-11-21 lzb
 * */
;
(function($, window, document, undefined) {
	var BHTips = function(ele, options) {
		var g = this;
		var tips = $('body').find('#bh_tips_wrap');

		if(tips.length != 0) {
			tips.remove();
		}

		g.$element = ele;
		g.defOptions = {
			'type': 'success', //类型error、warn、success
			'time': 1000,//浮现、消失时间
			'top': 0,//距离顶部位置
			'content': '操作成功'//提示内容
		};
		g.options = $.extend({}, g.defOptions, options);
		g.createTips();
	};

	BHTips.prototype.createTips = function() {
		var g = this,
			opt = g.options;
		var color = '#48C157',
			imgPosition = '-42px';
		var tHtml = ['<div id="bh_tips_wrap" class="bh-tips-wrap bh-tips-' + opt.type + '">',
			'<div class="bh-tips-icon">',
			'</div>',
			'<div class="bh-tips-prompt">',
			opt.content,
			'</div>',
			'</div>'
		].join('');

		var $tips = $(tHtml);

		if(opt.type == 'warn') {
			color = '#FF6701';
			imgPosition = '0px';
		} else if(opt.type == 'error') {
			color = '#ff4e44';
			imgPosition = '-22px';
		}

		$('body').append($tips);
		var w = $tips.width();
		$tips.css({
			'top': opt.top + 'px',
			'margin-left': w / -2 + 'px',
			'color': color,
			'border-color': color
		});
		$tips.find('.bh-tips-icon').css({
			'background-position-x': imgPosition
		});

		$tips.fadeIn(opt.time, function() {
			setTimeout(function() {
				$tips.fadeOut(opt.time, function() {
					$tips.remove();
				});
			}, 1000);
		});

	};

	//在插件中使用BHTips对象
	$.fn.BHTips = function(options) {
		//创建BHGrid的实体
		var bhTips = new BHTips(this, options);
		return bhTips;
	};
})(jQuery, window, document);

/**
 * BHDialog
 * 自定义弹出
 * 2016-11-22 lzb
 * */
;
(function($, window, document, undefined) {

	var BHDialog = function(ele, options) {
		var g = this;
		var time = new Date(),
			dialogID = time.getTime();
		g.defOptions = {
			id: dialogID,
			name: 'BHDialog_' + dialogID,
			cls: 'BHDialog',
			width: null,
			height: null,
			title: '',
			url: '',
			content: '',
			hidenHead: false, //隐藏头部
			hidenFooter: true, //隐藏底部
			headBtn: { //为null默认3个按钮且可使用cancelFun、saveFun、addFun事件
				left: [{
					name: 'cancel',
					text: '取消',
					cls: 'BHdialog-cancel-btn',
					format: null,
					clickFun: function() {
						g.dialog.close();
					}
				}],
				right: [{
					name: 'save',
					text: '保存',
					cls: 'BHdialog-cancel-save',
					format: null,
					clickFun: null
				}, {
					name: 'add',
					text: '继续添加',
					cls: 'BHdialog-cancel-add',
					format: null,
					clickFun: null
				}]
			},
			cancelFun: function() {
				g.dialog.close();
			},
			saveFun: null,
			addFun: null
		};
		g.defHeadLBtn = [{
			name: 'cancel',
			text: '取消',
			cls: 'BHdialog-cancel-btn',
			format: null,
			clickFun: null
		}];
		g.defHeadRBtn = [{
			name: 'save',
			text: '保存',
			cls: 'BHdialog-cancel-save',
			format: null,
			clickFun: null
		}, {
			name: 'add',
			text: '继续添加',
			cls: 'BHdialog-cancel-add',
			format: null,
			clickFun: null
		}];
		g.defBtnOptions = {
			name: '', //按钮字段名
			text: '', //显示文本
			format: null, //样式返回html
			clickFun: null //单击
		};

		if(options && options.headBtn) {
			if(options.headBtn.left) {
				var left = options.headBtn.left;
				for(var i = 0; i < left.length; i++) {
					var lBtnData = $.extend({}, g.defBtnOptions, left[i]);
					options.headBtn.left[i] = lBtnData;
				}
			}

			if(options.headBtn.right) {
				var right = options.headBtn.right;
				for(var i = 0; i < right.length; i++) {
					var rBtnData = $.extend({}, g.defBtnOptions, right[i]);
					options.headBtn.right[i] = rBtnData;
				}
			}

		}

		g.options = $.extend({}, g.defOptions, options);

		g.dialog = $.ligerDialog.open(g.options);
		g.$element = $('#' + dialogID);
		g.createHead();
		g.initEvent();
	};

	BHDialog.prototype.createHead = function() {
		var g = this,
			$element = g.$element,
			opt = g.options,
			headLBtn = opt.headBtn && opt.headBtn.left ? opt.headBtn.left : g.defHeadLBtn,
			headRBtn = opt.headBtn && opt.headBtn.right ? opt.headBtn.right : g.defHeadRBtn;

		if(headLBtn.length != 0) {
			var lBth = '<div class="BHdialog-head-left">';
			lBth += g.btnHtml(headLBtn);
			lBth += '</div>';
			$element.find('.l-dialog-tc-inner').append($(lBth));
		}

		if(headRBtn.length != 0) {
			var rBth = '<div class="BHdialog-head-right">';
			rBth += g.btnHtml(headRBtn);
			rBth += '</div>';
			$element.find('.l-dialog-tc-inner').append($(rBth));
		}

	};

	BHDialog.prototype.initEvent = function() {
		var g = this,
			opt = g.options,
			$element = g.$element,
			lBtnData = opt.headBtn ? opt.headBtn.left : g.defHeadLBtn,
			rBtnData = opt.headBtn ? opt.headBtn.right : g.defHeadRBtn;

		$element.find('.BHdialog-head-left .BHdialog-btn-wrap').children().click(function() {
			var id = $(this).attr('id');
			for(var i = 0; i < lBtnData.length; i++) {
				if(id == lBtnData[i].name) {
					if(opt.headBtn) {
						lBtnData[i].clickFun && lBtnData[i].clickFun();
					} else {
						opt.cancelFun && opt.cancelFun();
					}
					break;
				}
			}
		});

		$element.find('.BHdialog-head-right .BHdialog-btn-wrap').children().click(function() {
			var id = $(this).attr('id');
			for(var i = 0; i < rBtnData.length; i++) {
				if(id == rBtnData[i].name) {
					if(opt.headBtn) {
						rBtnData[i].clickFun && rBtnData[i].clickFun();
					} else {
						if(rBtnData[i].name == 'save') {
							opt.saveFun && opt.saveFun();
						} else {
							opt.addFun && opt.addFun();
						}
					}
					break;
				}
			}
		});
	};

	BHDialog.prototype.btnHtml = function(data) {
		var btnHtml = '';
		for(var i = 0; i < data.length; i++) {

			var name = data[i].name,
				text = data[i].text,
				cls = data[i].cls,
				format = data[i].format;
			btnHtml += '<div class="BHdialog-btn-wrap">';

			if(!format) {
				btnHtml += '<input type="button" id="' + name + '" class="ui-button ' + cls + '" value="' + text + '"/>';
			} else {
				btnHtml += data[i].format();
			}

			btnHtml += '</div>';
		}

		return btnHtml;
	};

	$.fn.BHDialog = function(options) {
		//创建BHGrid的实体
		var bhDialog = new BHDialog(this, options);
		return bhDialog.dialog;
	};
})(jQuery, window, document);

/**
 * BHTree
 * 2016-11-21 lzb
 * */
;
(function($, window, document, undefined) {
	/**
	 * @param ele---树状容器  options---配置信息 
	 * */
	var BHTree = function(ele, options) {
		var g = this;
		g.defOptions = {
			data: [],
			width: 0,
			height: 0,
			nodeIdFiled: 'id', //节点
			nodeNameFiled: 'name', //节点名称字段
			pNodeIdFiled: 'pId', //父节点
			expandAll: true, //是否全部展开
			selectedMulti:false,//多选
			toolbarDisplay:false,//底部工具栏显示(为false时悬停显示)
			treeOperationUrl: '', //树状操作请求地址
			opeAddAttach: {}, //相关请求传给后台的附带数据
			opeEditAttach: {},
			opeDelAttach: {},
			beforeClick: function(treeId, treeNode, clickFlag) { //treeId:对应 zTree 的 treeId treeNode:被点击的节点 JSON 数据对象 clickFlag:节点被点击后的选中操作类型
				return g.beforeClick(treeId, treeNode, clickFlag);
			},
			onClick: function(event, treeId, treeNode) {
				setTimeout(function() {
					if(g.eventStatus == 'click') { //eventStatus 用于区分单击和双击事件
						g.onClick(event, treeId, treeNode, options.onSelectNode);
					}
				}, 300);

			},
			beforeDblClick: function(treeId, treeNode, clickFlag) {
				return g.beforeDblClick(treeId, treeNode, clickFlag);
			},
			onDblClick: function(event, treeId, treeNode) {
				if(g.eventStatus == 'dbClick') {
					g.onDblClick(event, treeId, treeNode);
				}
			},
			onRightClick: function(event, treeId, treeNode) { //右键
				g.eventStatus = 'onRightClick';
				g.onRightClick(event, treeId, treeNode);
			},
			beforeDrag: function(treeId, treeNodes) {

			},
			beforeDrop: function(treeId, treeNodes, targetNode, moveType) {
				return g.beforeDrop(treeId, treeNodes, targetNode, moveType);
			},
			onDrop: function(event, treeId, treeNodes, targetNode, moveType) { //拖拽操作结束的事件回调
				g.onDrop(event, treeId, treeNodes, targetNode, moveType);
			},
			//自定义事件
			onSelectNode: null,
			beforeAdd:null,//添加前
			beforeEdit:null,
			beforeDel:null
		};
		g.options = $.extend({}, g.defOptions, options);
		g.setting = {
			edit: {
				enable: true,
				showRemoveBtn: false, //是否显示节点右侧删除按钮
				showRenameBtn: false
			},
			view: {
				selectedMulti: g.options.selectedMulti, //是否允许同时选中多个节点(为true时按ctrl可多选)
				showLine: false, //是否显示节点之间的连线（为true还需修改css中的图片路劲）
				showIcon: false //是否显示节点图标
			},
			data: {
				key: {
					name: g.options.nodeNameFiled
				},
				simpleData: {
					enable: true, //使用 /不使用 简单数据模式
					idKey: g.options.nodeIdFiled, //节点
					pIdKey: g.options.pNodeIdFiled, //父节点
					rootPId: null
				}
			},
			callback: {
				beforeClick: g.options.beforeClick, //点击前回调
				onClick: g.options.onClick, //点击回调
				beforeDblClick: g.options.beforeDblClick, //双击前回调
				onDblClick: g.options.onDblClick, //双击		
				onRightClick: g.options.onRightClick,
				beforeDrag: g.options.beforeDrag, //拖拽前(用于是否开启拖拽)
				beforeDrop: g.options.beforeDrop, //节点拖拽操作结束之前的事件回调
				onDrop: g.options.onDrop
			}
		};

		g.options.width == 0 && (g.options.width = ele.width());
		g.options.height == 0 && (g.options.height = ele.height());
		g.$element = ele;
		g.zNodes = g.options.data;
		g.init();
	};

	BHTree.prototype.init = function() {
		var g = this;
		g.createRightMenu();
		g.createBHTree();
		g.BHTreeInit();
		g.Event();
	};

	BHTree.prototype.createRightMenu = function() {
		var g = this;
		g.rightMenu = $.ligerMenu({ //右键菜单
			top: 100,
			left: 100,
			width: 120,
			items: [{
				id: "tree_menu_add",
				text: '增加',
				click: menuItemClick
			}, {
				id: "tree_menu_edit",
				text: '修改',
				click: menuItemClick
			}, {
				id: "tree_menu_del",
				text: '删除',
				click: menuItemClick
			}]
		});

		/**
		 * 树状右键菜单事件
		 * */
		function menuItemClick(item) {
			var nodeData = g.rightMenuNode;
			var id = item.id;

			switch(id) {
				case "tree_menu_add":
					g.addNode(nodeData);
					break;
				case "tree_menu_edit":
					if(!!nodeData.parentTId) {
						g.editNode(nodeData);
					} else {
						$.fn.BHTips({
							type: 'warn',
							content: '根节点不允许修改'
						});
					}
					break;
				case "tree_menu_del":
					if(!!nodeData.parentTId) {
						g.delNode(nodeData);
					} else {
						$.fn.BHTips({
							type: 'warn',
							content: '根节点不允许删除'
						});
					}
					break;
				default:
					break;
			}
		};

	};

	/**
	 * 创建树状容器
	 * */
	BHTree.prototype.createBHTree = function() {
		var g = this;
		var $element = g.$element,
			opt = g.options,
			id = $element.attr('id'),
			treeId = 'BH_' + id;
		g.treeId = treeId;
		var bWidth = 2, //边框宽度
			tBottom = 30; //底部按钮高度
		var container = [
			'<div class="bh-tree-container">',
			'	<div class="bh-tree-top">',
			'		<ul id="' + treeId + '" class="ztree"></ul>',
			'	</div>',
			'	<div class="bh-tree-bottom">',
			'		<table border="0" cellspacing="0" cellpadding="0">',
			'			<tr>',
			'				<td>',
			'					<div class="tree-btn-wrap tree-add-btn">',
			'					</div>',
			'				</td>',
			'				<td>',
			'					<div class="tree-btn-wrap tree-edit-btn">',
			'					</div>',
			'				</td>',
			'				<td>',
			'					<div class="tree-btn-wrap tree-del-btn">',
			'					</div>',
			'				</td>',
			'			</tr>',
			'		</table>',
			'	</div>',
			'</div>'
		].join('');
		$element.append($(container));
		$element.find('.bh-tree-container').css({
			'width': g.options.width - bWidth + 'px'
		});
		g.options.height != 0 && ($element.find('.bh-tree-container').css({
			'height': g.options.height - bWidth + 'px'
		}), $element.find('.bh-tree-top').css({
			'height': g.options.height - bWidth - 30 + 'px'
		}));
		$element.find('.bh-tree-bottom').css({
			'height': tBottom + 'px',
			'visibility':opt.toolbarDisplay ? 'visible' : 'hidden'
		});
	};

	/**
	 * 创建zTree
	 * */
	BHTree.prototype.BHTreeInit = function() {
		var g = this;
		g.treeObj = $.fn.zTree.init($('#' + g.treeId), g.setting, g.zNodes);
		g.options.expandAll && g.treeObj.expandAll(true);
//		return tree;
	};

	BHTree.prototype.Event = function() {
		var g = this,
			$element = g.$element,
			opt = g.options;
			
		if(!opt.toolbarDisplay){
			//底部按钮显隐
			$element.find('.bh-tree-container').on('mouseover', function(e) {
				$element.find('.bh-tree-bottom').css('visibility', 'visible');
			}).on('mouseout', function(e) {
				$element.find('.bh-tree-bottom').css('visibility', 'hidden');
			});
		}

		//底部按钮单击
		$element.find('.tree-btn-wrap').on('click', function(e) {
			var $this = $(this);
			var treeObj = g.treeObj;
			var nodes = treeObj.getSelectedNodes();

			if($this.hasClass('tree-add-btn')) {
				var nodeData = nodes[0];
				if(nodes.length == 0) {
					var pNode = treeObj.getNodesByFilter(function(node) {
						return node.level == 0
					}, false); //返回一个根节点  
					nodeData = pNode[0];
				}
				g.addNode(nodeData);
			} else if($this.hasClass('tree-edit-btn')) {
				if(nodes.length != 0) {
					if(nodes[0].parentTId) {
						g.editNode(nodes[0]);
					} else {
						$.fn.BHTips({
							type: 'warn',
							content: '根节点不允许修改'
						});
					}
				} else {
					$.fn.BHTips({
						type: 'warn',
						content: '选择修改根节点'
					});
				}
			} else if($(this).hasClass('tree-del-btn')) {
				if(nodes.length != 0) {
					if(nodes[0].parentTId) {
						g.delNode(nodes[0]);
					} else {
						$.fn.BHTips({
							type: 'warn',
							content: '根节点不允许删除'
						});
					}
				} else {
					$.fn.BHTips({
						type: 'warn',
						content: '选择删除的节点'
					});
				}
			}
		});
	};

	/**
	 * 树状事件回调函数
	 * @param treeId:对应 zTree 的 treeId 
	 * treeNode:被点击的节点 JSON 数据对象 
	 * clickFlag:节点被点击后的选中操作类型
	 * */
	BHTree.prototype.beforeClick = function(treeId, treeNode, clickFlag) {
		var g = this;
		var status = true;
		status && (g.eventStatus = 'click');
		return status;
	};

	BHTree.prototype.onClick = function(event, treeId, treeNode, selectFun) {
		if(!!selectFun) {
			selectFun(event, treeId, treeNode);
		}
	};

	BHTree.prototype.beforeDblClick = function(treeId, treeNode, clickFlag) {
		var g = this;
		var status = true;
		status && (g.eventStatus = 'dbClick');
		return status;
	};

	BHTree.prototype.onDblClick = function(event, treeId, treeNode) {
		if(!!treeNode.parentTId) {
			this.editNode(treeNode);
		} else {
			$.fn.BHTips({
				type: 'warn',
				content: '不允许修改根节点'
			});
		}
	};

	BHTree.prototype.onRightClick = function(event, treeId, treeNode) {
		var g = this;
		g.rightMenuNode = treeNode;
		g.rightMenu.show({
			top: event.pageY,
			left: event.pageX
		});
	};
	BHTree.prototype.beforeDrop = function(treeId, treeNodes, targetNode, moveType) {
		if(!targetNode || (!targetNode.parentTId && moveType != 'inner')) { //禁止在根节点进行prev next的拖拽类型操作
			return false;
		}
		return true;

	};

	BHTree.prototype.onDrop = function(event, treeId, treeNodes, targetNode, moveType) {
		var g = this;
		var options = g.options;
		var treeObj = g.treeObj;
		var nodeData = treeNodes[0];
		var idFiled = options.nodeIdFiled,
			pIdFiled = options.pNodeIdFiled,
			nameFiled = options.nodeNameFiled,
			treeOperationUrl = options.treeOperationUrl;
		var postData = {
			'operation_type': 'edit',
			'type': nodeData.treeType
		};
		postData[idFiled] = nodeData[idFiled];
		postData[nameFiled] = nodeData[nameFiled];
		postData[pIdFiled] = nodeData[pIdFiled];
		postData.level = nodeData.level;

		$.ajaxPost(treeOperationUrl, postData, function(data) {
			if(data.status == 200) {
				inquiry.close();
			} else {

			}
		});

	};

	/**
	 * 节点操作
	 * */
	BHTree.prototype.addNode = function(nodeData) {
		var g = this;
		var options = g.options;
		var treeObj = g.treeObj;
		var idFiled = options.nodeIdFiled,
			pIdFiled = options.pNodeIdFiled,
			nameFiled = options.nodeNameFiled,
			treeOperationUrl = options.treeOperationUrl;
		var inquiryDialog;
		var html = ['<div class="new-node" style="padding: 10px 18px;">',
			'<span>',
			'新分类：',
			'</span>',
			'<input type="text" id="new_node" class="ui-inputtext" style="width:160px"/>',
			'</div>'
		].join('');
		var opt = {
			btnAlign: 'center',
			content: html,
			confirmFun: function() {
				var parentTId = nodeData.parentTId;
				var postData = options.opeAddAttach;
				var cNode = [];
				var inquiry = false;
				var newNodeName = $.trim($('#new_node').val());
				if(!!options.beforeAdd){//用于是否阻止添加操作判断
					var s = options.beforeAdd(nodeData);
//					console.log(s);
					if(!s){
						$.fn.BHTips({
							'type': 'warn',
							'content': '只允许添加一级节点'
						});
						inquiryDialog.close();
						return s;
					}					
				}
				
				if(newNodeName == '') {
					return false;
				}
				
				if(!!parentTId) { //非根节点
					var pNode = nodeData.getParentNode();
					postData[pIdFiled] = nodeData[idFiled];
					cNode = !!pNode.children ? pNode.children : [];
				} else { //根节点
					postData[pIdFiled] = nodeData[idFiled];
					cNode = !!nodeData.children ? nodeData.children : [];
				}
				for(var i = 0; i < cNode.length; i++) {
					if(newNodeName == cNode[i][nameFiled]) {
						inquiry = true;
						break;
					}
				}

				if(!inquiry) {
					postData[nameFiled] = newNodeName;
					$.ajaxPost(treeOperationUrl, postData, function(data) {
						if(data.status == 200) {
//							postData[idFiled] = data.data[idFiled];
							postData[idFiled] = data.data;
							treeObj.addNodes(nodeData, postData);
							inquiryDialog.close();
						} else {
							$.fn.BHTips({
								'type': 'success',
								'content': '操作成功'
							})
						}
					});
				} else {
					$.fn.BHTips({
						type: 'warn',
						content: '同一级的树目录，不允许出现同名的分类'
					});
					$('#new_node').select();
				}											
				
			}
		}
		inquiryDialog = $.inquiryDialog(opt);
		$('#new_node').focus();
	};

	BHTree.prototype.editNode = function(nodeData) {
//		if(!!this.options.beforeEdit){//用于是否阻止添加操作判断
//			var s = this.options.beforeEdit(nodeData);
//			return s;
//		}
		var g = this;
		var options = g.options;
		var treeObj = g.treeObj;
		var idFiled = options.nodeIdFiled,
			pIdFiled = options.pNodeIdFiled,
			nameFiled = options.nodeNameFiled,
			treeOperationUrl = options.treeOperationUrl;
		var inquiryDialog;
		var html = ['<div class="new-node" style="padding: 10px 18px;">',
			'<span>',
			'新分类：',
			'</span>',
			'<input type="text" id="new_node" class="ui-inputtext" style="width:160px" value="' + nodeData.name + '"/>',
			'</div>'
		].join('');
		var opt = {
			btnAlign: 'center',
			content: html,
			confirmFun: function() {
				var newNodeName = $.trim($('#new_node').val());
				var identical = false;
				var pNode = nodeData.getParentNode();
				var cNode = pNode.children;

				if(newNodeName == '') {
					return false;
				}

				for(var i = 0; i < cNode.length; i++) {
					if(newNodeName == cNode[i][nameFiled]) {
						identical = true;
						break;
					}
				}

				if(!identical) {
					var postData = options.opeEditAttach;
					postData[idFiled] = nodeData[idFiled];
					postData[pIdFiled] = nodeData[pIdFiled];
					postData[nameFiled] = newNodeName;

					$.ajaxPost(treeOperationUrl, postData, function(data) {
						if(data.status == 200) {
							nodeData.name = newNodeName;
							treeObj.updateNode(nodeData);
							inquiryDialog.close();
						} else {

						}
					});
				} else {
					$.fn.BHTips({
						type: 'warn',
						content: '同一级的树目录，不允许出现同名的分类'
					});
					$('#new_node').select();
				}

			}
		};
		inquiryDialog = $.inquiryDialog(opt);
		$('#new_node').select();
	};

	BHTree.prototype.delNode = function(nodeData) {
//		if(!!this.options.beforeDel){//用于是否阻止添加操作判断
//			var s = this.options.beforeDel(nodeData);
//			return s;
//		}
		var g = this;
		var treeObj = g.treeObj;
		var options = g.options,
			idFiled = options.nodeIdFiled,
			treeOperationUrl = options.treeOperationUrl;
		var inquiryDialog;
		var opt = {
			btnAlign: 'center',
			content: '您确定要删除：【' + nodeData.name + ' 】节点吗？',
			confirmFun: function() {
				if(!nodeData.isParent) {
					var postData = options.opeDelAttach;
					postData[idFiled] = nodeData[idFiled];
					$.ajaxPost(treeOperationUrl, postData, function(data) {
						if(data.status == 200) {
							treeObj.removeNode(nodeData);
							inquiryDialog.close();
						} else {
							$.fn.BHTips({
								type: 'warn',
								content: data.msg
							});
						}
					});
				} else {
					$.fn.BHTips({
						type: 'warn',
						content: '父节点不允许删除'
					});
				}
			}
		}
		inquiryDialog = $.inquiryDialog(opt);
	};

	BHTree.prototype.refresh = function(data){
		var g = this;
			g.zNodes = data;
		g.treeObj = $.fn.zTree.init($('#' + g.treeId), g.setting, g.zNodes);
		
	}

	//在插件中使用BHGrid对象
	$.fn.BHTree = function(options) {
		//创建BHGrid的实体
		var bhTree = new BHTree(this, options);
		return bhTree;
	};

})(jQuery, window, document);

/**
 * BHGrid
 * 2016-11-21 lzb
 * */
;
(function($, window, document, undefined) {
	var GOBJ;
	var BHGrid = function(ele, opt) {
		var g = this;
		var colModel = opt.colModel ? opt.colModel : [],
			gridHeads = opt.gridHeads ? opt.gridHeads : [],
			summary = opt.summaryCOl ? opt.summaryCOl : [];
		rowToolbar = opt.rowToolbar ? opt.rowToolbar : [];
		GOBJ = g;
		g.$element = ele;
		//表格默认设置
		g.defOptions = {
			//jqgrid属性
			datatype: 'local', //加载本地数据
			data: [], //表格数据(arr)
			colModel: [], //列配置
			width: '100%', //（宽度不能小于冻结列的总宽度）
			height: '100%',
			shrinkToFit: false, //如果为false，则列宽度使用colModel指定的宽度
			rownumbers: true, //是否出现行号
			rownumWidth: 60, //行号宽度
			rowHeight: 28, //行高
			multiselect: false, //多选(复选框)
			hoverrows: true, //行悬停
			altRows: true, //交替行
			altclass: '', //交替行class(可改变行背景色)
			multiSort: false, //多列排序（&&）
			sortable: false, //是否列排序(不能用于多表头拖动)
			sortname: 'id', //排序列
			sortorder: 'asc', //排序方式 desc asc
			footerrow: false, //统计行
			userDataOnFooter: true, // 当为true时把userData放到底部					
			cellEdit: false, //单元格编辑
			cellsubmit: 'clientArray',
			pgbuttons: false, //不分页
			rowNum: 9999999999999, //显示全部数据
			formatCell: null,
			beforeEditCell: null,
			afterEditCell: null,
			beforeSaveCell: null,
			beforeSubmitCell: null,
			afterSaveCell: null,
			gridComplete: g.gridComplete, //当表格所有数据都加载完成而且其他的处理也都完成时触发此事件
			beforeSelectRow: g.beforeSelectRow, //(不能修改)
			onSelectRow: null, //当选择行时触发此事件
			ondblClickRow: null, //双击行时触发
			onRightClickRow: null, //右击
			beforeEditCell: null, //在编辑单元格前触发
			//自定义属性
			hideColMenuMaxHeight:200,//显隐菜单最大高度
			hideColMenuWidth:100,
			openHideColMenu: false, //开启显隐菜单
			openSortMenu: false, //开启排序菜单
			openSearchOperators: false, //是否开启过滤行
			openRowToolbar: false, //开启行工具栏
			rowToolbar: [], //工具栏配置
			gridHeads: [], //多表头设置(只支持2行表头)
			summaryCOl: [], //汇总列
			merCondition: [],//重新合并单元格事件
			hide_cb: false, //是否隐藏复选框
		};
		//列默认设置
		g.defColOptions = {
			label: '', //列的显示名称
			name: '', //关键字(与index值一致)
			index: '', //索引
			width: 60, //列宽
			hidden: false, //是否隐藏（不能隐藏冻结列）
			frozen: false, //冻结列（不允许编辑）
			resizable: true, //拖拽改变列宽
			sortable: false, //是否可排序
			sorttype: 'text', //用在当datatype为local时，定义搜索列的类型，可选值：int/integer - 对integer排序float/number/currency - 排序数字date - 排序日期text - 排序文本
			title: false, //是否添加title属性(用于提示)
			editable: false, //单元格是否可编辑
			edittype: 'text', //可以编辑的类型。可选值：text, textarea, select, checkbox, password, button, image and file.
			search: true, //此列是否可以作为搜索列
			stype: 'text', //定义搜索元素的类型  number select
			searchoptions: { //过滤列配置
				searchOperMenu: true, //过滤菜单
				clearSearch: false, //清除按钮
				sopt: ['eq', 'ne', 'le', 'lt', 'gt', 'ge', 'bw', 'bn', 'cn', 'nc', 'ew', 'en'], //过滤条件	
				//				sopt:['cn'],//过滤条件包含
				value: ':全部' //当stype 为select是的下拉值 ':全部;val:val;val2:val2;'
			}, //过滤条件
		};

		//多表头设置
		g.defGridHeadsItem = {
			useColSpanStyle: true,
			groupHeaders: []
		};

		g.defGroupHeaders = {
			numberOfColumns: 0,
			titleText: '',
			startColumnName: ''
		};

		//汇总列默认设置
		g.defSummary = {
			label: '',
			strLen: '', //字符串长度
			name: null,
			type: null, //汇总类型 avg  sum count max min 若没有设置类型则只写入label 的值
			format:null//样式
		};

		//工具栏默认设置
		g.defRowToolbar = {
			type: '',
			tClass: '',
			clickFun: null
		};

		g.options = $.extend({}, g.defOptions, opt);
		g.hideColMenuData = [];
		g.frozenColData = [];
		for(var i = 0; i < colModel.length; i++) {
			var colData = colModel[i];
			g.hideColMenuData.push({
				label: colData.label,
				name: colData.name,
				hidden: colData.hidden
			});
			g.options.colModel[i] = $.extend({}, g.defColOptions, colData);
			if(colData.frozen) {
				g.frozenColData.push(colData.name);
			}
		}

		for(var i = 0; i < gridHeads.length; i++) {
			var itemJson = $.extend({}, g.defGridHeadsItem, gridHeads[i]);
			for(var j = 0; j < itemJson.groupHeaders.length; j++) {
				itemJson.groupHeaders[j] = $.extend({}, g.defGroupHeaders, itemJson.groupHeaders[j]);
			}
			g.options.gridHeads[i] = itemJson;
		}

		for(var i = 0; i < summary.length; i++) {
			g.options.summaryCOl[i] = $.extend({}, g.defSummary, summary[i]);
		}

		for(var i = 0; i < rowToolbar.length; i++) {
			g.options.rowToolbar[i] = $.extend({}, g.defRowToolbar, rowToolbar[i]);
		}

		g.init();
	};

	BHGrid.prototype.init = function() {
		//一下顺序不能变
		var g = this,
			opt = g.options;
		g.creatGrid();
		g.setGridHeaders();
		g.setFooterData();
		//首行筛选栏
		//searchOperators 过滤条件  可以通过colModel的stype 和 searchoptions设置下拉  searchOperators设置过滤条件(操作符)
		opt.openSearchOperators && g.grid.filterToolbar({
			searchOperators: true,
			resetTitle: ''
		});
		//设置冻结列(需要在最后设置)
		g.grid.setFrozenColumns();
		g.initEvent();
	};

	BHGrid.prototype.creatGrid = function() {
		var g = this,
			$element = g.$element,
			opt = g.options,
			id = $element.attr('id'),
			tID = 'BH_table_' + id;
		var gHtml = ['<div class="BHgrid-container-wrap">',
			'<table id="' + tID + '">',
			'</table>',
			'<div class="hideColMenu-wrap" style="width:'+ opt.hideColMenuWidth +'px;max-height:'+ opt.hideColMenuMaxHeight +'px;overflow:auto;">',
			'</div>',
			'<div class="sortMenu-wrap">',
			'<ul>',
			'<li class="sort-item" data-sort-type="asc">',
			'<div class="sort-item-img sort-asc">',
			'</div>',
			'<div>',
			'升序(p)',
			'</div>',
			'</li>',
			'<li class="sort-item" data-sort-type="desc">',
			'<div class="sort-item-img sort-desc">',
			'</div>',
			'<div>',
			'降序(Q)',
			'</div>',
			'</li>',
			'<li class="sort-item" data-sort-type="clear">',
			'<div class="sort-item-img">',
			'</div>',
			'<div>',
			'清除排序',
			'</div>',
			'</li>',
			'</ul>',
			'</div>',
			'</div>'
		].join('');
		$element.append($(gHtml));
		g.$table = $('#' + tID);
		g.grid = $('#' + tID).jqGrid(g.options);
		if(g.options.hide_cb){
			g.grid.jqGrid('hideCol', 'cb');
		}
	};

	/**
	 * 设置多表头
	 * */
	BHGrid.prototype.setGridHeaders = function() {
		var g = this,
			opt = g.options,
			grid = g.grid,
			gridHeads = g.options.gridHeads,
			len = gridHeads.length;

		if(len != 0) {
			for(var i = 0; i < len; i++) {
				grid.setGroupHeaders(gridHeads[i]);
			}
		}
		//		g.hackHeight(grid);
	};

	/**
	 * 终极hack列冻结导致的高度问题()
	 */
	//	BHGrid.prototype.hackHeight = function(grid) {
	//		var id = grid.attr('id'),
	//			$nTrs = grid.find('tr.jqgrow'),
	//			$fTrs = $('#' + id + '_frozen tr.jqgrow');
	//			for(var i = 0; i < $fTrs.length; i++){
	//				var ntdH = parseFloat($($nTrs[i]).children().first().height());
	//				var ftdH = parseFloat($($fTrs[i]).children().first().height());
	//				console.log(ntdH + ' ' + ftdH);
	//				var spance = ftdH - ntdH;
	//				if(ntdH != ftdH){
	//					$($fTrs[i]).children().css({'height':ntdH + 'px'});
	////					$($fTrs[i]).find('td').css({'height': ntdH - spance + 'px'});
	//				}
	//			}
	//			
	//	 
	//	};

	BHGrid.prototype.initEvent = function() {
		var g = this,
			opt = g.options,
			grid = g.grid,
			$element = g.$element;

		//行号
		$element.on('click', '.ui-jqgrid-htable th[id$="_rn"]', function(e) {
			e.stopPropagation();
			if(opt.openHideColMenu) {
				var $colMenu = $element.find('.hideColMenu-wrap');
				if($colMenu.is(':hidden')) {
					var menuData = g.hideColMenuData;
					var frozenCol = g.frozenColData;
					var h = $(this).height(),
						w = $(this).width() - 10,
						x = $(this).offset().left,
						y = $(this).offset().top + h;
					var menuHtml = '<ul>';
					for(var i = 0; i < menuData.length; i++) {
						var col = menuData[i];
						var frozen = false;
						if(col.name == 'BH_row_id'){//自定义行id不需要
							continue;
						}
						for(var j = 0; j < frozenCol.length; j++) {
							if(frozenCol[j] == col.name) {
								frozen = true; //冻结列不能隐藏
								break;
							}
						}

						var item = ['<li class="hide-menu-item">',
							'<div class="checkbox-wrap">',
							'<input role="checkbox" type="checkbox"  data-Cname="' + col.name + '" ' + (col.hidden ? 'checked="checked"' : '') + ' ' + (frozen ? 'disabled' : '') + '>',
							'</div>',
							'<div class="col">',
							col.label,
							'</div>',
							'</li>'
						].join('');
						menuHtml += item;
					}
					menuHtml += '</ul>';
					$colMenu.html(menuHtml).css({
						'top': y + 'px',
						'left': x + 'px',
						'min-width': w + 'px'
					}).show();
				} else {
					$colMenu.hide();
				}
			}
		});

		$element.on('mousedown', '.ui-jqgrid-htable th', function(e) {
			var $searchTabel = $(this).find('.ui-search-table');
			if(opt.openSortMenu && $searchTabel.length == 0) {
				var $sortMenu = $element.find('.sortMenu-wrap');
				var id = $(this).attr('id');
				if(id && 3 == e.which) {
					var $hideColMenu = $element.find('.hideColMenu-wrap');
					var idArr = id.split('_'),
						cName = idArr[idArr.length - 1];

					!$hideColMenu.is(':hidden') && $hideColMenu.hide();

					if(cName != 'rn' && cName != 'cb') {
						var e = e || window.event,
							x = e.pageX + 1,
							y = e.pageY + 1,
							w = document.body.clientWidth,
							h = document.body.clientHeight;
						x = (x + 81) < w ? x : (w - 81);
						y = (y + 81) < h ? y : (h - y);
						$sortMenu.data('colName', cName).css({
							top: y + 'px',
							left: x + 'px'
						}).show();
						return false;
					}
				}

				if(!$sortMenu.is(':hidden')) {
					$sortMenu.hide();
				}
			}
		});

		//阻止浏览器默认右键点击事件
		$(".ui-jqgrid-htable *").bind("contextmenu", function() {
			return false;
		});

		//显隐菜单
		$element.on('click', '.hide-menu-item :checkbox', function(e) {
			var menuData = g.hideColMenuData;
			var cName = $(this).data('cname') + '';
			var status = $(this).is(':checked');
			if(status) {
				console.log(cName);
				grid.hideCol(cName);
			} else {
				grid.showCol(cName);
			}

			for(var i = 0; i < menuData.length; i++) {
				if(menuData[i].name == cName) {
					menuData[i].hidden = status;
					break;
				}
			}

		});

		//排序菜单
		$element.on('click', '.sortMenu-wrap .sort-item', function(e) {
			var cName = $(this).parents('.sortMenu-wrap').data('colName');
			var sType = $(this).data('sort-type');
			if(sType == 'clear') {
				cName = opt.sortname;
				sType = opt.sortorder;
			}

			grid.setGridParam({
				sortname: cName,
				sortorder: sType
			}).trigger("reloadGrid");
			if(opt.merCondition){//合并单元格
				$.Mergers(grid, opt.merCondition);
			}
		});

		//行工具栏
		$element.on('mouseenter', '.ui-jqgrid-btable .jqgrow', function() {
			if(opt.openRowToolbar) {
				var tOpt = opt.rowToolbar;
				var defRowToolbar = [{
					type: 'add',
					tClass: 'bh-toolbar-add',
					clickFun: null
				}, {
					type: 'edit',
					tClass: 'bh-toolbar-edit',
					clickFun: null
				}, {
					type: 'del',
					tClass: 'bh-toolbar-del',
					clickFun: null
				}];
				if(tOpt.length == 0) {
					tOpt = defRowToolbar;
					opt.rowToolbar = defRowToolbar;
				}
				g.creatRowToolbar($(this), tOpt, $element);
			}
			opt.hoverrows && $(this).addClass('ui-state-hover');
		});

		$element.on('mouseleave', '.ui-jqgrid-btable .jqgrow', function() {
			var $tr = $(this);
			var id = $tr.attr('id'),
				$frozenBdiv = $element.find('.frozen-bdiv');
			$frozenBdiv.length != 0 && ($tr = $frozenBdiv.find('tr[id="' + id + '"]'));
			if(opt.openRowToolbar) {
				var $toolbar = $tr.find('.row-toolbar-wrap');
				var $td = $toolbar.parent(),
					rnum = $toolbar.data('rnum');
				rnum = rnum ? rnum : $td.attr('title');
				$td.html(rnum);
			}
			opt.hoverrows && $tr.removeClass('ui-state-hover');
		});

		//行工具栏按钮
		$element.on('mousedown', '.row-toolbar-wrap .row-toolbar-btn', function(e) {
			e.stopPropagation();
			if(e.which == 1) {
				var rowToolbar = opt.rowToolbar;
				var type = $(this).data('type');
				var rowID = $(this).parents('.jqgrow').attr('id');
				for(var i = 0; i < rowToolbar.length; i++) {
					var tJson = rowToolbar[i];
					if(tJson.type == type) {
						tJson.clickFun(rowID, grid, type);
						break;
					}
				}
			}
			return false;
		});

		$(document).mousedown(function(e) {
			var e = window.event || e; // 兼容IE7
			var obj = $(e.srcElement || e.target);
			var hideColMenu = $('.hideColMenu-wrap');
			var $sortMenu = $element.find('.sortMenu-wrap');
			if(!$sortMenu.is(':hidden') && !$(obj).is('.sortMenu-wrap,.sortMenu-wrap *')) {
				$sortMenu.hide();
			}

			if(!hideColMenu.is(':hidden') && !$(obj).is('.ui-jqgrid-htable th[id$="_rn"],.hideColMenu-wrap,.hideColMenu-wrap *')) {
				$('.hideColMenu-wrap').hide();
			}
		});
		
		//选中多行
		$("body").on("click", ".ui-jqgrid-bdiv table tr", function(e){
			if(window.event.ctrlKey){
				var rowID = $(this).attr("id");
				grid.setSelection(rowID, true);
			}
		});
	};

	/**
	 * 行工具栏
	 * $tr:行对象 tOpt:工具栏配置 $element:容器
	 * */
	BHGrid.prototype.creatRowToolbar = function($tr, tOpt, $element) {
		var id = $tr.attr('id'),
			$frozenBdiv = $element.find('.frozen-bdiv');
		$frozenBdiv.length != 0 && ($tr = $frozenBdiv.find('tr[id="' + id + '"]'));
		
		var $toolbar = $tr.find('.row-toolbar-wrap');
		if($toolbar.length != 0){
			return false;
		}
		
		var $td = $tr.find('.jqgrid-rownum'),
			rNum = $td.text(),
			len = tOpt.length;
		var tHtml = '<div class="row-toolbar-wrap" data-rnum = "' + rNum + '"><ul>';

		for(var i = 0; i < len; i++) {
			var tJson = tOpt[i];
			tHtml += '<li><div class="row-toolbar-btn ' + tJson.tClass + '" data-type="' + tJson.type + '"></div></li>';
		}

		tHtml += '</ul></div>';
		$td.html(tHtml);
		var w = $td.find('.row-toolbar-wrap').width(),
			liW = w / len;
		$td.find('.row-toolbar-wrap li').css({
			'width': liW + 'px'
		});

	};

	/**
	 * 汇总
	 * */
	BHGrid.prototype.setFooterData = function() {
		var g = this,
			grid = g.grid,
			footerrow = g.options.footerrow,
			summaryCol = g.options.summaryCOl;

		if(footerrow) {
			var sJson = {};
			for(var i = 0; i < summaryCol.length; i++) {
				var sCol = summaryCol[i],
					label = sCol.label,
					strLen = parseInt(sCol.strLen + ''),
					cName = sCol.name,
					type = sCol.type;

				if(!!cName) {
					var val = '';
					if((type == 'avg' || type == 'sum' || type == 'count' || type == 'min' || type == 'max')) {
						val = grid.getCol(cName, false, type);
						val != 0 && !val ? (val = '') : (val += '');
					}
//					!!strLen && (val = val.substr(0, strLen));
					!!strLen && (val = parseFloat(val + '').toFixed(strLen));
					
					if(!!sCol.format){
						sJson[cName] = sCol.format(val,label);
					} else {
						sJson[cName] =  label + val;
					}
										
				}
			}
			grid.footerData('set', sJson,false);
		}
	};

	//事件
	BHGrid.prototype.gridComplete = function() {
			var $table = $(this);
			var ids = $table.getDataIDs();
			for(var i = 0; i < ids.length; i++) { //设置行高
				$table.setRowData(ids[i], false, {
					height: GOBJ.options.rowHeight
				});
			}
		},
		BHGrid.prototype.beforeSelectRow = function(rowid, e) {
			var e = window.event || e; // 兼容IE7
			var obj = $(e.srcElement || e.target);
			if($(obj).is('.row-toolbar-wrap,.row-toolbar-wrap *')) { //阻止单击行工具栏时的行事件
				return false;
			}
		};

	//在插件中使用BHGrid对象
	$.fn.BHGrid = function(options) {
		//创建BHGrid的实体
		var bhGrid = new BHGrid(this, options);
		return bhGrid.grid;
	};
})(jQuery, window, document);

/**
 * 缩略图插件
 * 2016-11-24 wyl
 * */
;
(function($, windows, document, undefined) {
	//定义构造函数
	var Thumbnail = function(ele, options) {
		this._e = ele,
			this._url = {},
			this.del_img = {},
			this.options = options
	};

	Thumbnail.prototype.init = function(options) {
		var def_options = {
			img: [{
				id: "", //标示
				url: "", //图片路径
				name: [], //图片名(切换功能)
				textClass: "", //文本样式
				text: [], //显示文本
			}],
			HideSwitch: false, //是否隐藏切换按钮
			HideText: false, //是否隐藏文本
			HideImgTool: false, //是否隐藏下方按钮
			HideImgToolAdd: false, //是否隐藏添加按钮
			HideImgToolEdit: false,
			HideImgToolDel: false,
			HideImgToolEnlarge: false,
			imgClick: function(obj) {

			},
			addFun: function(obj_from) { //添加回调 obj_from上传表单对象

			},
			editFun: function(obj, del) { //编辑回调 obj del(arr)：删除的img

			},
			delFun: function() { //删除回调

			}
		}
		this.options = $.extend(true, def_options, options);
		this.createThumbnail();
		return Thumbnail;
	};

	Thumbnail.prototype.createThumbnail = function() {
		var _e = this._e,
			options = this.options,
			img = options.img;
		for(var i = 0; i < img.length; i++) { //创建多个缩略图
			var _id = "img_" + img[i].id,
				img_url_i = img[i].url,
				_name_arr = (typeof img[i].name != "undefined" && img[i].name != null) ? img[i].name : [],
				init_img_url = _name_arr[0] ? (img_url_i + "small_300/" + _name_arr[0]) : "";
			var _date = new Date();
			_date = _date.getTime();
			var cards_html = ['<div class="goods-bigbox" id="' + _id + '" data-data_id="' + img[i].id + '" data-img_name="' + _name_arr + '" data-index="0">',
				'<div class="goods-imgbox">',
				'<span class="goods-imgspan">',
				'</span>',
				'<img class="goods-img" src="' + init_img_url + '"/>',
				'</div>',
				'<div class="goods-img-switch-wrap">',
				'<div class="switch-left">',
				'<span class="icon-left img-btn" data-type="left">',
				'</div>',
				'<div class="switch-right">',
				'<span class="icon-right img-btn" data-type="right">',
				'</div>',
				'</div>',
				'<p class="goods-name ' + img[i].textClass + '">',
				img[i].text[0],
				'</p>',
				'<form action="" method="post" class="add-img-from" style="display:none" id="form_' + _date + '">',
				'</form>',
				'<div class="goods-imgtool">',
				'<table border="0" cellspacing="" cellpadding="">',
				'<tr>',
				'<td>',
				'<span class="icon-add img-btn" data-type="add">',
				'</span>',
				'</td>',
				'<td>',
				'<span class="icon-edit2 img-btn" data-type="edit">',
				'</span>',
				'</td>',
				'<td>',
				'<span class="icon-delete img-btn" data-type="del">',
				'</span>',
				'</td>',
				'<td>',
				'<span class="icon-enlarge img-btn" data-type="enlarge">',
				'</span>',
				'</td>',
				'</tr>',
				'</table>',
				'</div>',
				'</div>'
			].join('');
			this._url[_id] = [];

			for(var j = 0; j < _name_arr.length; j++) {
				var img_url = img_url_i + "small_300/" + _name_arr[j];
				this._url[_id].push(img_url);
			}
			_e.append(cards_html);
			this._event(_id);
		}

		if(options.HideText) {
			_e.find(".goods-name").css("display", "none");
			_e.find(".goods-imgbox").css("height", "175px");
			_e.find(".goods-img").css({
				"max-height": "175px",
				"max-width": "150px"
			});
			_e.find(".goods-img-switch-wrap").css("margin-top", "-12px");
		} else {
			_e.find(".goods-img").css({
				"max-height": "150px",
				"max-width": "150px"
			});
		}

		options.HideImgToolAdd && _e.find(".icon-add").parent().css("display", "none");
		options.HideImgToolEdit && _e.find(".icon-edit2").parent().css("display", "none");
		options.HideImgToolDel && _e.find(".icon-delete").parent().css("display", "none");
		options.HideImgToolEnlarge && _e.find(".icon-enlarge").parent().css("display", "none");
	};

	Thumbnail.prototype.createFileInput = function(obj, type) {
		var d = new Date(),
			_id = d.getTime();
		var _input = $('<input type="file" value="上传文件" id="add_' + _id + '" data-operation="' + type + '" name="imgs[]">');
		obj.find(".add-img-from").append(_input);
		_input.click();
	};

	Thumbnail.prototype._event = function(_id) {
		var options = this.options,
			g = this,
			_e = this._e;
		var timeId = null; //用于避免图片单击和双击的冲突
		_e.on("click", ".goods-bigbox[id='" + _id + "'] .img-btn", function(e) {
			var bigbox = $(this).parents('.goods-bigbox');
			type = $(this).data("type");
			g.imgBtnFun(bigbox, type);
			return false;
		});
		_e.on("click", ".goods-bigbox[id='" + _id + "'] img", function(e) {
			var bigbox = $(this).parents('.goods-bigbox');
			clearTimeout(timeId);
			timeId = setTimeout(function() {
				g.options.imgClick(bigbox);
			}, 300);
		});
		_e.on("dblclick", ".goods-bigbox[id='" + _id + "'] img", function(e) {
			clearTimeout(timeId);
			var bigbox = $(this).parents('.goods-bigbox');
			type = $(this).data("type");
			g.imgBtnFun(bigbox, "enlarge");
			return false;
		});
		_e.on("mousemove", ".goods-bigbox[id='" + _id + "']", function() {
			!options.HideSwitch && $(this).find(".goods-img-switch-wrap").show();
			!options.HideImgTool && $(this).find(".goods-imgtool").show();
			return false;
		}).on("mouseout", ".goods-bigbox[id='" + _id + "']", function() {
			!options.HideSwitch && $(this).find(".goods-img-switch-wrap").hide();
			!options.HideImgTool && $(this).find(".goods-imgtool").hide();
			return false;
		});

		_e.on("change", ".goods-bigbox[id='" + _id + "'] .add-img-from input", function() {
			var obj = $(this),
				_id = obj.attr("id"),
				operation = obj.data("operation");
			var p_obj = obj.parents(".goods-bigbox"),
				p_id = p_obj.attr("id"),
				index = p_obj.data("index");
			_img_name = p_obj.data("img_name"),
				img_name_arr = _img_name == "" ? [] : _img_name.split(","),
				_len = img_name_arr.length;
			var new_img_url = "",
				new_img_url = g.getImgURL($(this));
			p_obj.find("img").attr("src", new_img_url);
			if(operation == "add") {
				g._url[p_id].push(new_img_url);
				img_name_arr.push(_id);
				p_obj.data("index", _len);
			} else {
				var del_img_name = img_name_arr[index],
					patt = /^add_\d+/ig;
				if(!patt.test(del_img_name)) { //图片为新增的不压入del_img中
					g.del_img[p_id] || (g.del_img[p_id] = []);
					g.del_img[p_id].push(del_img_name); //保存删除的图片名
				}
				img_name_arr[index] = _id;
				g._url[p_id][index] = new_img_url;
			}
			p_obj.data("img_name", img_name_arr.join(","));

			if(operation == "add") {
				g.options.addFun(p_obj);
			} else {
				g.options.editFun(p_obj, g.del_img[p_id]); //返回删除的图片
			}
		});
	}

	Thumbnail.prototype.imgBtnFun = function(e, type) {
		var id = e.attr("id"),
			img_name = e.data("img_name").split(","),
			len = img_name.length,
			index = e.data("index"),
			old_url = this._url[id][index],
			new_index = parseInt(index),
			new_url = "";

		if(type != "add" && !old_url) {
			return false;
		}

		switch(type) {
			case "left":
				new_index = parseInt(index) - 1;
				break;
			case "right":
				new_index = parseInt(index) + 1;
				break;
			case "add":
				this.imgAdd(e);
				break;
			case "edit":
				this.imgEdit(e);
				break;
			case "del":
				var name = img_name[index],
					patt = /^add_\d+/ig;

				if(!patt.test(name)) { //图片为新增的不压入del_img中
					this.del_img[id] || (this.del_img[id] = []);
					this.del_img[id].push(name); //保存删除的图片名
				}

				img_name.splice(index, 1);
				e.data("img_name", img_name.join(","));
				this._url[id].splice(index, 1); //删除图片组中的url
				len -= 1;
				new_index = parseInt(index);
				this.options.delFun(e, this.del_img[id]);
				break;
			case "enlarge":
				this.imgPreview(e);
				break;
			default:
				break;
		}

		if(new_index < 0) {
			new_index = len - 1;
		}

		if(new_index >= len) {
			new_index = 0;
		}

		new_url = this._url[id][new_index];
		new_url = new_url ? new_url : "";
		e.data("index", new_index);
		e.find(".goods-img").attr("src", new_url);
		return new_url;
	};

	Thumbnail.prototype.imgAdd = function(obj) {
		this.createFileInput(obj, "add");
	};

	Thumbnail.prototype.imgEdit = function(obj) {
		this.createFileInput(obj, "edit");
	};

	Thumbnail.prototype.getImgURL = function(obj) {
		var node = obj[0];
		var file = null;
		var new_img_url = "";

		if(node.files && node.files[0]) {
			file = node.files[0];
		} else if(node.files && node.files.item(0)) {
			file = node.files.item(0);
		}

		if(file.type != "image/png" && file.type != "image/jpeg") {
			Public.tips({
				"type": "wran",
				"time": 500,
				"content": "文件格式不正确！"
			});
			return false;
		}

		if((navigator.userAgent.indexOf('MSIE') >= 0) && (navigator.userAgent.indexOf('Opera') < 0)) {
			//这种获取方式支持IE10    
			node.select();
			new_img_url = document.selection.createRange().text;
		} else {
			try {
				//Firefox 因安全性问题已无法直接通过input[file].value 获取完整的文件路径  
				try {
					//Firefox7.0   
					new_img_url = file.getAsDataURL();
				} catch(e) {
					//Firefox8.0以上                                
					new_img_url = window.URL.createObjectURL(file);
				}
			} catch(e) { //这里不知道怎么处理了，如果是遨游的话会报这个异常                   
				//支持html5的浏览器,比如高版本的firefox、chrome、ie10  
				if(node.files && node.files[0]) {
					var reader = new FileReader();
					reader.onload = function(e) {
						new_img_url = e.target.result;
					};
					reader.readAsDataURL(node.files[0]);
				}
			}
		}

		return new_img_url;
	};

	Thumbnail.prototype.imgPreview = function(obj) {
		var g = this,
			id = obj.attr("id");
		var index = obj.data("index"),
			img_name = obj.data("img_name").split(",");
		var img_url = this._url[id][index];
		img_url = img_url.replace(/\/small_300\//, "/original/");
		var d = new Date(),
			id = d.getTime();
		var opt = {//缩放设置参数
			maxScale: 5,//放大倍数
			containerClass: 'zoomableContainer',
		};
		var _html = ['<div class="img-preview-wrap">',
			'<div class="img-wrap">',
			'<img id="'+ id +'" src="' + img_url + '"/>',
			'</div>',
			'<div class="top-btn-wrap">',
			'<div class="img-preview-down preview-btn" data-type="down">',
			'<span class="icon-download" title="下载">',
			'</span>',
			'</div>',
			'<div class="img-preview-close preview-btn" data-type="close">',
			'<span class="icon-close" title="关闭">',
			'</span>',
			'</div>',
			'</div>',
			'<div class="center-btn-wrap">',
			'<div class="switch-left preview-btn" data-type="left">',
			'<span class="icon-left" title="上一张">',
			'</span>',
			'</div>',
			'<div class="switch-right preview-btn" data-type="right">',
			'<span class="icon-right" title="下一张">',
			'</span>',
			'</div>',
			'</div>',
			'</div>'
		].join("");

		var img_preview = $(_html);

		$("body", top.window.document).append(img_preview);
		var w = img_preview.width() - 10,
			h = img_preview.height() - 10;
		
		img_preview.find('.img-wrap').css({
			'position':'absolute',
			'top':'50%',
			'left':'50%',
			'margin-top': -h / 2 + 'px',
			'margin-left': - w / 2 + 'px',
			'width': w + 'px',
			'height': h  + 'px'
		});
		
		window.top.smartZoom(opt);
		
		img_preview.on("click", ".preview-btn", function(e) {
			var type = $(this).data("type"),
				img_url = img_preview.find("img").attr("src");
			img_preview.find("img").css("zoom", 1);
			switch(type) {
				case "down":
					var a = document.createElement("a");

					if(!a.download) { //非ie
						a.href = img_url;
						a.download = img_name[obj.data("index")];
						a.click();
						a.remove();
					} else { //ie(未测试)
						alert("ie");
						var oPop = window.open(img_url, "", "display:none");
						for(; oPop.document.readyState != "complete";) {
							if(oPop.document.readyState == "complete") break;
						}
						oPop.document.execCommand("SaveAs");
						oPop.close();
					}
					break;
				case "close":	
					img_preview.remove();
					break;
				case "left":
				case "right":
					var new_url = g.imgBtnFun(obj, type);
					new_url = new_url.replace(/\/small_300\//, "/original/"); //使用大图
					$(".img-preview-wrap", top.window.document).find('.img-wrap').html('<img id="'+ id +'" src="' + new_url + '"/>');
					window.top.smartZoom(opt);//调用顶层父页面初始化缩放插件方法
					break;
				default:
					break;
			}
			return false;
		});
		img_preview.on("dblclick", ".preview-btn", function(e) {
			return false;
		});
		img_preview.on("dblclick", function() {
			$(this).remove();
			return false;
		});

		
//		var wrap_h = img_preview.find('.img-wrap').height(),
//			img_h = img_preview.find('img').height(),
//			mar_h = (wrap_h - img_h)/2;
//		img_preview.find('img').css('margin-top', mar_h);//设置垂直居中
		
		//缩放
//		img_preview.on("mousewheel DOMMouseScroll", function(e) {
//			var delta = (e.originalEvent.wheelDelta && (e.originalEvent.wheelDelta > 0 ? 1 : -1)) || // chrome & ie
//				(e.originalEvent.detail && (e.originalEvent.detail > 0 ? -1 : 1)); // firefox
//			var img = $(this).find("img"),
//				zoom = +img.css("zoom");
//
//			if(delta > 0) {
//				// 向上滚
//				if(zoom < 10) {
//					zoom += 0.1;
//				}
//			} else if(delta < 0) {
//				// 向下滚
//				if(zoom > 0.2) {
//					zoom -= 0.1;
//				}
//			}
//			img_h = img_preview.find('img').height() * zoom;
//			mar_h = (wrap_h - img_h)/2;
//			img.css({
//				"margin-top": mar_h, //缩放的时候设置垂直居中？
//				"zoom": zoom
//			});
//			if(zoom > 1) {
//				img.css("cursor", "move"); //不能直接用$(".goods-img").css(),
//			} else {
//				img.css("cursor", "default");
//			}
//		});
		

		//----------拖曳------2016.11.14--wyl
//		var dragging = false;
//		var iX, iY, maxY = 0,
//			maxX = 0;
//		var img_left, img_top,
//			img = img_preview.find("img"),
//			img_preview_wrap = img.parents(".img-preview-wrap"),
//			wrap_left = img_preview_wrap.offset().left;//获取背景的绝对定位左边距
//		img.on("mousedown", function(e) {
//			dragging = true;
//			iX = e.clientX - this.offsetLeft;
//			iY = e.clientY - this.offsetTop;
//			iC = e.clientX - this.left;
//			this.setCapture && this.setCapture();
//			return false;
//		});
//		img.mousemove(function(e) {
//			if(dragging && img.css("zoom") > 1) {
//				var e = e || window.event;
//				var oX = e.clientX - iX;
//				var oY = e.clientY - iY;
//
//				var left_move = parseFloat(img.css("margin-left")) <= 0 && parseFloat(img.css("margin-right")) >= 0 && parseFloat(oX) <= 0,
//					right_move = parseFloat(img.css("margin-right")) <= 0 && parseFloat(img.css("margin-left")) >= 0 && parseFloat(oX) >= 0,
//					up_move = parseFloat(img.css("margin-top")) <= 0 && parseFloat(img.css("margin-bottom")) >= 0 && parseFloat(oY) <= 0 ,
//					down_move = parseFloat(img.css("margin-bottom")) <= 0 && parseFloat(img.css("margin-top")) >= 0 && parseFloat(oY) >= 0 ;
//
//				if(left_move){
//					oX = 0;
//				}
//				img.css({
//					"margin-left": oX + "px",
//					"margin-top": oY + "px"
//				});
//				
//				return false;
//			}
//		});
//		img.on("mouseup mouseout", function(e){
//			dragging = false;
//			img.releaseCapture;
//			img.onmousemove = img.onmouseup = null;
//		});
	};

	Thumbnail.prototype.getData = function(filed) { //获取相关字段的数据
		switch(filed) {
			case "url":
				return Thumbnail._url;
				break;
			case "options":
				return Thumbnail.options;
				break;
			case "del_img":
				return Thumbnail.del_img;
				break;
			default:
				break;
		}
	};

	$.fn.thumbnail = function(options) {
		var obj = new Thumbnail(this, options);
		return obj.init(options);
	}
})(jQuery, window, document);

/**
 * 下拉框插件
 * demo: $(".a").BHComboBox(options)
 */

;
(function($, window, document, undefined) {
	var Comb = function(ele, options) {
		this._e = ele,
			this._id = ele.attr("id"),
			this.options = {}
	};

	Comb.prototype.init = function(options) {
		var e_obj = this._e;
		var com_obj;
		var def_options = {
			down_shape: 1, //0为灰色箭头，1为黑色倒三角形  
			hide_icon: 0,//是否隐藏下拉箭头
			data: [],
			valueField: 'id', //options.valueField
			textField: 'name', //options.textField
			value: options.data.length != 0 ? options.data[0][options.valueField || "id"] : "", //默认值为第一行记录
			selectBoxWidth: 400,
			keySupport: true, //按钮支持
			resize: true, //是否调整大小
			cancelable: true, //是否取消选择
			initIsTriggerEvent: false, //初始化时是否触发选择事件(有默认只是会触发选中事件)
			//			autocomplete: true, //自动完成设置为true时,而且不能设置renderItem，否则无效
			highLight: true, //自动完成是否匹配字符高亮显示(有默认值的情况下，默认值也会添加背景色)
			keySupport: true, //按键支持
			width: 400,
			is_clear: true, //输入下拉不存在的值，按回车或离开焦点时，自动清除该字段的值，默认值为true
			autocomplete: function(e) {
				var data = e_obj.data("data"),
					com_data = [], //过滤后的数据
					_key = $.trim(e.key),
					text_field = com_obj.get("textField");
				if(_key != "") {
					for(var i = 0; i < data.length; i++) {
						var str = data[i][text_field];
						var index = str.indexOf(_key);
						if(index != -1) {
							com_data.push(data[i]);
						}
					}
				} else {
					com_data = data;
				}

				if(com_data.length == 0) {
					com_data = data;
				}

				com_obj.set("data", com_data);
				e.show();
			},
			onButtonClick: function() {
				$($(this)[0].element).focus();
				com_obj.set("data", options.data);
				def_options.highLight = true;
			},
			onSuccess: function(data) {},
			onBeforeSelect: function(value, text) {

			},
			onSelected: function(val) {}
		};
		this.options = $.extend({}, def_options, options);
		e_obj.data("data", this.options.data);
		com_obj = e_obj.ligerComboBox(this.options);
		this.setDownType(this.options.down_shape);
		this.initEvent(e_obj);
		return com_obj;
	}

	/**
	 * 设置下拉三角形的形状
	 * @param {Object} obj
	 */
	Comb.prototype.setDownType = function(obj) {
//		var str = getRootPath();
//		if(obj) {
//			str += '/public/core/images/icon/xiala.png';
//		} else {
//			str += '/public/core/images/icon/icon-down.gif';
//		}
//		$("#department_text").siblings(".l-trigger").children().css("background", "url(" + str + ") no-repeat 50% 50%");
		if(obj){
			this._e.next().removeClass('down-shape');
			this._e.parent().find(".l-trigger-icon").removeClass('down-shape');
		}else{
			this._e.next().addClass('down-shape');
			this._e.parent().find(".l-trigger-icon").addClass('down-shape');
		}
		
	}

	Comb.prototype.initEvent = function(e){
		if(this.options.hide_icon){
			$(".l-trigger .l-trigger-icon").css("display", "none");
			e.parent().on("mouseover", function() {
				$(this).find(".l-trigger-icon").eq(0).css("display", "block");
			}).on("mouseout", function() {
				$(this).find(".l-trigger-icon").eq(0).css("display", "none");
			});
		}
	}
	/**
	 * 获取项目名称
	 */
	function getRootPath() {
		//获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp  
		var curWwwPath = window.document.location.href;
		//获取主机地址之后的目录，如： uimcardprj/share/meun.jsp  
		var pathName = window.document.location.pathname;
		var pos = curWwwPath.indexOf(pathName);
		//获取主机地址，如： http://localhost:8083  
		var localhostPaht = curWwwPath.substring(0, pos);
		//获取带"/"的项目名，如：/uimcardprj  
		var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
		return(localhostPaht + projectName);
	}

	$.fn.BHComboBox = function(options) {
		var obj = new Comb(this, options);
		return obj.init(options);
	}

})(jQuery, window, document);

/**
 * 下拉表格
 * 2016-11-24 wyl
 */
;(function($, window, document, undefined) {
	var comboGrid = function(ele, opt) {
		var g = this;
		g._e = ele; //获取input对象
		g.init(opt); //初始化函数
	}

	var comboBox_options; //合并后的下拉框参数
	comboGrid.prototype.init = function(opt) {
		var g = this,
			e = g._e;

		//表格配置参数
		g.defGridOptions = {
			id: '', //表格id
			width: 400, //默认表格宽度
			height: 150, //默认表格高度
			datatype: 'local', //加载本地数据
			altRows: false, //设置为true时，altclass才能生效
			altclass: 'altRowColor', //隔行换色
			onSelectRow: function(rowid) { //选中行
				var grid = e.parents(".combo-grid-wrap").find("table"),
					selected_data = $.getRowData(g.grid,[rowid]), //获取行数据
					text_field = comboBox_options.text_field,
					value_field = comboBox_options.value_field,
					_text = selected_data[0][text_field],
					_val = selected_data[0][value_field];
					g.val = _val;
					g.text = _text;
				e.val(_text);
				e.attr("data-item_val", _val); //将值字段名赋值给data属性
				e.parents(".combo-grid-wrap").children().eq(1).hide();
				comboBox_options.rowSelected(rowid, selected_data[0]);
			}
		}

		//下拉框配置参数
		g.defComboOptions = {
			defVal:'',
			defText:'',
			value_field: opt.value_field ? opt.value_field : 'id', //值字段名
			text_field: opt.text_field ? opt.text_field : 'name', //文本字段名(输入框显示)
			width: 100, //默认下拉框宽度
			height: 25, //默认下拉框高度
			icon_shape: 1, //下拉箭头，0为黑色三角形，1为灰色箭头
			hideDownBtn:false,//是否隐藏下拉按钮(为true则开启悬停显示)
			hideBorder: false, //是否去除边框
			readonly: false, //是否只读
			isAddBtn: false, //是否增加新增按钮
			addFun: function() { //新增按钮触发的函数
			},
			rowSelected: function(rowid) {} //表格选中行事件
		};
		comboBox_options = $.extend({}, g.defComboOptions, opt);
		g.val = comboBox_options.defVal;
		g.text = comboBox_options.defText;
		g.createComboBox(g, e, comboBox_options); //创建下拉框

		opt.data = opt.data ? opt.data : [];
		var grid_options = $.extend(true, g.defGridOptions, opt.grid);
		var _e = g._e.parents(".combo-grid-wrap");
		g.createTable(g, _e, grid_options); //创建表格
		this._event();
	}

	//创建下拉框
	comboGrid.prototype.createComboBox = function(g, e, options) {
		var shape = options.icon_shape ? 'icon-arrow-down' : '';
		var w = parseInt(options.width + '') - 16 - 2,
			h = parseInt(options.height + '') - 2 ;
		e.val(options.defText);
		e.wrap('<div class="combo-grid-wrap" style="width:'+ options.width +'px"><div class="combo-wrap"></div></div>');
		e.css({'width':w + 'px','height':h + 'px','border':'none','outline':'none','text-indent': '5px'});
		e.after('<span class="plugin-icon ' + shape + '" style="height:'+ h +'px;display:'+ (options.hideDownBtn ? 'none' : 'inline-block') +';"></span>');
		e.attr("readonly", options.readonly);
		e.parent().height(options.height);
		if(options.hideBorder) {
			e.parent().addClass("justBottom");
		}
	}

	//新增按钮
	comboGrid.prototype.addBtn = function(e) {
		var bottom = e.parents(".combo-grid-wrap").find(".ui-jqgrid-view");
		console.log(bottom);
		var _html = ['<div class="grid-bottom">',
			'<div class="add-wrap">',
			'<a href="javascript:void(0)">',
			'<span class="add-icon"></span>新增',
			'</a>',
			'</div>',
			'</div>'
		].join('');
		bottom.append(_html);
	}

	function setComboValue(e, rowid) {
		console.log(this._e);
		var grid = this._e.parents(".combo-grid-wrap").find("table");
		var selected_data = grid.getRowData(rowid);
		g._e.val(selected_data.name);
		console.log(rowdata);
	}
	comboGrid.prototype._event = function() {
		var g = this,
			_e = g._e.parent(), //_e = $(".combo-wrap")
			icon = $(_e).find('.plugin-icon');
		_e.bind("mouseover mouseout", function() {
			if(icon.hasClass('show-icon')) {
				icon.removeClass('show-icon');
			} else {
				icon.addClass('show-icon');
			}
		});

		//表格显隐
		var combo_wrap = _e.parent().children().eq(1);
		combo_wrap.on("mouseover", function() {
			$(this).show();
		}).on("mouseout", function() {
			$(this).hide();
		});

		icon.click(function() {
			var is_focus = g._e.is(":focus"),
				is_hide = combo_wrap.is(":hidden");
			if(!is_focus) {
				g._e.focus();
			}
			combo_wrap.toggle();
		});

		//新增按钮
		combo_wrap.on("click", "a", function() {
			g._e.parents(".combo-grid-wrap").children().eq(1).hide();
			comboBox_options.addFun();
		});

		//模糊搜索
		var timer;
		var tb = combo_wrap.find(".combo-table");
		g._e.on("keyup", function() {
			combo_wrap.show();
			var self = $(this);
			if(timer) {
				clearTimeout(timer);
			}
			timer = setTimeout(function() {
				//timer = null;
				tb.jqGrid('filterInput', self.val());
			}, 0);
		});
		
	}

	comboGrid.prototype.createTable = function(g, e, opt) {
		e.append('<div class="combo-table-wrap"><table class="combo-table" id="' + opt.id + '"></table></div>');
		var grid = e.find('.combo-table');
		g.grid = $(grid).jqGrid(opt);
		g._e.parent().next().hide();
		if(comboBox_options.isAddBtn) {
			g.addBtn(g._e);
		}
	};

	comboGrid.prototype.getVal = function(){
		return this.val;
	}

	comboGrid.prototype.getText = function(){
		return this.text;
	}

	$.fn.BHComboGrid = function(options) {
		var cgrid = new comboGrid(this, options);
		return cgrid;
	}
})(jQuery, window, document);

/**
 * 下拉树
 * */
;(function($, window, document, undefined) {
	var BHComboTree = function(ele,opt){
		var g = this;
		g.$element = ele;
		g.treeOptions = {
			data: [],
			nodeIdFiled: 'id', //节点
			nodeNameFiled: 'name', //节点名称字段
			pNodeIdFiled: 'pId', //父节点
			expandAll: false, //是否全部展开
			selectedMulti :true,
			toolbarDisplay:true,
			treeOperationUrl: '', //树状操作请求地址
			opeAddAttach: {}, //相关请求传给后台的附带数据
			opeEditAttach: {},
			opeDelAttach: {},
			onRightClick:null,
			onDblClick: function(event, treeId, treeNode) {
				g.beforeDblClick(event, treeId, treeNode);
			},
		};
		g.defOptions = {
			width:120,
			height:25,
			selectWidth:120,
			selectHeidht:200,
			valueFiled:'id',
			textFiled:['id'],//显示文本的字段
			spaceStyle:' ',//显示文本的字段值间的间隔(默认空格)
			defVal:'',//默认值
			defText:'',//默认文本
			Disabled:false,//禁用
			readonly:false,//是否只读,
			tree:g.treeOptions,//树状配置参数
			beforeDblClick:null,//双击选中前事件(返回false阻止)
			onDbClick:null//双击事件
		};
		if(!!opt.tree){
			opt.tree = $.extend({},g.treeOptions, opt.tree);
		}
		g.options = $.extend({}, g.defOptions, opt);
		g.val = g.options.defVal;
		g.text = g.options.defText;
		g.options.tree.width = g.options.selectWidth;
		g.options.tree.height = g.options.selectHeidht;
		g.createComboTree();
		g.createTree();
		g.initEvent();
		
	};
	
	BHComboTree.prototype.createComboTree = function(){
		var g = this,
			$element = g.$element,
			opt = g.options,
			w = opt.width - 2,
			h = opt.height - 2,
			tW = opt.selectWidth - 2,
			tH = opt.selectHeidht - 2,
			d = new Date(),
			treeId = d.getTime();
			$element.val(opt.defText);
			$element.wrap('<div class="BH-comboTree-wrap" style="width:'+ w +'px;height:'+ h +'px"><div class="BH-text-wrap" style="height:'+ h +'px;line-height:'+ h +'px;"></div></div>');
			$element.css({'border':'none','height': h + 'px','width':w - 16 + 'px','line-height':h + 'px'});
			opt.readonly && ($element.attr('readonly','readonly'));
			g.$comboTreeWrap = $element.parent().parent();
			$element.parent().append('<span class="comboTree-btn" style="width:16px;height:'+ h +'px"></span>');
			g.$comboTreeWrap.append('<div class="combo-tree-wrap" style="width:'+ tW +'px;height:'+ tH +'px;background:#fff;margin-left:-1px;margin-top:2px;"><div id="cT_'+ treeId +'"></div></div>');
			g.$tID = $('#cT_' + treeId);
	};
	
	BHComboTree.prototype.createTree = function(){
		var g = this,
			$tID = g.$tID,
			opt = g.options;
			g.treeObj = $tID.BHTree(opt.tree);
	};
	BHComboTree.prototype.beforeDblClick = function(event, treeId, treeNode){
		var g = this,
			opt = g.options,
			$element = g.$element,
			$comboTreeWrap = g.$comboTreeWrap,
			valueFiled = opt.valueFiled,
			textFiled = opt.textFiled,
			spaceStyle = opt.spaceStyle,
			beforeDblClick = g.options.beforeDblClick;
		var status = true;
			if(!!beforeDblClick){
				status = g.options.beforeDblClick(event, treeId, treeNode);
			}

			if(status){
				var t = '';
				var v = treeNode[valueFiled];
				
				for(var i = 0; i < textFiled.length; i++){
					t = t + treeNode[textFiled[i]] + spaceStyle;
				}
				t = t.slice(0,t.length - 1);
				g.val = v;
				g.text = t;
				$element.val(t);
				$element.attr("data-getval", v);
				$comboTreeWrap.find('.combo-tree-wrap').hide();
				!!opt.onDbClick && opt.onDbClick(event, treeId, treeNode);
			}
			
	};
	
	BHComboTree.prototype.initEvent = function(){
		var g = this,
			$element = g.$element,
			$comboTreeWrap = g.$comboTreeWrap,
			treeObj = g.treeObj.treeObj,
			opt = g.options;
		$comboTreeWrap.find('.BH-text-wrap').on('click','.comboTree-btn',function(){
			if(opt.Disabled) return false;
			var valueFiled = opt.valueFiled;
			var val = g.val;
			var node = treeObj.getNodeByParam(valueFiled, val, null);
			treeObj.selectNode(node);
			$comboTreeWrap.find('.combo-tree-wrap').toggle();			
		});
		
		$element.on('keyup',function(){
			if(opt.Disabled) return false;
			var nodeNameFiled = opt.tree.nodeNameFiled;
			var val = $(this).val();
			var nodes = treeObj.getNodesByFilter(function(node){
				var str = node[nodeNameFiled] + '';
				return str.indexOf(val) != -1;
			});
			treeObj.expandAll(false);
			treeObj.cancelSelectedNode();
			if(val != ''){
				for(var i = 0; i < nodes.length; i++){
					treeObj.selectNode(nodes[i],true);
				}				
			}		
			$comboTreeWrap.find('.combo-tree-wrap').show();
			$(this).focus();
		});
	};
	
	//设置默认值
	BHComboTree.prototype.setValue = function(val){
		var g = this,
			$element = g.$element,
			opt = g.options,
			treeObj = g.treeObj.treeObj,
			textFiled = opt.textFiled	,
			spaceStyle = opt.spaceStyle,
			valueFiled = opt.valueFiled;
		var node = treeObj.getNodeByParam(valueFiled, val, null);
			treeObj.selectNode(node);
		var t = '';
		$.each(textFiled, function(i,val) {			
			if(i != 0){
				t += spaceStyle;
			}
			t += node[val];
		});
		g.text = t;
		g.val = val;
		$element.val(t);
	};
	
	//获取值
	BHComboTree.prototype.getValue = function(){
		return this.val;
	};
	
	//获取文本
	BHComboTree.prototype.getText = function(){
		return this.text;
	};	
	
	//禁用
	BHComboTree.prototype.setDisabled = function(){
		this.options.Disabled = true;
	};
	
	//启用
	BHComboTree.prototype.setEnabled = function(){
		this.options.Disabled = false;
	};
	
	$.fn.BHComboTree = function(options) {
		var cT = new BHComboTree(this, options);
		return cT;
	}
})(jQuery, window, document);
/**
 * 经手人、制单人
 */
;(function($, window, document, undefined){
	
	var People = function(ele, opt){
		this._e = ele,
		this.options = opt;
	}
	
	var combo;
	People.prototype.init = function(options){
		var e = this._e;
		var people_obj = {};
		var _date = new Date();
		var id = "p_m_" + _date.getTime();
		var def_options = {
			def_path: Const.modules_img_url + "people_1.png", //默认图片路
			img_path: Const.modules_img_url, //图片路径
			img_name: "people_1.png", //图片名称
			text: "文本1", //显示文本
			placeholder: "点击输入", //输入框默认值
			isEdit: true, //是否编辑
			initVal: "", //默认值（combo valueField字段值）
			isAddBtn: true, //是否出现新增按钮
			itemSelectFun: function() { //项选择事

			},
			combo: { //下拉配置
				id: "id", //下拉框标示
				data: [],
				valueField: 'id',
				textField: 'name',
				selectBoxWidth: 70,
//				autocomplete: true,
				highLight: true,
				resize: true, //是否调整大小
				width: 70,
				height: 25,
				onSelected: function(val, text, item_data) {
					if(!item_data) {
						return false;
					}
					var img_path = options.img_path + "small_300/",
						img_name = item_data.imgs,
						_img = e.find(".p-img img"),
						_one = e.find(".p-img .name-one");

					if(img_name && img_name.length != 0) {
						var _url = img_path + img_name[0];
						if(_img.is(":hidden")) {
							_one.hide();
							_img.show();
						}
						_img.attr("src", _url);
					} else {
						if(!_img.is(":hidden")) {
							_img.hide();
						}
						createFontImg(e);
						_one.show();
					}

					options.itemSelectFun && options.itemSelectFun(val, text, item_data);
				},
				
				addFun: function() {

				},
				addRowButtonClick: function() { //添加
					options.combo.addFun();
				}
			}
		};
		var people_options = $.extend(true, {}, def_options, options);
//		people_options.combo.autocomplete = people_options.isEdit;
		!people_options.isAddBtn && delete(people_options.combo.addRowButtonClick);
		people_obj.combo = this.create(e, people_options);
		people_obj.options = people_options;
		return people_obj;
	
	}
	
	People.prototype.create = function(e, options) {
		var	_e = e;
		var _html = ['<div class="people">',
			'<div class="p-img">',
			'<div class="name-one" style="display:none">',
			'文',
			'</div>',
			'<img class="head-portrait-img" src="' + options.def_path + '"/>',
			'</div>',
			'<div class="p-name">',
			'<p>',
			options.text,
			'</p>',
			'<input type="text"  name="" class="ui-inputtext people-select-input" value="" placeholder="' + options.placeholder + '" />',
			'</div>',
			'</div>'
		].join("");
		var _people = $(_html);
		_e.append(_people);
		var _input = _e.find(".people-select-input");
		combo = $(_input).BHComboBox(options.combo);
		!options.isEdit ? (combo.setDisabled(true), combo.setText(options.initVal)) : combo.setValue(options.initVal);
		this.initEven(e, combo, options);
		return combo;
	}
	
	function createFontImg(e) { //获取最后一个字符
		var str = e.find(".people-select-input").val(),
			new_str = str.split("").reverse().join(""),
			_text = setSub(new_str, 1);
		e.find(".name-one").html(_text);
	}
	
	/**
	 * 截取中英文混合字符串
	 **/
	function setSub(str, n) {
		var strReg = /[^\x00-\xff]/g;
		var _str = str.replace(strReg, "**");
		var _len = _str.length;
		if(_len > n) {
			var _newLen = Math.floor(n / 2);
			var _strLen = str.length;
			for(var i = _newLen; i < _strLen; i++) {
				var _newStr = str.substr(0, i).replace(strReg, "**");
				if(_newStr.length >= n) {
					return str.substr(0, i);
					break;
				}
			}
		} else {
			return str;
		}
	}
	
	People.prototype.initEven = function(e, combo, options) {
		var _e = e;
		var _one = _e.find(".p-img .name-one");
		var _img = _e.find(".p-img img");
		var combo_input = $(combo.element);

		//输入框失去焦点
		combo_input.on("blur", function() {
			changeImg(this, options, _img, _one, _e);
		}).on("keyup", function(event){
			if(event.keyCode == 13){
				changeImg(this, options, _img, _one, _e);
			}
		});

//		combo_input.on("blur keyup", function(event) {
//			if(event.keyCode == 13 || $._data($(this)[0],"events")["blur"]){
//				
//				var val = $.trim($(this).val());
//				if(val == "") {
//					_img.attr("src", options.def_path);
//					if(_img.is(":hidden")) {
//						_one.html("");
//						_one.hide();
//						_img.show();
//					}
//				}else{
//					var data = combo.data;
//					var img_path = options.img_path + "small_300/",
//						img_name = data[0].imgs;
//						console.log(data[0]);
//					if(img_name && img_name.length != 0) {
//						var _url = img_path + img_name[0];
//						if(_img.is(":hidden")) {
//							_one.hide();
//							_img.show();
//						}
//						_img.attr("src", _url);
//					} else {
//						if(!_img.is(":hidden")) {
//							_img.hide();
//						}
//						createFontImg(e);
//						_one.show();
//					}
//				}
//			}
//		});

		//下拉按钮显隐
		if(options.isEdit) {
			_e.on("mouseover", ".l-text-combobox", function() {
				$(this).find(".l-trigger-icon").css("display", "block");
			}).on("mouseout", ".l-text-combobox", function() {
				$(this).find(".l-trigger-icon").css("display", "none");
			});
		}
	}
	
	//值改变时改变头像
	function changeImg(obj, options, _img, _one, _e){
		var val = $.trim($(obj).val());
		if(val == "") {
			_img.attr("src", options.def_path);
			if(_img.is(":hidden")) {
				_one.html("");
				_one.hide();
				_img.show();
			}
		}else{
			var data = combo.data;
			var img_path = options.img_path + "small_300/",
				img_name = null;
			for(var i in data){
				if(val == data[i].name){
					img_name = data[i].imgs;
					break;
				}
			}
			if(img_name && img_name.length != 0) {
				var _url = img_path + img_name[0];
				if(_img.is(":hidden")) {
					_one.hide();
					_img.show();
				}
				_img.attr("src", _url);
			} else {
				if(!_img.is(":hidden")) {
					_img.hide();
				}
				createFontImg(_e);
				_one.show();
			}
		}
	}
	
	
	$.fn.people = function(options){
		var obj = new People(this, options);
		return obj.init(options);
	}
	
})(jQuery, window, document);

/**
 * 时间控件
 */
;(function($, windows, document, undefined){
	
	$.fn.date = function(options) {
		var now_time = new Date().Format("yyyy-MM-dd");
		var def_options = {
			format: "yyyy-MM-dd", // yyyy-MM-dd hh:mm(带时间)
			label: "",
			labelWidth: "",
			labelAlign: '',
			selectBoxWidth: 100,
			width: 100,
			height: 25,
			initValue: now_time, //默认当前时间
			cancelable: false, //是否可以取消选择
			showTime: false, //是否显示时间部分
			readonly: false, //是否只读
			showFooterBtn: false, //显示底部按钮
			showYearBtn: false //显示年份切换按钮
		};
		var date_opions = $.extend(true, def_options, options);
		var date_obj = this.ligerDateEditor(date_opions);
		if(!date_opions.showYearBtn) {
			$(".l-box-dateeditor-header .l-box-dateeditor-header-prevyear").css("display", "none");
			$(".l-box-dateeditor-header .l-box-dateeditor-header-prevmonth").css("left", "0px");
			$(".l-box-dateeditor-header .l-box-dateeditor-header-nextmonth").css("right", "0px");
			$(".l-box-dateeditor-header .l-box-dateeditor-header-nextyear").css("display", "none");
		}
	
		if(!date_opions.showFooterBtn) {
			$(".l-box-dateeditor .l-box-dateeditor-toolbar").css("display", "none");
	
		}
		return date_obj;
	}
})(jQuery, window, document);
