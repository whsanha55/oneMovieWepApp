package controller;

import java.util.HashMap;

public class CommandFactory {
	//싱글톤 패턴
	private static CommandFactory instance = new CommandFactory();
	private HashMap<String, String> map = new HashMap<String, String>();
	
	private CommandFactory() {
		//영화 시작!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!		
		map.put("/adminBooking1.do", "controller.AdminBooking1Command");
		map.put("/adminBookingAjax.do", "controller.AdminBookingAjaxCommand");
		map.put("/adminBookingAjax2.do", "controller.AdminBookingAjax2Command");
		map.put("/memberBooking1.do", "controller.memberBookingCommand");
		map.put("/user/movie/listMovie.do", "controller.movie.ListMovieCommand");
		map.put("/user/movie/findMovie.do", "controller.movie.FindMovieCommand");
		map.put("/removeMovieForm.do", "controller.RemoveMovieFormCommand");
		map.put("/removeMovie.do", "controller.RemoveMovieCommand");
		map.put("/user/movie/detailMovie.do", "controller.movie.DetailMovieCommand");
		//영화 끝====================================================
		
		
		
		//극장 시작!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		map.put("/admin/theater/adminTheaterForm.do","controller.theater.AdminTheaterFormCommand");
		map.put("/admin/theater/listScheduleTurnForm.do", "controller.theater.ListScheduleTurnCommand");
		map.put("/admin/theater/addTurnForm.do", "controller.theater.AddTurnFormCommand");
		map.put("/admin/theater/addScheduleForm.do", "controller.theater.AddScheduleFormCommand");
		map.put("/admin/theater/addSchedule.do", "controller.theater.AddScheduleTurnCommand");
		
		map.put("/admin/theater/adminTheaterView.do", "controller.theater.AdminTheaterSearchAjaxCommand");
		//극장 끝====================================================

		map.put("/user/movie/listMovie.do", "controller.movie.ListMovieCommand");
		map.put("/user/movie/findMovie.do", "controller.movie.FindMovieCommand");
		map.put("/user/movie/detailMovie.do", "controller.movie.DetailMovieCommand");
		map.put("/user/movie/removeMovie.do", "controller.movie.RemoveMovieCommand");
		map.put("/user/movie/listAllMovie.do", "controller.movie.ListAllMovieCommand");

		
		//예약 시작!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		
		map.put("/adminBooking1.do", "controller.booking.AdminBooking1Command");
		map.put("/adminBookingAjax.do", "controller.booking.AdminBookingAjaxCommand");
		map.put("/adminBookingAjax2.do", "controller.booking.AdminBookingAjax2Command");
		map.put("/memberBooking.do", "controller.booking.MemberBookingCommand");
		map.put("/movieByTheaterAndDateAjax.do", "controller.theater.MovieByTheaterAndDateAjaxCommand");
		map.put("/theaterByMovieAndDateAjax.do", "controller.theater.TheaterByMovieAndDateAjaxCommand");
		map.put("/dateByMovieAndTheaterAjax.do", "controller.theater.DateByMovieAndTheaterAjaxCommand");
		map.put("/memberBookingSelectSeat.do", "controller.booking.MemberBookingSelectSeatCommand");
		map.put("/memberBookingPayForm.do", "controller.booking.MemberBookingPayFormCommand");
		map.put("/memberExecuteBooking.do", "controller.booking.MemberExecuteBookingCommand");
		map.put("/memberBookingListCurr.do", "controller.booking.MemberBookingListCurrCommand");
		map.put("/memberBookingListPast.do", "controller.booking.MemberBookingListPastCommand");
		map.put("/memberBookingListCancel.do", "controller.booking.MemberBookingListCancelCommand");
		//예약 끝====================================================
	}
	
	
	public static CommandFactory getInstance() {
		return instance;
	}
	
	public Command createCommand(String uri) {  
		String commandClass = map.get(uri); 
		if(commandClass == null) {
			return null;
		}		
		try {
			Class obj = Class.forName(commandClass);
			Command command = (Command)obj.newInstance();
			return command;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}

}


















