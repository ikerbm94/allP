<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- header를 따로 만들어 관리 --%>
<%-- 위치가 views인 index.jsp에서와는 달리 joinForm.jsp의 위치는 views/user이기 때문에 앞에 ../를 붙여줘야 한다 --%>
<%@ include file="../layout/header.jsp"%>

<div class="container">

	<form>
	
		<input type="hidden" id="id" value="${principal.user.id }" />
		
		<div class="form-group">
			<label for="username"><b>아이디</b></label>
			<%-- username은 수정이 안되게끔 readonly를 걸어준다 --%>
			<input type="text" value="${principal.user.username }" class="form-control" placeholder="Enter username" id="username" readonly="readonly" />
		</div>
		
		<div class="form-group">
			<label for="password"><b>비밀번호</b></label>
			<input type="password" class="form-control" placeholder="Enter password" id="password" />
		</div>
		
		<div class="form-group">
			<label for="email"><b>이메일</b></label>
			<input type="email" class="form-control" placeholder="Enter email" id="email" />
		</div>
		
	</form>
	
	<button id="btn-update" class="btn btn-primary">회원수정</button>
	
</div>

<%-- static 폴더의 경로까지도 기본세팅이 되어있기 때문에 /js/user.js로 해도 문제없다 --%>
<script src="/js/updateForm.js"></script>
<%-- footer를 따로 만들어 관리 --%>
<%-- 위치가 views인 index.jsp에서와는 달리 joinForm.jsp의 위치는 views/user이기 때문에 앞에 ../를 붙여줘야 한다 --%>
<%@ include file="../layout/footer.jsp"%>