<%@ page contentType ="text/html;charset=utf-8"%>
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
				$('<tr class="dummy"><td colspan="7" class="dummy"></td></tr>').insertAfter(tmp); //이벤트소스의 다음 형제로 추가해준다.
				var txt = "답변 : "+faq.basicContent+"<br>";
				var txt1 = faq.basicNo;
/* 				alert(tmp.children().eq(0).text()); */
				
				tmp.children().eq(3).text(faq.basicHit);
				tmp.next().children().eq(0).append(txt);
			/* 	tmp.next().children().eq(0).append($("<a href='/UspaceAcademy/FAQ/FAQUpdateForm.do?no='+tmp.children().eq(0).text()><input type='button' value='FAQ수정'></a>")); */
				
				tmp.next().children().eq(0).append($("<a href="+"/UspaceAcademy/FAQ/FAQUpdateForm.do?no="+tmp.children().eq(0).text()+"><input type='button' value='FAQ수정'></a>"));
				tmp.next().children().eq(0).append($("<a href="+"/UspaceAcademy/FAQ/FAQDelete.do?no="+tmp.children().eq(0).text()+"&type="+faq.basicType+"><input type='button' value='FAQ삭제'></a>"));
			},
			"error":function(xhr, status, errorMsg) {
				alert("오류발생"+status+","+errorMsg);
			},
			"beforeSend":function() {
				
			}
		})
	})	
})
</script>

<hr>
	<table border="2">
		<thead>
			<tr>
				<th>No</th>
				<th>FAQ제목</th>
<!-- 				<th>FAQ제목</th> -->
				<th>등록일</th>
				<th>조회수</th>
				<th>타입</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="faq" items="${requestScope.list}">
				<tr class="faqList">
					<td>${faq.basicNo}</td>
					<td>${faq.basicTitle}</td>
<%-- 					<td><a href="/UspaceAcademy/FAQ/FAQDetail.do?no=${faq.basicNo}">${faq.basicTitle}</a></td> --%>
					<td>${faq.basicDate}</td>
					<td>${faq.basicHit}</td>
					<td>${faq.basicType}</td>
				</tr>
			</c:forEach>
		</tbody>	
	</table><p>
	
<a href="/UspaceAcademy/FAQ/codeList.do?codeNames=FAQ"><input type="button" value="FAQ등록"></a>
