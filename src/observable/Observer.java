package observable;

import notification.Notification;

public interface Observer {
	
	public void receive(Notification n);
	
}