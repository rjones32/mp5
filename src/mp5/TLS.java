package mp5;

import java.time.Duration;
import java.lang.Object;

public class TLS extends Thread implements Observer{
	protected lightState nsState;
	protected lightState ewState;
	private   subject    ewSensor;
	private   TLS_Clock  stopWatch;
	private   double     time;
	
	public TLS(){
		nsState      = lightState.green;
		ewState      = lightState.red;
		stopWatch    =  new TLS_Clock();
		stopWatch.start();
	}
	
	public void register(subject newEWSensor){
		this.ewSensor = newEWSensor;
		ewSensor.add(this);
	}
	
	
	
	public void nsState(lightState newState){ nsState = newState;}

	public void run(){
		boolean statusChanged = false;
		while(true){
			time = stopWatch.elapsedTime();
			if(nsState==lightState.red&&ewState==lightState.green&&time>=(30.0)){
				ewState = lightState.amber;
				System.out.println("East-South Stoplight: Amber");
				stopWatch.stop();
				stopWatch.start();
			}
			else if(ewState==lightState.amber && nsState == lightState.red&&time>=(5.0)){
				nsState = lightState.green;
				ewState = lightState.red;
				System.out.println("North-South Stoplight: Green");
				System.out.println("East-West Stoplight: Red");
				stopWatch.stop();
				stopWatch.start();
				statusChanged = false;
			}
				
			if((nsState==lightState.green&&ewState==lightState.triggered)&&time>=(30.0)){
				nsState = lightState.amber;
				System.out.println("North-South Stoplight: Amber");
				stopWatch.stop();
				stopWatch.start();
			}
			else if(nsState==lightState.amber && time>=5.0 && ewState == lightState.triggered){
				ewState = lightState.green;
				nsState = lightState.red;
				System.out.println("East-West Stoplight: Green");
				System.out.println("North-South Stoplight: Red");
				stopWatch.stop();
				stopWatch.start();
			}
			else if(ewState == lightState.triggered && statusChanged == false){
				System.out.println();
				System.out.println("East-West Stoplight: car detected");
				statusChanged = true;
			}
			
			
			
		
		/*if(time%5==0)
			System.out.println("Seconds:"+time);*/
			
		}

	}
	
	public synchronized void update(Boolean carSensor) {
			
		if(carSensor==true)
				ewState = lightState.triggered;		
		
	}
	
	public void time(){
		time = stopWatch.elapsedTime();
		System.out.println("Seconds:"+time);
	}
	

	}
