import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

import java.awt.event.*;
import java.io.IOException;
import java.util.HashMap;

/***************************************************************************
	Type : CyclicBehaviour && implements KeyListener
	Repondre aux evennements clavier :
		- (+) : zoomer
		- (-) : dézoomer
		- (fleche haut) : bouger la caméra vers le haut
		- (fleche bas) : bouger la caméra vers le bas
		- (fleche droite) : bouger la caméra vers la droite
		- (fleche gauche) : bouger la caméra vers la gauche
****************************************************************************/

class behaviourEvents extends CyclicBehaviour implements KeyListener
{
	String myAID;
	Dessin dessin;
	long zoom = 0;
	int nbBat;
	behaviourEvents(Dessin dessin, int nbBat)
	{
		this.dessin = dessin;
		this.nbBat = nbBat;
	}
	public void action(){
		
	}
	public void keyPressed(KeyEvent e){
		if(e.getKeyCode() == 107){ // "+"
			if(zoom < 1){
				ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
				msg.addReceiver(new AID("univ", AID.ISLOCALNAME));
				HashMap map = (HashMap) dessin.map.clone();
				if(nbBat != map.size()) return;
				zoom++;
				try {
					msg.setContentObject(zoom(map, 1));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				myAgent.send(msg);
			}
		}
		if(e.getKeyCode() == 109){ // "-"
			if(zoom > -1){
				ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
				msg.addReceiver(new AID("univ", AID.ISLOCALNAME));
				HashMap map = (HashMap) dessin.map.clone();
				if(nbBat != map.size()) return;
				zoom--;
				try {
					msg.setContentObject(zoom(map, -1));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				myAgent.send(msg);
			}
		}
		if(e.getKeyCode() == 39){ // "->"
			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
			msg.addReceiver(new AID("IHM", AID.ISLOCALNAME));
			try {
				msg.setContentObject("r");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			myAgent.send(msg);
		}
		if(e.getKeyCode() == 37){ // "<-"
			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
			msg.addReceiver(new AID("IHM", AID.ISLOCALNAME));
			try {
				msg.setContentObject("l");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			myAgent.send(msg);
		}
		if(e.getKeyCode() == 38){ // "^"
			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
			msg.addReceiver(new AID("IHM", AID.ISLOCALNAME));
			try {
				msg.setContentObject("t");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			myAgent.send(msg);
		}
		if(e.getKeyCode() == 40){ // "v"
			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
			msg.addReceiver(new AID("IHM", AID.ISLOCALNAME));
			try {
				msg.setContentObject("b");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			myAgent.send(msg);
		}
		dessin.repaint();
	}
	public void keyReleased(KeyEvent e) {
		
	}
	public void keyTyped(KeyEvent e) {
		
	}
	// agrandir la taille des batiments
	public HashMap<String, int[]> zoom(HashMap<String, int[]>map, int inOut){
		String [] bat = (String[])map.keySet().toArray(new String[0]);
		for(int i=0; i<bat.length; i++){
			if(inOut == 1){
				((int[])map.get(bat[i]))[0] -= 5;
				((int[])map.get(bat[i]))[1] -= 5;
				((int[])map.get(bat[i]))[2] += 5;
				((int[])map.get(bat[i]))[3] += 5;
				((int[])map.get(bat[i]))[4] += 5;
				((int[])map.get(bat[i]))[5] -= 5;
				((int[])map.get(bat[i]))[6] -= 5;
				((int[])map.get(bat[i]))[7] += 5;
			}
			else{
				((int[])map.get(bat[i]))[0] += 5;
				((int[])map.get(bat[i]))[1] += 5;
				((int[])map.get(bat[i]))[2] -= 5;
				((int[])map.get(bat[i]))[3] -= 5;
				((int[])map.get(bat[i]))[4] -= 5;
				((int[])map.get(bat[i]))[5] += 5;
				((int[])map.get(bat[i]))[6] += 5;
				((int[])map.get(bat[i]))[7] -= 5;
			}
		}
		return map;
	}
}
