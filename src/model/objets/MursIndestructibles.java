package model.objets;

public final class MursIndestructibles extends Block{
	
	/**
	 * Bloc qui constitue la cloture du plateau de jeu. Son attribut fait qu'il ne peut etre attaque.
	 * @param X
	 * @param Y
	 */

	public MursIndestructibles(int X, int Y) {
		super(X, Y);
		this.attaquable=false;
	}

}
