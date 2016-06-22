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
			<c:forEach var="faq" items="${requestScope.list}">
				<tr id="tb">
					<td>${faq.basicNo}</td>
					<td><a href="/UspaceAcademy/FAQ/FAQDetail.do?no=${faq.basicNo}">${faq.basicTitle}</a></td>
					<td>${faq.basicDate}</td>
					<td>${faq.basicHit}</td>
				</tr>
			</c:forEach>
		</tbody>	
	</table><p>
<a href="/UspaceAcademy/FAQ/codeList.do?codeNames=FAQ"><input type="button" value="FAQ등록"></a>
