package mp5;

import java.lang.InterruptedException;

public class TLS extends Thread implements Observer {
	protected volatile lightState nsState;
	protected volatile lightState ewState;
	private volatile boolean running;
	private subject ewSensor;
	private TLS_Clock stopWatch;
	private double time;

	public TLS() {
		nsState = lightState.green;
		ewState = lightState.red;
		stopWatch = new TLS_Clock();
	}

	public void register(subject newEWSensor) {
		this.ewSensor = newEWSensor;
		ewSensor.add(this);
	}

	public void nsState(lightState newState) {
		nsState = newState;
	}

	public void run() {
		running = true;
		stopWatch.start();

		while (running) {
			time = stopWatch.elapsedTime();
			if ((nsState == lightState.red && ewState == lightState.green)&& (time >= (20.0))) {
				ewState = lightState.amber;
				System.out.println();
				System.out.println("East-West Stoplight: Amber");
				stopWatch.stop();
				stopWatch.start();
			} else if (ewState == lightState.amber
					&& nsState == lightState.red && time >= (3.0)) {
				nsState = lightState.green;
				ewState = lightState.red;
				System.out.println();
				System.out.println("North-South Stoplight: Green");
				System.out.println("East-West Stoplight: Red");
				stopWatch.stop();
				stopWatch.start();
			}

			else if ((nsState == lightState.green && ewState == lightState.triggered)
					&& time >= (30.0)) {
				nsState = lightState.amber;
				System.out.println();
				System.out.println("North-South Stoplight: Amber");
				stopWatch.stop();
				stopWatch.start();
			} else if (nsState == lightState.amber && time >= 3.0 && ewState == lightState.triggered) {
				ewState = lightState.green;
				nsState = lightState.red;
				System.out.println();
				System.out.println("East-West Stoplight: Green");
				System.out.println("North-South Stoplight: Red");
				stopWatch.stop();
				stopWatch.start();
			}

			/*
			 * if(time%5==0) System.out.println("Seconds:"+time);
			 */
		}

	}
	public void stopRunning(){
		running = false;
	}

	public synchronized void update(Boolean carSensor) {

		if (carSensor == true && ewState == lightState.red) {
			System.out.println();
			System.out.println("East-West Stoplight: Cars detected");
			ewState = lightState.triggered;
		}

	}

	public void time() {
		time = stopWatch.elapsedTime();
		System.out.println("Seconds:" + time);
	}

}
