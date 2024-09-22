package com.example.demo.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.mvc.support.*;

import com.example.demo.dto.*;
import com.example.demo.service.*;

import jakarta.servlet.http.*;

@Controller
public class MemberController {
	@Autowired
	private MemberService service;
	
	@GetMapping("/member/username/check")
	public ResponseEntity<String> 아이디사용가능확인(@RequestParam String username) {
		boolean result = service.아이디사용가능확인(username);
		if(result) {
			return ResponseEntity.status(HttpStatus.OK).body("사용가능합니다");
		} else {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("사용중입니다");
		}
	}
	
	@GetMapping("/member/join")
	public ModelAndView join() {
		return new ModelAndView("member/join");
	}
	
	@PostMapping("/member/join")
	public ModelAndView 멤버가입(@ModelAttribute MemberCreateDto dto, RedirectAttributes ra) {
		boolean result = service.멤버가입(dto);
		if(result==false)
			ra.addFlashAttribute("message", "회원가입에 실패했습니다 ");
		return new ModelAndView("redirect:/");
	}
	
	@GetMapping("/member/find-id")
	public ModelAndView 아이디찾기() {
		return new ModelAndView("member/find-id");
	}
	
	@PostMapping("/member/find-id")
	public ModelAndView 아이디찾기(@RequestParam String email, RedirectAttributes ra) {
		String username = service.아이디찾기(email);
		String message = username==null? "사용자를 찾을 수 없습니다":"아이디  : " + username;
		ra.addFlashAttribute("message", message);
		if(username!=null) {
			return new ModelAndView("redirect:/member/login");
		} else {
			return new ModelAndView("redirect:/member/find-id");
		}
	}
	
	@GetMapping("/member/login")
	public ModelAndView 로그인() {
		return new ModelAndView("member/login");
	}
	
	@PostMapping("/member/login")
	public ModelAndView 로그인(MemberLoginDto dto, HttpSession session, RedirectAttributes ra) {
		boolean result = service.login(dto);
		if(result) {
			session.setAttribute("username", dto.getUsername());
			return new ModelAndView("redirect:/");
		} else {
			ra.addFlashAttribute("message", "아이디나 비밀번호를 확인하세요");
			return new ModelAndView("redirect:/member/login");
		}
	}
	
	@PostMapping("/member/logout")
	public ModelAndView logout(HttpSession session) {
		session.invalidate();
		return new ModelAndView("redirect:/");
	}
	
	@PostMapping("/member/withdraw")
	public ModelAndView withdraw(HttpSession session, RedirectAttributes ra) {
		if(session.getAttribute("username")==null) {
			ra.addFlashAttribute("message", "잘못된 작업입니다");
			return new ModelAndView("redirect:/");
		}
		String username = (String)session.getAttribute("username");
		service.withdraw(username);
		session.invalidate();
		return new ModelAndView("redirect:/");
	}
}
