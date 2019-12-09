package notification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Notifier {
	
	protected HashMap<Class<? extends Notification>, List<Object>> index;
	
	public Notifier() {
		this.index = new HashMap<Class<? extends Notification>, List<Object>>();
	}

	public void addListener(Class<? extends Notification> notification, SatelliteListener s) {
		if (this.index.get(notification) == null) {
			List<Object> listO = new ArrayList<Object>();
			listO.add(s);
			this.index.put(notification, listO);
		}
		else {
			this.index.get(notification).add(s);
		}
	}
	
	public void unregister() {
		
	}
	
	public void sendNotification(Notification n) {
		
	}
}
