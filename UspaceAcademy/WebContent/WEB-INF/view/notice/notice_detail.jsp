<%@page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 게시물 상세페이지</title>
</head>
<body>
제목 :	${requestScope.notice.basicTitle}<br>
등록일:	${requestScope.notice.basicDate}<br>
내용 :	${reqeustScope.notice.basicContent}
<p>
<a href="/UspaceAcademy/notice/list.do">공지사항 리스트</a>
</body>
</html>