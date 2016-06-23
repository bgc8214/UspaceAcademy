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
<form action="/UspaceAcademy/FAQ/FAQWrite.do" method="post">
<input type="hidden" name="basicType" value="${requestScope.codeName}">
FAQ제목 : <input type="text" name="basicTitle" value="${requestScope.FAQForm.basicTitle}"><span class="error"><form:errors path="FAQForm.basicTitle" /></span><br>
FAQ내용 : <br>
<textarea rows="45" cols="100" name="basicContent">${requestScope.FAQForm.basicContent}</textarea><span class="error"><form:errors path="FAQForm.basicContent" /></span><br>
<input type="submit" value="전송">
</form>
