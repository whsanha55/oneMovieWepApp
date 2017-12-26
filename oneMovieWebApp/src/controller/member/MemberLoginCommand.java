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
				System.out.println(member.getMemberId() + "님이 로그인하셨습니다.");
				
				//4. 메인 페이지로 이동한다.
				req.setAttribute("result", "로그인되었습니다.");
				forward.setPath("/user/member/memberLoginView.jsp");  
				forward.setRedirect(false);
				return forward;				
				
			} else {
				req.setAttribute("result", "아이디와 비밀번호를 확인해주세요.");
				forward.setPath("/user/member/memberLoginView.jsp");
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
