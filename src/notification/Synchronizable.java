package notification;

public interface Synchronizable extends NotificationReceiver {

	public void tryToSynchronizeWith(Object o);
	
}
