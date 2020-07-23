
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>注册用户</title>
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
 
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
 
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<form class="form-horizontal" action="/Streamline/RegisterServlet" method="post" onsubmit="return validate()">
	<div class="row-fluid">
		<div class="span12">
			<h2>
				<em><em>在线课堂通行证</em></em>
			</h2>
				<div class="control-group">
					 <label class="control-label" >学生证号</label>
					<div class="controls">
						<input type="text" name="username"/>
					</div>
				</div>
			
				<div class="control-group">
					 <label class="control-label" for="inputPassword">密码(不多于8位)</label>
					<div class="controls">
						<input type="password" name="password"/>
					</div>
				</div>
				
				</div>
				<div class="control-group">
					<div class="controls">
						  <button class="btn" type="submit" name="submit">提交</button>
						  <a  href="login.jsp"><input type="button" value="返回登陆" /></a>
						   <input
			type="reset" name="reset">
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
	</form>
</body>

 
</html>

