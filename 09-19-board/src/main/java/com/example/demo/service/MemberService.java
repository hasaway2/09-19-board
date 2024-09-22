package com.example.demo.service;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.example.demo.dao.*;
import com.example.demo.dto.*;

@Service
public class MemberService {
	@Autowired
	private MemberDao memberDao;
	
	public boolean 아이디사용가능확인(String username) {
		return memberDao.countByUsername(username)==0;
	}
	
	public String 아이디찾기(String email) {
		return memberDao.findUsernameByEmail(email);
	}
	
	public boolean 멤버가입(MemberCreateDto dto) {
		return memberDao.save(dto.toEntity())==1;
	}

	public boolean login(MemberLoginDto dto) {
		return memberDao.countByUsernameAndPassword(dto.toEntity())==1;
	}

	public boolean withdraw(String username) {
		return memberDao.deleteByUsername(username);
	}
}
