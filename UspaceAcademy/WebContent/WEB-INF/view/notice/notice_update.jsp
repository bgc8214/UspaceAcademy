<%@page contentType="text/html;charset=utf-8"%>


<form action="/UspaceAcademy/notice/noticeUpdate.do" method="post">
<input type="hidden" name="no" value="${requestScope.notice.basicNo}">
<input type="hidden" name="date" value="${requestScope.notice.basicDate}">

공지제목  <input type="text" value="${requestScope.notice.basicTitle}" name="title"><br>
공지내용 <br>
<textarea rows="45" cols="100" name="content">${requestScope.notice.basicContent}</textarea><br>
<input type="submit" value="수정">
</form>
