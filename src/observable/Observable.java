package observable;

import notification.Notification;
import notification.Synchronizable;

public interface Observable {

	public void notifyAll(Notification n);
	public void register(Synchronizable s);
	public void unregister(Synchronizable s);
	
}