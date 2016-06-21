<%@ page contentType ="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- <script type="text/javascript" src="/UspaceAcademy/jQuery/jQuery.js"></script>
<script type="text/javascript">
$(document).ready(function() {
		$("#tbody tr").on("click", function(){
			$("#tb").css("background-color", "green");
		});
});

</script>
 -->

<hr>
	<table border="2">
		<thead>
			<tr>
				<td>No</td>
				<td>공지제목</td>
				<td>공지내용</td>
				<td>등록일</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="notice" items="${requestScope.list}">
				<tr id="tb">
					<td>${notice.basicNo}</td>
					<td>${notice.basicTitle}</td>
					<td>${notice.basicContent}</td>
					<td>${notice.basicDate}</td>
				</tr>
			</c:forEach>
		</tbody>	
	</table>
