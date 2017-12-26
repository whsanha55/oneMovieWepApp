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
		System.out.println(name);
		
		//2. 입력받은 정보를 DB와 대조하여 아이디를 구한다.
		try {
			MemberService service = MemberService.getInstance();
			String memberId = service.retrieveMemberId(name, email);
			
			if(memberId != null) {
				//3. 아이디를 리퀘스트 영역에 바인딩한다.
				req.setAttribute("memberId", memberId);
				
				//4. findId.jsp로 이동
				req.setAttribute("result", "입력하신 정보의 아이디는 " + memberId + " 입니다.");
				forward.setPath("/user/member/findIdView.jsp");
				forward.setRedirect(false);
				return forward;
				
			} else {
				req.setAttribute("result", "입력하신 정보의 아이디가 없습니다.");				
				forward.setPath("/user/member/findIdView.jsp");	
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
