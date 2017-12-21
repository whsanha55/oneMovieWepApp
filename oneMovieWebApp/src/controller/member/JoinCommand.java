package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.member.MemberVO;
import model.member.MemberService;

public class JoinCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		
		ActionForward forward = new ActionForward();
		

		//1. ȸ�������� ȸ������ ��ü�� ���Ѵ�.
		MemberVO member = new MemberVO();		
		member.setMemberId(req.getParameter("memberId"));		
		member.setMemberPwd(req.getParameter("memberPwd"));
		member.setName(req.getParameter("name"));
		member.setEmail(req.getParameter("email"));
		member.setGender(req. getParameter("gender"));
		member.setPhone(req.getParameter("phone"));
		member.setAddress(req.getParameter("address"));
		member.setZipcode(req.getParameter("zipcode"));

		try {			
			//2. DB�� �����Ѵ�.
			MemberService service = MemberService.getInstance();
			service.addMember(member);
						
			//3. ȸ������ ���� �������� �̵��Ѵ�.
			forward.setPath("/joinView.jsp");
			forward.setRedirect(false);
			return forward;			
			
		} catch (Exception e) {
			forward.setPath("/error.jsp");
			forward.setRedirect(false);
			return forward;	
		}			
		
		
		
		
		
	}

	
	
}
