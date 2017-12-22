package controller.booking;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		int price = Integer.parseInt(req.getParameter("price"));
		String paymentCode = "abcdefghijklmop";
		
		ActionForward forward = new ActionForward();
		try {
			BookingVO bookingVO = (BookingVO) req.getSession().getAttribute("bookingVO");
			ScheduleTurnVO turnVO =  (ScheduleTurnVO) req.getSession().getAttribute("turnVO");
//			bookingVO.setTurnNo(turnVO.getTurnNo());
			bookingVO.setTurnNo(226);
			bookingVO.setPaymentCode(paymentCode);
			bookingVO.setPrice(price);
			System.out.println(bookingVO.toString());
			System.out.println(bookingVO.getBookingSeats().toString());
			BookingService bookingService = BookingService.getInstance();
			bookingService.addBooking(bookingVO);
			forward.setPath("/user/booking/memberExecuteBooking.jsp");
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
