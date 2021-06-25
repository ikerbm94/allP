package com.bm.allp.controller.api;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bm.allp.config.auth.PrincipalDetail;
import com.bm.allp.dto.ResponseDto;
import com.bm.allp.model.Reply;
import com.bm.allp.service.ReplyService;

// 데이터만 리턴해줄 것이기 때문에 RestController이다
@RestController
public class ReplyApiController {
	
	@Autowired
	private ReplyService replyService;
	
	// ajax의 기본 데이터 타입은 body 데이터이기 때문에 @RequestBody 사용
	// @AuthenticationPrincipal을 통해 파라미터에서 Security에 내장되어있는 principal객체를 받아온다
	@PostMapping("/api/reply/save/{boardId}")
	public ResponseDto<Integer> replySave(@RequestBody Reply requestReply, @PathVariable int boardId, @AuthenticationPrincipal PrincipalDetail principal) {
		
		// System.out.println("ReplyApiController : 댓글등록 replySave() 호출");
		
		replyService.댓글등록(requestReply, boardId, principal.getUser());
		
		// HttpStatus.OK.value()는 숫자 200인데 성공적으로 실행되었다는 뜻이다
		// ajax에서 dataType을 json이라고 명시했기 때문에 아래의 리턴값의 형태는 json이다
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	@DeleteMapping("/api/reply/delete/{replyId}")
	public ResponseDto<Integer> replyDelete(@PathVariable int replyId) {
		
		// System.out.println("ReplyApiController : 댓글삭제 replyDelete() 호출");
		
		replyService.댓글삭제(replyId);
		
		// HttpStatus.OK.value()는 숫자 200인데 성공적으로 실행되었다는 뜻이다
		// ajax에서 dataType을 json이라고 명시했기 때문에 아래의 리턴값의 형태는 json이다
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	
}