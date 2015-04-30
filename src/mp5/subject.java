package mp5;

public interface subject {
	public void add(Observer o);
	public void remove(Observer o);
	public void notifyObserver();
}
