package tools;

import java.util.ArrayList;

import model.Satellite;

public class Communication {
	
	private static ArrayList<Satellite> sats = new ArrayList<Satellite>();

	public static boolean emission(int x, int[] data) {
		// TODO Auto-generated method stub
		for (int i = 0 ; i < sats.size() ; i++) {
			if (x>sats.get(i).getPosition().x-10 && x<sats.get(i).getPosition().y+10) {
				//On est dans une zone de réception du satellite (i)
				System.out.println("Zone de réception");
				sats.get(i).addDataToMemory(data);
				return true;
			}
		}
		return false;
	}
	
	public static void reception(Satellite sat) {
		if (!sats.contains(sat)) {
			sats.add(sat);
		}
	}

}
