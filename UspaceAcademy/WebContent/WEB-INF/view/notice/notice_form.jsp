<%@page contentType="text/html;charset=utf-8"%>
<form action="/UspaceAcademy/notice/noticeWrite.do" method="post">
<input type="hidden" name="codeName" value="${requestScope.codeName}">
공지제목 : <input type="text" name="title"><br>
공지내용 : <br>
<textarea rows="45" cols="100" name="content"></textarea><br>
<input type="submit" value="전송">
</form>
