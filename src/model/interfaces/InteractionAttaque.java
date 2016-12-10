package model.interfaces;

public interface InteractionAttaque {
	/**
	 * Methode hurt implemente dans - ObjetGenericGame - mere de toutes les classes car tous les objets peuvent potentiellement etre attaque.
	 * @param attack (points d'attaque que va subir la cible)
	 */
	public void hurt(int attack);
}
