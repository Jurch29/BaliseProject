package observable;

import notification.Notification;

public interface Observable {

	public void notifyAll(Notification n);
	public void register(Observer o);
	public void unregister(Observer o);
	
}