package com.controller;

import javax.servlet.http.HttpSession;

import org.omg.CORBA.Request;
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
	
	@RequestMapping(value="loginCheck/myPage")
	public String myPage(HttpSession session) {
		MemberDTO dto = (MemberDTO) session.getAttribute("login");
		String userid = dto.getUserid();
		dto = service.myPage(userid);
		System.out.println("유저정보 : "+ dto);
		session.setAttribute("login", dto);
		return "redirect:../myPage";
	}
	
	
	@RequestMapping(value="/memberAdd")
	public String memberAdd(MemberDTO d, Model m) {
		System.out.println(d);
		service.memberAdd(d);
		m.addAttribute("success", "회원가입성공");
		return "main";
	}

}
