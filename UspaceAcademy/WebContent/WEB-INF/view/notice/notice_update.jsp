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
<form action="/UspaceAcademy/notice/noticeUpdate.do?page=${param.page}" method="post">
<input type="hidden" name="basicNo" value="${requestScope.notice.basicNo}">
<input type="hidden" name="basicDate" value="${requestScope.notice.basicDate}">
<input type="hidden" name="basicType" value="${requestScope.notice.basicType}">
<input type="hidden" name="basicWriter" value="${requestScope.notice.basicWriter}">
<input type="hidden" name="basicHit" value="${requestScope.notice.basicHit}">
공지제목 : <input type="text" name="basicTitle" value="${requestScope.notice.basicTitle}"><span class="error"><form:errors path="updateForm.basicTitle"/></span><br>
공지내용 : <br>
<textarea rows="45" cols="100" name="basicContent">${requestScope.notice.basicContent}</textarea><span class="error"><form:errors path="updateForm.basicContent"/></span><br>
<input type="submit" value="수정">${requestScope.test }
</form>

