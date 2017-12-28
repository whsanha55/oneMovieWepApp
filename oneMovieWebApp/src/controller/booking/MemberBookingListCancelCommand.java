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

public class MemberBookingListCancelCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		ActionForward forward = new ActionForward();
		try {
			//�����ʿ�
			//String memberNo = (String) req.getSession().getAttribute("memberNo");
			String memberNo = "17121500004";
			
			int currentPage = 0;
			try {
				currentPage = Integer.parseInt(req.getParameter("currentPage"));
			} catch (NumberFormatException ne) {
				currentPage = 1;
			}
			PagingVO paging = new PagingVO();
			// 1. �� �������� ������ �Խñ� ���� �����Ѵ�.
			paging.setPostPerPage(10);
			// 2. �� �������� ������ ������ ��� ���� �����Ѵ�.
			paging.setPageBlock(3);
			// 3. ���� ������ ��ȣ�� �����Ѵ�.
			paging.setCurrentPage(currentPage);

			BookingService bookingService = BookingService.getInstance();
			
			int totalPost = bookingService.retrieveCountBookingList("memberNo", memberNo, 1);
			
			paging.setTotalPost(totalPost);
			paging.setProperty();
			
			req.setAttribute("paging", paging);
			System.out.println(paging.toString());
			int startRow = paging.getStartRow();
			int endRow = paging.getEndRow();
			
			List<BookingVO> bookingList = bookingService.retrieveBookingList("memberNo",memberNo,1,startRow,endRow);
			req.setAttribute("bookingList", bookingList);
			forward.setPath("/layoutUser.jsp?article=/user/booking/auth/memberBookingListCancel.jsp");
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
