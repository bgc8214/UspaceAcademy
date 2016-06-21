<%@ page contentType ="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<hr/>
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
				<tr>
					<td>${notice.basicNo}</td>
					<td>${notice.basicTitle}</td>
					<td>${notice.basicContent}</td>
					<td>${notice.basicDate}</td>
				</tr>
			</c:forEach>
		</tbody>	
	</table>
