<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

no : ${requestScope.notice.basicNo}<br>
제목 : ${requestScope.notice.basicTitle}<br>
등록일: ${requestScope.notice.basicDate}<br>
내용 : ${requestScope.notice.basicContent}
<p>
<a href="/UspaceAcademy/notice/list.do">공지사항 리스트</a>
<a href="/UspaceAcademy/notice/noticeUpdateForm.do"><input type="button" value="공지사항 수정"></a>

형변환 - String

버튼 클릭시 보내기 객체 보내기