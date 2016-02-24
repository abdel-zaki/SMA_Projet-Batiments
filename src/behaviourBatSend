import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

/*******************************************************************************************
	Type : CyclicBehaviour
	Demande cycliquement à Univ la liste des voisins en collision avec le batiment courant
*******************************************************************************************/

public class behaviourBatSend extends CyclicBehaviour{
	String myAID;
	public behaviourBatSend(String myAID){
		this.myAID = myAID;
	}
	public void action(){
		ACLMessage msgVoisins = new ACLMessage(ACLMessage.INFORM);
		msgVoisins.addReceiver(new AID("univ", AID.ISLOCALNAME));
		String obj = "neighbors";
		msgVoisins.setContent(obj);
		myAgent.send(msgVoisins);
		block();
	}
}
