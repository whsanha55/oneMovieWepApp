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

//회원정보 수정 전 재로그인 요청 처리
public class ReLoginCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		ActionForward forward = new ActionForward();
		//1. 아이디와 비밀번호를 구한다.
		String memberId = req.getParameter("memberId");
		String memberPwd = req.getParameter("memberPwd");
		
		try {
			MemberService service = MemberService.getInstance();
			MemberVO member = service.retrieveMember(memberId, memberPwd);
			
			
			
			if(member.getName() != null) {
				//2. 반환된 MemberVO 객체를 리퀘스트 영역에 바인딩한다.
				req.setAttribute("member", member);
				
				//3. 회원정보 수정 폼으로 이동한다.
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
