package com.bm.allp.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.bm.allp.dto.ResponseDto;

@ControllerAdvice // 어디에서든 해당 Exception이 발생하면 이곳으로 오게한다
@RestController
public class GlobalExceptionHandler {
	
	// 예외가 발생하면 해당 값을 리턴한다
	// 리턴값은 ResponseDto의 status와 data값이다
	@ExceptionHandler(value=Exception.class)
	public ResponseDto<String> Exception(Exception e) {
		return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
	}

}