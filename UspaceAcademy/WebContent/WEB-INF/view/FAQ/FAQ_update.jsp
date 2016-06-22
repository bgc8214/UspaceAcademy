<%@page contentType="text/html;charset=utf-8"%>
<form action="/UspaceAcademy/FAQ/FAQUpdate.do" method="post">
<input type="hidden" name="no" value="${requestScope.faq.basicNo}">
<input type="hidden" name="date" value="${requestScope.faq.basicDate}">

FAQ제목  <input type="text" value="${requestScope.faq.basicTitle}" name="title"><br>
FQA내용 <br>
<textarea rows="45" cols="100" name="content">${requestScope.faq.basicContent}</textarea><br>
<input type="submit" value="수정">
</form>

