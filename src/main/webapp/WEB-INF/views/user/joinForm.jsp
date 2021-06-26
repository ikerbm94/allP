<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- header를 따로 만들어 관리 --%>
<%-- 위치가 views인 index.jsp에서와는 달리 joinForm.jsp의 위치는 views/user이기 때문에 앞에 ../를 붙여줘야 한다 --%>
<%@ include file="../layout/header.jsp"%>

<div class="container">

	<form>
		
		<div class="form-group">
			<label for="username"><b>아이디</b></label>
			&nbsp;&nbsp;&nbsp;
			<label><font style="color:#999; size:12px;">※ 5~20자 내로 영어와 숫자만 입력해주세요</font></label>
			<input type="text" class="form-control" id="username">
			<div id="username_check"></div>
		</div>
		
		<div class="form-group">
			<label for="password"><b>비밀번호</b></label>
			&nbsp;&nbsp;&nbsp;
			<label><font style="color:#999; size:12px;">※ 8~25자 내로 영문, 숫자, 특수문자(!, @, #, $, %, ^, &, *)를 각각 최소 한 개 이상 입력해주세요</font></label>
			<input type="password" class="form-control" id="password">
		</div>

		<div class="form-group">
			<label for="passwordSync"><b>비밀번호 확인</b></label>
			<input type="password" class="form-control" id="passwordSync">
			<div id="password_check"></div>
		</div>
		
		<div class="form-group">
			<label for="email"><b>이메일</b></label>
			<input type="email" class="form-control" id="email">
		</div>
		
		<input type="button" id="btn-save" class="btn btn-primary" value="회원가입" />
		
	</form>
	
</div>

<%-- static 폴더의 경로까지도 기본세팅이 되어있기 때문에 /js/user.js로 해도 문제없다 --%>
<script src="/js/joinForm.js"></script>
<%-- footer를 따로 만들어 관리 --%>
<%-- 위치가 views인 index.jsp에서와는 달리 joinForm.jsp의 위치는 views/user이기 때문에 앞에 ../를 붙여줘야 한다 --%>
<%@ include file="../layout/footer.jsp"%>