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
		
		
		
		//아무것도 전달되지 않았을 경우: 전체 회원 수 구함
		if(keyfield == null && keyword == null && startRow == 0 && endRow == 0) {		
			ActionForward forward = new ActionForward();
			try {
				MemberService service = MemberService.getInstance();
				int totalCount = service.retrieveTotalCount();
				req.setAttribute("totalCount", totalCount);
				
				forward.setPath("/layout.jsp?content=manageMember.jsp");
				forward.setRedirect(false);
				return forward;
				
			} catch (Exception e) {
				req.setAttribute("exception", e);
				forward.setPath("/error.jsp");
				forward.setRedirect(false);
				return forward;
			}
			
			
			
		//keyfield와 keyword만 전달되었을 경우: 검색 결과에 따른 회원 수 구함
		} else if(startRow == 0 && endRow == 0 && keyfield != null && keyword != null) {
			ActionForward forward = new ActionForward();
			try {
				MemberService service = MemberService.getInstance();
				int searchCount = service.retrieveSearchCount(keyfield, keyword);
				
				req.setAttribute("searchCount", searchCount);
				
				forward.setPath("/layout.jsp?content=manageMember.jsp");
				forward.setRedirect(false);
				return forward;
				
			} catch (Exception e) {
				req.setAttribute("exception", e);
				forward.setPath("/error.jsp");
				forward.setRedirect(false);
				return forward;
			}
			
			
			
		//startRow, endRow가 전달되을 경우: 전체 회원 조회	
		} else if(keyfield == null && keyword == null && startRow != 0 && endRow != 0) {
			ActionForward forward = new ActionForward();	
			try {
				MemberService service = MemberService.getInstance();
				ArrayList<MemberVO> members = service.retrieveMemberList(startRow, endRow);
				req.setAttribute("members", members);
				
				forward.setPath("/layout.jsp?content=manageMember.jsp");
				forward.setRedirect(false);
				return forward;
				
			} catch (Exception e) {
				req.setAttribute("exception", e);
				forward.setPath("/error.jsp");
				forward.setRedirect(false);
				return forward;
			}
			
			
			
		//keyfield, keyword, startRow, endRow까지 전달되었을 경우: 회원 검색 결과 조회
		} else if(keyfield != null && keyword != null && startRow != 0 && endRow != 0) {
			ActionForward forward = new ActionForward();
			try {
				MemberService service = MemberService.getInstance();
				ArrayList<MemberVO> members = service.retrieveMemberList(keyfield, keyword, startRow, endRow);
				
				req.setAttribute("members", members);
				
				forward.setPath("/layout.jsp?content=manageMember.jsp");
				forward.setRedirect(false);
				return forward;
			} catch (Exception e) {
				req.setAttribute("exception", e);
				forward.setPath("/error.jsp");
				forward.setRedirect(false);
				return forward;
			}
			
		}  else {//뭐 하나 빠진 데이터 있을 경우?
			ActionForward forward = new ActionForward();
			forward.setPath("/layout.jsp?content=manageMember.jsp");
			forward.setRedirect(false);
			return forward;
		}	

		
		
		
	}
	
		
}
	


