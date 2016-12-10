package model.interfaces;

public interface Subject {
	/**
	 * Sujet. Patron de conception structurel "Observer" qui necessite l'implementation d'une methode attach et notify. Ici implemente dans Game.
	 * @param o - pour la liaison avec un observateur.
	 */
	void attach(Observer o);
	void notifyObservers();
}
