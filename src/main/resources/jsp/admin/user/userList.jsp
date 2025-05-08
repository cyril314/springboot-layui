<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>用户列表</title>
<link rel="stylesheet" type="text/css" href="${webpath}/static/ui/css/layui.css" />
<link rel="stylesheet" type="text/css" href="${webpath}/static/css/admin.css" />
</head>
<body>
<div class="wrap-container clearfix">
	<div class="column-content-detail" id="table-list">
		<form class="layui-form" action="../sysuser/list">
			<div class="layui-form-item">
				<div class="layui-inline tool-btn">
					<button class="layui-btn layui-btn-small layui-btn-normal addBtn" data-url="${webpath}/admin/user/edit"><i class="layui-icon">&#xe654;</i></button>
					<!-- <button class="layui-btn layui-btn-small layui-btn-danger delBtn"  data-url="article-add.html"><i class="layui-icon">&#xe640;</i></button>
					<button class="layui-btn layui-btn-small layui-btn-warm listOrderBtn hidden-xs" data-url="article-add.html"><i class="iconfont">&#xe656;</i></button> -->
				</div>
				<div class="layui-inline">
					<input type="text" name="queryName" value="${map.queryName }" placeholder="请输入名称" autocomplete="off" class="layui-input" />
				</div>
				<div class="layui-inline">
					<select name="states" lay-filter="status">
						<option value="">请选择一个状态</option>
						<option value="010">正常</option>
						<option value="021">停止</option>
					</select>
				</div>
				<button class="layui-btn layui-btn-normal" lay-submit="search">搜索</button>
			</div>
		</form>
		<table class="layui-hide" id="tableList" lay-filter="demo"></table>
	</div>
</div>
<script src="${webpath}/static/ui/layui.js" type="text/javascript" charset="utf-8"></script>
<script src="${webpath}/static/ui/js/common.js" type="text/javascript" charset="utf-8"></script>
<script type="text/html" id="barDemo">
	<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
	<a class="layui-btn layui-btn-xs edit-btn" data-url="sysuser/edit" data-id="{{d.id}}" lay-event="edit">编辑</a>
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script type="text/html" id="checkboxE">
	<input type="checkbox" name="enabled" value="{{d.enabled}}" lay-skin="switch" lay-text="正常|禁用" lay-filter="sexDemo" {{d.enabled==1?'checked':''}}>
</script>
<script type="text/html" id="checkboxS">
	<input type="checkbox" name="isys" value="{{d.isys}}" lay-skin="switch" lay-text="是|否" lay-filter="sexDemo" {{d.isys==1?'checked':''}}>
</script>
<script type="text/javascript">
layui.use(['jquery', 'table'], function(){
	var $ = layui.jquery,table = layui.table;
	//执行一个 table 实例
	table.render({
		elem: '#tableList'
		,height: 532
		,url:'${webpath}/admin/user/list'
		,method:'post'
		,page: true //开启分页
		,cols: [[//表头
			{checkbox: true, fixed: true}
			,{field: 'id', title: 'ID', width: '5%' , sort: true, fixed: 'left'}
			,{field: 'name', title: '用户姓名', width:'10%'}
			,{field: 'username', title: '登陆用户名', width:'15%', sort: true}
			,{field: 'desc', title: '描述', width: '30%'}
			,{field: 'enabled', title: '是否被禁用', templet: '#checkboxE', width: '10%'}
			,{field: 'isys', title: '是否是超级用户', templet: '#checkboxS', width: '10%'}
			,{fixed: 'right',title: '操作', align:'center', width: '15%', toolbar: '#barDemo'}
		]]
		,response: {
			statusName: 'code' //数据状态的字段名称，默认：code
			,statusCode: 200 //成功的状态码，默认：0
			,dataName: 'data' //数据列表的字段名称，默认：data
		}      
		,done: function (res, curr, count) {
			//如果是异步请求数据方式，res即为你接口返回的信息。
			//如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
			console.log(res);
		
			//得到当前页码
			console.log(curr);
		
			//得到数据总量
			console.log(count);
		}
	});
});
</script>
</body>
</html>
