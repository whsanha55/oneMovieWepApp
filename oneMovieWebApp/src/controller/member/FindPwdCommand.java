package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.member.MemberService;

//비밀번호 찾기 요청 처리
public class FindPwdCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		ActionForward forward = new ActionForward();
		//1. 회원 아이디와 이메일을 구한다.
		String memberId = req.getParameter("memberId");
		String email = req.getParameter("email");
		
		
		
		try {
			MemberService service = MemberService.getInstance();
			int count = service.retrieveMemberCount(memberId, email);
			
			
			//2. DB와 정보를 대조한다.
			if(count == 1) {
				//3. 임시비밀번호를 생성한다.
				String tempPwd = TempPwd.createTempPwd();
				
				//4. DB의 비밀번호를 임시비밀번호로 변경한다.
				service.updateMemberPwd(memberId, email, tempPwd);
								
				//5. 임시비밀번호를 회원 이메일로 발송한다.
				SendMail sendMail = new SendMail();
				try {
					sendMail.sendTempPwd(email, tempPwd);
				}catch (Exception e) {
					req.setAttribute("exception", e);
					forward.setPath("/error.jsp");	
					forward.setRedirect(false);
					return forward;	
				}	
					
				
				
				//7. 로그인 페이지로 이동한다.
				req.setAttribute("result", "입력하신 이메일로 임시비밀번호가 발송되었습니다. 메일을 확인해주세요.");
				forward.setPath("/user/member/findPwdView.jsp");	
				forward.setRedirect(false);
				return forward;	
				
			} else {
				req.setAttribute("result", "일치하는 회원정보가 없습니다. 아이디와 이메일을 확인해주세요.");
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
