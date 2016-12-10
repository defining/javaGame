package model.objets;
import model.other.ObjetGenericGame;

/**
 * Classe abstraite qui sert au developpement futur de nouveaux equipement si ils ont des comportements identiques.
 * @param X Position en X a l'instanciation.
 * @param Y Position en Y a l'instanciation.
 */


public class Block extends ObjetGenericGame{
	public Block(int X, int Y){
		super(X,Y);
	}
}
