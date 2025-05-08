<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>管理后台登录页面</title>
<link type="text/css" rel="stylesheet" href="${ctx}/static/login/login.css">
<script type="text/javascript" src="${ctx}/static/js/jquery.js"></script>
<!-- 登录页动态效果 -->
<script type="text/javascript">
	/*<![CDATA[*/
	if (window.top != null && window.top.location != window.location) {
		window.top.location = window.location;
	}
	if (self != top) {
		top.location.href = self.location.href;
	}
	/*]]>*/
</script>
</head>
<body class="login-page">
	<div class="login">
		<h2>用户登录</h2>
		<form method="post" id="form_login" action="/admin/login_check">
			<input type="text" id="username" name="username" placeholder="用户名" required="required" />
			<input type="password" id="password" name="password" placeholder="密码" required="required" />
			<button type="submit" class="btn btn-primary btn-block btn-large">登录</button>
		</form>
	</div>
</body>
</html>