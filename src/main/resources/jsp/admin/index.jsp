<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="renderer" content="webkit" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<title>网站后台管理模版</title>
<link rel="stylesheet" type="text/css" href="${webpath}/static/ui/css/layui.css" />
<link rel="stylesheet" type="text/css" href="${webpath}/static/css/admin.css" />
<script type="text/javascript" src="${webpath}/static/js/jquery.js"></script>
<script type="text/javascript" charset="utf-8" src="${webpath}/static/ui/layui.js" ></script>
<script type="text/javascript" charset="utf-8" src="${webpath}/static/ui/js/common.js" ></script>
<script type="text/javascript" charset="utf-8" src="${webpath}/static/ui/js/main.js" ></script>
</head>
<body>
<div class="main-layout" id='main-layout'>
	<!--侧边栏-->
	<div class="main-layout-side">
		<div class="m-logo"></div>
		<ul class="layui-nav layui-nav-tree" lay-filter="leftNav">
			<c:forEach items="${menus}" var="menu" varStatus="id">
				<li class="layui-nav-item">
					<a href="javascript:;"><i class="${menu.icon}"></i>${menu.name}</a>
					<c:choose>
						<c:when test="${(menu.children)==null || fn:length(menu.children)==0}">
							<a href="javascript:;" data-url="${menu.url}" data-id="${menu.id}" data-text="${menu.name}">
								<span class="l-line"></span>${menu.name}
							</a>
						</c:when>
						<c:otherwise>
							<dl class="layui-nav-child">
								<c:forEach items="${menu.children}" var="amenu" varStatus="id">
									<dd>
										<a href="javascript:;" data-url="${amenu.url}" data-id="${amenu.id}" data-text="${amenu.name}">
											<span class="l-line"></span>${amenu.name}
										</a>
									</dd>
								</c:forEach>
							</dl>
						</c:otherwise>
					</c:choose>
				</li>
			</c:forEach>
		</ul>
	</div>
	<!--右侧内容-->
	<div class="main-layout-container">
		<!--头部-->
		<div class="main-layout-header">
			<div class="menu-btn" id="hideBtn">
				<a href="javascript:;"><span class="iconfont">&#xe60e;</span></a>
			</div>
			<div class="menu-btn" id="refreshBtn">
				<a href="javascript:;" layadmin-event="refresh" title="刷新"><span class="iconfont">&#xe60d;</span></a>
			</div>
			<ul class="layui-nav" lay-filter="rightNav">
				<li class="layui-nav-item">
					<a href="javascript:;" data-url="#" data-id='4' data-text="邮件系统"><i class="iconfont">&#xe603;</i></a>
				</li>
				<li class="layui-nav-item">
					<a href="javascript:;" data-url="#" data-id='5' data-text="个人信息">超级管理员</a>
				</li>
				<li class="layui-nav-item">
					<a href="javascript:;">退出</a>
				</li>
			</ul>
		</div>
		<!--主体内容-->
		<div class="main-layout-body">
			<!--tab 切换-->
			<div class="layui-tab layui-tab-brief main-layout-tab" lay-filter="tab" lay-allowClose="true">
				<ul class="layui-tab-title">
					<li class="layui-this welcome">后台主页</li>
				</ul>
				<div class="layui-tab-content" id="LAY_app_body">
					<div class="layui-tab-item layui-show" style="background: #f5f5f5;">
						<iframe src="main" width="100%" height="100%" scrolling="auto" class="iframe" framborder="0" />
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--遮罩-->
	<div class="main-mask"></div>
</div>
</body>
</html>

