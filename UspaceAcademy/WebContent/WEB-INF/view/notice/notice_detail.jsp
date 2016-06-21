<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 게시물 상세페이지</title>
</head>
<body>
제목 :	${requestScope.notice.basicTitle}<br>
등록일: <fmt:formatDate value="${requestScope.notice.basicDate}" pattern="yyyy-MM-dd"/>	<br>
내용 :	${reqeustScope.notice.basicContent}
<p>
<a href="/UspaceAcademy/notice/list.do">공지사항 리스트</a>
</body>
</html>