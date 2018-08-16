<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>

</head>

<body>
	<div class="container">
		<h2>게시판</h2>
		<table class="table" width="500" cellpadding="0" cellspacing="0"
			border="1">

			<tr>
				<td>번호</td>
				<td>이름</td>
				<td>제목</td>
				<td>날짜</td>
				<td>히트</td>
			</tr>

			<c:forEach items="${list}" var="dto">
				<tr>
					<td>${dto.bId}</td>
					<td>${dto.bName}</td>
					<td><a href="content_view?bId=${dto.bId}">${dto.bTitle}</a></td>
					<td>${dto.bDate}</td>
					<td>${dto.bHit}</td>
				</tr>

			</c:forEach>

			<tr>
				<td colspan="5"><a href="write_view">글작성</a></td>
			</tr>
		</table>
	</div>

</body>

</html>