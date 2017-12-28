package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		String memberNo = "";
		HttpSession session = req.getSession();
		if(!session.getAttribute("memberNo").equals("")) {
			memberNo = (String)session.getAttribute("memberNo");
		} else {
			memberNo = "0";
		}
		
				
		ActionForward forward = new ActionForward();
		
		try {
			MemberService service = MemberService.getInstance();
			int count = 0;	
			
			if(keyfield.equals("member_id")) {
				count = service.retrieveIdDuplicate(keyword);
				keyfield = "���̵�";
			} else if(keyfield.equals("email")) {
				count = service.retrieveEmailDuplicate(memberNo, keyword);
				keyfield = "�̸���";
			}
						
			if(count == 0) {
				req.setAttribute("result", "��밡���� " + keyfield + "�Դϴ�.");
				req.setAttribute("key", "ok");
				forward.setPath("/user/member/dupView.jsp");
				forward.setRedirect(false);
				return forward;
			} else {
				req.setAttribute("result", "�̹� ������� " + keyfield + "�Դϴ�.");
				req.setAttribute("key", "no");
				forward.setPath("/user/member/dupView.jsp");
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
