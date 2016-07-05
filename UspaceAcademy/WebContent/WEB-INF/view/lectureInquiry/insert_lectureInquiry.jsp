<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<form action="/UspaceAcademy/lectureInquiry/registerLectureInquiry.do" method="post">
<input type="hidden" name="lectureNo2" value="${requestScope.lectureNo2 }">
<table border="1">
	<tr>
		<td>
			제목: <input type="text" name="advancedTitle" size="70" value="${requestScope.lectureInquiryValidate.advancedTitle }">
				<span class="error"> <form:errors path="lectureInquiry.advancedTitle" delimiter="//" /></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<label>비밀글 : <input type="radio" name="advancedSecret" value="true"></label>
				<span class="error"><form:errors path="lectureInquiry.advancedSecret" delimiter="//"/></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<label>공개글 : <input type="radio" name="advancedSecret" value="false"></label>
				<span class="error"><form:errors path="lectureInquiry.advancedSecret" delimiter="//"/></span>
		</td>
	</tr>
	<tr>
		<td>
			내용: <textarea rows="20" cols="100" name="advancedContent">${requestScope.lectureInquiryValidate.advancedContent }</textarea>
				<span class="error"> <form:errors path="lectureInquiry.advancedContent" delimiter="//" /></span>
			
		</td>
	</tr>
	<tr>
		<td><input type="submit" value="등록"> <input type="reset" value="초기화"></td>
	</tr>	
</table>
</form>

<a href="/UspaceAcademy/lectureInquiry/lectureInquiryList.do?lectureNo2=${requestScope.lectureNo2 }">전체 목록보기</a>