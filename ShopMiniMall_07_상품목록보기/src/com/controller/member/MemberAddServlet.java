package com.controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		
		//40번 줄은 값이 제대로 넘어오는지 찍어보기-확인용
		System.out.println(userid+passwd+username+post+addr1+addr2+phone1+phone2+phone3+email1+email2);
		
		MemberDTO dto = 
				new MemberDTO(userid, passwd, username, post, addr1, addr2, phone1, phone2, phone3, email1, email2);
		
		MemberService service = new MemberService();
		int n = service.memberAdd(dto);
		
		HttpSession session = request.getSession();
		session.setAttribute("memberAdd", "회원가입성공"); //회원가입버튼을 누르면 alert 창이 나와 회원가입이 성공했다는 메세지 출력
		session.setMaxInactiveInterval(60*30); //30분 , request 에 하는게 좋으나 그냥 session에 담아봄
		response.sendRedirect("main"); // 회원가입성공 메세지를 memberAdd 키에 담아 main.jsp로 전송
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
