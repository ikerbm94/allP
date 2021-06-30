<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- header를 따로 만들어 관리 --%>
<%@ include file="../layout/header.jsp"%>

<div class="container">

	<input type="hidden" id="boardId" value="${board.id }" />

	<div class="form-group">
		<label for="title">제목</label> <input value="${board.title }" type="text" class="form-control" id="title">
	</div>

	<div class="form-group">
		<label for="content">내용</label>
		<textarea class="form-control summernote" rows="5" id="content">${board.content }</textarea>
	</div>

	<button id="btn-board-update" class="btn btn-primary">수정하기</button>

</div>

<%-- Summer Note의 Javascript 부분이다 --%>
<%-- class를 지칭하는 .으로 summernote를 찾는다 --%>
<script>
	$('.summernote').summernote({
		placeholder : 'Enter Content',
		tabsize : 2,
		height : 300
	});
</script>

<%-- static 폴더의 경로까지도 기본세팅이 되어있기 때문에 /js/board.js로 해도 문제없다 --%>
<script src="/js/board.js"></script>
<%-- footer를 따로 만들어 관리 --%>
<%-- 위치가 views인 index.jsp에서와는 달리 joinForm.jsp의 위치는 views/user이기 때문에 앞에 ../를 붙여줘야 한다 --%>
<%@ include file="../layout/footer.jsp"%>