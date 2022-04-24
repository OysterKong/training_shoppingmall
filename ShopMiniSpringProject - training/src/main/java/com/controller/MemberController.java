package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dto.MemberDTO;
import com.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	MemberService service;
	
	
	@RequestMapping(value="/memberAdd")
	public String memberAdd(MemberDTO d, Model m) {
		System.out.println(d);
		service.memberAdd(d);
		m.addAttribute("success", "회원가입성공");
		return "main";
	}

}
