let boardIndex = {
	
	init: function(){
		
		// btn-board-save를 찾아서 그것을 클릭했을때 save 함수를 호출
		// "function(){}" 대신에 "()=>{}"를 사용한 이유는 this를 바인딩하기 위함이다
		$("#btn-board-save").on("click", ()=>{
			this.save();
		});		
		
		// btn-board-update를 찾아서 그것을 클릭했을때 delete 함수를 호출
		// "function(){}" 대신에 "()=>{}"를 사용한 이유는 this를 바인딩하기 위함이다
		$("#btn-board-update").on("click", ()=>{
			this.update();
		});		

		// btn-board-delete를 찾아서 그것을 클릭했을때 delete 함수를 호출
		// "function(){}" 대신에 "()=>{}"를 사용한 이유는 this를 바인딩하기 위함이다
		$("#btn-board-delete").on("click", ()=>{
			this.delete();
		});
		
	},
	
	// save 함수 (글 작성)
	save: function(){
		
		// alert('글 작성 save 함수 호출');
		
		// <input>와 <textarea> 태그의 id값을 통해 해당 값을 받아옴
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		}
		
		// console.log(data);
		
		// ajax를 통해서 3개의 데이터를 json으로 변경하여 insert 요청
		$.ajax({
			type: "POST",										// 전송방법은 POST
			
			url: "/api/board/save",								// 요청을 보낼 주소
			
			data: JSON.stringify(data),							// let data를 json으로 변환
			
			contentType: "application/json; charset=utf-8",		// 기본적인 전송 데이터의 타입은 body 데이터이기 때문에 json 타입을 전송한다고 명시 해야한다
			
			dataType: "json"									// 응답을 받을 데이터의 타입의 기본 값이 String이다
																// 그런데 생김새가 json인 String 데이터라면 dataType을 json으로 명시함으로써 이를 인지하고
																// json 형태의 javascript 객체로 변환하여 응답 받을 수 있다
																// 이 변환을 해주는 라이브러리를 Jackson이라고 한다
																
		// 요청을 실행하여 정상이면 .done을 실행한다
		}).done(function(resp){	// resp는 요청하여 받은 리턴값 (BoardApiController의 boardSave() 메서드의 리턴값)
			alert("글 작성이 완료되었습니다");
			// console.log(resp);
			location.href="/";
		// 요청을 실행하여 비정상이면 .fail을 실행한다
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
	},
	
	// update 함수 (글 수정)
	update: function(){
		
		// alert('글 수정 update 함수 호출');
		
		// hidden 값
		let boardId = $("#boardId").val();
		
		// <input>와 <textarea> 태그의 id값을 통해 해당 값을 받아옴
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		}
		
		
		$.ajax({
			type: "PUT",										// 전송방법은 DELETE
			
			url: "/api/board/update/"+boardId,						// 요청을 보낼 주소 + id값 전달
			
			data: JSON.stringify(data),							// let data를 json으로 변환
				
			contentType: "application/json; charset=utf-8",		// 기본적인 전송 데이터의 타입은 body 데이터이기 때문에 json 타입을 전송한다고 명시 해야한다
			
			dataType: "json"									// 응답을 받을 데이터의 타입의 기본 값이 String이다
																// 그런데 생김새가 json인 String 데이터라면 dataType을 json으로 명시함으로써 이를 인지하고
																// json 형태의 javascript 객체로 변환하여 응답 받을 수 있다
																// 이 변환을 해주는 라이브러리를 Jackson이라고 한다
																
		// 요청을 실행하여 정상이면 .done을 실행한다
		}).done(function(resp){	// resp는 요청하여 받은 리턴값 (BoardApiController의 update 메서드의 리턴값)
			alert("글 수정이 완료되었습니다");
			// console.log(resp);
			location.href="/board/detail/"+boardId;
		// 요청을 실행하여 비정상이면 .fail을 실행한다
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
	},
	
	// deleteById 함수 (글 삭제)
	delete: function(){
		
		// alert('글 삭제 deleteById 함수 호출');
		
		let boardId = $("#boardId").val();
		
		$.ajax({
			
			type: "DELETE",										// 전송방법은 DELETE
			
			url: "/api/board/delete/"+boardId,					// 요청을 보낼 주소 + boar의 id값 전달
			
			dataType: "json"									// 응답을 받을 데이터의 타입의 기본 값이 String이다
																// 그런데 생김새가 json인 String 데이터라면 dataType을 json으로 명시함으로써 이를 인지하고
																// json 형태의 javascript 객체로 변환하여 응답 받을 수 있다
																// 이 변환을 해주는 라이브러리를 Jackson이라고 한다
																
		// 요청을 실행하여 정상이면 .done을 실행한다
		}).done(function(resp){	// resp는 요청하여 받은 리턴값 (BoardApiController의 delete 메서드의 리턴값)
			alert("글 삭제가 완료되었습니다");
			// console.log(resp);
			location.href="/";
		// 요청을 실행하여 비정상이면 .fail을 실행한다
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
	}
	
}

// 위의 let index는 그냥 객체이기 때문에 아래의 명령어로 따로 실행시켜줘야 한다
boardIndex.init();