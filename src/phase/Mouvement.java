package phase;

import deplacement.Deplacement;
import model.Balise;

public class Mouvement extends Phase { //phase de mouvement seule (si d=null balise au repos total))
	
	public Mouvement(Deplacement d) {
		this.deplacement = d;
	}

	@Override
	public void step(Balise b) {
		// TODO Auto-generated method stub
		if (deplacement!=null) {
			b.setPosition(this.deplacement.getNextPoint(b.getPosition()));
		}
	}
	
	@Override
	public Phase nextPhase(Balise b) {
		
		//à la fin d'un mouvement on passe en collecte
		if (this.deplacement!=null) {
			if (this.deplacement.isDeplacementFini())
				return new Collecte(b.getDep());
		}
		//le mouvement continu
		return this;
	}
}