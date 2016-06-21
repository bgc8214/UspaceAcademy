<%@ page contentType ="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 게시판</title>
</head>
<body>
<hr/>
	<table border="2">
		<thead>
			<tr>
				<td>공지제목</td>
				<td>공지내용</td>
				<td>등록일</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="notice" items="${requestScope.list}">
				<tr>
					<td>${notice.basicTitle}</td>
					<td>${notice.basicContent}</td>
					<td>${notice.basicDate}</td>
				</tr>
			</c:forEach>
		</tbody>	
	</table>
	
</body>
</html>