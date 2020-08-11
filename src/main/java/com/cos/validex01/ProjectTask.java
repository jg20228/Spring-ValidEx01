package com.cos.validex01;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

//모델에는 setter가 있으면 안된다. getter만 가능
//최초의 생성자 or data를 넣을때, 사용자의 요청만 있을때
@Entity
@NoArgsConstructor
@Data
public class ProjectTask {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//Model은 실제로 DB에 넣는용도이다.
	//Model이 있으면 RequestLoginDto에 Size, NotBlank 거는것, ResponseDto가 만들어진다 
	
	//ConstraintViolationException
	@Size(max=10, message ="Summary cannot exceed the length")
	@NotBlank(message = "Summary cannot be blank")
	private String summary;
	@NotBlank(message = "AcceptanceCriteria cannot be blank")
	private String acceptanceCriteria; //설명?
	private String status;
	@Email(message = "Your email XXX")
	private String email;
}
