package com.example.demo.dao;

import org.apache.ibatis.annotations.*;

import com.example.demo.entity.*;

@Mapper
public interface MemberDao {
	@Select("select count(*) from member where username=#{username}")
	public int countByUsername(String username);
	
	@Insert("insert into member values(#{username}, #{password}, #{email})")
	public int save(Member member);
	
	@Select("select username from member where email=#{email}")
	public String findUsernameByEmail(String email);
	
	@Select("select count(*) from member where username=#{username} and password=#{password}")
	public int countByUsernameAndPassword(Member member);

	@Delete("delete from member where username=#{username}")
	public boolean deleteByUsername(String username);
}