package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;

import graphicLayer.GOval;
import graphicLayer.GRect;
import graphicLayer.GSpace;
import model.Balise;
import model.Satellite;
import tools.GlobaleVariable;
import tools.Tools;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		System.out.println("Nombre de satellite ?");
//		int nbSat = Integer.parseInt(Tools.readKeyboard());
		
//		System.out.println("Nombre de balises ?");
//		int nbBalise = Integer.parseInt(Tools.readKeyboard());
		
		runWorld(1,3);
	}

	private static void runWorld(int nbSat, int nbBalise) {
		// TODO Auto-generated method stub
		ArrayList<Satellite> sats = new ArrayList<Satellite>();
		ArrayList<Balise> balises = new ArrayList<Balise>();
		GSpace w = new GSpace("Baliseur", new Dimension(GlobaleVariable.sizeX, GlobaleVariable.sizeY));
		w.open();

		GRect mer = new GRect();
		mer.setColor(Color.blue);
		mer.setPosition(new Point(0,GlobaleVariable.sizeY-GlobaleVariable.hauteurMer));
		mer.setDimension(new Dimension(GlobaleVariable.sizeX,GlobaleVariable.hauteurMer));
		w.addElement(mer);
		
		int ecart = GlobaleVariable.sizeX/nbSat;
		
		for (int i = 0 ; i < nbSat ; i++) {
			GRect satellite = new GRect();
			satellite.setColor(Color.red);
			satellite.setPosition(new Point(ecart*i,10));
			satellite.setDimension(new Dimension(10,10));
			satellite.setBorderWidth(2);
			w.addElement(satellite);
			Satellite sat = new Satellite(satellite);
			sats.add(sat);
		}
		
		for (int i = 0 ; i < nbBalise ; i++) {
			GOval balise1 = new GOval();
			balise1.setPosition(new Point(300,GlobaleVariable.sizeY-GlobaleVariable.hauteurMer-5));
			balise1.setDimension(new Dimension(10,10));
			balise1.setColor(Color.black);
			w.addElement(balise1);
			Balise balise = new Balise(balise1);
			balises.add(balise);
		}
		
		while (true) {
			Tools.sleep(1000);
			for (int i = 0 ; i < sats.size() ; i++) {
				sats.get(i).step();
			}
			for (int i = 0 ; i < balises.size() ; i++) {
				balises.get(i).step();
			}
		}
	}
}