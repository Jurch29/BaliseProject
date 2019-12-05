package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import notification.PositionChange;
import tools.Communication;
import tools.GlobaleVariable;
import tools.Observable;
import tools.Observer;
import tools.Tools;
import vue.BaliseFrame;

public class Satellite extends SimulationElement implements Observable {
	
	Point position;
	private ArrayList<Integer> memoire;
	
	private boolean locked;
	private ArrayList<Observer> balises;
	private BaliseFrame vue;
	
	public Satellite(Point position, BaliseFrame app) {
		// TODO Auto-generated constructor stub
		this.memoire = new ArrayList<Integer>();
		this.position = position;
		
		this.locked = false;
		this.vue = app;
		this.balises = new ArrayList<Observer>();
	}
	
	public Point getPosition() {
		return this.position;
	}
	
	public void addDataToMemory(int[] data) {
		List<Integer> list = Arrays.stream(data).boxed().collect(Collectors.toList());
		memoire.addAll(list);
		this.locked = false;
	}
	
	@Override
	public void run() {
		while (true) {
			Tools.sleep(GlobaleVariable.vitesseSimulation);
			if (this.position.x+GlobaleVariable.vitesseSat>GlobaleVariable.sizeX) {
				this.position = new Point(this.position.x+GlobaleVariable.vitesseSat-GlobaleVariable.sizeX,this.position.y);
			}
			else
				this.position = new Point(this.position.x+GlobaleVariable.vitesseSat,this.position.y);
			
//			this.notifyObservers();
			this.notifyObservers(new PositionChange(this));
			this.vue.updateSatellite(this);
		}
	}
	
	public boolean lock() {
		if (!this.locked) {
			this.locked = true;
			return true;
		}
		return false;
	}

	@Override
	public void register(Observer o) {
		//TODO Auto-generated method stub
		if (!this.balises.contains(o))
			this.balises.add(o);
	}

	@Override
	public void unregister(Observer o) {
		//TODO Auto-generated method stub
		this.balises.remove(o);
	}

	@Override
	public void notifyObservers() {
		//TODO Auto-generated method stub
		for (int i = 0 ; i < this.balises.size() ; i++) {
			this.balises.get(i).updateFrom(this);
		}
	}

	@Override
	public void notifyObservers(Object arg) {
		//TODO Auto-generated method stub
		for (int i = 0 ; i < this.balises.size() ; i++) {
			this.balises.get(i).updateFrom(this, arg);
		}
	}
}
