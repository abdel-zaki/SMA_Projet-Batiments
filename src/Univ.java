import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jade.core.AID;
import jade.core.Agent;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

/***************************************************************************
	Type : Agent
	Génére les Agents batiments
****************************************************************************/

public class Univ extends Agent{
	AID voisin;
	List<String> listBat = new ArrayList<String>();
	HashMap lesBatiments = new HashMap();
	int nbBat = 200;
	AgentContainer ac;
	AgentController a;
	Univ(Object ac, Object a){
		this.ac = (AgentContainer)ac;
		this.a = (AgentController)a;
	}
	public void setup(){
		// Générer les Agents batiments
		for(int indexBat=1; indexBat<=nbBat; indexBat++){
			Batiment agt = new Batiment();
			try {
				a = ac.acceptNewAgent("batiment"+indexBat, agt);
				listBat.add("batiment"+indexBat);
				a.start();
			} catch (StaleProxyException ex) {
				ex.printStackTrace();
			}
		}
		addBehaviour(new behaviourUniv(getAID().getLocalName(), lesBatiments));
	}
}
