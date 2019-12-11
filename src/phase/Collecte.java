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
		if (!b.isMemoryFull()) {
			if (ThreadLocalRandom.current().nextInt(0, 5 + 1) == 1)
				b.addData(ThreadLocalRandom.current().nextInt(0, 50 + 1));
		}
	}
	
	@Override
	public Phase nextPhase(Balise b) {
		//Si la mémoire est pleine on passe en phase de synchsat afin de remonter en surface
		if (b.isMemoryFull()) {
			return new SynchSat(this.deplacement);
		}

		//en phase de collecte le mouvement est infinie, changement de phase seulement si la mémoire est pleine
		return this;
	}
}