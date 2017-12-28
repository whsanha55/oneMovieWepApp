package controller.booking;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.PagingVO;
import domain.booking.BookingVO;
import model.booking.BookingService;

public class MemberBookingListPastCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		ActionForward forward = new ActionForward();
		try {
			String memberNo = (String) req.getSession().getAttribute("memberNo");
			
			//테스트
//			String memberNo = "17121500004";
			
			
			int currentPage = 0;
			try {
				currentPage = Integer.parseInt(req.getParameter("currentPage"));
			} catch (NumberFormatException ne) {
				currentPage = 1;
			}
			PagingVO paging = new PagingVO();
			// 1. 한 페이지에 보여줄 게시글 수를 설정한다.
			paging.setPostPerPage(10);
			// 2. 한 페이지에 보여줄 페이지 목록 수를 설정한다.
			paging.setPageBlock(3);
			// 3. 현재 페이지 번호를 설정한다.
			paging.setCurrentPage(currentPage);
			
			BookingService bookingService = BookingService.getInstance();
			
			int totalPost = bookingService.retrieveCountBookingList("memberNo", memberNo, 3);
			paging.setTotalPost(totalPost);
			paging.setProperty();
			
			req.setAttribute("paging", paging);
			
			int startRow = paging.getStartRow();
			int endRow = paging.getEndRow();
			List<BookingVO> bookingList = bookingService.retrieveBookingList("memberNo",memberNo,3,startRow, endRow);
		
			req.setAttribute("bookingList", bookingList);
			
			forward.setPath("/layoutUser.jsp?article=/user/booking/auth/memberBookingListPast.jsp");
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
