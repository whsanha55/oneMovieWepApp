package controller.booking;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.booking.BookingVO;
import model.booking.BookingService;

public class MemberBookingListCurrCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		ActionForward forward = new ActionForward();
		try {
			//수정필요
			//String memberNo = (String) req.getSession().getAttribute("memberNo");
			String memberNo = "17121500004";
			
			BookingService bookingService = BookingService.getInstance();
			List<BookingVO> bookingList = bookingService.retrieveBookingList("memberNo",memberNo,2,0,10);
			req.setAttribute("bookingList", bookingList);
			forward.setPath("/user/booking/memberBookingListCurr.jsp");
			forward.setRedirect(false);
			return forward;
		} catch (Exception e) {
			forward.setPath("/error.jsp");
			forward.setRedirect(false);
			return forward;
		}
	
	}

}
