package controller.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.member.MemberVO;
import model.member.MemberService;

public class ManageMemberCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		//1. 검색조건과 검색어를 구한다.	
		String keyfield = req.getParameter("keyfield");
		String keyword = req.getParameter("keyword");
		int startRow = Integer.parseInt(req.getParameter("startRow"));
		int endRow = Integer.parseInt(req.getParameter("endRow"));
		
		System.out.println(keyfield);
		System.out.println(keyword);
		System.out.println(startRow);
		System.out.println(endRow);
		
		if(startRow == 0 && endRow == 0) {
			ActionForward forward = new ActionForward();
			try {
				MemberService service = MemberService.getInstance();
				int searchCount = service.retrieveSearchCount(keyfield, keyword);
				
				req.setAttribute("searchCount", searchCount);
				forward.setPath("/admin/member/manageView.jsp");
				forward.setRedirect(false);
				return forward;
				
				
			} catch(Exception e) {
				req.setAttribute("exception", e);
				forward.setPath("/error.jsp");
				forward.setRedirect(false);
				return forward;
			}
		} else {
			ActionForward forward = new ActionForward();
			try {
				MemberService service = MemberService.getInstance();
				ArrayList<MemberVO> members = service.retrieveMemberList(keyfield, keyword, startRow, endRow);
				
				req.setAttribute("members", members);
				forward.setPath("/admin/member/manageView.jsp");
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
	
		
}
	


