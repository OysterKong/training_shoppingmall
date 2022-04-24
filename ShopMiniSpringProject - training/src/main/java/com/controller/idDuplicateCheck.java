package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dto.MemberDTO;
import com.service.MemberService;

@Controller
public class idDuplicateCheck {
	
	@Autowired
	MemberService service;
	
	@RequestMapping(value="/idDuplicateCheck", produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String idDuplicateCheck(@RequestParam("id") String userid)  {
		MemberDTO dto = service.myPage(userid);
		System.out.println("���̵� �ߺ��˻�� - : " + dto);
		String mesg = "���̵��밡��";
		if (dto!=null) {
			mesg="���̵� �ߺ�";
		}
		return mesg;
	}

}
