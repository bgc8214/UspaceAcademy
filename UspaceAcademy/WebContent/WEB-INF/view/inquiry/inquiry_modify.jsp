<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 수정 폼 -->
<form action="/UspaceAcademy/inquiry/updateInquiry.do" method="post">
<input type="hidden" name="advancedNo" value="${requestScope.inquiryDetail.advancedNo }">
<input type="hidden" name="advancedSecret" value="${requestScope.inquiryDetail.advancedSecret }">
<input type="hidden" name="advancedHit" value="${requestScope.inquiryDetail.advancedHit }">
<input type="hidden" name="advancedId" value="${requestScope.inquiryDetail.advancedId }">

<table>
	<tr>
		<td>
			제목: <input type="text" name="advancedTitle" size="70" value="${requestScope.inquiryDetail.advancedTitle }">
		</td>
	</tr>
	<tr>
		<td>
			내용: <textarea rows="20" cols="100" name="advancedContent">${requestScope.inquiryDetail.advancedContent }</textarea>
		</td>
	</tr>
	<tr>
		<td align="center"><input type="submit" value="수정"> <input type="reset" value="초기화"></td>
	</tr>	
</table>
</form>
