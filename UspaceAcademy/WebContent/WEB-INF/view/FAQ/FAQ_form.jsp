<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style type="text/css">
table#tb {
	width: 700px;
	heiht: 100px;
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
<h3 class="pageTlt">FAQ 등록</h3>
<hr>

<form action="/UspaceAcademy/FAQ/FAQRegister.do" method="post">
<input type="hidden" name="basicType" value="${requestScope.codeName}">
	<table class="table table-bordered form-table" id="tb">
		<tbody>
			<tr>
				<th>제목</th>
				<td><input type="text" name="basicTitle" value="${requestScope.FAQForm.basicTitle}"><span class="error"><form:errors path="FAQForm.basicTitle" /></span></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="15" name="basicContent" class="form-control">${requestScope.FAQForm.basicContent}</textarea><span class="error"><form:errors path="FAQForm.basicContent" /></span>
			</tr>
		</tbody>
	</table>
	<div align="right">
		<input id="btn" type="submit" value="전송" class="btn btn-success">
	</div>	
</form>
