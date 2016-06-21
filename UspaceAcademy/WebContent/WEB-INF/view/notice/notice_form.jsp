<%@page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 등록 폼</title>
</head>
<body>
<form action="/UspaceAcademy/notice/noticeWrite.do" method="post">
<input type="hidden" name="codeName" value="${requestScope.codeName}">
공지제목 : <input type="text" name="title">
공지내용 : <br>
<textarea rows="80" cols="100" name="content"></textarea><br>
<input type="submit" value="전송">
</form>
</body>
</html>