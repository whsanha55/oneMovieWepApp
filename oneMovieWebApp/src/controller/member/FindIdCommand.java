package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.member.MemberService;

//ID 찾기 요청 처리
public class FindIdCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		ActionForward forward = new ActionForward();
		
		//1. 이름, 이메일 정보를 구한다.
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		
		//2. 입력받은 정보를 DB와 대조하여 아이디를 구한다.
		try {
			MemberService service = MemberService.getInstance();
			String memberId = service.retrieveMemberId(name, email);
			
			if(memberId != null) {
				//3. 아이디를 리퀘스트 영역에 바인딩한다.
				req.setAttribute("memberId", memberId);
				
				//4. findId.jsp로 이동
				forward.setPath("layout.jsp?content=user/member/findId.jsp");
				forward.setRedirect(false);
				return forward;
				
			} else {
				//안내 팝업??
				System.out.println("이름과 이메일을 확인해주세요.");
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
