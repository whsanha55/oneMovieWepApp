package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.member.MemberService;

public class DuplicateCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		//1. �˻����ǰ� Ű���带 ���Ѵ�.
		String keyfield = req.getParameter("keyfield");
		String keyword = req.getParameter("keyword");
		
		ActionForward forward = new ActionForward();
		try {
			MemberService service = MemberService.getInstance();
			int count = service.retrieveMemberDuplicate(keyfield, keyword);
			
			if(count == 0) {
				req.setAttribute("result", "��밡���� " + keyfield + "�Դϴ�.");
				req.setAttribute("key", "ok");
				forward.setPath("/dupView.jsp");
				forward.setRedirect(false);
				return forward;
			} else {
				req.setAttribute("result", "�̹� ������� " + keyfield + "�Դϴ�.");
				req.setAttribute("key", "no");
				forward.setPath("/dupView.jsp");
				forward.setRedirect(false);
				return forward;
			}
			
			
			
		} catch (Exception e) {
			forward.setPath("/error.jsp");
			forward.setRedirect(false);
			return forward;
		}
		
		
	}

	
	
}