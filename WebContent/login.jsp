
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
 
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
 
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

	<div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<h3 class="text-left text-info">
				在线课堂
			</h3>
			<form action="/Streamline/GetFavoriteState" method="post" role="form">
				<div class="form-group">
					 <label for="inputEmail3" class="col-sm-2 control-label">学生证号</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="inputEmail3" name="token"/>
					</div>
				</div>
				<div class="form-group">
					 <label for="inputPassword3" class="col-sm-2 control-label">密码</label>
					<div class="col-sm-10">
						<input type="password" class="form-control" id="inputPassword3" name="thumbnailUrl"/>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						 <button type="submit" class="btn btn-default">登录</button>
					</div>
				</div>

			</form>
		</div>
	</div>
</div>
</body>
</html>