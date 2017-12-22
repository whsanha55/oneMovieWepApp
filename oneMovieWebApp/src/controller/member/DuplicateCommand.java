package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.member.MemberService;

public class DuplicateCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		//1. 검색조건과 키워드를 구한다.
		String keyfield = req.getParameter("keyfield");
		String keyword = req.getParameter("keyword");
		
		ActionForward forward = new ActionForward();
		try {
			MemberService service = MemberService.getInstance();
			int count = service.retrieveMemberDuplicate(keyfield, keyword);
			
			if(count == 0) {
				req.setAttribute("result", "사용가능한 " + keyfield + "입니다.");
				req.setAttribute("key", "ok");
				forward.setPath("/dupView.jsp");
				forward.setRedirect(false);
				return forward;
			} else {
				req.setAttribute("result", "이미 사용중인 " + keyfield + "입니다.");
				req.setAttribute("key", "no");
				forward.setPath("/dupView.jsp");
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
