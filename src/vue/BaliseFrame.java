package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import graphicLayer.GRect;
import graphicLayer.GSpace;
import tools.GlobaleVariable;

public class BaliseFrame {
	
	public BaliseFrame() {
		GSpace w = new GSpace("Baliseur", new Dimension(GlobaleVariable.sizeX, GlobaleVariable.sizeY));
		w.open();

		GRect mer = new GRect();
		mer.setColor(Color.blue);
		mer.setPosition(new Point(0,GlobaleVariable.sizeY-GlobaleVariable.hauteurMer));
		mer.setDimension(new Dimension(GlobaleVariable.sizeX,GlobaleVariable.hauteurMer));
		w.addElement(mer);
	}

}
