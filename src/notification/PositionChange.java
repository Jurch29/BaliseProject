package notification;

public class PositionChange extends Notification {

	public PositionChange(Object o) {
		// TODO Auto-generated constructor stub
		this.source = o;
	}

	@Override
	public void run(Object o) {
		// TODO Auto-generated method stub
		((SatelliteListener) o).whenSatellitePositionChanged(this);
	}
}
