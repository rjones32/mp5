package mp5;

public class EW extends Thread implements subject {
	protected lightState ewState;
	protected boolean carSensor;
	
	public EW(){
		ewState   = lightState.red;
		carSensor = false;
		
	}
	@Override
	public void add(Observer o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Observer o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyObserver() {
		// TODO Auto-generated method stub
		
	}
	
	public void run(){
		
		
	
	}
	
	public void ewState(lightState newState){ ewState = newState;}
	public void carSensor(boolean newState){ carSensor = newState;}
	public lightState ewState(){return ewState;}
	public boolean carSesnor(){return carSensor;}

	
	

}
