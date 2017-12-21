package controller;

import java.util.HashMap;

public class CommandFactory {
	//ΩÃ±€≈Ê ∆–≈œ
	private static CommandFactory instance = new CommandFactory();
	private HashMap<String, String> map = new HashMap<String, String>();
	
	private CommandFactory() {
		map.put("/adminBooking1.do", "controller.booking.AdminBooking1Command");
		map.put("/adminBookingAjax.do", "controller.booking.AdminBookingAjaxCommand");
		map.put("/adminBookingAjax2.do", "controller.AdminBookingAjax2Command");
		map.put("/memberBooking1.do", "controller.memberBookingCommand");
		
		
		
		//theater part
		map.put("/admin/theater/adminTheaterForm.do","controller.theater.AdminTheaterFormCommand");
		map.put("/admin/theater/listScheduleTurnForm.do", "controller.theater.ListScheduleTurnCommand");
		map.put("/admin/theater/addTurnForm.do", "controller.theater.AddTurnFormCommand");
		map.put("/admin/theater/addScheduleForm.do", "controller.theater.AddScheduleFormCommand");
		map.put("/admin/theater/addSchedule.do", "controller.theater.AddScheduleTurnCommand");
		
		map.put("/admin/theater/adminTheaterView.do", "controller.theater.AdminTheaterSearchAjaxCommand");
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


















