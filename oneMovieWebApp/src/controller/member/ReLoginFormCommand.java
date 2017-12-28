package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;

public class ReLoginFormCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		ActionForward forward = new ActionForward();
		forward.setPath("/layoutUser.jsp?article=/user/member/reLogin.jsp");
		forward.setRedirect(false);
		return forward;
	}
	
	
	
}
