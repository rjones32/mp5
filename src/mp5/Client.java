package mp5;

import java.util.Scanner;

public class Client {
	private static TLS tls_system;
	private static EW  ew_sensor;

	
	private static Scanner input;
	private static String userInput;

	//method to display the menu 
	public static void menu(int menuType){
		if (menuType == 0){
			System.out.println("-------------TLS Simulator------------");
			System.out.println("S - Start simulator");
			System.out.println("? - Display menu");
			System.out.println("E - Exit");			
		}
		else if(menuType == 1){
			System.out.println("-------------Simulator Commands------------");
			System.out.println("T - Get current time on traffic light");
			System.out.println("? - Display menu");
			System.out.println("E - Exit" );
		}
		
		
	
	}
	//method to run the TLS simulator 
	public static void simulator(/*boolean userMode*/){
		boolean simExit = false;
		//Initialized the TLS_System and ew_sensor 
		tls_system = new TLS();
		ew_sensor  = new EW();
		//register the ew_sensor to the tls_system
		tls_system.register(ew_sensor);
		//start the thread for ew_sensor and tls_system
		ew_sensor.start();
		tls_system.start();
		
		menu(1);
		//start the simulator and does not exit till user hits e
		while(!simExit){
				userInput = input.nextLine();
				userInput = userInput.toLowerCase();
				
				
				//exit simulator 
			    if (userInput.equals("e")){
			    	tls_system.stopRunning();
			    	ew_sensor.stopRunning();
					simExit = true;	
			    }
				//display tls_system time
				else if (userInput.equals("t"))
					tls_system.time();
				//display the menu
				else if (userInput.equals("?")/*&&userMode==true*/)
					 menu(1);
		}
	}
	
	
	public static void main(String[] args) {
		boolean exit = false;
				
		input = new Scanner(System.in);
		
		menu(0);
		while(!exit){
			System.out.print("Input:");
			userInput = input.nextLine();
			userInput = userInput.toLowerCase();
			
			if(userInput.equals("s")){
				simulator();
				System.out.println();
				
			}
			else if(userInput.equals("?"))
				menu(0);
			
			else if(userInput.equals("e")){
				input.close();
				exit = true;
			}
			
		}

	}

}
