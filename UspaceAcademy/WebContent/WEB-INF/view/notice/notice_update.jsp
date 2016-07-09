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
<script type="text/javascript">
	$(document).ready(function() {
		$("#btn").on("click", function() {
			if(!$("input[name=basicTitle]").val()) {
				alert("공지제목을 입력하세요.");
				return false;
			}
			if(!$("textarea[name=basicContent]").val()) {
				alert("공지내용을 입력하세요.");
				return false;
			}
		})
	})

</script>
<h3 class="pageTlt">공지사항 수정</h3>
<hr>
<form action="/UspaceAcademy/notice/noticeUpdate.do?page=${param.page}" method="post">
<input type="hidden" name="basicNo" value="${requestScope.notice.basicNo}">
<input type="hidden" name="basicDate" value="${requestScope.notice.basicDate}">
<input type="hidden" name="basicType" value="${requestScope.notice.basicType}">
<input type="hidden" name="basicWriter" value="${requestScope.notice.basicWriter}">
<input type="hidden" name="basicHit" value="${requestScope.notice.basicHit}">
<table class="table table-bordered form-table">
	<tbody>
		<tr>
			<th>제목</th>
			<td><input type="text" name="basicTitle" value="${requestScope.notice.basicTitle}"><span class="error"><form:errors path="updateForm.basicTitle"/></span></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea name="basicContent" class="form-control">${requestScope.notice.basicContent}</textarea><span class="error"><form:errors path="updateForm.basicContent"/></span></td>
	</tbody>
</table>
<input type="submit" value="수정" class="btn btn-primary" id="btn">
</form>

