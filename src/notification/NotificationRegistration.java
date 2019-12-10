package notification;

import java.lang.reflect.Method;

public class NotificationRegistration {
	
	public NotificationRegistration(Object o, Method m) {
		this.observer = o;
		this.method = m;
	}

	protected Object observer;
	protected Method method;
	
	public Object getObserver() {
		return observer;
	}
	public void setObserver(Object observer) {
		this.observer = observer;
	}
	public Method getMethod() {
		return method;
	}
	public void setMethod(Method method) {
		this.method = method;
	}
}
