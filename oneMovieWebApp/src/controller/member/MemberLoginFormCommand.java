package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;

//로그인 폼을 요청하는 컨트롤러 서블릿
public class MemberLoginFormCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		ActionForward forward = new ActionForward();
		forward.setPath("/layoutUser.jsp?article=/user/member/memberLogin.jsp"); 		//주소 설정 꼭 다시
		forward.setRedirect(false);		
		return forward;
	}
		

}
