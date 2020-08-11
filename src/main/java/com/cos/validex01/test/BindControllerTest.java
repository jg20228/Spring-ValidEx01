package com.cos.validex01.test;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BindControllerTest {
	@GetMapping("/test/before")
	public void testBefore() {
		System.out.println("testBefore 실행됨");

	}
}
