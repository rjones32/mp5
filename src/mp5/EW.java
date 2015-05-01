package mp5;

import java.util.ArrayList;
import java.util.Iterator;

public class EW extends Thread implements subject {
	
	protected boolean carSensor;
	protected ArrayList<Observer> TLS_System;
	
	public EW(){
		carSensor  = false;
		TLS_System = new ArrayList<Observer>();
	}

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
		while(true){
			if(carSensor()==true){
				//System.out.println("east-west sensor has been triggered");
				notifyObserver();
				carSensor(false);
			}
		}
	}
	
	public void carSensor(boolean newState){carSensor = newState;}
	
	public boolean carSensor(){return carSensor;}

	
	

}
