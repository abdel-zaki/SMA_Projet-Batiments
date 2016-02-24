import java.util.ArrayList;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

/*******************************************************************************************
	Type : CyclicBehaviour
	- Récupérer la liste des Batiments avec lesquels le batiment courant est en collision
	- Demander aux batiments en collision avec moi de se pousser
	- (Inversement) Demander à l'Agent "Univ" de nous chercher une autre position pour bouger
*******************************************************************************************/

public class behaviourBatReceive extends CyclicBehaviour{
	String myAID;
	Object obj = new Object();
	public behaviourBatReceive(String myAID){
		this.myAID = myAID;
	}
	public void action(){
		block();
		ACLMessage msg = myAgent.receive();
		if(msg != null){
			try {
				obj = (Object)msg.getContentObject();
			} catch (UnreadableException e1) {
				obj = msg.getContent();
			}
			// Récupérer la liste des Batiments avec lesquels on est en collision
			if(obj instanceof ArrayList<?>){
				ArrayList<String> map = (ArrayList<String>)obj;
				if(!map.isEmpty()){
					for(int i=0; i<map.size(); i++){
						// Leur demander de se pousser !
						ACLMessage msgPush = new ACLMessage(ACLMessage.INFORM);
						msgPush.addReceiver(new AID(map.get(i), AID.ISLOCALNAME));
						msgPush.setContent("push");
						myAgent.send(msgPush);
					}
				}
			}
			// Un batiment demande au batiment en cours de se pousser
			else if(obj.equals("push")){
				String senderID = msg.getSender().getLocalName();
				// Dire à Univ qu'on doit bouger de là
				ACLMessage msgVoisins = new ACLMessage(ACLMessage.INFORM);
				msgVoisins.addReceiver(new AID("univ", AID.ISLOCALNAME));
				String obj = "move";
				msgVoisins.setContent(obj);
				myAgent.send(msgVoisins);
			}
		}
	}
}
