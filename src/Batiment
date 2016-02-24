import jade.core.AID;
import jade.core.Agent;

/***************************************************************************
	Type : Agent
	Représente un Agent batiment
****************************************************************************/

public class Batiment extends Agent{
	public static final int larg = 20;
	public static final int haut = 30;
	Batiment(){
		
	}
	AID voisin;
	public void setup(){
		addBehaviour(new behaviourBatCreate(getAID().getLocalName(), larg, haut));
		addBehaviour(new behaviourBatSend(getAID().getLocalName()));
		addBehaviour(new behaviourBatReceive(getAID().getLocalName()));
	}
}
