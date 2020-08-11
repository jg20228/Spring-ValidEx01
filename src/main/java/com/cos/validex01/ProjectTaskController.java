package com.cos.validex01;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/board")
public class ProjectTaskController {
	
	@Autowired 
	private ProjectTaskRepository projectTaskRepository;
	
	@PostMapping({"","/"})
	public ResponseEntity<?> save(@Valid @RequestBody ProjectTask requestProjectTask,BindingResult bindingResult){
		//값넣고 id값 바로 찾을 수 있음
		
		ProjectTask entitiyProjectTask = 
				projectTaskRepository.save(requestProjectTask);
		
		//이 밑에는 함수로 만들어두면 되는데 일단 적어둠
		RespDto<?> respDto = RespDto.builder()
				.statusCode(StatusCode.OK)
				.msg("요청에 성공하였습니다.")
				.data(entitiyProjectTask)
				.build();
		//return 할때 ResponseEntity<?>를 쓰면 편함.
		//?에 내가 return하고 싶은것을 넣는다.
		//ex) SELECT -> return할 object type
		//save했을때 많이 쓰이는 숫자값(1: 정상, 2:아이디 중복, 3:... , 4:...)
		
		//http 상태코드 참고
		return new ResponseEntity<RespDto>(respDto, HttpStatus.CREATED);
		//예전에 만들었던 CommonResponse를 만들면 status(상태값)을 하나 더 넣을수 있어서 추천
	}
}
