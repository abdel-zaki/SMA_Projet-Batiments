import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

/*******************************************************************************
	Type : CyclicBehaviour
	- Enregistrer les coordonnés envoyés par un Agent batiment dans une HashMap
	- Envoi à l'Agent IHM la dernière version de la HashMap
	- Répondre à un Agent batiment qui veut savoir ses voisins en collision avec lui
	- Lorsqu'un batiment veut bouger :
		- Chercher la direction du mouvement qui minimise la surface de collision
		- Mettre à jour les coords du batiment
		- Informer l'IHM des modifications faites
	- Appliquer l'effet de zoom parvenant de l'Agent Events
*******************************************************************************/

public class behaviourUniv extends CyclicBehaviour{
	String myAID;
	HashMap lesBatiments;
	long zoom = 0;
	Fonctions fun = new Fonctions();
	public behaviourUniv(String myAID, HashMap lesBatiments){
		this.myAID = myAID;
		this.lesBatiments = lesBatiments;
	}
	public void action(){
		ACLMessage msg = myAgent.receive();
		if(msg != null){
			Object obj = new Object();
			try {
				obj = msg.getContentObject();
			} catch (UnreadableException e1) {
				obj = msg.getContent();
			}
			// coordonnés envoyés par un agent batiment crée
			if(obj instanceof int[]){
				String senderID = msg.getSender().getLocalName();
				lesBatiments.put(senderID, obj);
				// informer l'IHM
				ACLMessage msgIHM = new ACLMessage(ACLMessage.INFORM);
				msgIHM.addReceiver(new AID("IHM", AID.ISLOCALNAME));
				try{
					msgIHM.setContentObject(lesBatiments);
				}catch (IOException ex){
					ex.printStackTrace();
				}
				myAgent.send(msgIHM);
			}
			// un batiment demande qui sont ses voisins en collision avec lui
			if(obj.equals("neighbors")){
				String senderID = msg.getSender().getLocalName();
				ACLMessage msgBat = new ACLMessage(ACLMessage.INFORM);
				msgBat.addReceiver(new AID(senderID, AID.ISLOCALNAME));
				ArrayList<String> map = fun.intersection(lesBatiments, senderID);
				try{
					msgBat.setContentObject(map);
				}catch (IOException ex){
					ex.printStackTrace();
				}
				myAgent.send(msgBat);
			}
			// un batiment veux bouger
			if(obj.equals("move")){
				String senderID = msg.getSender().getLocalName();
				ACLMessage msgBat = new ACLMessage(ACLMessage.INFORM);
				int [] coords = (int[]) lesBatiments.get(senderID);
				int [] tmpCoords = new int [8];
				for(int i=0; i<8; i++){
					tmpCoords[i] = coords[i];
				}
				HashMap<String, int[]> neighborsCoords = lesBatiments;
				// la surface de collision
				int surface = fun.surfaceDir(tmpCoords, neighborsCoords, -1);
				// simuler les movements possibles (vers 8 directions)
				int [] surfaces = new int [8];
				int tmp = Integer.MAX_VALUE;
				ArrayList<Integer> direction = new ArrayList<Integer>();
				for(int i=0; i<8; i++){
					int [] tmpTab = new int [8];
					for(int j=0; j<8; j++){
						tmpTab[j] = tmpCoords[j];
					}
					surfaces[i] = fun.surfaceDir(tmpTab, neighborsCoords, i);
					if(tmp > surfaces[i]){
						tmp = surfaces[i];
						direction.clear();
						direction.add(i);
					}
					if(tmp == surfaces[i]){
						direction.add(i);
					}
				}
				int dir = fun.rand(0, direction.size()-1);
				// choisir la direction qui minimise la surface de collision
				if(surface > fun.surfaceDir(tmpCoords, neighborsCoords, direction.get(dir))){
					fun.surfaceDir(coords, neighborsCoords, direction.get(dir));
					// mettre à jour les coords du batiment
					lesBatiments.put(senderID, coords);
					// informer l'IHM des modif faites
					msgBat.addReceiver(new AID("IHM", AID.ISLOCALNAME));
					try{
						msgBat.setContentObject(lesBatiments);
					}catch (IOException ex){
						ex.printStackTrace();
					}
					myAgent.send(msgBat);
				}
			}
			// zoom +/-
			if(obj instanceof HashMap){
				lesBatiments = (HashMap)obj;
			}
		}
	}
}
