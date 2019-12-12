package phase;

import java.util.concurrent.ThreadLocalRandom;
import deplacement.Deplacement;
import model.Balise;
import tools.GlobaleVariable;

public class Collecte extends Phase {
	
	public Collecte(Deplacement d) {
		this.deplacement = d;
	}

	@Override
	public void step(Balise b) {
		//On collecte et on bouge si la mémoire n'est pas pleine
		if (!b.isMemoryFull()) {
			if (deplacement!=null) {
				b.setPosition(this.deplacement.getNextPoint(b.getPosition()));
			}
			if (ThreadLocalRandom.current().nextInt(0, GlobaleVariable.difficulteCollecte + 1) == 1)
				b.addData(ThreadLocalRandom.current().nextInt(0, 50 + 1));
		}
	}
	
	@Override
	public Phase nextPhase(Balise b) {
		//Si la memoire est pleine on passe en phase de synchsat afin de remonter en surface
		if (b.isMemoryFull()) {
			return new SynchSat();
		}

		//en phase de collecte le mouvement est infinie, changement de phase seulement si la m�moire est pleine
		return this;
	}
}