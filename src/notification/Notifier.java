package notification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Notifier {
	
	protected HashMap<Class<? extends Notification>, List<Object>> index;
	
	
	protected HashMap<Class<? extends Notification>, List<NotificationRegistration>> index2;
	
	public Notifier() {
		this.index = new HashMap<Class<? extends Notification>, List<Object>>();
	}

	public void addListener(Class<? extends Notification> notification, SatelliteListener s) {
		if (this.index.get(notification) == null) {
			List<Object> listO = new ArrayList<Object>();
			listO.add(s);
			this.index.put(notification, listO);
			System.out.println("Ajout first");
		}
		else {
			if (!this.index.get(notification).contains(s)) {
				System.out.println("Ajout");
				this.index.get(notification).add(s);
			}
		}
	}
	
	public void removeListener(Class<? extends Notification> notification, SatelliteListener s) {
		this.index.get(notification).remove(s);
		System.out.println("Remove");
	}
	
	public void sendNotification(Notification n) {
		if (this.index.get(n.getClass())!=null) {
			List<Object> listO = this.index.get(n.getClass());
			for (int i = 0 ; i < listO.size() ; i++) {
				n.run(listO.get(i));
			}
		}
	}
}
