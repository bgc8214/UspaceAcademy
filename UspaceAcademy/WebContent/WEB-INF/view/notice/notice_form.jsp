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
				alert("제목을 입력하세요");
				return false;
			}
			if(!$("textarea[name=basicContent]").val()) {
				alert("내용을 입력하세요");
				return false;
			}
		})
	})

</script>
<h3 class="pageTlt">공지사항 등록</h3>
<hr>

<form action="/UspaceAcademy/notice/noticeWrite.do" method="post">
<input type="hidden" name="basicType" value="${requestScope.codeName}">
	<table class="table table-bordered form-table">
		<tbody>
			<tr>
				<th>제목</th>
				<td><input type="text" name="basicTitle" value="${requestScope.notice.basicTitle}"><span class="error"><form:errors path="notice.basicTitle" /></span></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea name="basicContent" class="form-control">${requestScope.notice.basicContent}</textarea><span class="error"><form:errors path="notice.basicContent" /></span></td>
			</tr>
		</tbody>
	</table><p>
	<input id="btn" type="submit" value="전송" class="btn btn-info">
</form>	