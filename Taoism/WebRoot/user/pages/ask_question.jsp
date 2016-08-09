<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>向大师提问</title>
	<script type="text/javascript" src="../../js/jquery.min.js"></script>
	<script charset="utf-8" src="kindeditor/kindeditor-min.js"></script>
	<script charset="utf-8" src="kindeditor/lang/zh_CN.js"></script>
	<style type="text/css">
		body{font-family: 'Microsoft YaHei';}
		.main{width:780px;margin:60px auto;padding:40px;background:#fff;}
		.title{width:100%;text-align: center;font-size:30px;margin-bottom: 10px;font-weight:bold;}
		table td:nth-of-type(1){vertical-align:top;width:80px;}

		.bo-div{text-align: center;width:100%;margin-top:20px;}
		.bo-div a{text-decoration: none;cursor:pointer;padding:3px 6px 3px 6px;border:1px solid #d0d0d0;
			border-radius:4px;color:#000;font-size:15px;background-color:#F0F0EE;}
		.bo-div a:hover{color:#fff;background-color:#2877ff;border: none;}
		.tip{display:none;color:#f00000;}
		.bd{width:100%;height:100%;position:fixed;z-index:-10;top:0;left:0;background-image:url(../../images/register_bmg1.jpg);
			background-repeat:no-repeat;background-size:cover;}
	</style>
	<!--引用插件-->
	<script>
		var editor;
		KindEditor.ready(function(K) {
			editor = K.create('textarea[name="content"]', {
				cssPath : '../plugins/code/prettify.css',
				uploadJson : '../jsp/upload_json.jsp',
				fileManagerJson : '../jsp/file_manager_json.jsp',
				allowFileManager : true,
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
				},

				//自定义工具栏选项，详细见kindeditor.js
				items:['undo', 'redo', '|', 'preview', 'print', 'cut', 'copy', 'paste',
					'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
					'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
					'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
					'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
					'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image', 'multiimage',
					'flash', 'media', 'insertfile', 'table', 'hr', 'emoticons', 'pagebreak',
					'anchor', 'link', 'unlink', '|', 'about']
			});			
		});

		function disappear(obj){
			if($(obj).val()!=""){
				$(obj).next().css("display","none");
			}
		}
		function check(){
			if($("#q_title").val()==""){
				$("#q_title").next().css("display","inline-block");
				$("#q_title").focus();
			}else if(editor.html()==""){
				//不是判断textarea的值
				$("textarea").next().css("display","block");
				
			}else{
				$("textarea").next().css("display","none");
				$("form").submit();
			}
		}
	</script>
</head>
<body>
	<div class="bd"></div>
	<div class="main">
		<div class="title">向大师提问</div>
		<form action="" method="post" name="example">
			<table class="tb">
				<tr>
					<td>标题：</td>
					<td>
						<input type="text" id="q_title" onblur="disappear(this)" name="q_title">&nbsp;&nbsp;
						<span class="tip">请输入标题</span>
					</td>
				</tr>
				<tr>
					<td>问题类型：</td>
					<td><select id="q_type" name="q_type"><option>问题类型</option></select></td>
				</tr>
				<tr>
					<td>内容：</td>
					<td>
						<textarea name="content" style="width:100%;height:300px;visibility:hidden;"></textarea>
						<div class="tip">请输入问题内容</div>
					</td>
				</tr>
				
			</table>
			<div class="bo-div">
				<a href="javascript:check();">提交</a>&nbsp;&nbsp;&nbsp;
				<a href="javascript:window.close();">返回</a>
			</div>
		</form>
	</div>
</body>
</html>