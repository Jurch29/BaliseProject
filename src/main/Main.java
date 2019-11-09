package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import graphicLayer.GOval;
import graphicLayer.GRect;
import graphicLayer.GSpace;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		drawWorld();
		GSpace w = new GSpace("Baliseur", new Dimension(800, 600));
		w.open();
		
		GRect mer = new GRect();
		mer.setColor(Color.blue);
		mer.setPosition(new Point(0,400));
		mer.setDimension(new Dimension(800,200));
		w.addElement(mer);
		
		GOval balise1 = new GOval();
		balise1.setPosition(new Point(300,500));
		balise1.setDimension(new Dimension(10,10));
		balise1.setColor(Color.black);
		w.addElement(balise1);
		
		GRect satellite = new GRect();
		satellite.setColor(Color.red);
		satellite.setPosition(new Point(10,10));
		satellite.setDimension(new Dimension(10,10));
		satellite.setBorderWidth(2);
		w.addElement(satellite);
		
		GRect satellite2 = new GRect();
		satellite2.setColor(Color.red);
		satellite2.setPosition(new Point(30,30));
		satellite2.setDimension(new Dimension(10,10));
		satellite2.setBorderWidth(2);
		w.addElement(satellite2);
		
		GRect satellite3 = new GRect();
		satellite3.setColor(Color.red);
		satellite3.setPosition(new Point(90,120));
		satellite3.setDimension(new Dimension(10,10));
		satellite3.setBorderWidth(2);
		w.addElement(satellite3);
		
		satellite2.setPosition(new Point(90,30));
	}
}