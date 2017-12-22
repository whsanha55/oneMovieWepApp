package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;
import domain.admin.AdminVO;
import model.admin.AdminService;

public class AdminLoginCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		//관리자 로그인 처리
		
		//1. 관리자 아이디, 비밀번호를 구한다.
		String adminId = req.getParameter("adminId");
		String adminPwd = req.getParameter("adminPwd");
		
		ActionForward forward = new ActionForward();
		
		try {
		//2. 로그인 정보를 DB와 대조한다.
			AdminService service = AdminService.getInstance();
			AdminVO admin = service.retrieveAdmin(adminId, adminPwd);
						
			if(admin != null) {
				//3. 세션에 관리자 아이디를 바인딩한다.
				HttpSession session = req.getSession();
				session.setAttribute("adminId", adminId);
				
				//4. 관리자 레이아웃 페이지로 이동한다.	
				forward.setPath("/adminMember/main.jsp");		//관리자 메인 주소 확인
				forward.setRedirect(true);
				return forward;
				
			} else {
				// 팝업 출력?? 어디서 처리하지??
				System.out.println("아이디와 비밀번호를 확인해주세요.");
				forward.setPath("/adminMember/adminLogin.jsp");	
				forward.setRedirect(true);
				return forward;
			}
				
			
		} catch (Exception e) {
			req.setAttribute("exception", e);
			forward.setPath("/error.jsp");
			forward.setRedirect(false);
			return forward;
		}
		

	}

	
	
	
}
