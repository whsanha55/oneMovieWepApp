package controller.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.member.MemberService;

public class ManageMemberCountCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		String keyfield;
		String keyword;
		
		if(req.getParameter("keyword") == null || req.getParameter("keyword").equals("")) {
			keyfield = "all";
			keyword = "all";
		} else {
			keyfield = req.getParameter("keyfield");
			keyword = req.getParameter("keyword");
		} 
		
		
		ActionForward forward = new ActionForward();
		try {
			MemberService service = MemberService.getInstance();		
			int searchCount = service.retrieveSearchCount(keyfield, keyword);
			req.setAttribute("searchCount", searchCount);
			forward.setPath("/admin/member/countView.jsp");
			forward.setRedirect(false);
			return forward;
			
		} catch(Exception e) {
			req.setAttribute("exception", e);
			forward.setPath("/error.jsp");
			forward.setRedirect(false);
			return forward;
		}
		
		
		
		
	}
	

}
