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

//로그인 요청 처리
public class MemberLoginCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		//1. 로그인 창에 입력된 아이디와 비밀번호를 구한다.
		String memberId = req.getParameter("memberId");
		String memberPwd = req.getParameter("memberPwd");
		
		//2. 로그인 정보를 DB와 대조한다.
		ActionForward forward = new ActionForward();
		try {
			MemberService service = MemberService.getInstance();
			MemberVO member = service.retrieveMember(memberId, memberPwd);
			
			if(member != null) {
				//3. 세션 영역에 아이디와 회원번호를 바인딩한다.
				HttpSession session = req.getSession();
				session.setAttribute("memberId", member.getMemberId());
				session.setAttribute("memberNo", member.getMemberNo());
								
				//4. 메인 페이지로 이동한다.
				forward.setPath("/layout.jsp");   	//경로 꼭 다시 확인이욤
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
