<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- header를 따로 만들어 관리 --%>
<%@ include file="../layout/header.jsp"%>

<%-- class="container"는 중앙배치를 위함이다 --%>
<div class="container">

	<table class="table table-hover" style="text-align: center;">
		<thead>
			<tr>
				<th width="7.5%">번호</th>
				<th width="45%">제목</th>
				<th width="15%">작성자</th>
				<th width="15%">날짜</th>
				<th width="7.5%">조회수</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="board" items="${boards.content }">
				<tr>
					<td>${board.id }</td>
					<td>${board.title }</td>
					<td><i>${board.user.username }</i></td>
					<td><fmt:formatDate value="${board.createDate}" pattern="yyyy-MM-dd" /></td>
					<td><span class="badge badge-primary badge-pill"><fmt:formatNumber value="${board.count }" /></span></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>



	<br />

	<ul class="pagination justify-content-center">
		<%-- justify-content-center => 가운데 정렬 --%>

		<c:choose>
			<c:when test="${boards.first }">
				<li class="page-item disabled"><a class="page-link" href="?page=${boards.number-1 }">이전</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="?page=${boards.number-1 }">이전</a></li>
			</c:otherwise>
		</c:choose>

		<c:choose>
			<c:when test="${boards.last }">
				<li class="page-item disabled"><a class="page-link" href="?page=${boards.number+1 }">다음</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="?page=${boards.number+1 }">다음</a></li>
			</c:otherwise>
		</c:choose>
	</ul>

</div>

<%-- footer를 따로 만들어 관리 --%>
<%@ include file="../layout/footer.jsp"%>