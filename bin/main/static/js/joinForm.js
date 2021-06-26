let joinFormIndex = {

	init: function() {

		// btn-save를 찾아서 그것을 클릭했을때 save함수를 호출
		// "function(){}" 대신에 "()=>{}"를 사용한 이유는 this를 바인딩하기 위함이다
		$("#btn-save").on("click", () => {
			this.save();
		});

		$("#username").blur(() => {
			this.username_check();
		});
		
		$("#password").blur(() => {
			this.password_check();
		});
		
		$("#passwordSync").blur(() => {
			this.passwordSync_check();
		});

	},

	// save 함수 (회원가입)
	save: function() {

		// alert('회원가입 save 함수 호출');

		// <input> 태그의 id값을 통해 해당 값을 받아옴
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		}

		// console.log(data);

		// ajax를 통해서 3개의 데이터를 json으로 변경하여 insert 요청
		$.ajax({
			type: "POST",										// 전송방법은 POST

			url: "/auth/joinProc",								// 요청을 보낼 주소

			data: JSON.stringify(data),							// let data를 json으로 변환

			contentType: "application/json; charset=utf-8",		// 기본적인 전송 데이터의 타입은 body 데이터이기 때문에 json 타입을 전송한다고 명시 해야한다

			dataType: "json"									// 응답을 받을 데이터의 타입의 기본 값이 String이다
																// 그런데 생김새가 json인 String 데이터라면 dataType을 json으로 명시함으로써 이를 인지하고
																// javascript 객체로 변환하여 응답 받을 수 있다
																// 이 변환을 해주는 라이브러리를 Jackson이라고 한다
																// 요청을 실행하여 정상이면 .done을 실행한다
		// resp는 요청하여 받은 리턴값 (UserApiController의 userSave() 메서드의 리턴값)														
		}).done(function(resp) {	
			alert("회원가입이 완료되었습니다");
			// console.log(resp);
			location.href = "/";
			// 요청을 실행하여 비정상이면 .fail을 실행한다
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
		
	},
	
	username_check: function() { // 아이디 유효성 검사
		
		let username = $("#username").val();
		
		let usernameRegExp = /^[a-zA-z0-9]{5,20}$/; // 정규식 표현 (5~20자 내로 영문 혹은 숫자만)

		if (username == "") {
			$("#username_check").text("아이디를 입력해주세요");
			$("#username_check").css("color", "red");
		} else if (!usernameRegExp.test(username)) { // 정규식 표현 테스트
			$("#username_check").text("사용 불가능한 아이디입니다");
			$("#username_check").css("color", "red");
		} else {
			$.ajax({
				type: "GET",
				url: "/auth/api/user/usernameCheck/"+username,
			}).done(function(resp) {
				if (resp == 1) {
					$("#username_check").text("이미 사용중인 아이디입니다"); // 중복
					$("#username_check").css("color", "red");
				} else if (resp == 0) {
					$("#username_check").text("사용 가능한 아이디입니다");
					$("#username_check").css("color", "blue");
				}
			}).fail(function(error) {
				alert(JSON.stringify(error));
			});
		}
		
	},
	
	password_check: function() { // 비밀번호 유효성 검사
	
		let password = document.getElementById("password").value;
		let passwordSync = document.getElementById("passwordSync").value;
		
		let passwordRegExp = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*]).{8,25}$/; // 정규식 표현 (8~25자 내로 영문, 숫자, 특수문자(!, @, #, $, %, ^, &, *)가 한 개씩 이상)
		
		if (password == "" || passwordSync == "") {
			$("#password_check").text("비밀번호를 입력해주세요");
			$("#password_check").css("color", "red");
		} else if (password != passwordSync) {
			$("#password_check").text("비밀번호가 일치하지 않습니다");
			$("#password_check").css("color", "red");
		} else if (!passwordRegExp.test(passwordSync)) {
			$("#password_check").text("사용 불가능한 비밀번호입니다");
			$("#password_check").css("color", "red");
		} else {
			$("#password_check").text("사용 가능한 비밀번호입니다");
			$("#password_check").css("color", "blue");
		}
		
	},
	
	passwordSync_check: function() { // 비밀번호 확인 유효성 검사
		
		let password = document.getElementById("password").value;
		let passwordSync = document.getElementById("passwordSync").value;
		
		let passwordRegExp = /^(?=.*[a-zA-Z])(?=.*[!@#$%^])(?=.*[0-9]).{8,25}$/; // 정규식 표현 (8~25자 내로 영문, 숫자, 특수문자(!, @, #, $, %, ^, &, *)가 한 개씩 이상)
		
		if (password == "" || passwordSync == "") {
			$("#password_check").text("비밀번호를 입력해주세요");
			$("#password_check").css("color", "red");
		} else if (password != passwordSync) {
			$("#password_check").text("비밀번호가 일치하지 않습니다");
			$("#password_check").css("color", "red");
		} else if (!passwordRegExp.test(passwordSync)) {
			$("#password_check").text("사용 불가능한 비밀번호입니다");
			$("#password_check").css("color", "red");
		} else {
			$("#password_check").text("사용 가능한 비밀번호입니다");
			$("#password_check").css("color", "blue");
		}

	}

}

// 위의 let index는 그냥 객체이기 때문에 아래의 명령어로 따로 실행시켜줘야 한다
joinFormIndex.init();