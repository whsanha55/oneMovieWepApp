package controller;

import java.util.HashMap;

public class CommandFactory {
	//ΩÃ±€≈Ê ∆–≈œ
	private static CommandFactory instance = new CommandFactory();
	private HashMap<String, String> map = new HashMap<String, String>();
	
	private CommandFactory() {
		
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


















