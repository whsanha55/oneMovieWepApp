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

public class AdminBookingAjaxCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		String keyfield = req.getParameter("keyfield");
		String keyword = req.getParameter("keyword");
		int startRow = Integer.parseInt(req.getParameter("startRow"));
		int endRow = Integer.parseInt(req.getParameter("endRow"));
		System.out.println(keyword);
		ActionForward forward = new ActionForward();
		try {
			BookingService bookingService = BookingService.getInstance();
			List<BookingVO> bookingList= bookingService.retrieveBookingList(keyfield, keyword, 0, startRow, endRow);
			req.setAttribute("bookingList", bookingList);
			forward.setPath("/admin/booking/adminBookingView.jsp");
			forward.setRedirect(false);
			
		} catch (Exception e) {
			req.setAttribute("exception", e);
			forward.setPath("/error.jsp");
			forward.setRedirect(false);

		}

		return forward;
	}

}
