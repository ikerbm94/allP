package com.bm.allp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseDto<T> {
	// 상태값을 숫자로 표시하기위해 int로 설정
	int status;
	T data;
}
