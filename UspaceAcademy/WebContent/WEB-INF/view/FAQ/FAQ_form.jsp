<%@page contentType="text/html;charset=utf-8"%>
<form action="/UspaceAcademy/FAQ/FAQWrite.do" method="post">
<input type="hidden" name="codeName" value="${requestScope.codeName}">
FAQ제목 : <input type="text" name="title"><br>
FAQ내용 : <br>
<textarea rows="45" cols="100" name="content" wrap="hard"></textarea><br>
<input type="submit" value="전송">
</form>
