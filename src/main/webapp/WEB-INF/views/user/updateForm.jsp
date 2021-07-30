<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- header를 따로 만들어 관리 --%>
<%-- 위치가 views인 index.jsp에서와는 달리 joinForm.jsp의 위치는 views/user이기 때문에 앞에 ../를 붙여줘야 한다 --%>
<%@ include file="../layout/header.jsp"%>

<div class="container">

	<form>

		<input type="hidden" id="id" value="${principal.user.id }" />

		<p>
			<b>아이디</b>
		</p>
		<div class="row">
			<div class="col">
				<input type="text" class="form-control" value="${principal.user.username }" id="username" readonly="readonly">
			</div>
			<div class="col"></div>
		</div>
		<br />

		<p>
			<b>비밀번호</b>
		</p>
		<div class="row">
			<div class="col">
				<input type="password" class="form-control" readonly="readonly">
			</div>
			<div class="col">
				<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#password_modal">비밀번호 수정</button>
			</div>
		</div>
		<br />

		<!-- 비밀번호 수정 모달 -->
		<div class="modal fade" id="password_modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-body">

						<font style="color: #999; size: 12px;">※ 8~25자 내로 영문, 숫자, 특수문자(!, @, #, $, %, ^, &, *)를 각각 최소 한 개 이상 입력해주세요.</font> <br /> <br />

						<p>
							<b>비밀번호</b>
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

					</div>
					<div class="modal-footer">
						<input type="hidden" id="password_validation" value="0">
						<button type="button" class="btn btn-primary" id="update_password">수정</button>
						<button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
					</div>
				</div>
			</div>
		</div>

		<p>
			<b>이메일</b>
		</p>
		<div class="row">
			<div class="col">
				<input type="email" class="form-control" id="email" value="${principal.user.email }" readonly="readonly" />
			</div>
			<div class="col">
				<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#email_modal">이메일 수정</button>
			</div>
		</div>

		<!-- 이메일 수정 모달 -->
		<div class="modal fade" id="email_modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">

					<div class="modal-body">
						<font style="color: #999; size: 12px;">※ 이메일 인증후에 수정이 가능합니다.</font> <br /> <br />

						<p>
							<b>이메일</b>
						</p>
						<div class="row">

							<div class="col">
								<input type="text" class="form-control" id="address">
							</div>
							@
							<div class="col">
								<input type="text" class="form-control" id="domain">
							</div>

							<div class="col">
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
								<input type="button" class="btn btn-primary" id="email_validate" value="인증번호 전송">
							</div>

						</div>

						<div id="email_check"></div>

						<br />

						<div class="row">
							<div class="col">
								<input type="hidden" class="form-control" id="user_code">
							</div>
							<div class="col">
								<input type="hidden" class="btn btn-primary" id="code_validate" value="인증하기">
							</div>
							<div class="col">
								<input type="hidden" id="code">
							</div>
						</div>

						<div id="code_check"></div>
					</div>

					<div class="modal-footer">
						<input type="hidden" id="email_validation" value="0">
						<button type="button" class="btn btn-primary" id="update_email">수정</button>
						<button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
					</div>

				</div>
			</div>
		</div>

		<br /> <br />

		<button type="button" class="btn btn-warning" data-toggle="modal" data-target="#delete_modal" style="float: right;">회원탈퇴</button>

		<!-- 회원탈퇴 모달 -->
		<div class="modal fade" id="delete_modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-body">
						<font style="size: 12px;"><b>회원정보가 전부 삭제됩니다. 정말로 탈퇴하시겠습니까?</b></font>
					</div>
					<div class="modal-footer">
						<input type="hidden" id="password_validation" value="0">
						<button type="button" class="btn btn-danger" id="user_delete">탈퇴</button>
						<button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
					</div>
				</div>
			</div>
		</div>

	</form>
</div>

<br />
<br />

<%-- static 폴더의 경로까지도 기본세팅이 되어있기 때문에 /js/user.js로 해도 문제없다 --%>
<script src="/js/userUpdateForm.js"></script>
<%-- footer를 따로 만들어 관리 --%>
<%-- 위치가 views인 index.jsp에서와는 달리 joinForm.jsp의 위치는 views/user이기 때문에 앞에 ../를 붙여줘야 한다 --%>
<%@ include file="../layout/footer.jsp"%>