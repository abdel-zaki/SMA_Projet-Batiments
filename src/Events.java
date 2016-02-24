import java.util.ArrayList;
import java.util.List;

import jade.core.AID;
import jade.core.Agent;
import jade.core.Runtime;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.*;

/***************************************************************************
	Type : Agent
	Possède un Behaviour écouteur des evennements clavier
****************************************************************************/

public class Events extends Agent{
	behaviourEvents bev;
	Events(Object bev)
	{
		this.bev = (behaviourEvents)bev;
	}
	public void setup(){
		addBehaviour(bev);
	}
}
