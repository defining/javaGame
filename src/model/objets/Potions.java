package model.objets;

import java.util.ArrayList;

public class Potions extends Objets {
	/**
	 * additionHealth donne de la vie supplementaire et l'interface InteractionObjets a ete implemente
	 */
	public static int additionnalHealth=10;
	public Potions(int X, int Y) {
		super(X, Y);
		this.type=6;
	}
	@Override
	public void InteractionWithObjets(ArrayList<? extends Objets> list) {
		for(int i=list.size()-1;i>=0;i--){
			if(this.getPosX()==list.get(i).getPosX() && this.getPosY()==list.get(i).getPosY()){
				list.remove(i);
				System.out.println("Espace ou pas, bye bye la potion!");
			}
		}
		
	}

}
