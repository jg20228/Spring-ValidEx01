package com.cos.validex01;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RespDto<T> {
	
	private int statusCode; //인터페이스로 정의를 해두는게 좋다. 1, 2, 3, 4, 5, 6, 7, 8, 9, 10
	private String msg;
	
	private T data; //에러도 담고 데이터도 담고
	
}
