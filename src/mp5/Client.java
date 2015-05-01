package mp5;

import java.security.SecureRandom;
import java.util.Scanner;

public class Client {
	private static TLS tls_system;
	private static EW  ew_sensor;

	
	private static Scanner input;
	private static String userInput;
	private static boolean userMode;

	
	public static void menu(int menuType){
		if (menuType == 0){
			System.out.println("A - Automated Simulator " );
			System.out.println("U - User Simulator");
			System.out.println("E - Exit");			
		}
		else if(menuType == 1){
			System.out.println("C - Set off East-West car sensor" );
			System.out.println("T - Get current time on traffic light");
			System.out.println("E - Exit" );
		}
		
		else if(menuType == 2){
			System.out.println("T - Get current time on traffic light");
			System.out.println("E - Exit" );			
		}
	
	}
	
	public static void simulator(boolean userMode){
		boolean simExit = false;
		boolean randomCar;
		SecureRandom random = new SecureRandom();
		
		if(userMode == true)
			menu(1);
		
		else 
			menu(2);
		
		
		while(!simExit){
			//if(input.hasNextLine()){
				System.out.print("input:");
				userInput = input.nextLine();
				userInput.toLowerCase();
				
				if(userInput.equals("c")&&userMode == true){
					ew_sensor.carSensor(true);
				}
				
				else if (userInput.equals("e"))
					simExit = true;	
				else if (userInput.equals("t"))
					tls_system.time();
				
			/*}
			
			else if(userMode == false){
				randomCar = random.nextBoolean();
				ew_sensor.carSensor(randomCar);	
			}*/
			
			
		}
		
		
		
		
	}
	
	
	public static void main(String[] args) {
		boolean exit = false;
		
		tls_system = new TLS();
		ew_sensor  = new EW();
		tls_system.register(ew_sensor);
		ew_sensor.start();
		tls_system.start();
		//tls_system.run();
		
		input = new Scanner(System.in);
		
			menu(0);
			
		while(!exit){
			System.out.print("Input:");
			userInput = input.nextLine();
			userInput.toLowerCase();
			
			if(userInput.equals("a")){
				userMode = false;
				simulator(userMode);
				
			}
			else if(userInput.equals("u")){
				userMode = true;
				simulator(userMode);
			}
			
			else if(userInput.equals("e")){
				input.close();
				exit = true;
			}
			
		}

	}

}