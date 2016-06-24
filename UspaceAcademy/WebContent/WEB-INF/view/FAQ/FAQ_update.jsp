<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style type="text/css">
table {
	
}

span, td, th {
	padding: 5px;
}

span {
	font-size: .9em;
	color: red;
}
</style>
<form action="/UspaceAcademy/FAQ/FAQUpdate.do?page=${page}" method="post">
<input type="hidden" name="basicNo" value="${requestScope.faq.basicNo}">
<input type="hidden" name="basicDate" value="${requestScope.faq.basicDate}">
<input type="hidden" name="basicHit" value="${requestScope.faq.basicHit}">
<input type="hidden" name="basicType" value="${requestScope.faq.basicType}">
<input type="hidden" name="basicWriter" value="${requestScope.faq.basicWriter}">
FAQ제목  <input type="text" name="basicTitle" value="${requestScope.faq.basicTitle}"><span class="error"><form:errors path="FAQForm.basicTitle" /></span><br>
FQA내용 <br>
<textarea rows="30" cols="50" name="basicContent">${requestScope.faq.basicContent}</textarea><span class="error"><form:errors path="FAQForm.basicContent" /></span><br>
<input type="submit" value="수정">
</form>
