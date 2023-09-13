<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>modify_view</h3>
	<form action="modify"  method="post" enctype="multipart/form-data">
		<input type="text" name="id"  readonly value=${dto.id }><br>
		<input type="text" name="name"><br>
		<input type="file" name="file"><br>
		<input type="submit" value="전송">
	</form>
	<a href="views">내용 확인</a>
</body>
</html>