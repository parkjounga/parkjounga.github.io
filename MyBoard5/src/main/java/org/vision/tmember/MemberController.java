package org.vision.tmember;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class MemberController
 */
@WebServlet("/member/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberDAO memberDAO;
	public void init() throws ServletException {
		memberDAO = new MemberDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		String nextPage = null;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String action = request.getPathInfo();
		System.out.println("action:" + action);
		
		String id = null;
		List<MemberVO> membersList=null;
		String pwd=null;
	    String name= null;
        String email= null;
	    MemberVO memberVO = null;

		switch(action) {
		case "":
		case "/listMembers.do":// 등록된 모든 회원 정보 보기 
			membersList = memberDAO.listMembers();
			request.setAttribute("membersList", membersList);
			nextPage = "/tmember/listMembers.jsp";
			break; 
		case "/addMember.do": // 새로운 회원 등록하기 
			id = request.getParameter("id");
			pwd = request.getParameter("pwd");
			name = request.getParameter("name");
			email = request.getParameter("email");
			memberVO = new MemberVO(id, pwd, name, email);
			memberDAO.addMember(memberVO);
			request.setAttribute("msg", "addMember");
			nextPage = "/member/listMembers.do";
			break;
		case "/memberForm.do": // 회원등록 폼 열기 
			nextPage = "/tmember/memberForm.jsp";
			break; 
		case "/modMemberForm.do": // 회원 정보 수정 폼 열기 
			id=request.getParameter("id");
		    MemberVO memInfo = memberDAO.findMember(id);
		    request.setAttribute("memInfo", memInfo);
		    nextPage="/tmember/modMemberForm.jsp";
		    break;
		case "/modMember.do": // 수정폼에서 수정한 정보 디비로 확정짓기 
			 id=request.getParameter("id");
		     pwd=request.getParameter("pwd");
		     name= request.getParameter("name");
	         email= request.getParameter("email");
		     memberVO = new MemberVO(id, pwd, name, email);
		     memberDAO.modMember(memberVO);
		     request.setAttribute("msg", "modified");
		     nextPage="/member/listMembers.do";
		     break;
	
		
		
		case "/delMember.do": // 회원 삭제 하기 
			 id=request.getParameter("id");
		     memberDAO.delMember(id);
		     request.setAttribute("msg", "deleted");
		     nextPage="/member/listMembers.do";
		     break;
		
		default:  // 유알엘 패턴이 위에 열거한것과 틀리거나 없을 때 
			membersList = memberDAO.listMembers();
			request.setAttribute("membersList", membersList);
			nextPage = "/tmember/listMembers.jsp";
		}
		
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}

}