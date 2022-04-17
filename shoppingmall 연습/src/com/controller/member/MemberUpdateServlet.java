package com.controller.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.MemberDTO;
import com.service.MemberService;

/**
 * Servlet implementation class MemberUpdateServlet
 */
@WebServlet("/MemberUpdateServlet")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberDTO dto = (MemberDTO)session.getAttribute("login");
		String nextPage = null;
		if (dto!=null) {
			request.setCharacterEncoding("utf-8");
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
			
			MemberDTO dto2 = new MemberDTO(userid,passwd,username,post,addr1,addr2,phone1,phone2,phone3,email1,email2);
			
			//System.out.println(dto2);
			MemberService service = new MemberService();
			int num = service.memberUpdate(dto2); //insert 문은 결과값이 정수, 짝수 개념

			System.out.println(num); // 최종적으로 데이터가 들어가고 나면 1이 찍힐것
			
			  if(num==1) {
			  dto2 = service.mypage(userid); //이름 null 값 나오지 않게 이름 가져오기 ( DB를 한번 더 갔다오는...)
			  dto2.setUsername(dto.getUsername()); //이름 null 값 나오지 않게 이름 가져오기
			  session.setAttribute("login", dto2); //세션데이터 덮어쓰기 ( 세션에 최신회원정보 저장 )
			  session.setAttribute("memberAdd", "회원정보가 수정되었습니다.");
			  }
			  //nextPage = "main";
			  nextPage = "MyPageServlet";
			} else { //회원이 아닌경우
			  nextPage = "LoginUIServlet";
			  session.setAttribute("mesg", "로그인이 필요한 작업입니다.");
			}
			  RequestDispatcher dis = request.getRequestDispatcher(nextPage);
			  dis.forward(request, response);
		}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
