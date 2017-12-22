package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;

//아이디 찾기 폼 요청
public class FindIdFormCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		ActionForward forward = new ActionForward();
		forward.setPath("/layout.jsp?content=user/member/findId.jsp");		//경로 꼭 재설정
		forward.setRedirect(false);
		return forward;
	}
	

}
