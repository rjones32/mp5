package mp5;

import java.security.SecureRandom;
import java.util.ArrayList;

public class EW extends Thread implements subject {
	
	protected volatile boolean carSensor;
	protected volatile boolean running;
	protected ArrayList<Observer> TLS_System;
	private SecureRandom random;
	
	public EW(){
		random     = new SecureRandom();
		carSensor  = false;
		TLS_System = new ArrayList<Observer>();
	}

	public void carSensor(boolean newState){carSensor = newState;}
	
	public boolean carSensor(){return carSensor;}
	
	public void add(Observer o) {
		TLS_System.add(o);
	}


	public void remove(Observer o) {
		int index = TLS_System.lastIndexOf(o);
		
		if(index>0)
			TLS_System.remove(index);
	}

	
	public synchronized void notifyObserver() {
		for(Observer o:TLS_System){
			o.update(carSensor);
		}
		
	}
	
	public void run(){
		running = true;
		while(running){		
			if(carSensor()==true){
				//System.out.println("east-west sensor has been triggered");
				notifyObserver();
				carSensor(false);
			}	
			
			carSensor(random.nextBoolean());	
		}
	}
	
	public void stopRunning(){running = false;}
	
}
