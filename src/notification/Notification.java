package notification;

public abstract class Notification {
	
	protected Object source;
	
	public Object getSource() {
		return source;
	}

	public void setSource(Object source) {
		this.source = source;
	}

}