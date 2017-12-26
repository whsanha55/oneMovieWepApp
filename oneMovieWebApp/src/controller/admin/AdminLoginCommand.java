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
		//������ �α��� ó��
		
		//1. ������ ���̵�, ��й�ȣ�� ���Ѵ�.
		String adminId = req.getParameter("adminId");
		String adminPwd = req.getParameter("adminPwd");
		System.out.println(adminId);
		System.out.println(adminPwd);
		
		ActionForward forward = new ActionForward();
		
		try {
		//2. �α��� ������ DB�� �����Ѵ�.
			AdminService service = AdminService.getInstance();
			AdminVO admin = service.retrieveAdmin(adminId, adminPwd);
						
			if(admin != null) {
				//3. ���ǿ� ������ ���̵� ���ε��Ѵ�.
				HttpSession session = req.getSession();
				session.setAttribute("adminId", adminId);
				System.out.println(adminId + "���� �α����߽��ϴ�.");
				
				//4. ������ ���̾ƿ� �������� �̵��Ѵ�.
				req.setAttribute("result", "������ �������� �̵��մϴ�.");
				forward.setPath("/admin/member/adminLoginView.jsp");		//������ ���� �ּ� Ȯ��
				forward.setRedirect(false);
				return forward;
				
			} else {
				req.setAttribute("result", "���̵�� ��й�ȣ�� Ȯ�����ּ���.");
				forward.setPath("/admin/member/adminLoginView.jsp");	
				forward.setRedirect(false);
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
