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

public class MemberBookingIsRefundableAjaxCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		ActionForward forward = new ActionForward();
		String ticketNo = req.getParameter("ticketNo");
		try {
			
			BookingService bookingService = BookingService.getInstance();
			boolean isRefundable = bookingService.retrieveBookingRefundable(ticketNo);
			req.setAttribute("isRefundable",isRefundable);
			forward.setPath("/user/booking/memberBookingisRefundableView.jsp");
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
