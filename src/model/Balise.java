package model;

import java.awt.Color;
import java.awt.Point;
import java.util.HashMap;

import deplacement.Deplacement;
import deplacement.Direction;
import deplacement.Verticale;
import notification.Notification;
import notification.NotificationRegistration;
import notification.Notifier;
import notification.PositionChange;
import phase.Mouvement;
import phase.Phase;
import tools.GlobaleVariable;
import tools.Tools;
import vue.BaliseFrame;

public class Balise extends SimulationElement {

	private Point position;
	private int data[];
	private int profondeur;
	private int nbData;
	private Phase phase;
	private Deplacement dep;
	private boolean baliseRun;
	private BaliseFrame vue;
	private HashMap<Class<? extends Notification>, NotificationRegistration> notifMaps;
	
	public Balise(Point p, BaliseFrame app, Notifier n) {
		this.data = new int[10];
		this.position = p;
		this.profondeur = 0;
		this.nbData = 0;
		this.baliseRun = true;
		this.vue = app;
		this.notifier = n;
		this.notifMaps = new HashMap<Class<? extends Notification>, NotificationRegistration>();
	}
	
	public int[] getData() {
		return data;
	}

	public void setData(int[] data) {
		this.data = data;
	}
	
	public Phase getPhase() {
		return phase;
	}
	
	public void setPhase(Phase phase) {
		this.phase = phase;
	}
	
	public Deplacement getDep() {
		return dep;
	}

	public void setDep(Deplacement dep) {
		this.dep = dep;
	}
	
	public void setPosition(Point position) {
		this.position = position;
	}
	
	public Point getPosition() {
		return position;
	}
	
	public int getNbData() {
		return this.nbData;
	}

	public int getProfondeur() {
		return this.profondeur;
	}
	
	public void addProfondeur(int profondeur) {
		this.profondeur += profondeur;
		this.phase = new Mouvement(new Verticale(this.profondeur, Direction.Bas));
	}
	
	public boolean isMemoryEmpty() {
		return this.nbData == 0;
	}
	
	public boolean isMemoryFull() {
		return this.nbData==9;
	}
	
	public void addData(int data) {
		this.data[nbData] = data;
		this.nbData++;
	}

	public void resetData() {
		this.data = new int[10];
		this.nbData = 0;
	}

	@Override
	public void run() {
		while (this.baliseRun) {
			Tools.sleep(GlobaleVariable.vitesseSimulation);
			this.phase.step(this);
			this.phase = this.phase.nextPhase(this);
			this.vue.updateBalisePosition(this);
		}
	}

	public void synchroReady() throws NoSuchMethodException, SecurityException {
		//ajout dans le notifier + ds notre maps de notifs
		this.vue.updateBaliseColor(this, Color.green);
		NotificationRegistration nr = new NotificationRegistration(this, this.getClass().getMethod("tryToSynchronizeWith", Notification.class));
		this.notifMaps.put(PositionChange.class, nr);
		this.notifier.addListener(PositionChange.class,nr);
	}

	public void tryToSynchronizeWith(Notification n) throws NoSuchMethodException, SecurityException {
		Satellite s = (Satellite) n.getSource();
		if (this.position.x>s.getPosition().x-10 && this.position.x<s.getPosition().x+10) {
			//On est dans une zone de r�ception du satellite
			if (s.lock()) {
				s.addDataToMemory(this.getData());
				s.unlock();
				this.resetData();
				this.notifier.removeListener(PositionChange.class,this.notifMaps.get(PositionChange.class));
				this.vue.updateBaliseColor(this, Color.black);
			}
		}
	}
}
