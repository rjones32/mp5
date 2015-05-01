package mp5;

public class TLS_Clock{
	
		private long startTime;
		private long endTime;
		private double elapsedTime;
		
		public void start(){
			startTime = System.nanoTime();
		}
		
		public double elapsedTime(){
			endTime     = System.nanoTime();
			elapsedTime = ((endTime - startTime)/1000000000.0);
			return elapsedTime;
		}
		
		public void stop(){ 
			elapsedTime = ((endTime - startTime)/1000000000.0);
			startTime   = 0;
			endTime     = 0;
		}
		

}
