package controller.booking;

import java.io.IOException;
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
import domain.theater.ScheduleTurnVO;
import model.booking.BookingService;
import model.theater.TheaterService;

public class MemberBookingSelectSeatCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		int turnNo = Integer.parseInt(req.getParameter("turnNo"));
		BookingVO bookingVO = new BookingVO();
		bookingVO.setTurnNo(turnNo);
		//수정필요!!
		//bookingVO.setMemberNo((String)req.getSession().getAttribute("memberNo"));
		bookingVO.setMemberNo("17121500004");
		
		
		HttpSession session =  req.getSession();
		BookingSnVO bookingSn = new BookingSnVO();
		bookingSn.setMovieSn(req.getParameter("movieSelectedName"));
		bookingSn.setTheaterSn(req.getParameter("theaterSelectedName"));
		bookingSn.setDateSn(req.getParameter("dateSelectedName"));
		bookingSn.setTurnSn(req.getParameter("turnSelectedName"));

		
		session.setAttribute("bookingVO", bookingVO);
		session.setAttribute("bookingSn", bookingSn);
		ActionForward forward = new ActionForward();
		try {
			BookingService bookingService = BookingService.getInstance();
			List<BookingSeatVO> seatList = bookingService.retrieveSeatListByTurnNo(turnNo);
			
			seatList.get(0).setFirstSeatLine(true);

			for (int i = 1; i < seatList.size(); i++) {
				if (!seatList.get(i - 1).getSeatName().substring(0, 1)
						.equals(seatList.get(i).getSeatName().substring(0, 1))) {
					seatList.get(i).setFirstSeatLine(true);
					if (i != 1) {
						seatList.get(i - 1).setLastSeatLine(true);
						;
					}
				}

			}
			req.setAttribute("seatList", seatList);

			// 수정필요
			// TheaterService theaterService = TheaterService.getInstance();
			// ScheduleTurnVO turnVO = theaterService.어떤것(turnNo);
//			ScheduleTurnVO turnVO = new ScheduleTurnVO();
//			req.getSession().setAttribute("turnVO", turnVO);

			forward.setPath("layoutUser.jsp?article=/user/booking/memberBookingSelectSeat.jsp");
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
