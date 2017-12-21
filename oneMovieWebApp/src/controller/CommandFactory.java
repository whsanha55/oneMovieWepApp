package controller;

import java.util.HashMap;

public class CommandFactory {
	//ΩÃ±€≈Ê ∆–≈œ
	private static CommandFactory instance = new CommandFactory();
	private HashMap<String, String> map = new HashMap<String, String>();
	
	private CommandFactory() {
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


















