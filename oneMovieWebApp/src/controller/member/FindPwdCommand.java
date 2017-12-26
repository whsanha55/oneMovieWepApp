package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.member.MemberService;

//��й�ȣ ã�� ��û ó��
public class FindPwdCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		ActionForward forward = new ActionForward();
		//1. ȸ�� ���̵�� �̸����� ���Ѵ�.
		String memberId = req.getParameter("memberId");
		String email = req.getParameter("email");
		
		
		
		try {
			MemberService service = MemberService.getInstance();
			int count = service.retrieveMemberCount(memberId, email);
			
			
			//2. DB�� ������ �����Ѵ�.
			if(count == 1) {
				//3. �ӽú�й�ȣ�� �����Ѵ�.
				String tempPwd = TempPwd.createTempPwd();
				
				//4. DB�� ��й�ȣ�� �ӽú�й�ȣ�� �����Ѵ�.
				service.updateMemberPwd(memberId, email, tempPwd);
								
				//5. �ӽú�й�ȣ�� ȸ�� �̸��Ϸ� �߼��Ѵ�.
				SendMail sendMail = new SendMail();
				try {
					sendMail.sendTempPwd(email, tempPwd);
				}catch (Exception e) {
					req.setAttribute("exception", e);
					forward.setPath("/error.jsp");	
					forward.setRedirect(false);
					return forward;	
				}	
					
				
				
				//7. �α��� �������� �̵��Ѵ�.
				req.setAttribute("result", "�Է��Ͻ� �̸��Ϸ� �ӽú�й�ȣ�� �߼۵Ǿ����ϴ�. ������ Ȯ�����ּ���.");
				forward.setPath("/user/member/findPwdView.jsp");	
				forward.setRedirect(false);
				return forward;	
				
			} else {
				req.setAttribute("result", "��ġ�ϴ� ȸ�������� �����ϴ�. ���̵�� �̸����� Ȯ�����ּ���.");
				forward.setPath("/user/member/findPwdView.jsp");	
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
