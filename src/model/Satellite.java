package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import tools.Communication;
import tools.GlobaleVariable;

public class Satellite extends Thread {
	
	Point position;
	private ArrayList<Integer> memoire;
	
	public Satellite(Point position) {
		// TODO Auto-generated constructor stub
		this.memoire = new ArrayList<Integer>();
		this.position = position;
	}
	
	public Point getPosition() {
		return this.position;
	}
	
	public void addDataToMemory(int[] data) {
		List<Integer> list = Arrays.stream(data).boxed().collect(Collectors.toList());
		memoire.addAll(list);
	}
	
	public void run() {
		if (this.position.x+GlobaleVariable.vitesseSat>GlobaleVariable.sizeX) {
			this.position = new Point(this.position.x+GlobaleVariable.vitesseSat-GlobaleVariable.sizeX,this.position.y);
		}
		else
			this.position = new Point(this.position.x+GlobaleVariable.vitesseSat,this.position.y);
		
		Communication.reception(this);
	}
}
