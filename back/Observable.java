package back;

public interface Observable {
    public void addObserver(Observer obs);
	public void removeObderver(Observer obs);
	public void notifyObservers();
}
