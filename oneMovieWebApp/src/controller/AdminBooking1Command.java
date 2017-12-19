package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.booking.BookingService;

public class AdminBooking1Command implements Command{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		ActionForward forward = new ActionForward();
		
			BookingService bookingService = BookingService.getInstance();
			forward.setPath("/adminBooking1.jsp");
			forward.setRedirect(false);			
				
		
		return forward;
	}
	
}
