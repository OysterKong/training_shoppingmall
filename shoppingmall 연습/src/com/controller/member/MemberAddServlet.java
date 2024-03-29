package com.controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.config.MySqlSessionFactory;
import com.dto.MemberDTO;
import com.service.MemberService;

/**
 * Servlet implementation class MemberAddServlet
 */
@WebServlet("/MemberAddServlet")
public class MemberAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberAddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		String passwd = request.getParameter("passwd");
		String username = request.getParameter("username");
		String post = request.getParameter("post");
		String addr1 = request.getParameter("addr1");
		String addr2 = request.getParameter("addr2");
		String phone1 = request.getParameter("phone1");
		String phone2 = request.getParameter("phone2");
		String phone3 = request.getParameter("phone3");
		String email1 = request.getParameter("email1");
		String email2 = request.getParameter("email2");
		//System.out.println(userid+passwd+username+post+addr1+addr2+phone1+phone2+phone3+email1+email2);
		
		MemberDTO dto = new MemberDTO(userid,passwd,username,post,addr1,addr2,phone1,phone2,phone3,email1,email2);
		
		MemberService service = new MemberService();
		int n = service.memberAdd(dto); //insert 문은 결과값이 정수, 짝수 개념

		//System.out.println(n); // 최종적으로 데이터가 들어가고 나면 1이 찍힐것
		
		HttpSession session = request.getSession();
		session.setAttribute("memberAdd", "회원가입성공, 축하해요");
		session.setMaxInactiveInterval(60*30);
		
		response.sendRedirect("main");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
