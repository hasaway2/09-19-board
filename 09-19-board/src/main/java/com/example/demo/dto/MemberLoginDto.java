package com.example.demo.dto;

import com.example.demo.entity.*;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
public class MemberLoginDto {
	@NotEmpty
	private String username;
	@NotEmpty
	private String password;
	
	public Member toEntity() {
		return new Member(username, password, null);
	}
}
