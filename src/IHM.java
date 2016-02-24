import java.util.HashMap;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.border.Border;

import jade.core.Agent;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.*;

/****************************************************
	Type : Agent
	Classe principale contenant la methode main()
	- Crée l'interface du programme
	- Instancie les classes Dessin, Univ et Events
****************************************************/

public class IHM extends Agent{
	int zoom = 0;
	// l'iHM ne fait qu'afficher les agents qui lui passent leurs positions
	static Dessin myDessin;
	public IHM(){
		
	}
	public static void main(String[]args) throws StaleProxyException{
		Runtime rt = Runtime.instance();
		rt.setCloseVM(true);
		Profile p = new ProfileImpl(null,1234,null);
		AgentContainer ac = rt.createMainContainer(p);
		AgentController a = null;
		// L'INTERFACE
		JFrame f = new JFrame("Application Multi-Agent v1.0");
		IHM me = new IHM();
		Dessin dessin = new Dessin();
		myDessin = dessin;
		dessin.setBounds(0,0,920,590);
		dessin.setBackground(Color.getHSBColor(0,0,0.4f));
		f.setLayout(null);
		f.setContentPane(dessin);
		f.setBackground(Color.getHSBColor(0,0,0.4f));
		Univ u = new Univ(ac, a);
		// Créer l'agent universel
		a = ac.acceptNewAgent("univ", u);
		a.start();
		behaviourEvents bev = new behaviourEvents(dessin, u.nbBat);
		Events ev = new Events(bev);
		f.addKeyListener(bev);
		Dimension SizeEcran = Toolkit.getDefaultToolkit().getScreenSize();
		f.setBounds((SizeEcran.width - 920) / 2, (SizeEcran.height - 590) / 2,920,590);
		f.setResizable(false);
		f.setDefaultCloseOperation(3);
		f.show();
		// Créer l'agent IHM
		a = ac.acceptNewAgent("IHM", me);
		a.start();
		// Créer l'agent Events
		a = ac.acceptNewAgent("events", ev);
		a.start();
	}
	
	public void setup(){
		addBehaviour(new behaviourIHM(myDessin));
	}
}
