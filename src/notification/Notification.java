package notification;

import tools.Observable;
import tools.Observer;

public abstract class Notification {
	
	protected Observable observable;
	
	public abstract void run(Observer observer);

}