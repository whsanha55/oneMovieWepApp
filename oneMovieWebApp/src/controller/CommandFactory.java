package controller;

import java.util.HashMap;

public class CommandFactory {
	// ΩÃ±€≈Ê ∆–≈œ
	private static CommandFactory instance = new CommandFactory();
	private HashMap<String, String> map = new HashMap<String, String>();

	private CommandFactory() {
		map.put("/adminBooking1.do", "controller.AdminBooking1Command");
		map.put("/adminBookingAjax.do", "controller.AdminBookingAjaxCommand");
		map.put("/adminBookingAjax2.do", "controller.AdminBookingAjax2Command");
		map.put("/memberBooking1.do", "controller.memberBookingCommand");
		map.put("/user/movie/listMovie.do", "controller.movie.ListMovieCommand");
		map.put("/user/movie/findMovie.do", "controller.movie.FindMovieCommand");
		map.put("/user/movie/writeMovieForm.do", "controller.movie.WriteMovieFormCommand");
		map.put("/user/movie/writeMovie.do", "controller.movie.WriteMovieCommand");
		map.put("/removeMovieForm.do", "controller.RemoveMovieFormCommand");
		map.put("/removeMovie.do", "controller.RemoveMovieCommand");

	}

	public static CommandFactory getInstance() {
		return instance;
	}

	public Command createCommand(String uri) {
		String commandClass = map.get(uri);
		if (commandClass == null) {
			return null;
		}
		try {
			Class obj = Class.forName(commandClass);
			Command command = (Command) obj.newInstance();
			return command;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
