<%@ page contentType ="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
table#tb {
	width: 700px;
	heiht: 100px;
}
</style>
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
 				$('<tr class="dummy"><td colspan="7" class="dummy"><textarea rows="10" cols="50" readonly="readonly" class="form-control">'+faq.basicContent+'</textarea></td></tr>').insertAfter(tmp); // 이벤트 소스의 다음 형제로 추가
				
			
				tmp.children().eq(3).text(faq.basicHit); 
				tmp.next().children().eq(0).append("<br>"); 
				if("${sessionScope.memberType}"=='administrator') {
					tmp.next().children().eq(0).append($("<a href="+"/UspaceAcademy/FAQ/FAQModifyForm.do?no="+tmp.children().eq(0).text()+"&hit="+faq.basicHit+"&page="+$("#page").val()+"><button class='btn btn-warning'>FAQ수정</button></a>"));
					tmp.next().children().eq(0).append($("<a href="+"/UspaceAcademy/FAQ/FAQRemove.do?no="+tmp.children().eq(0).text()+"&type="+faq.basicType+"&page="+$("#page").val()+"><button class='btn btn-danger'>FAQ삭제</button></a>"));

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
		$(this).css("background-color", "#D8EFF1");
		this.style.cursor = 'pointer';

	});

	$(".faqList").on("mouseout", function() {
		$(this).css("background-color", "white");
	})
	
	$("#btn").on("click", function() {
		if(!$("input[name=title]").val()) {
			alert("제목을 입력하세요.");
			return false;
		}
	})
})
</script>
<h3 class="pageTlt">FAQ</h3>
<hr>
	<table class="table table-bordered" id="tb">
		<thead>
			<tr>
				<th>No</th>
				<th>FAQ제목</th>
				<th>등록일</th>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody>
		<form><input id="page" type="hidden" value="${param.page }"></form>
			<c:forEach var="faq" items="${requestScope.FAQList}">
				<tr class="faqList">
					<td>${faq.basicNo}</td>
					<td>${faq.basicTitle}</td>
					<td>${faq.basicDate}</td>
					<td>${faq.basicHit}</td>
				</tr>
			</c:forEach>
		</tbody>	
	</table><p>


<%--◀이전 페이지 그룹 처리 --%>
<div class="pageNav" align="center">
	<c:choose>
		<c:when test="${requestScope.paging.previousPageGroup }">
			<a href="/UspaceAcademy/FAQ/FAQList.do?page=${requestScope.paging.beginPage-1}&type=FAQ" class="prevPage">
			<!-- ◀ --><strong>이전</strong>
			</a>
		</c:when>
		<c:otherwise><strong>이전</strong></c:otherwise>
	</c:choose>
	<%--페이지 처리 --%>
	<c:forEach begin="${requestScope.paging.beginPage }" end="${requestScope.paging.endPage }" var="page">
		<c:choose>
			<c:when test="${page == requestScope.paging.page }">
			<strong>${page}</strong>
			</c:when>
			<c:otherwise>
				<a href="/UspaceAcademy/FAQ/FAQList.do?page=${page}&type=FAQ">
				<strong>${page}</strong>
				</a>
			</c:otherwise>
		</c:choose>
	&nbsp;&nbsp;
	</c:forEach>
	<%--다음 페이지 그룹 처리 ▶--%>
	<c:choose>
		<c:when test="${requestScope.paging.nextPageGroup }">
			<a href="/UspaceAcademy/FAQ/FAQList.do?&page=${requestScope.paging.endPage + 1}&type=FAQ" class="nextPage">
			<!-- ▶ --><strong>다음</strong>
			</a>
		</c:when>
		<c:otherwise><strong>다음</strong></c:otherwise>
	</c:choose>
</div>
<p>
<!-- 관리자용 FAQ 등록 버튼 -->
<span class="FAQRegister"> 
	<c:if test="${sessionScope.memberType=='administrator'}">
	<div align="right">
		<a href="/UspaceAcademy/FAQ/codeList.do?codeNames=FAQ"><button class="btn btn-success">FAQ등록</button></a>
	</div>
	</c:if>
</span>	
<p>

<!-- 제목으로 검색 -->
<div class="boardBottom">
<form action="/UspaceAcademy/FAQ/FAQTitleSearch.do" method="post">
	<fieldset>
		<input type="text" name="title" placeholder="제목을 입력하세요"/>
		<input id="btn" type="submit" value="제목으로 검색" class="btn btn-info">
	</fieldset>
</form>
</div><br>



