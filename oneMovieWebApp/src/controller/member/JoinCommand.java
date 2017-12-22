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
		

		//1. 회원가입할 회원정보 객체를 구한다.
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
			//2. DB에 전달한다.
			MemberService service = MemberService.getInstance();
			service.addMember(member);
						
			//3. 회원가입 성공 페이지로 이동한다.
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
