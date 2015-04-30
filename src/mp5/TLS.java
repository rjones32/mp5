package mp5;

import java.time.Duration;
import java.lang.Object;

public class TLS extends Thread implements Observer{
	lightState nsState;
	lightState EW;
	
	public TLS(){
		nsState = lightState.green;
		EW      = lightState.red;
	}
	
	
	
	
	public void nsState(lightState newState){ nsState = newState;}

	public void update(lightState newEW) {EW = newEW;}




	
	
	
	
	

}
