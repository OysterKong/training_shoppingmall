package com.controller.goods;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.CartDTO;
import com.dto.MemberDTO;
import com.service.CartService;

/**
 * Servlet implementation class CartListServlet
 */
@WebServlet("/CartListServlet")
public class CartListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public CartListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession();
	      MemberDTO dto = (MemberDTO)session.getAttribute("login");
		 String nextPage = null;
	      if(dto!=null) {//회원인경우 
	    	String userid = dto.getUserid();
	    	CartService service = new CartService();
	    	List<CartDTO> list = service.cartList(userid);
	    	System.out.println(list);
	    	
	  		request.setAttribute("cartList", list);  //request 에 담아
	  		nextPage = "cartList.jsp";
	      }else {
			  nextPage = "LoginUIServlet";
			  session.setAttribute("mesg", "로그인이 필요한 작업입니다.");
		  }
	     
	    RequestDispatcher dis = request.getRequestDispatcher(nextPage);
	    dis.forward(request, response);  //request 에 담기 때문에 forward로전달해야한다.
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
