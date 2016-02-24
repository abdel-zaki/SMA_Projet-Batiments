import java.awt.*;
import java.util.HashMap;

import javax.swing.*;

/***************************************************************************
	Type : JPanel
	Dessine les rectangles des Agents batiments
****************************************************************************/

class Dessin extends JPanel
{
	// Matrice des rectangles des agents Batiment
	HashMap map = new HashMap();
	int [] root = {0, 0};
	Dessin()
	{}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		String [] voisins = (String[])map.keySet().toArray(new String[0]);
		for(int i=0; i<voisins.length; i++){
			int [] x = {((int[])map.get(voisins[i]))[0]-root[0],
						((int[])map.get(voisins[i]))[1]-root[0],
						((int[])map.get(voisins[i]))[2]-root[0],
						((int[])map.get(voisins[i]))[3]-root[0]};
			int [] y = {((int[])map.get(voisins[i]))[4]-root[1],
						((int[])map.get(voisins[i]))[5]-root[1],
						((int[])map.get(voisins[i]))[6]-root[1],
						((int[])map.get(voisins[i]))[7]-root[1]};
			g.setColor(new Color(100,200,100));
			g.fillPolygon(x,y,4);
			g.setColor(new Color(0,250,0));
			g.drawPolygon(x,y,4);
		}
	}
}
