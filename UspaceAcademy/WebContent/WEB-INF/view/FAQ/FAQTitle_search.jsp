<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="/UspaceAcademy/jQuery/jQuery.js"></script>
<script type="text/javascript">
var tmp;
$(document).ready(function() {
	$(".faqList").on("click", function() {
		tmp = $(this);
		$.ajax({
			"url":"/UspaceAcademy/FAQ/findFAQByNo.do", //요청 URL
			"type":"POST",	// HTTP 요청방식
			"data":{"no":tmp.children().eq(0).text(), "hit":tmp.children().eq(3).text()}, 
			"dataType":"json", //	응답데이터 타입 지정. text는 default
			"success":function(faq) {
				$("tbody tr.dummy").remove();
 				$('<tr class="dummy"><td colspan="7" class="dummy"><textarea rows="10" cols="50" readonly="readonly">답변 :'+faq.basicContent+'</textarea></td></tr>').insertAfter(tmp); // 이벤트 소스의 다음 형제로 추가
				
			
				tmp.children().eq(3).text(faq.basicHit); 
				tmp.next().children().eq(0).append("<br>"); 
				if("${sessionScope.memberType}"=='administrator') {
					tmp.next().children().eq(0).append($("<a href="+"/UspaceAcademy/FAQ/FAQUpdateForm.do?no="+tmp.children().eq(0).text()+"&hit="+faq.basicHit+"&page="+${requestScope.page}+"><input type='button' value='FAQ수정'></a>"));
					tmp.next().children().eq(0).append($("<a href="+"/UspaceAcademy/FAQ/FAQDelete.do?no="+tmp.children().eq(0).text()+"&type="+faq.basicType+"&page="+${requestScope.page}+"><input type='button' value='FAQ삭제' id='deleteFAQ'></a>"));
				}
			},
			"error":function(xhr, status, errorMsg) {
				alert("오류발생"+status+","+errorMsg);
			},
			"beforeSend":function() {
				
			}
		});
	});
	$(".faqList").on("mouseover", function() {
		$(this).css("background-color", "hotpink");

	});

	$(".faqList").on("mouseout", function() {
		$(this).css("background-color", "white");
	});
	
})

</script>







<hr>
	<table border="2" width="600">
		<thead>
			<tr>
				<th>No</th>
				<th>FAQ제목</th>
				<th>등록일</th>
				<th>조회수</th>
				<th>타입</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="faq" items="${requestScope.FAQTitleList}">
				<tr class="faqList">
					<td>${faq.basicNo}</td>
					<td>${faq.basicTitle}</td>
					<td>${faq.basicDate}</td>
					<td>${faq.basicHit}</td>
					<td>${faq.basicType}</td>
				</tr>
			</c:forEach>
		</tbody>	
	</table><p>
	
	
	
	
	<%--◀이전 페이지 그룹 처리 --%>
	<c:choose>
		<c:when test="${requestScope.paging.previousPageGroup }">
			<a href="/UspaceAcademy/FAQ/FAQTitleSearch.do?page=${requestScope.paging.beginPage-1}&title=${requestScope.title}">
			◀
			</a>
		</c:when>
		<c:otherwise>◀</c:otherwise>
	</c:choose>
	<%--페이지 처리 --%>
	<c:forEach begin="${requestScope.paging.beginPage }" end="${requestScope.paging.endPage }" var="page">
		<c:choose>
			<c:when test="${page == requestScope.paging.page }">
			 [${page }]
			</c:when>
			<c:otherwise>
				<a href="/UspaceAcademy/FAQ/FAQTitleSearch.do?page=${page}&title=${requestScope.title}">
					${page}
				</a>
			</c:otherwise>
		</c:choose>
	&nbsp;&nbsp;
	</c:forEach>
	<%--다음 페이지 그룹 처리 ▶--%>
	<c:choose>
		<c:when test="${requestScope.paging.nextPageGroup }">
			<a href="/UspaceAcademy/FAQ/FAQTitleSearch.do?&page=${requestScope.paging.endPage + 1}&title=${requestScope.title}">
			▶
			</a>
		</c:when>
		<c:otherwise>▶</c:otherwise>
	</c:choose>
<p>
<a href="/UspaceAcademy/FAQ/list.do?type=FAQ"><input type="button" value="FAQ 리스트"></a>