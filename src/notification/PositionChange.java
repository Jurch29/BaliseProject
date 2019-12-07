package notification;

public class PositionChange extends Notification {

	public PositionChange(Object o) {
		// TODO Auto-generated constructor stub
		this.source = o;
	}

	@Override
	public void run(Synchronizable s) {
		// TODO Auto-generated method stub
		s.tryToSynchronizeWith(this.source);
	}
}
