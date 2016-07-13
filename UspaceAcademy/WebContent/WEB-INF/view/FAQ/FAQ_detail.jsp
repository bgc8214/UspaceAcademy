<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
제목 <input type="text" value="${requestScope.faq.basicTitle}" readonly="readonly">&nbsp;&nbsp;&nbsp;&nbsp; 
등록일 <input type="text" value="${requestScope.faq.basicDate}" readonly="readonly"><br>
FAQ내용 <br>
<textarea rows="45" cols="100" readonly="readonly">${requestScope.faq.basicContent}</textarea>
<p>
<a href="/UspaceAcademy/FAQ/FAQList.do?type=${requestScope.faq.basicType}"><button class="listFAQ">FAQ리스트</button></a>
<a href="/UspaceAcademy/FAQ/FAQModifyForm.do?no=${requestScope.faq.basicNo}&hit=${requestScoipe.faq.basicHit"><button class="updateFAQ">FAQ수정</button></a>
<a href="/UspaceAcademy/FAQ/FAQRemove.do?no=${requestScope.faq.basicNo}&type=${requestScope.faq.basicType}"><button class="deleteFAQ">FAQ삭제</button></a>