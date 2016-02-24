import java.util.HashMap;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

/***************************************************************************
	Type : CyclicBehaviour
	Répondre au demandes de :
	- Faire bouger le plan vers les directions haut, bas, droite ou gauche
	- Agrandir ou diminuer les dimentions des batiments
****************************************************************************/

public class behaviourIHM extends CyclicBehaviour{
	Dessin dessin;
	behaviourIHM(Object dessin){
		this.dessin = (Dessin)dessin;
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
			// Agrandir ou diminuer les dimentions des batiments
			if(obj instanceof HashMap){
				HashMap map = (HashMap)obj;
				dessin.map = map;
			}
			// Faire bouger le plan en appuiant sur les touches haut, bas, droite et gauche
			if(obj.equals("r"))
				dessin.root[0] += 10;
			if(obj.equals("l"))
				dessin.root[0] -= 10;
			if(obj.equals("t"))
				dessin.root[1] -= 10;
			if(obj.equals("b"))
				dessin.root[1] += 10;
			// rafraichir le dessin
			dessin.repaint();
		}
	}
}
