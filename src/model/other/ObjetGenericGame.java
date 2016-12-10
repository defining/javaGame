package model.other;

import model.interfaces.InteractionAttaque;

public abstract class ObjetGenericGame implements InteractionAttaque {

	protected int posX; // Position en X
	protected int posY;
	protected int PresenceUp= 0; //0 représente un bloc vide. Si != 0 alors présence de quelqu'un ou quelque chose
	protected int PresenceDown= 0;
	protected int PresenceRight=0;
	protected int PresenceLeft=0;
	public int CoordonneePresenceUpX; //Coordonnée de l'objet en X sur la case du dessus
	public int CoordonneePresenceUpY;
	public int CoordonneePresenceDownX;
	public int CoordonneePresenceDownY;
	public int CoordonneePresenceLeftX;
	public int CoordonneePresenceLeftY;
	public int CoordonneePresenceRightX;
	public int CoordonneePresenceRightY;
	public char lastPosition; // Derniere position: 'l'= left, etc. 
	protected int health; //vie de l'objet
	public boolean alive; //Ce qui indique à la map si le perso doit être enlevée ou pas
	public boolean attaquable; // Si bloc indestructible alors attaquable = false
	public int experienceToWin;//le nombre de points d'experience que le joueur gagne à tuer ce monstre
	
	protected ObjetGenericGame(int X, int Y){
		this.posX = X;
		this.posY = Y;
		this.lastPosition='d';
		this.alive=true;
	}
	/**
	 * Getters de posX
	 * @return
	 */
	public int getPosX(){
		return this.posX;
	}
	
	public int getPosY(){
		return this.posY;
	}
	
	// Toutes les méthodes qui permettent de lire si la case adjacente est libre ou pas
	
	/**
	 * Setters de posX
	 * @param posX
	 */
	public void setPosX(int posX){
		 this.posX = posX;
	}
	
	public void setPosY(int posY){
		 this.posY = posY;
	}
	
	public int isCanMoveUp() {
		return PresenceUp;
	}
	
	
	//En paramètre: le personage sur la case et ses coordonnées
	public void setCanMoveUp(int PresenceUp, int x, int y) {
		this.PresenceUp = PresenceUp; //c'est la ligne qui permet de ne pas traverser un objet
		this.CoordonneePresenceUpX= x;
		this.CoordonneePresenceUpY= y;
	}

	public int isCanMoveDown() {
		return PresenceDown;
	}

	public void setCanMoveDown(int PresenceDown, int x, int y) {
		this.PresenceDown = PresenceDown;
		this.CoordonneePresenceDownX= x;
		this.CoordonneePresenceDownY= y;
	}

	public int isCanMoveRight() {
		return PresenceRight;
	}

	public void setCanMoveRight(int PresenceRight, int x, int y) {
		this.PresenceRight = PresenceRight;
		this.CoordonneePresenceRightX= x;
		this.CoordonneePresenceRightY= y;
	}
	/**
	 * @return l'identifiant de la case (si 0 alors case vide, etc)
	 */
	public int isCanMoveLeft() {
		return PresenceLeft;
	}

	/**
	 * Setter des différents paramètres pour localiser un objet (ici à gauche)
	 * @param PresenceLeft
	 * @param x
	 * @param y
	 */
	public void setCanMoveLeft(int PresenceLeft, int x, int y) {
		this.PresenceLeft = PresenceLeft;
		this.CoordonneePresenceLeftX= x;
		this.CoordonneePresenceLeftY= y;
	}
	/**
	 * Quand un objet recoit des attaques ca invoque la méthode "hurt()"
	 * Principe d'encapsulation pour que n'importe quelle valeur ne soit pas communiquée.
	 */
	public void hurt(int attack){
		this.setDamage(attack);
		}
	public void setDamage(int damage){
		if(damage<=0){
			System.out.println("Trop grande défense, tu ne le touches pas!");
		}
		else{
			this.setHealth(damage);
			}
		}
	
	public int getHealth(){
		return this.health;
	}
	
	/**
	 * Setter de la santé pour respecter l'encapsulation
	 * @param valeur
	 */
	
	public void setHealth(int valeur){
		
		if (this.health-valeur<=0){
			this.health=0;
			this.alive=false;
			System.out.println("Dead!");
		}
			else{
			this.health-=valeur;
			}
		
	}
		
	}