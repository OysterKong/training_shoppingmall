package com.controller.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.service.MemberService;

/**
 * Servlet implementation class MemberIdCheckServlet
 */
@WebServlet("/MemberIdCheckServlet")
public class MemberIdCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberIdCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userid = request.getParameter("userid"); //memberForm.jsp에서 ajax 로 넘긴 아이디 값이 넘어오는지 확인하는 절차 (파싱 데이터확인 )
		//System.out.println(userid); //아이디 값이 넘어오는지 확인하는 절차
		MemberService service = new MemberService();
		int count = service.idCheck(userid);//중복 count
		
		System.out.println(count);
		
		String mesg = "아이디 사용 가능";
		if(count==1) {
			mesg = "아이디 중복";
		}
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(mesg);  //응답 메세지 전송 ( 아이디 사용가능 or 아이디 중복 이라는 메시지를 전달 )
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
