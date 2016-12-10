package model.objets;

import java.util.ArrayList;

/**
 * 
 * Contient l'interaction de l'objet lorsque le player passe dessus
 *
 */

public class ArmesAttaque extends Armes {
	public static int additionnalAttak=10; 

	public ArmesAttaque(int X, int Y) {
		super(X, Y);
		this.type=7;
	}
	
	/**
	 * @param liste d'objets sur la map
	 * 
	 * Si son emplacement correspond a celui du PlayerPrincipal, il sera supprime de la liste (et ajoute a l'inventaire du joueur).
	 * 
	 * L'attribut additionalAttak est static pour qu'il soit accessible par la classe PlayerPrincipal mais on peut imaginer un systeme moins static a l'avenir
	 * 
	 */

	@Override
	public void InteractionWithObjets(ArrayList<? extends Objets> list) {
		for(int i=list.size()-1;i>=0;i--){
			if(this.getPosX()==list.get(i).getPosX() && this.getPosY()==list.get(i).getPosY()){
				list.remove(i);
				System.out.println("Espace ou pas, bye bye l'arme d'attaque!");
			}
		}
		
	}


}
