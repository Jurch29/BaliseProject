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
	
	public void addListener(Class<? extends Notification> notification, Object o, String strMethod) throws NoSuchMethodException, SecurityException {
		Method m = o.getClass().getMethod(strMethod, Notification.class);
		NotificationRegistration nr = new NotificationRegistration(o, m);
		if (this.index.get(notification) == null) {
			List<NotificationRegistration> listNR = new ArrayList<NotificationRegistration>();
			listNR.add(nr);
			this.index.put(notification, listNR);
		}
		else {
			if (!this.index.get(notification).contains(nr)) {
				this.index.get(notification).add(nr);
			}
		}
	}
	
	public void removeListener(Class<? extends Notification> notification, Object o, String strMethod) {
		if (this.index.get(notification)!=null) {
			Method m = null;
			try {
				m = o.getClass().getMethod(strMethod, Notification.class);
			} catch (NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
			NotificationRegistration nr = new NotificationRegistration(o, m);
			
//			System.out.println(System.identityHashCode(m));
			
			this.index.get(notification).remove(nr);
		}
	}
	
	public void sendNotification(Notification n) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		List<NotificationRegistration> nr = this.index.get(n.getClass());
		if (nr != null) {
			for (int i = 0 ; i < nr.size() ; i++) {
				Method m = nr.get(i).getMethod();
				Object o = nr.get(i).getObserver();
				m.invoke(o, n);
			}
		}
	}
}
