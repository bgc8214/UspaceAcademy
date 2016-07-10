<%@ page contentType ="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script type="text/javascript" src="/UspaceAcademy/jQuery/jQuery.js"></script>
<script type="text/javascript">

//폼체크
$(document).ready(function(){
	$("#search").on("click", function(){
		if(!$("input[name=keyword]").val()){
			alert("검색할 내용을 입력하세요!");
			
			return false;
		}
	});
});

</script>

<h3 class="pageTlt">${requestScope.lectureNo2}번 ${requestScope.lectureTitle} 강의 질문 게시판</h3>
<hr>

<table id="tb" class="table table-bordered table-hover">
	<thead>
		<tr>
			<!-- <td>글번호</td> -->			
			<th>제목</th>
			<th>글쓴이</th>
			<th>글 등록일</th>
			<th>조회수</th>
		</tr>
	</thead>
			
	<tbody>
		<input id="page" type="hidden" value="${param.page }">
		<c:forEach items="${requestScope.lectureInquiryList}" var="list">
			<input type="hidden" id="secret" value="${list.advancedSecret}">
			<tr>
				<%-- <td>${list.advancedNo }</td> --%>
				<td>
					<c:choose>
					<c:when test="${list.advancedSecret}">
						<a href="/UspaceAcademy/lectureInquiry/selectByAdvancedNoWithComment.do?advancedNo=${list.advancedNo }
						&advancedSecret=${list.advancedSecret}&lectureNo2=${list.lectureNo2 }" onclick="alert('비밀글 입니다.');">${list.advancedTitle }
						 <img width="15" height="15" src="/UspaceAcademy/image/lock.jpg"></a>
					</c:when>
					<c:otherwise>
						<a href="/UspaceAcademy/lectureInquiry/selectByAdvancedNoWithComment.do?advancedNo=${list.advancedNo }
						&advancedSecret=${list.advancedSecret}&lectureNo2=${list.lectureNo2 }">${list.advancedTitle }</a>
					</c:otherwise>
					</c:choose>
				</td>
				<td>${list.advancedId }</td>
				<td>${list.advancedDate }</td>
				<td>${list.advancedHit }</td>
			</tr> 
 		</c:forEach>
	</tbody>
	
</table>
<p>
<div align="center">
	<%--◀이전 페이지 그룹 처리 --%>
	<c:choose>
		<c:when test="${requestScope.paging.previousPageGroup }">
			<a href="/UspaceAcademy/lectureInquiry/lectureInquiryList.do?page=${requestScope.paging.beginPage - 1}&lectureNo2=${requestScope.lectureNo2}">
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
				<a href="/UspaceAcademy/lectureInquiry/lectureInquiryList.do?page=${page }&lectureNo2=${requestScope.lectureNo2}">
					${page }
				</a>
			</c:otherwise>
		</c:choose>
	&nbsp;&nbsp;
	</c:forEach>
	<%--다음 페이지 그룹 처리 ▶--%>
	<c:choose>
		<c:when test="${requestScope.paging.nextPageGroup }">
			<a href="/UspaceAcademy/lectureInquiry/lectureInquiryList.do?page=${requestScope.paging.endPage + 1}&lectureNo2=${requestScope.lectureNo2}">
			▶
			</a>
		</c:when>
		<c:otherwise>▶</c:otherwise>
	</c:choose>
	</div>
<p>

<!-- 검색관련 -->
<div align="left">
<form action="/UspaceAcademy/lectureInquiry/searchByKeyword.do?page=${param.page }" method="post">
	<input type="hidden" value="${requestScope.lectureNo2 }" name="lectureNo2">
	<select name="searchType">
		<option value="advancedTitle">제목</option>
		<option value="advancedContent">내용</option>
		<!-- <option value="advancedId">글쓴이</option> -->
	</select>
	<input type="text" name="keyword">
	<input id="search" type="submit" value="검색" class="class btn-info">
</form>
</div>
<br>

<c:choose>
	<c:when test="${sessionScope.memberType=='student'}">
		<div align="right">
		<a href="/UspaceAcademy/lectureInquiry/registerLectureInquiryForm.do?lectureNo2=${requestScope.lectureNo2 }"><button class="btn btn-success">질문하기 등록</button></a>
		</div>
	</c:when>
</c:choose>



	