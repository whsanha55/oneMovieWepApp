package controller.booking;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.theater.ScreenVO;
import model.booking.BookingService;
import model.theater.TheaterService;

public class AdminBookingCountAjaxCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		String keyfield = req.getParameter("keyfield");
		String keyword = req.getParameter("keyword");
		ActionForward forward = new ActionForward();
		try {
		
			BookingService bookingService=  BookingService.getInstance();
			int countBookingList = bookingService.retrieveCountBookingList(keyfield, keyword, 0);
			req.setAttribute("countBookingList", countBookingList);
			forward.setPath("/admin/booking/adminBookingCountView.jsp");
			forward.setRedirect(false);

		} catch (Exception e) {
			req.setAttribute("exception", e);
			forward.setPath("/error.jsp");
			forward.setRedirect(false);

		}

		return forward;
	}

}
