package notification;

import observable.Observable;
import observable.Observer;

public abstract class Notification {
	
	protected Observable observable;
	
	public abstract void run(Observer observer);

}