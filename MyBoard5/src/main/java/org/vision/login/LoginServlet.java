package org.vision.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.vision.tmember.MemberDAO;
import org.vision.tmember.MemberVO;
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		doHandle(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		doHandle(request, response);
	}
	private void doHandle(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String user_id = request.getParameter("user_id");
		String user_pwd = request.getParameter("user_pwd");
		MemberVO memberVO = new MemberVO();
		memberVO.setId(user_id);
		memberVO.setPwd(user_pwd);
		MemberDAO dao = new MemberDAO();
		boolean result = dao.isExisted(memberVO);
		
		HttpSession session =request.getSession();
		
		if (session.getAttribute("login.id") != null ) {
			session.invalidate();
			response.sendRedirect("Main.jsp");
		}
		
		if (result) {
		
			session.setAttribute("isLogon", true);
			session.setAttribute("login.id", user_id);
			session.setAttribute("login.pwd", user_pwd);
			MemberVO vo = dao.findMember(user_id);
			session.setAttribute("memInfo",vo);
			
			out.print("<html><body>");
			out.print("안녕하세요 " + user_id + "님!!!<br>");
			out.print("<a href='Main.jsp'>메인가기</a>");
			out.print("</body></html>");
		} else {
			out.print("<html><body>회원 아이디가 틀립니다.");
			out.print("<a href='login/formLogin.jsp'> 다시 로그인하기</a>");
			out.print("</body></html>");
		}
	}
}