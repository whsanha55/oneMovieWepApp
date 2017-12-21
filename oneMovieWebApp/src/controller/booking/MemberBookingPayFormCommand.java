package controller.booking;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import controller.ActionForward;
import controller.Command;
import domain.booking.BookingSeatVO;
import domain.booking.BookingVO;
import domain.member.MemberVO;
import domain.theater.ScheduleTurnVO;
import model.booking.BookingService;
import oracle.net.aso.g;

public class MemberBookingPayFormCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {


		BookingVO bookingVO = new BookingVO();
		for(String temp : req.getParameter("bookingSeats").split(",")) {
			BookingSeatVO bookingSeatVO = new BookingSeatVO();
			bookingSeatVO.setSeatNo(Integer.parseInt(temp));
			bookingVO.addBookingSeat(bookingSeatVO);
			System.out.println(temp);
		}
		ScheduleTurnVO turnVO = (ScheduleTurnVO)req.getSession().getAttribute("turnVO");
		
		bookingVO.setTurnNo(turnVO.getTurnNo());
		//수정 필요
		//bookingVO.setMemberNo((String)req.getSession().getAttribute("memberNo"));
		bookingVO.setMemberNo("17121500004");
		
		req.getSession().setAttribute("bookingVO", bookingVO);
		ActionForward forward = new ActionForward();
		forward.setPath("/user/booking/memberBookingPayForm.jsp");
		forward.setRedirect(false);
		return forward;

	}

}
