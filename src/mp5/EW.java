package mp5;

import java.security.SecureRandom;
import java.util.ArrayList;

public class EW extends Thread implements subject {
	
	protected volatile boolean carSensor;
	protected volatile boolean running;
	protected ArrayList<Observer> TLS_System;
	private SecureRandom random;
	private TLS_Clock  stopWatch;
	private double     time;
	
	//Constructor that setup the variable for the thread 
	public EW(){
		random     = new SecureRandom();
		carSensor  = false;
		TLS_System = new ArrayList<Observer>();
	}

	//setter for carSensor
	public void carSensor(boolean newState){carSensor = newState;}
	//getter for carSensor 
	public boolean carSensor(){return carSensor;}
	
	//method to add observer to EW thread
	public void add(Observer o) {
		TLS_System.add(o);
	}

	//method to remove a Observer to EW thread
	public void remove(Observer o) {
		int index = TLS_System.lastIndexOf(o);
		
		if(index>0)
			TLS_System.remove(index);
	}

	//method to notify the TLS_System that there is a change 
	public synchronized void notifyObserver() {
		for(Observer o:TLS_System){
			o.update(carSensor);
		}
		
	}
	
	//method for the thread to start running 
	public void run(){
		running = true;
		stopWatch = new TLS_Clock();
		stopWatch.start();
		boolean randomCar;
		
		while(running){		
			time = stopWatch.elapsedTime();
			if(carSensor()==true){
				notifyObserver();
				carSensor(false);
			}	
			
			if(time%5==0){
				randomCar = random.nextBoolean();
				carSensor(randomCar);
			}
		}
	}
	
	//method to stop the thread for running 
	public void stopRunning(){running = false;}
	
}
