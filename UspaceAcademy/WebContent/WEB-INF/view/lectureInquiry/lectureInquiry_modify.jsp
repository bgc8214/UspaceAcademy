<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<form action="/UspaceAcademy/lectureInquiry/updateLectureInquiry.do" method="post" name="updateLectureInquiry">
<input type="hidden" name="advancedNo" value="${requestScope.lectureInquiryDetail.advancedNo }">
<input type="hidden" name="lectureNo2" value="${requestScope.lectureInquiryDetail.lectureNo2 }">
<input type="hidden" name="advancedSecret" value="${requestScope.lectureInquiryDetail.advancedSecret }">

<table>
	<tr>
		<td>
			제목: <input type="text" name="advancedTitle" size="70" value="${requestScope.lectureInquiryDetail.advancedTitle }">
			<span class="error"> <form:errors path="lectureInquiryForm.advancedTitle" delimiter="//" /></span>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	<label>비밀글 : <input type="radio" name="advancedSecret" value="true"></label>
			<span class="error"><form:errors path="lectureInquiryForm.advancedSecret" delimiter="//"/></span>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	<label>공개글 : <input type="radio" name="advancedSecret" value="false"></label>
			<span class="error"><form:errors path="lectureInquiryForm.advancedSecret" delimiter="//"/></span>
			
		</td>
	</tr>	
	<tr>
		<td>
			내용: <textarea rows="20" cols="100" name="advancedContent">${requestScope.lectureInquiryDetail.advancedContent }</textarea>
			<span class="error"> <form:errors path="lectureInquiryForm.advancedContent" delimiter="//" /></span>
		</td>
	</tr>
	<tr>
		<td align="center"><input type="submit" value="등록"> <input type="reset" value="초기화"></td>
	</tr>	
</table>
</form>

<a href="/UspaceAcademy/lectureInquiry/lectureInquiryList.do?lectureNo2=${requestScope.lectureInquiryDetail.lectureNo2 }">1:1문의 목록보기</a>