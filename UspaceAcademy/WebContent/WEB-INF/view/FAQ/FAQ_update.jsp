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
<h3 class="pageTlt">FAQ 수정</h3>
<form action="/UspaceAcademy/FAQ/FAQUpdate.do?page=${page}" method="post">
<input type="hidden" name="basicNo" value="${requestScope.faq.basicNo}">
<input type="hidden" name="basicDate" value="${requestScope.faq.basicDate}">
<input type="hidden" name="basicHit" value="${requestScope.faq.basicHit}">
<input type="hidden" name="basicType" value="${requestScope.faq.basicType}">
<input type="hidden" name="basicWriter" value="${requestScope.faq.basicWriter}">
<table class="table table-bordered form-table">
	<tbody>
		<tr>
			<th>제목</th>
			<td><input type="text" name="basicTitle" value="${requestScope.faq.basicTitle}"><span class="error"><form:errors path="FAQForm.basicTitle" /></span></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea name="basicContent" class="form-control">${requestScope.faq.basicContent}</textarea><span class="error"><form:errors path="FAQForm.basicContent" /></span></td>
	</tbody>
</table>
<input type="submit" value="수정" class="btn btn-primary">
</form>
