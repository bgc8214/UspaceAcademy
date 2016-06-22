<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 수정 폼 -->
<form action="/UspaceAcademy/inquiry/updateInquiryByAdvancedNo.do" method="post">
<input type="hidden" name="no" value="${requestScope.inquiryDetail.advancedNo }">

<table>
	<tr>
		<td>
			<input type="text" name="title" size="70" value="${requestScope.inquiryDetail.advancedTitle }">
		</td>
	</tr>
	<tr>
		<td>
			<textarea rows="20" cols="100" name="content">${requestScope.inquiryDetail.advancedContent }</textarea>
		</td>
	</tr>
	<tr>
		<td align="center"><!-- <input type="submit" value="수정"> --> <input type="reset" value="초기화"></td>
	</tr>	
</table>
</form>
