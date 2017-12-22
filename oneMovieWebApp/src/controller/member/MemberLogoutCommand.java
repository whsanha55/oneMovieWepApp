package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;

public class MemberLogoutCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		ActionForward forward = new ActionForward();
		
		try {
			
			//1. HttpSession ������ ���ε��� ������ �����Ѵ�.
			HttpSession session = req.getSession();
			session.invalidate();
			
			//3. ���� ȭ������ �̵��Ѵ�.
			forward.setPath("/layout.jsp");
			forward.setRedirect(true);
			return forward;	
			
			
		} catch (Exception e) {
			req.setAttribute("exception", e);
			forward.setPath("/error.jsp");
			forward.setRedirect(false);
			return forward;			
		}
		
		
		
		
	}

	
	
}
