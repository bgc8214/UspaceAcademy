<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<form action="/UspaceAcademy/inquiry/insertInquiry.do" method="post" name="insertInquiry">
<input type="hidden" name="advancedType" value="${requestScope.advancedType}">
<table>
	<tr>
		<td>
			제목: <input type="text" name="advancedTitle" size="70" value="${requestScope.inquiryValidate.advancedTitle }">
			<span class="error"> <form:errors path="inquiry.advancedTitle" delimiter="//" /></span>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	<label>비밀글 : <input type="radio" name="secret" value="true"></label>
			<span class="error"><form:errors path="inquiry.advancedSecret" delimiter="//"/></span>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	<label>공개글 : <input type="radio" name="secret" value="false"></label>
			<span class="error"><form:errors path="inquiry.advancedSecret" delimiter="//"/></span>
			
		</td>
	</tr>	
	<tr>
		<td>
			내용: <textarea rows="20" cols="100" name="advancedContent">${requestScope.inquiryValidate.advancedContent }</textarea>
			<span class="error"> <form:errors path="inquiry.advancedContent" delimiter="//" /></span>
		</td>
	</tr>
	<tr>
		<td align="center"><input type="submit" value="등록"> <input type="reset" value="초기화"></td>
	</tr>	
</table>
</form>

<a href="/UspaceAcademy/inquiry/inquiryList.do">1:1문의 목록보기</a>
<%-- ${requestScope.insertInquiry} --%>