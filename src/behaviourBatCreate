import java.awt.Rectangle;
import java.io.IOException;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

/***************************************************************************
	Type : OneShotBehaviour
	- Génère aléatoirement une seule fois les coordonnées d'un rectangle
	- Envoi ces coordonnées à l'Agent Univ
****************************************************************************/

public class behaviourBatCreate extends OneShotBehaviour{
	String myAID;
	int larg;
	int haut;
	public behaviourBatCreate(String myAID, int larg, int haut){
		this.myAID = myAID;
		this.larg = larg;
		this.haut = haut;
	}
	public void action(){
		ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
		msg.addReceiver(new AID("univ", AID.ISLOCALNAME));
		int coods[] = {rand(0,900-haut), rand(0,570-larg), rand(0,900-larg), rand(0,570-haut)};
		int [] rec = new int[8];
		if(rand(0,1) == 1){
			int [] rectmp = {coods[0], coods[0], coods[0]+haut, coods[0]+haut, coods[1]+larg, coods[1], coods[1], coods[1]+larg};
			rec = rectmp;
		}
		else{
			int [] rectmp = {coods[2], coods[2], coods[2]+larg, coods[2]+larg, coods[3]+haut, coods[3], coods[3], coods[3]+haut};
			rec = rectmp;
		}
		try{
			msg.setContentObject(rec);
		}catch (IOException e){
			e.printStackTrace();
		}
		myAgent.send(msg);
	}
	// Fonction random génère un nombre entre min et max
	int rand(int Min, int Max){
		return Min + (int)(Math.random() * ((Max - Min) + 1));
	}
}
