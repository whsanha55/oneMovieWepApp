package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;
import model.member.MemberService;

//ȸ�� Ż�� ��û ó��
public class WithdrawCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		ActionForward forward = new ActionForward();
		
		//1. ȸ����ȣ�� ���Ѵ�.
		HttpSession session = req.getSession();
		String memberNo = (String)session.getAttribute("memberNo");
		
		try {
			MemberService service = MemberService.getInstance();
			service.deleteMember(memberNo);
			session.invalidate();
			forward.setPath("/user/member/withdrawView.jsp");
			forward.setRedirect(false);
			return forward;
			
		} catch(Exception e) {
			req.setAttribute("exception", e);
			forward.setPath("/error.jsp");
			forward.setRedirect(false);
			return forward;			
		}
		
		

	}
	

}
