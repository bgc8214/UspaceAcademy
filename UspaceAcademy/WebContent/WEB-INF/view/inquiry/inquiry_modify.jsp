<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 수정 폼 -->
<form action="/UspaceAcademy/lectureInquiry/updateLectureInquiry.do" method="post">
<input type="hidden" name="advancedNo" value="${requestScope.lectureInquiryDetail.advancedNo }">
<input type="hidden" name="advancedSecret" value="${requestScope.lectureInquiryDetail.advancedSecret }">
<input type="hidden" name="advancedHit" value="${requestScope.lectureInquiryDetail.advancedHit }">
<input type="hidden" name="advancedId" value="${requestScope.lectureInquiryDetail.advancedId }">
<%-- <input type="hidden" name="advancedDate" value="${requestScope.inquiryDetail.advancedDate }"> --%>

<table>
	<tr>
		<td>
			제목: <input type="text" name="title" size="70" value="${requestScope.lectureInquiryDetail.advancedTitle }">
		</td>
	</tr>
	<tr>
		<td>
			내용: <textarea rows="20" cols="100" name="content">${requestScope.lectureInquiryDetail.advancedContent }</textarea>
		</td>
	</tr>
	<tr>
		<td align="center"><input type="submit" value="수정"> <input type="reset" value="초기화"></td>
	</tr>	
</table>
</form>
