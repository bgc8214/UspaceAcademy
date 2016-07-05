<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h3 class="pageTlt">공지사항 상세페이지</h3>
<hr>
<table class="table table-bordered form-table">
	<tbody>
		<tr>
			<th>제목</th>
			<td>${requestScope.notice.basicTitle}
				<th>조회수</th>
				<td>${requestScope.notice.basicHit}</td>
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td colspan="3">
				<textarea class="form-control">${requestScope.notice.basicContent}</textarea>
			</td>
		</tr>
		<tr>
			<th>등록일</th>
			<td><input type="text" value="${requestScope.notice.basicDate}" readonly="readonly"></td>
			<!-- 관리자용 공지사항 등록 버튼 -->
			<span class="lectureRegister">
			<td colspan="2" align="right">
				<a href="/UspaceAcademy/notice/list.do?type=${requestScope.notice.basicType}"><button class="btn btn-success">공지사항 전체</button></a>
				<c:if test="${sessionScope.memberType=='administrator'}">
				<a href="/UspaceAcademy/notice/noticeUpdateForm.do?no=${requestScope.notice.basicNo}&page=${param.page}"><button class="btn btn-primary">공지사항 수정</button></a>
				<a href="/UspaceAcademy/notice/noticeDelete.do?no=${requestScope.notice.basicNo}&type=${requestScope.notice.basicType}&page=${param.page}"><button class="btn btn-danger">공지사항 삭제</button></a>	
				</c:if>
			</td>	
			</span>
		</tr>
	</tbody>
</table>