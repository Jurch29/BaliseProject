package notification;

public abstract class Notification {
	
	protected Object source;
	
	public abstract void run(Object o);

}