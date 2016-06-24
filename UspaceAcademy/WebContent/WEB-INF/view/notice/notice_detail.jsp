<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

제목 <input type="text" value="${requestScope.notice.basicTitle}" readonly="readonly">&nbsp;&nbsp;&nbsp;&nbsp; 
등록일 <input type="text" value="${requestScope.notice.basicDate}" readonly="readonly"><br>
공지내용 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;&nbsp;  &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;
조회수 <input type="text" value="${requestScope.notice.basicHit}" readonly="readonly"><br>
<textarea rows="45" cols="100" readonly="readonly">${requestScope.notice.basicContent}</textarea>
<p>
<a href="/UspaceAcademy/notice/list.do?type=${requestScope.notice.basicType}&page=${param.page}"><input type="button" value="공지사항리스트"></a>

<!-- 관리자용 공지사항 등록 버튼 -->
<span class="lectureRegister">
	<c:if test="${sessionScope.memberType=='administrator'}">
		<a href="/UspaceAcademy/notice/noticeUpdateForm.do?no=${requestScope.notice.basicNo}&page=${param.page}"><input type="button" value="공지사항 수정"></a>
	<a href="/UspaceAcademy/notice/noticeDelete.do?no=${requestScope.notice.basicNo}&type=${requestScope.notice.basicType}&page=${param.page}"><input type="button" value="공지사항 삭제"></a>	
	</c:if>
</span>







