<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<form action="/UspaceAcademy/lectureInquiry/insertLectureInquiry.do" method="post" name="insertLectureInquiry">
<input type="hidden" name="codeName" value="${requestScope.codeName}">
<table>
	<tr>
		<td>
			제목: <input type="text" name="advancedTitle" size="70" value="${requestScope.lectureInquiryValidate.advancedTitle }" 
			<span class="error"> <form:errors path="lectureInquiry.advancedTitle" delimiter="//" /></span>>
		</td>
	</tr>
	<tr>
		<td>
			내용: <textarea rows="20" cols="100" name="advancedContent">${requestScope.lectureInquiryValidate.advancedContent }
			<span class="error"> <form:errors path="lectureInquiry.advancedContent" delimiter="//" /></span>
			</textarea>
			
		</td>
	</tr>
	<tr>
		<td align="center"><input type="submit" value="등록"> <input type="reset" value="초기화"></td>
	</tr>	
</table>
</form>

<a href="/UspaceAcademy/inquiry/inquiryList.do">1:1문의 목록보기</a>