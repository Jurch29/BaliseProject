package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map.Entry;

import graphicLayer.GOval;
import graphicLayer.GRect;
import graphicLayer.GSpace;
import model.Balise;
import model.Satellite;
import tools.GlobaleVariable;

public class BaliseFrame {
	
	private HashMap<Balise,GOval> balises;
	private HashMap<Satellite,GRect> satellites;
	private GSpace world;
	
	public BaliseFrame() {
		this.balises = new HashMap<Balise,GOval>();
		this.satellites = new HashMap<Satellite,GRect>();
		this.world = new GSpace("Baliseur", new Dimension(GlobaleVariable.sizeX, GlobaleVariable.sizeY));
		this.world.open();

		GRect mer = new GRect();
		mer.setColor(Color.blue);
		mer.setPosition(new Point(0,GlobaleVariable.sizeY-GlobaleVariable.hauteurMer));
		mer.setDimension(new Dimension(GlobaleVariable.sizeX,GlobaleVariable.hauteurMer));
		this.world.addElement(mer);
	}
	
	public void addBalise(Balise b, Point position) {
		GOval baliseDraw = new GOval();
		baliseDraw.setPosition(position);
		baliseDraw.setDimension(new Dimension(10,10));
		baliseDraw.setColor(Color.black);
		this.world.addElement(baliseDraw);
		this.balises.put(b, baliseDraw);
	}
	
	public void addSatellite(Satellite s, Point position) {
		GRect satellite = new GRect();
		satellite.setColor(Color.red);
		satellite.setPosition(position);
		satellite.setDimension(new Dimension(10,10));
		satellite.setBorderWidth(2);
		this.world.addElement(satellite);
		this.satellites.put(s, satellite);
	}
	
	public void update() {
		//parcours des 2 hahmaps et redraw avec la position ds l'objet
		for(Entry<Balise, GOval> entry : this.balises.entrySet()) {
			Balise cle = entry.getKey();
			GOval valeur = entry.getValue();
			
			valeur.setPosition(cle.getPosition());
		}
		
		for(Entry<Satellite, GRect> entry : this.satellites.entrySet()) {
			Satellite cle = entry.getKey();
			GRect valeur = entry.getValue();
			
			valeur.setPosition(cle.getPosition());
		}
	}
	
	public void updateBalisePosition(Balise b) {
		GOval baliseDraw = this.balises.get(b);
		baliseDraw.setPosition(b.getPosition());
	}

	public void updateSatellitePosition(Satellite sat) {
		GRect satDraw = this.satellites.get(sat);
		satDraw.setPosition(sat.getPosition());
	}
	
	public void updateBaliseColor(Balise b, Color c) {
		GOval baliseDraw = this.balises.get(b);
		baliseDraw.setColor(c);
	}
	
	public void updateSatColor(Satellite s, Color c) {
		GRect SatDraw = this.satellites.get(s);
		SatDraw.setColor(c);
	}
}
