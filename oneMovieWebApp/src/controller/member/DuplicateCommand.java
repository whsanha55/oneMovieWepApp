package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		String memberNo = "";
		HttpSession session = req.getSession();
		if(!session.getAttribute("memberNo").equals("")) {
			memberNo = (String)session.getAttribute("memberNo");
		} else {
			memberNo = "0";
		}
		
				
		ActionForward forward = new ActionForward();
		
		try {
			MemberService service = MemberService.getInstance();
			int count = 0;	
			
			if(keyfield.equals("member_id")) {
				count = service.retrieveIdDuplicate(keyword);
				keyfield = "아이디";
			} else if(keyfield.equals("email")) {
				count = service.retrieveEmailDuplicate(memberNo, keyword);
				keyfield = "이메일";
			}
						
			if(count == 0) {
				req.setAttribute("result", "사용가능한 " + keyfield + "입니다.");
				req.setAttribute("key", "ok");
				forward.setPath("/user/member/dupView.jsp");
				forward.setRedirect(false);
				return forward;
			} else {
				req.setAttribute("result", "이미 사용중인 " + keyfield + "입니다.");
				req.setAttribute("key", "no");
				forward.setPath("/user/member/dupView.jsp");
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
