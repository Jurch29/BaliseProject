package notification;

import java.util.ArrayList;

public class Notifier {
	
	protected ArrayList<NotificationReceiver> receiver;
	
	public Notifier() {
		this.receiver = new ArrayList<NotificationReceiver>();
	}

	public ArrayList<NotificationReceiver> getReceiver() {
		return receiver;
	}

	public void setReceiver(ArrayList<NotificationReceiver> receiver) {
		this.receiver = receiver;
	}
	
	public void addReceiver(NotificationReceiver r) {
		if (!this.receiver.contains(r))
			this.receiver.add(r);
	}

	public void removeReceiver(NotificationReceiver r) {
		this.receiver.remove(r);
	}
	
	public void sendNotification(Notification n) {
		for (int i = 0 ; i < this.receiver.size() ; i++) {
			this.receiver.get(i).receive(n);
		}
	}
}
