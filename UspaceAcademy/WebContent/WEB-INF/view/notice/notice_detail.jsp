<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
제목 <input type="text" value="${requestScope.notice.basicTitle}" readonly="readonly">&nbsp;&nbsp;&nbsp;&nbsp; 
등록일 <input type="text" value="${requestScope.notice.basicDate}" readonly="readonly"><br>
공지내용 <br>
<textarea rows="45" cols="100" readonly="readonly">${requestScope.notice.basicContent}</textarea>
<p>
<a href="/UspaceAcademy/notice/list.do?type=${requestScope.notice.basicType}">공지사항 리스트</a>
<a href="/UspaceAcademy/notice/noticeUpdateForm.do?no=${requestScope.notice.basicNo}"><input type="button" value="공지사항 수정"></a>
<a href="/UspaceAcademy/notice/noticeDelete.do?no=${requestScope.notice.basicNo}&type=${requestScope.notice.basicType}"><input type="button" value="공지사항 삭제"></a>

