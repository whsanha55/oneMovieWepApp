package controller.booking;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.booking.BookingRefundVO;
import domain.booking.BookingSeatVO;
import domain.booking.BookingVO;
import model.booking.BookingService;

public class MemberBookingRefundAjaxCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		String ticketNo = req.getParameter("ticketNo");
		System.out.println(ticketNo);
		System.out.println("length : " + ticketNo.length());
		ActionForward forward = new ActionForward();
		try {
			
			BookingRefundVO bookingRefundVO = new BookingRefundVO(); 
			bookingRefundVO.setTicketNo(ticketNo);
			//환불 예정 정책
			//환불날짜는 하루후(1)
			//환불금액은 100% (1)
			bookingRefundVO.setRefundDatePolicy(1);
			bookingRefundVO.setRefundPricePolicy(1);			
			
			BookingService bookingService = BookingService.getInstance();
			boolean isRefund = bookingService.deleteBooking(bookingRefundVO);
			req.setAttribute("isRefund", isRefund);
			forward.setPath("/user/booking/memberRefundBookingView.jsp");
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
