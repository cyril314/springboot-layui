<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<title>后台欢迎页</title>
<link rel="stylesheet" type="text/css" href="${webpath}/static/ui/css/layui.css" />
<link rel="stylesheet" type="text/css" href="${webpath}/static/css/admin.css" />
</head>
<body>
<div class="wrap-container welcome-container">
	<div class="row">
		<div class="welcome-left-container col-lg-9">
			<!--服务器信息-->
			<div class="server-panel panel panel-default">
				<div class="panel-header">服务器信息</div>
				<div class="panel-body clearfix">
					<div class="col-md-2">
						<p class="title">服务器计算机名</p>
						<span class="info">${os.computerName}</span>
					</div>
					<div class="col-md-2">
						<p class="title">服务器操作系统</p>
						<span class="info">${os.osname}</span>
					</div>
					<div class="col-md-2">
						<p class="title">服务器MAC</p>
						<span class="info">${os.mac}</span>
					</div>
					<div class="col-md-2">
						<p class="title">服务器IP地址</p>
						<span class="info">${os.ip}</span>
					</div>
					<div class="col-md-2">
						<p class="title">JAVA版本</p>
						<span class="info">${os.java}</span>
					</div>
					<div class="col-md-2">
						<p class="title">服务器日期</p>
						<span class="info">${os.sysTime}</span>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
<script src="${webpath}/static/ui/layui.js" type="text/javascript" charset="utf-8"></script>
<script src="${webpath}/static/ui/js/common.js" type="text/javascript" charset="utf-8"></script>
</html>
