<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>计算sum</title>
</head>
<body>
<h1>给你计算好了收入和支出总和喔~</h1>
<h2>
<%String income=(String)request.getParameter("income");
String outcome=request.getParameter("outcome");
%>

收入:${income} 支出：${outcome}

</h2>

</body>
</html>