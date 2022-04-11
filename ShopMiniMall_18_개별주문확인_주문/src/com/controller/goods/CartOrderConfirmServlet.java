package com.controller.goods;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.CartDTO;
import com.dto.GoodsDTO;
import com.dto.MemberDTO;
import com.service.CartService;
import com.service.GoodsService;
import com.service.MemberService;

/**
 * Servlet implementation class GoodsListServlet
 */
@WebServlet("/CartOrderConfirmServlet")
public class CartOrderConfirmServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//주문확인 confirm=>주문완료 done
		 HttpSession session = request.getSession();
	      MemberDTO dto = (MemberDTO)session.getAttribute("login");
		 String nextPage = null;
	      if(dto!=null) {

	    	  String num = request.getParameter("num");//CartDTO
	    	  System.out.println("CartOrderConfirmServlet num====="+num);
	    	  CartService cService = new CartService();
	    	  CartDTO cDTO = cService.cartbyNum(num);//Cart 정보조회
	    	  
	    	  
	    	  String userid =dto.getUserid(); //MemberDTO
	    	  MemberService mService= new MemberService();
	    	  MemberDTO mDTO = mService.mypage(userid);//사용자 정보조회
				/*
				 * System.out.println("cdto"+ cDTO); System.out.println("mdto"+ mDTO);
				 */
	          request.setAttribute("cDTO", cDTO);
	          request.setAttribute("mDTO", mDTO);
	       
			nextPage = "orderConfirm.jsp";

	      }else {
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
