package com.controller.main;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.GoodsDTO;
import com.service.GoodsService;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public MainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		GoodsService service = new GoodsService();
		List<GoodsDTO> list = service.goodsList("top"); //goodscategory 가 top 인 상품들
		
		System.out.println(list); // db속 gcategory 가 top 에 해당하는 상품들 list 가 찍히는지 main 단에서 실행해보기
		
		// 가져온 데이터를 request 에다가 "goodsList" 란 key로 list 를 forward 형태로 넘김 -> main.jsp 로
		// 가져온 항목은 main단에서 한번 출력해주고 가지고 있을 필요가 없으므로 request 에다가 하는 것
		request.setAttribute("goodsList", list);
		RequestDispatcher dis = request.getRequestDispatcher("main.jsp");
		dis.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
