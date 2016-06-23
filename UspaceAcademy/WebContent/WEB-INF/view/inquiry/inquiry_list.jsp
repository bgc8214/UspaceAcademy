<%@ page contentType ="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
목록<br>
<table width="500" border='1'>
	<thead>
		<tr>
			<td>글번호</td>			
			<td>제목</td>
			<td>글쓴이</td>
			<td>글 등록일</td>
			<td>조회수</td>
		</tr>
	</thead>
			
	<tbody>
		<c:forEach items="${requestScope.list}" var="list" >
			<tr>
				<td>${list.advancedNo }</td>
				<td><a href="/UspaceAcademy/inquiry/selectByAdvancedNo.do?advancedNo=${list.advancedNo }">${list.advancedTitle }</a></td>
				<td>${list.advancedId }</td>
				<td>${list.advancedDate }</td>
				<td>${list.advancedHit }</td>
			</tr> 
 		</c:forEach> 
	</tbody>
</table>
<p>
<a href="/UspaceAcademy/inquiry/codeList.do?codeName=1:1문의">1:1문의 등록</a>
	
	<%-- ${requestScope.list} --%>
	