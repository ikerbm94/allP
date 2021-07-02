let userUpdateFormIndex = {

	init: function() {

		// update_password를 찾아서 그것을 클릭했을때 save함수를 호출
		// "function(){}" 대신에 "()=>{}"를 사용한 이유는 this를 바인딩하기 위함이다
		$("#update_password").on("click", () => {
			this.update_password();
		});
		
		$("#update_email").on("click", () => {
			this.update_email();
		});
		
		// 패스워드 정규식 표현
		$("#password").blur(() => {
			this.password_check();
		});
		
		// 패스워드 확인 및 정규식 표현
		$("#passwordSync").blur(() => {
			this.password_check();
		});
		
		// 이메일 주소 도메인 선택
		$("#domain_select").on("click", () => {
			this.domain_select();
		});
		
		// 이메일 전송
		$("#email_validate").on("click", () => {
			this.email_validate();
		});
		
		// 인증번호 인증
		$("#code_validate").on("click", () => {
			this.code_validate();
		});
		
		// 회원탈퇴
		$("#user_delete").on("click", () => {
			this.user_delete();
		});
		
		
	},

	// 비밀번호 수정
	update_password: function() {

		let id = $("#id").val();
		let data = {
			password: $("#password").val()
		};
		let p = $("#password_validation").val();
		
		if (p != 1) {
			alert("비밀번호를 확인해주세요.");
			$("#password").focus();
		} else if (p == 1) {
			$.ajax({
				type: "PUT",
				url: "/api/user/update/"+id,
				data: JSON.stringify(data),							// let data를 json으로 변환
				contentType: "application/json; charset=utf-8",		// 기본적인 전송 데이터의 타입은 body 데이터이기 때문에 json 타입을 전송한다고 명시 해야한다
				dataType: "json"									// 응답을 받을 데이터의 타입의 기본 값이 String이다
																	// 그런데 생김새가 json인 String 데이터라면 dataType을 json으로 명시함으로써 이를 인지하고
																	// javascript 객체로 변환하여 응답 받을 수 있다
																	// 이 변환을 해주는 라이브러리를 Jackson이라고 한다
																	// 요청을 실행하여 정상이면 .done을 실행한다
			}).done(function(resp) {	
				alert("비밀번호 수정이 완료되었습니다.");
				location.href = "/logout";
			}).fail(function(error) {
				alert(JSON.stringify(error));
			});
		}

	},
	
	// 이메일 수정
	update_email: function() {

		let id = $("#id").val();
		let data = {
			email: $("#address").val()+"@"+$("#domain").val()
		};
		let e = $("#email_validation").val();
		
		if (e != 1) {
			alert("이메일 인증을 완료해주세요.");
			$("#address").focus();
			console.log($("#code").val());
		} else if (e == 1) {
			$.ajax({
				type: "PUT",
				url: "/api/user/update/"+id,
				data: JSON.stringify(data),							// let data를 json으로 변환
				contentType: "application/json; charset=utf-8",		// 기본적인 전송 데이터의 타입은 body 데이터이기 때문에 json 타입을 전송한다고 명시 해야한다
				dataType: "json"									// 응답을 받을 데이터의 타입의 기본 값이 String이다
																	// 그런데 생김새가 json인 String 데이터라면 dataType을 json으로 명시함으로써 이를 인지하고
																	// javascript 객체로 변환하여 응답 받을 수 있다
																	// 이 변환을 해주는 라이브러리를 Jackson이라고 한다
																	// 요청을 실행하여 정상이면 .done을 실행한다
			}).done(function(resp) {	
				alert("이메일 수정이 완료되었습니다.");
				location.href = "/logout";
			}).fail(function(error) {
				alert(JSON.stringify(error));
			});
		}

	},
	
	password_check: function() {
		
		let password = $("#password").val();
		let passwordSync = $("#passwordSync").val();
		
		let passwordRegExp = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*]).{8,25}$/; // 정규식 표현 (8~25자 내로 영문, 숫자, 특수문자(!, @, #, $, %, ^, &, *)가 한 개씩 이상)
		
		if (password == "" || passwordSync == ""){
			$("#password_check").text("비밀번호를 입력해주세요.");
			$("#password_check").css("color", "red");
			$("#password_validation").prop("value", 0);
		} else if (password != passwordSync) {
			$("#password_check").text("비밀번호가 일치하지 않습니다.");
			$("#password_check").css("color", "red");
			$("#password_validation").prop("value", 0);
		} else if (!passwordRegExp.test(password)) {
			$("#password_check").text("사용 불가능한 비밀번호입니다.");
			$("#password_check").css("color", "red");
			$("#password_validation").prop("value", 0);
		} else {
			$("#password_check").text("사용 가능한 비밀번호입니다.");
			$("#password_check").css("color", "blue");
			$("#password_validation").prop("value", 1);
		}
		
	},
	
	domain_select: function() { // 이메일 도메인 선택
		
		let domain_select = $("#domain_select").val();

		if (domain_select == "self") {
			$("#domain").prop("readonly", false);
			$("#domain").val("");
		} else {
			$("#domain").prop("readonly", true);
			$("#domain").val(domain_select);
		}
		
	},
	
	email_validate: function() { // 이메일 인증하기
		
		let address = $("#address").val();
		let domain = $("#domain").val();
		let email = address + "@" + domain ;
		
		let emailRegExp = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/; // 이메일 정규식
		
		if (address == "" || domain == "" || !emailRegExp.test(email)){
			$("#email_check").text("입력하신 이메일 주소를 확인해주세요.");
			$("#email_check").css("color", "red");
		} else {
			$("#user_code").prop("type","text");
			$("#code_validate").prop("type","button");
			
			$.ajax({
				type: "POST",
				url: "/auth/api/user/emailCheck/"+email,
				dataType:"text"
			}).done(function(resp) {
				$("#code").val(resp);
			}).fail(function(error) {
				alert(JSON.stringify(error));
			});
			
			alert("인증번호가 전송되었습니다.");
			
			$("#email_check").text("");
		}
		 
	},
	
	code_validate: function() {
		
		let user_code = $("#user_code").val();
		let code = $("#code").val();
		
		if (user_code != code) {
			$("#code_check").text("입력하신 인증번호가 일치하지 않습니다. 다시 입력하시거나 처음부터 다시 진행해주세요.");
			$("#code_check").css("color", "red");
		} else {
			$("#code_check").text("");
			alert("이메일 인증이 완료되었습니다.")
			$("#email_validation").prop("value", 1);
		}
		
	},
	
	user_delete: function() {
		
		let id = $("#id").val();
		
		$.ajax({
			type: "DELETE",
			url: "/api/user/delete/"+id,
			dataType: "json"
		}).done(function(resp){
			alert("회원 탈퇴가 완료되었습니다.");
			location.href="/logout";
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
		
	}

}

// 위의 let index는 그냥 객체이기 때문에 아래의 명령어로 따로 실행시켜줘야 한다
userUpdateFormIndex.init();