package notification;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Notifier {
	
	protected HashMap<Class<? extends Notification>, List<NotificationRegistration>> index;
	
	public Notifier() {
		this.index = new HashMap<Class<? extends Notification>, List<NotificationRegistration>>();
	}
	
	public void addListener(Class<? extends Notification> notification, NotificationRegistration nr) throws NoSuchMethodException, SecurityException {
		if (this.index.get(notification) == null) {
			List<NotificationRegistration> listNR = new ArrayList<NotificationRegistration>();
			listNR.add(nr);
			this.index.put(notification, listNR);
		}
		else {
			if (!this.index.get(notification).contains(nr)) {
				synchronized (this.index.get(notification)) {
					this.index.get(notification).add(nr);
				}
			}
		}
	}
	
	public boolean removeListener(Class<? extends Notification> notification, NotificationRegistration nr) {
		if (this.index.get(notification)!=null) {
			synchronized (this.index.get(notification)) {
				return this.index.get(notification).remove(nr);
			}
		}
		return false;
	}
	
	public void sendNotification(Notification n) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		List<NotificationRegistration> nrList = this.index.get(n.getClass());
		if (nrList != null && nrList.size()!=0) {
			synchronized (nrList) {
				for (int i = 0 ; i < nrList.size() ; i++) {
					Method m = nrList.get(i).getMethod();
					Object o = nrList.get(i).getObserver();
					m.invoke(o, n);
				}
			}
		}
	}
}
