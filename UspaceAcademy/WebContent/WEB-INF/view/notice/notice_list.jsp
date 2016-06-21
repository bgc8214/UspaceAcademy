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
				<td>등록일</td>
				<td>조회수</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="notice" items="${requestScope.list}">
				<tr id="tb">
					<td>${notice.basicNo}</td>
					<td><a href="/UspaceAcademy/notice/noticeDetail.do?no=${notice.basicNo}">${notice.basicTitle}</a></td>
					<td>${notice.basicDate}</td>
					<td>${notice.basicHit}</td>
				</tr>
			</c:forEach>
		</tbody>	
	</table><p>
<a href="/UspaceAcademy/notice/codeList.do?codeNames=공지사항"><input type="button" value="공지사항등록"></a>
