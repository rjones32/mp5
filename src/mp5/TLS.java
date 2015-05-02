package mp5;


public class TLS extends Thread implements Observer {
	protected volatile lightState nsState;
	protected volatile lightState ewState;
	private volatile boolean running;
	private subject ewSensor;
	private TLS_Clock stopWatch;
	private double time;

	//Constructor for the TLS 
	//sets the initial states of NS stop lights and EW stop lights
	public TLS() {
		nsState = lightState.green;
		ewState = lightState.red;
		stopWatch = new TLS_Clock();
	}

	//method to register a subject
	public void register(subject newEWSensor) {
		this.ewSensor = newEWSensor;
		ewSensor.add(this);
	}

	//method to set a new state for the NS stop light
	public void nsState(lightState newState) {
		nsState = newState;
	}
	//method to run the thread
	public void run() {
		running = true;
		stopWatch.start();
		//continue running till it is false
		while (running) {
			//gets the elapsed time
			time = stopWatch.elapsedTime();
			//if nsState is red and ewState is green and time is greater than 20 seconds than change ewState to amber
			if ((nsState == lightState.red && ewState == lightState.green)&& (time >= (20.0))) {
				ewState = lightState.amber;
				System.out.println();
				System.out.println("East-West Stoplight: Amber");
				stopWatch.stop();
				stopWatch.start();
			} 
			//if ewState is amber and nsState is red and time is greater than 3 seconds
			//Change nsState to green and ewState to red
			else if (ewState == lightState.amber&& nsState == lightState.red && time >= (3.0)) {
				nsState = lightState.green;
				ewState = lightState.red;
				System.out.println();
				System.out.println("North-South Stoplight: Green");
				System.out.println("East-West Stoplight: Red");
				stopWatch.stop();
				stopWatch.start();
			}
			//if nsState is green and weState is triggered and time is greater than 30 seconds
			//change nsState to amber
			else if ((nsState == lightState.green && ewState == lightState.triggered)&& time >= (30.0)) {
				nsState = lightState.amber;
				System.out.println();
				System.out.println("North-South Stoplight: Amber");
				stopWatch.stop();
				stopWatch.start();
			} 
			//if nsState is amber and ewState is triggered and time is greater than 3 seconds
			//change ewState to green and nsState to red
			else if (nsState == lightState.amber && time >= 3.0 && ewState == lightState.triggered) {
				ewState = lightState.green;
				nsState = lightState.red;
				System.out.println();
				System.out.println("East-West Stoplight: Green");
				System.out.println("North-South Stoplight: Red");
				stopWatch.stop();
				stopWatch.start();
			}

			
		}

	}
	public void stopRunning(){
		running = false;
	}
	//method to get updated status of the East-west sensor 
	public synchronized void update(Boolean carSensor) {

		if (carSensor == true && ewState == lightState.red) {
			System.out.println();
			System.out.println("East-West Stoplight: Cars detected");
			ewState = lightState.triggered;
		}

	}
	//method to get current elapsedTime for the TLS_System
	public void time() {
		time = stopWatch.elapsedTime();
		System.out.println("Seconds:" + time);
	}

}
