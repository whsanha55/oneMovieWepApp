package controller.booking;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;
import domain.booking.BookingVO;
import domain.theater.ScheduleTurnVO;
import model.booking.BookingService;

public class MemberExecuteBookingCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		//¹Ì»ç¿ë
		String cardCompany = req.getParameter("cardCompany");
		String serialCardNum1 = req.getParameter("serialCardNum1");
		String serialCardNum2 = req.getParameter("serialCardNum2");
		String serialCardNum3 = req.getParameter("serialCardNum3");
		String serialCardNum4 = req.getParameter("serialCardNum4");
		
		
		Random rd = new Random();

		StringBuilder paymentCode = new StringBuilder();
		for (int i = 0; i < 16; i++) {

			switch (rd.nextInt(3)) {
			case 0:
				paymentCode.append((char)((rd.nextInt(26)+97)));
				break;
			case 1:
				paymentCode.append((char)((rd.nextInt(26)+65)));
				break;
			case 2:
				paymentCode.append(rd.nextInt(10));
				break;
			}
		}
		
		HttpSession session = req.getSession();
		BookingVO bookingVO = (BookingVO) session.getAttribute("bookingVO");
		
		ActionForward forward = new ActionForward();
		try {
			
			bookingVO.setPaymentCode(paymentCode.toString());
			BookingService bookingService = BookingService.getInstance();
			bookingService.addBooking(bookingVO);
			
			session.removeAttribute("bookingVO");
			session.removeAttribute("bookingSn");
			forward.setPath("/layoutUser.jsp?article=/user/booking/auth/memberExecuteBooking.jsp");
			forward.setRedirect(true);
			return forward;
		} catch (Exception e) {
			req.setAttribute("exception", e);
			forward.setPath("/error.jsp");
			forward.setRedirect(false);
			return forward;
		}
	}
}
