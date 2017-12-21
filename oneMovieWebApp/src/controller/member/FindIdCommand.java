package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.member.MemberService;

//ID ã�� ��û ó��
public class FindIdCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		ActionForward forward = new ActionForward();
		
		//1. �̸�, �̸��� ������ ���Ѵ�.
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		
		//2. �Է¹��� ������ DB�� �����Ͽ� ���̵� ���Ѵ�.
		try {
			MemberService service = MemberService.getInstance();
			String memberId = service.retrieveMemberId(name, email);
			
			if(memberId != null) {
				//3. ���̵� ������Ʈ ������ ���ε��Ѵ�.
				req.setAttribute("memberId", memberId);
				
				//4. findId.jsp�� �̵�
				forward.setPath("layout.jsp?content=user/member/findId.jsp");
				forward.setRedirect(false);
				return forward;
				
			} else {
				//�ȳ� �˾�??
				System.out.println("�̸��� �̸����� Ȯ�����ּ���.");
				forward.setPath("/user/member/memberLogin.jsp");	
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
