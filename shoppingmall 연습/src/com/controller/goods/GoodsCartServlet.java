package com.controller.goods;

import java.io.IOException;

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
 * Servlet implementation class GoodsCartServlet
 */
@WebServlet("/GoodsCartServlet")
public class GoodsCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public GoodsCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession();
			MemberDTO dto = (MemberDTO)session.getAttribute("login");
			String nextPage = null;
			if(dto!=null) {
				String gImage = request.getParameter("gImage");
				String gCode = request.getParameter("gCode");
				String gName = request.getParameter("gName");
				String gPrice = request.getParameter("gPrice");
				String gSize = request.getParameter("gSize");
				String gColor = request.getParameter("gColor");
				String gAmount = request.getParameter("gAmount");
				String userid = dto.getUserid(); //세션
				
				CartDTO xx = new CartDTO();
				xx.setgImage(gImage);
				xx.setgCode(gCode);
				xx.setgName(gName);
				xx.setgPrice(Integer.parseInt(gPrice));
				xx.setgSize(gSize);
				xx.setgColor(gColor);
				xx.setgAmount(Integer.parseInt(gAmount));
				xx.setUserid(userid);
				System.out.println(xx);
				
				CartService service = new CartService();
				int n = service.CartAdd(xx); // CartMapper.cartAdd
				
				nextPage = "GoodsRetrieveServlet?gCode="+gCode;
				session.setAttribute("mesg", gCode+" Cart저장성공");
			} else {
				nextPage = "LoginUIServlet";
				session.setAttribute("mesg", "로그인이 필요한 작업입니다.");
			}
			response.sendRedirect(nextPage);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
