<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- <form action="/UspaceAcademy/inquiry/insertInquiry.do" method="post">
<input type="hidden" name="codeName" value="${requestScope.codeName}">
제목: <input type="text" name="title"><br>
내용<br>
<textarea rows="50" cols="100" name="content"></textarea><br>
<input type="submit" value="등록">
</form> --%>


<form action="/UspaceAcademy/inquiry/insertInquiry.do" method="post" name="insertInquiry">
<input type="hidden" name="codeName" value="${requestScope.codeName}">
<table>
	<tr>
		<td>
			<input type="text" name="advancedTitle" size="70" placeholder="공지사항 제목">
		</td>
	</tr>
	<tr>
		<td>
			<textarea rows="20" cols="100" name="advancedContent" placeholder="내용을 입력하세요."></textarea>
		</td>
	</tr>
	<tr>
		<td align="center"><input type="submit" value="등록"> <input type="reset" value="초기화"></td>
	</tr>	
</table>
</form>

<a href="/UspaceAcademy/inquiry/inquiryList.do">1:1문의 목록보기</a>
<%-- ${requestScope.insertInquiry} --%>