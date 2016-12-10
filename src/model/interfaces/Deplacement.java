package model.interfaces;

public interface Deplacement {
	/**
	 * Interface de deplacement implemente dans toutes les classes qui peuvent se deplacer. C'est-a-dire Player et Projectiles. 
	 * @param X deplacement en x si la methode movePlayer correspondante approuve le deplacement (si il n'y a pas d'obstacle).
	 * @param Y Meme chose pour le deplacement en y.
	 */
	public void move(int X,int Y);
	public void movePlayerRight();
	public void movePlayerLeft();
	public void movePlayerDown();
	public void movePlayerUp();

}
