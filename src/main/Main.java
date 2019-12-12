package main;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import deplacement.Deplacement;
import deplacement.Direction;
import deplacement.Horizontale;
import deplacement.Libre;
import deplacement.Verticale;
import model.Balise;
import model.Satellite;
import notification.Notifier;
import tools.GlobaleVariable;
import tools.Tools;
import vue.BaliseFrame;

public class Main {

	public static void main(String[] args) {
		
		ArrayList<Satellite> sats = new ArrayList<Satellite>();
		ArrayList<Balise> balises = new ArrayList<Balise>();
		
		System.out.println("Nombre de satellite ?");
		int nbSat = Integer.parseInt(Tools.readKeyboard());
		
		BaliseFrame app = new BaliseFrame();
		Notifier notifier = new Notifier();
		
		int ecart = GlobaleVariable.sizeX/nbSat;
		for (int i = 0 ; i < nbSat ; i++) {
			Satellite sat = new Satellite(new Point(ecart*i,10), app, notifier);
			sats.add(sat);
			app.addSatellite(sat, new Point(ecart*i,10));
		}
		
		Point p = new Point(300,GlobaleVariable.sizeY-GlobaleVariable.hauteurMer-5);
		Balise balise = new Balise(p, app, notifier);
		balise.setDep(new Horizontale(15, Direction.Gauche));
		balise.addProfondeur(20);
		balises.add(balise);
		app.addBalise(balise, p);
		
		p = new Point(100,GlobaleVariable.sizeY-GlobaleVariable.hauteurMer-5);
		Balise balise1 = new Balise(p, app, notifier);
		balise1.setDep(new Verticale(15, Direction.Bas));
		balise1.addProfondeur(20);
		balises.add(balise1);
		app.addBalise(balise1, p);
		
		List<Deplacement> deplacements = new ArrayList<Deplacement>();
		deplacements.add(new Verticale(5,Direction.Bas));
		deplacements.add(new Horizontale(5,Direction.Droite));
		
		p = new Point(200,GlobaleVariable.sizeY-GlobaleVariable.hauteurMer-5);
		Balise balise2 = new Balise(p, app, notifier);
		balise2.setDep(new Libre(deplacements));
		balise2.addProfondeur(20);
		balises.add(balise2);
		app.addBalise(balise2, p);
		
		for (int i = 0 ; i < sats.size() ; i++) {
			sats.get(i).start();
		}
		for (int i = 0 ; i < balises.size() ; i++) {
			balises.get(i).start();
		}
	}
}

