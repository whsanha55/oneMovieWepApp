package controller.booking;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;
import domain.booking.BookingSeatVO;
import domain.booking.BookingSnVO;
import domain.booking.BookingVO;
import domain.member.MemberVO;
import domain.theater.ScheduleTurnVO;
import model.booking.BookingService;
import oracle.net.aso.g;

public class MemberBookingPayFormCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		HttpSession session = req.getSession();
		BookingVO bookingVO = (BookingVO) session.getAttribute("bookingVO");
		bookingVO.setPrice(Integer.parseInt(req.getParameter("priceSelectedName")));
		
		BookingSnVO bookingSn = (BookingSnVO) session.getAttribute("bookingSn");
		bookingSn.setSeatSn(req.getParameter("seatSelectedName"));
		bookingSn.setPriceSn(Integer.parseInt(req.getParameter("priceSelectedName")));
		
		for(String temp : req.getParameter("bookingSeats").split(",")) {
			BookingSeatVO bookingSeatVO = new BookingSeatVO();
			bookingSeatVO.setSeatNo(Integer.parseInt(temp));
			bookingVO.addBookingSeat(bookingSeatVO);
			
		}
		
		
		ActionForward forward = new ActionForward();
		forward.setPath("/layoutUser.jsp?article=/user/booking/auth/memberBookingPayForm.jsp");
		forward.setRedirect(false);
		return forward;

	}

}
