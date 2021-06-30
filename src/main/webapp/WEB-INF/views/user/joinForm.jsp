<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- header를 따로 만들어 관리 --%>
<%-- 위치가 views인 index.jsp에서와는 달리 joinForm.jsp의 위치는 views/user이기 때문에 앞에 ../를 붙여줘야 한다 --%>
<%@ include file="../layout/header.jsp"%>

<div class="container">

	<form>

		<p>
			<b>아이디</b>&nbsp;&nbsp;
			<font style="color: #999; size: 12px;">※ 5~20자 내로 영어와 숫자만 입력해주세요</font>
		</p>

		<div class="row">
			<div class="col">
				<input type="text" class="form-control" id="username">
			</div>
			<div class="col"></div>
			<div class="col"></div>
		</div>

		<div id="username_check"></div>

		<br />

		<p>
			<b>비밀번호</b>&nbsp;&nbsp;
			<font style="color: #999; size: 12px;">※ 8~25자 내로 영문, 숫자, 특수문자(!, @, #, $, %, ^, &, *)를 각각 최소 한 개 이상 입력해주세요</font>
		</p>

		<div class="row">
			<div class="col">
				<input type="password" class="form-control" id="password">
			</div>
			<div class="col"></div>
		</div>

		<br />

		<p>
			<b>비밀번호 확인</b>
		</p>
		<div class="row">
			<div class="col">
				<input type="password" class="form-control" id="passwordSync">
			</div>
			<div class="col"></div>
		</div>

		<div id="password_check"></div>

		<br />

		<p>
			<b>이메일</b>&nbsp;&nbsp;
			<font style="color: #999; size: 12px;">※ 이메일 인증후에 회원가입이 가능합니다</font>
		</p>

		<div class="row">
		
			<div class="col">
				<input type="text" class="form-control" id="address">
			</div>
			@
			<div class="col">
				<input type="text" class="form-control" id="domain">
			</div>
			
			<div class="col-lg-3">
				<select class="form-control" id="domain_select">
					<option value="self">직접입력</option>
					<option value="naver.com">naver.com</option>
					<option value="kakao.com">kakao.com</option>
					<option value="gmail.com">gmail.com</option>
					<option value="nate.com">nate.com</option>
					<option value="daum.net">daum.net</option>
					<option value="hanmail.net">hanmail.net</option>
				</select>
			</div>
			
			<div class="col">
				<input type="button" id="email_validate" class="btn btn-primary" value="인증하기" />
			</div>
			
		</div>

		<div id="email_check"></div>

		<br/><br/> <input type="button" id="btn-save" class="btn btn-primary" value="회원가입" />

	</form>

</div>

<%-- static 폴더의 경로까지도 기본세팅이 되어있기 때문에 /js/user.js로 해도 문제없다 --%>
<script src="/js/joinForm.js"></script>
<%-- footer를 따로 만들어 관리 --%>
<%-- 위치가 views인 index.jsp에서와는 달리 joinForm.jsp의 위치는 views/user이기 때문에 앞에 ../를 붙여줘야 한다 --%>
<%@ include file="../layout/footer.jsp"%>