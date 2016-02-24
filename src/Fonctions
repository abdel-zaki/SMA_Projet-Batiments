import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;

/***************************************************************************
	Type : Class
	Classe contenant les fonctions de calcules suivantes :
	- intersection()	: teste s'il y a une intersection de batiments
	- collision()		: calcule la surface de collision
	- surfaceDir()		: calcule la surface de collision en fonction de la direction du mouvement
	- rand(min, max)	: retourne un nombre aléatoire entre min et max
****************************************************************************/

public class Fonctions {
	int depl = 3;
	public Fonctions() {
		
	}
	// tester s'il y a une intersection
	public ArrayList<String> intersection(HashMap map, String AID){
		String [] voisins = (String[])map.keySet().toArray(new String[0]);
		int me = 0;
		ArrayList<String> intersection = new ArrayList<String>();
		for(int i=0; i<voisins.length; i++){
			if(voisins[i].equals(AID)) me = i;
		}
		int width = Math.abs(((int[])map.get(voisins[me]))[3])-Math.abs(((int[])map.get(voisins[me]))[0]);
		int height = Math.abs(((int[])map.get(voisins[me]))[4])-Math.abs(((int[])map.get(voisins[me]))[5]);
		Rectangle myRect = new Rectangle(((int[])map.get(voisins[me]))[1], ((int[])map.get(voisins[me]))[5], width, height);
		for(int j=0; j<voisins.length; j++){
			Rectangle hisRect = new Rectangle();
			if(me != j){
				int width2 = Math.abs(((int[])map.get(voisins[j]))[3])-Math.abs(((int[])map.get(voisins[j]))[0]);
				int height2 = Math.abs(((int[])map.get(voisins[j]))[4])-Math.abs(((int[])map.get(voisins[j]))[5]);
				hisRect = new Rectangle(((int[])map.get(voisins[j]))[1], ((int[])map.get(voisins[j]))[5], width2, height2);
			}
			if(myRect.intersects(hisRect)){
				intersection.add(voisins[j]);
			}
		}
		return intersection;
	}
	// calculer la surface de collision
	int collision(int[] myCoords, int[] hisCoords){
		int surface = 0;
		int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;
		for(int i=0; i<4; i++){
			if(myCoords[i] < minX) minX = myCoords[i];
			if(hisCoords[i] < minX) minX = hisCoords[i];
			if(myCoords[i] > maxX) maxX = myCoords[i];
			if(hisCoords[i] > maxX) maxX = hisCoords[i];
		}
		for(int i=4; i<8; i++){
			if(myCoords[i] < minY) minY = myCoords[i];
			if(hisCoords[i] < minY) minY = hisCoords[i];
			if(myCoords[i] > maxY) maxY = myCoords[i];
			if(hisCoords[i] > maxY) maxY = hisCoords[i];
		}
		int interLarg = 0, interHaut = 0;
		for(int i=minX; i<=maxX; i++){
			if((i >= myCoords[0]) && (i <= myCoords[3]) && (i >= hisCoords[0]) && (i <= hisCoords[3])){
				interLarg++;
			}
		}
		for(int i=minY; i<=maxY; i++){
			if((i >= myCoords[5]) && (i <= myCoords[4]) && (i >= hisCoords[5]) && (i <= hisCoords[4])){
				interHaut++;
			}
		}
		surface = interLarg * interHaut;
		return surface;
	}
	// la surface de collision en fonction de la direction du mouvement
	int surfaceDir(Object myCoordsTmp, HashMap<String, int[]> neighborsCoords, int dir){
		int [] myCoords = (int [])myCoordsTmp;
		int surface = 0;
		switch (dir){
			case 0 : // - haut
				myCoords[4] -= depl;
				myCoords[5] -= depl;
				myCoords[6] -= depl;
				myCoords[7] -= depl;
				break;
			case 1 : // - droite haut
				myCoords[0] += depl;
				myCoords[1] += depl;
				myCoords[2] += depl;
				myCoords[3] += depl;
				myCoords[4] -= depl;
				myCoords[5] -= depl;
				myCoords[6] -= depl;
				myCoords[7] -= depl;
				break;
			case 2 : // - droite
				myCoords[0] += depl;
				myCoords[1] += depl;
				myCoords[2] += depl;
				myCoords[3] += depl;
				break;
			case 3 : // - droite bas
				myCoords[0] += depl;
				myCoords[1] += depl;
				myCoords[2] += depl;
				myCoords[3] += depl;
				myCoords[4] += depl;
				myCoords[5] += depl;
				myCoords[6] += depl;
				myCoords[7] += depl;
				break;
			case 4 : // - bas
				myCoords[4] += depl;
				myCoords[5] += depl;
				myCoords[6] += depl;
				myCoords[7] += depl;
				break;
			case 5 : // - gauche bas
				myCoords[0] -= depl;
				myCoords[1] -= depl;
				myCoords[2] -= depl;
				myCoords[3] -= depl;
				myCoords[4] += depl;
				myCoords[5] += depl;
				myCoords[6] += depl;
				myCoords[7] += depl;
				break;
			case 6 : // - gauche
				myCoords[0] -= depl;
				myCoords[1] -= depl;
				myCoords[2] -= depl;
				myCoords[3] -= depl;
				break;
			case 7 : // - gauche haut
				myCoords[0] -= depl;
				myCoords[1] -= depl;
				myCoords[2] -= depl;
				myCoords[3] -= depl;
				myCoords[4] -= depl;
				myCoords[5] -= depl;
				myCoords[6] -= depl;
				myCoords[7] -= depl;
				break;
		}
		String [] voisinsID = neighborsCoords.keySet().toArray(new String[0]);
		for(int i=0; i<voisinsID.length; i++){
			int [] voisins = neighborsCoords.get(voisinsID[i]);
			surface += collision(myCoords, voisins);
		}
		return surface;
	}
	// random entre min et max
	int rand(int Min, int Max){
		return Min + (int)(Math.random() * ((Max - Min) + 1));
	}
}
