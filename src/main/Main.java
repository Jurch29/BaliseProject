package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;

import deplacement.Direction;
import deplacement.Horizontale;
import deplacement.Verticale;
import graphicLayer.GRect;
import graphicLayer.GSpace;
import model.Balise;
import model.Satellite;
import phase.Collecte;
import tools.GlobaleVariable;
import tools.Tools;
import vue.BaliseFrame;

public class Main {

	public static void main(String[] args) {
		
		ArrayList<Satellite> sats = new ArrayList<Satellite>();
		ArrayList<Balise> balises = new ArrayList<Balise>();
		
		System.out.println("Nombre de satellite ?");
		int nbSat = Integer.parseInt(Tools.readKeyboard());
		
		System.out.println("Nombre de balises ?");
		int nbBalise = Integer.parseInt(Tools.readKeyboard());
		
		BaliseFrame app = new BaliseFrame();
		
		int ecart = GlobaleVariable.sizeX/nbSat;
		for (int i = 0 ; i < nbSat ; i++) {
			Satellite sat = new Satellite(new Point(ecart*i,10), app);
			sats.add(sat);
			app.addSatellite(sat, new Point(ecart*i,10));
		}
		
		Point p = new Point(300,GlobaleVariable.sizeY-GlobaleVariable.hauteurMer-5);
		Balise balise = new Balise(p, app);
		balise.setDep(new Horizontale(15, Direction.Gauche));
		balise.addProfondeur(20);
		balise.addAllSats(sats);
		balises.add(balise);
		app.addBalise(balise, p);
		
		p = new Point(100,GlobaleVariable.sizeY-GlobaleVariable.hauteurMer-5);
		Balise balise1 = new Balise(p, app);
		balise1.setDep(new Verticale(15, Direction.Bas));
		balise1.addProfondeur(20);
		balise1.addAllSats(sats);
		balises.add(balise1);
		app.addBalise(balise1, p);
		
//		for (int i = 0 ; i < nbBalise ; i++) {
//			Point p = new Point(300,GlobaleVariable.sizeY-GlobaleVariable.hauteurMer-5);
//			Balise balise = new Balise(p, app);
//			balise.setDep(new Horizontale(15, Direction.Gauche));
//			balise.addProfondeur(20);
//			balise.addAllSats(sats);
//			balises.add(balise);
//			app.addBalise(balise, p);
//		}
		
		for (int i = 0 ; i < sats.size() ; i++) {
			sats.get(i).start();
		}
		for (int i = 0 ; i < balises.size() ; i++) {
			balises.get(i).start();
		}
	}
}