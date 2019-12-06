package notification;

import observable.Observable;
import observable.Observer;

public class Synchronized extends Notification {
	
	public Synchronized(Observable o) {
		// TODO Auto-generated constructor stub
		this.observable = o;
	}

	@Override
	public void run(Observer observer) {
		// TODO Auto-generated method stub
		
	}
}