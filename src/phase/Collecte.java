package phase;

import java.util.concurrent.ThreadLocalRandom;
import deplacement.Deplacement;
import model.Balise;

public class Collecte extends Phase {
	
	public Collecte(Deplacement d) {
		this.deplacement = d;
	}

	@Override
	public void step(Balise b) {
		//TODO Auto-generated method stub
		if (deplacement!=null) {
			b.setPosition(this.deplacement.getNextPoint(b.getPosition()));
		}
		if (!b.isMemoryFull())
			b.addData(ThreadLocalRandom.current().nextInt(0, 50 + 1));
	}
	
	@Override
	public Phase nextPhase(Balise b) {
		if (b.isMemoryFull()) {   //Si condition sortie
			System.out.println("Passage en SynchSat");
			b.synchroReady();
			return new SynchSat();
		}
		if (this.deplacement.isDeplacementFini())
			return new Mouvement(null);
		
		return this;
	}
}