package phase;

import deplacement.Deplacement;
import model.Balise;

public class Mouvement extends Phase { //phase de mouvement seule (si d=null balise au repos total))
	
	public Mouvement(Deplacement d) {
		// TODO Auto-generated constructor stub
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
		
		//après un mouvement on rentre en collecte
		if (this.deplacement!=null) {
			if (this.deplacement.isDeplacementFini())
				return new Collecte(b.getDep());
		}
		
		return this;
	}
}