package phase;

import java.util.concurrent.ThreadLocalRandom;
import deplacement.Deplacement;
import model.Balise;

public class Collecte extends Phase {
	
	private Deplacement deplacement;
	
	public Collecte(Deplacement d) {
		this.deplacement = d;
	}

	@Override
	public void step(Balise b) {
		//TODO Auto-generated method stub
		if (deplacement!=null) {
			b.setPosition(this.deplacement.getNextPoint(b.getPosition()));
		}
		b.addData(ThreadLocalRandom.current().nextInt(0, 50 + 1));
	}
	
	@Override
	public Phase nextPhase(Balise b) {
		if (b.isMemoryFull()) {   //Si condition sortie
			return new SynchSat();
		}
		return this;
	}
	
}