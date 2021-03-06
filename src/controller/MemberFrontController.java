package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.member.IdFindAction;
import action.member.MemberDeleteAction;
import action.member.MemberJoinAction;
import action.member.MemberListAction;
import action.member.MemberLoginAction;
import action.member.MemberModifyAction;
import action.member.MemberModifyFormAction;
import action.member.MemberViewAction;
import vo.ActionForward;

/**
 * Servlet implementation class MemberFrontController
 */
@WebServlet("*.mem")
public class MemberFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberFrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command= RequestURI.substring(contextPath.length());
		ActionForward forward = null;
		Action action = null;
		System.out.println(command);
		
        //요청처리 부분 
		if(command.equals("/memberLogin.mem")) {
			forward = new ActionForward();
			request.setAttribute("pagefile", "member/loginForm.jsp");
			forward.setPath("template.jsp");
		}
		else if(command.equals("/memberJoin.mem")) {
			forward = new ActionForward();
			request.setAttribute("pagefile", "member/joinForm.jsp");
			forward.setPath("template.jsp");
		}
		else if(command.equals("/memberLoginAction.mem")) {
			action = new MemberLoginAction();
			try {
				forward= action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/memberJoinAction.mem")) {
			action = new MemberJoinAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/memberListAction.mem")) {
			action = new MemberListAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/memberViewAction.mem")) {
			action = new MemberViewAction();
			try {
				forward= action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/memberDeleteAction.mem")) {
			action = new MemberDeleteAction();
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/memberLogout.mem")) {
			HttpSession session = request.getSession();
			session.removeAttribute("id");
			response.setContentType("text/html;charset=utf-8");
			forward = new ActionForward();
			forward.setPath("goodsList.shop");
		}
		else if(command.equals("/memberModifyFormAction.mem")) {
			action = new MemberModifyFormAction();
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/memberModifyAction.mem")) {
			action = new MemberModifyAction();
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/idFind.mem")) {
				forward = new ActionForward();
				request.setAttribute("pagefile", "member/idFind.jsp");
				forward.setPath("template.jsp");
			}
		else if(command.equals("/idFindAction.mem")) {
			action= new IdFindAction();
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	
		//3.포워딩 
		if(forward!=null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			}else {
				RequestDispatcher dispathcer = request.getRequestDispatcher(forward.getPath());
				dispathcer.forward(request, response);
			}
		}
	}

}
