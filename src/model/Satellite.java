package model;

import java.awt.Color;
import java.awt.Point;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import notification.Notifier;
import notification.PositionChange;
import notification.Synchronized;
import tools.GlobaleVariable;
import tools.Tools;
import vue.BaliseFrame;

public class Satellite extends SimulationElement {
	
	protected Point position;
	private ArrayList<Integer> memoire;
	private boolean locked;
	private BaliseFrame vue;
	
	public Satellite(Point position, BaliseFrame app, Notifier n) {
		this.memoire = new ArrayList<Integer>();
		this.position = position;
		this.locked = false;
		this.vue = app;
		this.notifier = n;
	}
	
	public Point getPosition() {
		return this.position;
	}
	
	public void addDataToMemory(int[] data) {
		List<Integer> list = Arrays.stream(data).boxed().collect(Collectors.toList());
		memoire.addAll(list);
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
			
			try {
				this.notifier.sendNotification(new PositionChange(this));
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
			this.vue.updateSatellitePosition(this);
		}
	}
	
	//Vérouiller le satellite lors d'une synchro (pas forcèment utile)
	public boolean lock() {
		if (!this.locked) {
			this.locked = true;
			try {
				//Une synchronization a lieu on envoie une notif (inutile dans ce cas car aucun object inscrit a synchronized)
				this.vue.updateSatColor(this, Color.orange);
				this.notifier.sendNotification(new Synchronized(this));
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}
	
	public void unlock() {
		this.vue.updateSatColor(this, Color.red);
		this.locked = false;
	}
}
