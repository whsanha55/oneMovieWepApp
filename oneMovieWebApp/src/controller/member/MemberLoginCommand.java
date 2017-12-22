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

//�α��� ��û ó��
public class MemberLoginCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		//1. �α��� â�� �Էµ� ���̵�� ��й�ȣ�� ���Ѵ�.
		String memberId = req.getParameter("memberId");
		String memberPwd = req.getParameter("memberPwd");
		
		//2. �α��� ������ DB�� �����Ѵ�.
		ActionForward forward = new ActionForward();
		try {
			MemberService service = MemberService.getInstance();
			MemberVO member = service.retrieveMember(memberId, memberPwd);
			
			if(member != null) {
				//3. ���� ������ ���̵�� ȸ����ȣ�� ���ε��Ѵ�.
				HttpSession session = req.getSession();
				session.setAttribute("memberId", member.getMemberId());
				session.setAttribute("memberNo", member.getMemberNo());
								
				//4. ���� �������� �̵��Ѵ�.
				forward.setPath("/layout.jsp");   	//��� �� �ٽ� Ȯ���̿�
				forward.setRedirect(true);
				return forward;				
				
			} else {
				
				forward.setPath("/user/member/memberLogin.jsp");
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
