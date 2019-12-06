package notification;

import model.Balise;
import model.Satellite;
import observable.Observable;
import observable.Observer;

public class PositionChange extends Notification {

	public PositionChange(Observable o) {
		// TODO Auto-generated constructor stub
		this.observable = o;
	}

	@Override
	public void run(Observer observer) {
		// TODO Auto-generated method stub
		if (((Balise)observer).getPosition().x>((Satellite) this.observable).getPosition().x-10 && ((Balise)observer).getPosition().x<((Satellite) this.observable).getPosition().x+10) {
			//On est dans une zone de réception du satellite (i)
			if (((Satellite) this.observable).lock()) {
				((Satellite) this.observable).addDataToMemory(((Balise)observer).getData());
				((Balise)observer).resetData();
				for (int i = 0 ; i < ((Balise)observer).getSats().size() ; i++) {
					((Balise)observer).getSats().get(i).unregister((observer));
				}
			}
		}
	}
}
