<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>菜单管理</title>
</head>
<!-- 如果使用frameset 的包含页面  主页面不能有 body-->
<frameset cols="230,*" border="1">
	<frame src="${pageContext.request.contextPath}/sys/toMenuLeft.action" name="left">
	<frame src="${pageContext.request.contextPath}/sys/toMenuRight.action" name="right">
</frameset>

</html>