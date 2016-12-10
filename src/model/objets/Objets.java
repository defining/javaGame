package model.objets;

import model.interfaces.InteractionObjets;

public abstract class Objets extends Block  implements InteractionObjets{

	public boolean alive;
	public int type;

	public Objets(int X, int Y) {
		super(X, Y);
		this.attaquable=false;
		this.alive=true;
	}

}
