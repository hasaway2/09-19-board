package com.example.demo.dto;

import com.example.demo.entity.*;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@AllArgsConstructor
@ToString
public class MemberCreateDto {
	@NotEmpty
	private String username;
	@NotEmpty
	private String password;
	@NotEmpty
	private String email;
	
	public Member toEntity() {
		return new Member(username, password, email);
	}
}
