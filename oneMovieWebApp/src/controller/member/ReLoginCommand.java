package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;
import domain.member.MemberVO;
import model.member.MemberService;

//ȸ������ ���� �� ��α��� ��û ó��
public class ReLoginCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		ActionForward forward = new ActionForward();
		//1. ���̵�� ��й�ȣ�� ���Ѵ�.
		String memberId = req.getParameter("memberId");
		String memberPwd = req.getParameter("memberPwd");
		
		try {
			MemberService service = MemberService.getInstance();
			MemberVO member = service.retrieveMember(memberId, memberPwd);
			
			
			
			if(member.getName() != null) {
				//2. ��ȯ�� MemberVO ��ü�� ������Ʈ ������ ���ε��Ѵ�.
				req.setAttribute("member", member);
				
				//3. ȸ������ ���� ������ �̵��Ѵ�.
				forward.setPath("layoutUser.jsp?article=/user/member/modifyMemberForm.jsp");
				forward.setRedirect(false);
				return forward;
				
			} else {
				forward.setPath("layoutUser.jsp?article=/user/member/reLogin.jsp");
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
