<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
제목 <input type="text" value="${requestScope.faq.basicTitle}" readonly="readonly">&nbsp;&nbsp;&nbsp;&nbsp; 
등록일 <input type="text" value="${requestScope.faq.basicDate}" readonly="readonly"><br>
FAQ내용 <br>
<textarea rows="45" cols="100" readonly="readonly">${requestScope.faq.basicContent}</textarea>
<p>
<a href="/UspaceAcademy/FAQ/list.do?type=${requestScope.faq.basicType}"><input type="button" value="FAQ 리스트"></a>
<a href="/UspaceAcademy/FAQ/FAQUpdateForm.do?no=${requestScope.faq.basicNo}&hit=${requestScoipe.faq.basicHit"><input type="button" value="FAQ 수정"></a>
<a href="/UspaceAcademy/FAQ/FAQDelete.do?no=${requestScope.faq.basicNo}&type=${requestScope.faq.basicType}"><input type="button" value="FAQ 삭제"></a>