package model.objets;

import java.util.ArrayList;

/**
 * 
 * (Voir javadoc de la classe ArmesAttaque)
 *
 */

public class ArmesDefense extends Armes {
	public static int additionnalDefense=10;

	public ArmesDefense(int X, int Y) {
		super(X, Y);
		this.type=8;
	}

	@Override
	public void InteractionWithObjets(ArrayList<? extends Objets> list) {
		for(int i=list.size()-1;i>=0;i--){
			if(this.getPosX()==list.get(i).getPosX() && this.getPosY()==list.get(i).getPosY()){
				list.remove(i);
				System.out.println("Espace ou pas, bye bye l'armure!");
			}
		}
		
	}


}
