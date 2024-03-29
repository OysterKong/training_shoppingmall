package com.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dto.MemberDTO;
import com.service.MemberService;

@Controller
public class LoginCotroller {
	
	@Autowired
	MemberService service;
	
	@RequestMapping(value="/loginCheck/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:../";
	}
	
	@RequestMapping(value="/login")
	public String login(@RequestParam Map<String, String> map, HttpSession session, Model m){
	System.out.println("map : " + map);	
	MemberDTO dto = service.login(map);
	if(dto!=null) {
		session.setAttribute("login", dto);
		return "main";
	} else {
		m.addAttribute("mesg", "아이디 또는 비밀번호가 없습니다.");
	return "loginForm";
	}

	}
}
