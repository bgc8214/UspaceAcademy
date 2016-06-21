<%@ page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

no: ${requestScope.inquiry.advancedNo}<br>
제목: ${requestScope.inquiry.advancedTitle}<br>
비밀글 여부: ${requestScope.inquiry.advancedSecret}<br>
글 등록일: ${requestScope.inquiry.advancedDate}<br>
내용<br>
${requestScope.inquiry.advancedContent}<br>
조회수: ${requestScope.inquiry.advancedHit}<br>
글쓴이: ${requestScope.inquiry.advancedId}<br>
강의 번호: ${requestScope.inquiry.lectureNo2}<br>
<p>

<a href="/UspaceAcademy/inquiry/inquiry_list.do">1:1문의 목록</a>