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
<form action="/UspaceAcademy/notice/noticeWrite.do" method="post">
<input type="hidden" name="basicType" value="${requestScope.codeName}">
<%-- ${requestScope.codeName}<br> --%>
공지제목 : <input type="text" name="basicTitle" value="${requestScope.notice.basicTitle}"><span class="error"><form:errors path="notice.basicTitle" /></span><br>
공지내용 : <br>
<textarea rows="45" cols="100" name="basicContent">${requestScope.notice.basicContent}</textarea><span class="error"><form:errors path="notice.basicContent" /></span><br>
<input id="btn" type="submit" value="전송">
</form>
