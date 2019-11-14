package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;

import deplacement.Direction;
import deplacement.Horizontale;
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
		
		for (int i = 0 ; i < nbBalise ; i++) {
			Point p = new Point(300,GlobaleVariable.sizeY-GlobaleVariable.hauteurMer-5);
			Balise balise = new Balise(p, app);
			balise.setPhase(new Collecte(new Horizontale(15, Direction.Bas)));
			balise.addProfondeur(15);
			balises.add(balise);
			app.addBalise(balise, p);
		}
		
		for (int i = 0 ; i < sats.size() ; i++) {
			sats.get(i).start();
		}
		for (int i = 0 ; i < balises.size() ; i++) {
			balises.get(i).start();
		}
		
	}

//	private static void premiereVersion(int nbSat, int nbBalise) {

//		ArrayList<Satellite> sats = new ArrayList<Satellite>();
//		ArrayList<Balise> balises = new ArrayList<Balise>();
//		
//		GSpace w = new GSpace("Baliseur", new Dimension(GlobaleVariable.sizeX, GlobaleVariable.sizeY));
//		w.open();
//
//		GRect mer = new GRect();
//		mer.setColor(Color.blue);
//		mer.setPosition(new Point(0,GlobaleVariable.sizeY-GlobaleVariable.hauteurMer));
//		mer.setDimension(new Dimension(GlobaleVariable.sizeX,GlobaleVariable.hauteurMer));
//		w.addElement(mer);
//		
//		int ecart = GlobaleVariable.sizeX/nbSat;
//		
//		for (int i = 0 ; i < nbSat ; i++) {
//			GRect satellite = new GRect();
//			satellite.setColor(Color.red);
//			satellite.setPosition(new Point(ecart*i,10));
//			satellite.setDimension(new Dimension(10,10));
//			satellite.setBorderWidth(2);
//			w.addElement(satellite);
//			Satellite sat = new Satellite(satellite);
//			sats.add(sat);
//		}
//		
//		GOval baliseDraw1 = new GOval();
//		baliseDraw1.setPosition(new Point(300,GlobaleVariable.sizeY-GlobaleVariable.hauteurMer-5));
//		baliseDraw1.setDimension(new Dimension(10,10));
//		baliseDraw1.setColor(Color.black);
//		w.addElement(baliseDraw1);
//		Balise balise1 = new Balise(baliseDraw1);
//		balise1.addProfondeur(10);
//		balises.add(balise1);
////		
//		GOval baliseDraw2 = new GOval();
//		baliseDraw2.setPosition(new Point(300,GlobaleVariable.sizeY-GlobaleVariable.hauteurMer-5));
//		baliseDraw2.setDimension(new Dimension(10,10));
//		baliseDraw2.setColor(Color.black);
//		w.addElement(baliseDraw2);
//		Balise balise2 = new Balise(baliseDraw2);
//		balise2.addProfondeur(30);
//		balises.add(balise2);
//		
//		for (int i = 0 ; i < nbBalise ; i++) {
//			GOval baliseDraw = new GOval();
//			baliseDraw.setPosition(new Point(300,GlobaleVariable.sizeY-GlobaleVariable.hauteurMer-5));
//			baliseDraw.setDimension(new Dimension(10,10));
//			baliseDraw.setColor(Color.black);
//			w.addElement(baliseDraw);
//			Balise balise = new Balise(baliseDraw);
//			balise.addProfondeur(10);
//			balises.add(balise);
//		}
//		
//	
//		while (true) {
//			Tools.sleep(GlobaleVariable.vitesseSimulation);
//			for (int i = 0 ; i < sats.size() ; i++) {
//				sats.get(i).step();
//			}
//			for (int i = 0 ; i < balises.size() ; i++) {
//				balises.get(i).step();
//			}
//		}
//	}
	
//	public static void secondeversion() {
//		ArrayList<Satellite> sats = new ArrayList<Satellite>();
//		ArrayList<Balise> balises = new ArrayList<Balise>();
//		
//		System.out.println("Nombre de satellite ?");
//		int nbSat = Integer.parseInt(Tools.readKeyboard());
//		
//		System.out.println("Nombre de balises ?");
//		int nbBalise = Integer.parseInt(Tools.readKeyboard());
//		
//		BaliseFrame app = new BaliseFrame();
//		
//		int ecart = GlobaleVariable.sizeX/nbSat;
//		for (int i = 0 ; i < nbSat ; i++) {
//			Satellite sat = new Satellite(new Point(ecart*i,10));
//			sats.add(sat);
//			app.addSatellite(sat, new Point(ecart*i,10));
//		}
//		
//		for (int i = 0 ; i < nbBalise ; i++) {
//			Point p = new Point(300,GlobaleVariable.sizeY-GlobaleVariable.hauteurMer-5);
//			Balise balise = new Balise(p);
//			balises.add(balise);
//			balise.addProfondeur(15);
//			app.addBalise(balise, p);
//		}
//		
//		while (true) {
//			Tools.sleep(GlobaleVariable.vitesseSimulation);
//			for (int i = 0 ; i < sats.size() ; i++) {
//				sats.get(i).run();
//			}
//			for (int i = 0 ; i < balises.size() ; i++) {
//				balises.get(i).run();
//			}
//			app.update();
//		}
//	}
	
	/*Observable : -register/unregister des observers
	 * 			   -il envoie un message aux Observers quand la propri�t� qui interesse les observer est modifi�
	 * 			   ex : �tat "synchronisable"/"synchronis�"
	 * 
	 * Observer : il est capable de se mettre � jour par update (Observable o)
	 *
	 *Class Balise{
	 *
	 *void update(object source){
	 *if (ensurface+enfenetreSat)
	 *synchroAvec(source)
	 *}
	 *
	 *Class Satellite{
	 *void avance(){
	 *vable.notifyObserver(this);
	 *}
	 */
	
}