<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<title>用户添加</title>
<link rel="stylesheet" type="text/css" href="${webpath}/static/ui/css/layui.css" />
<link rel="stylesheet" type="text/css" href="${webpath}/static/css/admin.css" />
</head>
<body>
<form id="formAction" class="layui-form column-content-detail" method="post">
	<input type="hidden" name="id" value="${user.id }" />
	<div class="layui-tab">
		<ul class="layui-tab-title">
			<li class="layui-this">用户信息</li>
		</ul>
		<div class="layui-tab-content">
			<div class="layui-tab-item layui-show">
				<div class="layui-form-item">
					<label class="layui-form-label">用户姓名：</label>
					<div class="layui-input-block">
						<input type="text" name="name" value="${user.name }" required lay-verify="required" placeholder="请输入用户姓名" autocomplete="off" class="layui-input" />
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">登录名称：</label>
					<div class="layui-input-block">
						<input type="text" name="username" value="${user.username }" required lay-verify="required" placeholder="请输入登录名" autocomplete="off" class="layui-input" />
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">是否禁用：</label>
					<div class="layui-input-block">
						<input type="checkbox" name="enabled" lay-skin="switch" lay-filter="switchFilt" lay-text="否|是" ${user.enabled==1?'checked':''} />
					</div>
				</div>
				<div class="layui-form-item layui-form-text">
					<label class="layui-form-label">描述内容：</label>
					<div class="layui-input-block">
						<textarea class="layui-textarea layui-hide" name="desc" lay-verify="content" id="LAY_demo_editor">${user.desc }</textarea>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="layui-form-item" style="padding-left: 10px;">
		<div class="layui-input-block">
			<button class="layui-btn layui-btn-normal" lay-submit lay-filter="formFilt">立即提交</button>
			<button type="reset" class="layui-btn layui-btn-primary">重置</button>
		</div>
	</div>
</form>
<script src="${webpath}/static/ui/layui.js" type="text/javascript" charset="utf-8"></script>
<script src="${webpath}/static/ui/js/common.js" type="text/javascript" charset="utf-8"></script>
<script>
layui.use(['form', 'layedit'], function(){
	var form = layui.form
	,layer = layui.layer
	,$ = layui.jquery
	,layedit = layui.layedit;
	
	//创建一个编辑器
	var editIndex = layedit.build('LAY_demo_editor',{height: 200});
 
	form.on('switch(switchFilt)', function(data) {
		if(data.elem.checked){
			data.elem.value=1;
		}else{
			data.elem.value=0;
		}
	});
	//监听提交
	form.on('submit(formFilt)', function(data) {
		layedit.sync(editIndex);
		layedit.getContent(editIndex);
		$.post('${webpath}/admin/user/save', $('#formAction').serialize(), function(res) {
			if (res.code == 200) {
				layer.msg(res.msg, {
					icon: 1
				}, function(index) {
					CloseWin();
				})
			} else {
				layer.msg(res.msg, {
					icon: 0
				}, function() {
					location.reload(); // 页面刷新
					return false;
				})
			}
		}, 'json');
		return false;
	});
});
</script>
</body>
</html>