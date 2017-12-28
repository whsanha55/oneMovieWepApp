package controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
		String keyfield;
		String keyword;
		int startRow;
		int endRow;
		String sort;
		int sortType;

		//1. 검색조건과 검색어를 구한다.	
		if(req.getParameter("keyword").equals("")) {	//전체조회
			keyfield = "all";
			keyword = "all";
		} else {
			keyfield = req.getParameter("keyfield");
			keyword = req.getParameter("keyword");
		}
		
		startRow = Integer.parseInt(req.getParameter("startRow"));
		endRow = Integer.parseInt(req.getParameter("endRow"));
		
		System.out.println(keyfield + " " + keyword + " " + startRow + " " + endRow);
		
		sort = req.getParameter("sort");
		sortType = Integer.parseInt(req.getParameter("sortType"));
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		map.put("sort", sort);
		map.put("sortType", sortType);
		
		System.out.println(map);
		
		ActionForward forward = new ActionForward();
		try {
			MemberService service = MemberService.getInstance();
			ArrayList<MemberVO> members = service.retrieveMemberList(map);
			
			System.out.println(members);
			
			req.setAttribute("members", members);
			forward.setPath("/admin/member/manageView.jsp");
			forward.setRedirect(false);
			return forward;
			
		} catch (Exception e) {
			req.setAttribute("exception", e);
			forward.setPath("/error.jsp");
			forward.setRedirect(false);
			return forward;
		}
	
		
		
		
	}
	
		
}
	



