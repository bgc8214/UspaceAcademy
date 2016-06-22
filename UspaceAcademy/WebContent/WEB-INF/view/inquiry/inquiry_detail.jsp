<%@ page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%-- no: ${requestScope.insertInquiry.advancedNo}<br>
제목: ${requestScope.insertInquiry.advancedNo}<br>
글쓴이: ${requestScope.inquiry.advancedId}<br>
비밀글 여부: ${requestScope.inquiry.advancedSecret}<br>
내용<br>
${requestScope.inquiry.advancedContent}<br>
글 등록일: ${requestScope.inquiry.advancedDate}<br>
조회수: ${requestScope.inquiry.advancedHit}<br>
강의 번호: ${requestScope.inquiry.lectureNo2}<br> --%>

<table>
	<tr>
		<td>
			no: ${requestScope.inquiryDetail.advancedNo}<br> 글쓴이: ${requestScope.inquiryDetail.advancedId}<br>글 등록일:${requestScope.inquiryDetail.advancedDate}
			<br>조회수: ${requestScope.inquiryDetail.advancedHit}
		</td>
	</tr>
	<tr>
		<td>
			제목: ${requestScope.inquiryDetail.advancedTitle}
		</td>
	</tr>
	<tr>
		<td>
			내용: ${requestScope.inquiryDetail.advancedId}
		</td>
	</tr>
</table>

<p>

<%-- <a href="/UspaceAcademy/inquiry/updateInquiryByAdvancedNo.do?advancedNo=${requestScope.inquiryDetail.advancedNo }">1:1문의 수정</a> --%>
<a href="/UspaceAcademy/inquiry/updateInquiryByAdvancedNo.do?advancedNo=1">1:1문의 수정</a>
<a href="/UspaceAcademy/inquiry/deleteInquiryByAdvancedNo.do?advancedNo=${requestScope.inquiryDetail.advancedNo }">1:1문의 삭제</a>
<a href="/UspaceAcademy/inquiry/inquiryList.do">1:1문의 목록</a>
