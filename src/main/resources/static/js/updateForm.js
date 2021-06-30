let updateFormIndex = {

	init: function() {

		// btn-save를 찾아서 그것을 클릭했을때 save함수를 호출
		// "function(){}" 대신에 "()=>{}"를 사용한 이유는 this를 바인딩하기 위함이다
		$("#btn-update").on("click", () => {
			this.update();
		});
		
	},

	// update 함수 (회원수정)
	update: function() {

		// alert('회원수정 update 함수 호출');

		// hidden 값
		let id = $("#id").val();

		// <input> 태그의 id값을 통해 해당 값을 받아옴
		let data = {
			password: $("#password").val(),
			email: $("#email").val()
		}

		// console.log(data);

		// ajax를 통해서 3개의 데이터를 json으로 변경하여 insert 요청
		$.ajax({
			type: "PUT",										// 전송방법은 PUT

			url: "/api/user/update/" + id,						// 요청을 보낼 주소

			data: JSON.stringify(data),							// let data를 json으로 변환

			contentType: "application/json; charset=utf-8",		// 기본적인 전송 데이터의 타입은 body 데이터이기 때문에 json 타입을 전송한다고 명시 해야한다

			dataType: "json"									// 응답을 받을 데이터의 타입의 기본 값이 String이다
																// 그런데 생김새가 json인 String 데이터라면 dataType을 json으로 명시함으로써 이를 인지하고
																// javascript 객체로 변환하여 응답 받을 수 있다
																// 이 변환을 해주는 라이브러리를 Jackson이라고 한다
																// 요청을 실행하여 정상이면 .done을 실행한다
		// resp는 요청하여 받은 리턴값 (UserApiController의 userUpdate 메서드의 리턴값)														
		}).done(function(resp) {	
			alert("회원수정이 완료되었습니다");
			// console.log(resp);
			location.href = "/logout";
			// 요청을 실행하여 비정상이면 .fail을 실행한다
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	}

}

// 위의 let index는 그냥 객체이기 때문에 아래의 명령어로 따로 실행시켜줘야 한다
updateFormIndex.init();