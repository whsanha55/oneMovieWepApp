package controller.booking;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.booking.BookingService;

public class AdminBookingByMemberCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		ActionForward forward = new ActionForward();

		
		req.setAttribute("memberNo", req.getParameter("memberNo"));
		
		forward.setPath("/admin/layoutAdmin.jsp?article=/admin/booking/adminBookingByMember.jsp");
		forward.setRedirect(false);

		return forward;
	}

}
