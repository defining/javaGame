package model.interfaces;


import model.objets.Objets;
/**
 * Implemente dans - PlayerPrincpal - et dans les objets qu'on peut ramasser. La methode est polymorphe. 
 * @param ArrayList d'objets. Soit liste de potions, soit d'armes soit d'armures
 *
 */
public interface InteractionObjets {
	public int type=0 ;
	public void InteractionWithObjets(java.util.ArrayList<? extends Objets> list);
}
